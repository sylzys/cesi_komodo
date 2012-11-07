<?php

class Application_Model_Nommat extends Application_Model_Element_Abstract {
    protected $_idField = 'nomid';
    protected $_matid;
    protected $_matqte;
    
    public function getNomid() {
        return $this->_nomid;
    }

    public function setNomlib($nomid) {
        $this->_nomid = $nomid;
    }

    public function getMatid() {
        return $this->_matid;
    }

    public function setMatid($_matid) {
        $this->_matid = $_matid;
    }

    public function getMatqte() {
        return $this->_matqte;
    }

    public function setMatqte($_matqte) {
        $this->_matqte = $_matqte;
    }


    
}

?>