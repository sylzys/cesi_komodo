UPDATE "public"."client" SET "clitel"='0232101658', "climail"='jfdnhojd@djfhnkdj.fr', "cliactivite"='Mecanique auto', "clisiret"='98787687' WHERE ("cliid"='1');


INSERT INTO "public"."demande" ("cliid", "utiid", "demandeetat", "demandedteadd") VALUES ('1', '1', '60', '2012-10-27');

INSERT INTO "public"."suivdossier" ("interid", "demandeid", "devid", "utiid", "suivdosdate", "suivdoscom", "suividossuppr") VALUES ('1', '2', '2', '1', '2012-10-19', 'Blbalblabalblaa', 'f');

INSERT INTO "public"."suivdossier" ("interid", "demandeid", "devid", "utiid", "suivdosdate", "suivdoscom", "suividossuppr") VALUES ('1', '2', '3', '1', '2012-10-27', 'Kihfzcozehd ezufhozu uoczhfcokze', 'f');

UPDATE "public"."devis" SET "devetat"='refusée' WHERE ("devid"='2');
UPDATE "public"."devis" SET "devetat"='en attente' WHERE ("devid"='3');

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
INSERT INTO "public"."demande" ("demandeid", "cliid", "utiid", "demandeetat") VALUES ('4', '2', '1', '20');
UPDATE "public"."client" SET "utiid"='1' WHERE ("cliid"='2');

