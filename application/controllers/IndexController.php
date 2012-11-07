<?php

class IndexController extends Zend_Controller_Action
{

    public function init()
    {
        parent::init();
    }

    public function indexAction()
    {
        $this->_redirect($this->baseUrl . '/home/index');
    }

    public function createqrAction()
    {
        // action body
    }

}









