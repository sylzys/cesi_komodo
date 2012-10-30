﻿INSERT INTO profil (
PROFID,
PROFLIB,
PROFSUPPR
)
VALUES (
nextval('profil_profid_seq') , 'administrateur', false
), (
nextval('profil_profid_seq') , 'commercial', false
), (
nextval('profil_profid_seq') , 'stock', false
), (
nextval('profil_profid_seq') , 'production', false
);

INSERT INTO controller (
CTRLID,
CTRLNOM,
CTRLLIB,
CTRLSUPPR
)
VALUES (
nextval('controller_ctrlid_seq'), 'admin', 'Pannel administration', false
), (
nextval('controller_ctrlid_seq'), 'clients', 'Annuaire des clients', false
), (
nextval('controller_ctrlid_seq'), 'home', 'Accueil', false
), (
nextval('controller_ctrlid_seq'), 'prod', 'Gestion de la production', false
), (
nextval('controller_ctrlid_seq'), 'stock', 'Gestion des stocks', false
);

INSERT INTO action (
ACTIONID,
CTRLID,
ACTIONNOM,
ACTIONLIB,
ACTIONSUPPR
)
VALUES (
nextval('action_actionid_seq'), 1, 'index','Index du site', false
), (
nextval('action_actionid_seq'), 1, 'client','Accès aux infos clients', false
), (
nextval('action_actionid_seq'), 1, 'clientlist','Consultation de la liste des clients', false
), (
nextval('action_actionid_seq'), 1, 'clientchangeacces','Changement de l''accès du client', false
), (
nextval('action_actionid_seq'), 1, 'user','', false
), (
nextval('action_actionid_seq'), 1, 'userlist','Consultation de la liste des utilisateurs', false
), (
nextval('action_actionid_seq'), 1, 'userchangeprofil','Changement des profils utilisateurs', false
), (
nextval('action_actionid_seq'), 2, 'index','Index du site', false
), (
nextval('action_actionid_seq'), 2, 'getclientinfos','Consultation des informations des clients', false
), (
nextval('action_actionid_seq'), 3, 'index','Index du site', false
), (
nextval('action_actionid_seq'), 4, 'index','Index du site', false
), (
nextval('action_actionid_seq'), 4, 'processbat','Bon à tirer', false
), (
nextval('action_actionid_seq'), 4, 'processbaj','Bon à jeter', false
), (
nextval('action_actionid_seq'), 4, 'createnom','Création de nomenclature', false
), (
nextval('action_actionid_seq'), 4, 'createnomrow','Ajout de ligne de matière dans les nomenclatures', false
), (
nextval('action_actionid_seq'), 4, 'createexistingnom','Création d enomenclature à partir d''une existante', false
), (
nextval('action_actionid_seq'), 4, 'loadnom','Consultation d''une nomenclature', false
), (
nextval('action_actionid_seq'), 4, 'createqr','Générer un qr code', false
), (
nextval('action_actionid_seq'), 4, 'editnom','Modifier une nomenclature', false
), (
nextval('action_actionid_seq'), 4, 'chainprocess','Accès aux chaines de production', false
), (
nextval('action_actionid_seq'), 4, 'chainprocesslist','Consultation des chaines de production', false
), (
nextval('action_actionid_seq'), 4, 'disablechain','Changement de l''état d''une chaine de production', false
), (
nextval('action_actionid_seq'), 4, 'getbatinfos','Consultation d''un bon à tirer', false
), (
nextval('action_actionid_seq'), 4, 'getbajinfos','Consultation d''un bon à jeter', false
), (
nextval('action_actionid_seq'), 4, 'alert','Accès aux alertes stock', false
), (
nextval('action_actionid_seq'), 4, 'bat','Accès au bons à tirer', false
), (
nextval('action_actionid_seq'), 4, 'baj','Accès aux bons à jeter', false
), (
nextval('action_actionid_seq'), 4, 'saveNom','Sauvegarder une nomenclature', false
), (
nextval('action_actionid_seq'), 5, 'index','Index du site', false
), (
nextval('action_actionid_seq'), 5, 'gestionstock','Accès à la gestion des stocks', false
), (
nextval('action_actionid_seq'), 5, 'historiquecommande','Accès à l''historique des commandes', false
), (
nextval('action_actionid_seq'), 5, 'rechercheproduit','', true
), (
nextval('action_actionid_seq'), 5, 'gestionfournisseur','Accès à la gestion des fournisseurs', false
), (
nextval('action_actionid_seq'), 5, 'recherchenomenclature','Consultation des nomenclatures dans le module stock', false
), (
nextval('action_actionid_seq'), 3, 'getnotifs','Consultation des notifications', false
), (
nextval('action_actionid_seq'), 4, 'deletenom','Suppression d''un numenclature', false
), (
nextval('action_actionid_seq'), 1, 'profil','Accès aux profils', false
), (
nextval('action_actionid_seq'), 1, 'profillist','Consultation des profils', false
), (
nextval('action_actionid_seq'), 1, 'profilchangeright','Changement de droits des profils', false
), (
nextval('action_actionid_seq'), 5, 'ajaxeditmat','Consultation des stocks d''une matière première', false
), (
nextval('action_actionid_seq'), 1, 'profiladd','Ajout de profil', false
), (
nextval('action_actionid_seq'), 5, 'ajaxlistfour','Consultation de la liste des fournisseurs', false
), (
nextval('action_actionid_seq'), 5, 'ajaxinfosfour','Consultation détail fournisseur', false
), (
nextval('action_actionid_seq'), 5, 'ajaxupdtstock','Modification des stocks', false
), (
nextval('action_actionid_seq'), 5, 'ajaxdelstock','Suppression des stocks', false
), (
nextval('action_actionid_seq'), 5, 'ajaxaddstock','Ajout d''un stock', false
), (
nextval('action_actionid_seq'), 5, 'ajaxaddmat','Ajout d''une matière première', false
), (
nextval('action_actionid_seq'), 5, 'ajaxdelmat','Suppression d''une matière première', false
), (
nextval('action_actionid_seq'), 5, 'ajaxupdtmat','Modification d''une matière première', false
), (
nextval('action_actionid_seq'), 5, 'ajaxcmdmat','Passer une comamnde', false
), (
nextval('action_actionid_seq'), 5, 'ajaxlistnom','Rechercher les nomanclatures dans le module stock', false
), (
nextval('action_actionid_seq'), 1, 'useradd','Ajouter un utilisateur', false
), (
nextval('action_actionid_seq'), 1, 'useredit','Modifier un utilisateur', false
), (
nextval('action_actionid_seq'), 1, 'userdelete','Supprimer un utilisateur', false
), (
nextval('action_actionid_seq'), 5, 'addfourn','Ajouter un fournisseur', false
), (
nextval('action_actionid_seq'), 5, 'deletefourn','Supprimer un fournisseur', false
), (
nextval('action_actionid_seq'), 5, 'interfrn','Afficher l''interlocuteur d''un fournisseur', false
), (
nextval('action_actionid_seq'), 5, 'infosupdtfrn','Formulaire de modification d''un fournisseur', false
), (
nextval('action_actionid_seq'), 5, 'editfourn','Modifier un fournisseur', false
), (
nextval('action_actionid_seq'), 5, 'ajaxlistunite','Afficher la liste des unités de mesure', false
), (
nextval('action_actionid_seq'), 5, 'ajaxaddunite','Ajouter une unité de mesure', false
), (
nextval('action_actionid_seq'), 5, 'ajaxupdtunite','Modifier une unité de mesure', false
), (
nextval('action_actionid_seq'), 5, 'ajaxdelunite','Supprimer une unité de mesure', false
);

