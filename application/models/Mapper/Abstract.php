<?php

abstract class Application_Model_Mapper_Abstract {


    protected $_dbTable;

    public function setDbTable($dbTable = null) {
        if (!is_null($dbTable) && $dbTable instanceof $this->_dbTableName) {
            $this->_dbTable = $dbTable;
        } else {
            $dbTable = new $this->_dbTableName();
        }
        if (!$dbTable instanceof Zend_Db_Table_Abstract) {
            throw new Exception('Invalid table data gateway provided');
        } else {
            $this->_dbTable = $dbTable;
        }
        return $this;
    }

    public function getDbTable() {
        if (null === $this->_dbTable) {
            $this->setDbTable();
        }
        return $this->_dbTable;
    }

    protected function getClassNameElement() {

        $class = strstr(get_class($this), 'Mapper', true);

        return $class;
    }

    protected function _getObjectFromDbRow($row) {
        $class = $this->getClassNameElement();
        $oElement = new $class;
        $IdField = $oElement->getIdField();
        foreach ($row as $key => $value) {
            if (strtoupper($key) == strtoupper($IdField)) {
                $oElement->setId($value);
            } else {
                $methodName = 'set' . ucfirst($key);
                $oElement->$methodName($value);
            }
        }

        return $oElement;
    }

    protected function _setObjectFromDbRow(Application_Model_Element_Abstract $oElement) {
        $result = $this->getDbTable()->info(Zend_Db_Table_Abstract::COLS);
        $data = array();
        $IdField = $oElement->getIdField();
        foreach ($result as $colName) {
            if (strtoupper($colName) == strtoupper($IdField)) {
                $data[$IdField] = $oElement->getId();
            } else {
                $methodName = 'get' . ucfirst($colName);
                $val = $oElement->$methodName();
                if (is_bool($val)) {
                    $val = ($val) ? 'true' : 'false';
                }
                $data[$colName] = $val;
            }
        }
        return $data;
    }

  
    public function fetchAll($select = null) {
        if ($select instanceof Zend_Db_Select) {
            $resultSet = $this->getDbTable()->fetchAll($select);
        } else {
            $resultSet = $this->getDbTable()->fetchAll();
        }
        if (0 == count($resultSet)) {
            return;
        }
        $class = $this->getClassNameElement();
        $entries = array();
        foreach ($resultSet as $row) {

            $entries[] = $this->_getObjectFromDbRow($row);
        }
        return $entries;
    }

    public function find($id) {
        $result = $this->getDbTable()->find($id);
        if (0 == count($result)) {
            return NULL;
        }

        $row = $result->current();

        $oElement = $this->_getObjectFromDbRow($row);

        return $oElement;
    }

    public function delete($id) {

        $select = $this->getDbTable()->select()->where('id = ?', intval($id));

        $row = $this->getDbTable()->fetchRow($select)->delete();
    }

    public function save(Application_Model_Element_Abstract $oElement) {
        $data = $this->_setObjectFromDbRow($oElement);

        if (null === ($id = $oElement->getId())) {
            $newIdApprenant = $this->getDbTable()->insert($data);
            if (isset($newIdApprenant) && !empty($newIdApprenant)) {
                $oElement->setId($newIdApprenant);
            } else {
                throw new Exception(__METHOD__ . " : La requete insert n\'a pas renvoye d\'id : line " . __LINE__);
            }
        } else {
            //throw new Phoenix_Exception('Impossible de modifier un Ã©lÃ©ment de rï¿½fï¿½rence');
            $this->getDbTable()->update($data, array($oElement->getIdField() .  ' = ?' => $id));
        }
    }

    
    public function getTableForSelect() {

        $aElement = $this->fetchAll();
        $result = array();

        foreach ($aElement as $key => $value) {
            $result[$aElement[$key]->getId()] = $aElement[$key]->getTitle();
        }

        return $result;
    }
    
    public function getWhere($where = null) {
        if (is_array($where)) {
            $oSelect = $this->getDbTable()->select()->where(implode($where, ', '));
            return $this->fetchAll($oSelect);
        } else {
            return $this->fetchAll();
        }
    }
}
?>
