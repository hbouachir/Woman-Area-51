package tn.esprit.spring.womanarea51.services;

import java.io.File;
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
				new File(ClassLoader.getSystemResource("static/images/logo.png").toURI()));
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
				new File(ClassLoader.getSystemResource("static/images/logo.png").toURI()));
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
	public String GenerateBadge(User U, event e) throws Exception {

		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDRectangle.A6);
		document.addPage(page);

		String script = "  ParticipantId: ".concat(U.getUsername()).concat("                 EventId :")
				.concat(e.getEventId().toString());
		Path path = Paths.get(ClassLoader.getSystemResource("static/images/logo.png").toURI());
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		PDImageXObject image = PDImageXObject.createFromFile(path.toAbsolutePath().toString(), document);

		float x = (PageSize.A6.getWidth() - image.getWidth()) / 2;
		float y = (PageSize.A6.getHeight() - image.getHeight()) / 2;
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);

		contentStream.beginText();
		contentStream.showText(script);
		contentStream.endText();

		contentStream.drawImage(image, x - 2, y - 2);
		contentStream.close();
		String pathPDF = e.getEventId() + "ID-" + U.getId() + ".pdf";
		document.save(pathPDF);
		document.close();
		System.out.println("created pdf :D");
		// Pdf created

		return pathPDF;

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
				new File(ClassLoader.getSystemResource("static/images/logo.png").toURI()));
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
				new File(ClassLoader.getSystemResource("static/images/logo.png").toURI()));
		mimeMessageHelper.addInline("WomaenArea51", res);

		// ! add complaints section url!!!

		emailSender.send(mm);

	}
}
