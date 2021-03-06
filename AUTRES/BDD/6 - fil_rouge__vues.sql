CREATE OR REPLACE VIEW detailbonatirer AS 
SELECT b.bonatirerid, b.bonatirerdte, b.bonatirersuppr, p.prodid, p.bonajeterid, p.chainedatedeb, p.chainedatefin, p.qrcode, p.prodsuppr, c.chaineid, c.chainelib, c.chainedispo, c.chainesuppr, d.comid, d.comdate, d.comdateprev, d.cometat, d.comproddeb, d.comprodfin, d.comprix, d.comsuppr
FROM bonatirer b, production p, chaine c, commande d
WHERE p.bonatirerid = b.bonatirerid AND p.chaineid = c.chaineid AND p.comid = d.comid;


CREATE OR REPLACE VIEW detailcommande AS 
SELECT c.comid, c.interid, c.demandeid, c.comtitre, c.comdesc, c.comdate, c.cometat, c.comprix, c.comsuppr,
i.internom, i.interprenom, i.intersuppr,
l.cliid, l.clirais, l.clinom, l.clisuppr,
u.utiid, u.utinom, u.utiprenom, u.utisuppr
FROM commande AS c , client AS l , interlocuteur AS i , utilisateur AS u , demande AS d
WHERE c.interid = i.interid AND c.demandeid = d.demandeid AND d.cliid = l.cliid AND d.utiid = u.utiid;


CREATE OR REPLACE VIEW detailclient AS 
SELECT i.internom, i.interprenom, i.intersuppr, l.cliid, l.clirais, l.clinom, l.clisuppr, u.utiid, u.utinom, u.utiprenom, u.utisuppr
FROM client AS l , interlocuteur AS i , utilisateur AS u
WHERE i.cliid = l.cliid AND i.utiid = u.utiid;


CREATE OR REPLACE VIEW detaildevis AS 
SELECT d.devid, d.devdate, d.devetat, d.devprix, d.devsuppr, l.cliid, l.clirais, l.clinom, l.cliadresse, l.clicp, l.cliville, l.clietat,
l.clilogin,  l.climdp, l.cliacces, l.clisuppr, i.internom, i.interprenom, i.intermail, i.intertel, i.intersuppr
FROM devis d, client l, interlocuteur i
WHERE d.interid = i.interid AND i.cliid = l.cliid;

/*
CREATE OR REPLACE VIEW detaildevis AS 
SELECT d.devid, d.devdate, d.devprix, d.devsuppr, l.cliid, l.clirais, l.clinom, l.cliadresse, l.clicp, l.cliville, l.clietat,
l.clilogin,  l.climdp, l.cliacces, l.clisuppr, i.internom, i.interprenom, i.intermail, i.intertel, i.intersuppr, s.suivdosid, s.suividossuppr
FROM devis d, client l, suivdossier s, interlocuteur i
WHERE d.devid = s.devid AND s.interid = i.interid AND i.cliid = l.cliid;
*/

CREATE OR REPLACE VIEW calcultempsnom AS 
SELECT nomenclature.nomtemps, devnom.devnomqte AS comqte, devnom.nomid, commande.comid FROM (((commande JOIN devis ON ((commande.demandeid = devis.demandeid))) JOIN devnom ON ((devis.devid = devnom.devid))) JOIN nomenclature ON ((devnom.nomid = nomenclature.nomid)));

CREATE OR REPLACE VIEW client_comm AS 
 SELECT c.cliid, c.utiid, c.uti_utiid, c.clirais, c.clinom, c.clidteadd, C.cliacces, c.cliadresse, c.clicp, c.cliville, c.clisuppr, u.utinom, u.utiprenom FROM client c, utilisateur u WHERE c.utiid = u.utiid;

CREATE OR REPLACE VIEW actctrl AS
SELECT c.ctrlid, p.profid, c.ctrlnom, c.ctrllib, a.actionid, a.actionnom, a.actionlib FROM profil as p, controller as c, action as a, profaction as s WHERE p.profid=s.profid AND a.actionid = s.actionid AND a.ctrlid = c.ctrlid AND c.ctrlsuppr = false AND a.actionsuppr = false;
;
CREATE OR REPLACE VIEW controller_action AS
SELECT c.ctrlid, c.ctrlnom, c.ctrllib, a.actionid, a.actionnom, a.actionlib FROM controller c, action a WHERE (((a.ctrlid = c.ctrlid) AND (c.ctrlsuppr = false)) AND (a.actionsuppr = false));

