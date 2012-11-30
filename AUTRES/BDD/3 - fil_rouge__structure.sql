/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  30/11/2012 15:03:42                      */
/*==============================================================*/


/*==============================================================*/
/* Table : ACTION                                               */
/*==============================================================*/
create table ACTION (
   ACTIONID             SERIAL               not null,
   CTRLID               INT4                 null,
   ACTIONNOM            VARCHAR(50)          null,
   ACTIONLIB            VARCHAR(254)         null,
   ACTIONSUPPR          BOOL                 null,
   constraint PK_ACTION primary key (ACTIONID)
);

/*==============================================================*/
/* Index : ACTION_PK                                            */
/*==============================================================*/
create unique index ACTION_PK on ACTION (
ACTIONID
);

/*==============================================================*/
/* Index : CTRLACTION_FK                                        */
/*==============================================================*/
create  index CTRLACTION_FK on ACTION (
CTRLID
);

/*==============================================================*/
/* Table : AGENDA                                               */
/*==============================================================*/
create table AGENDA (
   AGEID                SERIAL               not null,
   UTIID                INT4                 not null,
   AGEINTITULE          VARCHAR(254)         null,
   AGEDEB               DATE                 null,
   AGEFIN               DATE                 null,
   AGELIEU              VARCHAR(50)          null,
   AGEDESC              VARCHAR(1000)        null,
   AGEETAT              BOOL                 null,
   AGESUPPR             BOOL                 null,
   AGEUNIQID            VARCHAR(500)         null,
   constraint PK_AGENDA primary key (AGEID)
);

/*==============================================================*/
/* Index : AGENDA_PK                                            */
/*==============================================================*/
create unique index AGENDA_PK on AGENDA (
AGEID
);

/*==============================================================*/
/* Index : UTIAGE_FK                                            */
/*==============================================================*/
create  index UTIAGE_FK on AGENDA (
UTIID
);

/*==============================================================*/
/* Table : ALERTE                                               */
/*==============================================================*/
create table ALERTE (
   ALERTEID             SERIAL               not null,
   ALERTELBL            VARCHAR(50)          null,
   ALERTESUPPR          BOOL                 null,
   constraint PK_ALERTE primary key (ALERTEID)
);

/*==============================================================*/
/* Index : ALERTE_PK                                            */
/*==============================================================*/
create unique index ALERTE_PK on ALERTE (
ALERTEID
);

/*==============================================================*/
/* Table : ALERTEMAT                                            */
/*==============================================================*/
create table ALERTEMAT (
   ALERTEID             INT4                 not null,
   MATID                INT4                 not null,
   QTEMAT               INT4                 null,
   constraint PK_ALERTEMAT primary key (ALERTEID, MATID)
);

/*==============================================================*/
/* Index : ALERTEMAT_PK                                         */
/*==============================================================*/
create unique index ALERTEMAT_PK on ALERTEMAT (
ALERTEID,
MATID
);

/*==============================================================*/
/* Index : ALERTEMAT_FK                                         */
/*==============================================================*/
create  index ALERTEMAT_FK on ALERTEMAT (
ALERTEID
);

/*==============================================================*/
/* Index : ALERTEMAT2_FK                                        */
/*==============================================================*/
create  index ALERTEMAT2_FK on ALERTEMAT (
MATID
);

/*==============================================================*/
/* Table : BONATIRER                                            */
/*==============================================================*/
create table BONATIRER (
   BONATIRERID          SERIAL               not null,
   PRODID               INT4                 null,
   BONATIRERDTE         DATE                 null,
   BONATIRERSUPPR       BOOL                 null,
   constraint PK_BONATIRER primary key (BONATIRERID)
);

/*==============================================================*/
/* Index : BONATIRER_PK                                         */
/*==============================================================*/
create unique index BONATIRER_PK on BONATIRER (
BONATIRERID
);

/*==============================================================*/
/* Index : BONATIRERPROD_FK                                     */
/*==============================================================*/
create  index BONATIRERPROD_FK on BONATIRER (
PRODID
);

/*==============================================================*/
/* Table : BONSAJETER                                           */
/*==============================================================*/
create table BONSAJETER (
   BONAJETERID          SERIAL               not null,
   PRODID               INT4                 null,
   BONAJETERDTE         DATE                 null,
   BONAJETERSUPPR       BOOL                 null,
   constraint PK_BONSAJETER primary key (BONAJETERID)
);

/*==============================================================*/
/* Index : BONSAJETER_PK                                        */
/*==============================================================*/
create unique index BONSAJETER_PK on BONSAJETER (
BONAJETERID
);

/*==============================================================*/
/* Index : BONAJETERPROD_FK                                     */
/*==============================================================*/
create  index BONAJETERPROD_FK on BONSAJETER (
PRODID
);

