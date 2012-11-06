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
"public".suivdossier.suivdoscom,
"public".suivdossier.suivdosdate
FROM
(((("public".client
JOIN "public".demande ON (("public".demande.cliid = "public".client.cliid)))
JOIN "public".suivdossier ON (("public".suivdossier.demandeid = "public".demande.demandeid)))
JOIN "public".utilisateur ON (("public".client.utiid = "public".utilisateur.utiid))))
;

