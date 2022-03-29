package tn.sirine.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.sirine.spring.entities.Mensaje;
import tn.sirine.spring.service.IMessageServiceA;

@RestController
public class homeController {

	@Autowired
	IMessageServiceA imsg;
	@GetMapping("/listemsg")
	@ResponseBody
	List<Mensaje> listedePosts(){
		return imsg.getAllMessage();
	}
}
