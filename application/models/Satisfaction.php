<?php

class Application_Model_Satisfaction extends Application_Model_Element_Abstract {
    protected $_idField = 'satid';
    protected $_cliid;
    protected $_q1;
    protected $_q2;
    protected $_q3;
    protected $_q4;
    protected $_q5;
    protected $_satcom;
    protected $_satdate;

    public function getCliid() {
        return $this->_cliid;
    }

    public function setCliid($cliid) {
        $this->_cliid = $cliid;
    }

    public function getQ1() {
        return $this->_q1;
    }

    public function setQ1($q1) {
        $this->_q1 = $q1;
    }

    public function getQ2() {
        return $this->_q2;
    }

    public function setQ2($q2) {
        $this->_q2 = $q2;
    }

    public function getQ3() {
        return $this->_q3;
    }

    public function setQ3($q3) {
        $this->_q3 = $q3;
    }

    public function getQ4() {
        return $this->_q4;
    }

    public function setQ4($q4) {
        $this->_q4 = $q4;
    }

    public function getQ5() {
        return $this->_q5;
    }

    public function setQ5($q5) {
        $this->_q5 = $q5;
    }

    public function getSatcom() {
        return $this->_satcom;
    }

    public function setSatcom($satcom) {
        $this->_satcom = $satcom;
    }

    public function getSatdate() {
        return $this->_satdate;
    }

    public function setSatdate($satdate) {
        $this->_satdate = $satdate;
    }

    public function getIdField() {
        return $this->_idField;
    }

    public function setIdField($idField) {
        $this->_idField = $idField;
    }  
}

?>