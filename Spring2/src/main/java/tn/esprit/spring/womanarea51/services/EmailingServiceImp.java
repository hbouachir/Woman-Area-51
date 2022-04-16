package tn.esprit.spring.womanarea51.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.itextpdf.text.PageSize;
import com.google.zxing.WriterException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.donation;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.feedback;

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
				+ d.getFund().getFundName() + "- " + d.getFund().getFundDescription() + ".\n"
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
				+ d.getFund().getFundName() + "- " + d.getFund().getFundDescription() + ".\n"
				+ "Thank you for your contribution.\n\n" + "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Donation confirmation");
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomenArea51", res);

		emailSender.send(mm);
	}

	public void ParticipationConfirmation(User U, event e, String pathPDF) throws Exception {

		// Mail with Badge attachement
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "Your participation in the following event has been confirmed: \n" + "Event :" + e.getEventName()
				+ "- " + e.getDescription() + "\nLocation: " + e.getEventLocation() + "\nDate and time: "
				+ dateFormat.format(e.getEventDate()) + " " + e.getEventTime() + ".\n"
				+ "Thank you for your participation. We look forward to hearing your feedback on the event after attending.\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Participation confirmation");
		FileSystemResource fileSystemResource = new FileSystemResource(new File(pathPDF));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		emailSender.send(mm);

	}

	// pdf generation for badge
	public String GenerateBadge(User U, event e) throws WriterException, IOException, Exception {

		String pathPDF = e.getEventId() + "ID-" + U.getId() + ".pdf";
		String QRpath = e.getEventId() + "ID-" + U.getId() + "QR";
		String script1 ="   Participant: ".concat(U.getUsername())+"\n\n";
		String script2="   EventId: "+e.getEventId().toString();
		Text text1 = new Text(script1); 
		Text text2 = new Text(script2); 
		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);     
	
	      Paragraph p = new Paragraph();
	      p.setFont(font);
	      p.setBold();
	      p.setFontSize(25);
	      p.add(text1);
	      p.add(text2);
	      
		String qrimg = IQRS.generateQRCodeImage(e.getEventId().toString() + e.getEventName(), 250, 250, QRpath);
		
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pathPDF));
		Document document = new Document(pdfDoc);
		
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		ImageData data = ImageDataFactory.create(res.getPath());
		
		Image image = new Image(data);
		image.setFixedPosition(112f,450f);
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
		document.showTextAligned(p, 40, 40, null);

		document.close();
		
		return (pathPDF);

	}

	public void feedbackConfirmation(feedback f, User U) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "Your feedback for the following event has been confirmed: \n" + "Event :"
				+ f.getEvent_feedback().getEventName() + "- " + f.getEvent_feedback().getDescription() + "\nLocation: "
				+ f.getEvent_feedback().getEventLocation() + "\nDate and time: "
				+ dateFormat.format(f.getEvent_feedback().getEventDate()) + " " + f.getEvent_feedback().getEventTime()
				+ ".\n" + "Thank you for your feedback. We look forward to your participation in future events!\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Feedback confirmation");
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomaenArea51", res);

		emailSender.send(mm);
	}

	public void CancelParticipation(feedback f) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

		User U = f.getParticipant();
		MimeMessage mm = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);
		mimeMessageHelper.setFrom(U.getEmail());
		mimeMessageHelper.setTo(U.getEmail());
		mimeMessageHelper.setText("Hello " + U.getFirstName() + " " + U.getLastName() + "," + "\n \n"
				+ "You are no longer a participent in the following event: \n" + "Event :"
				+ f.getEvent_feedback().getEventName() + "- " + f.getEvent_feedback().getDescription() + "\nLocation: "
				+ f.getEvent_feedback().getEventLocation() + "\nDate and time: "
				+ dateFormat.format(f.getEvent_feedback().getEventDate()) + " " + f.getEvent_feedback().getEventTime()
				+ ".\n" + "If you think there has been an error, please contact us via our complaints section.\n\n"
				+ "Regards,\n" + "The womenArea51 Team");
		mimeMessageHelper.setSubject("Participation cancelled");
		FileSystemResource res = new FileSystemResource(
				new File(ClassLoader.getSystemResource("static/images/logoWomenArea51.png").toURI()));
		mimeMessageHelper.addInline("WomaenArea51", res);

		// ! add complaints section url!!!

		emailSender.send(mm);

	}
}