INSERT INTO utilisateur (
UTIID ,
UTINOM ,
UTIPRENOM ,
UTILOGIN ,
UTIMDP ,
UTIMAIL ,
UTITEL ,
UTISUPPR
)
VALUES 
(nextval('utilisateur_utiid_seq'), 'Administrateur', NULL , 'admin', MD5( 'admin' ) , 'admin@plastprod.fr', '0654637643', false), 
(nextval('utilisateur_utiid_seq'), 'Commercial 1', NULL , 'commercial1', MD5( 'commercial1' ) , 'commercial1@plastprod.fr', '0689764365', false),
(nextval('utilisateur_utiid_seq'), 'Commercial 2', NULL , 'commercial2', MD5( 'commercial2' ) , 'commercial2@plastprod.fr', '0689344365', false),
(nextval('utilisateur_utiid_seq'), 'Commercial 3', NULL , 'commercial3', MD5( 'commercial3' ) , 'commercial3@plastprod.fr', '0689762315', false),
(nextval('utilisateur_utiid_seq'), 'Stock', NULL , 'stock', MD5( 'stock' ) , 'stock@plastprod.fr', '066543643', false),
(nextval('utilisateur_utiid_seq'), 'Production', NULL , 'prod', MD5( 'prod' ) , 'prod@plastprod.fr', '0676543465', false);

