<?php

class Application_Model_Utilisateur extends Application_Model_Element_Abstract {
    protected $_idField = 'utiid';
    protected $_utinom;
    protected $_utiprenom;
    protected $_utilogin;
    protected $_utimdp;
    protected $_utimail;
    protected $_utitel;
    protected $_utisuppr;
    protected $_utidtelog;
    
    public function getUtinom() {
        return $this->_utinom;
    }

    public function setUtinom($utinom) {
        $this->_utinom = $utinom;
    }

    public function getUtiprenom() {
        return $this->_utiprenom;
    }

    public function setUtiprenom($utiprenom) {
        $this->_utiprenom = $utiprenom;
    }

    public function getUtilogin() {
        return $this->_utilogin;
    }

    public function setUtilogin($utilogin) {
        $this->_utilogin = $utilogin;
    }

    public function getUtimdp() {
        return $this->_utimdp;
    }

    public function setUtimdp($utimdp) {
        $this->_utimdp = $utimdp;
    }

    public function getUtimail() {
        return $this->_utimail;
    }

    public function setUtimail($utimail) {
        $this->_utimail = $utimail;
    }

    public function getUtitel() {
        return $this->_utitel;
    }

    public function setUtitel($utitel) {
        $this->_utitel = $utitel;
    }

    public function getUtisuppr() {
        return $this->_utisuppr;
    }

    public function setUtisuppr($utisuppr) {
        $this->_utisuppr = $utisuppr;
    }

    public function getUtidtelog() {
        return $this->_utidtelog;
    }

    public function setUtidtelog($_utidtelog) {
        $this->_utidtelog = $_utidtelog;
    }
}


?>
