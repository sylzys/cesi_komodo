<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Interlocuteur
 *
 * @author overadmin
 */
class Application_Model_Interlocuteur  extends Application_Model_Element_Abstract{    
    
    protected $idField = 'interid';
    
    protected $fourid;
    
    protected $cliid;
    
    protected $internom;
    
    protected $intermail;
    
    protected $intertel;
    
    protected $intersuppr;
    
    protected $interprenom;
    
    protected $_utiid;
    
    protected $_interposte;
    
    protected $_interdteadd;
    
    public function getInterdteadd() {
        return $this->_interdteadd;
    }

    public function setInterdteadd($interdteadd) {
        $this->_interdteadd = $interdteadd;
    }

        
    public function getInterposte() {
        return $this->_interposte;
    }

    public function setInterposte($interposte) {
        $this->_interposte = $interposte;
    }

    
    public function getUtiid() {
        return $this->_utiid;
    }

    public function setUtiid($utiid) {
        $this->_utiid = $utiid;
    }

        public function getIdField() {
	return $this->idField;
    }

    public function setIdField($idField) {
	$this->idField = $idField;
    }

    public function getFourid() {
	return $this->fourid;
    }

    public function setFourid($fourid) {
	$this->fourid = $fourid;
    }

    public function getCliid() {
	return $this->cliid;
    }

    public function setCliid($cliid) {
	$this->cliid = $cliid;
    }

    public function getInternom() {
	return $this->internom;
    }

    public function setInternom($internom) {
	$this->internom = $internom;
    }

    public function getIntermail() {
	return $this->intermail;
    }

    public function setIntermail($intermail) {
	$this->intermail = $intermail;
    }

    public function getIntertel() {
	return $this->intertel;
    }

    public function setIntertel($intertel) {
	$this->intertel = $intertel;
    }

    public function getIntersuppr() {
	return $this->intersuppr;
    }

    public function setIntersuppr($intersuppr) {
	$this->intersuppr = $intersuppr;
    }   
    
    public function getInterprenom() {
	return $this->interprenom;
    }

    public function setInterprenom($interprenom) {
	$this->interprenom = $interprenom;
    }
}

?>
