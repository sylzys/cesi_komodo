/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

//import com.google.gdata.client.calendar.CalendarService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;


import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import controllers.GoogleCalendar;
import controllers.UserActif;
import instances.ClientInstance;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import models.Agenda;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.plaf.DatePickerAddon;
import org.jdesktop.swingx.plaf.DatePickerUI;

/**
 *
 * @author sylv
 */
public class Evenement extends KContainer {

    JLabel title = new JLabel("Ajouter un évènement à mon agenda");
    JButton button = new JButton();
    JTextField txt_title = new javax.swing.JTextField();
    JLabel jLabel1 = new javax.swing.JLabel();
    JLabel jLabel2 = new javax.swing.JLabel();
    JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
    JTextArea txt_description = new javax.swing.JTextArea();
    JLabel jLabel3 = new javax.swing.JLabel();
    //JFormattedTextField jFormattedTextField1 = new javax.swing.JFormattedTextField();
    JXDatePicker datepicker_debut = new JXDatePicker();
    JXDatePicker datepicker_fin = new JXDatePicker();
    JFormattedTextField txt_heure_debut = new javax.swing.JFormattedTextField();
    JLabel jLabel4 = new javax.swing.JLabel();
    JLabel jLabel5 = new javax.swing.JLabel();
    JLabel jLabel6 = new javax.swing.JLabel();
    JLabel jLabel7 = new javax.swing.JLabel();
    JLabel jLabel8 = new javax.swing.JLabel();
    JLabel jLabel9 = new javax.swing.JLabel();
    JLabel jLabel10 = new javax.swing.JLabel();
    JLabel jLabel11 = new javax.swing.JLabel();
    JLabel jLabel12 = new javax.swing.JLabel();
    JLabel jLabel13 = new javax.swing.JLabel();
    JLabel jLabel14 = new javax.swing.JLabel();
    JLabel jLabel15 = new javax.swing.JLabel();
    JLabel jLabel16 = new javax.swing.JLabel();
    JLabel jLabel17 = new javax.swing.JLabel();
    JLabel jLabel18 = new javax.swing.JLabel();
    JLabel jLabel19 = new javax.swing.JLabel();
    JLabel jLabel20 = new javax.swing.JLabel();
    JFormattedTextField txt_heure_fin = new javax.swing.JFormattedTextField();
    JFormattedTextField jFormattedTextField4 = new javax.swing.JFormattedTextField();

    public Evenement(UserActif user) {
        super();
        this.user = user;
        initPanel();
    }

