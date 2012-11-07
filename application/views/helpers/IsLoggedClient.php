<?php

class Zend_View_Helper_IsLoggedClient extends Zend_View_Helper_Abstract {
    
    public function isLoggedClient() {
        $sess = Zend_Auth::getInstance()->getIdentity();
        if (isset($sess->clilogin)) {
            return true;
        }
        return false;
    }
    
}

?>