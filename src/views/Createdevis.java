/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.Uniqid;
import controllers.TableDispatcher;
import controllers.UserActif;
import instances.DevisInstance;
import instances.DevnomInstance;
import models.Demande;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Date;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import models.CurrentDatas;
import models.Devis;
import models.Devnom;
import models.ModelesTables;
import models.Nomenclature;
import org.hibernate.Session;
import org.hibernate.Transaction;
import models.Suivdossier;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tableModels.AllnomenclaturelistModel;
import tableModels.NomenclatureModel;
import tableModels.NomenclaturelistModel;

/**
 *
 * @author sylv
 */
public class Createdevis extends KContainer {

    JLabel title = new JLabel("Créer un devis");
    JPanel left = new JPanel(),
            right = new JPanel();
    public JLabel nomRow = new javax.swing.JLabel();
    int id;
    private Fenetre fen = Fenetre.getInstance();
    private TableModel model;
    private TableModel modelListSelectedNom;
    private JTable table = new JTable();
    private JTable selectedNomList = new JTable();
    public JLabel newRow = new javax.swing.JLabel();
    public JLabel newPrice = new javax.swing.JLabel();
    public JLabel newTotal = new javax.swing.JLabel();
    public JFormattedTextField linedisc = new JFormattedTextField();
    public JFormattedTextField Intitule = new JFormattedTextField();
    public JTextArea Description = new JTextArea();
    public HashMap<Integer, Nomenclature> listeSelectedNom = new HashMap();
    public DefaultTableModel modelSelectedNom = new DefaultTableModel();

    public Createdevis(UserActif user, int id) {
        super();
        this.user = user;
        this.id = id;
        initPanel();
    }

    @Override
    protected void initPanel() {
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(1000, 750));

