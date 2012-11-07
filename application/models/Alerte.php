<?php

class Application_Model_Alerte extends Application_Model_Element_Abstract {
    protected $_idField = 'alerteid';
    protected $_alertelbl;
    protected $_alertesuppr;

    public function getalertelbl() {
        return $this->_alertelbl;
    }

    public function setAlertelbl($alertelbl) {
        $this->_alertelbl = $alertelbl;
    }

    public function getAlertesuppr() {
        return $this->_alertesuppr;
    }

    public function setAlertesuppr($alertesuppr) {
        $this->_alertesuppr = $alertesuppr;
    }


    
}

?>