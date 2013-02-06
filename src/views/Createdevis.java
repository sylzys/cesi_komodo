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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import models.CurrentDatas;
import models.Devis;
import models.Devnom;
import models.ModelesTables;
import org.hibernate.Session;
import org.hibernate.Transaction;
import models.Suivdossier;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tableModels.AllnomenclaturelistModel;
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
    private JTable table = new JTable();
    public JLabel newRow = new javax.swing.JLabel();
    public JLabel newPrice = new javax.swing.JLabel();
    public JLabel newTotal = new javax.swing.JLabel();
    public JFormattedTextField linedisc = new JFormattedTextField();
    public JFormattedTextField Intitule = new JFormattedTextField();
    public JTextArea Description = new JTextArea();

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

        JPanel ab1 = new JPanel();
        ab1.setPreferredSize(new Dimension(650, 750));
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
        ab3.setPreferredSize(new Dimension(200, 20));

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




        model = new AllnomenclaturelistModel();
        table.setModel(model);
        //tri des colonnes
        table.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(500, 270));
        table.setFillsViewportHeight(true);
        table.setBackground(Color.white);
        table.setPreferredSize(new Dimension(240, 140));
        content.add(table, BorderLayout.CENTER);

        ab1.setBackground(Color.white);
        ab2.setBackground(Color.white);
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);
        selectedNom.setBackground(Color.white);
        ab3.setBackground(Color.white);
        jLabel3.setBackground(Color.white);
        ab4.setBackground(Color.white);

        linedisc.setPreferredSize(new Dimension(80, 20));

        newRow.setText("");
        selectedNom.add(newRow);
        newPrice.setText("");
        selectedNom.add(newPrice);
        newTotal.setText("Total : 0 €");
        ab1.add(icon);
        ab1.add(title);
        ab1.add(retour);
        ab1.add(b1);
        ab1.add(ab2);
        ab1.add(b2);
        ab1.add(selectedNom);
        ab1.add(linedisc, BorderLayout.CENTER);
        ab1.add(newTotal);
        ab1.add(ab3);
        ab1.add(ab4);
        ab1.add(jLabel3);
        content.add(ab1);

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToDataBase();
            }
        });

        calculIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String oldTotalString = newTotal.getText();
                Integer linediscValue = Integer.parseInt(linedisc.getText());
                double newLinediscValue = (((double) linediscValue) / 100);
                oldTotalString = oldTotalString.replace("Total : ", "");
                oldTotalString = oldTotalString.replace(" €", "");
                Integer oldTotal = Integer.parseInt(oldTotalString);
                Integer theNewTotal = (int) Math.round(oldTotal - (oldTotal * newLinediscValue));
                newTotal.setText("Total : " + Integer.toString(theNewTotal) + " €");
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    Point p = e.getPoint();

                    int row = target.rowAtPoint(p);
                    int column = target.convertColumnIndexToModel(target.columnAtPoint(p));
                    TableModel tm = table.getModel();
                    String numnum = (String) tm.getValueAt(row, 0);
                    BigDecimal price = new BigDecimal(tm.getValueAt(row, 2).toString());
                  //  String thename = table.getColumnName(numnum);
                    String oldTotalString = newTotal.getText();
                    oldTotalString = oldTotalString.replace("Total : ", "");
                    oldTotalString = oldTotalString.replace(" €", "");
                    BigDecimal oldTotal = new BigDecimal(oldTotalString);
                    BigDecimal theNewTotal = oldTotal.add(price);
                    String oldRows = newRow.getText();
                    String newRowContent = oldRows + "     -" + idNumnum + ";" + numnum + "  " + String.valueOf(price) + " €";
                    newRow.setText(newRowContent);
                    newTotal.setText("Total : " + String.valueOf(theNewTotal) + " €");
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
            String desc = newTotal.getText();
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

                System.out.println("b");
                System.out.println(newRow.getText());
                String str[] = newRow.getText().split("-");

                for (int i = 1; i < str.length; i = i + 2) {
                    System.out.println(str[i]);
                    System.out.println(str.length);
                    String idCutString[] = str[i].split(";");
                    System.out.println(idCutString.length);
                    System.out.println("c");
                    if (idCutString.length > 0) {
                        System.out.println(idCutString.length);
                        System.out.println("d");
                        System.out.println(idCutString[0]);
                        HibernateConnection connection = HibernateConnection.getInstance();
                        connection.getSession().createSQLQuery("INSERT INTO devnom (devid, devnomqte, nomid) VALUES (?, ?, ?)")
                                .setParameter(1, 1)
                                .setParameter(2, 1) // Since you want it to be a TIMESTAMP
                                .setParameter(3, idCutString[0])
                                .executeUpdate();
                    }


                }
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