/*==============================================================*/
/* Table : CHAINE                                               */
/*==============================================================*/
create table CHAINE (
   CHAINEID             SERIAL               not null,
   CHAINELIB            VARCHAR(50)          null,
   CHAINEDISPO          BOOL                 null,
   CHAINESUPPR          BOOL                 null,
   constraint PK_CHAINE primary key (CHAINEID)
);

/*==============================================================*/
/* Index : CHAINE_PK                                            */
/*==============================================================*/
create unique index CHAINE_PK on CHAINE (
CHAINEID
);

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table CLIENT (
   CLIID                SERIAL               not null,
   UTIID                INT4                 null,
   UTI_UTIID            INT4                 null,
   CLIRAIS              CHAR(10)             null,
   CLINOM               VARCHAR(50)          null,
   CLIADRESSE           VARCHAR(250)         null,
   CLICP                CHAR(10)             null,
   CLIVILLE             VARCHAR(100)         null,
   CLIPAYS              VARCHAR(50)          null,
   CLITEL               VARCHAR(10)          null,
   CLIFAX               VARCHAR(10)          null,
   CLIMAIL              VARCHAR(100)         null,
   CLIACTIVITE          VARCHAR(254)         null,
   CLISIRET             VARCHAR(20)          null,
   CLICA                INT4                 null,
   CLISITE              VARCHAR(100)         null,
   CLIDG                VARCHAR(100)         null,
   CLIETAT              INT4                 null,
   CLILOGIN             CHAR(50)             null,
   CLIMDP               CHAR(32)             null,
   CLIACCES             BOOL                 null,
   CLISUPPR             BOOL                 null,
   CLIDTELOG            DATE                 null,
   CLIDTEADD            DATE                 null,
   CLIURLTMP            VARCHAR(500)         null,
   CLISIREN             VARCHAR(20)          null,
   CLINAF               VARCHAR(20)          null,
   CLIUNIQID            VARCHAR(500)         null,
   constraint PK_CLIENT primary key (CLIID)
);

/*==============================================================*/
/* Index : CLIENT_PK                                            */
/*==============================================================*/
create unique index CLIENT_PK on CLIENT (
CLIID
);

/*==============================================================*/
/* Index : CLICOMMERCIAL_FK                                     */
/*==============================================================*/
create  index CLICOMMERCIAL_FK on CLIENT (
UTI_UTIID
);

/*==============================================================*/
/* Index : CREACLI_FK                                           */
/*==============================================================*/
create  index CREACLI_FK on CLIENT (
UTIID
);

/*==============================================================*/
/* Table : CMDMAT                                               */
/*==============================================================*/
create table CMDMAT (
   CMDMATID             SERIAL               not null,
   MATID                INT4                 not null,
   FOURID               INT4                 not null,
   DATECMDMAT           VARCHAR(10)          null,
   QTECMDMAT            INT4                 null,
   PUCMDMAT             INT4                 null,
   REFFOURCMDMAT        VARCHAR(254)         null,
   constraint PK_CMDMAT primary key (CMDMATID)
);

/*==============================================================*/
/* Index : CMDMAT_PK                                            */
/*==============================================================*/
create unique index CMDMAT_PK on CMDMAT (
CMDMATID
);

/*==============================================================*/
/* Index : MATCMD_FK                                            */
/*==============================================================*/
create  index MATCMD_FK on CMDMAT (
MATID
);

/*==============================================================*/
/* Index : FOURCMD_FK                                           */
/*==============================================================*/
create  index FOURCMD_FK on CMDMAT (
FOURID
);

/*==============================================================*/
/* Table : COMMANDE                                             */
/*==============================================================*/
create table COMMANDE (
   COMID                SERIAL               not null,
   ENQID                INT4                 null,
   INTERID              INT4                 null,
   DEMANDEID            INT4                 null,
   COMTITRE             VARCHAR(254)         null,
   COMDESC              VARCHAR(500)         null,
   COMDATE              DATE                 null,
   COMDATEPREV          DATE                 null,
   COMETAT              INT4                 null,
   COMRESUME            VARCHAR(1000)        null,
   COMPRODDEB           DATE                 null,
   COMPRODFIN           DATE                 null,
   COMPRIX              INT4                 null,
   COMSUPPR             BOOL                 null,
   COMUNIQID            VARCHAR(500)         null,
   constraint PK_COMMANDE primary key (COMID)
);

/*==============================================================*/
/* Index : COMMANDE_PK                                          */
/*==============================================================*/
create unique index COMMANDE_PK on COMMANDE (
COMID
);

/*==============================================================*/
/* Index : DEMCOM_FK                                            */
/*==============================================================*/
create  index DEMCOM_FK on COMMANDE (
DEMANDEID
);

/*==============================================================*/
/* Index : INTERCOM_FK                                          */
/*==============================================================*/
create  index INTERCOM_FK on COMMANDE (
INTERID
);

/*==============================================================*/
/* Index : ENQCOM_FK                                            */
/*==============================================================*/
create  index ENQCOM_FK on COMMANDE (
ENQID
);

