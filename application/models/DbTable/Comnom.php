<?php
class Application_Model_DbTable_Comnom extends Zend_Db_Table_Abstract
{
    protected $_name = 'comnom';
    
    protected $_primary = array('comid','nomid');
}
?>
