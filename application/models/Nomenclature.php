<?php

class Application_Model_Nomenclature extends Application_Model_Element_Abstract {
    protected $_idField = 'nomid';
    protected $_nomlib;
    protected $_nomdate;
    protected $_nomtemps;
    protected $_nomnbchaine;
    protected $_nomqr;
    protected $_nomprix;
    protected $_nomdes;
    protected $_nomsuppr;
    
    public function getNomlib() {
        return $this->_nomlib;
    }

    public function setNomlib($nomlib) {
        $this->_nomlib = $nomlib;
    }

    public function getNomdate() {
        return $this->_nomdate;
    }

    public function setNomdate($nomdate) {
        $this->_nomdate = $nomdate;
    }

    public function getNomtemps() {
        return $this->_nomtemps;
    }

    public function setNomtemps($nomtemps) {
        $this->_nomtemps = $nomtemps;
    }

    public function getNomnbchaine() {
        return $this->_nomnbchaine;
    }

    public function setNomnbchaine($nomnbchaine) {
        $this->_nomnbchaine = $nomnbchaine;
    }

    public function getNomqr() {
        return $this->_nomqr;
    }

    public function setNomqr($nomqr) {
        $this->_nomqr = $nomqr;
    }

    public function getNomprix() {
        return $this->_nomprix;
    }

    public function setNomprix($nomprix) {
        $this->_nomprix = $nomprix;
    }

    public function getNomdes() {
        return $this->_nomdes;
    }

    public function setNomdes($nomdes) {
        $this->_nomdes = $nomdes;
    }

    public function getNomsuppr() {
        return $this->_nomsuppr;
    }

    public function setNomsuppr($nomsuppr) {
        $this->_nomsuppr = $nomsuppr;
    }

    
}

?>