/*==============================================================*/
/* Table : COMNOM                                               */
/*==============================================================*/
create table COMNOM (
   COMID                INT4                 not null,
   NOMID                INT4                 not null,
   COMQTE               INT4                 null,
   constraint PK_COMNOM primary key (COMID, NOMID)
);

/*==============================================================*/
/* Index : COMNOM_PK                                            */
/*==============================================================*/
create unique index COMNOM_PK on COMNOM (
COMID,
NOMID
);

/*==============================================================*/
/* Index : COMNOM_FK                                            */
/*==============================================================*/
create  index COMNOM_FK on COMNOM (
COMID
);

/*==============================================================*/
/* Index : COMNOM2_FK                                           */
/*==============================================================*/
create  index COMNOM2_FK on COMNOM (
NOMID
);

/*==============================================================*/
/* Table : CONTROLLER                                           */
/*==============================================================*/
create table CONTROLLER (
   CTRLID               SERIAL               not null,
   CTRLNOM              VARCHAR(100)         null,
   CTRLLIB              VARCHAR(100)         null,
   CTRLSUPPR            BOOL                 null,
   constraint PK_CONTROLLER primary key (CTRLID)
);

/*==============================================================*/
/* Index : CONTROLLER_PK                                        */
/*==============================================================*/
create unique index CONTROLLER_PK on CONTROLLER (
CTRLID
);

/*==============================================================*/
/* Table : DEMANDE                                              */
/*==============================================================*/
create table DEMANDE (
   DEMANDEID            SERIAL               not null,
   INTERID              INT4                 null,
   CLIID                INT4                 not null,
   UTIID                INT4                 not null,
   DEMANDETITRE         VARCHAR(254)         null,
   DEMANDEDESC          VARCHAR(500)         null,
   DEMANDEETAT          INT4                 null,
   DEMANDEDTEADD        DATE                 null,
   DEMANDESUPPR         BOOL                 null,
   DEMANDEUNIQID        VARCHAR(500)         null,
   constraint PK_DEMANDE primary key (DEMANDEID)
);

/*==============================================================*/
/* Index : DEMANDE_PK                                           */
/*==============================================================*/
create unique index DEMANDE_PK on DEMANDE (
DEMANDEID
);

/*==============================================================*/
/* Index : CREADEM_FK                                           */
/*==============================================================*/
create  index CREADEM_FK on DEMANDE (
UTIID
);

/*==============================================================*/
/* Index : DEMCLI_FK                                            */
/*==============================================================*/
create  index DEMCLI_FK on DEMANDE (
CLIID
);

/*==============================================================*/
/* Index : INTERDEM_FK                                          */
/*==============================================================*/
create  index INTERDEM_FK on DEMANDE (
INTERID
);

/*==============================================================*/
/* Table : DEVIS                                                */
/*==============================================================*/
create table DEVIS (
   DEVID                SERIAL               not null,
   DEMANDEID            INT4                 null,
   INTERID              INT4                 null,
   DEVTITRE             VARCHAR(254)         null,
   DEVDESC              VARCHAR(500)         null,
   DEVETAT              VARCHAR(50)          null,
   DEVDATE              DATE                 null,
   DEVPRIX              INT4                 null,
   DEVSUPPR             BOOL                 null,
   DEVUNIQID            VARCHAR(500)         null,
   constraint PK_DEVIS primary key (DEVID)
);

/*==============================================================*/
/* Index : DEVIS_PK                                             */
/*==============================================================*/
create unique index DEVIS_PK on DEVIS (
DEVID
);

/*==============================================================*/
/* Index : DEMDEV_FK                                            */
/*==============================================================*/
create  index DEMDEV_FK on DEVIS (
DEMANDEID
);

/*==============================================================*/
/* Index : INTERDEV_FK                                          */
/*==============================================================*/
create  index INTERDEV_FK on DEVIS (
INTERID
);

/*==============================================================*/
/* Table : DEVNOM                                               */
/*==============================================================*/
create table DEVNOM (
   NOMID                INT4                 not null,
   DEVID                INT4                 not null,
   DEVNOMQTE            INT4                 null,
   constraint PK_DEVNOM primary key (NOMID, DEVID)
);

/*==============================================================*/
/* Index : DEVNOM_PK                                            */
/*==============================================================*/
create unique index DEVNOM_PK on DEVNOM (
NOMID,
DEVID
);

/*==============================================================*/
/* Index : DEVNOM_FK                                            */
/*==============================================================*/
create  index DEVNOM_FK on DEVNOM (
NOMID
);

/*==============================================================*/
/* Index : DEVNOM2_FK                                           */
/*==============================================================*/
create  index DEVNOM2_FK on DEVNOM (
DEVID
);

