package instances;

import controllers.Synchro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Enquete;
import models.GetReporting;
import models.ParamSync;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class ReportingInstance {

    private static ReportingInstance instance;
    private List<GetReporting> lstreport;
    private String where;
    private Hashtable h;
    private Enquete enq;

    /**
     * Constructeur prive
     */
    private ReportingInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized ReportingInstance getInstance() {
        if (instance == null)
        {
            instance = new ReportingInstance();
        }

        return instance;
    }

    public synchronized List<GetReporting> GetReporting(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        slctbdd();
        return lstreport;
    }

    public synchronized void setReporting(Enquete enq) {
        this.enq = enq;
    }

    public void slctbdd() {

        if (enq == null)
        {
            lstreport = new ArrayList<GetReporting>();
        }
        else
        {
            lstreport.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from getreporting ";
        if (!where.isEmpty())
        {
            sql += where;
        }

        try
        {
            Query query = connection.getSession().createQuery(sql);
            if (!h.isEmpty())
            {
                Set<String> set = h.keySet();
                Iterator<String> itr = set.iterator();
                while (itr.hasNext())
                {
                    String str = itr.next();
                    query.setParameter(str, h.get(str));
                }
            }
            this.lstreport = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public synchronized Boolean updtbdd(Enquete enq) {
        if (HibernateConnection.online == false)
        {
            ParamSync param = new ParamSync();
            param.setClinom(enq.getInterid(), "inter");
            if(enq.isEnqsuppr() == true)
            {
                param.setType("Suppression");
            }
            else
            {
                param.setType("Mise à jour");   
            }
            Synchro sync = new Synchro();
            sync.objSerializable(enq, param);
        }       
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().update(enq);
            tx.commit();
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }       
    }

    public Boolean addbdd() {
        if (HibernateConnection.online == false)
        {
            ParamSync param = new ParamSync();
            param.setClinom(this.enq.getInterid(),"inter");
            param.setType("Ajout");
            Synchro sync = new Synchro();
            sync.objSerializable(this.enq, param);
        }        
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().save(this.enq);
            tx.commit();
            return true;
        }
        catch (HibernateException e)
        {
            System.out.println(e);
            return false;
        }
    }
}