    @Override
    protected void initPanel() {
        JPanel ab1 = new JPanel();
        ab1.setLayout(new BoxLayout(ab1, BoxLayout.LINE_AXIS));
        ab1.setPreferredSize(new Dimension(200,20));
        
        
        JPanel b1 = new JPanel();
        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        jLabel1.setText("Intitulé");
        jLabel1.setPreferredSize(new Dimension(100, 20));
        jLabel5.setPreferredSize(new Dimension(20, 10));
        jLabel6.setPreferredSize(new Dimension(20, 10));
        b1.add(jLabel5, BorderLayout.CENTER);
        b1.add(jLabel1, BorderLayout.CENTER);
        b1.add(txt_title, BorderLayout.CENTER);
        b1.add(jLabel6, BorderLayout.CENTER);
        b1.setPreferredSize(new Dimension(200, 20));

        JPanel ab2 = new JPanel();
        ab2.setLayout(new BoxLayout(ab2, BoxLayout.LINE_AXIS));
        ab2.setPreferredSize(new Dimension(200,20));
        
        JPanel b2 = new JPanel();
        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        jLabel2.setText("Description");
        jLabel2.setPreferredSize(new Dimension(100, 10));
        jLabel7.setPreferredSize(new Dimension(20, 10));
        jLabel8.setPreferredSize(new Dimension(20, 10));
        b2.add(jLabel7, BorderLayout.CENTER);
        txt_description.setPreferredSize(new Dimension(200, 120));
        txt_description.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        b2.add(jLabel2, BorderLayout.WEST);
        b2.add(txt_description, BorderLayout.EAST);
        b2.add(jLabel8, BorderLayout.CENTER);
        
        JPanel ab3 = new JPanel();
        ab3.setLayout(new BoxLayout(ab3, BoxLayout.LINE_AXIS));
        ab3.setPreferredSize(new Dimension(200,30));
        

        JPanel b3 = new JPanel();
        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        jLabel3.setPreferredSize(new Dimension(100, 10));
        jLabel11.setPreferredSize(new Dimension(20, 10));
        jLabel12.setPreferredSize(new Dimension(50, 10));
        jLabel13.setPreferredSize(new Dimension(20, 10));
        jLabel14.setPreferredSize(new Dimension(20, 10));
        jLabel12.setText("Heure");
        b3.add(jLabel11, BorderLayout.CENTER);
        jLabel3.setText("Date de début");
        b3.add(jLabel3, BorderLayout.WEST);
        Format timeFormat = new SimpleDateFormat("HH:mm");
        txt_heure_debut = new JFormattedTextField(timeFormat);
        txt_heure_fin = new JFormattedTextField(timeFormat);
        //txt_heure_fin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.HOUR_OF_DAY0_FIELD))));
        //txt_heure_debut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.HOUR_OF_DAY0_FIELD))));
        b3.add(datepicker_debut, BorderLayout.CENTER);
        b3.add(jLabel14, BorderLayout.CENTER);
        
        b3.add(jLabel12, BorderLayout.CENTER);
        
        b3.add(txt_heure_debut, BorderLayout.EAST);
        b3.add(jLabel13, BorderLayout.CENTER);
        
        JPanel ab4 = new JPanel();
        ab4.setLayout(new BoxLayout(ab4, BoxLayout.LINE_AXIS));
        ab4.setPreferredSize(new Dimension(200,30));
        
        JPanel b4 = new JPanel();
        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        jLabel4.setText("Date de fin");
        jLabel4.setPreferredSize(new Dimension(100, 10));
        jLabel16.setPreferredSize(new Dimension(20, 10));
        jLabel17.setPreferredSize(new Dimension(50, 10));
        jLabel18.setPreferredSize(new Dimension(20, 10));
        jLabel19.setPreferredSize(new Dimension(20, 10));
        jLabel17.setText("Heure");
       
        
        //ligne debut
        b4.add(jLabel16, BorderLayout.WEST);
        b4.add(jLabel4, BorderLayout.WEST);
        b4.add(datepicker_fin,BorderLayout.CENTER);       
        b4.add(jLabel19, BorderLayout.WEST);
        
        //ligne fin        
        //b4.add(jLabel18, BorderLayout.WEST);
        b4.add(jLabel17, BorderLayout.WEST);
        b4.add(txt_heure_fin, BorderLayout.CENTER);
        
        JPanel ab5 = new JPanel();
        ab5.setLayout(new BoxLayout(ab5, BoxLayout.LINE_AXIS));
        ab5.setPreferredSize(new Dimension(200,30));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setPreferredSize(new Dimension(500, 380));
            content.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
            
        button.setText("Ajouter");
        
        jLabel15.setPreferredSize(new Dimension(200, 20));
        content.add(title);
        content.add(ab1);
        content.add(b1);
        content.add(ab2);
        content.add(b2);
        content.add(ab3);
        content.add(b3);
        content.add(ab4);
        content.add(b4);
        content.add(ab5);;
        content.add(button, BorderLayout.LINE_END);
        content.add(jLabel15);
        
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    
                    
                    if(txt_description.getText()!= ""
                        && txt_heure_debut.getText() != ""
                            && txt_heure_fin.getText()!= ""
                            && txt_title.getText()!= ""
                            && datepicker_debut.getDate() != null
                            && datepicker_fin.getDate()!= null)
                    {
                        GoogleCalendar gc =  new GoogleCalendar();
                        gc.init();
                        Event event = new Event();
                        Date startDate = datepicker_debut.getDate();
                        //Date endDate = new Date(startDate.getTime() + 3600000);
                        Date endDate  = datepicker_fin.getDate();
                        //DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
                        Format timeFormat = new SimpleDateFormat("HH:mm");

                        Date tmpDate = (Date)txt_heure_debut.getValue();
                        startDate.setHours(tmpDate.getHours());
                        startDate.setMinutes(tmpDate.getMinutes());
                        DateTime start  = new DateTime(startDate,TimeZone.getTimeZone("UTC"));
                        //DateTime start = (DateTime)timeFormat.parseObject(txt_heure_debut.getText());
                        event.setStart(new EventDateTime().setDateTime(start));
                        //DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
                        //DateTime end = (DateTime)txt_heure_fin.getValue();
                        Date tmpDate2 = (Date)txt_heure_fin.getValue();
                        endDate.setHours(tmpDate2.getHours());
                        endDate.setMinutes(tmpDate2.getMinutes());
                        DateTime end = new DateTime(endDate,TimeZone.getTimeZone("UTC"));
                        event.setEnd(new EventDateTime().setDateTime(end));

                        event.setSummary(txt_title.getText());
                        event.setDescription(txt_description.getText());

                        String title = "Nouvel Evènement";     

                        Calendar calendar = null;

                        //test si la connection internet est valide. 
                        if(HibernateConnection.online == true )
                        {
                            try {
                            calendar = gc.getCalendars("My agenda");
                            gc.addEvent(calendar, event);

                            }catch (IOException ex) {
                                Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null,"Votre évènement a été ajouté avec succès.");
                            txt_description.setText("");
                            txt_title.setText("");
                            txt_heure_debut.setText("");
                            txt_heure_fin.setText("");
                            datepicker_debut.setDate(null);
                            datepicker_fin.setDate(null);
                        }
                        else
                        {                      
                            JOptionPane.showMessageDialog(null,"Impossible d'ajouter l'évènement en mode hors ligne.");                       
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Vous devez compléter tous les champs");                   
                    }
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        title.setHorizontalAlignment(SwingConstants.CENTER);  
        title.setBorder(new EmptyBorder(0, 0, 10, 0));
        Font f = new Font("Euphemia", Font.PLAIN, 22);
        title.setFont(f); 
        title.setPreferredSize(new Dimension(1000, 40));
        
        JLabel icon = new JLabel(new ImageIcon("ressources/images/calendar.jpg"));
        
        
        this.panel.add(icon, BorderLayout.PAGE_START);
        this.panel.add(title, BorderLayout.AFTER_LINE_ENDS);
        this.panel.add(content, BorderLayout.PAGE_END);
        
        
     
        //partie CM 
//            try {
//      try {
//        // authorization
//          GoogleCalendar googleCalendar = new GoogleCalendar();
//          
//        Credential credential = googleCalendar.authorize();
//
//        // set up global Calendar instance
//        com.google.api.services.calendar.Calendar client = new com.google.api.services.calendar.Calendar.Builder(
//            GoogleCalendar.HTTP_TRANSPORT, GoogleCalendar.JSON_FACTORY, credential).setApplicationName(
//            "Google-CalendarSample/1.0").build();
//        // run commands
//        googleCalendar.showCalendars();
//        //addCalendarsUsingBatch();
//        //Calendar calendar = addCalendar();
//        //updateCalendar(calendar);
//        Calendar calendar = googleCalendar.getCalendars("My agenda");
//        if(calendar == null){
//        	
//        	calendar = googleCalendar.addCalendar();
//        	//calendar = addCalendarsUsingBatch("My agenda");
//        }
////        Date startDate = new Date();
////        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
////        Date endDate = new Date(startDate.getTime() + 3600000);
////        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
//        
//
        
        
    //        googleCalendar.addEvent(calendar,event);
    //        googleCalendar.showEvents(calendar);
    //        //deleteCalendarsUsingBatch();
    //        //deleteCalendar(calendar);
    //
    //      } catch (IOException e) {
    //        System.err.println(e.getMessage());
    //      }
    //    } catch (Throwable t) {
    //      t.printStackTrace();
    //    }
    //        
    //
        
    }

//    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
//
//        CalendarService client = new CalendarService("komodo");
//        client.setUserCredentials("usernameIndisponible@gmail.com", "");
//
//        URL postUrl = new URL("https://www.google.com/calendar/feeds/usernameIndisponible@gmail.com/private/full");
//        CalendarEventEntry myEntry = new CalendarEventEntry();
//
//        myEntry.setTitle(new PlainTextConstruct("Event 1"));
//        myEntry.setContent(new PlainTextConstruct("Conférence Peugeot"));
//
//        DateTime startTime = DateTime.parseDateTime("2012-12-17T15:00:00-08:00");
//        DateTime endTime = DateTime.parseDateTime("2012-12-17T17:00:00-08:00");
//        When eventTimes = new When();
//        eventTimes.setStartTime(startTime);
//        eventTimes.setEndTime(endTime);
//        myEntry.addTime(eventTimes);
//
//
//        CalendarEventEntry insertedEntry = client.insert(postUrl, myEntry);
//        
//        
//        
//    }
//
//    private static CalendarEventEntry createEvent(CalendarService service,
//            String eventTitle, String eventContent, String recurData,
//            boolean isQuickAdd, WebContent wc) throws ServiceException, IOException {
//        CalendarEventEntry myEntry = new CalendarEventEntry();
//
//
//
//
//        myEntry.setTitle(new PlainTextConstruct(eventTitle));
//        myEntry.setContent(new PlainTextConstruct(eventContent));
//        myEntry.setQuickAdd(isQuickAdd);
//        myEntry.setWebContent(wc);
//
//        // If a recurrence was requested, add it. Otherwise, set the
//        // time (the current date and time) and duration (30 minutes)
//        // of the event.
//        if (recurData == null) {
//            Calendar calendar = new GregorianCalendar();
//            DateTime startTime = new DateTime(calendar.getTime(), TimeZone
//                    .getDefault());
//
//            calendar.add(Calendar.MINUTE, 30);
//            DateTime endTime = new DateTime(calendar.getTime(),
//                    TimeZone.getDefault());
//
//            When eventTimes = new When();
//            eventTimes.setStartTime(startTime);
//            eventTimes.setEndTime(endTime);
//            myEntry.addTime(eventTimes);
//        } else {
//            Recurrence recur = new Recurrence();
//            recur.setValue(recurData);
//            myEntry.setRecurrence(recur);
//        }
//        URL eventFeedUrl = new URL("https://www.google.com/calendar/feeds/usernameIndisponible@gmail.com/private/full");
//        // Send the request and receive the response:
//        return service.insert(eventFeedUrl, myEntry);
//    }
}