/*==============================================================*/
/* Table : ENQUETE                                              */
/*==============================================================*/
create table ENQUETE (
   ENQID                SERIAL               not null,
   UTIID                INT4                 null,
   INTERID              INT4                 null,
   ENQDOS               VARCHAR(50)          null,
   ENQIDDOS             INT4                 null,
   ENQDTE               DATE                 null,
   ENQINT               VARCHAR(50)          null,
   ENQDESC              VARCHAR(500)         null,
   ENQPOS               BOOL                 null,
   ENQTYPE              BOOL                 null,
   ENQSUPPR             BOOL                 null,
   ENQUNIQID            VARCHAR(500)         null,
   constraint PK_ENQUETE primary key (ENQID)
);

/*==============================================================*/
/* Index : ENQUETE_PK                                           */
/*==============================================================*/
create unique index ENQUETE_PK on ENQUETE (
ENQID
);

/*==============================================================*/
/* Index : ENQUTI_FK                                            */
/*==============================================================*/
create  index ENQUTI_FK on ENQUETE (
UTIID
);

/*==============================================================*/
/* Index : ENQINTER_FK                                          */
/*==============================================================*/
create  index ENQINTER_FK on ENQUETE (
INTERID
);

/*==============================================================*/
/* Table : FONCTION                                             */
/*==============================================================*/
create table FONCTION (
   FONCID               SERIAL               not null,
   FONCLIB              VARCHAR(50)          null,
   FONCSUPPR            BOOL                 null,
   constraint PK_FONCTION primary key (FONCID)
);

/*==============================================================*/
/* Index : FONCTION_PK                                          */
/*==============================================================*/
create unique index FONCTION_PK on FONCTION (
FONCID
);

/*==============================================================*/
/* Table : FONCUTI                                              */
/*==============================================================*/
create table FONCUTI (
   UTIID                INT4                 not null,
   FONCID               INT4                 not null,
   constraint PK_FONCUTI primary key (UTIID, FONCID)
);

/*==============================================================*/
/* Index : FONCUTI_PK                                           */
/*==============================================================*/
create unique index FONCUTI_PK on FONCUTI (
UTIID,
FONCID
);

/*==============================================================*/
/* Index : FONCUTI_FK                                           */
/*==============================================================*/
create  index FONCUTI_FK on FONCUTI (
UTIID
);

/*==============================================================*/
/* Index : FONCUTI2_FK                                          */
/*==============================================================*/
create  index FONCUTI2_FK on FONCUTI (
FONCID
);

/*==============================================================*/
/* Table : FOURNISSEUR                                          */
/*==============================================================*/
create table FOURNISSEUR (
   FOURID               SERIAL               not null,
   FOURRAIS             VARCHAR(10)          null,
   FOURNOM              VARCHAR(50)          null,
   FOURADRESSE          VARCHAR(250)         null,
   FOURCP               VARCHAR(10)          null,
   FOURVILLE            VARCHAR(100)         null,
   FOURSUPPR            BOOL                 null,
   constraint PK_FOURNISSEUR primary key (FOURID)
);

/*==============================================================*/
/* Index : FOURNISSEUR_PK                                       */
/*==============================================================*/
create unique index FOURNISSEUR_PK on FOURNISSEUR (
FOURID
);

/*==============================================================*/
/* Table : HISTOSTOCK                                           */
/*==============================================================*/
create table HISTOSTOCK (
   HISTOID              SERIAL               not null,
   STOCKID              INT4                 not null,
   COMID                INT4                 not null,
   HISTODTE             VARCHAR(10)          null,
   HISTOQTE             INT4                 null,
   HISTOSUPPR           BOOL                 null,
   constraint PK_HISTOSTOCK primary key (HISTOID)
);

/*==============================================================*/
/* Index : HISTOSTOCK_PK                                        */
/*==============================================================*/
create unique index HISTOSTOCK_PK on HISTOSTOCK (
HISTOID
);

/*==============================================================*/
/* Index : STOCKHISTO_FK                                        */
/*==============================================================*/
create  index STOCKHISTO_FK on HISTOSTOCK (
STOCKID
);

/*==============================================================*/
/* Index : CMDHISTO_FK                                          */
/*==============================================================*/
create  index CMDHISTO_FK on HISTOSTOCK (
COMID
);

/*==============================================================*/
/* Table : INTERLOCUTEUR                                        */
/*==============================================================*/
create table INTERLOCUTEUR (
   INTERID              SERIAL               not null,
   FOURID               INT4                 null,
   UTIID                INT4                 null,
   CLIID                INT4                 null,
   INTERNOM             VARCHAR(50)          null,
   INTERPRENOM          VARCHAR(50)          null,
   INTERMAIL            VARCHAR(50)          null,
   INTERTEL             VARCHAR(20)          null,
   INTERPOSTE           VARCHAR(100)         null,
   INTERDTEADD          DATE                 null,
   INTERSUPPR           BOOL                 null,
   INTERUNIQID          VARCHAR(500)         null,
   constraint PK_INTERLOCUTEUR primary key (INTERID)
);

/*==============================================================*/
/* Index : INTERLOCUTEUR_PK                                     */
/*==============================================================*/
create unique index INTERLOCUTEUR_PK on INTERLOCUTEUR (
INTERID
);

