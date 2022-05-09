package tn.esprit.spring.womanarea51.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.ERole;
import tn.esprit.spring.womanarea51.entities.File;
import tn.esprit.spring.womanarea51.entities.FileComplaint;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.ComplaintRepository;
import tn.esprit.spring.womanarea51.repositories.FileComplaintRepository;
@Service
public class FileComplaintService implements IFileComplaintService {
	@Autowired
	FileComplaintRepository fr;
	@Autowired
	ComplaintRepository cr;
	
	@Override
    public void removeFile(Long f, Long idCourse, User U) {
        FileComplaint file = fr.findById(f).orElse(null);
         
           /* try {
                FTPService.uFileremove(file.getFileName(),"complaint", file.getCourse().getCourseId());
                fr.delete(file);
            } catch (Exception e) {
                System.out.println(e);
            }*/
        
    }
    @Override
    public FileComplaint addFileComplaint(MultipartFile file, Long idCourse, User U,FileComplaint fichi) {
        
        
        try {
            FTPService.uFileUpload(file, "complaint", idCourse);
            
            fichi.setUploadDate(new Date());
            fichi.setFileName(file.getOriginalFilename());
            fichi.setFilePath("https://www.womanarea51.ml/Courses/"+idCourse.toString()+"/"+file.getOriginalFilename());
            
            return fr.save(fichi);
        }catch (Exception e){
            System.out.println("Error Uploading file");
        }
        return null;
    }

    @Override
    public List<FileComplaint> findAll() {
        return (List<FileComplaint>) fr.findAll();

    }

}
