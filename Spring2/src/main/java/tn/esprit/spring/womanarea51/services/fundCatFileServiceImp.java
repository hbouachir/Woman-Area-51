package tn.esprit.spring.womanarea51.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.FundCategoryFile;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.fundCategory;
import tn.esprit.spring.womanarea51.repositories.fundCategoryFileRepository;
import java.util.Date;

@Service
public class fundCatFileServiceImp implements IFundCatFileService{
	@Autowired
	fundCategoryFileRepository fr;
	
	@Autowired
	IFundCategoryService IFCS;
	

	
    public FundCategoryFile addFile(MultipartFile file, Long id, User U) {
        fundCategory fcat = IFCS.FindFundCat(id);
        try {
            FundCatFTPService.uFileUpload(file,"fundCat",id);
            FundCategoryFile f= new FundCategoryFile();
            f.setUploadDate(new Date());
            f.setFileName(file.getOriginalFilename());
            f.setFilePath("https://www.womanarea51.ml/fundCat/"+id.toString());
            f.setFc(fcat);
            return fr.save(f);
        }catch (Exception e){
            System.out.println("Error Uploading file");
        }
        return null;
    }


    
//    public void removeFile(Long f, Long id, User U) {
//        FundCategoryFile file = fr.findById(id).orElse(null);
//        if (!(file == null) ) {
//            try {
//                FTPService.uFileremove(file.getFileName(),"fundCat",file.getFc().getCategoryId());
//                fr.delete(file);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }
//
//    
//    public List<FundCategoryFile>findAll() {
//        return fr.findAll();
//
//    }
    
    public FundCategoryFile GetFundFiles(Long id){
    	fundCategory fc= IFCS.FindFundCat(id);
    	
    	return fc.getFile(); 
    }
		
}