/*==============================================================*/
/* Index : CLIINTER_FK                                          */
/*==============================================================*/
create  index CLIINTER_FK on INTERLOCUTEUR (
CLIID
);

/*==============================================================*/
/* Index : FOURINTER_FK                                         */
/*==============================================================*/
create  index FOURINTER_FK on INTERLOCUTEUR (
FOURID
);

/*==============================================================*/
/* Index : CREAINTER_FK                                         */
/*==============================================================*/
create  index CREAINTER_FK on INTERLOCUTEUR (
UTIID
);

/*==============================================================*/
/* Table : MATIERE                                              */
/*==============================================================*/
create table MATIERE (
   MATID                SERIAL               not null,
   UNITEID              INT4                 null,
   MATLIB               VARCHAR(100)         null,
   MATSUPPR             BOOL                 null,
   constraint PK_MATIERE primary key (MATID)
);

/*==============================================================*/
/* Index : MATIERE_PK                                           */
/*==============================================================*/
create unique index MATIERE_PK on MATIERE (
MATID
);

/*==============================================================*/
/* Index : MATUNITE_FK                                          */
/*==============================================================*/
create  index MATUNITE_FK on MATIERE (
UNITEID
);

/*==============================================================*/
/* Table : NOMENCLATURE                                         */
/*==============================================================*/
create table NOMENCLATURE (
   NOMID                SERIAL               not null,
   NOMLIB               VARCHAR(50)          null,
   NOMDATE              DATE                 null,
   NOMTEMPS             CHAR(10)             null,
   NOMNBCHAINE          INT4                 null,
   NOMQR                VARCHAR(100)         null,
   NOMPRIX              INT4                 null,
   NOMDES               VARCHAR(250)         null,
   NOMSUPPR             BOOL                 null,
   constraint PK_NOMENCLATURE primary key (NOMID)
);

/*==============================================================*/
/* Index : NOMENCLATURE_PK                                      */
/*==============================================================*/
create unique index NOMENCLATURE_PK on NOMENCLATURE (
NOMID
);

/*==============================================================*/
/* Table : NOMMAT                                               */
/*==============================================================*/
create table NOMMAT (
   NOMID                INT4                 not null,
   MATID                INT4                 not null,
   MATQTE               INT4                 null,
   constraint PK_NOMMAT primary key (NOMID, MATID)
);

/*==============================================================*/
/* Index : NOMMAT_PK                                            */
/*==============================================================*/
create unique index NOMMAT_PK on NOMMAT (
NOMID,
MATID
);

/*==============================================================*/
/* Index : NOMMAT_FK                                            */
/*==============================================================*/
create  index NOMMAT_FK on NOMMAT (
NOMID
);

/*==============================================================*/
/* Index : NOMMAT2_FK                                           */
/*==============================================================*/
create  index NOMMAT2_FK on NOMMAT (
MATID
);

/*==============================================================*/
/* Table : PRODUCTION                                           */
/*==============================================================*/
create table PRODUCTION (
   PRODID               SERIAL               not null,
   COMID                INT4                 null,
   BONAJETERID          INT4                 null,
   BONATIRERID          INT4                 null,
   CHAINEID             INT4                 null,
   CHAINEDATEDEB        DATE                 null,
   CHAINEDATEFIN        DATE                 null,
   QRCODE               CHAR(100)            null,
   PRODSUPPR            BOOL                 null,
   constraint PK_PRODUCTION primary key (PRODID)
);

/*==============================================================*/
/* Index : PRODUCTION_PK                                        */
/*==============================================================*/
create unique index PRODUCTION_PK on PRODUCTION (
PRODID
);

/*==============================================================*/
/* Index : COMPROD_FK                                           */
/*==============================================================*/
create  index COMPROD_FK on PRODUCTION (
COMID
);

/*==============================================================*/
/* Index : CHAINEPROD_FK                                        */
/*==============================================================*/
create  index CHAINEPROD_FK on PRODUCTION (
CHAINEID
);

/*==============================================================*/
/* Index : BONAJETERPROD2_FK                                    */
/*==============================================================*/
create  index BONAJETERPROD2_FK on PRODUCTION (
BONAJETERID
);

/*==============================================================*/
/* Index : BONATIRERPROD2_FK                                    */
/*==============================================================*/
create  index BONATIRERPROD2_FK on PRODUCTION (
BONATIRERID
);

/*==============================================================*/
/* Table : PROFACTION                                           */
/*==============================================================*/
create table PROFACTION (
   ACTIONID             INT4                 not null,
   PROFID               INT4                 not null,
   constraint PK_PROFACTION primary key (ACTIONID, PROFID)
);

/*==============================================================*/
/* Index : PROFACTION_PK                                        */
/*==============================================================*/
create unique index PROFACTION_PK on PROFACTION (
ACTIONID,
PROFID
);

/*==============================================================*/
/* Index : PROFACTION_FK                                        */
/*==============================================================*/
create  index PROFACTION_FK on PROFACTION (
ACTIONID
);

