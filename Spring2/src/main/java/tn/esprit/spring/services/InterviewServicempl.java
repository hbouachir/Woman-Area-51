package tn.esprit.spring.services;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Contract;
import tn.esprit.spring.entities.ContractKey;
import tn.esprit.spring.entities.Interview;
import tn.esprit.spring.entities.InterviewKey;
import tn.esprit.spring.entities.JobOffer;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Valid;
import tn.esprit.spring.repositories.ContractRepository;
import tn.esprit.spring.repositories.InterviewRepository;
import tn.esprit.spring.repositories.JobOfferRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class InterviewServicempl implements InterviewService {
	@Autowired
	InterviewRepository ir ;
	@Autowired
	UserRepository ur ;
	@Autowired
	JobOfferRepository jr ;
	@Autowired
	ContractRepository cr ;
	@Autowired
    private JavaMailSender javaMailSender;
	@Override
	public List<Interview> showAllInterview() {
		// TODO Auto-generated method stub
		return ( List<Interview>) ir.findAll() ;
	}

	@Override
	public void addInterview(Interview i, Long idOffer, Long userId) {
		// TODO Auto-generated method stub
		InterviewKey interviewKey = new InterviewKey();
		interviewKey.setIdOffer(idOffer);
		interviewKey.setUserId(userId);
		i.setIdInterview(interviewKey);
		ir.save(i);
	}

	@Override
	public void UpdateInterview(Interview i, Long idOffer, Long userId) {
		// TODO Auto-generated method stub
		InterviewKey interviewKey = new InterviewKey();
		interviewKey.setIdOffer(idOffer);
		interviewKey.setUserId(userId);
		i.setIdInterview(interviewKey);
		ir.save(i);
	}

	@Override
	public Set<String> ListOfAcceptedUser() {
		// TODO Auto-generated method stub
		return ir.ListOfAcceptedUser();
	}

	@Override
	public Set<String> ListOfPendingUser() {
		// TODO Auto-generated method stub
		return  ir.ListOfPendingUser();
	}
	@Override
	public Set<String> ListOfRejectedUser() {
		// TODO Auto-generated method stub
		return ir.ListOfRejectedUser();
	}

	@Override
	public List<Interview> listInterviewsParUser(Long userId) {
		// TODO Auto-generated method stub
		return (List<Interview>) ir.listInterviewsParUser(userId);
	}

	@Override
	public void generateContract(Interview i) {
		// TODO Auto-generated method stub
		ContractKey k = new ContractKey();
		Contract c = new Contract();
		i.setValid(Valid.Accepted);
		Optional<User> user =  ur.findById(i.getIdInterview().getUserId());
		Optional<JobOffer> offer =  jr.findById(i.getIdInterview().getIdOffer());
	    k.setUserId(user.get().getUserId());
	    k.setIdOffer(offer.get().getIdOffer());
	    c.setUserName(user.get().getUserName());
	    c.setEmail(user.get().getEmail());
	    c.setCompanyName(offer.get().getCompanyName());
	    c.setOfferTitle(offer.get().getOfferTitle());
	    c.setSalaire(offer.get().getSalaire());
	    c.setStartDate(offer.get().getStartDate());
	    c.setEndDate(offer.get().getEndDate());
	    c.setTypeContract(offer.get().getTypeContract());
	    c.setContractKey(k);
	    ir.save(i);
	    cr.save(c);  		
	}
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

	@Override
	public List<Contract> ContractParUser(Long userId) {
		// TODO Auto-generated method stub
		return cr.ContractParUser(userId);
	}

	@Override
	public void PostToOffer(Long idOffer, Long userId) {
		// TODO Auto-generated method stub
		InterviewKey interviewKey = new InterviewKey();
		interviewKey.setIdOffer(idOffer);
		interviewKey.setUserId(userId);
		Interview i = new Interview();
		i.setUrlMeet("meet");
	    i.setIdInterview(interviewKey);
		ir.save(i);
	}

	@Override
	public void DelteInterview(Long idOffer, Long userId) {
		// TODO Auto-generated method stub
		Interview i = new Interview();
		InterviewKey interviewKey = new InterviewKey();
		interviewKey.setIdOffer(idOffer);
		interviewKey.setUserId(userId);
		i.setIdInterview(interviewKey);
		ir.delete(i);
		
	}

	@Override
	public void rejectUser(Interview i) {
		// TODO Auto-generated method stub
		i.setValid(Valid.Rejected);
		ir.save(i);
		
	}

	@Override
	public void PendUser(Interview i) {
		// TODO Auto-generated method stub
		i.setValid(Valid.Pending);
		ir.save(i);
		
	}

	


	

}
