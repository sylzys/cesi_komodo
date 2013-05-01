/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import instances.HibernateConnection;
import java.util.ArrayList;
import java.util.List;
import models.Devis;
import models.Devnom;
import models.Matiere;
import models.Nommat;
import org.hibernate.Session;

/**
 *
 * @author overadmin
 */
public class DevisPdf extends MyPdf {
    
    

    public DevisPdf(String name) {
        super(name);
    }

    public void makePdfDevis() {
        MyPdf.prefix = "devis";
        setInstance();
        Session session = HibernateConnection.getSession();
        List<Devis> list = session.createSQLQuery("SELECT * FROM devis WHERE devid = " + this.id + ";").addEntity(Devis.class).list();

        Devis devis = (Devis) list.get(0);
        MyPdf mypdf = new MyPdf(Integer.toString(devis.getDevid()));

        List<Devnom> listDevNom = session.createSQLQuery("SELECT * FROM devnom WHERE devid=" + this.id + ";").addEntity(Devnom.class).list();
        int j = 0;
        Devnom devnom;
        String[][] data;
        List<String[][]> tab = new ArrayList<>();
        for (j = 0; j < listDevNom.size(); j++) {
            devnom = listDevNom.get(j);
            List<Nommat> listNomMat = session.createSQLQuery("SELECT * From nommat WHERE nomid =" + devnom.getDevid() + " ").addEntity(Nommat.class).list();
            int i = 0;
            Nommat nommat;
            List<Matiere> listMatiere;
            Matiere mat;
            data = new String[listNomMat.size()][3];
            for (i = 0; i < listNomMat.size(); i++) {
                nommat = listNomMat.get(i);
                listMatiere = session.createSQLQuery("SELECT * From matiere WHERE matid =" + Integer.toString(nommat.getMatid()) + " ").addEntity(Matiere.class).list();
                mat = listMatiere.get(0);
                data[i][0] = Integer.toString(mat.getMatid());
                data[i][1] = mat.getMatlib();
                data[i][2] = Integer.toString(nommat.getMatqte());
            }
            tab.add(data);
        }



        try {
            this.addTitlePage(devis.getDevdate(), Integer.toString(devis.getDevid()), devis.getDevdesc());
            this.createQrcode(Integer.toString(devis.getDevid()));
            int k = 0;
            for (k = 0; k < tab.size(); k++) {
                this.addTable(tab.get(k));
                this.addRow("");
            }
            this.addRow("Prix : " + devis.getDevprix());
            this.document.close();
            

        } catch (Exception e) {
            e.getStackTrace();
        }

        this.savePdf(this.getFILE());

    }
}