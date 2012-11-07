<?php

class Application_Controller_Plugin_Ssl extends Zend_Controller_Plugin_Abstract {

    public function preDispatch(Zend_Controller_Request_Abstract $request) {

        $shouldSecureUrl = false;

        //get the config settings for SSL
        $options = new Zend_Config_Ini(APPLICATION_PATH . '/configs/ssl.ini');
        $options = $options->ssl;

        //if config is empty, exit
        if (!is_object($options))
            return;

        //simpler to use
        $options = $options->toArray();

        //only use it production environment
        if (APPLICATION_ENV == 'production') {
            
            //Si l'application est entièrement sécurisé
            if ( isset($options['modules'][$request->module]['require_ssl']) && $options['modules'][$request->module]['require_ssl']) {
                $shouldSecureUrl = true;
                //Si le controller est entièrement sécurisé ou seulement l'action
                if((isset($options['modules'][$request->module][$request->controller]['require_ssl']))){
                    $shouldSecureUrl = $options['modules'][$request->module][$request->controller]['require_ssl'];
                }
                if(( isset($options['modules'][$request->module][$request->controller][$request->action]['require_ssl']))){
                    $shouldSecureUrl = $options['modules'][$request->module][$request->controller][$request->action]['require_ssl'];
                }
            }

            if ($shouldSecureUrl) {

                $this->_secureUrl($request);
            }

        }
    }

    protected function _secureUrl(Zend_Controller_Request_Abstract $request) {
        $server = $request->getServer();
        $hostname = $server['HTTP_HOST'].'/sp-front/public';

        if (!$request->isSecure()) {
            $url = Zend_Controller_Request_Http::SCHEME_HTTPS . "://" . $hostname .
                    $request->getPathInfo();

            $redirector = Zend_Controller_Action_HelperBroker::getStaticHelper('redirector');
            $redirector->setGoToUrl($url);
            $redirector->redirectAndExit();
        }
    }
}

?>