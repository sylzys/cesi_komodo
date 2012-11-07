<?php

class Application_Model_Profil extends Application_Model_Element_Abstract {
    protected $_idField = 'profid';
    protected $_proflib;
    protected $_profsuppr;

    public function getProflib() {
        return $this->_proflib;
    }

    public function setProflib($proflib) {
        $this->_proflib = $proflib;
    }

    public function getProfsuppr() {
        return $this->_profsuppr;
    }

    public function setProfsuppr($profsuppr) {
        $this->_profsuppr = $profsuppr;
    }


    
}

?>
