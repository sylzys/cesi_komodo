<?php

class ClientinternetController extends Zend_Controller_Action {

    private $sess;

    public function init() {
        parent::init();
        /* Initialize action controller here */
        $this->_helper->layout->setLayout('client');
        //$this->sess = Zend_Auth::getInstance()->getIdentity();
    }

    public function indexAction() {
        $this->_redirect($this->baseUrl . '/clientinternet/viewcommands');
    }

    public function viewcommandsAction() {
        $clientMapper = new Application_Model_ClientMapper();

        $clientRow = $clientMapper->find($this->_session->cliid);

        $this->view->client = $clientRow;

        $this->view->titre = "Commande client";

        $db = Zend_Registry::get('dbAdapter');

        $result = $db->query("select comid from get_clicom(" . $this->_session->cliid . ") as (comid int) ");
        
        $commandes = array();
        
        foreach ($result as $k) {
            $commandeMapper = new Application_Model_CommandeMapper();
            $commandes[] = $commandeMapper->find($k['comid']);
        }
        if($commandes != null){
            $this->view->commands = $commandes;
        }
    }

    public function ajaxcommandAction() {
        $id = $this->_getParam('id');
        $tab_param = explode('_', $id);

        $db = Zend_Registry::get('dbAdapter');

        $result = $db->query('select * from detailcommande where comid=' . $tab_param[1]);

        $suivieDossierMapper = new Application_Model_SuivieDossierMapper();
        $suivieDossier = new Application_Model_DbTable_Suiviedossier();
        $selectSuivieDossier = $suivieDossier->select();
        $selectSuivieDossier->where('comid = ' . $tab_param[1]);

        $resultSuivieDossier = $suivieDossierMapper->fetchAll($selectSuivieDossier);

        foreach ($result as $k => $value) {
            if ($k == 0)
                $resultCommand[] = $value;
        }
        foreach ($resultSuivieDossier as $k => $value) {
            $resultsuivie[] = $value->getSuivdoscom();
        }
        $comNomDbTable = new Application_Model_DbTable_Comnom();
        $select = $comNomDbTable->select();
        $select->where('comid = ?', $tab_param[1]);
        $rowsComNom = $comNomDbTable->fetchAll($select);

        $nomenclatureDbTable = new Application_Model_DbTable_Nomenclature();
        $select = $nomenclatureDbTable->select();

        $arrayNom = array();


        foreach($rowsComNom as $val) {
            $nomenclatureDbTable = new Application_Model_DbTable_Nomenclature();
        $select = $nomenclatureDbTable->select();
            $select->where('nomid=?', $val->nomid);
            $row = $nomenclatureDbTable->fetchRow($select);
            if ($row != null) {
                $arrayNom[] = $row['nomlib'];
            }
        }

        //$html = '<p>'.$resultCommand['cometat'].'</p><p>Voir la nomenclature.</p>';
        //header('Content-Type: text/html; charset=utf-8');
        //echo $html;

        $this->_helper->layout->disableLayout();

        header('Content-Type: application/json;');
        $finalArray = array($resultsuivie, $resultCommand, $arrayNom);
        $json = json_encode($finalArray);
        echo $json;
    }

    public function ajaxcommentAction() {
        $id = $this->_getParam('id');
        $tab_param = explode('_', $id);
        $comid = $tab_param[1];
        $comment = $this->getRequest()->getParam('comment');
        $idCli = $this->_session->cliid;

        $interlocuteurMapper = new Application_Model_InterlocuteurMapper();
        $dbInter = new Application_Model_DbTable_Interlocuteur();
        $select = $dbInter->select();
        $select->where('cliid = ' . $idCli);
        $interModel = $interlocuteurMapper->fetchAll($select);

        $dateCom = new Zend_Date();

        $commentModel = new Application_Model_SuivieDossier();
        $commentModel->setSuivdoscom($comment);
        $commentModel->setComid($comid);
        $commentModel->setInterid($interModel[0]->getId());
        $commentModel->setSuivdosdate($dateCom->toString('YYYY-MM-dd'));

        $commentMapper = new Application_Model_SuivieDossierMapper();
        $commentMapper->save($commentModel);
        $this->_helper->layout->disableLayout();
    }

}

