

<?php
/**
 * ProdController.php
 * @description handles production actions
 */
class prodController extends Zend_Controller_Action {

    public function init() {
        parent::init();
        $this->view->headLink()->appendStylesheet($this->view->baseUrl() . "/css/style_prod_c.css");
    }

    public function createNewNom($content, $filename, $errorCorrectionLevel = 'H', $matrixPointSize = 7) {
        QRcode::png($content, $filename, $errorCorrectionLevel, $matrixPointSize, 2);
    }
/**
 * function GeneratePdf
 * generate pdf from a template
 * @author sylvain
 * @param $nomName, $filename, $nomDesc, $nomChain, $nomTime, $nomId
 * @return boolean
 *
 */
public function generatePdf($nomName, $filename, $nomDesc, $nomChain, $nomTime, $nomId) {

    $date = date('d/m/Y');

        //nom du pdf a generer
    $filename = BASE_PATH . "/public/pdf/nomenclatures/nomenclature_" . $nomName . ".pdf";

        //recuperation des données du formulaire
    $request = $this->getRequest()->getPost();

    $truc = $request["updateNameFlag"];

    if ($truc) {
        unlink($filename);
    }

        //load du template pdf
    $pdf = Zend_Pdf::load(BASE_PATH . '/public/pdf/templates/nomenclature.pdf');
    $page = $pdf->pages[0];

    $page->setFont(Zend_Pdf_Font::fontWithName(Zend_Pdf_Font::FONT_TIMES), 14);
        //Affichage du QR code généré
    $imagePath = BASE_PATH . '/public/images/nomenclatures/' . $nomName . '.png';
    $image = Zend_Pdf_Image::imageWithPath($imagePath);
        // Draw image
    $page->drawImage($image, 450, 470, 550, 570);

        //Affichage de la date
    $page->drawText($date, 60, 543);
        //Affichage du nom de la nomenclature
    $page->drawText($nomName, 105, 515);
    $page->drawText($nomDesc, 105, 485);

    $x = 70;
    $y = 380;

        // boucle pour afficher le contenu du formulaire (produit, réf, qte)
    for ($i = 0; $request["prod_{$i}"]; $i++) {

        $page->drawText($request["prod_{$i}"], $x, $y);
        $page->drawText($request["matName_{$i}"], $x+=190, $y);
        $page->drawText($request["qte_{$i}"], $x+=190, $y);
        $page->drawText($request["prix_{$i}"], $x+=190, $y);
        $y -= 20;
        $x = 70;
    }
        //sauvegarde du pdf
    $pdf->save($filename);

    if ($request["updateNameFlag"]) {
        if ($this->updateNom($request)) {
            return true;
        }
    } else {
        if ($this->saveNom($request)) {
            return true;
        }
    }
}

 /**
 * function GeneratePdfBAT
 * generate BAT pdf from template
 * @author sylvain
 * @param $id, $comid, $chaineid, $listeNom
 * @return boolean
 *
 */
 public function generatePdfBat($id, $comid, $chaineid, $listeNom) {
    $date = date('d/m/Y');
        //nom du pdf a generer
    $filename = BASE_PATH . "/public/pdf/BAT/bat_" . $id . ".pdf";

        //load du template pdf
    $pdf = Zend_Pdf::load(BASE_PATH . '/public/pdf/templates/template_BAT.pdf');
    $page = $pdf->pages[0];

    $page->setFont(Zend_Pdf_Font::fontWithName(Zend_Pdf_Font::FONT_TIMES), 14);
        //Affichage du QR code généré
    $imagePath = BASE_PATH . '/public/images/BAT/bat_' . $id . '.png';
    $image = Zend_Pdf_Image::imageWithPath($imagePath);
        // Draw image
    $page->drawImage($image, 450, 570, 550, 670);

        //Affichage de la date
    $page->drawText($date, 100, 757);

        //Affichage de l'ID du BAT
    $page->drawText($id, 255, 757);

        //Affichage du n° de la commande
    $page->drawText($comid, 170, 695);

        //Affichage de la chaine utilisée
    $page->drawText($chaineid, 155, 580);

    $y = 530;

        // boucle pour afficher les nomenclatures utilisées
    $liste = explode(", ", $listeNom);
    for ($i = 0; $i <= sizeof($liste) - 1; $i++) {
        $page->drawText($liste[$i], 205, $y);
        $y -= 20;
    }
        //sauvegarde du pdf
    $pdf->save($filename);
}

public function generatePdfBaj($id, $comid) {
    $date = date('d/m/Y');
        //nom du pdf a generer
    $filename = BASE_PATH . "/public/pdf/BAJ/baj_" . $id . ".pdf";

        //load du template pdf
    $pdf = Zend_Pdf::load(BASE_PATH . '/public/pdf/templates/template_BAJ.pdf');
    $page = $pdf->pages[0];

    $page->setFont(Zend_Pdf_Font::fontWithName(Zend_Pdf_Font::FONT_TIMES), 14);

        //Affichage de la date
    $page->drawText($date, 100, 335);

        //Affichage de l'ID du BAJ
    $page->drawText($id, 275, 335);

        //Affichage du n° de la commande
    $page->drawText($comid, 160, 274);

        //sauvegarde du pdf
    $pdf->save($filename);
}

