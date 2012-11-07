<?php
class Application_Model_Matiere extends Application_Model_Element_Abstract {
    protected $_idField = 'matid';
    protected  $_uniteid;
    protected $_matlib;
    protected $_matsuppr;
    protected $_matprix;
   
    public function getMatprix() {
        return $this->_matprix;
    }

    public function setMatprix($_matprix) {
        $this->_matprix = $_matprix;
    }

        public function getIdField() {
        return $this->_idField;
    }

    public function setIdField($_idField) {
        $this->_idField = $_idField;
    }

    public function getMatlib() {
        return $this->_matlib;
    }

    public function setMatlib($_matlib) {
        $this->_matlib = $_matlib;
    }

    public function getMatsuppr() {
        return $this->_matsuppr;
    }

    public function setMatsuppr($_matsuppr) {
        $this->_matsuppr = $_matsuppr;
    }
    public function getUniteid() {
    	return $this->_uniteid;
    }
    
    public function setUniteid($_uniteid) {
    	$this->_uniteid = $_uniteid;
    }    
}

?>