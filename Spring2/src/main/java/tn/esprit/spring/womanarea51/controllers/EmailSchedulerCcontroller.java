package tn.esprit.spring.womanarea51.controllers;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.EmailJob;
import tn.esprit.spring.womanarea51.entities.EmailRequest;
import tn.esprit.spring.womanarea51.entities.EmailResponse;


@RestController
public class EmailSchedulerCcontroller {
	
	@Autowired
	private Scheduler scheduler;
	 
	@PostMapping("/schedule/email")
	public ResponseEntity<EmailResponse> scheduleEmail(){
		EmailRequest emailRequest=new EmailRequest();
		emailRequest.setBody("testing");
		emailRequest.setDateTime(LocalDateTime.now().plusSeconds(2));
		System.out.println(emailRequest.getDateTime());
		emailRequest.setEmail("nadaazzabi1@gmail.com");
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