INSERT INTO fonction (
FONCID,
FONCLIB,
FONCSUPPR
)
VALUES (
nextval('fonction_foncid_seq'), 'ADMINISTRATEUR', false
), (
nextval('fonction_foncid_seq'), 'COMMERCIAL', false
), (
nextval('fonction_foncid_seq'), 'RESPONSABLE PRODUCTION', false
), (
nextval('fonction_foncid_seq'), 'RESPONSABLE MAGASIN', false
);

INSERT INTO foncuti (
UTIID ,
FONCID
)
VALUES (
'1', '1'
), (
'2', '2'
), (
'3', '2'
), (
'4', '2'
), (
'5', '3'
), (
'6', '4'
);

INSERT INTO utiProf (
UTIID,
PROFID
)
VALUES (
'1', '1'
), (
'2', '2'
) , (
'3', '2'
) , (
'4', '2'
), (
'5', '3'
), (
'6', '4'
);

INSERT INTO client (
CLIID ,
UTIID ,
CLIRAIS ,
CLINOM ,
CLIADRESSE ,
CLICP ,
CLIVILLE ,
CLIETAT ,
CLILOGIN ,
CLIMDP ,
CLIACCES ,
CLISUPPR
)
VALUES (
nextval('client_cliid_seq'), '2', 'SAS', 'AutoConstruct', '11 rue Harry Cover', '76000', 'Rouen', '100', 'client1', MD5( 'client1' ) , '1', false
), (
nextval('client_cliid_seq'), '2', 'SA', 'AutoRD', '25 rue Henry', '75000', 'Paris', '100', 'client2', MD5( 'client2' ) , '1', false
), (
nextval('client_cliid_seq'), '3', 'SARL', 'Renot', '54 route de Paris', '76320', 'Saint Pierre', '100', 'client3', MD5( 'client3' ) , '1', false
), (
nextval('client_cliid_seq'), '3', 'SAS', 'AutoSAS', '198 rue Jean Bon', '27100', 'Val de Reuil', '100', 'client4', MD5( 'client4' ) , '1', false
), (
nextval('client_cliid_seq'), '4', 'SAS', 'ProdSAS', '32 route de Dieppe', '76430', 'Montville', '100', 'client5', MD5( 'client5' ), '1', false
);

INSERT INTO fournisseur (
FOURID ,
FOURRAIS ,
FOURNOM ,
FOURADRESSE ,
FOURCP ,
FOURVILLE ,
FOURSUPPR
)
VALUES (
nextval('fournisseur_fourid_seq'), 'SAS', 'FourMat', '32 route de Paris', '76000', 'Rouen', false
), (
nextval('fournisseur_fourid_seq'), 'SA', 'FourStock', '56 route du Havre', '76400', 'Le Houlme', false
), (
nextval('fournisseur_fourid_seq'), 'SAS', 'MatiereFour', '182 rue Jeanne d''Arc', '76000', 'Rouen', false
), (
nextval('fournisseur_fourid_seq'), 'SA', 'FournitureMat', '123 route de Dieppe', '76430', 'Montville', false
), (
nextval('fournisseur_fourid_seq'), 'SAS', 'FournisseurSAS', '76 rue du Renard', '76000', 'Rouen', false
);

