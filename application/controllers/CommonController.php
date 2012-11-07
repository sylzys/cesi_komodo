<?php

class CommonController extends Zend_Controller_Action {


    public function init() {
        parent::init();
    }

    public function headerAction() {
        
    }
    
    public function indexAction() {
        
    }
    
    public function menuAction() {
        $auth = Zend_Auth::getInstance();
	if($auth->hasIdentity()) {
            $this->view->a= $auth['utilogin'];
        }
        $this->view->a= $auth['utilogin'];
    }

    public function logajaxAction() {
        $this->_helper->layout->disableLayout();
        $this->render('ajax');
        $lineNumber = $this->getRequest()->getParam('line');
        $fileName = $this->getRequest()->getParam('fileName');
        $contentError = $this->getRequest()->getParam('contentError');

        $rec = Zend_Registry::get('Logger');
        $logger = $rec->getLogger();
        $logger->log('----------------- ERREUR AJAX -----------------' . "\n ligne : " . $lineNumber . "\n  file name : " . $fileName . "\n " . $contentError, Zend_Log::CRIT);
    }


    public function footerAction() {
        $this->_helper->layout->setLayout('layout');
    }


    private function curl_get_file_contents($URL) {
        $URL = 'http://www.galileo-web.com/photoblog/wp-content/photos/photo_tour_eiffel_1.jpg';
        $c = curl_init();
        curl_setopt($c, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($c, CURLOPT_URL, $URL);
        $contents = curl_exec($c);
        curl_close($c);

        if ($contents)
            return $contents;
        else
            return FALSE;
    }

}

?>