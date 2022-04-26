package tn.esprit.spring.womanarea51.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Attachment;
import tn.esprit.spring.womanarea51.services.IAttachmentService;
@RestController
public class AttachmentController {

	@Autowired
	IAttachmentService att ;
	@PostMapping("/addAttachment")
	public Attachment addAttachment(@RequestBody Attachment a){
		return att.addAttachment(a);
	}
	@DeleteMapping("/deleteAttachment/{idAttachment}")
	public void deleteAttachment(@PathVariable long idAttachment){
		att.deleteAttachment(idAttachment);
	}
	@PutMapping("/updateAttachment")
	public Attachment updateAttachment(@RequestBody Attachment a){
		return att.UpdateAttachment(a);
		}
	@GetMapping("/getOneAttachment/{idAttachment}")
	public Attachment showAttachment(@PathVariable long idAttachment ){
		return att.showAttachment(idAttachment)	;	
	}
	@GetMapping("/ShowAllAttachment")
	public List<Attachment> showAllAttachment(){	
		return att.showAllAttachment();			
	}
}
