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

    public function satisfactionAction() {

        $clientMapper = new Application_Model_ClientMapper();

        $clientRow = $clientMapper->find($this->_session->cliid);

        $this->view->client = $clientRow;
    }

    public function savesatisfactionAction() {
        $this->render('ajax');
        $this->_helper->layout->disableLayout();
        $dbAdapter = Zend_Registry::get('dbAdapter');
        $q1 = $this->getRequest()->getParam('q1');
        $q2 = $this->getRequest()->getParam('q2');
        $q3 = $this->getRequest()->getParam('q3');
        $q4 = $this->getRequest()->getParam('q4');
        $q5 = $this->getRequest()->getParam('q5');
        $comment = $this->getRequest()->getParam('comment');
	$date = date('d/m/Y');
        $data = array(
            'q1' => $q1,
            'q2' => $q2,
            'q3' => $q3,
            'q4' => $q4,
            'q5' => $q5,
            'satcom' => $comment,
            'satdate' => $date,
            'cliid' => $this->_session->cliid
        );
        if($dbAdapter->insert("satisfaction", $data)){
            return true;
        }
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
        if ($commandes != null) {
            $this->view->commands = $commandes;
        }
        $resultCountDmd = $db->query('SELECT Count(commande.comid) as total FROM commande
        INNER JOIN demande ON commande.demandeid = demande.demandeid
        WHERE commande.cometat = 100 AND demande.cliid = ' . $this->_session->cliid . '');
        $this->view->totalCmd = $resultCountDmd->fetch();
        $resultTotalSatisfaction = $db->query('SELECT Count(satisfaction.satid) as total
        FROM satisfaction WHERE satisfaction.cliid = ' . $this->_session->cliid . '');
        $this->view->totalSatisfaction = $resultTotalSatisfaction->fetch();
    }

    public function testcommandeAction() {
        
    }

    public function ajaxcommandAction() {
        $id = $this->_getParam('id');
        $tab_param = explode('_', $id);
        $comid = $tab_param[1];
        $db = Zend_Registry::get('dbAdapter');

        $res = $db->query('SELECT devid FROM devis WHERE demandeid IN (SELECT demandeid FROM commande WHERE comid = '.$comid.')');
        foreach ($res as $l => $val) {
            if ($l == 0)
                $devid = $val;
        }
        
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
        $devNomDbTable = new Application_Model_DbTable_Devnom();
        $select = $devNomDbTable->select();
        $select->where('devid = ?', $devid);
        $rowsDevNom = $devNomDbTable->fetchAll($select);

        $nomenclatureDbTable = new Application_Model_DbTable_Nomenclature();
        $select = $nomenclatureDbTable->select();

        $arrayNom = array();


        foreach ($rowsDevNom as $val) {
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

    public function registerAction() {
        //$this->_helper->layout->disableLayout();
        $error_auth = false;
        $o_Client = null;
        $message = "";
        $form = new Application_Form_CLient();
        $this->_helper->layout->setLayout('loginclient');
        $o_ClientMapper = new Application_Model_ClientMapper();
        //on l'ajoute a la vue
        $this->view->formClient = $form;
        $request = $this->getRequest();
        //traitement url creation mdp
        if ($request->getParam('url') != null) {
            $this->view->url = $request->getParam('url');
            $o_Select = $o_ClientMapper->getDbTable()->select();
            $o_Select->where("cliurltmp ='" . $this->view->url . "'");
            $tab_Clients = $o_ClientMapper->fetchAll($o_Select);
            if (count($tab_Clients) == 0) {
                $error_auth = true;
                $message = "L'URL est incorrecte, ou vous avez déjà enregistré votre mot de passe. Merci de contacter notre commercial";
            } else {
                foreach ($tab_Clients as $cli) {
                    $o_Client = $cli;
                    $this->view->login = $cli->getClilogin();
                    $this->view->welcome = "Bonjour " . $cli->getClinom() . ", pour acceder à l'application, veuillez créer votre mot de passe.<br />
                    (La longueur minimale est de 6 caractères)";
                }
            }

            if ($this->_request->isPost()) {
                // récuperation des données du formulaire
                $formData = $this->_request->getPost();
                // on check si les donnée sont bien valid
                if ($form->isValid($formData)) {
                    // //récupération des valeurs

                    $password = $form->getValue('password');
                    $conf_password = $form->getValue('password_conf');

                    if ($password != $conf_password) {
                        $message = 'Les mots de passe ne correspondent pas';
                        $error_auth = true;
                    } else {
                        $bcrypt = new Application_Controller_Plugin_Bcrypt();
                        $hash = $bcrypt->HashPassword($password);
                        $o_Client->setClimdp($hash);
                        $o_Client->setCliacces(true);
                        $o_Client->setCliurltmp("");
                        $o_ClientMapper->save($o_Client);
                        $authAdapter = new Application_Model_Common_Bcrypt($dbAdapter);
                        $authAdapter->setTableName('client')
                                ->setIdentityColumn('clilogin')
                                ->setCredentialColumn('climdp')
                                ->setIdentity($login)
                                ->setCredential($password);
                        $o_Select = $authAdapter->getDbSelect();
                        $o_Select->where('clisuppr = false');
                        $o_Select->where('cliacces = true');
                        $this->_redirect('clientinternet/index');
                    }
                }
            }
        } else if ($request->getParam('lost_password') != null) {
            $this->view->welcome = "Bonjour. <br />Pour générer un nouveau mot de passe, merci d'entrer votre adresse email. Un lien vous sera envoyé rapidement.";
            $form = new Application_Form_LostPassword();
            $this->view->formClient = $form;
            if ($this->_request->isPost()) {
                $formData = $this->_request->getPost();
                if ($form->isValid($formData)) {
                    $this->request->email = $form->getValue('email');
                    $o_ClientMapper = new Application_Model_ClientMapper();
                    $o_Select = $o_ClientMapper->getDbTable()->select();
                    $o_Select->where("climail ='" . $this->request->email . "'");
                    $tab_Clients = $o_ClientMapper->fetchAll($o_Select);
                    if (count($tab_Clients) == 0) {
                        $error_auth = true;
                        $message = "<font color='red'>Aucun compte n'est associé à cette adresse.<br /> Merci de contacter notre commercial</font>";
                    } else {
                        foreach ($tab_Clients as $cli) {
                            $o_Client = $cli;
                            $this->view->welcome = "<font color='green'>Une URL de réinitialisation du mot de passe a été envoyée à votre adresse<br /></font>" . $this->request->email;
                            $this->view->uuid = $this->gen_uuid();
                            $this->send_mail($this->view->uuid, $this->request->email, $cli->getClinom());
                            $o_Client->setCliurltmp($this->view->uuid);
                            $o_Client->setCliacces(false);
                            $o_ClientMapper->save($o_Client);
                        }
                        $this->_redirect('clientinternet/');
                    }
                }
            }
        }

        // else {
        //     $error_auth = true;
        //     $message = "L'URL est incorrecte, ou vous avez déjà enregistré votre mot de passe. Merci de contacter notre commercial";
        //     return true;
        // }
        $this->view->error_auth = $error_auth;
        $this->view->message = $message;
    }

    public function gen_uuid() {
        return sprintf('%04x%04x%04x%04x%04x%04x%04x%04x',
                        // 32 bits for "time_low"
                        mt_rand(0, 0xffff), mt_rand(0, 0xffff),
                        // 16 bits for "time_mid"
                        mt_rand(0, 0xffff),
                        // 16 bits for "time_hi_and_version",
                        // four most significant bits holds version number 4
                        mt_rand(0, 0x0fff) | 0x4000,
                        // 16 bits, 8 bits for "clk_seq_hi_res",
                        // 8 bits for "clk_seq_low",
                        // two most significant bits holds zero and one for variant DCE1.1
                        mt_rand(0, 0x3fff) | 0x8000,
                        // 48 bits for "node"
                        mt_rand(0, 0xffff), mt_rand(0, 0xffff), mt_rand(0, 0xffff)
        );
    }

    public function send_mail($uuid, $mail_addr, $nom) {
        $config = array('ssl' => 'tls', 'port' => 587, 'auth' => 'login',
            'username' => 'plastprod76',
            'password' => 'filrouge');

        $transport = new Zend_Mail_Transport_Smtp('smtp.gmail.com', $config);

        $mail = new Zend_Mail('UTF-8');
        date_default_timezone_set('Europe/Berlin');

        $msg = "<html>
        <body>
        <div id='content' style='font-size:17px;'>

        <center><h2>Réinitialisation du mot de passe</h2>
        Bonjour " . $nom . ",<br /><br />
        Votre demande de réinitialisation de mot de passe a bien été prise en compte.<br />
        Afin de redéfinir votre mot de passe, merci de vous rendre sur l'adresse suivante: <br /><br />
        http://cesi2.dev/clientinternet/register?url=" . $uuid . "<br /><br />
        Cordialement,
        Plast' Prod
        </div>
        </body></html>";
        //if no HTML on mail, msg in txt, just in case
        $msg_txt = "Réinitialisation du mot de passe\r\n\r\n
        Bonjour " . $nom . ",\r\n\r\n
        Votre demande de réinitialisation de mot de passe a bien été prise en compte.\r\n
        Afin de redéfinir votre mot de passe, merci de vous rendre sur l'adresse suivante: \r\n\r\n
        http://cesi2.dev/clientinternet/register?url=" . $uuid . "\r\n\r\n
        Cordialement,
        Plast' Prod";
        // $msg .= "\r\n";
        // $msg .= "--$boundary\r\n";
        $expediteur = "Plast'Prod<plast@prod.fr>";
        $reponse = $expediteur;

        $mail->setBodyText($msg_txt);
        $mail->setBodyHtml($msg);
        $mail->setFrom('contact@plastprod.com', 'PlastProd');
        $mail->addTo("sylzys@gmail.com", $nom); //$mail_addr, $nom);
        $mail->setSubject('Votre demande de mot de passe');
        $mail->send($transport);
        //mail($mail, "Demande de réinitialisation de mot de passe", $msg, "Reply-to: $expediteur\r\nFrom: $expediteur\r\n".$header);
    }

}

