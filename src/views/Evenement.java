/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.google.gdata.client.calendar.CalendarService;
import controllers.UserActif;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import com.google.gdata.data.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.Recurrence;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author sylv
 */
public class Evenement extends KContainer {

    JLabel title = new JLabel("PANNEAU EVENEMENT");
    JButton button = new JButton();
    JTextField jTextField1 = new javax.swing.JTextField();
    JLabel jLabel1 = new javax.swing.JLabel();
    JLabel jLabel2 = new javax.swing.JLabel();
    JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
    JTextArea jTextArea1 = new javax.swing.JTextArea();
    JLabel jLabel3 = new javax.swing.JLabel();
    JFormattedTextField jFormattedTextField1 = new javax.swing.JFormattedTextField();
    JFormattedTextField jFormattedTextField2 = new javax.swing.JFormattedTextField();
    JLabel jLabel4 = new javax.swing.JLabel();
    JFormattedTextField jFormattedTextField3 = new javax.swing.JFormattedTextField();
    JFormattedTextField jFormattedTextField4 = new javax.swing.JFormattedTextField();

    public Evenement(UserActif user) {
        super();
        this.user = user;
        initPanel();
    }

    @Override
    protected void initPanel() {
        JPanel b1 = new JPanel();
        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        jLabel1.setText("Intitulé");
        b1.add(jLabel1, BorderLayout.WEST);
        b1.add(jTextField1, BorderLayout.EAST);


        JPanel b2 = new JPanel();
        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        jLabel2.setText("Description");
        b2.add(jLabel2, BorderLayout.WEST);
        b1.add(jTextArea1, BorderLayout.EAST);

        JPanel b3 = new JPanel();
        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        jLabel3.setText("Début");
        b3.add(jLabel3, BorderLayout.WEST);
        b3.add(jFormattedTextField1, BorderLayout.CENTER);
        b3.add(jFormattedTextField2, BorderLayout.EAST);

        JPanel b4 = new JPanel();
        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        jLabel4.setText("Fin");
        b4.add(jLabel4, BorderLayout.WEST);
        b4.add(jFormattedTextField3, BorderLayout.CENTER);
        b4.add(jFormattedTextField4, BorderLayout.EAST);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.add(b1);
        content.add(b2);
        content.add(b3);
        content.add(b4);


        button.setText("Ajouter");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });


        this.panel.add(title, BorderLayout.NORTH);
        this.panel.add(content, BorderLayout.CENTER);
        this.panel.add(button, BorderLayout.SOUTH);
    }

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
//
//        CalendarService client = new CalendarService("komodo");
//        client.setUserCredentials("usernameIndisponible@gmail.com", "ilford35104313");
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
        
        
        
    }

    private static CalendarEventEntry createEvent(CalendarService service,
            String eventTitle, String eventContent, String recurData,
            boolean isQuickAdd, WebContent wc) throws ServiceException, IOException {
        CalendarEventEntry myEntry = new CalendarEventEntry();




        myEntry.setTitle(new PlainTextConstruct(eventTitle));
        myEntry.setContent(new PlainTextConstruct(eventContent));
        myEntry.setQuickAdd(isQuickAdd);
        myEntry.setWebContent(wc);

        // If a recurrence was requested, add it. Otherwise, set the
        // time (the current date and time) and duration (30 minutes)
        // of the event.
        if (recurData == null) {
            Calendar calendar = new GregorianCalendar();
            DateTime startTime = new DateTime(calendar.getTime(), TimeZone
                    .getDefault());

            calendar.add(Calendar.MINUTE, 30);
            DateTime endTime = new DateTime(calendar.getTime(),
                    TimeZone.getDefault());

            When eventTimes = new When();
            eventTimes.setStartTime(startTime);
            eventTimes.setEndTime(endTime);
            myEntry.addTime(eventTimes);
        } else {
            Recurrence recur = new Recurrence();
            recur.setValue(recurData);
            myEntry.setRecurrence(recur);
        }
        URL eventFeedUrl = new URL("https://www.google.com/calendar/feeds/usernameIndisponible@gmail.com/private/full");
        // Send the request and receive the response:
        return service.insert(eventFeedUrl, myEntry);
    }
}
