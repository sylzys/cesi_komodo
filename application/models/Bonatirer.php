<?php

class Application_Model_Bonatirer extends Application_Model_Element_Abstract {
    protected $_idField = 'bonatirerid';
    protected $_prodid;
    protected $_bonatirerdte;
    protected $_bonatirersuppr;
    
    public function getProdid() {
        return $this->_prodid;
    }

    public function setProdid($prodid) {
        $this->_prodid = $prodid;
    }

        
    public function getBonatirerdte() {
        return $this->_bonatirerdte;
    }

    public function setBonatirerdte($bonatirerdte) {
        $this->_bonatirerdte = $bonatirerdte;
    }

    public function getBonatirersuppr() {
        return $this->_bonatirersuppr;
    }

    public function setBonatirersuppr($bonatirersuppr) {
        $this->_bonatirersuppr = $bonatirersuppr;
    }


    
}

?>