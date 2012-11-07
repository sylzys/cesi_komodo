<?php

/**
 * application/controllers/plugins/Auth.php
 * Plugin pour le contrôle d'accès de l'utilisateur
 * 
 * */
class Plugin_Auth extends Zend_Controller_Plugin_Abstract {

    private $_auth = null;
    private $_acl = null;

    public function __construct($auth, $acl) {
        $this->_auth = $auth;
        $this->_acl = $acl;
    }

    // Vérifie que l'utilisateur a la permission d'accéder au contrôleur de la page demandée
    public function preDispatch(Zend_Controller_Request_Abstract $request) {
        if ($this->_auth->hasIdentity()) {
            // Récupération du type d'utilisateur (= rôle au sens acl)
            $identity = $this->_auth->getIdentity();
            $nom_user = $identity->uti_login;
            $id_role = $identity->uti_id_type_utilisateur;
            $utilisateur = new Application_Model_DbTable_Utilisateur();
            $result = $utilisateur->getTypeUtilisateur($nom_user);
            $nom_role = $result['tu_libelle'];
        } // Fin if($this->_auth->hasIdentity())

        $controller = $request->controller;
        $resource = $this->_acl->has($controller) ? $controller : null;

        if (!$this->_acl->isAllowed($nom_role, $resource)) {
            // Type d'utilisateur non autorisé à accéder à la page
            $request->setControllerName('Auth');
            $request->setActionName('index');
        } // Fin if (!$this->_acl->isAllowed($nom_role, $resource))
    }

// Fin function preDispatch
}