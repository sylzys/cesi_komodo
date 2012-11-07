<?php


class Bootstrap extends Zend_Application_Bootstrap_Bootstrap
{
    
    
    
    /**
     * function initPath, initialise les path en fonction globale
     *
     * @return void
     *
     */
    public function _initPath() {
        // Define service path
        $config = new Zend_Config_Ini(APPLICATION_PATH . '/configs/application.ini', APPLICATION_ENV);
        defined('SERVICE_PATH') || define('SERVICE_PATH', (getenv('SERVICE_PATH') ? getenv('SERVICE_PATH') : $config->service_path));
        
        defined('BASE_PATH') || define('BASE_PATH', dirname(realpath(dirname(__FILE__))));

        
    }

    
    
    /**
     * function _initTranslate, Initialisation de la traduction par défaut langue française
     *
     * @return void
     */
    public function _initTranslate() {

        try {
            $locale = new Zend_Locale();
            $locale->setDefault('fr_FR');

            $translate = new Zend_Translate('ini', APPLICATION_PATH . '/languages/lang.fr.ini', 'fr');
            $translate->addTranslation(APPLICATION_PATH . '/languages/lang.en.ini', 'en');

            Zend_Registry::set('Zend_Translate', $translate);
            $translate->setLocale('fr_FR');
        } catch (Exception $e) {
            // TODO LOG
            echo $e;
        }
        $configSession = new Zend_Session_Namespace('config');
        if (!isset($configSession->lang)) {
            $configSession->lang = 'fr_FR';
        }
        $translate->setLocale($configSession->lang);
    }
    
    protected function _initView()
    {
         
    }
    
    protected function _initAutoloadRessource() {
        //configuration de l'Autoload
        $ressourceLoader = new Zend_Loader_Autoloader_Resource(array(
                    'namespace' => 'Application',
                    'basePath' => dirname(__FILE__),
                ));

        //permet d'indiquer les répertoires dans lesquels se trouveront nos classes:
        //notamment, l'ACL et le pugin
        $ressourceLoader->addResourceType('plugin', 'controllers/plugins/', 'Controller_Plugin');
        $ressourceLoader->addResourceType('controller', 'controllers/', 'Controller');
        $ressourceLoader->addResourceType('mapper', 'models/Mapper', 'Model_Mapper');
        $ressourceLoader->addResourceType('element', 'models/Element', 'Model_Element');


        return $ressourceLoader;
    }
    
    
     protected function _initDB() {
       //Chargement de la configuration dans le fichier 'ini'
       $dbConfig = new Zend_Config_Ini(APPLICATION_PATH . '/configs/application.ini', 'production');
       //Construction de l'adapter avec les paramètres
       $dbAdapter = Zend_Db::factory($dbConfig->database->adapter, array(
            'host'     => $dbConfig->database->params->host,
            'port'     => $dbConfig->database->params->port,
            'username' => $dbConfig->database->params->username,
            'password' => $dbConfig->database->params->password,
            'dbname'   => $dbConfig->database->params->dbname,
            'persistent' => $dbConfig->database->params->persistent   
        ));
       //adapter par défault
        Zend_Db_Table_Abstract::setDefaultAdapter($dbAdapter);
        //Sauvegarde dans Zend Regitry
        Zend_Registry::set('dbAdapter', $dbAdapter);
    }

//a commenter si souci d'ACL
    protected function _initAcl() {
       //Création d'une instance de notre ACL
       if (Zend_Registry::isRegistered('My_Acl')) {
           $acl = Zend_Registry::get('My_Acl');
       } else {
           $acl = new Application_Model_Common_MyAcl();
       }
       //enregistrement du plugin de manière à ce qu'il soit exécuté
       Zend_Controller_Front::getInstance()->registerPlugin(new Application_Controller_Plugin_Acl());

       // permet de définir l'acl par défaut à l'aide de vue, de cette manière
        //l'ACL est accessible dans les vues
        Zend_View_Helper_Navigation_HelperAbstract::setDefaultAcl($acl);

        //vérifie si une identité existe et applique le rôle
       $auth = Zend_Auth::getInstance();
     //  $role = (!$auth->hasIdentity()) ? 'guest' : $auth->getIdentity()->authorities;
    }

    protected function _initErrorHandlerPlugin() {
        $config = new Zend_Config_Ini(APPLICATION_PATH . '/configs/application.ini', APPLICATION_ENV);
        $eh = new Zend_Controller_Plugin_ErrorHandler();
        $eh->setErrorHandlerModule($config->errorhandlerplugin->module)
                ->setErrorHandlerController($config->errorhandlerplugin->controller)
                ->setErrorHandlerAction($config->errorhandlerplugin->action);
        Zend_Controller_Front::getInstance()->throwExceptions(false);
        Zend_Controller_Front::getInstance()->registerPlugin($eh);

        return $eh; // Eventuellement pour plus tard
    }

    protected function _initHelperPath() {
        //On enregistre le plugin
        Zend_Controller_Action_HelperBroker::addPath(APPLICATION_PATH . '/controllers/Action/Helper', 'Helper_');
    }
    
    public function _initAutoloading() {
	$frontController = Zend_Controller_Front::getInstance();
	$frontController->registerPlugin(new My_Controller_Plugin_Global());
    } 
   

}

