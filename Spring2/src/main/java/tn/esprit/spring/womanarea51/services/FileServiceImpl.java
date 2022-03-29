package tn.esprit.spring.womanarea51.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.ERole;
import tn.esprit.spring.womanarea51.entities.File;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.CourseRepository;
import tn.esprit.spring.womanarea51.repositories.FileRepository;

import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    FileRepository fr;

    @Autowired
    CourseRepository cr;

    @Override
    public File addFile(MultipartFile file, Long idCourse, User U) {
        Course course = cr.findById(idCourse).orElse(null);
        if ((course == null) || (!(course.getInstructor().equals(U)) && !(U.getRoles().contains(ERole.ROLE_ADMIN)))) return null;
        try {
            FTPService.fileUpload(file, idCourse);
            File f = new File();
            f.setUploadDate(new Date());
            f.setFileName(file.getOriginalFilename());
            f.setFilePath("https://www.womanarea51.ml/Courses/"+idCourse.toString()+"/"+file.getOriginalFilename());
            f.setCourse(course);
            return fr.save(f);
        }catch (Exception e){
            System.out.println("Error Uploading file");
        }
        return null;
    }


    @Override
    public void removeFile(Long f, Long idCourse, User U) {
        File file = fr.findById(f).orElse(null);
        if (!((file == null) || ((!(file.getCourse().getInstructor().equals(U))) && (!(U.getRoles().contains(ERole.ROLE_ADMIN)))))) {
            try {
                FTPService.removeFile(file.getFileName(), file.getCourse().getCourseId());
                fr.delete(file);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public List<File> findAll() {
        return (List<File>) fr.findAll();

    }
}
