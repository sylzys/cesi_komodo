/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

//import com.google.gdata.client.calendar.CalendarService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.DateTime;


import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.toedter.calendar.JDateChooser;
import controllers.GoogleCalendar;
import controllers.UserActif;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    JTextField jTextField1 = new javax.swing.JTextField();
    JLabel jLabel1 = new javax.swing.JLabel();
    JLabel jLabel2 = new javax.swing.JLabel();
    JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
    JTextArea jTextArea1 = new javax.swing.JTextArea();
    JLabel jLabel3 = new javax.swing.JLabel();
    //JFormattedTextField jFormattedTextField1 = new javax.swing.JFormattedTextField();
    JXDatePicker datepicker_debut = new JXDatePicker();
    //JXDatePicker datepicker_fin = new JXDatePicker();
    JFormattedTextField jFormattedTextField2 = new javax.swing.JFormattedTextField();
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
    //JFormattedTextField jFormattedTextField3 = new javax.swing.JFormattedTextField();
    //JFormattedTextField jFormattedTextField4 = new javax.swing.JFormattedTextField();

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
        b1.add(jTextField1, BorderLayout.CENTER);
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
        jTextArea1.setPreferredSize(new Dimension(200, 120));
        jTextArea1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        b2.add(jLabel2, BorderLayout.WEST);
        b2.add(jTextArea1, BorderLayout.EAST);
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
        //jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        b3.add(datepicker_debut, BorderLayout.CENTER);
        b3.add(jLabel14, BorderLayout.CENTER);
        b3.add(jLabel12, BorderLayout.CENTER);
        b3.add(jFormattedTextField2, BorderLayout.EAST);
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
        b4.add(jLabel16, BorderLayout.WEST);
        b4.add(jLabel4, BorderLayout.WEST);
       // b4.add(jFormattedTextField3, BorderLayout.CENTER);
        b4.add(jLabel19, BorderLayout.WEST);
        b4.add(jLabel17, BorderLayout.WEST);
        //b4.add(jFormattedTextField4, BorderLayout.EAST);
        b4.add(jLabel18, BorderLayout.WEST);
        
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
        
        JXDatePicker plop = new JXDatePicker();
        
        
        

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                GoogleCalendar gc =  new GoogleCalendar();
                gc.init();
                Event event = new Event();
                Date startDate = new Date();
                Date endDate = new Date(startDate.getTime() + 3600000);
                DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
                event.setStart(new EventDateTime().setDateTime(start));
                DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
                event.setEnd(new EventDateTime().setDateTime(end));

                event.setSummary("plop");
                event.setDescription("Description");

                String title = "Nouvel Evènement";     

                Calendar calendar = null;
                try {
                    calendar = gc.getCalendars("My agenda");
                    gc.addEvent(calendar, event);

                } catch (IOException ex) {
                    Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null,"Votre évènement a été ajouté avec succès.");
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
