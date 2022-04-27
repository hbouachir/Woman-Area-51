package tn.esprit.spring.womanarea51.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.ERole;
import tn.esprit.spring.womanarea51.entities.File;
import tn.esprit.spring.womanarea51.entities.Filepost;
import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.FileRepository;
import tn.esprit.spring.womanarea51.repositories.FilepostRepository;
import tn.esprit.spring.womanarea51.repositories.PostRepository;

@Service
public class FilepostServiceImpl implements FilepostService {
@Autowired
PostRepository postRepository;
@Autowired
FilepostRepository fr;
	@Override
	public Filepost addFile(MultipartFile file, Long idPost, User U) {
		  Post  post =  postRepository.findById(idPost).orElse(null);
		 
	        if ((post == null) || (!(post.getUserp().equals(U)) && !(U.getRoles().contains(ERole.ROLE_ADMIN)))) return null;
	        try {
	            System.out.println("Error 31 file");

	            FTPService.uFileUpload(file,"Post" ,idPost);
	            System.out.println("Error 34");

	            Filepost f = new Filepost();
	            f.setUploadDate(new Date());
	            f.setFileName(file.getOriginalFilename());
	            f.setFilePath("https://www.womanarea51.ml/Post/"+idPost.toString()+"/"+file.getOriginalFilename());
	            f.setPost(post);
	            return fr.save(f);
	        }catch (Exception e){
	            System.out.println("Error Uploading file");
	        }
	       
		return null;
	}

	@Override
	public void removeFile(Long idFile, Long idPost, User U) {
		// TODO Auto-generated method stub
		String type;
		 Filepost file = fr.findById(idFile).orElse(null);
	        if (!((file == null) || ((!(file.getPost().getUserp().equals(U))) && (!(U.getRoles().contains(ERole.ROLE_ADMIN)))))) {
	            try {
	                FTPService.uFileremove(file.getFileName(), "Post",file.getPost().getUserp().getId());
	                fr.delete(file);
	            } catch (Exception e) {
	                System.out.println(e);
	            }
	        }
		
	}

	@Override
	public List<Filepost> findAll() {
		// TODO Auto-generated method stub
		   return (List<Filepost>) fr.findAll();
		
	}

}
