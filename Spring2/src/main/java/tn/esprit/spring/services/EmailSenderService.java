package tn.esprit.spring.services;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	      @Autowired
	      private JavaMailSender javaMailSender;

	        public void sendMail(String toEmail,String body,String subject,String attachment) throws MessagingException {

	        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
	        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
	        mimeMessageHelper.setFrom("moez1997meerigui@gmail.com");
	        mimeMessageHelper.setTo(toEmail);
	        mimeMessageHelper.setText(body);
	        mimeMessageHelper.setSubject(subject);
	       
	        FileSystemResource fileSystemResource=
	                new FileSystemResource(new File(attachment));
	        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
	                fileSystemResource);
	        
	        
	        javaMailSender.send(mimeMessage);
	        System.out.println("Mail sent successfully..");


	    }
}
