<?php

/**
 * Aide d'action de chargement de formulaires
 * 
 * @uses Zend_Controller_Action_Helper_Abstract
 */
class Helper_IsAllowed extends Zend_Controller_Action_Helper_Abstract {

    /**
     * @var Zend_Loader_PluginLoader
     */
    public $pluginLoader;
    protected $_controller;
    protected $_action;

    /**
     * Constructeur: initialisee le chargeur de classes d'aides ou plugins
     * 
     * @return void
     */
    public function __construct() {
        $this->pluginLoader = new Zend_Loader_PluginLoader();
    }

    /**
     * Charge le formulaire avec les options passées
     * 
     * @return true or false if user is allowed to access
     */
    public function isAllowed() {
        $acl = null;
        if (Zend_Registry::isRegistered('My_Acl')) {
            //récupération de l'ACL
            $acl = Zend_Registry::get('My_Acl');
        }
        //Récupération de l'authentification
        $_auth = Zend_Auth::getInstance();
        //Si l'utilisateur n'est pas authentifié il est alors en guest
        $role = (!$_auth->hasIdentity()) ? 'guest' : ((isset($_auth->getIdentity()->authorities[0]) ? $_auth->getIdentity()->authorities[0] : 'guest'));
        return $acl->isAllowed($role, $this->_controller, $this->_action);
    }

    /**
     * Strategy pattern: appelle l'aide comme méthode du contrôleur d'action
     * 
     * @param  string $name 
     * @param  array|Zend_Config $options 
     * @return Zend_Form
     */
    public function direct($controller, $action) {
        $this->_controller = $controller;
        $this->_action = $action;
        return $this->isAllowed();
    }

}

?>
