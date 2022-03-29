package tn.esprit.spring.womanarea51.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.File;
import tn.esprit.spring.womanarea51.entities.User;

import java.util.List;

public interface FileService {
    File addFile(MultipartFile f, Long idCourse, User U);
    void removeFile(Long idFile,Long idCourse, User U );
    //List<File> findFilesByCourse();
    List<File> findAll();

}
