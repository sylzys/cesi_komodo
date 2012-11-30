INSERT INTO profil (
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
nextval('action_actionid_seq'), 1, 'user','Module utilisateur', false
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
nextval('action_actionid_seq'), 5, 'rechercheproduit','Rechercher une nomenclature', true
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
UTIDTELOG,
UTISUPPR
)
VALUES 
(nextval('utilisateur_utiid_seq'), 'Administrateur', 'admin' , 'admin', MD5( 'admin' ) , 'admin@plastprod.fr', '0654637643','2012-12-05', false), 
(nextval('utilisateur_utiid_seq'), 'Commercial 1', 'com' , 'com1', MD5( 'com1' ) , 'commercial1@plastprod.fr', '0689764365','2012-11-25', false),
(nextval('utilisateur_utiid_seq'), 'Commercial 2', 'com' , 'com2', MD5( 'com2' ) , 'commercial2@plastprod.fr', '0689344365','2012-10-15', false),
(nextval('utilisateur_utiid_seq'), 'Commercial 3', 'com' , 'com3', MD5( 'com3' ) , 'commercial3@plastprod.fr', '0689762315','2012-12-08', false),
(nextval('utilisateur_utiid_seq'), 'Stock', 'stock' , 'stock', MD5( 'stock' ) , 'stock@plastprod.fr', '066543643','2012-12-12', false),
(nextval('utilisateur_utiid_seq'), 'Production', 'prod' , 'prod', MD5( 'prod' ) , 'prod@plastprod.fr', '0676543465','2012-11-24', false);

