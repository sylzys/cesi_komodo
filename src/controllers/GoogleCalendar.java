package controllers;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar.Calendars;
import com.google.api.services.calendar.Calendar.Calendars.Get;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Yaniv Inbar
 */
public class GoogleCalendar {

  /** Global instance of the HTTP transport. */
  public  HttpTransport HTTP_TRANSPORT;// = new NetHttpTransport();

  /** Global instance of the JSON factory. */
  public  JsonFactory JSON_FACTORY;// = new JacksonFactory();

  public  com.google.api.services.calendar.Calendar client;

  public java.util.List<Calendar> addedCalendarsUsingBatch = Lists.newArrayList();

  /** Authorizes the installed application to access user's protected data. */
  
  public GoogleCalendar()
  {
      HTTP_TRANSPORT = new NetHttpTransport();
      JSON_FACTORY = new JacksonFactory();
  }
  public Credential authorize() throws Exception {
    // load client secrets
    
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
        JSON_FACTORY, GoogleCalendar.class.getResourceAsStream("client_secrets.json"));
    if(clientSecrets.getDetails().getClientId().startsWith("Enter")
        || clientSecrets.getDetails().getClientSecret().startsWith("Enter")) {
      System.out.println(
          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=calendar "
          + "into calendar-cmdline-sample/src/main/resources/client_secrets.json");
      System.exit(1);
    }
    // set up file credential store
    FileCredentialStore credentialStore = new FileCredentialStore(
        new File(System.getProperty("user.home"), ".credentials/calendar.json"), JSON_FACTORY);
    // set up authorization code flow
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
        Collections.singleton(CalendarScopes.CALENDAR)).setCredentialStore(credentialStore).build();
    // authorize
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

  public  void init() {
    try {
      try {
        // authorization
        Credential credential = authorize();

        // set up global Calendar instance
        client = new com.google.api.services.calendar.Calendar.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
            "test-google-calendar").build();
        
        
        // run commands
//        showCalendars();
//        //addCalendarsUsingBatch();
//        //Calendar calendar = addCalendar();
//        //updateCalendar(calendar);
//        Calendar calendar = getCalendars("My agenda");
//        
//        if(calendar == null){
//        	
//        	calendar = addCalendar();
//        	//calendar = addCalendarsUsingBatch("My agenda");
//        }
//        Date startDate = new Date();
//        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
//        Date endDate = new Date(startDate.getTime() + 3600000);
//        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
        

//        Event event = new Event();
//		Date startDate = new Date();
//		Date endDate = new Date(startDate.getTime() + 3600000);
//		DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
//		event.setStart(new EventDateTime().setDateTime(start));
//		DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
//		event.setEnd(new EventDateTime().setDateTime(end));
//
//		event.setSummary("plop");
//        
//        String title = "Nouvel �v�nement";
//        
//        
//        newEvent(start, end, title);
//        addEvent(calendar,event);
//        showEvents(calendar);
        //deleteCalendarsUsingBatch();
        //deleteCalendar(calendar);

      } catch (IOException e) {
        System.err.println(e.getMessage());
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
    //System.exit(1);
  } 

  public  void showCalendars() throws IOException {
    View.header("Show Calendars");
    CalendarList feed = client.calendarList().list().execute();
   
    
    View.display(feed);   
  }
  
  public  Calendar getCalendars(String name) throws IOException {
	    View.header("Show Calendars");
	    CalendarList feed = client.calendarList().list().execute();

	   
	    Calendar calendar = null;
	  
	    for (CalendarListEntry entry : feed.getItems()) {
	        System.out.println();
	        System.out.println("-----------------------------------------------");
	        if(entry.getSummary().matches(name)){
	        	
	        	calendar = client.calendars().get(entry.getId()).execute();
	        	return calendar;   	   
	        }
	      }
		 return null;
	  }

  public  Calendar addCalendarsUsingBatch(String nameCalendar) throws IOException {
    View.header("Add Calendars using Batch");
    BatchRequest batch = client.batch();

    // Create the callback.
    JsonBatchCallback<Calendar> callback = new JsonBatchCallback<Calendar>() {

      public void onSuccess(Calendar calendar, GoogleHeaders responseHeaders) {
        View.display(calendar);
        addedCalendarsUsingBatch.add(calendar);
      }

      @Override
      public void onFailure(GoogleJsonError e, GoogleHeaders responseHeaders) {
        System.out.println("Error Message: " + e.getMessage());
      }
    };
    
    

    // Create 2 Calendar Entries to insert.
    Calendar entry1 = new Calendar().setSummary(nameCalendar);
    client.calendars().insert(entry1).queue(batch, callback);   

//    Calendar entry2 = new Calendar().setSummary("Calendar for Testing 2");
//    client.calendars().insert(entry2).queue(batch, callback);

    batch.execute();
    return entry1;
  }

  public  Calendar addCalendar() throws IOException {
    View.header("Add Calendar");
    Calendar entry = new Calendar();
    entry.setSummary("My agenda");
    Calendar result = client.calendars().insert(entry).execute();
    View.display(result);
    return result;
  }

  public  Calendar updateCalendar(Calendar calendar) throws IOException {
    View.header("Update Calendar");
    Calendar entry = new Calendar();
    entry.setSummary("Updated Calendar for Testing");
    Calendar result = client.calendars().patch(calendar.getId(), entry).execute();
    View.display(result);
    return result;
  }


  public  void addEvent(Calendar calendar,Event event) throws IOException {
    View.header("Add Event");
   
    Event result = client.events().insert(calendar.getId(), event).execute();
    View.display(result);
  }

  public  Event newEvent(DateTime start,DateTime end,String title) {
    Event event = new Event();   
    event.setSummary(title);
    Date startDate = new Date();
    Date endDate = new Date(startDate.getTime() + 3600000);
   
    event.setStart(new EventDateTime().setDateTime(start));  
    event.setEnd(new EventDateTime().setDateTime(end));
    return event;
  }

  public  void showEvents(Calendar calendar) throws IOException {
    View.header("Show Events");
    Events feed = client.events().list(calendar.getId()).execute();
    View.display(feed);
  }
  
  
  

  public  void deleteCalendarsUsingBatch() throws IOException {
    View.header("Delete Calendars Using Batch");
    BatchRequest batch = client.batch();
    for (Calendar calendar : addedCalendarsUsingBatch) {
      client.calendars().delete(calendar.getId()).queue(batch, new JsonBatchCallback<Void>() {

        public void onSuccess(Void content, GoogleHeaders responseHeaders) {
          System.out.println("Delete is successful!");
        }

        @Override
        public void onFailure(GoogleJsonError e, GoogleHeaders responseHeaders) {
          System.out.println("Error Message: " + e.getMessage());
        }
      });
    }

    batch.execute();
  }

  public  void deleteCalendar(Calendar calendar) throws IOException {
    View.header("Delete Calendar");
    client.calendars().delete(calendar.getId()).execute();
  }
}

