<?php
/*
 Document   : stockController
Created on : 21 mars 2012, 17:41:17
Author     : Lavie
Description:
class php pour le controller stock
*/

/*
TODO
*/
class stockController extends Zend_Controller_Action
{
	public function init()
	{
		parent::init();
	}
	
	public function indexAction()
	{
		//redirection vers la gestion des matières premières
		$this->_redirect($this->baseUrl . '/stock/gestionstock');

	}
	//Fonction pour récupérer les matières premières, les qte et les seuils d'alertes
	public function gestionstockAction()
	{
		$this->view->titre = "Gestion des stocks";
		//Insertion du css
		$this->view->headLink()->appendStylesheet($this->view->baseUrl() . '/css/stock.css');
		//insertion du javascript
		$this->view->headScript()->appendFile($this->view->baseUrl() . '/js/stock.js');
		$dbAdapter = Zend_Registry::get('dbAdapter');
		//matiere premiere et quantité
		$reqMat = 'SELECT * FROM matunite WHERE matsuppr = false ORDER BY matlib';
		$tabMatiere = $dbAdapter->fetchAll($reqMat);
		$reqQte = "SELECT * FROM qtestock WHERE stocksortie='' OR stocksortie IS NULL";
		$qte = $dbAdapter->fetchAll($reqQte);
		$reqSeuil = "SELECT * FROM seuilmat";
		$seuil = $dbAdapter->fetchAll($reqSeuil);
		$this->view->matiere = $tabMatiere;
		$this->view->qte = $qte;
		$this->view->seuil = $seuil;
	}
	//Fonction pour afficher l'historique des commandes
	public function historiquecommandeAction()
	{
		$this->view->titre = "Historique des commandes";
		//Insertion du css
		$this->view->headLink()->appendStylesheet($this->view->baseUrl() . '/css/historiquecommand.css');
		$dbAdapter = Zend_Registry::get('dbAdapter');
		//matiere premiere et quantitÃ©
		$reqCmdMat = 'SELECT * FROM cmd_mat ORDER BY cmdmatid DESC';
		$tabCmdMat = $dbAdapter->fetchAll($reqCmdMat);
		$this->view->cmdMat = $tabCmdMat;
	}
	//Fonction pour supprimer un fournisseur
	public function deletefournAction() {
		//On récupère l'ID dfournisseur
		$frnid = $this->_getParam('id');
		//Découpage du parametre pour ne garder que l'ID
		$tab_param = explode('_', $frnid);
		$db = Zend_Registry::get('dbAdapter');
		//Update de la table fournisseur (foursuppr à true)
		$query = "UPDATE fournisseur SET foursuppr = true WHERE fourid = $tab_param[1]";
		//Exécution de la requete
		$result = $db->query($query);
		//Update de la table interlocuteur (intersuppr à true)
		$query = "UPDATE interlocuteur SET intersuppr = true WHERE fourid = $tab_param[1]";
		//Exécution de la requete
		$result = $db->query($query);
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour modifier un fournisseur
	public function editfournAction() {
		$request = $this->getRequest();
		//Si la requête est de type post
		if ($request->isPost()) {
			//Si il y'a des données
			if ($request->getPost()) {
				//Nouvel adapter
				$dbAdapter = Zend_Registry::get('dbAdapter');
				//Requete fournisseur
				$fourid = $request->getPost('idFrn');
				$rowsFrn = array(
						'fourrais' => $request->getPost('raisFrn'),
						'fournom' => $request->getPost('nomFrn'),
						'fouradresse' => $request->getPost('addrFrn'),
						'fourcp' => $request->getPost('cpFrn'),
						'fourville' => $request->getPost('villeFrn')
				);
				$tableFour = 'fournisseur';
				$where = "fourid = $fourid";
				$dbAdapter->update($tableFour, $rowsFrn, $where);
				//Requete interlocuteur
				$interid = $request->getPost('idInter');
				$rowsInter = array(
						'internom' => $request->getPost('nomInter'),
						'interprenom' => $request->getPost('prenomInter'),
						'intermail' => $request->getPost('emailInter'),
						'intertel' => $request->getPost('telInter')
				);
				$tableInter = 'interlocuteur';
				$where = "interid = $interid";
				$dbAdapter->update($tableInter, $rowsInter, $where);
			}
		}
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour ajouter un fournisseur
	public function addfournAction() {
		$request = $this->getRequest();
		//Si la requête est de type post
		if ($request->isPost()) {
			//Si il y'a des données
			if ($request->getPost()) {
				//Nouvel adapter
				$dbAdapter = Zend_Registry::get('dbAdapter');
				//On récupère les infos du formulaire
				$rowsFour = array(
						'fourrais' => $request->getPost('raisFrn'),
						'fournom' => $request->getPost('nomFrn'),
						'fouradresse' => $request->getPost('addrFrn'),
						'fourcp' => $request->getPost('cpFrn'),
						'fourville' => $request->getPost('villeFrn'),
						'foursuppr'	=>	'false'
				);
				$tableFour = 'fournisseur';
				$dbAdapter->insert($tableFour, $rowsFour);
				$slctLastId = "SELECT last_value FROM fournisseur_fourid_seq";
				$idfrnarray = $dbAdapter->fetchAll($slctLastId);
				$rowsInter = array(
						'fourid' => $idfrnarray[0]['last_value'],
						'internom' => $request->getPost('nomInter'),
						'interprenom' => $request->getPost('prenomInter'),
						'intermail' => $request->getPost('emailInter'),
						'intertel' => $request->getPost('telInter'),
						'intersuppr'	=>	'false'
				);
				$tableInter = 'interlocuteur';
				$dbAdapter->insert($tableInter, $rowsInter);
				//Exécution de la requête
				$result = $dbAdapter->query($query);
				$this->_helper->layout->disableLayout();
			}
		}
	}
	//Fonction pour récupérer les informations d'un interlocuteur
	public function interfrnAction()
	{
		//On récupère l'ID de l'interlocuteur
		$interid = $this->_getParam('id');
		//Découpage du parametre pour ne garder que l'ID
		$tab_param = explode('_', $interid);
		$db = Zend_Registry::get('dbAdapter');
		//Select de l'interlocuteur
		$query = "SELECT * FROM interlocuteur WHERE interid = $tab_param[1]";
		//Exécution de la requete
		$result = $db->query($query);
		//Boucle sur le résultat
		foreach($result as $k => $value)
		{
			$resultInter[] = $value;
		}
		if(isset($resultInter))
		{
			//Entete JSON
			header('Content-Type: application/json;');
			//Encodage du résultat en JSON
			$json = json_encode($resultInter);
			echo $json;
		}
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour récupérer les données d'un fournisseur
	public function infosupdtfrnAction()
	{
		//On récupère l'ID
		$frnid = $this->_getParam('id');
		//Découpage du parametre pour ne garder que l'ID
		$tab_param = explode('_', $frnid);
		$db = Zend_Registry::get('dbAdapter');
		//Select de l'interlocuteur
		$query = "SELECT * FROM frninter WHERE fourid = $tab_param[1]";
		//Exécution de la requete
		$result = $db->query($query);
		//Boucle sur le résultat
		foreach($result as $k => $value)
		{
			$resultFrn[] = $value;
		}
		if(isset($resultFrn))
		{
			//Entete JSON
			header('Content-Type: application/json;');
			//Encodage du résultat en JSON
			$json = json_encode($resultFrn);
			echo $json;
		}
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour sélectionner les fournisseurs
	public function gestionfournisseurAction()
	{
		$this->view->titre = "Gestion des fournisseurs";
		//Insertion du css
		$this->view->headLink()->appendStylesheet($this->view->baseUrl() . '/css/fournisseur.css');
		//Insertion du javascript
		$this->view->headScript()->appendFile($this->view->baseUrl() . '/js/frn.js');
		$dbAdapter = Zend_Registry::get('dbAdapter');
		$reqFrn = 'SELECT * FROM frninter ORDER BY fournom ASC';
		$tabFrn = $dbAdapter->fetchAll($reqFrn);
		$this->view->frn = $tabFrn;
	}
	//Fonction pour sélectionner les matières premières
	public function recherchenomenclatureAction()
	{
		//Insertion du css
		$this->view->headLink()->appendStylesheet($this->view->baseUrl() . '/css/recherchenom.css');
		$this->view->titre = "Liste des nomenclatures";
		$dbAdapter = Zend_Registry::get('dbAdapter');
		//liste fournisseur
		$sql = 'SELECT * FROM matiere WHERE matsuppr = false';
		$res = $dbAdapter->fetchAll($sql);
		$this->view->matiere = $res;
	}
	//Fonction pour rechercher les nomenclatures associées à une matière
	public function ajaxlistnomAction()
	{
		//On récupère l'ID de la matière première
		$id = $this->_getParam('matid');
		//Découpage du paramètre pour ne garder que l'ID
		$tab_param = explode('_', $id);

		$db = Zend_Registry::get('dbAdapter');
		//Requete pour sélectionner les nomenclatures associées à la matière
		$query = "SELECT nomid,nomlib,nomdate,matqte,unitelbl FROM get_nommat($tab_param[1])AS(nomid integer, nomlib varchar(50), nomdate date, matqte integer, unitelbl varchar(50)) ORDER BY nomid ASC";
		$result = $db->query($query);
		//Boucle sur le résultat
		foreach($result as $k => $value)
		{
			$resultMat[] = $value;
		}
		//Si résultatMat est déclaré
		if(isset($resultMat))
		{
			//Entete JSON
			header('Content-Type: application/json;');
			//Encodage du résultat en JSON
			$json = json_encode($resultMat);
			echo $json;
		}
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour récupérer  le détail d'une matière en ajax
	public function ajaxeditmatAction()
	{
		//On récupère l'ID de la matière première
		$id = $this->_getParam('id');
		//Découpage du paramètre pour ne garder que l'ID
		$tab_param = explode('_', $id);

		$db = Zend_Registry::get('dbAdapter');
		//Requète qui récupère le détail d'une matière première (appel d'une fonction postgres --> GET_STOCK)
		$query = "SELECT matid, fourid, fourrais, fournom, fouradresse, fourcp, fourville, foursuppr, interid,
		internom, interprenom, intermail, intertel, intersuppr, stockid, stockdatecom, stockentree, stocksortie, stockqte,
		stockreffour, stockpu, stocksuppr, matlib, matsuppr
		FROM
		get_stock($tab_param[1])
		AS
		(matid integer, fourid integer, fourrais varchar(10), fournom varchar(50), fouradresse varchar(250), fourcp varchar(10),
		fourville varchar(100), foursuppr bool, interid integer, internom varchar(50), interprenom varchar(50), intermail varchar(50),
		intertel varchar(20), intersuppr bool, stockid integer, stockdatecom varchar(10), stockentree varchar(10), stocksortie varchar(10), stockqte integer,
		stockreffour varchar(100), stockpu integer, stocksuppr bool, matlib varchar(100), matsuppr bool) ORDER BY stockid";
		$result = $db->query($query);
		//Enregistrement du résultat dans un tableau
		foreach($result as $k => $value)
		{
			if($value['stocksortie'] == "")
				$resultMat[] = $value;
		}
		//Si le résultat est vide
		if(empty($resultMat))
		{
			//On met la variable à false
			$resultMat[] = false;
		}
		//Entête JSON
		header('Content-Type: application/json;');
		//Encodage du résultat en JSON
		$json = json_encode($resultMat);
		echo $json;
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour récupéérer les fournisseurs
	public function ajaxlistfourAction()
	{
		$db = Zend_Registry::get('dbAdapter');
		//liste fournisseur
		$query = "SELECT * FROM fournisseur";
		$result = $db->query($query);
		foreach($result as $k => $value)
		{
			$resultFour[] = $value;
		}
		//Entete JSON
		header('Content-Type: application/json;');
		//Encodage du résultat en JSON
		$json = json_encode($resultFour);
		echo $json;
		$this->_helper->layout->disableLayout();
	}
	//Fonction poursélectionner les informations d'un fournisseur
	public function ajaxinfosfourAction(){
		//On récupère l'ID du fournisseur
		$id = $this->_getParam('id_four');
		//Découpage du paramètre pour ne garder que l'ID
		$db = Zend_Registry::get('dbAdapter');
		//Info fournisseur
		$query = "SELECT fourid, fourrais, fournom, fouradresse, fourcp, fourville,
		interid, internom, interprenom, intermail, intertel
		FROM
		get_infosFour($id)
		AS
		(fourid integer, fourrais varchar(10), fournom varchar(50), fouradresse varchar(250), fourcp varchar(10),
		fourville varchar(100), interid integer, internom varchar(50), interprenom varchar(50), intermail varchar(50),
		intertel varchar(20))";
		$result = $db->query($query);
		//Enregistrement du résultat dans un tableau
		foreach($result as $k => $value)
		{
			$resultInfosFour[] = $value;
		}
		//Entete JSON
		header('Content-Type: application/json;');
		//Encodage du résultat en JSON
		$jsonInfosFour = json_encode($resultInfosFour);
		echo $jsonInfosFour;
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour la modification d'un stock
	public function ajaxupdtstockAction()
	{
		$request = $this->getRequest();
		//Si la requete est de type post
		if ($request->isPost()) {
			//Si il y'a des données
			if ($request->getPost()) {
				//Nouvel adapter
				$dbAdapter = Zend_Registry::get('dbAdapter');
				//On récupère les infos du formulaire (i = au numéro du formulaire)
				$i = $request->getPost('i');
				$idstock = $request->getPost('stockid');
				$reffour = $request->getPost('reffour');
				$qte = $request->getPost('qte');
				$pu = $request->getPost('pu');
				$stockdatecom = $request->getPost('stockdatecom_'.$i);
				$stockdateent = $request->getPost('stockdateent_'.$i);
				$stockdatesor= $request->getPost('stockdatesor_'.$i);
				$interid = $request->getPost('interidnew_'.$i);
				//On update la table stock
				$query  = "UPDATE stock SET interid = $interid, stockdatecom = '$stockdatecom', stockentree = '$stockdateent', stocksortie = '$stockdatesor', stockqte = $qte, stockpu = $pu, stockreffour = '$reffour' WHERE stockid = $idstock";
				//Exécution de la requete
				$result = $dbAdapter->query($query);
				$this->_helper->layout->disableLayout();
			}
		}
	}
	//Fonction pour supprimer un stock
	public function ajaxdelstockAction()
	{
		//On récupère l'ID de la matière première
		$id = $this->_getParam('id');
		//Découpage du parametre pour ne garder que l'ID
		$tab_param = explode('_', $id);
		$db = Zend_Registry::get('dbAdapter');
		//Update de la table stock (stocksuppr a true)
		$query = "UPDATE stock SET stocksuppr = true WHERE stockid = $tab_param[1]";
		//Exécution de la requete
		$result = $db->query($query);
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour l'ajout d'un stock
	public function ajaxaddstockAction()
	{
		$request = $this->getRequest();
		//Si la requête est de type post
		if ($request->isPost()) {
			//Si il y'a des données
			if ($request->getPost()) {
				//adapter bdd
				$dbAdapter = Zend_Registry::get('dbAdapter');
				//On récupère les infos du formulaire (i = au numéro du formulaire)
				$idMat = $request->getPost('matid');
				$reffour = $request->getPost('reffour');
				$qte = $request->getPost('qte');
				$pu = $request->getPost('pu');
				$stockdatecom = $request->getPost('stockdatecom');
				$stockdateent = $request->getPost('stockdateent');
				$stockdatesor= $request->getPost('stockdatesor');
				$interid = $request->getPost('interidnew_0');
				//Requete d'insertion d'un stock
				$query  = "INSERT INTO stock (interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES ($interid, $idMat, '$stockdatecom', '$stockdateent', '$stockdatesor', $qte, '$reffour', $pu, false)";
				//Exécution de la requete
				$result = $dbAdapter->query($query);
				$this->_helper->layout->disableLayout();
			}
		}
	}
	//Fonction pour l'ajout d'une matière
	public function ajaxaddmatAction()
	{
		$request = $this->getRequest();
		$dbAdapter = Zend_Registry::get('dbAdapter');
		//On récupère la désignation de la matière première
		$lbl = $this->_getParam('lbl');
		$uniteid = $this->_getParam('uniteid');
		//Requete d'insertion d'une matière
		$insMat  = "INSERT INTO matiere (uniteid, matlib, matsuppr) VALUES ($uniteid, '$lbl', false)";
		//Exécution de la requete
		$resInsMat = $dbAdapter->query($insMat);
		$slctLastId = "SELECT last_value FROM matiere_matid_seq";
		$idmatarray = $dbAdapter->fetchAll($slctLastId);
		$idmat = $idmatarray[0]['last_value'];
		//Insertion des alertes de base (0 pour le mini et le max)
		$insAlertMin = "INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (1, $idmat, 0)";
		$resultInsAlertMin = $dbAdapter->query($insAlertMin);
		$insAlertMax = "INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (2, $idmat, 0)";
		$resultInsAlertMax = $dbAdapter->query($insAlertMax);
		$this->_helper->layout->disableLayout();
	}
	//Suppression d'une matière première
	public function ajaxdelmatAction()
	{
		//On récupère l'ID de la matière première
		$id = $this->_getParam('id');
		//Découpage du paramètre pour ne garder que l'ID
		$tab_param = explode('_', $id);
		$db = Zend_Registry::get('dbAdapter');
		//Update de la table matiere (matsuppr a true)
		$query = "UPDATE matiere SET matsuppr = true WHERE matid = $tab_param[1]";
		//Exécution de la requête
		$result = $db->query($query);
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour la modification d'une matière première
	public function ajaxupdtmatAction()
	{
		//Nouvel adapter
		$dbAdapter = Zend_Registry::get('dbAdapter');
		//On récupère les datas
		$matid = $this->_getParam('matid');
		$matlbl = $this->_getParam('lbl');
		$uniteid = $this->_getParam('uniteid');
		$query  = "UPDATE matiere SET uniteid = $uniteid, matlib = '$matlbl' WHERE matid = $matid";
		//Exécution de la requête
		$result = $dbAdapter->query($query);
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour la commande d'une matière première
	public function ajaxcmdmatAction()
	{
		$dbAdapter = Zend_Registry::get('dbAdapter');
		//On récupère les data
		$matid = $this->_getParam('matid');
		$frnslct = $this->_getParam('idFrn');
		$pu = $this->_getParam('pu');
		$qte = $this->_getParam('qte');
		$reffour = $this->_getParam('reffourmat');
		$date = date('d/m/Y');
		$query  = "INSERT INTO cmdmat (matid, fourid, datecmdmat, qtecmdmat, pucmdmat, reffourcmdmat) VALUES ($matid, $frnslct, '$date', $qte, $pu, '$reffour')";
		//Exécution de la requête
		$result = $dbAdapter->query($query);
		$slctLastId = "SELECT last_value FROM cmdmat_cmdmatid_seq";
		$idcmdarray = $dbAdapter->fetchAll($slctLastId);
		$idcmd = $idcmdarray[0]['last_value'];
		$slctInfoFour = "SELECT fourrais, fournom, fouradresse, fourcp, fourville FROM fournisseur WHERE fourid = $frnslct";
		$fourarray = $dbAdapter->fetchAll($slctInfoFour);
		$fourrais = $fourarray[0]['fourrais'];
		$fournom = $fourarray[0]['fournom'];
		$fouradresse = $fourarray[0]['fouradresse'];
		$fourcp = $fourarray[0]['fourcp'];
		$fourville = $fourarray[0]['fourville'];
		$slctInfoMat = "SELECT matlib, unitelbl FROM matunite WHERE matid = $matid";
		$matarray = $dbAdapter->fetchAll($slctInfoMat);
		$matlbl = $matarray[0]['matlib'];
		$unitelbl = utf8_decode($matarray[0]['unitelbl']);
		$total = $qte * $pu;
		//nom du pdf a generer
		$filename = BASE_PATH . "/public/pdf/commandeMat/cmdmat_" . $idcmd . ".pdf";
		//load du template pdf
		$pdf = Zend_Pdf::load(BASE_PATH . '/public/pdf/templates/commandeMat.pdf');
		$page = $pdf->pages[0];
		$page->setFont(Zend_Pdf_Font::fontWithName(Zend_Pdf_Font::FONT_TIMES), 12);
		//Affichage du texte
		$page->drawText($idcmd, 275, 678);
		$page->drawText($date, 450, 655);
		$page->drawText($fourrais.' '.$fournom, 110, 590);
		$page->drawText($fouradresse, 110, 575);
		$page->drawText($fourcp.' '.$fourville, 110, 560);
		$page->drawText($matlbl, 90, 510);
		$page->drawText($reffour, 290, 510);
		$page->drawText($qte.' '.$unitelbl, 355, 510);
		$page->drawText($pu, 460, 510);
		$page->drawText($total, 455, 380);
		//sauvegarde du pdf
		$pdf->save($filename);
		//Affichage de l'url pour charger le pdf en jquery
		echo '../../../pdf/commandeMat/cmdmat_'.$idcmd.'.pdf';
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour récupéérer les unités de mesures
	public function ajaxlistuniteAction()
	{
		$db = Zend_Registry::get('dbAdapter');
		//Requête de slection (liste des unités de mesure)
		$query = "SELECT * FROM unite ORDER BY unitelbl ASC";
		$result = $db->query($query);
		foreach($result as $k => $value)
		{
			$resultUnite[] = $value;
		}
		//Entete JSON
		header('Content-Type: application/json;');
		//Encodage du résultat en JSON
		$json = json_encode($resultUnite);
		echo $json;
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour ajouter une unité de mesure
	public function ajaxadduniteAction()
	{
		$request = $this->getRequest();
		$dbAdapter = Zend_Registry::get('dbAdapter');
		//Ligne à insérer
		$rowsUnite = array(
			'unitelbl' => $this->_getParam('lbl'),
			'unitesuppr' => "f"
		);
		//Déclaration de la table dans une variable
		$table = 'unite';
		//Insertion dans la table "unite"
		$dbAdapter->insert($table, $rowsUnite);
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour modifier une unité de mesure
	public function ajaxupdtuniteAction()
	{
		$request = $this->getRequest();
		$dbAdapter = Zend_Registry::get('dbAdapter');
		$id = $this->_getParam('idUnite');
		//Ligne à update
		$rowsUnite = array(
				'unitelbl' => $this->_getParam('lbl'),
		);
		//Déclaration de la table dans une variable
		$table = 'unite';
		//Clause where
		$where = "uniteid = $id";
		//Update dans la table "unite"
		$dbAdapter->update($table, $rowsUnite, $where);
		$this->_helper->layout->disableLayout();
	}
	//Fonction pour supprimer une unité de mesure
	public function ajaxdeluniteAction()
	{
		$request = $this->getRequest();
		$dbAdapter = Zend_Registry::get('dbAdapter');
		$id = $this->_getParam('idUnite');
		//Ligne à update
		$rowsUnite = array(
				'unitesuppr' => "t",
		);
		//Déclaration de la table dans une variable
		$table = 'unite';
		//Clause where
		$where = "uniteid = $id";
		//Update dans la table "unite"
		$dbAdapter->update($table, $rowsUnite, $where);
		$this->_helper->layout->disableLayout();
	}
}

