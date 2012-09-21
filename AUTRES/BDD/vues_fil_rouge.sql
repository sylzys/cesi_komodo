CREATE OR REPLACE VIEW detailbonatirer AS 
SELECT b.bonatirerid, b.bonatirerdte, b.bonatirersuppr, p.prodid, p.bonajeterid, p.chainedatedeb, p.chainedatefin, p.qrcode, p.prodsuppr, c.chaineid, c.chainelib, c.chainedispo, c.chainesuppr, d.comid, d.comdate, d.comdateprev, d.cometat, d.comproddeb, d.comprodfin, d.comprix, d.comsuppr
FROM bonatirer b, production p, chaine c, commande d
WHERE p.bonatirerid = b.bonatirerid AND p.chaineid = c.chaineid AND p.comid = d.comid;

CREATE OR REPLACE VIEW detailcommande AS 
SELECT c.comid, c.comdate, c.comdateprev, c.cometat, c.comproddeb, c.comprodfin, c.comprix, c.comsuppr, l.cliid, l.clirais, l.clinom, l.cliadresse, l.clicp, l.cliville, l.clietat,
l.clilogin,  l.climdp, l.cliacces, l.clisuppr, i.internom, i.interprenom, i.intermail, i.intertel, i.intersuppr, s.suivdosid, s.suividossuppr
FROM commande c, client l, suivdossier s, interlocuteur i
WHERE s.comid = c.comid AND l.cliid = i.cliid AND s.interid = i.interid;

CREATE OR REPLACE VIEW detaildevis AS 
SELECT d.devid, d.devdate, d.devprix, d.devsuppr, l.cliid, l.clirais, l.clinom, l.cliadresse, l.clicp, l.cliville, l.clietat,
l.clilogin,  l.climdp, l.cliacces, l.clisuppr, i.internom, i.interprenom, i.intermail, i.intertel, i.intersuppr, s.suivdosid, s.suividossuppr
FROM devis d, client l, suivdossier s, interlocuteur i
WHERE d.devid = s.devid AND s.interid = i.interid AND i.cliid = l.cliid;

CREATE OR REPLACE VIEW calcultempsnom AS 
SELECT c.comid, c.comqte, n.nomid, n.nomtemps FROM comnom c, nomenclature n WHERE c.nomid = n.nomid;

CREATE OR REPLACE VIEW client_comm AS 
SELECT c.cliid, c.utiid, c.clirais, c.clinom, c.cliadresse, c.clicp, c.cliville, c.clisuppr, u.utinom, u.utiprenom
FROM client c, utilisateur u
WHERE c.utiid = u.utiid;

CREATE OR REPLACE VIEW actctrl AS
SELECT c.ctrlid, p.profid, c.ctrlnom, c.ctrllib, a.actionid, a.actionnom, a.actionlib FROM profil as p, controller as c, action as a, profaction as s WHERE p.profid=s.profid AND a.actionid = s.actionid AND a.ctrlid = c.ctrlid AND c.ctrlsuppr = false AND a.actionsuppr = false;
;

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
SELECT 	s.comid, i.interid, i.internom, i.interprenom, l.cliid, l.clinom, s.suivdoscom, s.suivdosdate, s.utiid
FROM suivdossier s, interlocuteur i,  client l
WHERE s.interid = i.interid AND i.cliid = l.cliid AND s.suividossuppr = false;
