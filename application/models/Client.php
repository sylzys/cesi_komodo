<?php

class Application_Model_Client extends Application_Model_Element_Abstract {
    protected $_idField = 'cliid';
    protected $_uti_utiid;
    protected $_clipays;
    protected $_clitel;
    protected $_clifax;
    protected $_climail;
    protected $_cliactivite;
    protected $_clisiret;
    protected $_clica;
    protected $_clisite;
    protected $_clidg;
    protected $_clidteadd;
    protected $_utiid;
    protected $_clirais;
    protected $_clinom;
    protected $_cliadresse;
    protected $_clicp;
    protected $_cliville;
    protected $_clietat;
    protected $_clilogin;
    protected $_climdp;
    protected $_cliacces;
    protected $_clisuppr;
    protected $_clidtelog;
    protected $_cliurltmp;
    protected $_clinaf;
    protected $_clisiren;
    protected $_cliuniqid;
    protected $_suivdosuniqid;
    
    public function getClisiret() {
        return $this->_clisiret;
    }

    public function setClisiret($clisiret) {
        $this->_clisiret = $clisiret;
    }

        
    public function getClisiren() {
        return $this->_clisiren;
    }

    public function setClisiren($clisiren) {
        $this->_clisiren = $clisiren;
    }

        public function getClinaf() {
        return $this->_clinaf;
    }

    public function setClinaf($clinaf) {
        $this->_clinaf = $clinaf;
    }

    public function getUtiid() {
        return $this->_utiid;
    }

    public function setUtiid($utiid) {
        $this->_utiid = $utiid;
    }

    public function getCliurltmp() {
        return $this->_cliurltmp;
    }

    public function setCliurltmp($cliurltmp) {
        $this->_cliurltmp = $cliurltmp;
    }
    
    public function getClirais() {
        return $this->_clirais;
    }

    public function setClirais($clirais) {
        $this->_clirais = $clirais;
    }

    public function getClinom() {
        return $this->_clinom;
    }

    public function setClinom($clinom) {
        $this->_clinom = $clinom;
    }

    public function getCliadresse() {
        return $this->_cliadresse;
    }

    public function setCliadresse($cliadresse) {
        $this->_cliadresse = $cliadresse;
    }

    public function getClicp() {
        return $this->_clicp;
    }

    public function setClicp($clicp) {
        $this->_clicp = $clicp;
    }

    public function getCliville() {
        return $this->_cliville;
    }

    public function setCliville($cliville) {
        $this->_cliville = $cliville;
    }

    public function getClietat() {
        return $this->_clietat;
    }

    public function setClietat($clietat) {
        $this->_clietat = $clietat;
    }

    public function getClilogin() {
        return $this->_clilogin;
    }

    public function setClilogin($clilogin) {
        $this->_clilogin = $clilogin;
    }

    public function getClimdp() {
        return $this->_climdp;
    }

    public function setClimdp($climdp) {
        $this->_climdp = $climdp;
    }

    public function getCliacces() {
        return $this->_cliacces;
    }

    public function setCliacces($cliacces) {
        $this->_cliacces = $cliacces;
    }

    public function getClisuppr() {
        return $this->_clisuppr;
    }

    public function setClisuppr($clisuppr) {
        $this->_clisuppr = $clisuppr;
    }
    public function getClidtelog() {
        return $this->_clidtelog;
    }

    public function setClidtelog($_clidtelog) {
        $this->_clidtelog = $_clidtelog;
    }
    public function getUti_utiid() {
        return $this->_uti_utiid;
    }

    public function setUti_utiid($uti_utiid) {
        $this->_uti_utiid = $uti_utiid;
    }

    public function getClipays() {
        return $this->_clipays;
    }

    public function setClipays($clipays) {
        $this->_clipays = $clipays;
    }

    public function getClitel() {
        return $this->_clitel;
    }

    public function setClitel($clitel) {
        $this->_clitel = $clitel;
    }

    public function getClifax() {
        return $this->_clifax;
    }

    public function setClifax($clifax) {
        $this->_clifax = $clifax;
    }

    public function getClimail() {
        return $this->_climail;
    }

    public function setClimail($climail) {
        $this->_climail = $climail;
    }

    public function getCliactivite() {
        return $this->_cliactivite;
    }

    public function setCliactivite($cliactivite) {
        $this->_cliactivite = $cliactivite;
    }
    
    public function getClica() {
        return $this->_clica;
    }

    public function setClica($clica) {
        $this->_clica = $clica;
    }

    public function getClisite() {
        return $this->_clisite;
    }

    public function setClisite($clisite) {
        $this->_clisite = $clisite;
    }

    public function getClidg() {
        return $this->_clidg;
    }

    public function setClidg($clidg) {
        $this->_clidg = $clidg;
    }

    public function getClidteadd() {
        return $this->_clidteadd;
    }

    public function setClidteadd($clidteadd) {
        $this->_clidteadd = $clidteadd;
    }
    
    public function getCliuniqid() {
        return $this->_cliuniqid;
    }

    public function setCliuniqid($cliuniqid) {
        $this->_cliuniqid = $cliuniqid;
    }

    public function getSuivdosuniqid() {
        return $this->_suivdosuniqid;
    }

    public function setSuivdosuniqid($suivdosuniqid) {
        $this->_suivdosuniqid = $suivdosuniqid;
    }


}

?>