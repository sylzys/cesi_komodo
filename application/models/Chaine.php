<?php

class Application_Model_Chaine extends Application_Model_Element_Abstract {
    protected $_idField = 'chaineid';
    protected $_chainelib;
    protected $_chainesuppr;
    protected $_chainedispo;
    

    public function getChainelib() {
        return $this->_chainelib;
    }

    public function setChainelib($chainelib) {
        $this->_chainelib = $chainelib;
    }

    public function getChainesuppr() {
        return $this->_chainesuppr;
    }

    public function setChainesuppr($chainesuppr) {
        $this->_chainesuppr = $chainesuppr;
    }

    public function getChainedispo() {
        return $this->_chainedispo;
    }

    public function setChainedispo($chainedispo) {
        $this->_chainedispo = $chainedispo;
    }


    
}
?>