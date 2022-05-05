package tn.esprit.spring.womanarea51.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.google.zxing.WriterException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.donation;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.feedback;
import tn.esprit.spring.womanarea51.entities.fund;

@Service
public class EmailingServiceImp implements IEmailingService {

	@Autowired
	DonationServiceImp DSI;

	@Autowired
	FundServiceImp FSI;

	@Autowired
	feedbackServiceImp FBSI;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	IQRCodeGenerator IQRS;

	public void CreditCardDonation(User U, donation d) throws MessagingException, Exception {
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + d.getUser().getFirstName() + " " + d.getUser().getLastName() + ","
				+ "\n \n" + "Your donation amount of " + String.valueOf(d.getAmount()) + "DT has been confirmed for "
				+ d.getFund().getFundName() + "- " + d.getFund().getFundDescription() + "\nFundRaiser Link: "
				+ "https://www.womanarea51.ml/FundRaisers/" + d.getFund().getFundId().toString() + ".\n"
				+ "Thank you for your contribution.\n\n" + "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Donation confirmation");
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomaenArea51", res);

		emailSender.send(mm);
	}

	// Cash or cheque donation mail
	public void confirmdonation(User U, donation d) throws Exception {

		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + d.getUser().getFirstName() + " " + d.getUser().getLastName() + ","
				+ "\n \n" + "Your donation amount of " + String.valueOf(d.getAmount()) + "DT has been confirmed for "
				+ d.getFund().getFundName() + "- " + d.getFund().getFundDescription() + "\nFundRaiser Link: "
				+ "https://www.womanarea51.ml/FundRaisers/" + d.getFund().getFundId().toString() + ".\n"
				+ "Thank you for your contribution.\n\n" + "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Donation confirmation");
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomenArea51", res);

		emailSender.send(mm);
	}

	public void ParticipationConfirmation(User U, event e, String pathPDF) throws Exception {

		// Mail with Badge attachement
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "Your participation in the following event has been confirmed: \n" + "Event: " + e.getEventName()
				+ "- " + e.getDescription() + "\nLocation: " + e.getEventLocation() + "\nDate: \n" + "	Starts At :"
				+ dateFormat.format(e.getEventDateStart()) + "\n" + "	Ends At: "
				+ dateFormat.format(e.getEventDateEnd()) + "\n" + "Time: " + e.getEventTime() + "\nEvent Link: "
				+ "https://www.womanarea51.ml/Events/" + e.getEventId().toString() + ".\n"
				+ "You will find your access badge attached below. Please note that it is required to access the event.\n"
				+ "Thank you for your participation. We look forward to hearing your feedback on the event after attending.\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Participation confirmation");
		FileSystemResource fileSystemResource = new FileSystemResource(new File(pathPDF));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

		emailSender.send(mm);

	}

	// pdf generation for Participant badge
	public String GenerateBadge(User U, event e) throws WriterException, IOException, Exception {

		String pathPDF = e.getEventId() + "ID-" + U.getId() + ".pdf";
		String QRpath = e.getEventId() + "ID-" + U.getId() + "QR";
		String script1 = "   Participant: ".concat(U.getUsername()) + "\n\n";
		String script2 = "   Event: " + e.getEventName();
		String script3 = "PARTICIPANT";
		Text text1 = new Text(script1);
		Text text2 = new Text(script2);
		Text text3 = new Text(script3);
		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		DeviceRgb blue = new DeviceRgb(11, 170, 192);
		Paragraph p = new Paragraph();
		Paragraph p2 = new Paragraph();
		p.setFont(font);
		p.setBold();
		p.setFontSize(25);
		p.add(text1);
		p.add(text2);
		p2.setFont(font);
		p2.setBold();
		p2.setFontSize(40);
		p2.setFontColor(blue);
		p2.add(text3);
		String qrimg = IQRS.generateQRCodeImage("https://www.womanarea51.ml/Events/" + e.getEventId().toString(), 250,
				250, QRpath);

		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pathPDF));
		pdfDoc.addNewPage();
		Document document = new Document(pdfDoc);

		float width = pdfDoc.getDefaultPageSize().getWidth();
		float height = pdfDoc.getDefaultPageSize().getHeight();
		PdfCanvas canvas = new PdfCanvas(pdfDoc.getPage(1));
		canvas.rectangle(20, 20, width - 40, height - 40);
		canvas.stroke();
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		ImageData data = ImageDataFactory.create(res.getPath());

		Image image = new Image(data);
		image.setFixedPosition(112f, 450f);
		document.add(image);

		data = ImageDataFactory.create(qrimg);
		image = new Image(data);
		// set Absolute Position
		image.setFixedPosition(175f, 200f);
		// set Scaling
		image.setAutoScaleHeight(false);
		image.setAutoScaleWidth(false);
		// set Rotation

		document.add(image);
		document.showTextAligned(p2, 165, 170, null);
		document.showTextAligned(p, 40, 40, null);

		document.close();
		

		return (pathPDF);

	}
	
	public String GeneratgeBadgeByType(User U, event e,String type) throws IOException, WriterException, URISyntaxException {
		System.out.println(type+" *****************************");
		
		String pathPDF = e.getEventId() + "ID-" + U.getId() + ".pdf";
		String QRpath = e.getEventId() + "ID-" + U.getId() + "QR";
		String script1 = "   Member: "+U.getFirstName()+" "+U.getLastName()+ "\n\n";
		String script2 = "   Event: " + e.getEventName();
		String script3 = type;
		Text text1 = new Text(script1);
		Text text2 = new Text(script2);
		Text text3 = new Text(script3);
		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		DeviceRgb pink = new DeviceRgb(221, 26, 94);
		DeviceRgb green = new DeviceRgb(145, 182, 50);
		DeviceRgb blue = new DeviceRgb(11, 170, 192);
		Paragraph p = new Paragraph();
		Paragraph p2 = new Paragraph();
		p.setFont(font);
		p.setBold();
		p.setFontSize(25);
		p.add(text1);
		p.add(text2);

		p2.setFont(font);
		p2.setBold();
		p2.setFontSize(40);
		
		if (type.contentEquals("STAFF")) {
			
			p2.setFontColor(pink);
		}
		else if(type.contentEquals("SPEAKER")) {
			
			p2.setFontColor(green);
		}
		else
			
			p2.setFontColor(blue);
		
		
		p2.add(text3);
		String qrimg = IQRS.generateQRCodeImage("https://www.womanarea51.ml/Events/" + e.getEventId().toString(), 250,
				250, QRpath);

		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pathPDF));
		pdfDoc.addNewPage();
		Document document = new Document(pdfDoc);

		float width = pdfDoc.getDefaultPageSize().getWidth();
		float height = pdfDoc.getDefaultPageSize().getHeight();
		
		PdfCanvas canvas = new PdfCanvas(pdfDoc.getPage(1));
		canvas.rectangle(20, 20, width - 40, height - 40);
		canvas.stroke();
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		ImageData data = ImageDataFactory.create(res.getPath());

		Image image = new Image(data);
		image.setFixedPosition(112f, 450f);
		document.add(image);

		data = ImageDataFactory.create(qrimg);
		image = new Image(data);
		// set Absolute Position
		image.setFixedPosition(175f, 200f);
		// set Scaling
		image.setAutoScaleHeight(false);
		image.setAutoScaleWidth(false);
		// set Rotation

		document.add(image);
		

		if (type.contentEquals("STAFF")) {
			
			document.showTextAligned(p2, width/2 -63, 170, null);
		}
		else 
			document.showTextAligned(p2, width/2 -100, 170, null);
		
		document.showTextAligned(p, 40, 40, null);
		
		document.close();
		

		return (pathPDF);
		
	}
		
	public void DeleteBadgeFiles(User U, event e) {
		String pathPDF = e.getEventId() + "ID-" + U.getId() + ".pdf";
		String QRpath = e.getEventId() + "ID-" + U.getId() + "QR"+".png";
		File f = new File(pathPDF);
		try {
			if (f.delete()) 
			{
				System.out.println(f.getName() + " deleted"); 
			} else {
				System.out.println("failed");
			}
		} catch (

		Exception exe) {
			exe.printStackTrace();
		}
		
		File f2 = new File(QRpath);
		try {
			if (f2.delete()) // returns Boolean value
			{
				System.out.println(f2.getName() + " deleted"); 
			} else {
				System.out.println("failed");
			}
		} catch (

		Exception exe) {
			exe.printStackTrace();
		}
		
	}

	public void feedbackConfirmation(feedback f, User U) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "Your feedback for the following event has been confirmed: \n" + "Event: "
				+ f.getEvent_feedback().getEventName() + "- " + f.getEvent_feedback().getDescription() + "\nLocation: "
				+ f.getEvent_feedback().getEventLocation() + "\nDate: \n" + "	Starts At :"
				+ dateFormat.format(f.getEvent_feedback().getEventDateStart()) + "\n" + "	Ends At: "
				+ dateFormat.format(f.getEvent_feedback().getEventDateEnd()) + "\n" + "Time: "
				+ f.getEvent_feedback().getEventTime() + "\nEvent Link: " + "https://www.womanarea51.ml/Events/"
				+ f.getEvent_feedback().getEventId().toString() + ".\n"
				+ "Thank you for your feedback. We look forward to your participation in future events!\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Feedback confirmation");
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomaenArea51", res);

		emailSender.send(mm);
	}

	public void CancelParticipation(feedback f) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		User U = f.getParticipant();
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "You are no longer a participent in the following event: \n" + "Event: "
				+ f.getEvent_feedback().getEventName() + "- " + f.getEvent_feedback().getDescription() + "\nLocation: "
				+ f.getEvent_feedback().getEventLocation() + "\nDate: \n" + "	Starts At :"
				+ dateFormat.format(f.getEvent_feedback().getEventDateStart()) + "\n" + "	Ends At: "
				+ dateFormat.format(f.getEvent_feedback().getEventDateEnd()) + "\n" + "Time: "
				+ f.getEvent_feedback().getEventTime() + "\nEvent Link: " + "https://www.womanarea51.ml/Events/"
				+ f.getEvent_feedback().getEventId().toString() + ".\n"
				+ "If you think there has been an error, please contact us via our complaints section.\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Participation cancelled");
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomaenArea51", res);

		// ! add complaints section url!!!

		emailSender.send(mm);

	}

	public void StaffMail(User U, event e, String pathPDF) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "Your participation as a staff member in the following event has been confirmed: \n" + "Event: " + e.getEventName()
				+ "- " + e.getDescription() + "\nLocation: " + e.getEventLocation() + "\nDate: \n" + "	Starts At :"
				+ dateFormat.format(e.getEventDateStart()) + "\n" + "	Ends At: "
				+ dateFormat.format(e.getEventDateEnd()) + "\n" + "Time: " + e.getEventTime() + "\nEvent Link: "
				+ "https://www.womanarea51.ml/Events/" + e.getEventId().toString() + ".\n"
				+ "You will find your access badge attached below. Please note that it is required to access the event.\n"
				+ "Thank you for your participation. We look forward to working together on this and future events.\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("STAFF: Access Badge for event");
		FileSystemResource fileSystemResource = new FileSystemResource(new File(pathPDF));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

		emailSender.send(mm);
		
	}
	
	public void SpeakerMail(User U, event e, String pathPDF) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "Your participation as a speaker in the following event has been confirmed: \n" + "Event: " + e.getEventName()
				+ "- " + e.getDescription() + "\nLocation: " + e.getEventLocation() + "\nDate: \n" + "	Starts At :"
				+ dateFormat.format(e.getEventDateStart()) + "\n" + "	Ends At: "
				+ dateFormat.format(e.getEventDateEnd()) + "\n" + "Time: " + e.getEventTime() + "\nEvent Link: "
				+ "https://www.womanarea51.ml/Events/" + e.getEventId().toString() + ".\n"
				+ "You will find your access badge attached below. Please note that it is required to access the event.\n"
				+ "Thank you for your participation. We look forward to working together on this and future events.\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("SPEAKER: Access Badge for event");
		FileSystemResource fileSystemResource = new FileSystemResource(new File(pathPDF));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

		emailSender.send(mm);
		
	}

	public void VirtualEvent(User U, event e) throws Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "Your participation in the following event has been confirmed: \n" + "Event: " + e.getEventName()
				+ "- " + e.getDescription() + "\nVirtual meeting link: " + e.getEventLocation() + "\nDate: \n" + "	Starts At :"
				+ dateFormat.format(e.getEventDateStart()) + "\n" + "	Ends At: "
				+ dateFormat.format(e.getEventDateEnd()) + "\n" + "Time: " + e.getEventTime() + "\nEvent Link: "
				+ "https://www.womanarea51.ml/Events/" + e.getEventId().toString() + ".\n"
				+ "A good internet connection and a computer are recommended.\n"
				+ "Thank you for your participation. We look forward to hearing your feedback on the event after attending.\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Participation confirmation");
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomaenArea51", res);
		emailSender.send(mm);
		
	}

	public void eventUpdate(User U, event e,String pathPDF) throws Exception {
		
		String type;
		if (e.getType().toString()=="INPERSON")
			type="Location";
		else
			type="Virtual meeting link";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "Updates/changes have been added to an event you are participating in. Please note the new changes: \n" + "Event: " + e.getEventName()
				+ "- " + e.getDescription() + "\n"+type+": " + e.getEventLocation() + "\nDate: \n" + "	Starts At :"
				+ dateFormat.format(e.getEventDateStart()) + "\n" + "	Ends At: "
				+ dateFormat.format(e.getEventDateEnd()) + "\n" + "Time: " + e.getEventTime() + "\nEvent Link: "
				+ "https://www.womanarea51.ml/Events/" + e.getEventId().toString() + ".\n"
				+"We apologize for any inconvience. If you have any questions or concerns, please contact us through our complaints section.\n"
				+ "Thank you for your participation. We look forward to hearing your feedback on the event after attending.\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Event Updates");
		emailSender.send(mm);
		
	}
	public void Personalized(User U,String subject, String body) throws Exception {
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomenArea51", res);

		emailSender.send(mm);
		
	}
}



