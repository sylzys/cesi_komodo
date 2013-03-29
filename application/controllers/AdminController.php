<?php

class adminController extends Zend_Controller_Action
{

    public function init()
    {
        parent::init();
    }

    public function indexAction()
    {
        $oUtilisateurMapper = new Application_Model_UtilisateurMapper();
        $oUserSelect = $oUtilisateurMapper->getDbTable()->Select();
        $oClientMapper = new Application_Model_ClientMapper();
        $oCliSelect = $oClientMapper->getDbTable()->select();
        
        
        $oUserSelect->where('utisuppr = FALSE');
        $tabUsers = $oUtilisateurMapper->fetchAll($oUserSelect);
//        $oUserSelect->where('date_last_logged != NULL');
//        $oUserSelect->order('date_last_logged DESC');
        $oUserSelect->limit(5, 0);
        $tabUsersLogged = $oUtilisateurMapper->fetchAll($oUserSelect);
        $oCliSelect->where('clisuppr = FALSE');
        $tabCli = $oClientMapper->fetchAll($oCliSelect);
        $oCliSelect->where('cliacces = TRUE');
        $tabCliRemote = $oClientMapper->fetchAll($oCliSelect);
//        $oCliSelect->where('date_last_logged != NULL');
//        $oCliSelect->order('date_last_logged DESC');
        $oCliSelect->limit(5, 0);
        $tabCliLogged = $oClientMapper->fetchAll($oCliSelect);
        
        $this->view->titre = "Administration";
        $this->view->users_nb = count($tabUsers);
        $this->view->users_last = $tabUsersLogged;
        $this->view->clients_nb = count($tabCli);
        $this->view->clients_nb_remote = count($tabCliRemote);
        $this->view->clients_last = $tabCliLogged;
    }
    
    public function clientAction() {
        $this->view->titre = "Gestion des clients";
        $this->view->headLink()->appendStylesheet($this->view->baseUrl() . '/css/admin_clients.css');
    }
    
    public function clientlistAction() {
        $o_ClientMapper = new Application_Model_ClientMapper();
        $o_Select = $o_ClientMapper->getDbTable()->select();
        $o_Select->where('clisuppr = false');
        $o_Select->order(array('clinom ASC', 'cliacces'));
        $tab_Clients = $o_ClientMapper->fetchAll($o_Select);
        if (count($tab_Clients) == 0) {
            $tab_Clients = false;
        } else {
            $o_UtilisateurMapper = new Application_Model_UtilisateurMapper();
            $tab_Utilisateurs = array();
            foreach ($tab_Clients as $cli) {
                if (!array_key_exists($cli->getUtiid(), $tab_Utilisateurs)) {
                    $tab_Utilisateurs[$cli->getUtiid()] = $o_UtilisateurMapper->find($cli->getUtiid());
                }
            }
            $this->view->utilisateurs = $tab_Utilisateurs;
        }
        $this->view->clients = $tab_Clients;
        $this->_helper->layout->disableLayout();
    }
    
    public function clientchangeaccesAction() {
        $request = $this->getRequest();
    	$o_ClientMapper = new Application_Model_ClientMapper();
        $result = $request->getPost();
        $o_Client = $o_ClientMapper->find((int)$result['id']);
        // modify client acces
        switch ($result['modif']) {
            case "disable":
                $o_Client->setCliacces(false);
                $o_ClientMapper->save($o_Client);
                break;
            case "enable":
                $o_Client->setCliacces(true);
                $o_ClientMapper->save($o_Client);
                break;
        }
//        $this->render('ajax');
        $this->_helper->layout->disableLayout();
        return true;
    }

    public function userAction() {
        $this->view->titre = "Gestion des utilisateurs";
        $this->view->headLink()->appendStylesheet($this->view->baseUrl() . '/css/admin_utilisateurs.css');
    }
    
    public function usersheetAction() {
        
    }
    
