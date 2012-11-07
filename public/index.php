<?php
error_reporting(E_ALL);
// Define path to application directory
defined('APPLICATION_PATH')
    || define('APPLICATION_PATH', realpath(dirname(__FILE__) . '/../application'));

// Define application environment
defined('APPLICATION_ENV')
    || define('APPLICATION_ENV', (getenv('APPLICATION_ENV') ? getenv('APPLICATION_ENV') : 'production'));

// Ensure library/ is on include_path
set_include_path(implode(PATH_SEPARATOR, array(
    realpath(APPLICATION_PATH . '/../library'),
    get_include_path(),
)));

/** Zend_Application */
require_once 'Zend/Application.php';



// Create application, bootstrap, and run
$application = new Zend_Application(
    APPLICATION_ENV,
    APPLICATION_PATH . '/configs/application.ini'
);

// ajout du plugin controller pour rediriger directement vers le login 
// quand l'utilisateur n'est pas loguer
require_once APPLICATION_PATH . '/../application/plugin/Global.php';

define("ROOT_URL",'http://127.0.0.1');

$application->bootstrap()
            ->run();
?>