CREATE OR REPLACE FUNCTION get_clicom(integer) RETURNS SETOF record
AS $$
DECLARE
recclicom RECORD;
BEGIN
FOR recclicom 
IN (SELECT comid FROM commande WHERE comid IN (SELECT comid FROM suivdossier WHERE interid IN (SELECT interid FROM interlocuteur WHERE cliid = $1)))
LOOP
RETURN NEXT recclicom;
END LOOP ;
RETURN ;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_alertemat(integer) RETURNS SETOF record
AS $$
DECLARE
recalertemat RECORD;
BEGIN
FOR recalertemat 
IN (SELECT alerteid, alerteseuil, alertelbl, alertesuppr FROM alerte WHERE alerteid IN (SELECT alerteid FROM alerteStock WHERE stockid IN (SELECT stockid FROM stock WHERE matid = $1)))
LOOP
RETURN NEXT recalertemat;
END LOOP ;
RETURN ;
END;
$$ LANGUAGE plpgsql;
/*SELECT alerteid, alerteseuil, alertelbl, alertesuppr FROM get_alertemat(4) AS (alerteid integer, alerteseuil integer, alertelbl varchar(50), alertesuppr bool);*/

CREATE OR REPLACE FUNCTION get_utiprof(integer) RETURNS SETOF record
AS $$
DECLARE
recutiprof RECORD;
BEGIN
FOR recutiprof  
IN (SELECT profid, proflib FROM profil WHERE profid IN (SELECT profid FROM utiprof WHERE utiid IN (SELECT utiid FROM utiprof WHERE utiid = $1)))
LOOP
RETURN NEXT recutiprof ;
END LOOP ;
RETURN ;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_ressources(integer) RETURNS SETOF record
AS $$
DECLARE
recressources RECORD;
BEGIN
FOR recressources  
IN (SELECT c.ctrlid, c.ctrlnom, a.actionid, a.actionnom FROM profil as p, controller as c, action as a, profaction as s WHERE p.profid=s.profid AND a.actionid = s.actionid AND a.ctrlid = c.ctrlid AND p.profid = $1)
LOOP
RETURN NEXT recressources ;
END LOOP ;
RETURN ;
END;
$$ LANGUAGE plpgsql;
/*SELECT ctrlid, ctrlnom, actionid, actionnom FROM get_ressources(1) AS (ctrlid integer, ctrlnom varchar(100), actionid integer, actionnom varchar(50));*/

CREATE OR REPLACE FUNCTION deletenom(p_idnom INTEGER) RETURNS void
AS $$
DECLARE
BEGIN
	DELETE FROM nommat WHERE nomid = $1;
	UPDATE nomenclature SET nomsuppr = true WHERE nomid = $1;
RETURN;
END;
$$ LANGUAGE plpgsql;
/* select deletenom(1);*/

CREATE OR REPLACE FUNCTION get_notification(integer) RETURNS SETOF record
AS $$
DECLARE
recnotification RECORD;
BEGIN
FOR recnotification
IN (SELECT suivdosid, comid, interid, devid, suivdosdate, suivdoscom, suividossuppr FROM suivdossier WHERE utiid = $1)
LOOP
RETURN NEXT recnotification ;
END LOOP ;
RETURN ;
END;
$$ LANGUAGE plpgsql;
/*SELECT suivdosid, comid, interid, devid, suivdosdate, suivdoscom, suividossuppr FROM get_notification(1) AS (suivdosid integer, comid integer, interid integer, devid integer, suivdosdate date, suivdoscom varchar(1000), suividossuppr bool);*/

CREATE OR REPLACE FUNCTION get_stock(integer) RETURNS SETOF record
AS $$
DECLARE
recstock RECORD;
BEGIN
FOR recstock
IN (SELECT m.matid, f.fourid, f.fourrais, f.fournom, f.fouradresse, f.fourcp, f.fourville, f.foursuppr, i.interid, i.internom, i.interprenom, i.intermail, i.intertel, i.intersuppr, s.stockid, s.stockdatecom, s.stockentree, s.stocksortie, s.stockqte, s.stockreffour, s.stockpu, s.stocksuppr, m.matlib, m.matsuppr FROM fournisseur as f, interlocuteur as i, stock as s, matiere as m WHERE i.interid = s.interid AND f.fourid = i.fourid AND s.stocksuppr = false AND m.matid = $1 AND s.matid = $1)
LOOP
RETURN NEXT recstock;
END LOOP ;
RETURN ;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_infosFour(integer) RETURNS SETOF record
AS $$
DECLARE
recinfos RECORD;
BEGIN
FOR recinfos
IN (SELECT f.fourid, f.fourrais, f.fournom, f.fouradresse, f.fourcp, f.fourville, i.interid, i.internom, i.interprenom, i.intermail, i.intertel FROM fournisseur as f, interlocuteur as i WHERE f.fourid=$1 AND i.fourid = $1)
LOOP
RETURN NEXT recinfos;
END LOOP ;
RETURN ;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_nommat(integer) RETURNS SETOF record
AS $$
DECLARE
recinfos RECORD;
BEGIN
FOR recinfos
IN (SELECT i.nomid, i.nomlib, i.nomdate, k.matqte, u.unitelbl FROM nomenclature as i, nommat as k, matiere as m, unite as u WHERE i.nomsuppr = false AND i.nomid=k.nomid AND k.matid = m.matid AND m.uniteid = u.uniteid AND k.matid = $1)
LOOP
RETURN NEXT recinfos;
END LOOP ;
RETURN ;
END;
$$ LANGUAGE plpgsql;