<?php

// declaration du mapper
$oUtilisateurMapper = new Application_Model_UtilisateurMapper();

// avoir une liste complete
$tabUtilisateurs = $oUtilisateurMapper->fetchAll();

// avoir une liste complete avec clause where
$where = array('UITNOM LIKE "%Comm%"', 'UTIMAIL LIKE "%@plastprod.fr"');
$tabUtilisateurs = $oUtilisateurMapper->getWhere($where);

// trouver un element par son id
$id = 1;
$oUtilisateur = $oUtilisateurMapper->find($id);

// modifier un element
$id = 1;
$newName = 'robert';
$oUtilisateur = $oUtilisateurMapper->find($id);
$oUtilisateur->setUTINOM($newName);
$oUtilisateurMapper->save($oUtilisateur);

// creer un nouvel element
$oNewUtilisateur = new Application_Model_Utilisateur();
$oNewUtilisateur->setUTILOGIN('bob');
$oUtilisateurMapper->save($oNewUtilisateur);

// supprimer un element revient a faire une modif sur le champs [TABLE_PREFIX]SUPPR = 1



?>