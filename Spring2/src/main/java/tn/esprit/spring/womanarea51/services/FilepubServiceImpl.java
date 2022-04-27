package tn.esprit.spring.womanarea51.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.ERole;
import tn.esprit.spring.womanarea51.entities.Filepost;
import tn.esprit.spring.womanarea51.entities.Filepub;
import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.Pub;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.FilepostRepository;
import tn.esprit.spring.womanarea51.repositories.FilepubRepository;
import tn.esprit.spring.womanarea51.repositories.PostRepository;
import tn.esprit.spring.womanarea51.repositories.PubRepository;

@Service
public class FilepubServiceImpl implements FilepubService{
	@Autowired
	PubRepository pubRepository;
	@Autowired
	FilepubRepository fr;
	@Override
	public Filepub addFilepub(MultipartFile file, Long idPub, User U) {
		Pub  pub =  pubRepository.findById(idPub).orElse(null);
		 
        if ((pub == null) || (!(pub.getUserpub().equals(U)) && !(U.getRoles().contains(ERole.ROLE_ADMIN)))) return null;
        try {
            FTPService.uFileUpload(file,"Pub" ,idPub);
            Filepub f = new Filepub();
            f.setUploadDate(new Date());
            f.setFileName(file.getOriginalFilename());
            f.setFilePath("https://www.womanarea51.ml/Pub/"+idPub.toString()+"/"+file.getOriginalFilename());
            f.setPub(pub);
            return fr.save(f);
        }catch (Exception e){
            System.out.println("Error Uploading file");
        }
       
	return null;
	}

	@Override
	public void removeFile(Long idFile, Long idPub, User U) {
		// TODO Auto-generated method stub
		 Filepub file = fr.findById(idFile).orElse(null);
	        if (!((file == null) || ((!(file.getPub().getUserpub().equals(U))) && (!(U.getRoles().contains(ERole.ROLE_ADMIN)))))) {
	            try {
	                FTPService.uFileremove(file.getFileName(), "Pub",file.getPub().getUserpub().getId());
	                fr.delete(file);
	            } catch (Exception e) {
	                System.out.println(e);
	            }
	        }
	}

	@Override
	public List<Filepub> findAll() {
		// TODO Auto-generated method stub
		   return (List<Filepub>) fr.findAll();
		
	}
	
}
