<?php

class clientsController extends Zend_Controller_Action
{

    public function init()
    {
        parent::init();

    }

    public function indexAction()
    {
        $this->view->headLink()->appendStylesheet($this->view->baseUrl() . "/css/filtergrid.css");
        $this->view->headLink()->appendStylesheet($this->view->baseUrl() . "/css/style_annuaire.css");
        $this->view->headScript()->appendFile($this->view->baseUrl() . "/js/tablefilter.js");
       // $this->view->headScript()->appendFile($this->view->baseUrl() . "/js/tablefilter_compressed.js");
        $this->view->headScript()->appendFile($this->view->baseUrl() . "/js/modernizr.js");
        $this->view->headScript()->appendFile($this->view->baseUrl() . "/js/jquery.js");
        $this->view->headScript()->appendFile($this->view->baseUrl() . "/js/jquery-ui.js");
        $this->view->titre = "Liste des Clients";
        $dbAdapter = Zend_Registry::get('dbAdapter');
        //get client name
        $sql = "SELECT * FROM client_comm WHERE clisuppr = false";
        $res = $dbAdapter->fetchAll($sql);
        $this->view->clients = $res;
    }

   public function getclientinfosAction()
    {
        //details commandes client
        $dbAdapter = Zend_Registry::get('dbAdapter');
        $request = $this->getRequest();
        $result = $request->getPost();
        //get client name
        $sql = 'SELECT * FROM detailcommande WHERE comid = ?';
        $res = $dbAdapter->fetchAll($sql, $result['id']);
        echo json_encode((array) $res[0]);
        $this->_helper->layout->disableLayout();
    }


}

