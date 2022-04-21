package tn.esprit.spring.womanarea51.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.userFile;
import tn.esprit.spring.womanarea51.repositories.userFileRepository;

@Service
public class userFileServiceImp implements IuserFileService{
	
	@Autowired
	IUserService IUS;
	
	@Autowired
	userFileRepository fr;

    public userFile addFile(MultipartFile file,User U) {
        
        try {
            FTPUserService.uFileUpload(file,"profile",U.getId());
            userFile f = U.getFile();
            if (f == null) f = new userFile();
            f.setUploadDate(new Date());
            f.setFileName(U.getId().toString());
            f.setFilePath("https://www.womanarea51.ml/profile/"+U.getId().toString());
            U.setFile(f);
            f.setU(U);
            return fr.save(f);
        }catch (Exception e){
            System.out.println("Error Uploading file");
        }
        return null;
    }


    
//    public void removeFile(Long f, User U) {
//        userFile file = fr.findById(f).orElse(null);
//        if (!(file == null) ) {
//            try {
//                FTPService.uFileremove(file.getFileName(),"user",U.getId());
//                fr.delete(file);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }

    
    public List<userFile>findAll() {
        return fr.findAll();

    }
		
 public List<userFile>GetUserFiles(Long id){
	 List<userFile> list= new ArrayList<userFile>();
	 fr.findAll().forEach(f->{
		 if (f.getU().getId()==id)
			 list.add(f);
	 });
	 return list;
 }
 
 


}