INSERT INTO interlocuteur (
INTERID ,
FOURID ,
UTIID,
CLIID ,
INTERNOM ,
INTERPRENOM ,
INTERMAIL ,
INTERTEL ,
INTERSUPPR
)
VALUES (
nextval('interlocuteur_interid_seq'), '1', NULL, NULL , 'Lefevre', 'Harry', 'harrylevefre@gmail.com', '0665434321', false
), (
nextval('interlocuteur_interid_seq'), '2', NULL, NULL , 'Fournier', 'Jean', 'jeanfournier@gmail.com', '0634231265', false
), (
nextval('interlocuteur_interid_seq'), NULL, 2 , '1', 'Jacques', 'Jean', 'jeanjacques@gmail.com', '0654678798', false
), (
nextval('interlocuteur_interid_seq'), NULL, 3 , '3', 'Marie', 'Anne', 'annemarie@gmail.com', '0676894523', false
), (
nextval('interlocuteur_interid_seq'), NULL, 2 , '5', 'Claude', 'Jean', 'jeanclaude@gmail.com', '0623438756', false
), (
nextval('interlocuteur_interid_seq'), '3', NULL , NULL, 'Paul', 'Dupres', 'pauldupres@gmail.com', '0685454548', false
), (
nextval('interlocuteur_interid_seq'), '4', NULL , NULL, 'Henry', 'Charles', 'henrycharles@gmail.com', '0652121417', false
), (
nextval('interlocuteur_interid_seq'), '5', NULL , NULL, 'Maxime', 'Dupond', 'maximedupond@gmail.com', '0687454578', false
),(
nextval('interlocuteur_interid_seq'), NULL, NULL , '2', 'Antoine', 'Dumet', 'antoinedumet@gmail.com', '0676894523', false
),(
nextval('interlocuteur_interid_seq'), NULL, NULL , '4', 'Yves', 'paul', 'yvespaul@gmail.com', '0676894523', false
);

INSERT INTO chaine (
CHAINEID ,
CHAINELIB ,
CHAINESUPPR ,
CHAINEDISPO
)
VALUES (
nextval('chaine_chaineid_seq'), 'chaine 1', false, '1'
), (
nextval('chaine_chaineid_seq'), 'chaine 2', false, '1'
), (
nextval('chaine_chaineid_seq'), 'chaine 3', false, '1'
);
INSERT INTO unite (
UNITEID ,
UNITELBL ,
UNITESUPPR
)
VALUES (
nextval('unite_uniteid_seq'), 'Kg' , false
),(
nextval('unite_uniteid_seq'), 'Gramme' , false
),(
nextval('unite_uniteid_seq'), 'Mètre' , false
),(
nextval('unite_uniteid_seq'), 'M3' , false
),(
nextval('unite_uniteid_seq'), 'Litre' , false
),(
nextval('unite_uniteid_seq'), 'Unité' , false
),(
nextval('unite_uniteid_seq'), 'Carton' , false
),(
nextval('unite_uniteid_seq'), 'x 1000' , false
);
INSERT INTO matiere (
MATID ,
UNITEID,
MATLIB ,
MATSUPPR
)
VALUES (
nextval('matiere_matid_seq'), 6, 'vis 2 x 2', false
), (
nextval('matiere_matid_seq'), 6, 'rondelle 5 x 10', false
), (
nextval('matiere_matid_seq'), 6, 'puce electronique', false
), (
nextval('matiere_matid_seq'), 6, 'condensateur', false
), (
nextval('matiere_matid_seq'), 1, 'plastique', false
), (
nextval('matiere_matid_seq'), 5, 'peinture rouge', false
), (
nextval('matiere_matid_seq'), 6, 'tournevis plat', false
);


