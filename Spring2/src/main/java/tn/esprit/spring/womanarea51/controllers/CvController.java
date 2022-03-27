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

import tn.esprit.spring.womanarea51.entities.Cv;
import tn.esprit.spring.womanarea51.services.ICvService;

@RestController
public class CvController {
	@Autowired
    ICvService cs ;
	@PostMapping("/addCv")
	public Cv addCv (@RequestBody Cv c){
		return cs.addCv(c);
	}
	@DeleteMapping("/deleteCv/{cvId}")
	public void deleteCv(@PathVariable long cvId){
		cs.deleteCv(cvId);
	}
	@PutMapping("/updateCv")
	public Cv updateCv(@RequestBody Cv c){
		return cs.UpdateCv(c);
		}
	@GetMapping("/getOneCv/{cvId}")
	public Cv showCv(@PathVariable long cvId ){
		return cs.ShowCv(cvId);
	}
	@GetMapping("/ShowAllCv")
	public List<Cv> showAllCv(){	
		return cs.ShowAllCv();		
	}
}
