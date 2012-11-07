<?php

class Application_Model_Bonsajeter extends Application_Model_Element_Abstract {
    protected $_idField = 'bonajeterid';
    protected $_prodid;
    protected $_bonajeterdte;
    protected $_bonajetersuppr;
    
    public function getProdid() {
        return $this->_prodid;
    }

    public function setProdid($prodid) {
        $this->_prodid = $prodid;
    }

    public function getBonajeterdte() {
        return $this->_bonajeterdte;
    }

    public function setBonajeterdte($bonajeterdte) {
        $this->_bonajeterdate = $bonajeterdte;
    }

    public function getBonajetersuppr() {
        return $this->_bonajetersuppr;
    }

    public function setBonajetersuppr($bonajetersuppr) {
        $this->_bonajetersuppr = $bonajetersuppr;
    }


}

?>