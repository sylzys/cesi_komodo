<?php

class Application_Model_ProfilMapper extends Application_Model_Mapper_Abstract {

    protected $_dbTableName = 'Application_Model_DbTable_Profil';

    public function findProfils() {
        $o_Select = $this->getDbTable()->select();
        $o_Select->where('profsuppr = false');
        $tab_profils = $this->fetchAll($o_Select);
        return $tab_profils;
    }

    public function findProfilsByUser($user) {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->query("select profid, proflib from get_utiprof(" . $user->getId() . ") as (profid int, proflib varchar(100))");
        $stmt->setFetchMode(Zend_Db::FETCH_OBJ);
        $result = array();
        while ($row = $stmt->fetch()) {
            $result[] = $row;
        }
        return $result;
    }

    public function findRessourcesByProfil($id_profil) {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->query("SELECT ctrlid, ctrlnom, actionid, actionnom FROM get_ressources(" . (int) $id_profil . ") AS (ctrlid integer, ctrlnom varchar(100), actionid integer, actionnom varchar(50))");
        $stmt->setFetchMode(Zend_Db::FETCH_OBJ);
        $result = array();
        while ($row = $stmt->fetch()) {
            $result[] = $row;
        }
        return $result;
    }

    public function findProfilsUsers() {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->query("SELECT profid, utiid FROM utiprof");
        $stmt->setFetchMode(Zend_Db::FETCH_OBJ);
        $result = array();
        while ($row = $stmt->fetch()) {
            $result[] = $row;
        }
        return $result;
    }
    
    public function deleteProfilsByUser($id_user) {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->prepare('DELETE FROM utiprof WHERE utiid = :userid');
	return $stmt->execute(array(':userid' => (int)$id_user));
    }
    
    public function addProfilUser($id_profil, $id_user) {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->prepare('INSERT INTO utiprof (utiid, profid) VALUES (:userid, :profilid)');
	return $stmt->execute(array(':profilid' => (int)$id_profil, ':userid' => (int)$id_user));
    }
    
    public function findRights() {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->query("SELECT * FROM controller_action");
        $stmt->setFetchMode(Zend_Db::FETCH_OBJ);
        $result = array();
        while ($row = $stmt->fetch()) {
            $o_tmp = new stdClass();
            $o_tmp->act_id = $row->actionid;
            $o_tmp->act_lib = $row->actionlib;
            $result[$row->ctrllib][] = $o_tmp;
        }
        return $result;
    }
    
    public function findProfilsRights() {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->query("SELECT profid, actionid FROM profaction");
        $stmt->setFetchMode(Zend_Db::FETCH_OBJ);
        $res = $stmt->fetchAll();
        $result = array();
        foreach ($res as $act) {
            $result[$act->profid][] = $act->actionid;
        }
        return $result;
    }
    
    public function deleteRightsByProfil($id_profil) {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->prepare('DELETE FROM profaction WHERE profid = :profid');
	return $stmt->execute(array(':profid' => (int)$id_profil));
    }
    
    public function addProfilRight($id_profil, $id_act) {
        $db = Zend_Registry::get('dbAdapter');
        $stmt = $db->prepare('INSERT INTO profaction (profid, actionid) VALUES (:profid, :actionid)');
	return $stmt->execute(array(':profid' => $id_profil, ':actionid' => $id_act));
    }
    
}

?>
