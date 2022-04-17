package tn.esprit.spring.womanarea51.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.EmailJob;
import tn.esprit.spring.womanarea51.entities.EmailRequest;
import tn.esprit.spring.womanarea51.entities.EmailResponse;
import tn.esprit.spring.womanarea51.entities.event;

@Service
public class EmailSchedulingServiceImp implements IEmailScheduling {

	@Autowired
	private Scheduler scheduler;
	
	
	@Override
	
	public ResponseEntity<EmailResponse> scheduleEmail(String email, String username, event e){
		
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String StringDate=dateFormat.format(e.getEventDate());
		System.out.println(StringDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime eventDate = LocalDateTime.parse(StringDate, formatter);

		EmailRequest emailRequest=new EmailRequest();
		emailRequest.setBody("Hello "+ username+", \n"
				+"We noticed you haven't submitted your feedback for your participation in the following event:\n"
				+"Event:"+ e.getDescription()
				+"\n Event Date: "+e.getEventDate()
				+"\n Event location: "+e.getEventLocation()
        		+"\n We are interested in hearing what you have to say about this event. Your feedback would give us better understanding of your interests. \n"
        		+ "\n Thank you for your participation. We look forward to your participation in future events!\n\n"
        		+ "Regards,\n"
        		+ "The womenArea51 Team");
		emailRequest.setDateTime(eventDate.plusDays(2));
		System.out.println("email scheduled for"+emailRequest.getDateTime());
		emailRequest.setEmail(email);
		emailRequest.setSubject("testing quartz scheduler");
		
		
		
		try {
	            ZonedDateTime dateTime = ZonedDateTime.of(emailRequest.getDateTime(),Calendar.getInstance().getTimeZone().toZoneId());
	            if(dateTime.isBefore(ZonedDateTime.now())) {
	                EmailResponse EmailResponse = new EmailResponse(false,
	                        "dateTime must be after current time");
	                return ResponseEntity.badRequest().body(EmailResponse);
	            }

	            JobDetail jobDetail = buildJobDetail(emailRequest);
	            Trigger trigger = buildTrigger(jobDetail, dateTime);
	            System.out.println(dateTime);
	            scheduler.scheduleJob(jobDetail, trigger);

	            EmailResponse emailResponse = new EmailResponse(true,
	                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Scheduled Successfully :D");
	            return ResponseEntity.ok(emailResponse);
	        } catch (SchedulerException ex) {
	            

	            EmailResponse emailResponse = new EmailResponse(false,
	                    "Error scheduling email :(");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emailResponse);
	        }
	    
		
	}
	private JobDetail buildJobDetail(EmailRequest scheduleEmailRequest) {
		JobDataMap jobDataMap =new JobDataMap();

		jobDataMap.put("email", scheduleEmailRequest.getEmail());
		jobDataMap.put("subject", scheduleEmailRequest.getSubject());
		jobDataMap.put("body", scheduleEmailRequest.getBody());
		
		return JobBuilder.newJob(EmailJob.class)
				.withIdentity(UUID.randomUUID().toString(),"email-jobs")
				.withDescription("Send Email Job")
				.usingJobData(jobDataMap)
				.storeDurably()
				.build();
		
		
	}
	
	private Trigger buildTrigger (JobDetail jobDetail, ZonedDateTime startAt) {
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "email-triggers")
				.startAt(Date.from(startAt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
				.build();
	}
	
}
