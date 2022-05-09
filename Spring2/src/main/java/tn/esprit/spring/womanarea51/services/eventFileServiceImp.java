package tn.esprit.spring.womanarea51.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.eventFile;
import tn.esprit.spring.womanarea51.repositories.eventFileRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class eventFileServiceImp implements IeventFileService {
	
	@Autowired
	eventFileRepository fr;
	
	@Autowired
	IEventService IES;

	
    public eventFile addFile(MultipartFile file, Long id, User U) {
        event event = IES.FindEvent(id);
        try {
        	
            FTPService.uFileUpload(file,"event",id);
           
            eventFile f = new eventFile();
            f.setUploadDate(new Date());
            f.setFileName(file.getOriginalFilename());
            f.setFilePath("https://www.womanarea51.ml/event/"+id.toString()+"/"+file.getOriginalFilename());
            f.setE(event);
            return fr.save(f);
        }catch (Exception e){
            System.out.println("Error Uploading file");
        }
        return null;
    }


    
    public void removeFile(Long f, Long id, User U) {
        eventFile file = fr.findById(f).orElse(null);
        if (!(file == null) ) {
            try {
                FTPService.uFileremove(file.getFileName(),"event",file.getE().getEventId());
                fr.delete(file);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    
    public List<eventFile>findAll() {
        return fr.findAll();

    }
		
 public List<eventFile>GeteventFiles(Long id){
	 List<eventFile> list= new ArrayList<eventFile>();
	 fr.findAll().forEach(f->{
		 if (f.getE().getEventId()==id)
			 list.add(f);
	 });
	 return list;
 }
 
 

}