        final JPanel ab1 = new JPanel();
        right.setPreferredSize(new Dimension(650, 750));
        ab1.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.LIGHT_GRAY));

        Description.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JLabel jLabel7 = new javax.swing.JLabel();
        JLabel jLabel8 = new javax.swing.JLabel();
        JPanel listeDevis = new JPanel();

        JPanel b1 = new JPanel();
        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        jLabel1.setText("Intitulé");
        jLabel1.setPreferredSize(new Dimension(100, 20));
        jLabel4.setPreferredSize(new Dimension(20, 10));
        jLabel6.setPreferredSize(new Dimension(20, 10));
        Intitule.setPreferredSize(new Dimension(400, 10));
        b1.add(jLabel4, BorderLayout.CENTER);
        b1.add(jLabel1, BorderLayout.CENTER);
        b1.add(Intitule, BorderLayout.CENTER);
        b1.add(jLabel6, BorderLayout.CENTER);
        b1.setPreferredSize(new Dimension(500, 20));

        JPanel ab2 = new JPanel();
        ab2.setLayout(new BoxLayout(ab2, BoxLayout.LINE_AXIS));
        ab2.setPreferredSize(new Dimension(200, 10));


        JPanel b2 = new JPanel();
        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        jLabel2.setText("Description");
        jLabel2.setPreferredSize(new Dimension(100, 20));
        jLabel5.setPreferredSize(new Dimension(20, 10));
        jLabel7.setPreferredSize(new Dimension(20, 10));
        Description.setPreferredSize(new Dimension(400, 60));
        b2.add(jLabel5, BorderLayout.CENTER);
        b2.add(jLabel2, BorderLayout.CENTER);
        b2.add(Description, BorderLayout.CENTER);
        b2.add(jLabel7, BorderLayout.CENTER);
        b2.setPreferredSize(new Dimension(500, 70));

        final JPanel selectedNom = new JPanel();
        selectedNom.setLayout(new BoxLayout(selectedNom, BoxLayout.LINE_AXIS));
        selectedNom.setPreferredSize(new Dimension(500, 300));
        selectedNom.add(nomRow);

        JPanel ab3 = new JPanel();
        ab3.setLayout(new BoxLayout(ab3, BoxLayout.LINE_AXIS));
        ab3.setPreferredSize(new Dimension(400, 400));

        JPanel ab4 = new JPanel();
        ab4.setLayout(new BoxLayout(ab4, BoxLayout.LINE_AXIS));
        ab4.setPreferredSize(new Dimension(400, 50));


        content.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));


        JButton button = new JButton("Générer le devis");
        jLabel8.setPreferredSize(new Dimension(20, 50));

        JButton calculIt = new JButton("Recalculer les prix");
        JButton retour = new JButton("Annuler");
        retour.addActionListener(new Createdevis.RetourListenerDmd());

        ab4.add(button, BorderLayout.CENTER);
        ab4.add(calculIt, BorderLayout.CENTER);
        ab4.add(jLabel8, BorderLayout.CENTER);

        jLabel3.setPreferredSize(new Dimension(200, 20));


        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(0, 0, 10, 0));
        Font f = new Font("Euphemia", Font.PLAIN, 22);
        title.setFont(f);
        title.setPreferredSize(new Dimension(300, 40));

        JLabel icon = new JLabel(new ImageIcon("ressources/images/newdevis.jpg"));


        listeDevis.setBackground(Color.white);
        listeDevis.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        listeDevis.setLayout(new BoxLayout(listeDevis, BoxLayout.LINE_AXIS));

        model = new AllnomenclaturelistModel();
        table.setModel(model);
        //tri des colonnes
        table.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(220, 220));
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.add(table.getTableHeader(), BorderLayout.PAGE_START);

        JTableHeader headerListLeft = table.getTableHeader();
        headerListLeft.setBackground(Color.cyan);
        JScrollPane paneLeft = new JScrollPane(table);

        table.setBackground(Color.white);
        left.setBackground(Color.white);
        right.setBackground(Color.white);
        content.setBackground(Color.white);
        left.add(paneLeft, BorderLayout.PAGE_START);
        content.add(left);

        ab1.setBackground(Color.white);
        ab2.setBackground(Color.white);
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);
        selectedNom.setBackground(Color.white);
        ab3.setBackground(Color.white);
        jLabel3.setBackground(Color.white);
        ab4.setBackground(Color.white);



        Object[][] data = {};
        Object[] header = {"Id", "Libellé", "prix", "Qté"};
        modelSelectedNom = new DefaultTableModel(data, header);
        selectedNomList = new JTable(modelSelectedNom);
        selectedNomList.setPreferredScrollableViewportSize(new Dimension(500, 270));
        selectedNomList.setFillsViewportHeight(true);
        selectedNomList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        JTableHeader headerList = selectedNomList.getTableHeader();
        headerList.setBackground(Color.cyan);
        JScrollPane pane = new JScrollPane(selectedNomList);
        pane.setBorder(BorderFactory.createMatteBorder(0, 70, 0, 100, Color.white));

        right.add(icon, BorderLayout.NORTH);
        right.add(title, BorderLayout.NORTH);
        right.add(retour, BorderLayout.NORTH);

        right.add(b1, BorderLayout.AFTER_LAST_LINE);
        right.add(b2, BorderLayout.AFTER_LAST_LINE);

        right.add(pane, BorderLayout.CENTER);

        newTotal.setText("Total : 0 €");

        JLabel LinediscLbl = new JLabel("Remise (en %)");
        LinediscLbl.setBorder(BorderFactory.createMatteBorder(0, 300, 0, 10, Color.white));
        linedisc.setPreferredSize(new Dimension(50, 20));

        newTotal.setBorder(BorderFactory.createMatteBorder(0, 300, 0, 0, Color.white));

        JPanel divTotal = new JPanel();
        divTotal.setPreferredSize(new Dimension(650, 25));
        JPanel divLinedisc = new JPanel();
        divLinedisc.setPreferredSize(new Dimension(650, 25));

        divLinedisc.add(LinediscLbl);
        divLinedisc.add(linedisc);
        divTotal.add(newTotal);
        right.add(divLinedisc);
        right.add(divTotal);
        right.add(ab4);

        content.add(right);

        selectedNomList.addKeyListener(new KeyListener() {
            public void actionPerformed(KeyListener ime) {
                
            }

            @Override
            public void keyTyped(KeyEvent ke) {
                
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                Integer i = 0;
                BigDecimal price = new BigDecimal(0);
                while (i < modelSelectedNom.getRowCount()) {
                    BigDecimal priceNom = new BigDecimal(modelSelectedNom.getValueAt(i, 2).toString());
                    BigDecimal qtYNom = new BigDecimal(modelSelectedNom.getValueAt(i, 3).toString());
                    System.out.println("cdgsgs");
                    i = i + 1;
                    BigDecimal tralala = qtYNom.multiply(priceNom);
                    price = price.add(tralala);
                }

                newTotal.setText("Total : " + String.valueOf(price) + " €");
            }
        });

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToDataBase();
            }
        });

        calculIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String oldTotalString = newTotal.getText();
                Integer linediscValue = Integer.parseInt(linedisc.getText());
                BigDecimal newLinediscValue = new BigDecimal((((double) linediscValue) / 100));
                oldTotalString = oldTotalString.replace("Total : ", "");
                oldTotalString = oldTotalString.replace(" €", "");
                BigDecimal oldTotal = new BigDecimal(oldTotalString);
                
                    BigDecimal tralala = oldTotal.multiply(newLinediscValue);
                    oldTotal = oldTotal.subtract(tralala).round(MathContext.DECIMAL32);
                    
                newTotal.setText("Total : " + oldTotal + " €");
            }
        });



        table.addMouseListener(new MouseAdapter() {
            int flag = 0;

            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    Point p = e.getPoint();

                    int row = target.rowAtPoint(p);
                    int column = target.convertColumnIndexToModel(target.columnAtPoint(p));
                    TableModel tm = table.getModel();
                    String numnum = (String) tm.getValueAt(row, 0);
                    Integer idNumnum = (Integer) tm.getValueAt(row, 999);
                    BigDecimal price = new BigDecimal(tm.getValueAt(row, 2).toString());
                    //  String thename = table.getColumnName(numnum);
                    String oldTotalString = newTotal.getText();
                    oldTotalString = oldTotalString.replace("Total : ", "");
                    oldTotalString = oldTotalString.replace(" €", "");
                    BigDecimal oldTotal = new BigDecimal(oldTotalString);
                    BigDecimal theNewTotal = oldTotal.add(price);
                    String oldRows = newRow.getText();
                    Nomenclature nomToAdd = new Nomenclature();
                    nomToAdd.setNomid(idNumnum);
                    nomToAdd.setNomlib(numnum);
                    nomToAdd.setNomprix(price);
                    flag = flag + 1;
                    newTotal.setText("Total : " + String.valueOf(theNewTotal) + " €");

                    modelSelectedNom.insertRow(0, new Object[]{idNumnum, numnum, price, 1});

                }
                if (e.getClickCount() == 1) {
                    //----> Affichage du detail de la nom ?
                }
            }
        });

        this.panel.add(content, BorderLayout.PAGE_END);
    }

    private void addToDataBase() {
        try {
            CurrentDatas cd = CurrentDatas.getInstance();




            Devis dvis = new Devis();
            dvis.setDevetat("En cours");
            dvis.setDevdate(new Date());
            String oldTotalString = newTotal.getText();
            oldTotalString = oldTotalString.replace("Total : ", "");
            oldTotalString = oldTotalString.replace(" €", "");
            BigDecimal prix = new BigDecimal(oldTotalString);
            dvis.setDevprix(prix);
            dvis.setDevsuppr(false);
            String titre = Intitule.getText();
            String desc = Description.getText();
            dvis.setDemandeid(id);
            dvis.setDevtitre(titre);
            dvis.setDevdesc(desc);
            Uniqid uniqid = new Uniqid(cd.getUser().getId());
            dvis.setDevuniqid(uniqid.getUniqid());

            Session session2 = HibernateConnection.getSession();
            Demande dmdd = (Demande) session2.get(Demande.class, id);
            dvis.setInterid(dmdd.getInterid());

            DevisInstance dvis_inst = DevisInstance.getInstance();

            dvis_inst.setDevis(dvis);

            System.out.println("a");
            if (dvis_inst.ajouterDansBaseDeDonnees()) {

                HibernateConnection connection = HibernateConnection.getInstance();
                Query query = connection.getSession().createSQLQuery("SELECT last_value FROM devis_devid_seq");
                int idLastDevis = Integer.valueOf(((BigInteger) query.uniqueResult()).intValue());
                System.out.println("b");

                System.out.println(selectedNomList.getRowCount());

                System.out.println("fdgdg");
                System.out.println(modelSelectedNom.getRowCount());
                int i = 0;
                Session mysession = connection.getSession();
                Transaction transaction = null;
                try {
                    while (i < modelSelectedNom.getRowCount()) {
                        transaction = mysession.beginTransaction();
                        Integer idNom = Integer.parseInt(modelSelectedNom.getValueAt(i, 0).toString());
                        Integer qtYNom = Integer.parseInt(modelSelectedNom.getValueAt(i, 3).toString());
                        
                        String muery = "INSERT INTO devnom (devid, devnomqte, nomid) VALUES (" + idLastDevis + ", " + qtYNom + ", " + idNom + ")";
                        System.out.println(muery);
                        mysession.createSQLQuery(muery).executeUpdate();
                        System.out.println("cdgsgs");
                        i = i + 1;
                        mysession.getTransaction().commit();
                    }
                    mysession.flush();
                } catch (HibernateException e) {
                    transaction.rollback();
                    e.printStackTrace();
                } finally {
                }
                System.out.println("cdgsoooooooooogs");
                JOptionPane.showMessageDialog(null, "Votre devis a été ajouté avec succès.");
            }

            dmdd.setDemandeid(id);

            dmdd.setDemandeetat(
                    60);

            session2.update(dmdd);
            Transaction tx4 = HibernateConnection.getSession().beginTransaction();

            tx4.commit();
            DemandeDetail devisForm = new DemandeDetail(id);

            fen.RenewContener(devisForm.getPanel());
        } catch (Exception e) {
            System.out.println(e.getMessage());


        }

    }

    private class RetourListenerDmd implements ActionListener {

        public RetourListenerDmd() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CurrentDatas cur = CurrentDatas.getInstance();

            DemandeDetail devisForm = new DemandeDetail(id);
            fen.RenewContener(devisForm.getPanel());
        }
    }
}
