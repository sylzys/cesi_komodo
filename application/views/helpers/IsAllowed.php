<?php

/**
 * Classe permetant de savoir si l'utilisateur connecté est autorisé a utilisé l'action du controleur.
 *
 */
class Zend_View_Helper_IsAllowed extends Zend_View_Helper_Abstract {

    public function isAllowed($controller, $action) {
        $acl = null;
        if (Zend_Registry::isRegistered('My_Acl')) {
            //récupération de l'ACL
            $acl = Zend_Registry::get('My_Acl');
        }
        //Récupération de l'authentification
        $_auth = Zend_Auth::getInstance();
        //Si l'utilisateur n'est pas authentifié il est alors en guest
        $role = (!$_auth->hasIdentity()) ? 'guest' : ((isset($_auth->getIdentity()->authorities[0]) ? $_auth->getIdentity()->authorities[0] : 'guest'));
        return $acl->isAllowed($role, $controller, $action);
    }

}

?>