INSERT INTO nomenclature (
NOMID ,
NOMLIB ,
NOMDATE ,
NOMTEMPS ,
NOMNBCHAINE ,
NOMQR ,
NOMPRIX ,
NOMDES ,
NOMSUPPR
)
VALUES (
nextval('nomenclature_nomid_seq'), 'Comodo 1', '2012-03-14', '35', '1', '1.png', '200', 'Comodo numero 1', false
), (
nextval('nomenclature_nomid_seq'), 'Comodo 2', '2012-03-02', '180', '2', '2.png', '1540', 'Comodo numero 2', false
), (
nextval('nomenclature_nomid_seq'), 'Comodo 3', '2012-03-03', '110', '1', '3.png', '230', 'Comodo numero 3', false
), (
nextval('nomenclature_nomid_seq'), 'Comodo 4', '2012-03-28', '240', '3', '4.png', '2790', 'Comodo numero 4', false
), (
nextval('nomenclature_nomid_seq'), 'Comodo 5', '2012-03-16', '210', '1', '5.png', '320', 'Comodo numero 5', false
);

INSERT INTO nommat (
NOMID ,
MATID ,
MATQTE
)
VALUES (
'1', '2', '2'
), (
'1', '4', '4'
), (
'2', '4', '3'
), (
'3', '5', '1'
), (
'4', '5', '6'
), (
'2', '6', '5'
), (
'5', '3', '4'
), (
'5', '7', '1'
), (
'2', '5', '2'
), (
'4', '3', '3'
);


INSERT INTO stock (
STOCKID ,
INTERID ,
MATID ,
STOCKDATECOM ,
STOCKENTREE ,
STOCKSORTIE ,
STOCKQTE ,
STOCKREFFOUR,
STOCKPU,
STOCKSUPPR
)
VALUES (
nextval('stock_stockid_seq'), '2', '1', '2012-03-14', '2012-03-30', NULL , '500', '2456789', '4', false
), (
nextval('stock_stockid_seq'), '1', '4', '2012-03-12', '2012-03-16', NULL , '200', '4456677', '50', false
), (
nextval('stock_stockid_seq'), '1', '2', '2012-03-04', '2012-03-30', NULL , '1000', '5756790', '14', false
), (
nextval('stock_stockid_seq'), '2', '3', '2012-03-13', '2012-03-19', NULL , '100', '978676576', '150', false
), (
nextval('stock_stockid_seq'), '2', '5', '2012-03-11', '2012-03-31', NULL , '20', '45654654', '235', false
), (
nextval('stock_stockid_seq'), '1', '6', '2012-03-11', '2012-03-31', NULL , '20', '786564', '25', false
), (
nextval('stock_stockid_seq'), '1', '7', '2012-03-11', '2012-03-31', NULL , '20', '787877', '5', false
), (
nextval('stock_stockid_seq'), '2', '7', '2012-03-11', '2012-03-31', NULL , '20', '985244', '2', false
);

INSERT INTO devis (
DEVID ,
DEVDATE ,
DEVPRIX ,
DEVSUPPR
)
VALUES (
nextval('devis_devid_seq'), '2012-03-08', '1900', false
), (
nextval('devis_devid_seq'), '2012-03-13', '20050', false
), (
nextval('devis_devid_seq'), '2012-03-20', '7654', false
), (
nextval('devis_devid_seq'), '2012-03-04', '72630', false
), (
nextval('devis_devid_seq'), '2012-03-13', '2309', false
);

INSERT INTO devnom (
NOMID ,
DEVID ,
DEVNOMQTE
)
VALUES (
'3', '1', '3'
), (
'1', '1', '1'
), (
'4', '2', '2'
), (
'5', '2', '5'
), (
'1', '3', '1'
), (
'5', '4', '2'
), (
'1', '4', '2'
);

INSERT INTO agenda (
AGEID ,
UTIID ,
AGEDEB ,
AGEFIN ,
AGELIEU ,
AGESUPPR
)
VALUES (
nextval('agenda_ageid_seq'), '2', '2012-03-28 16:00:00', '2012-03-28 17:00:00', 'Salle de reunion 1', false
), (
nextval('agenda_ageid_seq'), '5', '2012-03-31 08:30:00', '2012-03-14 10:00:00', 'Salle de reunion 2', false
);

INSERT INTO alerte (
ALERTEID, 
ALERTELBL, 
ALERTESUPPR
) 
VALUES(
nextval('alerte_alerteid_seq'), 'mini', false
), (
nextval('alerte_alerteid_seq'), 'max', false
);

