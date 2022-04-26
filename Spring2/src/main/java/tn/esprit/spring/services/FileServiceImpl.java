package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.File;
import tn.esprit.spring.repositories.CourseRepository;
import tn.esprit.spring.repositories.FileRepository;

import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    FileRepository fr;

    @Autowired
    CourseRepository cr;

    @Override
    public File addFile(MultipartFile file, Long c) {
        try {
            FTPService.fileUpload(file, c);
            File f = new File();
            f.setUploadDate(new Date());
            f.setFileName(file.getOriginalFilename());
            f.setFilePath("https://www.womanarea51.ml/Courses/"+c.toString()+"/"+file.getOriginalFilename());
            f.setCourse(cr.findById(c).orElse(null));
            return fr.save(f);
        }catch (Exception e){
            System.out.println("Error Uploading file");
        }
        return null;
    }

    @Override
    public File updateFile(File f, Long c) {
        Course course = cr.findById(c).get();
        f.setCourse(course);
        return fr.save(f);
    }

    @Override
    public void removeFile(Long f) {
        File file = fr.findById(f).orElse(null);
        try{
            FTPService.removeFile(file.getFileName(), file.getCourse().getCourseId());
            fr.delete(file);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public List<File> findAll() {
        return (List<File>) fr.findAll();

    }
}
