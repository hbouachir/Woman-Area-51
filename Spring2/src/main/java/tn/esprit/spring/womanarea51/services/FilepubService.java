package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Filepub;
import tn.esprit.spring.womanarea51.entities.User;

public interface FilepubService {
	 Filepub addFilepub(MultipartFile file, Long idPub, User U);
	    void removeFile(Long idFile,Long idPub, User U );
	    //List<File> findFilesByCourse();
	    List<Filepub> findAll();
}
