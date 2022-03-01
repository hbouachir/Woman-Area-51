package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Attachment;

public interface IAttachmentService {

	Attachment addAttachment(Attachment a);
	Attachment showAttachment(Long idAttachment);
	 List<Attachment> showAllAttachment();
	 Attachment UpdateAttachment (Attachment a);
	 void deleteAttachment (Long idAttachment);
}
