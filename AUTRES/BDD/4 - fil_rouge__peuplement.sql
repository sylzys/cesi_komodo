--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- Name: action_actionid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('action_actionid_seq', 63, true);


--
-- Name: agenda_ageid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('agenda_ageid_seq', 2, true);


--
-- Name: alerte_alerteid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('alerte_alerteid_seq', 2, true);


--
-- Name: bonatirer_bonatirerid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('bonatirer_bonatirerid_seq', 4, true);


--
-- Name: bonsajeter_bonajeterid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('bonsajeter_bonajeterid_seq', 1, true);


--
-- Name: chaine_chaineid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('chaine_chaineid_seq', 3, true);


--
-- Name: client_cliid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('client_cliid_seq', 7, true);


--
-- Name: cmdmat_cmdmatid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('cmdmat_cmdmatid_seq', 1, true);


--
-- Name: commande_comid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('commande_comid_seq', 4, true);


--
-- Name: controller_ctrlid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('controller_ctrlid_seq', 5, true);


--
-- Name: demande_demandeid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('demande_demandeid_seq', 1, false);


--
-- Name: devis_devid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('devis_devid_seq', 5, true);


--
-- Name: enquete_enqid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('enquete_enqid_seq', 1, false);


--
-- Name: fonction_foncid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('fonction_foncid_seq', 4, true);


--
-- Name: fournisseur_fourid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('fournisseur_fourid_seq', 5, true);


--
-- Name: histostock_histoid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('histostock_histoid_seq', 1, false);


--
-- Name: interlocuteur_interid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('interlocuteur_interid_seq', 10, true);


--
-- Name: matiere_matid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('matiere_matid_seq', 7, true);


--
-- Name: nomenclature_nomid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('nomenclature_nomid_seq', 5, true);


--
-- Name: production_prodid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('production_prodid_seq', 4, true);


--
-- Name: profil_profid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('profil_profid_seq', 4, true);


--
-- Name: stock_stockid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('stock_stockid_seq', 8, true);


--
-- Name: suivdossier_suivdosid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('suivdossier_suivdosid_seq', 6, true);


--
-- Name: unite_uniteid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('unite_uniteid_seq', 8, true);


--
-- Name: utilisateur_utiid_seq; Type: SEQUENCE SET; Schema: public; Owner: cesi
--

SELECT pg_catalog.setval('utilisateur_utiid_seq', 6, true);


--
-- Data for Name: controller; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO controller (ctrlid, ctrlnom, ctrllib, ctrlsuppr) VALUES (1, 'admin', 'Pannel administration', false);
INSERT INTO controller (ctrlid, ctrlnom, ctrllib, ctrlsuppr) VALUES (2, 'clients', 'Annuaire des clients', false);
INSERT INTO controller (ctrlid, ctrlnom, ctrllib, ctrlsuppr) VALUES (3, 'home', 'Accueil', false);
INSERT INTO controller (ctrlid, ctrlnom, ctrllib, ctrlsuppr) VALUES (4, 'prod', 'Gestion de la production', false);
INSERT INTO controller (ctrlid, ctrlnom, ctrllib, ctrlsuppr) VALUES (5, 'stock', 'Gestion des stocks', false);


