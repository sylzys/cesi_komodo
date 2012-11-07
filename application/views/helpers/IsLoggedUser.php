<?php

class Zend_View_Helper_IsLoggedUser extends Zend_View_Helper_Abstract {
    
    public function isLoggedUser() {
        $sess = Zend_Auth::getInstance()->getIdentity();
        if (isset($sess->utilogin)) {
            return true;
        }
        return false;
    }
    
}

?>