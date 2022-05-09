package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.File;
import tn.esprit.spring.womanarea51.entities.FileComplaint;
import tn.esprit.spring.womanarea51.entities.User;

public interface IFileComplaintService {
	FileComplaint addFileComplaint(MultipartFile file, Long idCourse, User U, FileComplaint fichi);
	 void removeFile(Long idFile,Long idCourse, User U );
	 List<FileComplaint> findAll();
}
