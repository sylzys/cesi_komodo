<?php

abstract class Application_Model_Element_Abstract {
    protected $_id = null;
    protected $_idField = null;
    
    public function getId() {
        return $this->_id;
    }
    
    public function setId($id) {
        $this->_id = $id;
    }
    
    public function getIdField() {
        return $this->_idField;
    }
    
}
?>
