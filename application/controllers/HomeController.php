<?php

class homeController extends Zend_Controller_Action
{

    public function init()
    {
        parent::init();
        /* Initialize action controller here */
    }

    public function getnotifsAction()
    {
       $dbAdapter = Zend_Registry::get('dbAdapter');
       if ($this->getRequest()->getParam('show_header') && $this->getRequest()->getParam('id')) {
         $id = $this->getRequest()->getParam('id');
         $res = $dbAdapter->query("SELECT suivdosid, comid, interid, devid, suivdosdate, suivdoscom, suividossuppr FROM get_notification(".$id.") AS (suivdosid integer, comid integer, interid integer, devid integer, suivdosdate date, suivdoscom varchar(1000), suividossuppr bool)");
         $res->setFetchMode(Zend_Db::FETCH_OBJ);
         $result = array();
         while ($row = $res->fetch()) {
            $result[] = (array)$row;
        }
        $div = "<div id=\"all_msg\">";
        foreach ($result as $a) {
          $div.=" <header name=\"".$a['suivdosid']."\"  style=\"border:1px solid;";
          if ($a['suividossuppr'] == false)
            $div .= "font-weight:bold;";
        $div .= "\"> De Toto: ".substr($a['suivdoscom'], 0, 35)."</header>";
        $div .= "<article style=\"display:none;\" class=\"msg_content\" name=\"".$a['suivdosid']."\"></article>";
        $div .="<a href=\"#\">Afficher</a>";
    }
    $div .="</div>";
    echo $div;
    $this->_helper->layout->disableLayout();
}
else if ($this->getRequest()->getParam('show_msg') && $this->getRequest()->getParam('id')) {
    $id = $this->getRequest()->getParam('id');
    $res = $dbAdapter->query("SELECT suivdosid, comid, interid, devid, suivdosdate, suivdoscom, suividossuppr FROM get_notification(1) AS (suivdosid integer, comid integer, interid integer, devid integer, suivdosdate date, suivdoscom varchar(1000), suividossuppr bool) WHERE suivdosid=". $this->getRequest()->getParam('id'));
    $res->setFetchMode(Zend_Db::FETCH_OBJ);
    $result = array();
    while ($row = $res->fetch()) {
        $result[] = (array)$row;
    }
    echo $result[0]['suivdoscom'];

}
else if ($this->getRequest()->getParam('read') && $this->getRequest()->getParam('id')) {
   $id = $this->getRequest()->getParam('id');
   $dbAdapter->query("UPDATE suivdossier SET suividossuppr = true WHERE suivdosid = ".$id);
}
else {
    if ($this->getRequest()->getParam('id')) {
        $id = $this->getRequest()->getParam('id');

        $res = $dbAdapter->query("SELECT suivdosid, comid, interid, devid, suivdosdate, suivdoscom, suividossuppr FROM get_notification(".$id.") AS (suivdosid integer, comid integer, interid integer, devid integer, suivdosdate date, suivdoscom varchar(1000), suividossuppr bool) WHERE suividossuppr = false");

        $res->setFetchMode(Zend_Db::FETCH_OBJ);

        $result = array();
        while ($row = $res->fetch()) {
            $result[] = $row;
        }
        if (count($result) == 0)
            echo json_encode("");
        else
            echo json_encode(count($result));
    }
}
$this->_helper->layout->disableLayout();

}


/* Initialize action controller here */
public function indexAction() {
//        $auth = Zend_Auth::getInstance();
//	if(!$auth->hasIdentity()) {
//            $this->_redirect('/login/loginuser');
//	}
    $this->_helper->layout->setLayout('layout');
    $this->view->titre = "Accueil";

    $oProfilMapper = new Application_Model_ProfilMapper();
    $tabProfil = $oProfilMapper->fetchAll();
    $this->view->profils = $tabProfil;

    $oUtilisateurMapper = new Application_Model_UtilisateurMapper();

    // $dbAdapter = Zend_Registry::get('dbAdapter');
    // $res = $dbAdapter->query("SELECT suivdosid, comid, interid, devid, suivdosdate, suivdoscom, suividossuppr FROM get_notification(".$id.") AS (suivdosid integer, comid integer, interid integer, devid integer, suivdosdate date, suivdoscom varchar(1000), suividossuppr bool)");
    // $res->setFetchMode(Zend_Db::FETCH_OBJ);
    // $result = array();
    // while ($row = $res->fetch()) {
    //     $result[] = (array)$row;
    // }
    // $this->view->lol = $result;
//        $oNewUser = new Application_Model_Utilisateur();
//        $oNewUser->setUtilogin('kikoolol');
//        $oUtilisateurMapper->save($oNewUser);
    $where = array('utisuppr = false');
    $tabUtilisateur = $oUtilisateurMapper->getWhere($where);
    $this->view->users = $tabUtilisateur;
    $this->view->user_find = $oUtilisateurMapper->find(7);

//        $db->getProfiler()->setEnabled(true);
//        print $db->getProfiler()->getLastQueryProfile()->getQuery();
//        print_r($db->getProfiler()->getLastQueryProfile()->getQueryParams());
//        $db->getProfiler()->setEnabled(false);

//        $oUserUpdate = array_pop($tabUtilisateur);
//        $oUserUpdate->setUtilogin('plopouille');
//        $oUtilisateurMapper->save($oUserUpdate);

}


}