 /**
 * function check_stock
 * check if stock doesn't go negative
 * @author sylvain
 * @param array $tab_nom
 * @return array
 *
 */
 public function check_stock($tab_nom) {
    $dbAdapter = Zend_Registry::get('dbAdapter');
    for ($i = 0; $i < count($tab_nom); $i++) {
        $infos = $dbAdapter->fetchAll("SELECT * FROM nommat where nomid={$tab_nom[$i]}");
        for ($j = 0; $j < count($infos); $j++) {
            //vérifie si le stock ne devient pas négatif
            $result = $dbAdapter->query("SELECT stockqte from stock where matid = {$infos[$j]['matid']}");
            $resultqtemat = 0;
            foreach($result as $k => $value)
            {
            	//Addition des qte en stock
                $resultqtemat = $resultqtemat + $value['stockqte'];
            }
            $qte = $resultqtemat - $infos[$j]['matqte'];
            if ($qte < 0)
            {
            	$resultMat = $dbAdapter->fetchAll("SELECT * FROM matiere WHERE matid = {$infos[$j]['matid']}");
                return $resultMat[0]['matlib'];
            }
        }
    }
    return false;
}
public function convtime($date) {
	list($year, $month, $day, ) = explode('-', $date);
	$timestamps = mktime(0, 0, 0, $month, $day, $year);
	return $timestamps;
}
public function convdte($time) {
	$dte = date('Y-m-d', $time);
	return $dte;
}
/**
 * function processbataction
 * validate and process bat: sql queries...
 * @author sylvain
 * @return bolean
 *
 */
public function processbatAction() {
    include_once '../library/phpqrcode/qrlib.php';
    $request = $this->getRequest();
    if ($request->isPost()) {
        if ($request->getPost()) {
        	//Paramètres postés
            $idCommande = $request->getPost('id');
            $nom = $request->getPost('nom');
            $chaine = $request->getPost('chaine');
            //nomenclature(s) utilisées
            $tab_nom = explode(',', $nom);

            //vérifie si le stock ne devient pas négatif
            $stockNeg = $this->check_stock($tab_nom);
            //Si négatif
            if ($stockNeg != false)
            {
            	echo ("Attention : <br>
            		La matière : <b>{$stockNeg}</b> est en quantité insuffisante<br>
            		Cette commande ne peut pas être mise en production<br>
                    Veuillez en alerter le gestionnaire des stocks
            		");
                $this->_helper->layout->disableLayout();
                return false;
            }
            //Sinon gestion du stock en mode FIFO
            else
            {
            	$dbAdapter = Zend_Registry::get('dbAdapter');
            	//Boucle sur les nomenclatures
            	for ($i = 0; $i < count($tab_nom); $i++)
	            {
	            	$nomid = $tab_nom[$i];
	            	//Selection des infos matieres de la nomenclature
	                $infos = $dbAdapter->fetchAll("SELECT * FROM nommat where nomid=$nomid");
	                //Boucle sur les infos nommat
	                for ($j = 0; $j < count($infos); $j++)
	                {
	                	$qteMat = $infos[$j]['matqte'];
	                	//Variable pour sortir de la boucle do
	                	$sortie = false;
	                	//Boucle pour soustraire la qte de matière
	                	do {
	                		$matid = $infos[$j]['matid'];
	                		//Selection des stocks
		                	$slcStock = $dbAdapter->fetchAll("SELECT * FROM stock WHERE matid=$matid AND stocksuppr=false AND (stocksortie='' OR stocksortie IS NULL)");
		                	//Tableau timestamp des stocks
		                	$timestamp = array();
		                	//Boucle sur les stocks
		                	for($k = 0; $k < count($slcStock); $k++)
		                	{
		                		//Date d'entrée de stock
		                		$dateStock = $slcStock[$k]['stockentree'];
		                		//Vérifie qu'elle n'est pas vide
		                		if($slcStock[$k]['stockentree'] != "")
		                		{
		                			//Conversion en timestamp
		                			$timestamp[] = $this->convtime($dateStock);
		                		}
		                	}
		                	//On récupère le plus petit timestamp
		                	$firstDate = min($timestamp);
		                	//reconverti en date format US
		                	$dteStock = $this->convdte($firstDate);
		                	//Date du jour pour la date de sortie
		                	$dateUs = date('Y-m-d');
		                	//Selection du stock correspondant à la matière et à la date d'entrée
		                	$firstStock = $dbAdapter->fetchAll("SELECT * FROM stock WHERE matid =$matid AND stockentree='$dteStock'");
		                	//$slctQteStock = $dbAdapter->fetchAll("SELECT * FROM stock WHERE stockid={$idFirstStock[0]['stockid']}");
		                	$idStock = $firstStock[0]['stockid'];
		                	$qteStoc = $firstStock[0]['stockqte'];
		                	//Différence entre la qte demandée et celle présente dans le stock
		                	$dif = $qteStoc - $qteMat;
		                	//Si la différence est supérieur à 0
		                	if($dif > 0)
		                	{
		                		//On sauvegarde la quantité de matière retirée
		                		$saveQte = $qteMat;
		                		//Update la qte de stock
		                		$updtQteStock = $dbAdapter->fetchAll("UPDATE stock set stockqte = $dif WHERE stockid = $idStock");
		                		//Sortie de la boucle
		                		$sortie = true;
		                	}
		                	//Sinon si la différence donne 0
		                	elseif($dif == 0)
		                	{
		                		//Sauvegarde de la qte retirée
		                		$saveQte = $qteStoc;
		                		//Update date de sortie et qte stock à 0
		                		$updtQteStock = $dbAdapter->fetchAll("UPDATE stock set stockqte=0, stocksortie = '$dateUs' WHERE stockid = $idStock");
		                		//Sortie de la boucle
		                		$sortie = true;
		                	}
		                	//Sinon si la différence est inférieur à zéro
		                	elseif($dif < 0)
		                	{
		                		//Sauvegarde la qte du stock
		                		$saveQte = $qteStoc;
		                		//Update date de sortie et qte stock
		                		$updtQteStock = $dbAdapter->fetchAll("UPDATE stock set stockqte=0, stocksortie = '$dateUs' WHERE stockid = $idStock");
		                		//On soustrait la qte pour le nouveau besoin en matière
		                		$qteMat = $qteMat - $qteStoc;
		                		//On sort pas de la boucle
		                		$sortie = false;
		                	}
		                	//SAUVEGARDE ETAT STOCK (HISTORIQUE)
		                	//date du jour format US
		                	$dateNow = date('Y-m-d');
		                	//Id du stock
		                	$idStock = $firstStock[0]['stockid'];
		                	//Insertion de l'historique
		                	$dbAdapter->query('INSERT INTO histostock (stockid, comid, histodte, histoqte, histosuppr) VALUES (?, ?, ?, ?, ?)', array($idStock, $idCommande, "$dateNow", $saveQte, 'false'));
		                	//Selection de l'Id correspondant
		                	$lastIdHisto = $dbAdapter->fetchAll('SELECT last_value FROM histostock_histoid_seq');
	                	} while ($sortie == false);
	                } //Fin boucle nommat
	            } //Fin boucle nomenclature

	            //remplit la table production
	            $dbAdapter->query('INSERT into production (comid, chaineid, chainedatedeb, prodsuppr) VALUES (?, ?, ? , ?)', array($idCommande, $chaine, 'NOW()', 'false'));
	            $lastId = $dbAdapter->fetchAll('SELECT last_value FROM production_prodid_seq');
	            $lastIdProd = $lastId[0]['last_value'];

	            //creating QR code
	            $filename = BASE_PATH . '/public/images/BAT/bat_' . $lastIdProd . '.png';
	            $short_name = $lastIdProd . '.png';
	            $this->createNewNom($lastIdProd, $filename);

	            //remplit table BAT
	            $dbAdapter->query('INSERT into bonatirer (prodid, bonatirerdte, bonatirersuppr) VALUES (?, ?, ?)', array($lastIdProd, '$NOW()', 'false'));
	            $lastId = $dbAdapter->fetchAll('SELECT last_value FROM bonatirer_bonatirerid_seq');
	            $lastIdBat = $lastId[0]['last_value'];

	            //met à jour le champ bonatirerid dans la table production
	            $data = array(
	            		'bonatirerid' => $lastIdBat,
	            		'qrcode' => $short_name
	            );
	            $where[] = "prodid = {$lastIdProd}";
	            $result = $dbAdapter->update("production", $data, $where);

	            //indique la chaine choisie comme non disponible
	            $o_chainMapper = new Application_Model_ChaineMapper();
	            $o_chaine = $o_chainMapper->find((int) $chaine);
	            $o_chaine->setChainedispo(0);
	            $o_chainMapper->save($o_chaine);

	            //genère le PDF
	            $this->generatePdfBat($lastIdProd, $idCommande, $chaine, $nom);
	            $this->_helper->layout->disableLayout();
	            echo 'lastId='.$lastIdBat;
            } //Fin si stock négatif
        }
    }
}
/**
 * function processbajaction
 * validate and process baj: sql queries...
 * @author sylvain
 * @return bolean
 *
 */
public function processbajAction() {
    include_once '../library/phpqrcode/qrlib.php';

    $request = $this->getRequest();
    if ($request->isPost()) {
        if ($request->getPost()) {
            $id = $request->getPost('id');
                //remplit la table production
            $dbAdapter = Zend_Registry::get('dbAdapter');
            $lastId = $dbAdapter->fetchAll('SELECT prodid FROM production WHERE comid = ' . $id . 'AND chainedatefin IS NULL LIMIT 1');
            $lastIdProd = $lastId[0]['prodid'];


                //remplit table BAJ
            $dbAdapter->query('INSERT into bonsajeter (prodid, bonajeterdte, bonajetersuppr) VALUES (?, ?, ?)', array($lastIdProd, '$NOW()', 'false'));
            $lastId = $dbAdapter->fetchAll('SELECT last_value FROM bonsajeter_bonajeterid_seq');
            $lastIdBaj = $lastId[0]['last_value'];
                //met à jour le champ bonatirerid dans la table production
            $data = array(
                'bonajeterid' => $lastIdBaj,
                'qrcode' => $short_name
                );
            $where[] = "prodid = {$lastIdBaj}";
            $result = $dbAdapter->update("production", $data, $where);

                //récupère la liste des nomenclatures utilisées
            $sql = "SELECT nomid FROM calcultempsnom WHERE comid=?";
            $res = $dbAdapter->fetchAll($sql, $id);
            foreach ($res as $a)
                $nomlist[] = $a['nomid'];

                //récupère l'état actuel de la commande
            $etatComm = $dbAdapter->fetchAll('SELECT cometat FROM commande WHERE comid = ' . $id);
            $etatComm = $etatComm[0]['cometat'];

            //ON REINTEGRE LA MATIERE DE LA COMMANDE

            $historique = $dbAdapter->fetchAll("SELECT * FROM histostock WHERE comid = $id AND histosuppr = false");
            for ($i = 0; $i < count($historique); $i++)
            {
            		$histoid = $historique[$i]['histoid'];
            		$qte = $historique[$i]['histoqte'];
            		$stockid = $historique[$i]['stockid'];
            		$stock = $dbAdapter->fetchAll("SELECT * FROM stock WHERE stockid = $stockid");
            		$qteStock = $stock[0]['stockqte'];
            		$qteNew = $qteStock + $qte;
            		$dbAdapter->query("UPDATE stock SET stockqte = $qteNew WHERE stockid = $stockid");
            		$dbAdapter->query("UPDATE histostock SET histosuppr=true WHERE histoid = $histoid");
            }

            //FIN REINTEGRATION MATIERE


                //genère le PDF
            $this->generatePdfBaj($lastIdBaj, $id);
            $this->_helper->layout->disableLayout();
        	echo 'lastId='.$lastIdBaj;
        }
    }
}

public function indexAction() {
    $o_nomenclatureMapper = new Application_Model_NomenclatureMapper();
    $where = array('nomsuppr = false');
    $tab_noms = $o_nomenclatureMapper->getWhere($where);
    $this->view->nomenclatures = $tab_noms;
    $this->view->titre = "Sélectionnez une nomenclature";
}

public function createnomAction() {
    $o_alerteMapper = new Application_Model_NomenclatureMapper();
    $where = array('nomsuppr = false');
    $tab_noms = $o_alerteMapper->getWhere($where);
    $this->view->nomenclatures = $tab_noms;
    $reqMat = "SELECT matiere.matlib, stock.stockpu, matiere.matid
    FROM matiere , stock WHERE matiere.matid = stock.matid";
    $dbAdapter = Zend_Registry::get('dbAdapter');
    $res = $dbAdapter->fetchAll($reqMat);
    $this->view->matieres = $res;
    $o_chainMapper = new Application_Model_ChaineMapper();
    $where = array('chainesuppr = false');
    $chains = $o_chainMapper->getWhere($where);
    $this->view->nbreChaine = count($chains);
    $this->view->titre = "Créer une nomenclature";
}

public function createnomrowAction() {
    $reqMat = "SELECT matiere.matlib, stock.stockpu, matiere.matid
    FROM matiere , stock WHERE matiere.matid = stock.matid";
    $dbAdapter = Zend_Registry::get('dbAdapter');
    $res = $dbAdapter->fetchAll($reqMat);
    $this->view->matieres = $res;
    $lastRowId = $this->getRequest()->getPost('lastrowid');
    $this->view->lastRowId = $lastRowId;
    $this->_helper->layout->disableLayout();
}

public function createexistingnomAction() {
    $this->_helper->layout->disableLayout();
    $flag = 1;
    $nomId = $this->getRequest()->getParam('idnom');
    $o_nomatMapper = new Application_Model_NommatMapper();
    $where = array('nomid = ' . $nomId);
    $tab_noms = $o_nomatMapper->getWhere($where);
    $reqMat = "SELECT matiere.matlib, stock.stockpu, matiere.matid
    FROM matiere , stock WHERE matiere.matid = stock.matid";
    $dbAdapter = Zend_Registry::get('dbAdapter');
    $res = $dbAdapter->fetchAll($reqMat);
    $this->view->matieres = $res;
    $i = count($tab_noms);

    $this->view->nomenclatures = $tab_noms;
}

public function loadnomAction() {
    $this->render('ajax');
    $this->_helper->layout->disableLayout();
    $o_nomenclatureMapper = new Application_Model_NomenclatureMapper();
    $o_nomatMapper = new Application_Model_NommatMapper();
    $o_matiereMapper = new Application_Model_MatiereMapper();

    $id = $this->getRequest()->getPost('idnom');
    $where = array('nomid = ' . $id);
    $tab_noms = $o_nomatMapper->getWhere($where);
    echo $tab_noms[0]->getMatid();
}

public function createqrAction() {

    include_once '../library/phpqrcode/qrlib.php';
    $request = $this->getRequest();

    $this->_helper->layout->disableLayout();
    if ($request->isPost()) {
        if ($request->getPost()) {
            $getPost = $request->getPost();
            $request = $this->getRequest()->getPost();
            $nomName = $request["nomName"];
            $nomDesc = $getPost[nomDesc];
            $nomTime = $getPost[nomTemps];
            $nomId = $request["nomId"];
            $nomChain = $getPost[nomChain];
            $nomProduct = array();
                //$request->getPost('nomProduct');
            $filename = BASE_PATH . '/public/images/nomenclatures/' . $nomName . '.png';
            if ($this->createNewNom($nomName, $filename)) {
                return true;
            }
                //génération du pdf à partir du formulaire
            if ($this->generatePdf($nomName, $filename, $nomDesc, $nomChain, $nomTime, $nomId)) {
                $this->getRequest()->getPost();
            }
        }
    }
}

public function updateNom($request) {
    $dbAdapter = Zend_Registry::get('dbAdapter');

    $nomId = $request["nomId"];
    $nomName = $request["nomName"];
    $nomDesc = $request["nomDesc"];
    $nomTemps = $request["nomTemps"];
    $nomChain = $request["nomChain"];
    $nomPrix = $request["nomPrix"];
    $nomQr = $nomName . '.png';
    $i = 0;
    $parameter = new Application_Model_DbTable_Nomenclature();
    $select = $parameter->select();
    $select->where('nomid = ' . $nomId . '');
    $rowParameter = $parameter->fetchRow($select);
    $rowParameter->nomtemps = trim($nomTemps);
    $rowParameter->nomlib = $nomName;
    $rowParameter->nomnbchaine = $nomChain;
    $rowParameter->nomqr = $nomQr;
    $rowParameter->nomprix = $nomPrix;
    $rowParameter->nomdes = $nomDesc;
    $rowParameter->save();
    $idNomReturn = $nomId;
    for ($i = 0; $request["prod_{$i}"]; $i++) {
        if ($request["prod_{$i}"] != '') {
            $matId = $request["prod_{$i}"];
            $matQte = $request["qte_{$i}"];
            $totalRow = intval($request['totalNommat']);
            if ($totalRow < $i+1) {
                $requete = "INSERT into nommat (nomid, matid, matqte) VALUES ($idNomReturn, $matId, $matQte)";
                $variable = $dbAdapter->query($requete);
            } else {
                $parameter = new Application_Model_DbTable_Nommat();
                $select = $parameter->select();
                $select->where('nomid = ' . $nomId . ' AND matid = ' . $matId . '');
                $rowParameter = $parameter->fetchRow($select);
                $rowParameter->matqte = $matQte;
                $rowParameter->save();
            }
        } else {
            break;
        }
    }
    return true;
}

public function editnomAction() {
    $this->view->titre = "Editer une nomenclature";
    if ($this->getRequest()->getParam('idnom')) {
        $o_nomenclatureMapper = new Application_Model_NomenclatureMapper();
        $o_nomatMapper = new Application_Model_NommatMapper();
        $o_matiereMapper = new Application_Model_MatiereMapper();
        $id = $this->getRequest()->getParam('idnom');
        $where = array('nomid = ' . $id);
        $tab_nomat = $o_nomatMapper->getWhere($where);
        $tab_noms = $o_nomenclatureMapper->getWhere($where);
        $this->view->nomenclature = $tab_noms;
        $this->view->nommat = $tab_nomat;
    }
}

public function chainprocessAction() {
    $this->view->headScript()->appendFile($this->view->baseUrl() . '/js/prod.js');
    $this->view->titre = "Gestion des chaines de production";
}
/**
 * function chainprocesslistactionn
 * gets production chain list
 * @author sylvain
 *
 */
public function chainprocesslistAction() {
    $o_chainMapper = new Application_Model_ChaineMapper();

    // $reqChain = "SELECT * FROM chaine WHERE chainesuppr = 'false'";
    // $dbAdapter = Zend_Registry::get('dbAdapter');
    // $res = $dbAdapter->fetchAll($reqMat);
    //$where = array('chainesuppr = ' .'false');
    //$tab_chaine = $o_chainMapper->getWhere($where);
    $tab_chaine = $o_chainMapper->fetchAll();
    $this->view->chaine = $tab_chaine;
    $this->_helper->layout->disableLayout();
}

/**
* Function ajaxaddchain
* Ajout d'une chaine de production
*/
public function ajaxaddchainAction()
{
    $request = $this->getRequest();
    $dbAdapter = Zend_Registry::get('dbAdapter');
        //On récupère la désignation de la chaine
    $lbl = $this->_getParam('lbl');

        //Exécution de la requete
    $dbAdapter->query("INSERT into chaine (chainelib, chainedispo, chainesuppr) values ('meuh', 'true', 'false')");
    $this->_helper->layout->disableLayout();
}

/**
* Function ajaxdelchain
* Suppression d'une chaine de production
*/
public function ajaxdelchainAction()
{
    $request = $this->getRequest();
   
    //     //On récupère la désignation de la chaine
    // $id = $this->_getParam('id');

    //     //Exécution de la requete
    // $o_chainMapper = new Application_Model_ChaineMapper();
    // $o_chaine = $o_chainMapper->find((int) $id);
    // $o_chaine->setChainesuppr(1);
    // $o_chainMapper->save($o_chaine);
    // $this->_helper->layout->disableLayout();
     $request = $this->getRequest();
    $dbAdapter = Zend_Registry::get('dbAdapter');
        //On récupère la désignation de la chaine
    $id = $this->_getParam('id');

        //Exécution de la requete
    $dbAdapter->query("DELETE FROM chaine WHERE chaineid=".$id);
    $this->_helper->layout->disableLayout();
}
/**
 * function disablechainactionn
 * disables a production chain
 * @author sylvain
 *
 */
public function disablechainAction() {
    $request = $this->getRequest();
    $o_chainMapper = new Application_Model_ChaineMapper();
    $result = $request->getPost();
    $o_chaine = $o_chainMapper->find((int) $result['id']);
        //modify chain status
    switch ($result['modif']) {
        case "stop":
        $o_chaine->setChainedispo(0);
        $o_chainMapper->save($o_chaine);
        break;
        case "start":
        $o_chaine->setChainedispo(1);
        $o_chainMapper->save($o_chaine);
        break;
        case "suppress":
        $o_chaine->setChainesuppr(1);
        $o_chainMapper->save($o_chaine);
        break;
        case "restore":
        $o_chaine->setChainesuppr(0);
        $o_chainMapper->save($o_chaine);
        break;
    }
    $this->_helper->layout->disableLayout();
}
/**
 * function getbatinfosaction
 * get bat infos: nomenclatures, time to produce,...
 * @author sylvain
 *
 */
public function getbatinfosAction() {
    $dbAdapter = Zend_Registry::get('dbAdapter');
    $request = $this->getRequest();
    $result = $request->getPost();
        //get client name
    $sql = 'SELECT clinom FROM detailcommande WHERE comid = ?';
    $res = $dbAdapter->fetchAll($sql, $result['id']);
    $push[] = $res[0]['clinom'];

        //recup nomenclatures utilisees ...
    $sql = "SELECT nomid FROM calcultempsnom WHERE comid=?";
    $res = $dbAdapter->fetchAll($sql, $result['id']);
    foreach ($res as $a)
        $nomlist[] = $a['nomid'];
    $push[] = implode(", ", $nomlist);
        //... et le temps estimé de prod
    $sql = "SELECT SUM (nomtemps::integer * comqte::integer) as \"total_time\" FROM calcultempsnom WHERE comid=? GROUP BY comid";
    $res = $dbAdapter->fetchAll($sql, $result['id']);
    $push[] = $res[0]['total_time'];
    echo json_encode($push);
    $this->_helper->layout->disableLayout();
}
/**
 * function getbajinfosaction
 * get baj infos: client name
 * @author sylvain
 *
 */
public function getbajinfosAction() {
    $dbAdapter = Zend_Registry::get('dbAdapter');
    $request = $this->getRequest();
    $result = $request->getPost();
        //get client name
    $sql = 'SELECT clinom FROM detailcommande WHERE comid = ?';
    $res = $dbAdapter->fetchAll($sql, $result['id']);
    $push[] = $res[0]['clinom'];

    echo json_encode($push);
    $this->_helper->layout->disableLayout();
}

public function alertAction() {
    $o_AlerteMapper = new Application_Model_AlerteMapper();
    $alertes = $o_AlerteMapper->fetchAll();
    $this->view->titre = "Gestion des seuils d'alertes";
    $o_matiereMapper = new Application_Model_MatiereMapper();
    $listMatiere = $o_matiereMapper->fetchAll();
    $this->view->matieres = $listMatiere;
    if ($this->getRequest()->isPost()) {
        if ($this->getRequest()->getPost('matlib')) {
            $request = $this->getRequest();
            $matLib = $request->getPost('matlib');
            //$req = 'SELECT alerte.alertelbl, matiere.matlib, alertemat.qtemat, alertemat.matid FROM alerte,
           // alertemat, matiere WHERE alerte.alertesuppr = FALSE AND matiere.matid = ' . $matLib . ' AND alerte.alerteid = alertemat.alerteid AND alertemat.matid = matiere.matid';
            $req = 'SELECT alerte.alertelbl, matiere.matlib, matiere.uniteid, unite.unitelbl, unite.uniteid, alertemat.qtemat, alertemat.matid FROM alerte,
            alertemat, matiere, unite WHERE alerte.alertesuppr = FALSE AND matiere.matid = ' . $matLib . ' AND matiere.uniteid = unite.uniteid AND alerte.alerteid = alertemat.alerteid AND alertemat.matid = matiere.matid';
            $db = Zend_Registry::get('dbAdapter');
            $result = $db->query($req);
            foreach ($result as $k => $value) {
                $resultMat[] = $value;
            }
            $this->render('ajax');
            $this->_helper->layout->disableLayout();
            $return = '{"liste": { "unite": "'.$resultMat[0]["unitelbl"].'", "mat": "' . $resultMat[0]["matlib"] . '","matId": "' . $resultMat[0]["matid"] . '","seuils": {
                "' . $resultMat[1]["alertelbl"] . '": ' . $resultMat[1]["qtemat"] . ',
                "' . $resultMat[0]["alertelbl"] . '": ' . $resultMat[0]["qtemat"] . '}}}';
                echo $return;
            }
            if ($this->getRequest()->getPost('matId')) {
                $dbAdapter = Zend_Registry::get('dbAdapter');
                $this->render('ajax');
                $this->_helper->layout->disableLayout();
                $id = $this->getRequest()->getParam('matId');
                $min = $this->getRequest()->getParam('min');
                $max = $this->getRequest()->getParam('max');
                $requeteMin = "UPDATE alertemat SET qtemat = " . $min . " WHERE matid = $id AND alerteid = 1";
                $requeteMax = "UPDATE alertemat SET qtemat = " . $max . " WHERE matid = $id AND alerteid = 2";
                $dbAdapter->query($requeteMin);
                $dbAdapter->query($requeteMax);
                return true;
            }
        }
    }

    public function batAction() {
        $dbAdapter = Zend_Registry::get('dbAdapter');

        $this->view->headLink()->appendStylesheet($this->view->baseUrl() . "/css/style_bat.css");
        $this->view->titre = "Gestion des BAT";
        //get command list
        $o_commMapper = new Application_Model_CommandeMapper();
        $tab_comm = $o_commMapper->fetchAll();
        $this->view->commande = $tab_comm;

        //get free chains list
        $o_chainMapper = new Application_Model_ChaineMapper();
        $o_Select = $o_chainMapper->getDbTable()->select();
        $o_Select->where('chainesuppr = false');
        $o_Select->where('chainedispo = true');
        $tab_chaine = $o_chainMapper->fetchAll($o_Select);
        $this->view->chaine = $tab_chaine;
    }

    public function bajAction() {
        $this->view->titre = "Gestion des BAJ";
        //get command list
        $o_commMapper = new Application_Model_CommandeMapper();
        $tab_comm = $o_commMapper->fetchAll();
        $this->view->commande = $tab_comm;
        $dbAdapter = Zend_Registry::get('dbAdapter');
    }

    private function saveNom($request) {
        $dbAdapter = Zend_Registry::get('dbAdapter');

        $nomName = $request["nomName"];
        $nomDesc = $request["nomDesc"];
        $nomTemps = $request["nomTemps"];
        $nomChain = $request["nomChain"];
        $nomQr = $nomName . '.png';
        $i = 0;
        $nomChain = 2;
        $truc = "INSERT into nomenclature (nomlib, nomdate, nomtemps, nomnbchaine, nomqr, nomprix, nomdes, nomsuppr) VALUES ('$nomName', '2012-03-12', $nomTemps, $nomChain, '$nomQr', 500, '$nomDesc', false) RETURNING nomid";
        $idNomEnCours = $dbAdapter->query($truc);
        $idNomReturn = $dbAdapter->lastInsertId('nomenclature', 'nomid');
        for (;; $i++) {
            if ($request["prod_{$i}"] != '') {
                $matId = $request["prod_{$i}"];
                $matQte = $request["qte_{$i}"];

                $requete = "INSERT into nommat (nomid, matid, matqte) VALUES ($idNomReturn, $matId, $matQte)";
                $variable = $dbAdapter->query($requete);
                print_r($variable);
            } else {
                break;
            }
        }
        return true;
    }

    public function deletenomAction() {
        $dbAdapter = Zend_Registry::get('dbAdapter');
        $this->render('ajax');
        $this->_helper->layout->disableLayout();
        if ($this->getRequest()->getParam('nomId')) {
            $id = $this->getRequest()->getParam('nomId');
            $requete = "UPDATE nomenclature SET nomsuppr = true WHERE nomid = $id";
            $dbAdapter->query($requete);
            return true;
        }
    }

}//END prodController CLASS