/*
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
);*/

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
UTI_UTIID ,
CLIRAIS ,
CLINOM ,
CLIADRESSE ,
CLICP ,
CLIVILLE ,
CLIPAYS,
CLITEL,
CLIFAX,
CLIMAIL,
CLISIRET,
CLICA,
CLIDG,
CLIETAT ,
CLILOGIN ,
CLIMDP ,
CLIACCES ,
CLISUPPR,
CLIDTELOG,
CLIDTEADD,
CLISIREN,
CLIUNIQID
)
VALUES (
nextval('client_cliid_seq'), '1', '1', 'SAS', 'AutoConstruct', '11 rue Harry Cover', '76000', 'Rouen', 'FRANCE', '0235458745', '0235458748', 'autoconstruct@outlook.com', '65z7841fr25', 55000, 'Paul Arthur', '100', 'client1', MD5( 'client1' ) , '1', false,'2012-03-14','2012-03-14', '784FDFFD54', 'gfdgdfgfd56454'
), (
nextval('client_cliid_seq'), '1', '1', 'SA', 'AutoRD', '25 rue Henry', '75000', 'Paris', 'FRANCE', '0154789875', '0154789878', 'autord@outlook.com', '78r45125gt', 65478, 'Yves Dupont', '100', 'client2', MD5( 'client2' ) , '1', false,'2012-11-21','2012-03-14','SDSDQ5465465','gfhgfh5464564fghgfh'
), (
nextval('client_cliid_seq'), '1', '1', 'SARL', 'Renot', '54 route de Paris', '76320', 'Saint Pierre', 'FRANCE', '0154789856', '0154789858', 'renot@outlook.com', '54dfd2155415', 78545, 'Dohn Carlos', '100', 'client3', MD5( 'client3' ) , '1', false,'2012-02-08','2012-03-14', 'JHJK564564','fdgfffffff2222222'
), (
nextval('client_cliid_seq'), '1', '1', 'SAS', 'AutoSAS', '198 rue Jean Bon', '27100', 'Val de Reuil', 'FRANCE', '0358745689', '0358745688', 'autosas@outlook.com', '4578dfre2145', 10005465, 'Jean Allezy', '100', 'client4', MD5( 'client4' ) , '1', false,'2012-09-17','2012-03-14','DZS54654','gdfdfdsddd5545674'
), (
nextval('client_cliid_seq'), '1', '1', 'SAS', 'ProdSAS', '32 route de Dieppe', '76430', 'Montville', 'FRANCE', '0254879852', '0254879858', 'prodsas@outlook.com', '75845fg5454', 6554654, 'Pierre Antoine', '100', 'client5', MD5( 'client5' ), '1', false,'2012-05-16','2012-03-14','ESDRZE56465','dgfdfgfdgfdgfdgfd'
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
INTERSUPPR,
INTERUNIQID
)
VALUES (
nextval('interlocuteur_interid_seq'), '1', NULL, NULL , 'Lefevre', 'Harry', 'harrylevefre@gmail.com', '0665434321', false, 'dsfdsfdsfdsf5456416'
), (
nextval('interlocuteur_interid_seq'), '2', NULL, NULL , 'Fournier', 'Jean', 'jeanfournier@gmail.com', '0634231265', false, 'dsfdsfdsfdsfdsfdsfds'
), (
nextval('interlocuteur_interid_seq'), NULL, 2 , '1', 'Jacques', 'Jean', 'jeanjacques@gmail.com', '0654678798', false, 'sddddddfggghghh525885'
), (
nextval('interlocuteur_interid_seq'), NULL, 3 , '3', 'Marie', 'Anne', 'annemarie@gmail.com', '0676894523', false, 'fdgfdgfgggggfddhghfgh'
), (
nextval('interlocuteur_interid_seq'), NULL, 2 , '5', 'Claude', 'Jean', 'jeanclaude@gmail.com', '0623438756', false, 'dfgfdgdfghfgdhgfhgfh'
), (
nextval('interlocuteur_interid_seq'), '3', NULL , NULL, 'Paul', 'Dupres', 'pauldupres@gmail.com', '0685454548', false, 'gdfdfgfdgghgfhgfhjhgj'
), (
nextval('interlocuteur_interid_seq'), '4', NULL , NULL, 'Henry', 'Charles', 'henrycharles@gmail.com', '0652121417', false, 'fghfghssddsfgdhgfjhgjhh'
), (
nextval('interlocuteur_interid_seq'), '5', NULL , NULL, 'Maxime', 'Dupond', 'maximedupond@gmail.com', '0687454578', false, 'gsqsqsdghkjjkkk'
),(
nextval('interlocuteur_interid_seq'), NULL, NULL , '2', 'Antoine', 'Dumet', 'antoinedumet@gmail.com', '0676894523', false, 'kjkkkkkkkkkkkk'
),(
nextval('interlocuteur_interid_seq'), NULL, NULL , '4', 'Yves', 'paul', 'yvespaul@gmail.com', '0676894523', false, 'gfsqsdgfhjklkjtr45456'
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

INSERT INTO agenda (
AGEID ,
UTIID ,
AGEINTITULE,
AGEDEB ,
AGEFIN ,
AGELIEU ,
AGEDESC,
AGEETAT,
AGESUPPR,
AGEUNIQID
)
VALUES (
nextval('agenda_ageid_seq'), '1', 'Rendez-vous avec Mr Dupont', '2012-03-28 16:00:00', '2012-03-28 17:00:00', 'Salle de reunion 1', 'Description du rendez-vous 1', false, false, '21245gfhgfhjghghgfh'
), (
nextval('agenda_ageid_seq'), '1', 'Rendez-vous avec Mr Allezy', '2012-08-21 11:30:00', '2012-08-21 12:30:00', 'Salle de reunion 2', 'Description du rendez-vous 2', false, false, 'fcgghjhjhhj45564'
), (
nextval('agenda_ageid_seq'), '1', 'Rendez-vous avec Mr Dupuis', '2012-04-14 14:30:00', '2012-04-14 16:30:00', 'Salle de reunion 3', 'Description du rendez-vous 3', false, false, 'fhngfjhjhdsfgffhfgt54645'
), (
nextval('agenda_ageid_seq'), '1', 'Rendez-vous avec Mr Duval', '2012-05-16 13:30:00', '2012-05-16 14:30:00', 'Salle de reunion 1', 'Description du rendez-vous 4', false, false, 'dfghhjhjkjjk564654546'
), (
nextval('agenda_ageid_seq'), '1', 'Rendez-vous avec Mr Jean', '2012-12-17 08:30:00', '2012-12-17 10:30:00', 'Salle de reunion 2', 'Description du rendez-vous 5', false, false, 'fdhhjhjsddfghhj546546354'
), (
nextval('agenda_ageid_seq'), '1', 'Rendez-vous avec Mr Cover', '2012-10-22 10:30:00', '2012-10-22 17:30:00', 'Salle de reunion 3', 'Description du rendez-vous 6', false, false, 'dffghfghdsfdfgghfhj546546'
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
'2', '1', 5000
), (
'1', '2', 20
), (
'2', '2', 2000
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
'1', '6', 10
), (
'2', '6', 500
), (
'1', '7', 10
), (
'2', '7', 100000
);
INSERT INTO demande (
DEMANDEID ,
INTERID,
CLIID,
UTIID,
DEMANDETITRE,
DEMANDEDESC,
DEMANDEETAT ,
DEMANDEDTEADD ,
DEMANDESUPPR,
DEMANDEUNIQID
)
VALUES (
nextval('demande_demandeid_seq'), 3, 1, 1, 'Titre de la demande 1', 'Description de la demande 1', 50, '2012-03-14', false, 'frdghgfhgjhjhfgdsgfh54654'
), (
nextval('demande_demandeid_seq'), 3, 1, 1, 'Titre de la demande 2', 'Description de la demande 1', 50, '2012-03-15', false, 'dsgfdghfhjhjjh5465646'
), (
nextval('demande_demandeid_seq'), 3, 1, 1, 'Titre de la demande 3', 'Description de la demande 1', 30, '2012-03-14', false, 'fgdhgfhgkjh456654'
), (
nextval('demande_demandeid_seq'), 3, 1, 1, 'Titre de la demande 4', 'Description de la demande 1', 30, '2012-04-13', false, 'hjghfddsfdfgjh5654546546'
);
INSERT INTO devis (
DEVID ,
DEMANDEID,
INTERID,
DEVTITRE,
DEVDESC,
DEVETAT,
DEVDATE ,
DEVPRIX ,
DEVSUPPR,
DEVUNIQID
)
VALUES (
nextval('devis_devid_seq'), 1, 3, 'Titre du devis 1', 'Description du devis 1', 50, '2012-03-08', '1900', false, 'fdhgfghjhkhjk45654645'
), (
nextval('devis_devid_seq'), 2, 3, 'Titre du devis 2', 'Description du devis 2', 20,  '2012-03-13', '20050', false, 'fdhhgfhjhhkjhkjkl54546546'
), (
nextval('devis_devid_seq'), 3, 3, 'Titre du devis 3', 'Description du devis 3', 80,  '2012-03-20', '7654', false, 'fghjhgkjhjkhjk54664545645'
), (
nextval('devis_devid_seq'), 1, 3, 'Titre du devis 4', 'Description du devis 4', 100,  '2012-03-04', '72630', false, 'fghjhfdsqdfsfgdfh456456'
), (
nextval('devis_devid_seq'), 2, 3, 'Titre du devis 5', 'Description du devis 5', 0,  '2012-03-13', '2309', false, 'fgfhkjksdqdsgfhhgkjlk4564'
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

INSERT INTO commande (
COMID ,
INTERID,
DEMANDEID,
COMTITRE,
COMDESC,
COMDATE ,
COMDATEPREV ,
COMETAT ,
COMPRODDEB ,
COMPRODFIN ,
COMPRIX ,
COMSUPPR,
COMUNIQID
)
VALUES (
nextval('commande_comid_seq'), 3, 1, 'Titre commande 1', 'Description commande 1', '2012-03-14', '2012-03-16', '80', '2012-03-14', NULL , '2309', false, 'dfghhgjhjhkjl456456'
), (
nextval('commande_comid_seq'), 3, 1, 'Titre commande 2', 'Description commande 2', '2012-03-15', '2012-03-31', '10', '2012-03-15', NULL , '70987', false, 'gfhkiumpopopopoopo54566'
), (
nextval('commande_comid_seq'), 3, 1, 'Titre commande 3', 'Description commande 3', '2012-03-14', '2012-04-02', '100', '2012-03-14', '2012-04-01', '26543', false, 'zqsezzarertyrytru5254'
), (
nextval('commande_comid_seq'), 3, 1, 'Titre commande 4', 'Description commande 4', '2012-04-13', '2012-04-14', '100', '2012-04-13', '2012-04-14', '346543', false, 'rhyghfwgjghkjhjk45646'
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
SUIVIDOSSUPPR,
SUIVDOSUNIQID
)
VALUES (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-15', 'Commentaire 1 au sujet de la commande numero 1', false, 'gffhjgjhkhjkljl45646'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-16', 'Commentaire 2 au sujet de la commande numero 1', false, 'zzaezetrretrteyret45656'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-17', 'Commentaire 3 au sujet de la commande numero 1', false, 'dfghjhjkjhkjhkl456456'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-18', 'Commentaire 4 au sujet de la commande numero 1', false, 'gfdhgfjghkjljklmm5664'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-14', 'Commentaire 5 au sujet de la commande numero 1', false, 'dfsqdsqqsdfgshgf546645'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-15', 'Commentaire 6 au sujet de la commande numero 1', false, 'fhjhgjhkjhklj546546'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-16', 'Commentaire 7 au sujet de la commande numero 1', false, 'gfhhjjhkjklkjl54456'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-19', 'Commentaire 8 au sujet de la commande numero 1', false, 'gfhhytrytryurt546546'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-20', 'Commentaire 9 au sujet de la commande numero 1', false, 'hgfhjgkjhjk456456'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-03-21', 'Commentaire 10 au sujet de la commande numero 1', false, 'ghhgujhgkijhjk546456'
), (
nextval('suivdossier_suivdosid_seq'), '1', '3', NULL , '1' , '2012-05-17', 'Commentaire 11 au sujet de la commande numero 1', false, 'tyutrutyuyt45645654'
), (
nextval('suivdossier_suivdosid_seq'), NULL , '1', '3', NULL , '2012-03-15', 'Commentaire suite au devis numero 3', false, 'fdghfguuyuytuytuuytuyt'
), (
nextval('suivdossier_suivdosid_seq'), '2', '4' , NULL , '1', '2012-03-15', 'Commentaire de l''utilisateur numero 1 pour la commande numero 2', false, '456gfdghfhfhgfhgfh'
), (
nextval('suivdossier_suivdosid_seq'), NULL , '2' , '3', '5', '2012-03-16', 'Commentaire de l''utilisateur numero 5 pour le devis numero 3', false, '7788787fdsfgdgfghgfh'
), (
nextval('suivdossier_suivdosid_seq'), '3', '5', NULL , '5', '2012-03-23', 'Commande', false, '54645dfghgfhgfhgfhgfh'
), (
nextval('suivdossier_suivdosid_seq'), '4', '4', NULL , '5', '2012-03-23', 'Commande', false, 'fdgdfhggfhjhkjljkmkl54654'
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

INSERT INTO enquete (
ENQID ,
UTIID ,
INTERID ,
ENQDOS,
ENQIDDOS,
ENQDTE,
ENQINT,
ENQDESC,
ENQPOS,
ENQTYPE,
ENQSUPPR,
ENQUNIQID
)
VALUES (
nextval('enquete_enqid_seq'), 1, 1 , 'Enqdos1', 10, '2012-12-05', '564dffd', 'Description de l''enquete 1',  false, true, false, 'fhfgjhgjhgjhg45656'
), (
nextval('enquete_enqid_seq'), 1, 1 , 'Enqdos2', 45, '2012-11-20', 'fgdsfg54', 'Description de l''enquete 2',  false, false, false, 'gfjhkjhlkjl546546'
), (
nextval('enquete_enqid_seq'), 1, 1 , 'Enqdos3', 100, '2012-10-14', 'ghg56', 'Description de l''enquete 3',  false, true, false, 'ghjhkhkjl45645645654'
), (
nextval('enquete_enqid_seq'), 1, 1 , 'Enqdos4', 50, '2012-09-04', 'hgjh8512', 'Description de l''enquete 4',  false, false, false, 'jhkjhkljljklmk45651654'
);

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