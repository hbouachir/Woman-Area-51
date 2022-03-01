package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Attachment;
import tn.esprit.spring.repositories.AttachmentRepository;
@Service
public class AttachmentService implements IAttachmentService {

	@Autowired
	AttachmentRepository att ;
	@Override
	public Attachment addAttachment(Attachment a) {
		// TODO Auto-generated method stub
		return att.save(a);
	}
	@Override
	public List<Attachment> showAllAttachment() {
		// TODO Auto-generated method stub
		return ( List<Attachment>) att.findAll() ;
	}
	@Override
	public Attachment UpdateAttachment(Attachment a) {
		// TODO Auto-generated method stub
		return att.save(a);
	}
	@Override
	public void deleteAttachment(Long idAttachment) {
		// TODO Auto-generated method stub
		att.deleteById(idAttachment);
	}
	@Override
	public Attachment showAttachment(Long idAttachment) {
		// TODO Auto-generated method stub
		return att.findById(idAttachment).orElse(null);
	}
}
