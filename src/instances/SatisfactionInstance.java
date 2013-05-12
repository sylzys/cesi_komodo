package instances;

import controllers.Synchro;
import java.awt.Dimension;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Enquete;
import models.GetReporting;
import models.Interlocuteur;
import models.ParamSync;
import models.Satisfaction;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class SatisfactionInstance {

    private static SatisfactionInstance instance;
    private List<Satisfaction> listSatisfaction;
    private int where;

    /**
     * Constructeur prive
     */
    private SatisfactionInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized SatisfactionInstance getInstance() {
        if (instance == null)
        {
            instance = new SatisfactionInstance();
        }

        return instance;
    }

    public synchronized List<Satisfaction> Satisfaction(int where) {
        this.where = where;
        slctbdd(where);
        return listSatisfaction;
    }


    public List<Satisfaction> slctbdd(int where) {
        this.where = where;
            listSatisfaction = new ArrayList<Satisfaction>();
        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from satisfaction ";
        try
        {
            Query query = connection.getSession().createSQLQuery("SELECT * FROM satisfaction where cliid = " + where + " order by satdate desc").addEntity(Satisfaction.class);
        
            this.listSatisfaction = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return this.listSatisfaction;
    }
}