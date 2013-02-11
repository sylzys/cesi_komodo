<?php
class Application_Model_SuivieDossier extends Application_Model_Element_Abstract
{
    protected $_idField = "suivdosid";
    
    protected $_suivdosdate;
    
    protected $_suivdoscom;
    
    protected $_suividossuppr;
    
    protected $_comid;
    
    protected $_interid;
    
    protected $_devid;
    
    protected $_utiid;
    public function getSuivdosuniqid() {
        return $this->_suivdosuniqid;
    }

    public function setSuivdosuniqid($suivdosuniqid) {
        $this->_suivdosuniqid = $suivdosuniqid;
    }

        protected $_suivdosuniqid;
    
    protected $_demandeid;
    public function getDemandeid() {
        return $this->_demandeid;
    }

    public function setDemandeid($demandeid) {
        $this->_demandeid = $demandeid;
    }

        public function getComid() {
	return $this->_comid;
    }

    public function setComid($comid) {
	$this->_comid = $comid;
    }

    public function getInterid() {
	return $this->_interid;
    }

    public function setInterid($interid) {
	$this->_interid = $interid;
    }

    public function getDevid() {
	return $this->_devid;
    }

    public function setDevid($devid) {
	$this->_devid = $devid;
    }

    public function getUtiid() {
	return $this->_utiid;
    }

    public function setUtiid($utiid) {
	$this->_utiid = $utiid;
    }   

    public function getIdField() {
	return $this->_idField;
    }

    public function setIdField($idField) {
	$this->_idField = $idField;
    }

    public function getSuivdosdate() {
	return $this->_suivdosdate;
    }

    public function setSuivdosdate($suivdosdate) {
	$this->_suivdosdate = $suivdosdate;
    }

    public function getSuivdoscom() {
	return $this->_suivdoscom;
    }

    public function setSuivdoscom($suivdoscom) {
	$this->_suivdoscom = $suivdoscom;
    }

    public function getSuividossuppr() {
	return $this->_suividossuppr;
    }

    public function setSuividossuppr($suividossuppr) {
	$this->_suividossuppr = $suividossuppr;
    }


    
    
}
?>
