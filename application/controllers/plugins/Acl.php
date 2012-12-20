<?php
 
final class Application_Controller_Plugin_Acl extends Zend_Controller_Plugin_Abstract {
    
    protected $_auth;
    protected $_acl;
    protected $_authPage;
    protected $_cliPage;
    protected $_cliPageRegister;
    protected $_errorPage;
    protected $_request;

    public function __construct() {
        $this->_authPage = array(
            'client' => array('module' => 'default', 'controller' => 'Login', 'action' => 'loginclient'),
            'user' => array('module' => 'default', 'controller' => 'Login', 'action' => 'loginuser')
        );
        $this->_cliPage = array('module' => 'default', 'controller' => 'clientinternet', 'action' => 'index');
        $this->_cliPageRegister = array('module' => 'default', 'controller' => 'clientinternet', 'action' => 'register');
        $this->_errorPage = array('module' => 'default', 'controller' => 'error', 'action' => 'denied');
        
        $this->_auth = Zend_Auth::getInstance();
        if (Zend_Registry::isRegistered('My_Acl')) {
            $this->_acl = Zend_Registry::get('My_Acl');
        } else {
            throw new Zend_Controller_Exception("Acl not defined !");
        }
    }
 
    public function getAuth() {
        return $this->_auth;
    }
    
    public function getAcl() {
        return $this->_acl;
    }
    
    public function preDispatch(Zend_Controller_Request_Abstract $request) {
        $this->_request = $request;
        $module = $this->_request->getModuleName();
        $controller = $ressource = strtolower($this->_request->getControllerName());
        $action = $this->_request->getActionName();
        if (in_array($controller, array('clientinternet', 'login')) && isset($this->getAuth()->getIdentity()->cliid)) {
            return;
        } elseif (!isset($this->getAuth()->getIdentity()->cliid) && $controller == "clientinternet" && $action == "register") {
            // on est pas logged, on accepte de passer par la page register
            //$this->redirectClientRegister();
            return;
        } elseif (isset($this->getAuth()->getIdentity()->cliid) && !in_array($controller, array('clientinternet', 'login'))) {
            // on est logged on redirige vers la page par defaut
            $this->redirectClient();
            return;
        }
        
        $access_granted = false;
        $roles = $this->getAcl()->getRoles();
        
        if (!$this->getAcl()->has($controller)) {
            $ressource = null;
        }
        foreach ($roles as $role) {
//            echo "role:'$role->proflib', ressource:'$ressource', action:'$action'";
            if ($this->getAcl()->isAllowed($role->proflib, $ressource, $action)) {
                $access_granted = true;
            }
        }
        if (!$access_granted) {
            if (!$this->getAuth()->hasIdentity()) {
                $this->notLogged($controller);
            } else {
                $this->denyAccess();
            }
        }
    }
    
    public function notLogged($from_ctrl) {
        if (strtolower($from_ctrl) == 'clientinternet') {
            $this->_request->setModuleName($this->_authPage['client']['module']);
            $this->_request->setControllerName($this->_authPage['client']['controller']);
            $this->_request->setActionName($this->_authPage['client']['action']);
        } else {
            $this->_request->setModuleName($this->_authPage['user']['module']);
            $this->_request->setControllerName($this->_authPage['user']['controller']);
            $this->_request->setActionName($this->_authPage['user']['action']);
        }
    }
    
    public function denyAccess() {
        $this->_request->setModuleName($this->_errorPage['module']);
        $this->_request->setControllerName($this->_errorPage['controller']);
        $this->_request->setActionName($this->_errorPage['action']);
    }
    
    public function redirectClient() {
        $this->_request->setModuleName($this->_cliPage['module']);
        $this->_request->setControllerName($this->_cliPage['controller']);
        $this->_request->setActionName($this->_cliPage['action']);
    }
    
    public function redirectClientRegister() {
        $this->_request->setModuleName($this->_cliPageRegister['module']);
        $this->_request->setControllerName($this->_cliPageRegister['controller']);
        $this->_request->setActionName($this->_cliPageRegister['action']);
    }
 
}

?>