/*==============================================================*/
/* Index : PROFACTION2_FK                                       */
/*==============================================================*/
create  index PROFACTION2_FK on PROFACTION (
PROFID
);

/*==============================================================*/
/* Table : PROFIL                                               */
/*==============================================================*/
create table PROFIL (
   PROFID               SERIAL               not null,
   PROFLIB              VARCHAR(100)         null,
   PROFSUPPR            BOOL                 null,
   constraint PK_PROFIL primary key (PROFID)
);

/*==============================================================*/
/* Index : PROFIL_PK                                            */
/*==============================================================*/
create unique index PROFIL_PK on PROFIL (
PROFID
);

/*==============================================================*/
/* Table : STOCK                                                */
/*==============================================================*/
create table STOCK (
   STOCKID              SERIAL               not null,
   INTERID              INT4                 null,
   MATID                INT4                 null,
   STOCKDATECOM         VARCHAR(10)          null,
   STOCKENTREE          VARCHAR(10)          null,
   STOCKSORTIE          VARCHAR(10)          null,
   STOCKQTE             INT4                 null,
   STOCKREFFOUR         VARCHAR(100)         null,
   STOCKPU              INT4                 null,
   STOCKSUPPR           BOOL                 null,
   constraint PK_STOCK primary key (STOCKID)
);

/*==============================================================*/
/* Index : STOCK_PK                                             */
/*==============================================================*/
create unique index STOCK_PK on STOCK (
STOCKID
);

/*==============================================================*/
/* Index : FOURSTOCK_FK                                         */
/*==============================================================*/
create  index FOURSTOCK_FK on STOCK (
INTERID
);

/*==============================================================*/
/* Index : MATSTOCK_FK                                          */
/*==============================================================*/
create  index MATSTOCK_FK on STOCK (
MATID
);

/*==============================================================*/
/* Table : SUIVDOSSIER                                          */
/*==============================================================*/
create table SUIVDOSSIER (
   SUIVDOSID            SERIAL               not null,
   COMID                INT4                 null,
   INTERID              INT4                 null,
   DEMANDEID            INT4                 null,
   DEVID                INT4                 null,
   UTIID                INT4                 null,
   SUIVDOSDATE          DATE                 null,
   SUIVDOSCOM           VARCHAR(1000)        null,
   SUIVIDOSSUPPR        BOOL                 null,
   SUIVDOSUNIQID        VARCHAR(1)           null,
   constraint PK_SUIVDOSSIER primary key (SUIVDOSID)
);

/*==============================================================*/
/* Index : SUIVDOSSIER_PK                                       */
/*==============================================================*/
create unique index SUIVDOSSIER_PK on SUIVDOSSIER (
SUIVDOSID
);

/*==============================================================*/
/* Index : INTERSUIVDOS_FK                                      */
/*==============================================================*/
create  index INTERSUIVDOS_FK on SUIVDOSSIER (
INTERID
);

/*==============================================================*/
/* Index : DEVSUIVDOS_FK                                        */
/*==============================================================*/
create  index DEVSUIVDOS_FK on SUIVDOSSIER (
DEVID
);

/*==============================================================*/
/* Index : UTISUIVDOS_FK                                        */
/*==============================================================*/
create  index UTISUIVDOS_FK on SUIVDOSSIER (
UTIID
);

/*==============================================================*/
/* Index : COMSUIVDOS_FK                                        */
/*==============================================================*/
create  index COMSUIVDOS_FK on SUIVDOSSIER (
COMID
);

/*==============================================================*/
/* Index : DEMSUIVDOS_FK                                        */
/*==============================================================*/
create  index DEMSUIVDOS_FK on SUIVDOSSIER (
DEMANDEID
);

/*==============================================================*/
/* Table : UNITE                                                */
/*==============================================================*/
create table UNITE (
   UNITEID              SERIAL               not null,
   UNITELBL             VARCHAR(50)          null,
   UNITESUPPR           BOOL                 null,
   constraint PK_UNITE primary key (UNITEID)
);

/*==============================================================*/
/* Index : UNITE_PK                                             */
/*==============================================================*/
create unique index UNITE_PK on UNITE (
UNITEID
);

/*==============================================================*/
/* Table : UTILISATEUR                                          */
/*==============================================================*/
create table UTILISATEUR (
   UTIID                SERIAL               not null,
   UTINOM               VARCHAR(50)          null,
   UTIPRENOM            VARCHAR(50)          null,
   UTILOGIN             VARCHAR(50)          null,
   UTIMDP               VARCHAR(32)          null,
   UTIMAIL              VARCHAR(50)          null,
   UTITEL               VARCHAR(20)          null,
   UTISUPPR             BOOL                 null,
   UTIDTELOG            DATE                 null,
   constraint PK_UTILISATEUR primary key (UTIID)
);