INSERT INTO alertemat (
ALERTEID ,
MATID,
QTEMAT
)
VALUES(
'1', '1', 10
), (
'2', '1', 20
), (
'1', '2', 20
), (
'2', '2', 40
), (
'1', '3', 5
), (
'2', '3', 100
), (
'1', '4', 1
), (
'2', '4', 50000
), (
'1', '5', 10
), (
'2', '5', 2000
), (
'1', '6', 1
), (
'2', '6', 50
), (
'1', '7', 10
), (
'2', '7', 100000
);

INSERT INTO commande (
COMID ,
COMDATE ,
COMDATEPREV ,
COMETAT ,
COMPRODDEB ,
COMPRODFIN ,
COMPRIX ,
COMSUPPR
)
VALUES (
nextval('commande_comid_seq'), '2012-03-14', '2012-03-16', '80', '2012-03-14', NULL , '2309', false
), (
nextval('commande_comid_seq'), '2012-03-15', '2012-03-31', '10', '2012-03-15', NULL , '70987', false
), (
nextval('commande_comid_seq'), '2012-03-14', '2012-04-02', '100', '2012-03-14', '2012-04-01', '26543', false
), (
nextval('commande_comid_seq'), '2012-04-13', '2012-04-14', '100', '2012-04-13', '2012-04-14', '346543', false
);

INSERT INTO comnom (
COMID ,
NOMID ,
COMQTE
)
VALUES (
'3', '2', '3'
), (
'1', '4', '34'
), (
'2', '5', '2'
), (
'1', '5', '32'
), (
'3', '1', '37'
), (
'2', '1', '87'
), (
'4', '5', '875'
), (
'2', '4', '6'
), (
'1', '3', '876'
), (
'1', '1', '8754'
);

INSERT INTO suivdossier (
SUIVDOSID ,
COMID ,
INTERID ,
DEVID ,
UTIID ,
SUIVDOSDATE ,
SUIVDOSCOM ,
SUIVIDOSSUPPR
)
VALUES (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , NULL , '2012-03-14', 'Commentaire au sujet de la commande numero 1', false
), (
nextval('suivdossier_suivdosid_seq'), NULL , '1', '3', NULL , '2012-03-15', 'Commentaire suite au devis numero 3', false
), (
nextval('suivdossier_suivdosid_seq'), '2', '4' , NULL , '1', '2012-03-15', 'Commentaire de l''utilisateur numero 1 pour la commande numero 2', false
), (
nextval('suivdossier_suivdosid_seq'), NULL , '2' , '3', '5', '2012-03-16', 'Commentaire de l''utilisateur numero 5 pour le devis numero 3', false
), (
nextval('suivdossier_suivdosid_seq'), '3', '5', NULL , '5', '2012-03-23', 'Commande', false
), (
nextval('suivdossier_suivdosid_seq'), '4', '4', NULL , '5', '2012-03-23', 'Commande', false
);

INSERT INTO production (
PRODID ,
COMID ,
BONAJETERID ,
BONATIRERID ,
CHAINEID ,
CHAINEDATEDEB ,
CHAINEDATEFIN ,
QRCODE ,
PRODSUPPR
)
VALUES (
nextval('production_prodid_seq'), '1', NULL , NULL , '1' , '2012-03-15 08:00:00', '2012-03-15 15:00:00', '1_1.png', false
), (
nextval('production_prodid_seq'), '2', NULL , NULL , '2', '2012-03-14 16:00:00', '2012-03-15 10:00:00', '2_2.png', false
), (
nextval('production_prodid_seq'), '1', NULL , NULL , '1' , '2012-03-14 16:00:00', '2012-03-15 10:00:00', '3_1.png', false
), (
nextval('production_prodid_seq'), '3', NULL , NULL ,'3' , '2012-03-14 16:00:00', '2012-03-15 10:00:00', '4_3.png', false
);

INSERT INTO bonsajeter (
BONAJETERID ,
PRODID ,
BONAJETERDTE ,
BONAJETERSUPPR
)
VALUES (
nextval('bonsajeter_bonajeterid_seq'), '3' , '2012-03-15', false
);

