package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.Attachment;

public interface IAttachmentService {

	Attachment addAttachment(Attachment a);
	Attachment showAttachment(Long idAttachment);
	 List<Attachment> showAllAttachment();
	 Attachment UpdateAttachment (Attachment a);
	 void deleteAttachment (Long idAttachment);
}