    public function userlistAction() {
        $o_UtilisateurMapper = new Application_Model_UtilisateurMapper();
        $o_Select = $o_UtilisateurMapper->getDbTable()->select();
        $o_Select->where('utisuppr = false');
        $o_Select->order(array('utinom ASC', 'utiprenom ASC'));
        $tab_Utilisateurs = $o_UtilisateurMapper->fetchAll($o_Select);
        $this->view->profils = array();
        if (count($tab_Utilisateurs) == 0) {
            $tab_Utilisateurs = false;
        } else {
            $o_ProfilMapper = new Application_Model_ProfilMapper();
            $profils = $o_ProfilMapper->findProfils();
            if (is_null($profils)) {
                $profils = array();
            }
            $tab_ProfilsUsed = $o_ProfilMapper->findProfilsUsers();
            $profils_used = array();
            foreach ($tab_ProfilsUsed as $pu) {
                $profils_used[$pu->utiid][] = $pu->profid;
            }
            $this->view->profils = $profils;
            $this->view->profils_used = $profils_used;
        }
        $this->view->utilisateurs = $tab_Utilisateurs;
        $this->_helper->layout->disableLayout();
    }
    
    public function userchangeprofilAction() {
        $request = $this->getRequest();
        $o_UtilisateurMapper = new Application_Model_UtilisateurMapper();
        $o_ProfilMapper = new Application_Model_ProfilMapper();
    	$result = $request->getPost();
        if (!isset($result['id_utilisateur']) || !isset($result['profils'])) {
            return false;
        }
        $o_Utilisateur = $o_UtilisateurMapper->find((int)$result['id_utilisateur']);
        if (is_null($o_Utilisateur)) {
            return false;
        }
        if (!$o_ProfilMapper->deleteProfilsByUser($o_Utilisateur->getId())) {
            return false;
        }
        if (is_array($result['profils'])) {
            foreach ($result['profils'] as $id_profil) {
                $o_Profil = $o_ProfilMapper->find((int)$id_profil);
                if (is_null($o_Profil)) {
                    return false;
                }
                if (!$o_ProfilMapper->addProfilUser($o_Profil->getId(), $o_Utilisateur->getId())) {
                    return false;
                }
            }
        }
//        $this->render('ajax');
        $this->_helper->layout->disableLayout();
        return true;
    }
    
    public function useraddAction() {
        $request = $this->getRequest();
        $result = $request->getPost();
        $this->_helper->layout->disableLayout();
        $o_UtilisateurMapper = new Application_Model_UtilisateurMapper();
        $o_Select = $o_UtilisateurMapper->getDbTable()->select();
        $o_Select->where('utilogin = :login');
        $o_Select->where('utisuppr = FALSE');
        $o_Select->bind(array(':login'=>$result['login']));
        if (!is_null($o_UtilisateurMapper->fetchAll($o_Select))) {
            echo 'Ce login existe deja';
            return false;
        }
        $o_Utilisateur = new Application_Model_Utilisateur();
        $o_Utilisateur->setUtilogin($result['login']);
        $o_Utilisateur->setUtimail($result['email']);
        // generation hash bcrypt
        $bcrypt = new Application_Controller_Plugin_Bcrypt();
        $hash = $bcrypt->HashPassword($result['passwd']);
        $o_Utilisateur->setUtimdp($hash);
        $o_Utilisateur->setUtinom($result['lastname']);
        $o_Utilisateur->setUtiprenom($result['firstname']);
        $o_Utilisateur->setUtitel($result['tel']);
        $o_Utilisateur->setUtisuppr(false);
        $o_UtilisateurMapper->save($o_Utilisateur);
        return true;
    }
    
