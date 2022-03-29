package tn.esprit.spring.womanarea51.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;



import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.feedback;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IEmailScheduling;
import tn.esprit.spring.womanarea51.services.IEventService;
import tn.esprit.spring.womanarea51.services.IUserService;
import tn.esprit.spring.womanarea51.services.IfeedbackService;

@RestController
public class feedbackController {
	
	@Autowired
    IfeedbackService IFBS;
	
	@Autowired
	IUserService IUS;
	
	@Autowired
    IEventService IES;
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	UserRepository UR;
	
	@Autowired
	IEmailScheduling IemailS;
	
	
	@PostMapping("/participate/{idevent}")
	@ResponseBody
	void Partcipate(@PathVariable ("idevent") Long eventId) throws URISyntaxException, IOException, DocumentException {
		System.out.println("hello sir");
		//User U=UR.findByUsername(authentication.getName()).orElse(null);
		feedback f=new feedback();
		
		event e=IES.FindEvent(eventId);
		User U=IUS.findOne(2L);
		f.setParticipant(U);
		
		if (e.getPlaces()!= null)
		{
			e.setPlaces(e.getPlaces()-1);
			System.out.println(e.getPlaces());
			IES.EditEvent(e);
		}
		f.setEvent_feedback(e);
		IFBS.Participate(f);
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");  
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(U.getEmail()); 
        message.setSubject("Participation confirmation"); 
        message.setText("Hello "+ f.getParticipant().getFirstName() +" "+ f.getParticipant().getLastName()+","+"\n \n"
        		+"Your participation in the following event has been confirmed: \n"
        		+"Event :"+e.getDescription()
        		+"\nLocation: "+e.getEventLocation()
        		+"\nDate and time: "+dateFormat.format(e.getEventDate())
        		+".\n"
        		+ "Thank you for your participation. We look forward to hearing your feedback on the event after attending.\n\n"
        		+ "Regards,\n"
        		+ "The womenArea51 Team");
       try {
    	   emailSender.send(message);
       }catch(Exception ee) {
    	   System.out.println(ee.getMessage());
       }
        
        //pdf generation for badge
        System.out.println("hiii");
 //       Path path1 = Paths.get(ClassLoader.getSystemResource("logo.png").toURI());
        System.out.println("hello");
/*        Rectangle one = new Rectangle(70,140);
        Document document = new Document(one);
        
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        
        PDImageXObject image = PDImageXObject.createFromFile(path1.toAbsolutePath().toString(), document);
        contentStream.drawImage(image, 0, 0);
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.beginText();
        contentStream.showText(e.getEventId()+"\n"+U.getUsername());
        contentStream.endText();
        contentStream.close();
        String path = e.getEventId()+"ID-"+U.getId()+".pdf";
        
        
		document.close();

        System.out.println("before path : ");
        
        System.out.println(path);
        document.save(path);

//        Path path = Paths.get(ClassLoader.getSystemResource("/static/images/logo.png").toURI());
        String path = new ClassPathResource("/static/images/logo.png").getPath();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextImageExample.pdf"));
        document.open();
        Image img = Image.getInstance(path);
        document.add(img);

        document.close();
*/
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A6);
        document.addPage(page);
        
        String script="Participant: ".concat(U.getUsername()).concat(" Event :").concat(e.getEventId().toString());
        Path path = Paths.get(ClassLoader.getSystemResource("static/images/logo.png").toURI());
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDImageXObject image 
          = PDImageXObject.createFromFile(path.toAbsolutePath().toString(), document);
        
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
        
        contentStream.beginText();
        contentStream.showText(script); 
        contentStream.endText();
        contentStream.drawImage(image,0,0 );
        contentStream.close();
        String pathPDF = e.getEventId()+"ID-"+U.getId()+".pdf";
        document.save(pathPDF);
        document.close();
		System.out.println("created pdf :D");
		IemailS.scheduleEmail(U.getEmail(), U.getUsername(), e);
		
		
	}
	
	
	@PutMapping("/Feedback/{event}")
	feedback InputFeedback(@RequestBody feedback f,@PathVariable("event") Long eventId, Authentication authentication) throws DocumentException, IOException, URISyntaxException {
		User U=UR.findByUsername(authentication.getName()).orElse(null);
		f=IFBS.calculRating(f);
		System.out.println(f.getRating().toString());
		event e=IES.FindEvent(eventId);
		f.setParticipant(U);
		f.setEvent_feedback(e);
		System.out.println(e.getEventId());
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");  
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(f.getParticipant().getEmail()); 
        message.setSubject("Participation confirmation"); 
        message.setText("Hello "+ U.getFirstName() +" "+ U.getLastName()+","+"\n \n"
        		+"Your feedback for the following event has been confirmed: \n"
        		+"Event :"+e.getDescription()
        		+"\nLocation: "+e.getEventLocation()
        		+"\nDate and time: "+dateFormat.format(e.getEventDate())
        		+".\n"
        		+ "Thank you for your feedback. We look forward to your participation in future events!\n\n"
        		+ "Regards,\n"
        		+ "The womenArea51 Team");
        emailSender.send(message);
        
 

   
		
	return IFBS.EditFeedback(f);
				
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/remove-feedback/{feedbackId}")
	
	void RemoveFeedback(Authentication authentication,@PathVariable("feedbackId") Long id) {
		feedback f=IFBS.FindFeedback(id);
		f.setComment(null);
		f.setFutureEvents(null);
		f.setLocation(null);
		f.setObject(null);
		f.setOrganizers(null);
		f.setRating(null);
		f.setRecommend(null);
		f.setService(null);
		f.setAddedValue(null);
		IFBS.EditFeedback(f);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/remove-participant/{feedbackId}")
	@ResponseBody
	void RemoveParticipant(Authentication authentication,@PathVariable("feedbackId") Long id) {
		feedback f=IFBS.FindFeedback(id);
		event e=f.getEvent_feedback();
		e.setPlaces(e.getPlaces()+1);
		IES.EditEvent(e);
		
		IFBS.DeleteFeedback(f);
	}
	
	
	@GetMapping("/find-feedbacks")
	List<feedback> FindFeedbacks() {
		return IFBS.ListFeedbacks();
	}
	
	@GetMapping("/find-feedbacks-byEvent/{event-id}")
	List<feedback> FindFeedbacksByEvent(@PathVariable("event-id") Long id) {
		return IFBS.FindFeedbacksByEvent(id);
	}
	
	@GetMapping("/find-feedbacks-byParticipant/{user-id}")
	List<feedback> FindFeedbacksByParticipant(@PathVariable("user-id") Long id) {
		return IFBS.FindFeedbacksByParticipant(id);
	}
	
	@GetMapping("/find-feedbacks/{event-id}/{user-id}")
	feedback FindFeedbacksByEventAndUSer(@PathVariable("event-id") Long eventId, @PathVariable("user-id") Long userId) {
		return IFBS.FindFeedbackByUserAndEvent(userId,eventId).get(0);
	}
	
	
	@GetMapping("/AVG-event-rating/{event-id}")
	double AvgRatingPerEvent(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		return IFBS.AVGEventRating(list);
	}
	
	@GetMapping("/Min-event-rating/{event-id}")
	double MinRatingPerEvent(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		return IFBS.MinEventRating(list);
	}
	
	@GetMapping("/Max-event-rating/{event-id}")
	double MaxRatingPerEvent(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		return IFBS.MaxEventRating(list);
	}
	
	

}
