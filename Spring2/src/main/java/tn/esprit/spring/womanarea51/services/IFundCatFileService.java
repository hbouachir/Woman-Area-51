package tn.esprit.spring.womanarea51.services;



import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.FundCategoryFile;
import tn.esprit.spring.womanarea51.entities.User;



public interface IFundCatFileService {
	
	 public FundCategoryFile addFile(MultipartFile file, Long id, User U) ;
	 
//	 public void removeFile(Long f, Long id, User U) ;

	 public FundCategoryFile GetFundFiles(Long id);
}
