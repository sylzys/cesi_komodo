<?php

class Application_Model_Commande extends Application_Model_Element_Abstract {
    protected $_idField = 'comid';
    protected $_comdate;
    protected $_comdateprev;
    protected $_cometat;
    protected $_comresume;
    protected $_comdproddeb;
    protected $_comprodfin;
    protected $_comprix;
    protected $_comsuppr;
    protected $_interid;
    protected $_demandeid;
    protected $_comtitre;
    protected $_comdesc;
    protected $_utiid;
    protected $_enqid;
    protected $_comuniqid;
    
    public function getUtiid() {
        return $this->_utiid;
    }

    public function setUtiid($utiid) {
        $this->_utiid = $utiid;
    }

        public function getComdproddeb() {
        return $this->_comdproddeb;
    }

    public function setComdproddeb($comdproddeb) {
        $this->_comdproddeb = $comdproddeb;
    }

    public function getInterid() {
        return $this->_interid;
    }

    public function setInterid($interid) {
        $this->_interid = $interid;
    }

    public function getDemandeid() {
        return $this->_demandeid;
    }

    public function setDemandeid($demandeid) {
        $this->_demandeid = $demandeid;
    }

    public function getComtitre() {
        return $this->_comtitre;
    }

    public function setComtitre($comtitre) {
        $this->_comtitre = $comtitre;
    }

    public function getComdesc() {
        return $this->_comdesc;
    }

    public function setComdesc($comdesc) {
        $this->_comdesc = $comdesc;
    }

    
    public function getComdate() {
        return $this->_comdate;
    }

    public function setComdate($comdate) {
        $this->_comdate = $comdate;
    }
    public function getComresume() {
        return $this->_comresume;
    }

    public function setComresume($comresume) {
        $this->_comresme = $comresume;
    }
    public function getComdateprev() {
        return $this->_comdateprev;
    }

    public function setComdateprev($comdateprev) {
        $this->_comdateprev = $comdateprev;
    }

    public function getCometat() {
        return $this->_cometat;
    }

    public function setCometat($cometat) {
        $this->_cometat = $cometat;
    }

    public function getComproddeb() {
        return $this->_comdproddeb;
    }

    public function setComproddeb($comproddeb) {
        $this->_comdproddeb = $comproddeb;
    }

    public function getComprodfin() {
        return $this->_comprodfin;
    }

    public function setComprodfin($comprodfin) {
        $this->_comprodfin = $comprodfin;
    }

    public function getComprix() {
        return $this->_comprix;
    }

    public function setComprix($comprix) {
        $this->_comprix = $comprix;
    }

    public function getComsuppr() {
        return $this->_comsuppr;
    }

    public function setComsuppr($comsuppr) {
        $this->_comsuppr = $comsuppr;
    }
    
    public function getEnqid() {
        return $this->_enqid;
    }

    public function setEnqid($enqid) {
        $this->_enqid = $enqid;
    }

    public function getComuniqid() {
        return $this->_comuniqid;
    }

    public function setComuniqid($comuniqid) {
        $this->_comuniqid = $comuniqid;
    }


}
?>