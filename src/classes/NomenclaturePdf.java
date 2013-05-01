/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import instances.HibernateConnection;
import java.util.List;
import models.Matiere;
import models.Nomenclature;
import models.Nommat;
import org.hibernate.Session;

/**
 *
 * @author overadmin
 */
public class NomenclaturePdf  extends MyPdf{
    
    
    public NomenclaturePdf(String name) {
        super(name);
    }
    public void makePdfNomenclature()
    {
        MyPdf.prefix = "nomenclature";
        setInstance();
        Session session = HibernateConnection.getSession();        
        List<Nomenclature>list = session.createSQLQuery("SELECT * From nomenclature WHERE nomid ="+this.id+ ";").addEntity(Nomenclature.class).list();               
        Nomenclature nomen = list.get(0);
        List<Nommat>listNomMat = session.createSQLQuery("SELECT * From nommat WHERE nomid ="+this.id+ " ").addEntity(Nommat.class).list();       
         
        int i = 0;
        Nommat nommat;
        List<Matiere> listMatiere;
        Matiere mat;
        String[][]data = new String[listNomMat.size()][3];
        for(i =0;i< listNomMat.size();i++)
        {
            nommat = listNomMat.get(i);
            listMatiere =  session.createSQLQuery("SELECT * From matiere WHERE matid ="+ Integer.toString(nommat.getMatid())+ " ").addEntity(Matiere.class).list();
            mat = listMatiere.get(0);
            data[i][0] = Integer.toString(mat.getMatid());
            data[i][1] = mat.getMatlib();
            data[i][2] = Integer.toString(nommat.getMatqte());                   
        }
        
        
        try
        {
            this.addTitlePage(nomen.getNomdate(),Integer.toString(nomen.getNomid()),nomen.getNomdes());	
            this.createQrcode(Integer.toString(nomen.getNomid()));
            this.addTable(data);
            this.addRow("Prix : " + nomen.getNomprix());
            this.document.close();
            
            
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }
        
        savePdf(this.getFILE());      
    }    
}