/*==============================================================*/
/* Index : UTILISATEUR_PK                                       */
/*==============================================================*/
create unique index UTILISATEUR_PK on UTILISATEUR (
UTIID
);

/*==============================================================*/
/* Table : UTIPROF                                              */
/*==============================================================*/
create table UTIPROF (
   UTIID                INT4                 not null,
   PROFID               INT4                 not null,
   constraint PK_UTIPROF primary key (UTIID, PROFID)
);

/*==============================================================*/
/* Index : UTIPROF_PK                                           */
/*==============================================================*/
create unique index UTIPROF_PK on UTIPROF (
UTIID,
PROFID
);

/*==============================================================*/
/* Index : UTIPROF_FK                                           */
/*==============================================================*/
create  index UTIPROF_FK on UTIPROF (
UTIID
);

/*==============================================================*/
/* Index : UTIPROF2_FK                                          */
/*==============================================================*/
create  index UTIPROF2_FK on UTIPROF (
PROFID
);

alter table ACTION
   add constraint FK_ACTION_CTRLACTIO_CONTROLL foreign key (CTRLID)
      references CONTROLLER (CTRLID)
      on delete restrict on update restrict;

alter table AGENDA
   add constraint FK_AGENDA_UTIAGE_UTILISAT foreign key (UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table ALERTEMAT
   add constraint FK_ALERTEMA_ALERTEMAT_ALERTE foreign key (ALERTEID)
      references ALERTE (ALERTEID)
      on delete restrict on update restrict;

alter table ALERTEMAT
   add constraint FK_ALERTEMA_ALERTEMAT_MATIERE foreign key (MATID)
      references MATIERE (MATID)
      on delete restrict on update restrict;

alter table BONATIRER
   add constraint FK_BONATIRE_BONATIRER_PRODUCTI foreign key (PRODID)
      references PRODUCTION (PRODID)
      on delete restrict on update restrict;

alter table BONSAJETER
   add constraint FK_BONSAJET_BONAJETER_PRODUCTI foreign key (PRODID)
      references PRODUCTION (PRODID)
      on delete restrict on update restrict;

alter table CLIENT
   add constraint FK_CLIENT_CLICOMMER_UTILISAT foreign key (UTI_UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table CLIENT
   add constraint FK_CLIENT_CREACLI_UTILISAT foreign key (UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table CMDMAT
   add constraint FK_CMDMAT_FOURCMD_FOURNISS foreign key (FOURID)
      references FOURNISSEUR (FOURID)
      on delete restrict on update restrict;

alter table CMDMAT
   add constraint FK_CMDMAT_MATCMD_MATIERE foreign key (MATID)
      references MATIERE (MATID)
      on delete restrict on update restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_DEMCOM_DEMANDE foreign key (DEMANDEID)
      references DEMANDE (DEMANDEID)
      on delete restrict on update restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_ENQCOM_ENQUETE foreign key (ENQID)
      references ENQUETE (ENQID)
      on delete restrict on update restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_INTERCOM_INTERLOC foreign key (INTERID)
      references INTERLOCUTEUR (INTERID)
      on delete restrict on update restrict;

alter table COMNOM
   add constraint FK_COMNOM_COMNOM_COMMANDE foreign key (COMID)
      references COMMANDE (COMID)
      on delete restrict on update restrict;

alter table COMNOM
   add constraint FK_COMNOM_COMNOM2_NOMENCLA foreign key (NOMID)
      references NOMENCLATURE (NOMID)
      on delete restrict on update restrict;

alter table DEMANDE
   add constraint FK_DEMANDE_CREADEM_UTILISAT foreign key (UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table DEMANDE
   add constraint FK_DEMANDE_DEMCLI_CLIENT foreign key (CLIID)
      references CLIENT (CLIID)
      on delete restrict on update restrict;

alter table DEMANDE
   add constraint FK_DEMANDE_INTERDEM_INTERLOC foreign key (INTERID)
      references INTERLOCUTEUR (INTERID)
      on delete restrict on update restrict;

alter table DEVIS
   add constraint FK_DEVIS_DEMDEV_DEMANDE foreign key (DEMANDEID)
      references DEMANDE (DEMANDEID)
      on delete restrict on update restrict;

alter table DEVIS
   add constraint FK_DEVIS_INTERDEV_INTERLOC foreign key (INTERID)
      references INTERLOCUTEUR (INTERID)
      on delete restrict on update restrict;

alter table DEVNOM
   add constraint FK_DEVNOM_DEVNOM_NOMENCLA foreign key (NOMID)
      references NOMENCLATURE (NOMID)
      on delete restrict on update restrict;

alter table DEVNOM
   add constraint FK_DEVNOM_DEVNOM2_DEVIS foreign key (DEVID)
      references DEVIS (DEVID)
      on delete restrict on update restrict;

alter table ENQUETE
   add constraint FK_ENQUETE_ENQINTER_INTERLOC foreign key (INTERID)
      references INTERLOCUTEUR (INTERID)
      on delete restrict on update restrict;

alter table ENQUETE
   add constraint FK_ENQUETE_ENQUTI_UTILISAT foreign key (UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table FONCUTI
   add constraint FK_FONCUTI_FONCUTI_UTILISAT foreign key (UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table FONCUTI
   add constraint FK_FONCUTI_FONCUTI2_FONCTION foreign key (FONCID)
      references FONCTION (FONCID)
      on delete restrict on update restrict;

alter table HISTOSTOCK
   add constraint FK_HISTOSTO_CMDHISTO_COMMANDE foreign key (COMID)
      references COMMANDE (COMID)
      on delete restrict on update restrict;

alter table HISTOSTOCK
   add constraint FK_HISTOSTO_STOCKHIST_STOCK foreign key (STOCKID)
      references STOCK (STOCKID)
      on delete restrict on update restrict;

alter table INTERLOCUTEUR
   add constraint FK_INTERLOC_CLIINTER_CLIENT foreign key (CLIID)
      references CLIENT (CLIID)
      on delete restrict on update restrict;

alter table INTERLOCUTEUR
   add constraint FK_INTERLOC_CREAINTER_UTILISAT foreign key (UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table INTERLOCUTEUR
   add constraint FK_INTERLOC_FOURINTER_FOURNISS foreign key (FOURID)
      references FOURNISSEUR (FOURID)
      on delete restrict on update restrict;

alter table MATIERE
   add constraint FK_MATIERE_MATUNITE_UNITE foreign key (UNITEID)
      references UNITE (UNITEID)
      on delete restrict on update restrict;

alter table NOMMAT
   add constraint FK_NOMMAT_NOMMAT_NOMENCLA foreign key (NOMID)
      references NOMENCLATURE (NOMID)
      on delete restrict on update restrict;

alter table NOMMAT
   add constraint FK_NOMMAT_NOMMAT2_MATIERE foreign key (MATID)
      references MATIERE (MATID)
      on delete restrict on update restrict;

alter table PRODUCTION
   add constraint FK_PRODUCTI_BONAJETER_BONSAJET foreign key (BONAJETERID)
      references BONSAJETER (BONAJETERID)
      on delete restrict on update restrict;

alter table PRODUCTION
   add constraint FK_PRODUCTI_BONATIRER_BONATIRE foreign key (BONATIRERID)
      references BONATIRER (BONATIRERID)
      on delete restrict on update restrict;

alter table PRODUCTION
   add constraint FK_PRODUCTI_CHAINEPRO_CHAINE foreign key (CHAINEID)
      references CHAINE (CHAINEID)
      on delete restrict on update restrict;

alter table PRODUCTION
   add constraint FK_PRODUCTI_COMPROD_COMMANDE foreign key (COMID)
      references COMMANDE (COMID)
      on delete restrict on update restrict;

alter table PROFACTION
   add constraint FK_PROFACTI_PROFACTIO_ACTION foreign key (ACTIONID)
      references ACTION (ACTIONID)
      on delete restrict on update restrict;

alter table PROFACTION
   add constraint FK_PROFACTI_PROFACTIO_PROFIL foreign key (PROFID)
      references PROFIL (PROFID)
      on delete restrict on update restrict;

alter table STOCK
   add constraint FK_STOCK_FOURSTOCK_INTERLOC foreign key (INTERID)
      references INTERLOCUTEUR (INTERID)
      on delete restrict on update restrict;

alter table STOCK
   add constraint FK_STOCK_MATSTOCK_MATIERE foreign key (MATID)
      references MATIERE (MATID)
      on delete restrict on update restrict;

alter table SUIVDOSSIER
   add constraint FK_SUIVDOSS_COMSUIVDO_COMMANDE foreign key (COMID)
      references COMMANDE (COMID)
      on delete restrict on update restrict;

alter table SUIVDOSSIER
   add constraint FK_SUIVDOSS_DEMSUIVDO_DEMANDE foreign key (DEMANDEID)
      references DEMANDE (DEMANDEID)
      on delete restrict on update restrict;

alter table SUIVDOSSIER
   add constraint FK_SUIVDOSS_DEVSUIVDO_DEVIS foreign key (DEVID)
      references DEVIS (DEVID)
      on delete restrict on update restrict;

alter table SUIVDOSSIER
   add constraint FK_SUIVDOSS_INTERSUIV_INTERLOC foreign key (INTERID)
      references INTERLOCUTEUR (INTERID)
      on delete restrict on update restrict;

alter table SUIVDOSSIER
   add constraint FK_SUIVDOSS_UTISUIVDO_UTILISAT foreign key (UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table UTIPROF
   add constraint FK_UTIPROF_UTIPROF_UTILISAT foreign key (UTIID)
      references UTILISATEUR (UTIID)
      on delete restrict on update restrict;

alter table UTIPROF
   add constraint FK_UTIPROF_UTIPROF2_PROFIL foreign key (PROFID)
      references PROFIL (PROFID)
      on delete restrict on update restrict;

