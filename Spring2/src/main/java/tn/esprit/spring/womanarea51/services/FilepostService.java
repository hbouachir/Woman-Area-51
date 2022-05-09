package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Filepost;
import tn.esprit.spring.womanarea51.entities.User;

public interface FilepostService {
	   Filepost addFile(MultipartFile f, Long idPost, User U);
	    void removeFile(Long idFile,Long idPost, User U );
	    //List<File> findFilesByCourse();
	    List<Filepost> findAll();
}