CREATE OR REPLACE VIEW qtestock AS
SELECT s.stockqte, j.matid, s.stocksortie FROM stock as s, matiere as j WHERE s.matid = j.matid AND j.matsuppr = false AND s.stocksuppr = false;

CREATE OR REPLACE VIEW seuilmat AS
SELECT a.alertelbl, h.qtemat, h.matid FROM alerte as a, alertemat as h WHERE a.alerteid = h.alerteid AND a.alertesuppr = false;

CREATE OR REPLACE VIEW cmd_mat AS 
SELECT c.cmdmatid, m.matlib, m.uniteid, u.unitelbl, f.fournom, c.datecmdmat, c.qtecmdmat
FROM cmdmat c, matiere m, fournisseur f, unite u
WHERE c.matid = m.matid AND c.fourid=f.fourid AND m.uniteid = u.uniteid;

CREATE OR REPLACE VIEW frninter AS 
SELECT f.fourid, f.fourrais, f.fournom, f.fouradresse, f.fourcp, f.fourville, i.interid, i.internom, i.interprenom, i.intermail, i.intertel
FROM fournisseur f, interlocuteur i
WHERE i.fourid = f.fourid AND f.foursuppr = false AND i.intersuppr = false;

CREATE OR REPLACE VIEW matunite AS
SELECT u.unitelbl, m.uniteid, m.matid, m.matlib, m.matsuppr
FROM unite u, matiere m
WHERE u.uniteid = m.uniteid;

CREATE OR REPLACE VIEW getinter AS
SELECT i.interid, i.internom, i.interprenom, i.intermail, i.intertel, i.interposte, i.interdteadd, i.intersuppr, i.utiid, u.utinom, u.utiprenom, u.utilogin, u.utimdp, u.utimail, u.utitel, u.utidtelog, u.utisuppr,
c.clirais, c.clinom, c.cliadresse, c.clicp, c.cliville, c.clipays, c.clitel, c.clifax, c.climail, c.cliactivite, c.clisiret, c.clica, c.clisite, c.clidg,
c.clietat, c.clilogin, c.climdp, c.cliacces, c.clisuppr, c.clidteadd, c.clidtelog 
FROM utilisateur u, client c, interlocuteur i
WHERE u.utiid = i.utiid AND c.cliid = i.cliid;

CREATE OR REPLACE VIEW getalerte AS
SELECT 	s.suivdosid, s.comid, i.interid, i.internom, i.interprenom, l.cliid, l.clinom, s.suivdoscom, s.suivdosdate, s.utiid, s.suividossuppr
FROM suivdossier s, interlocuteur i,  client l
WHERE s.interid = i.interid AND i.cliid = l.cliid;

CREATE OR REPLACE VIEW "public"."detailsdemande" AS 
SELECT
"public".demande.demandeid,
"public".client.cliid,
"public".client.clirais,
"public".demande.demandeetat,
"public".utilisateur.utinom,
"public".utilisateur.utiprenom,
"public".client.clinom,
"public".client.cliville,
"public".client.clitel,
"public".client.cliactivite,
"public".client.clica,
"public".demande.demandetitre,
"public".demande.demandedesc
FROM
((("public".client
JOIN "public".demande ON (("public".demande.cliid = "public".client.cliid))))
JOIN "public".utilisateur ON (("public".client.utiid = "public".utilisateur.utiid)))
;

CREATE OR REPLACE VIEW "public"."listnom" AS 
SELECT
"public".nomenclature.nomlib,
"public".nomenclature.nomdes,
"public".nomenclature.nomprix,
"public".nomenclature.nomid, 
"public".devnom.devid, 
"public".devnom.devnomqte
FROM (devnom JOIN nomenclature ON ((nomenclature.nomid = devnom.nomid)))
;

CREATE OR REPLACE VIEW getreporting AS
SELECT e.enqid, e.utiid, e.interid, i.interuniqid, c.cliid, c.cliuniqid, e.enqdos, e.enqiddos, e.enqdte, e.enqint, e.enqdesc, e.enqpos, e.enqtype, e.enquniqid
FROM enquete e, interlocuteur i, client c
WHERE e.interid = i.interid AND i.cliid = c.cliid AND e.enqsuppr = false;

CREATE OR REPLACE VIEW getreportcount AS
SELECT COUNT(*), cliid, enqpos FROM getreporting GROUP BY enqdte, cliid, enqpos ORDER BY enqdte DESC LIMIT 10;
