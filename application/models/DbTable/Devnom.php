<?php
class Application_Model_DbTable_Devnom extends Zend_Db_Table_Abstract
{
    protected $_name = 'devnom';
    
    protected $_primary = array('devid','nomid','devnomqte');
}
?>
