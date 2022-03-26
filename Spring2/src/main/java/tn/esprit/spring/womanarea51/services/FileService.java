package tn.esprit.spring.womanarea51.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.File;

import java.util.List;

public interface FileService {
    File addFile(MultipartFile f, Long c);
    File updateFile(File f, Long c);
    void removeFile(Long f);
    //List<File> findFilesByCourse();
    List<File> findAll();

}