    public function usereditAction() {
        $request = $this->getRequest();
        $result = $request->getPost();
        $this->_helper->layout->disableLayout();
        $o_UtilisateurMapper = new Application_Model_UtilisateurMapper();
        $o_Utilisateur = $o_UtilisateurMapper->find($result['id']);
        if (is_null($o_Utilisateur)) {
            echo 'Cet utilisateur n\'existe pas';
            return false;
        }
        $o_Select = $o_UtilisateurMapper->getDbTable()->select();
        $o_Select->where('utilogin = :login');
        $o_Select->where('utisuppr = FALSE');
        $o_Select->where('utiid != ' . $o_Utilisateur->getId());
        $o_Select->bind(array(':login'=>$result['login']));
        if (!is_null($o_UtilisateurMapper->fetchAll($o_Select))) {
            echo 'Ce login existe deja';
            return false;
        }
        $o_Utilisateur->setUtilogin($result['login']);
        $o_Utilisateur->setUtimail($result['email']);
        if ($result['passwd'] != '') {
            $bcrypt = new Application_Controller_Plugin_Bcrypt();
            $hash = $bcrypt->HashPassword($result['passwd']);
            $o_Utilisateur->setUtimdp($hash);
        }
        $o_Utilisateur->setUtinom($result['lastname']);
        $o_Utilisateur->setUtiprenom($result['firstname']);
        $o_Utilisateur->setUtitel($result['tel']);
        $o_Utilisateur->setUtisuppr(false);
        $o_UtilisateurMapper->save($o_Utilisateur);
        return true;
    }
    
    public function userdeleteAction() {
        $request = $this->getRequest();
        $result = $request->getPost();
        $this->_helper->layout->disableLayout();
        $o_UtilisateurMapper = new Application_Model_UtilisateurMapper();
        $o_Utilisateur = $o_UtilisateurMapper->find((int)$result['id']);
    	if ($o_Utilisateur == null) {
            return false;
        }
        $o_Utilisateur->setUtisuppr(true);
        return $o_UtilisateurMapper->save($o_Utilisateur);
    }
    
    public function profilAction() {
        $this->view->titre = "Gestion des profils";
        $this->view->headLink()->appendStylesheet($this->view->baseUrl() . '/css/admin_profils.css');
    }
    
    
    public function profillistAction() {
        $o_ProfilMapper = new Application_Model_ProfilMapper();
        $tab_Profils = $o_ProfilMapper->findProfils();
        if (count($tab_Profils) == 0) {
            $tab_Profils = false;
        } else {
            $tab_Ressources = $o_ProfilMapper->findRights();
            $tab_CurrentRights = $o_ProfilMapper->findProfilsRights();
            $this->view->rights = $tab_Ressources;
            $this->view->current_rights = $tab_CurrentRights;
        }
        
        $this->view->profils = $tab_Profils;
        $this->_helper->layout->disableLayout();
    }
    
    
    public function profilchangerightAction() {
        $request = $this->getRequest();
        $o_ProfilMapper = new Application_Model_ProfilMapper();
    	$result = $request->getPost();
        
        
        if (!isset($result['id_profil']) || !isset($result['droits'])) {
            return false;
        }
        $o_Profil = $o_ProfilMapper->find((int)$result['id_profil']);
        if (is_null($o_Profil)) {
            return false;
        }
        if (!$o_ProfilMapper->deleteRightsByProfil($o_Profil->getId())) {
            return false;
        }
        if (is_array($result['droits'])) {
            $result['droits'] = array_unique($result['droits']);
            foreach ($result['droits'] as $id_act) {
                if (!$o_ProfilMapper->addProfilRight($o_Profil->getId(), (int)$id_act)) {
                    echo 'bad insert for ' . $id_act;
//                    return false;
                }
                echo 'sccess';
            }
        }
//        $this->render('ajax');
        $this->_helper->layout->disableLayout();
        return true;
    }
    
    public function profiladdAction() {
        $request = $this->getRequest();
        $result = $request->getPost();
        $this->_helper->layout->disableLayout();
        $o_ProfilMapper = new Application_Model_ProfilMapper();
        
        
        
        $o_Select = $o_ProfilMapper->getDbTable()->select();
        $o_Select->where('proflib = :proflib');
        $o_Select->where('profsuppr = FALSE');
        $o_Select->bind(array(':proflib'=>$result['proflib']));
        if (!is_null($o_ProfilMapper->fetchAll($o_Select))) {
            echo 'Ce profil existe deja';
            return false;
        }
        $o_Profil = new Application_Model_Profil();
        $o_Profil->setProflib($result['proflib']);
        $o_Profil->setProfsuppr(false);
        $o_ProfilMapper->save($o_Profil);
        return true;
    }
    
}

