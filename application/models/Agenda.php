<?php

class Application_Model_Agenda extends Application_Model_Element_Abstract {
    protected $_idField = 'ageid';
    protected $_utiid;
    protected $_interid;
    protected $_agedeb;
    protected $_agefin;
    protected $_agelieu;
    protected $_agesuppr;
    
    public function getUtiid() {
        return $this->_utiid;
    }

    public function setUtiid($utiid) {
        $this->_utiid = $utiid;
    }

    public function getInterid() {
        return $this->_interid;
    }

    public function setInterid($interid) {
        $this->_interid = $interid;
    }

    public function getAgedeb() {
        return $this->_agedeb;
    }

    public function setAgedeb($agedeb) {
        $this->_agedeb = $agedeb;
    }

    public function getAgefin() {
        return $this->_agefin;
    }

    public function setAgefin($agefin) {
        $this->_agefin = $agefin;
    }

    public function getAgelieu() {
        return $this->_agelieu;
    }

    public function setAgelieu($agelieu) {
        $this->_agelieu = $agelieu;
    }

    public function getAgesuppr() {
        return $this->_agesuppr;
    }

    public function setAgesuppr($agesuppr) {
        $this->_agesuppr = $agesuppr;
    }




}

?>