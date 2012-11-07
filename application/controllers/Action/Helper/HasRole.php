<?php

/**
 * Aide d'action de chargement de formulaires
 * 
 * @uses Zend_Controller_Action_Helper_Abstract
 */
class Helper_HasRole extends Zend_Controller_Action_Helper_Abstract {

    /**
     * @var Zend_Loader_PluginLoader
     */
    public $pluginLoader;
    protected $_roles;
    protected $_roleName;

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
    public function hasRole() {
        foreach ($this->_roles as $role){
            if($role->getName() == $this->_roleName){
                return true;
            }
        }
        return false;
    }

    /**
     * Strategy pattern: appelle l'aide comme méthode du contrôleur d'action
     * 
     * @param  string $name 
     * @param  array|Zend_Config $options 
     * @return Zend_Form
     */
    public function direct($roles, $roleName) {
        $this->_roles = $roles;
        $this->_roleName = $roleName;
        return $this->hasRole();
    }

}

?>