--
-- Data for Name: action; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (1, 1, 'index', 'Index du site', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (2, 1, 'client', 'Accès aux infos clients', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (3, 1, 'clientlist', 'Consultation de la liste des clients', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (4, 1, 'clientchangeacces', 'Changement de l''accès du client', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (5, 1, 'user', '', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (6, 1, 'userlist', 'Consultation de la liste des utilisateurs', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (7, 1, 'userchangeprofil', 'Changement des profils utilisateurs', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (8, 2, 'index', 'Index du site', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (9, 2, 'getclientinfos', 'Consultation des informations des clients', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (10, 3, 'index', 'Index du site', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (11, 4, 'index', 'Index du site', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (12, 4, 'processbat', 'Bon à tirer', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (13, 4, 'processbaj', 'Bon à jeter', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (14, 4, 'createnom', 'Création de nomenclature', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (15, 4, 'createnomrow', 'Ajout de ligne de matière dans les nomenclatures', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (16, 4, 'createexistingnom', 'Création d enomenclature à partir d''une existante', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (17, 4, 'loadnom', 'Consultation d''une nomenclature', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (18, 4, 'createqr', 'Générer un qr code', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (19, 4, 'editnom', 'Modifier une nomenclature', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (20, 4, 'chainprocess', 'Accès aux chaines de production', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (21, 4, 'chainprocesslist', 'Consultation des chaines de production', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (22, 4, 'disablechain', 'Changement de l''état d''une chaine de production', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (23, 4, 'getbatinfos', 'Consultation d''un bon à tirer', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (24, 4, 'getbajinfos', 'Consultation d''un bon à jeter', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (25, 4, 'alert', 'Accès aux alertes stock', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (26, 4, 'bat', 'Accès au bons à tirer', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (27, 4, 'baj', 'Accès aux bons à jeter', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (28, 4, 'saveNom', 'Sauvegarder une nomenclature', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (29, 5, 'index', 'Index du site', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (30, 5, 'gestionstock', 'Accès à la gestion des stocks', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (31, 5, 'historiquecommande', 'Accès à l''historique des commandes', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (32, 5, 'rechercheproduit', '', true);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (33, 5, 'gestionfournisseur', 'Accès à la gestion des fournisseurs', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (34, 5, 'recherchenomenclature', 'Consultation des nomenclatures dans le module stock', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (35, 3, 'getnotifs', 'Consultation des notifications', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (36, 4, 'deletenom', 'Suppression d''un numenclature', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (37, 1, 'profil', 'Accès aux profils', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (38, 1, 'profillist', 'Consultation des profils', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (39, 1, 'profilchangeright', 'Changement de droits des profils', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (40, 5, 'ajaxeditmat', 'Consultation des stocks d''une matière première', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (41, 1, 'profiladd', 'Ajout de profil', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (42, 5, 'ajaxlistfour', 'Consultation de la liste des fournisseurs', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (43, 5, 'ajaxinfosfour', 'Consultation détail fournisseur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (44, 5, 'ajaxupdtstock', 'Modification des stocks', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (45, 5, 'ajaxdelstock', 'Suppression des stocks', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (46, 5, 'ajaxaddstock', 'Ajout d''un stock', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (47, 5, 'ajaxaddmat', 'Ajout d''une matière première', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (48, 5, 'ajaxdelmat', 'Suppression d''une matière première', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (49, 5, 'ajaxupdtmat', 'Modification d''une matière première', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (50, 5, 'ajaxcmdmat', 'Passer une comamnde', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (51, 5, 'ajaxlistnom', 'Rechercher les nomanclatures dans le module stock', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (52, 1, 'useradd', 'Ajouter un utilisateur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (53, 1, 'useredit', 'Modifier un utilisateur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (54, 1, 'userdelete', 'Supprimer un utilisateur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (55, 5, 'addfourn', 'Ajouter un fournisseur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (56, 5, 'deletefourn', 'Supprimer un fournisseur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (57, 5, 'interfrn', 'Afficher l''interlocuteur d''un fournisseur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (58, 5, 'infosupdtfrn', 'Formulaire de modification d''un fournisseur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (59, 5, 'editfourn', 'Modifier un fournisseur', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (60, 5, 'ajaxlistunite', 'Afficher la liste des unités de mesure', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (61, 5, 'ajaxaddunite', 'Ajouter une unité de mesure', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (62, 5, 'ajaxupdtunite', 'Modifier une unité de mesure', false);
INSERT INTO action (actionid, ctrlid, actionnom, actionlib, actionsuppr) VALUES (63, 5, 'ajaxdelunite', 'Supprimer une unité de mesure', false);


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO utilisateur (utiid, utinom, utiprenom, utilogin, utimdp, utimail, utitel, utisuppr, utidtelog) VALUES (1, 'Administrateur', NULL, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@plastprod.fr', '0654637643', false, NULL);
INSERT INTO utilisateur (utiid, utinom, utiprenom, utilogin, utimdp, utimail, utitel, utisuppr, utidtelog) VALUES (2, 'Commercial 1', NULL, 'commercial1', '552a42b69b329bf60d96e1cb6aa5e321', 'commercial1@plastprod.fr', '0689764365', false, NULL);
INSERT INTO utilisateur (utiid, utinom, utiprenom, utilogin, utimdp, utimail, utitel, utisuppr, utidtelog) VALUES (3, 'Commercial 2', NULL, 'commercial2', '04c62ae304ba5a5df7d9703e5d3b483e', 'commercial2@plastprod.fr', '0689344365', false, NULL);
INSERT INTO utilisateur (utiid, utinom, utiprenom, utilogin, utimdp, utimail, utitel, utisuppr, utidtelog) VALUES (4, 'Commercial 3', NULL, 'commercial3', 'af4184c099afa747374dd59f0ed01203', 'commercial3@plastprod.fr', '0689762315', false, NULL);
INSERT INTO utilisateur (utiid, utinom, utiprenom, utilogin, utimdp, utimail, utitel, utisuppr, utidtelog) VALUES (5, 'Stock', NULL, 'stock', '908880209a64ea539ae8dc5fdb7e0a91', 'stock@plastprod.fr', '066543643', false, NULL);
INSERT INTO utilisateur (utiid, utinom, utiprenom, utilogin, utimdp, utimail, utitel, utisuppr, utidtelog) VALUES (6, 'Production', NULL, 'prod', 'd6e4a9b6646c62fc48baa6dd6150d1f7', 'prod@plastprod.fr', '0676543465', false, NULL);


--
-- Data for Name: agenda; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO agenda (ageid, utiid, ageintitule, agedeb, agefin, agelieu, agedesc, ageetat, agesuppr) VALUES (1, 2, NULL, '2012-03-28', '2012-03-28', 'Salle de reunion 1', NULL, NULL, false);
INSERT INTO agenda (ageid, utiid, ageintitule, agedeb, agefin, agelieu, agedesc, ageetat, agesuppr) VALUES (2, 5, NULL, '2012-03-31', '2012-03-14', 'Salle de reunion 2', NULL, NULL, false);


--
-- Data for Name: alerte; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO alerte (alerteid, alertelbl, alertesuppr) VALUES (1, 'mini', false);
INSERT INTO alerte (alerteid, alertelbl, alertesuppr) VALUES (2, 'max', false);


--
-- Data for Name: unite; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO unite (uniteid, unitelbl, unitesuppr) VALUES (1, 'Kg', false);
INSERT INTO unite (uniteid, unitelbl, unitesuppr) VALUES (2, 'Gramme', false);
INSERT INTO unite (uniteid, unitelbl, unitesuppr) VALUES (3, 'Mètre', false);
INSERT INTO unite (uniteid, unitelbl, unitesuppr) VALUES (4, 'M3', false);
INSERT INTO unite (uniteid, unitelbl, unitesuppr) VALUES (5, 'Litre', false);
INSERT INTO unite (uniteid, unitelbl, unitesuppr) VALUES (6, 'Unité', false);
INSERT INTO unite (uniteid, unitelbl, unitesuppr) VALUES (7, 'Carton', false);
INSERT INTO unite (uniteid, unitelbl, unitesuppr) VALUES (8, 'x 1000', false);


--
-- Data for Name: matiere; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO matiere (matid, uniteid, matlib, matsuppr) VALUES (1, 6, 'vis 2 x 2', false);
INSERT INTO matiere (matid, uniteid, matlib, matsuppr) VALUES (2, 6, 'rondelle 5 x 10', false);
INSERT INTO matiere (matid, uniteid, matlib, matsuppr) VALUES (3, 6, 'puce electronique', false);
INSERT INTO matiere (matid, uniteid, matlib, matsuppr) VALUES (4, 6, 'condensateur', false);
INSERT INTO matiere (matid, uniteid, matlib, matsuppr) VALUES (5, 1, 'plastique', false);
INSERT INTO matiere (matid, uniteid, matlib, matsuppr) VALUES (6, 5, 'peinture rouge', false);
INSERT INTO matiere (matid, uniteid, matlib, matsuppr) VALUES (7, 6, 'tournevis plat', false);


--
-- Data for Name: alertemat; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (1, 1, 10);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (2, 1, 20);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (1, 2, 20);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (2, 2, 40);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (1, 3, 5);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (2, 3, 100);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (1, 4, 1);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (2, 4, 50000);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (1, 5, 10);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (2, 5, 2000);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (1, 6, 1);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (2, 6, 50);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (1, 7, 10);
INSERT INTO alertemat (alerteid, matid, qtemat) VALUES (2, 7, 100000);


--
-- Data for Name: bonatirer; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO bonatirer (bonatirerid, prodid, bonatirerdte, bonatirersuppr) VALUES (1, 1, '2012-03-14', false);
INSERT INTO bonatirer (bonatirerid, prodid, bonatirerdte, bonatirersuppr) VALUES (2, 2, '2012-03-16', false);
INSERT INTO bonatirer (bonatirerid, prodid, bonatirerdte, bonatirersuppr) VALUES (3, 3, '2012-03-20', false);
INSERT INTO bonatirer (bonatirerid, prodid, bonatirerdte, bonatirersuppr) VALUES (4, 4, '2012-03-20', false);


--
-- Data for Name: bonsajeter; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO bonsajeter (bonajeterid, prodid, bonajeterdte, bonajetersuppr) VALUES (1, 3, '2012-03-15', false);


--
-- Data for Name: chaine; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO chaine (chaineid, chainelib, chainedispo, chainesuppr) VALUES (1, 'chaine 1', true, false);
INSERT INTO chaine (chaineid, chainelib, chainedispo, chainesuppr) VALUES (2, 'chaine 2', true, false);
INSERT INTO chaine (chaineid, chainelib, chainedispo, chainesuppr) VALUES (3, 'chaine 3', true, false);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO client (cliid, utiid, uti_utiid, clirais, clinom, cliadresse, clicp, cliville, clipays, clitel, clifax, climail, cliactivite, clisiret, clica, clisite, clidg, clietat, clilogin, climdp, cliacces, clisuppr, clidtelog, clidteadd, clinaf, clisiren) VALUES (3, 3, NULL, 'SARL      ', 'Renot', '54 route de Paris', '76320     ', 'Saint Pierre', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 'client3                                           ', 'c27af3f6460eb10979adb366fc7f6856', true, false, NULL, NULL, NULL, NULL);
INSERT INTO client (cliid, utiid, uti_utiid, clirais, clinom, cliadresse, clicp, cliville, clipays, clitel, clifax, climail, cliactivite, clisiret, clica, clisite, clidg, clietat, clilogin, climdp, cliacces, clisuppr, clidtelog, clidteadd, clinaf, clisiren) VALUES (4, 3, NULL, 'SAS       ', 'AutoSAS', '198 rue Jean Bon', '27100     ', 'Val de Reuil', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 'client4                                           ', 'de285ec98e0f83211da217a4e1c5923e', true, false, NULL, NULL, NULL, NULL);
INSERT INTO client (cliid, utiid, uti_utiid, clirais, clinom, cliadresse, clicp, cliville, clipays, clitel, clifax, climail, cliactivite, clisiret, clica, clisite, clidg, clietat, clilogin, climdp, cliacces, clisuppr, clidtelog, clidteadd, clinaf, clisiren) VALUES (5, 4, NULL, 'SAS       ', 'ProdSAS', '32 route de Dieppe', '76430     ', 'Montville', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 'client5                                           ', '7e8670fef6f81f377fe6e162ea1077e5', true, false, NULL, NULL, NULL, NULL);
INSERT INTO client (cliid, utiid, uti_utiid, clirais, clinom, cliadresse, clicp, cliville, clipays, clitel, clifax, climail, cliactivite, clisiret, clica, clisite, clidg, clietat, clilogin, climdp, cliacces, clisuppr, clidtelog, clidteadd, clinaf, clisiren) VALUES (1, 1, 1, 'SAS       ', 'AutoConstruct', '11 rue Harry Cover', '76000     ', 'Rouen', NULL, NULL, NULL, NULL, NULL, NULL, 1000, NULL, NULL, 100, 'client1                                           ', 'a165dd3c2e98d5d607181d0b87a4c66b', true, false, NULL, NULL, NULL, NULL);
INSERT INTO client (cliid, utiid, uti_utiid, clirais, clinom, cliadresse, clicp, cliville, clipays, clitel, clifax, climail, cliactivite, clisiret, clica, clisite, clidg, clietat, clilogin, climdp, cliacces, clisuppr, clidtelog, clidteadd, clinaf, clisiren) VALUES (2, 1, 1, 'SA        ', 'AutoRD', '25 rue Henry', '75000     ', 'Paris', NULL, NULL, NULL, NULL, NULL, NULL, 1000, NULL, NULL, 100, 'client2                                           ', '2c66045d4e4a90814ce9280272e510ec', true, false, NULL, NULL, NULL, NULL);
INSERT INTO client (cliid, utiid, uti_utiid, clirais, clinom, cliadresse, clicp, cliville, clipays, clitel, clifax, climail, cliactivite, clisiret, clica, clisite, clidg, clietat, clilogin, climdp, cliacces, clisuppr, clidtelog, clidteadd, clinaf, clisiren) VALUES (7, 1, 1, NULL, 'sdw', 'esdqdwx', 'qwdx      ', 's', NULL, 'T67890', '', '', 'hejqshwdb,n', 'kjh', 0, '', '', 0, NULL, NULL, false, false, NULL, NULL, '', 'kjh');


--
-- Data for Name: fournisseur; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO fournisseur (fourid, fourrais, fournom, fouradresse, fourcp, fourville, foursuppr) VALUES (1, 'SAS', 'FourMat', '32 route de Paris', '76000', 'Rouen', false);
INSERT INTO fournisseur (fourid, fourrais, fournom, fouradresse, fourcp, fourville, foursuppr) VALUES (2, 'SA', 'FourStock', '56 route du Havre', '76400', 'Le Houlme', false);
INSERT INTO fournisseur (fourid, fourrais, fournom, fouradresse, fourcp, fourville, foursuppr) VALUES (3, 'SAS', 'MatiereFour', '182 rue Jeanne d''Arc', '76000', 'Rouen', false);
INSERT INTO fournisseur (fourid, fourrais, fournom, fouradresse, fourcp, fourville, foursuppr) VALUES (4, 'SA', 'FournitureMat', '123 route de Dieppe', '76430', 'Montville', false);
INSERT INTO fournisseur (fourid, fourrais, fournom, fouradresse, fourcp, fourville, foursuppr) VALUES (5, 'SAS', 'FournisseurSAS', '76 rue du Renard', '76000', 'Rouen', false);


--
-- Data for Name: cmdmat; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO cmdmat (cmdmatid, matid, fourid, datecmdmat, qtecmdmat, pucmdmat, reffourcmdmat) VALUES (1, 1, 1, '30/04/2012', 20, 150, '5454hg');


--
-- Data for Name: commande; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO commande (comid, comtitre, comdesc, comdate, comdateprev, cometat, comresume, comproddeb, comprodfin, comprix, comsuppr) VALUES (1, NULL, NULL, '2012-03-14', '2012-03-16', 80, NULL, '2012-03-14', NULL, 2309, false);
INSERT INTO commande (comid, comtitre, comdesc, comdate, comdateprev, cometat, comresume, comproddeb, comprodfin, comprix, comsuppr) VALUES (2, NULL, NULL, '2012-03-15', '2012-03-31', 10, NULL, '2012-03-15', NULL, 70987, false);
INSERT INTO commande (comid, comtitre, comdesc, comdate, comdateprev, cometat, comresume, comproddeb, comprodfin, comprix, comsuppr) VALUES (3, NULL, NULL, '2012-03-14', '2012-04-02', 100, NULL, '2012-03-14', '2012-04-01', 26543, false);
INSERT INTO commande (comid, comtitre, comdesc, comdate, comdateprev, cometat, comresume, comproddeb, comprodfin, comprix, comsuppr) VALUES (4, NULL, NULL, '2012-04-13', '2012-04-14', 100, NULL, '2012-04-13', '2012-04-14', 346543, false);


--
-- Data for Name: nomenclature; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO nomenclature (nomid, nomlib, nomdate, nomtemps, nomnbchaine, nomqr, nomprix, nomdes, nomsuppr) VALUES (1, 'Comodo 1', '2012-03-14', '35        ', 1, '1.png', 200, 'Comodo numero 1', false);
INSERT INTO nomenclature (nomid, nomlib, nomdate, nomtemps, nomnbchaine, nomqr, nomprix, nomdes, nomsuppr) VALUES (2, 'Comodo 2', '2012-03-02', '180       ', 2, '2.png', 1540, 'Comodo numero 2', false);
INSERT INTO nomenclature (nomid, nomlib, nomdate, nomtemps, nomnbchaine, nomqr, nomprix, nomdes, nomsuppr) VALUES (3, 'Comodo 3', '2012-03-03', '110       ', 1, '3.png', 230, 'Comodo numero 3', false);
INSERT INTO nomenclature (nomid, nomlib, nomdate, nomtemps, nomnbchaine, nomqr, nomprix, nomdes, nomsuppr) VALUES (4, 'Comodo 4', '2012-03-28', '240       ', 3, '4.png', 2790, 'Comodo numero 4', false);
INSERT INTO nomenclature (nomid, nomlib, nomdate, nomtemps, nomnbchaine, nomqr, nomprix, nomdes, nomsuppr) VALUES (5, 'Comodo 5', '2012-03-16', '210       ', 1, '5.png', 320, 'Comodo numero 5', false);


--
-- Data for Name: comnom; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO comnom (comid, nomid, comqte) VALUES (3, 2, 3);
INSERT INTO comnom (comid, nomid, comqte) VALUES (1, 4, 34);
INSERT INTO comnom (comid, nomid, comqte) VALUES (2, 5, 2);
INSERT INTO comnom (comid, nomid, comqte) VALUES (1, 5, 32);
INSERT INTO comnom (comid, nomid, comqte) VALUES (3, 1, 37);
INSERT INTO comnom (comid, nomid, comqte) VALUES (2, 1, 87);
INSERT INTO comnom (comid, nomid, comqte) VALUES (4, 5, 875);
INSERT INTO comnom (comid, nomid, comqte) VALUES (2, 4, 6);
INSERT INTO comnom (comid, nomid, comqte) VALUES (1, 3, 876);
INSERT INTO comnom (comid, nomid, comqte) VALUES (1, 1, 8754);


--
-- Data for Name: demande; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO demande (demandeid, cliid, utiid, demandeetat, demandedteadd, demandesuppr) VALUES (1, 1, 1, 50, '2012-10-22', false);


--
-- Data for Name: devis; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO devis (devid, devetat, devdate, devprix, devsuppr, deviid) VALUES (1, NULL, '2012-03-08', 1900, false, NULL);
INSERT INTO devis (devid, devetat, devdate, devprix, devsuppr, deviid) VALUES (2, NULL, '2012-03-13', 20050, false, NULL);
INSERT INTO devis (devid, devetat, devdate, devprix, devsuppr, deviid) VALUES (3, NULL, '2012-03-20', 7654, false, NULL);
INSERT INTO devis (devid, devetat, devdate, devprix, devsuppr, deviid) VALUES (4, NULL, '2012-03-04', 72630, false, NULL);
INSERT INTO devis (devid, devetat, devdate, devprix, devsuppr, deviid) VALUES (5, NULL, '2012-03-13', 2309, false, NULL);


--
-- Data for Name: devnom; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO devnom (nomid, devid, devnomqte) VALUES (3, 1, 3);
INSERT INTO devnom (nomid, devid, devnomqte) VALUES (1, 1, 1);
INSERT INTO devnom (nomid, devid, devnomqte) VALUES (4, 2, 2);
INSERT INTO devnom (nomid, devid, devnomqte) VALUES (5, 2, 5);
INSERT INTO devnom (nomid, devid, devnomqte) VALUES (1, 3, 1);
INSERT INTO devnom (nomid, devid, devnomqte) VALUES (5, 4, 2);
INSERT INTO devnom (nomid, devid, devnomqte) VALUES (1, 4, 2);


--
-- Data for Name: interlocuteur; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (1, 1, NULL, NULL, 'Lefevre', 'Harry', 'harrylevefre@gmail.com', '0665434321', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (2, 2, NULL, NULL, 'Fournier', 'Jean', 'jeanfournier@gmail.com', '0634231265', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (3, NULL, 2, 1, 'Jacques', 'Jean', 'jeanjacques@gmail.com', '0654678798', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (4, NULL, 3, 3, 'Marie', 'Anne', 'annemarie@gmail.com', '0676894523', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (5, NULL, 2, 5, 'Claude', 'Jean', 'jeanclaude@gmail.com', '0623438756', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (6, 3, NULL, NULL, 'Paul', 'Dupres', 'pauldupres@gmail.com', '0685454548', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (7, 4, NULL, NULL, 'Henry', 'Charles', 'henrycharles@gmail.com', '0652121417', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (8, 5, NULL, NULL, 'Maxime', 'Dupond', 'maximedupond@gmail.com', '0687454578', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (10, NULL, NULL, 4, 'Yves', 'paul', 'yvespaul@gmail.com', '0676894523', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (9, 1, NULL, 2, 'Antoine', 'Dumet', 'antoinedumet@gmail.com', '0676894523', NULL, NULL, false);
INSERT INTO interlocuteur (interid, fourid, utiid, cliid, internom, interprenom, intermail, intertel, interposte, interdteadd, intersuppr) VALUES (11, NULL, 1, 1, 'Toto', 'Titi', 'toto_titi@kikoo.com', '06766554321', NULL, NULL, false);


--
-- Data for Name: enquete; Type: TABLE DATA; Schema: public; Owner: cesi
--



--
-- Data for Name: fonction; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO fonction (foncid, fonclib, foncsuppr) VALUES (1, 'ADMINISTRATEUR', false);
INSERT INTO fonction (foncid, fonclib, foncsuppr) VALUES (2, 'COMMERCIAL', false);
INSERT INTO fonction (foncid, fonclib, foncsuppr) VALUES (3, 'RESPONSABLE PRODUCTION', false);
INSERT INTO fonction (foncid, fonclib, foncsuppr) VALUES (4, 'RESPONSABLE MAGASIN', false);


--
-- Data for Name: foncuti; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO foncuti (utiid, foncid) VALUES (1, 1);
INSERT INTO foncuti (utiid, foncid) VALUES (2, 2);
INSERT INTO foncuti (utiid, foncid) VALUES (3, 2);
INSERT INTO foncuti (utiid, foncid) VALUES (4, 2);
INSERT INTO foncuti (utiid, foncid) VALUES (5, 3);
INSERT INTO foncuti (utiid, foncid) VALUES (6, 4);


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO stock (stockid, interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES (1, 2, 1, '2012-03-14', '2012-03-30', NULL, 500, '2456789', 4, false);
INSERT INTO stock (stockid, interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES (2, 1, 4, '2012-03-12', '2012-03-16', NULL, 200, '4456677', 50, false);
INSERT INTO stock (stockid, interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES (3, 1, 2, '2012-03-04', '2012-03-30', NULL, 1000, '5756790', 14, false);
INSERT INTO stock (stockid, interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES (4, 2, 3, '2012-03-13', '2012-03-19', NULL, 100, '978676576', 150, false);
INSERT INTO stock (stockid, interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES (5, 2, 5, '2012-03-11', '2012-03-31', NULL, 20, '45654654', 235, false);
INSERT INTO stock (stockid, interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES (6, 1, 6, '2012-03-11', '2012-03-31', NULL, 20, '786564', 25, false);
INSERT INTO stock (stockid, interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES (7, 1, 7, '2012-03-11', '2012-03-31', NULL, 20, '787877', 5, false);
INSERT INTO stock (stockid, interid, matid, stockdatecom, stockentree, stocksortie, stockqte, stockreffour, stockpu, stocksuppr) VALUES (8, 2, 7, '2012-03-11', '2012-03-31', NULL, 20, '985244', 2, false);


--
-- Data for Name: histostock; Type: TABLE DATA; Schema: public; Owner: cesi
--



--
-- Data for Name: nommat; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO nommat (nomid, matid, matqte) VALUES (1, 2, 2);
INSERT INTO nommat (nomid, matid, matqte) VALUES (1, 4, 4);
INSERT INTO nommat (nomid, matid, matqte) VALUES (2, 4, 3);
INSERT INTO nommat (nomid, matid, matqte) VALUES (3, 5, 1);
INSERT INTO nommat (nomid, matid, matqte) VALUES (4, 5, 6);
INSERT INTO nommat (nomid, matid, matqte) VALUES (2, 6, 5);
INSERT INTO nommat (nomid, matid, matqte) VALUES (5, 3, 4);
INSERT INTO nommat (nomid, matid, matqte) VALUES (5, 7, 1);
INSERT INTO nommat (nomid, matid, matqte) VALUES (2, 5, 2);
INSERT INTO nommat (nomid, matid, matqte) VALUES (4, 3, 3);


--
-- Data for Name: production; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO production (prodid, comid, bonajeterid, bonatirerid, chaineid, chainedatedeb, chainedatefin, qrcode, prodsuppr) VALUES (1, 1, NULL, 1, 1, '2012-03-15', '2012-03-15', '1_1.png                                                                                             ', false);
INSERT INTO production (prodid, comid, bonajeterid, bonatirerid, chaineid, chainedatedeb, chainedatefin, qrcode, prodsuppr) VALUES (2, 2, NULL, 2, 2, '2012-03-14', '2012-03-15', '2_2.png                                                                                             ', false);
INSERT INTO production (prodid, comid, bonajeterid, bonatirerid, chaineid, chainedatedeb, chainedatefin, qrcode, prodsuppr) VALUES (3, 1, 1, 3, 1, '2012-03-14', '2012-03-15', '3_1.png                                                                                             ', false);
INSERT INTO production (prodid, comid, bonajeterid, bonatirerid, chaineid, chainedatedeb, chainedatefin, qrcode, prodsuppr) VALUES (4, 3, NULL, 4, 3, '2012-03-14', '2012-03-15', '4_3.png                                                                                             ', false);


--
-- Data for Name: profil; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO profil (profid, proflib, profsuppr) VALUES (1, 'administrateur', false);
INSERT INTO profil (profid, proflib, profsuppr) VALUES (2, 'commercial', false);
INSERT INTO profil (profid, proflib, profsuppr) VALUES (3, 'stock', false);
INSERT INTO profil (profid, proflib, profsuppr) VALUES (4, 'production', false);


--
-- Data for Name: profaction; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO profaction (actionid, profid) VALUES (1, 1);
INSERT INTO profaction (actionid, profid) VALUES (2, 1);
INSERT INTO profaction (actionid, profid) VALUES (3, 1);
INSERT INTO profaction (actionid, profid) VALUES (4, 1);
INSERT INTO profaction (actionid, profid) VALUES (5, 1);
INSERT INTO profaction (actionid, profid) VALUES (6, 1);
INSERT INTO profaction (actionid, profid) VALUES (7, 1);
INSERT INTO profaction (actionid, profid) VALUES (8, 1);
INSERT INTO profaction (actionid, profid) VALUES (9, 1);
INSERT INTO profaction (actionid, profid) VALUES (10, 1);
INSERT INTO profaction (actionid, profid) VALUES (11, 1);
INSERT INTO profaction (actionid, profid) VALUES (12, 1);
INSERT INTO profaction (actionid, profid) VALUES (13, 1);
INSERT INTO profaction (actionid, profid) VALUES (14, 1);
INSERT INTO profaction (actionid, profid) VALUES (15, 1);
INSERT INTO profaction (actionid, profid) VALUES (16, 1);
INSERT INTO profaction (actionid, profid) VALUES (17, 1);
INSERT INTO profaction (actionid, profid) VALUES (18, 1);
INSERT INTO profaction (actionid, profid) VALUES (19, 1);
INSERT INTO profaction (actionid, profid) VALUES (20, 1);
INSERT INTO profaction (actionid, profid) VALUES (21, 1);
INSERT INTO profaction (actionid, profid) VALUES (22, 1);
INSERT INTO profaction (actionid, profid) VALUES (23, 1);
INSERT INTO profaction (actionid, profid) VALUES (24, 1);
INSERT INTO profaction (actionid, profid) VALUES (25, 1);
INSERT INTO profaction (actionid, profid) VALUES (26, 1);
INSERT INTO profaction (actionid, profid) VALUES (27, 1);
INSERT INTO profaction (actionid, profid) VALUES (28, 1);
INSERT INTO profaction (actionid, profid) VALUES (29, 1);
INSERT INTO profaction (actionid, profid) VALUES (30, 1);
INSERT INTO profaction (actionid, profid) VALUES (31, 1);
INSERT INTO profaction (actionid, profid) VALUES (32, 1);
INSERT INTO profaction (actionid, profid) VALUES (33, 1);
INSERT INTO profaction (actionid, profid) VALUES (34, 1);
INSERT INTO profaction (actionid, profid) VALUES (36, 1);
INSERT INTO profaction (actionid, profid) VALUES (37, 1);
INSERT INTO profaction (actionid, profid) VALUES (38, 1);
INSERT INTO profaction (actionid, profid) VALUES (39, 1);
INSERT INTO profaction (actionid, profid) VALUES (40, 1);
INSERT INTO profaction (actionid, profid) VALUES (41, 1);
INSERT INTO profaction (actionid, profid) VALUES (42, 1);
INSERT INTO profaction (actionid, profid) VALUES (43, 1);
INSERT INTO profaction (actionid, profid) VALUES (44, 1);
INSERT INTO profaction (actionid, profid) VALUES (45, 1);
INSERT INTO profaction (actionid, profid) VALUES (46, 1);
INSERT INTO profaction (actionid, profid) VALUES (47, 1);
INSERT INTO profaction (actionid, profid) VALUES (48, 1);
INSERT INTO profaction (actionid, profid) VALUES (49, 1);
INSERT INTO profaction (actionid, profid) VALUES (50, 1);
INSERT INTO profaction (actionid, profid) VALUES (51, 1);
INSERT INTO profaction (actionid, profid) VALUES (52, 1);
INSERT INTO profaction (actionid, profid) VALUES (53, 1);
INSERT INTO profaction (actionid, profid) VALUES (54, 1);
INSERT INTO profaction (actionid, profid) VALUES (55, 1);
INSERT INTO profaction (actionid, profid) VALUES (56, 1);
INSERT INTO profaction (actionid, profid) VALUES (57, 1);
INSERT INTO profaction (actionid, profid) VALUES (58, 1);
INSERT INTO profaction (actionid, profid) VALUES (59, 1);
INSERT INTO profaction (actionid, profid) VALUES (60, 1);
INSERT INTO profaction (actionid, profid) VALUES (61, 1);
INSERT INTO profaction (actionid, profid) VALUES (62, 1);
INSERT INTO profaction (actionid, profid) VALUES (63, 1);
INSERT INTO profaction (actionid, profid) VALUES (8, 2);
INSERT INTO profaction (actionid, profid) VALUES (9, 2);
INSERT INTO profaction (actionid, profid) VALUES (10, 2);
INSERT INTO profaction (actionid, profid) VALUES (10, 3);
INSERT INTO profaction (actionid, profid) VALUES (29, 3);
INSERT INTO profaction (actionid, profid) VALUES (30, 3);
INSERT INTO profaction (actionid, profid) VALUES (31, 3);
INSERT INTO profaction (actionid, profid) VALUES (32, 3);
INSERT INTO profaction (actionid, profid) VALUES (33, 3);
INSERT INTO profaction (actionid, profid) VALUES (34, 3);
INSERT INTO profaction (actionid, profid) VALUES (10, 4);
INSERT INTO profaction (actionid, profid) VALUES (11, 4);
INSERT INTO profaction (actionid, profid) VALUES (12, 4);
INSERT INTO profaction (actionid, profid) VALUES (13, 4);
INSERT INTO profaction (actionid, profid) VALUES (14, 4);
INSERT INTO profaction (actionid, profid) VALUES (15, 4);
INSERT INTO profaction (actionid, profid) VALUES (16, 4);
INSERT INTO profaction (actionid, profid) VALUES (17, 4);
INSERT INTO profaction (actionid, profid) VALUES (18, 4);
INSERT INTO profaction (actionid, profid) VALUES (19, 4);
INSERT INTO profaction (actionid, profid) VALUES (20, 4);
INSERT INTO profaction (actionid, profid) VALUES (21, 4);
INSERT INTO profaction (actionid, profid) VALUES (22, 4);
INSERT INTO profaction (actionid, profid) VALUES (23, 4);
INSERT INTO profaction (actionid, profid) VALUES (24, 4);
INSERT INTO profaction (actionid, profid) VALUES (25, 4);
INSERT INTO profaction (actionid, profid) VALUES (26, 4);
INSERT INTO profaction (actionid, profid) VALUES (27, 4);
INSERT INTO profaction (actionid, profid) VALUES (28, 4);
INSERT INTO profaction (actionid, profid) VALUES (35, 1);
INSERT INTO profaction (actionid, profid) VALUES (35, 2);
INSERT INTO profaction (actionid, profid) VALUES (35, 3);
INSERT INTO profaction (actionid, profid) VALUES (35, 4);
INSERT INTO profaction (actionid, profid) VALUES (36, 4);
INSERT INTO profaction (actionid, profid) VALUES (40, 3);
INSERT INTO profaction (actionid, profid) VALUES (42, 3);
INSERT INTO profaction (actionid, profid) VALUES (43, 3);
INSERT INTO profaction (actionid, profid) VALUES (44, 3);
INSERT INTO profaction (actionid, profid) VALUES (45, 3);
INSERT INTO profaction (actionid, profid) VALUES (46, 3);
INSERT INTO profaction (actionid, profid) VALUES (47, 3);
INSERT INTO profaction (actionid, profid) VALUES (48, 3);
INSERT INTO profaction (actionid, profid) VALUES (49, 3);
INSERT INTO profaction (actionid, profid) VALUES (50, 3);
INSERT INTO profaction (actionid, profid) VALUES (51, 3);
INSERT INTO profaction (actionid, profid) VALUES (55, 3);
INSERT INTO profaction (actionid, profid) VALUES (56, 3);
INSERT INTO profaction (actionid, profid) VALUES (57, 3);
INSERT INTO profaction (actionid, profid) VALUES (58, 3);
INSERT INTO profaction (actionid, profid) VALUES (59, 3);
INSERT INTO profaction (actionid, profid) VALUES (60, 3);
INSERT INTO profaction (actionid, profid) VALUES (61, 3);
INSERT INTO profaction (actionid, profid) VALUES (62, 3);
INSERT INTO profaction (actionid, profid) VALUES (63, 3);


--
-- Data for Name: suivdossier; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO suivdossier (suivdosid, comid, interid, demandeid, devid, utiid, suivdosdate, suivdoscom, suividossuppr, suivdossuppr) VALUES (1, 1, 3, NULL, NULL, NULL, '2012-03-14', 'Commentaire au sujet de la commande numero 1', false, NULL);
INSERT INTO suivdossier (suivdosid, comid, interid, demandeid, devid, utiid, suivdosdate, suivdoscom, suividossuppr, suivdossuppr) VALUES (2, NULL, 1, NULL, 3, NULL, '2012-03-15', 'Commentaire suite au devis numero 3', false, NULL);
INSERT INTO suivdossier (suivdosid, comid, interid, demandeid, devid, utiid, suivdosdate, suivdoscom, suividossuppr, suivdossuppr) VALUES (3, 2, 4, NULL, NULL, 1, '2012-03-15', 'Commentaire de l''utilisateur numero 1 pour la commande numero 2', false, NULL);
INSERT INTO suivdossier (suivdosid, comid, interid, demandeid, devid, utiid, suivdosdate, suivdoscom, suividossuppr, suivdossuppr) VALUES (4, NULL, 2, NULL, 3, 5, '2012-03-16', 'Commentaire de l''utilisateur numero 5 pour le devis numero 3', false, NULL);
INSERT INTO suivdossier (suivdosid, comid, interid, demandeid, devid, utiid, suivdosdate, suivdoscom, suividossuppr, suivdossuppr) VALUES (5, 3, 5, NULL, NULL, 5, '2012-03-23', 'Commande', false, NULL);
INSERT INTO suivdossier (suivdosid, comid, interid, demandeid, devid, utiid, suivdosdate, suivdoscom, suividossuppr, suivdossuppr) VALUES (6, 4, 4, NULL, NULL, 5, '2012-03-23', 'Commande', false, NULL);


--
-- Data for Name: utiprof; Type: TABLE DATA; Schema: public; Owner: cesi
--

INSERT INTO utiprof (utiid, profid) VALUES (1, 1);
INSERT INTO utiprof (utiid, profid) VALUES (2, 2);
INSERT INTO utiprof (utiid, profid) VALUES (3, 2);
INSERT INTO utiprof (utiid, profid) VALUES (4, 2);
INSERT INTO utiprof (utiid, profid) VALUES (5, 3);
INSERT INTO utiprof (utiid, profid) VALUES (6, 4);


--
-- PostgreSQL database dump complete
--

