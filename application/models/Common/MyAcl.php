<?php

class Application_Model_Common_MyAcl extends Zend_Acl {

    protected $_ressources = array();
    protected $_roles = array();
    protected $_rights = array();
    protected $_user = null;
    
    public function __construct() {
        Zend_Registry::set('My_Acl', $this);
        if (Zend_Auth::getInstance()->hasIdentity()) {
            $this->_setUser(Zend_Auth::getInstance()->getIdentity()->utiid);
        }
        $this->initAcls();
    }
    
    protected function _setUser($id_user) {
        $o_UtilisateurMapper = new Application_Model_UtilisateurMapper();
        $o_Utilisateur = $o_UtilisateurMapper->find($id_user);
        if (is_null($o_Utilisateur)) {
            throw new Zend_Controller_Exception("User doesn't exists!");
        } else {
            $this->_user = $o_Utilisateur;
        }
    }
    
    public function initAcls() {
        $this->_initRoles();
        $this->_initRessources();
        $this->_initRights();
    }
    
    public function getRessources() {
        return $this->_ressources;
    }
    
    public function getRoles() {
        return $this->_roles;
    }
    
    public function getRights() {
        return $this->_rights;
    }

    protected function _initRessources() {
        $this->addResource(new Zend_Acl_Resource('login'));
        $this->addResource(new Zend_Acl_Resource('error'));
        if (!is_null($this->_user)) {
            $o_ProfilMapper = new Application_Model_ProfilMapper();
            foreach ($this->_roles as $role) {
                $tab_Ressources = $o_ProfilMapper->findRessourcesByProfil($role->profid);
                if (!is_null($tab_Ressources)) {
                    foreach ($tab_Ressources as $res) {
                        $this->_rights[$role->proflib][$res->ctrlnom][] = $res->actionnom;
                        if (!in_array($res->ctrlnom, $this->_ressources)) {
                            $this->_ressources[] = $res->ctrlnom;
                            $this->addResource(new Zend_Acl_Resource($res->ctrlnom));
                        }
                    }
                }
            }
            
        }
    }

    protected function _initRoles() {
        if (is_null($this->_user)) {
            $guest = new stdClass();
            $guest->proflib = 'guest';
            $this->_roles[] = $guest;
            $this->addRole(new Zend_Acl_Role('guest'));
            return;
        }
        $o_ProfilMapper = new Application_Model_ProfilMapper();
        $tab_Profils = $o_ProfilMapper->findProfilsByUser($this->_user);
        if (count($tab_Profils) == 0) {
//            throw new Zend_Controller_Exception("No roles created!");
//            $this->_roles[] = 'guest';
            $this->addRole(new Zend_Acl_Role('guest'));
        } else {
            foreach ($tab_Profils as $role) {
                $this->_roles[] = $role;
                $this->addRole(new Zend_Acl_Role(strtolower($role->proflib)));
            }
        }
    }

    protected function _initRights() {
        if (is_null($this->_user)) {
            $this->allow('guest', 'login', null);
            return;
        }
        foreach ($this->_rights as $prof => $right) {
            foreach ($right as $ctrl => $acts) {
                $this->allow($prof, $ctrl, $acts);
            }
            $this->allow($prof, 'login', null);
            $this->allow($prof, 'error', null);
        }
    }

}
