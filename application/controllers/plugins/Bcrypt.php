<?php
#
# Classe adaptée a partir d'un composant de phpass
#
final class Application_Controller_Plugin_Bcrypt extends Zend_Controller_Plugin_Abstract {
	
    const DEFAULT_WORK_FACTOR = 8;
    
    private $__itoa64;
    private $__iteration_count_log2;
    private $__portable_hashes;
    private $__random_state;

    public function __construct($iteration_count_log2 = self::DEFAULT_WORK_FACTOR, $portable_hashes = FALSE) {
        $this->__itoa64 = './0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
        
        if ($iteration_count_log2 < 4 || $iteration_count_log2 > 31) {
            $iteration_count_log2 = self::DEFAULT_WORK_FACTOR;
        }
			
		$this->__iteration_count_log2 = $iteration_count_log2;

		$this->__portable_hashes = $portable_hashes;

		$this->__random_state = microtime();
		if (function_exists('getmypid'))
			$this->__random_state .= getmypid();
	}

        public function preDispatch(Zend_Controller_Request_Abstract $request) {
            
        }
        
	private function __get_random_bytes($count)
	{
		$output = '';
		if (is_readable('/dev/urandom') &&
		    ($fh = @fopen('/dev/urandom', 'rb'))) {
			$output = fread($fh, $count);
			fclose($fh);
		}

		if (strlen($output) < $count) {
			$output = '';
			for ($i = 0; $i < $count; $i += 16) {
				$this->__random_state =
				    md5(microtime() . $this->__random_state);
				$output .=
				    pack('H*', md5($this->__random_state));
			}
			$output = substr($output, 0, $count);
		}

		return $output;
	}

	private function __encode64($input, $count)
	{
		$output = '';
		$i = 0;
		do {
			$value = ord($input[$i++]);
			$output .= $this->__itoa64[$value & 0x3f];
			if ($i < $count)
				$value |= ord($input[$i]) << 8;
			$output .= $this->__itoa64[($value >> 6) & 0x3f];
			if ($i++ >= $count)
				break;
			if ($i < $count)
				$value |= ord($input[$i]) << 16;
			$output .= $this->__itoa64[($value >> 12) & 0x3f];
			if ($i++ >= $count)
				break;
			$output .= $this->__itoa64[($value >> 18) & 0x3f];
		} while ($i < $count);

		return $output;
	}

	private function __gensalt_private($input)
	{
		$output = '$P$';
		$output .= $this->__itoa64[min($this->__iteration_count_log2 +
			((PHP_VERSION >= '5') ? 5 : 3), 30)];
		$output .= $this->__encode64($input, 6);

		return $output;
	}

	private function __crypt_private($password, $setting)
	{
		$output = '*0';
		if (substr($setting, 0, 2) == $output)
			$output = '*1';

		$id = substr($setting, 0, 3);
		# We use "$P$", phpBB3 uses "$H$" for the same thing
		if ($id != '$P$' && $id != '$H$')
			return $output;

		$count_log2 = strpos($this->__itoa64, $setting[3]);
		if ($count_log2 < 7 || $count_log2 > 30)
			return $output;

		$count = 1 << $count_log2;

		$salt = substr($setting, 4, 8);
		if (strlen($salt) != 8)
			return $output;

		# We're kind of forced to use MD5 here since it's the only
		# cryptographic primitive available in all versions of PHP
		# currently in use.  To implement our own low-level crypto
		# in PHP would result in much worse performance and
		# consequently in lower iteration counts and hashes that are
		# quicker to crack (by non-PHP code).
		if (PHP_VERSION >= '5') {
			$hash = md5($salt . $password, TRUE);
			do {
				$hash = md5($hash . $password, TRUE);
			} while (--$count);
		} else {
			$hash = pack('H*', md5($salt . $password));
			do {
				$hash = pack('H*', md5($hash . $password));
			} while (--$count);
		}

		$output = substr($setting, 0, 12);
		$output .= $this->__encode64($hash, 16);

		return $output;
	}

	private function __gensalt_extended($input)
	{
		$count_log2 = min($this->__iteration_count_log2 + 8, 24);
		# This should be odd to not reveal weak DES keys, and the
		# maximum valid value is (2**24 - 1) which is odd anyway.
		$count = (1 << $count_log2) - 1;

		$output = '_';
		$output .= $this->__itoa64[$count & 0x3f];
		$output .= $this->__itoa64[($count >> 6) & 0x3f];
		$output .= $this->__itoa64[($count >> 12) & 0x3f];
		$output .= $this->__itoa64[($count >> 18) & 0x3f];

		$output .= $this->__encode64($input, 3);

		return $output;
	}

	private function __gensalt_blowfish($input)
	{
		# This one needs to use a different order of characters and a
		# different encoding scheme from the one in encode64() above.
		# We care because the last character in our encoded string will
		# only represent 2 bits.  While two known implementations of
		# bcrypt will happily accept and correct a salt string which
		# has the 4 unused bits set to non-zero, we do not want to take
		# chances and we also do not want to waste an additional byte
		# of entropy.
		$itoa64 = './ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

		$output = '$2a$';
		$output .= chr(ord('0') + $this->__iteration_count_log2 / 10);
		$output .= chr(ord('0') + $this->__iteration_count_log2 % 10);
		$output .= '$';

		$i = 0;
		do {
			$c1 = ord($input[$i++]);
			$output .= $itoa64[$c1 >> 2];
			$c1 = ($c1 & 0x03) << 4;
			if ($i >= 16) {
				$output .= $itoa64[$c1];
				break;
			}

			$c2 = ord($input[$i++]);
			$c1 |= $c2 >> 4;
			$output .= $itoa64[$c1];
			$c1 = ($c2 & 0x0f) << 2;

			$c2 = ord($input[$i++]);
			$c1 |= $c2 >> 6;
			$output .= $itoa64[$c1];
			$output .= $itoa64[$c2 & 0x3f];
		} while (1);

		return $output;
	}

	public function HashPassword($password)
	{
            $random = '';

            if (CRYPT_BLOWFISH == 1 && !$this->__portable_hashes) {
                    $random = $this->__get_random_bytes(16);
                    $hash = crypt($password, $this->__gensalt_blowfish($random));
                    if (strlen($hash) == 60)
                        return $hash;
            }

            if (CRYPT_EXT_DES == 1 && !$this->__portable_hashes) {
                    if (strlen($random) < 3)
                        $random = $this->__get_random_bytes(3);
                    $hash = crypt($password, $this->__gensalt_extended($random));
                    if (strlen($hash) == 20)
                        return $hash;
            }

            if (strlen($random) < 6)
                $random = $this->__get_random_bytes(6);
            $hash = $this->__crypt_private($password, $this->__gensalt_private($random));
            if (strlen($hash) == 34)
                return $hash;

            # Returning '*' on error is safe here, but would _not_ be safe
            # in a crypt(3)-like function used _both_ for generating new
            # hashes and for validating passwords against existing hashes.
            return '*';
	}

	public function CheckPassword($password, $stored_hash)
	{
            $hash = $this->__crypt_private($password, $stored_hash);
            if ($hash[0] == '*')
                $hash = crypt($password, $stored_hash);

            return $hash == $stored_hash;
	}
}

?>