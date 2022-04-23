package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.api.client.json.gson.GsonFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;


@Controller
public class GoogleCalController {

	private final static Log logger = LogFactory.getLog(GoogleCalController.class);
	private static final String APPLICATION_NAME = "";
	private static HttpTransport httpTransport;
	//private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	//private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static com.google.api.services.calendar.Calendar client;

	GoogleClientSecrets clientSecrets;
	GoogleAuthorizationCodeFlow flow;
	Credential credential;

	@Value("${google.client.client-id}")
	private String clientId;
	@Value("${google.client.client-secret}")
	private String clientSecret;
	@Value("${google.client.redirectUri}")
	private String redirectURI;

	private Set<Event> events = new HashSet<>();

	final DateTime date1 = new DateTime("2017-05-05T16:30:00.000+05:30");
	final DateTime date2 = new DateTime(new Date());

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@RequestMapping(value = "/login/google", method = RequestMethod.GET)
	public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
		return new RedirectView(authorize());
	}

	@RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
	public ResponseEntity<String> oauth2Callback(@RequestParam(value = "code") String code) {
		com.google.api.services.calendar.model.Events eventList;
		String message;
		try {
			TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
			credential = flow.createAndStoreCredential(response, "userID");
			client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
					.setApplicationName(APPLICATION_NAME).build();
			Events events = client.events();
			eventList = events.list("primary").setTimeMin(date1).setTimeMax(date2).execute();
			message = eventList.getItems().toString();
			System.out.println("My:" + eventList.getItems());
		} catch (Exception e) {
			logger.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ")."
					+ " Redirecting to google connection status page.");
			message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
					+ " Redirecting to google connection status page.";
		}

		System.out.println("cal message:" + message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	public Set<Event> getEvents() throws IOException {
		return this.events;
	}

	private String authorize() throws Exception {
		AuthorizationCodeRequestUrl authorizationUrl;
		if (flow == null) {
			Details web = new Details();
			web.setClientId(clientId);
			web.setClientSecret(clientSecret);
			clientSecrets = new GoogleClientSecrets().setWeb(web);
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
					Collections.singleton(CalendarScopes.CALENDAR)).build();
		}
		authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);
		System.out.println("cal authorizationUrl->" + authorizationUrl);
		return authorizationUrl.build();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	
		public void addevent() throws IOException{
			Event event = new Event();
			event.setDescription("Expert interview");
			event.setSummary("Oussama API");
			DateTime startDateTime = new DateTime("2022-04-15T09:00:00+01:00");
			EventDateTime start = new EventDateTime()
					    .setDateTime(startDateTime)
					    .setTimeZone("Africa/Tunis");
			event.setStart(start);
			DateTime endDateTime = new DateTime("2022-04-16T17:00:00+01:00");
				EventDateTime end = new EventDateTime()
				    .setDateTime(endDateTime)
				    .setTimeZone("Africa/Tunis");
			event.setEnd(end);
			//event.setSummary(eventEntity.getSummary());
			//event.setLocation(location);
			//event.setDescription(description);
			client.events().insert("primary", event).execute();
		}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addevent2() throws IOException{
		Event event = new Event();
		event.setDescription("Expert interview");
		event.setSummary("Oussama API");
		DateTime startDateTime = new DateTime("2022-04-15T09:00:00+01:00");
		EventDateTime start = new EventDateTime()
				.setDateTime(startDateTime)
				.setTimeZone("Africa/Tunis");
		event.setStart(start);
		DateTime endDateTime = new DateTime("2022-04-16T17:00:00+01:00");
		EventDateTime end = new EventDateTime()
				.setDateTime(endDateTime)
				.setTimeZone("Africa/Tunis");
		event.setEnd(end);
		//event.setSummary(eventEntity.getSummary());
		//event.setLocation(location);
		//event.setDescription(description);
		client.events().insert("oussama.lahouel@esprit.tn", event).execute();
	}
}