INSERT INTO cmdmat (
CMDMATID ,
MATID ,
FOURID ,
DATECMDMAT,
QTECMDMAT,
PUCMDMAT,
REFFOURCMDMAT
)
VALUES (
nextval('cmdmat_cmdmatid_seq'), 1 , 1, '30/04/2012', 20, 150, '5454hg'
);

INSERT INTO bonatirer (
BONATIRERID ,
PRODID ,
BONATIRERDTE ,
BONATIRERSUPPR
)
VALUES (
nextval('bonatirer_bonatirerid_seq'), '1' , '2012-03-14', false
), (
nextval('bonatirer_bonatirerid_seq'), '2' , '2012-03-16', false
), (
nextval('bonatirer_bonatirerid_seq'), '3' , '2012-03-20', false
), (
nextval('bonatirer_bonatirerid_seq'), '4' , '2012-03-20', false
);

UPDATE production SET BONATIRERID = '1' WHERE PRODID = 1;
UPDATE production SET BONATIRERID = '2' WHERE PRODID = 2;
UPDATE production SET BONATIRERID = '3', BONAJETERID = '1' WHERE PRODID = 3;
UPDATE production SET BONATIRERID = '4' WHERE PRODID = 4;

INSERT INTO profAction (
ACTIONID,
PROFID
)
VALUES (
'1', '1'
), (
'2', '1'
), (
'3', '1'
), (
'4', '1'
), (
'5', '1'
), (
'6', '1'
), (
'7', '1'
), (
'8', '1'
), (
'9', '1'
), (
'10', '1'
), (
'11', '1'
), (
'12', '1'
), (
'13', '1'
), (
'14', '1'
), (
'15', '1'
), (
'16', '1'
), (
'17', '1'
), (
'18', '1'
), (
'19', '1'
), (
'20', '1'
), (
'21', '1'
), (
'22', '1'
), (
'23', '1'
), (
'24', '1'
), (
'25', '1'
), (
'26', '1'
), (
'27', '1'
), (
'28', '1'
), (
'29', '1'
), (
'30', '1'
), (
'31', '1'
), (
'32', '1'
), (
'33', '1'
), (
'34', '1'
), (
'36', '1'
), (
'37', '1'
), (
'38', '1'
), (
'39', '1'
), (
'40', '1'
), (
'41', '1'
), (
'42', '1'
), (
'43', '1'
), (
'44', '1'
), (
'45', '1'
), (
'46', '1'
), (
'47', '1'
), (
'48', '1'
), (
'49', '1'
), (
'50', '1'
), (
'51', '1'
), (
'52', '1'
), (
'53', '1'
), (
'54', '1'
), (
'55', '1'
), (
'56', '1'
), (
'57', '1'
), (
'58', '1'
), (
'59', '1'
), (
'60', '1'
), (
'61', '1'
), (
'62', '1'
), (
'63', '1'
), (
'8', '2'
), (
'9', '2'
), (
'10', '2'
), (
'10', '3'
), (
'29', '3'
), (
'30', '3'
), (
'31', '3'
), (
'32', '3'
), (
'33', '3'
), (
'34', '3'
), (
'10', '4'
), (
'11', '4'
), (
'12', '4'
), (
'13', '4'
), (
'14', '4'
), (
'15', '4'
), (
'16', '4'
), (
'17', '4'
), (
'18', '4'
), (
'19', '4'
), (
'20', '4'
), (
'21', '4'
), (
'22', '4'
), (
'23', '4'
), (
'24', '4'
), (
'25', '4'
), (
'26', '4'
), (
'27', '4'
), (
'28', '4'
), (
'35', '1'
), (
'35', '2'
), (
'35', '3'
), (
'35', '4'
),(
'36', '4'
),(
'40', '3'
), (
'42', '3'
),(
'43', '3'
),(
'44', '3'
),(
'45', '3'
),(
'46', '3'
),(
'47', '3'
), (
'48', '3'
), (
'49', '3'
), (
'50', '3'
), (
'51', '3'
), (
'55', '3'
), (
'56', '3'
), (
'57', '3'
), (
'58', '3'
), (
'59', '3'
), (
'60', '3'
), (
'61', '3'
), (
'62', '3'
), (
'63', '3'
);