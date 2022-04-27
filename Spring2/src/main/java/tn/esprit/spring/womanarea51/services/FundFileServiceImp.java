package tn.esprit.spring.womanarea51.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.FundFiles;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.fund;
import tn.esprit.spring.womanarea51.repositories.FundFileRepository;

@Service
public class FundFileServiceImp implements IFundFilesService {

	@Autowired
	IFundService IFS;

	FundFileRepository fr;

	public FundFiles addFile(MultipartFile file, Long id, User U) {
		fund fund = IFS.FindFund(id);
		try {

			FTPService.uFileUpload(file, "event", id);

			FundFiles f = new FundFiles();
			f.setUploadDate(new Date());
			f.setFileName(file.getOriginalFilename());
			f.setFilePath("https://www.womanarea51.ml/event/" + id.toString() + "/" + file.getOriginalFilename());
			f.setF(fund);
			return fr.save(f);
		} catch (Exception e) {
			System.out.println("Error Uploading file");
		}
		return null;
	}

	public void removeFile(Long f, Long id, User U) {
		FundFiles file = fr.findById(f).orElse(null);
		if (!(file == null)) {
			try {
				FTPService.uFileremove(file.getFileName(), "event", file.getF().getFundId());
				fr.delete(file);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public List<FundFiles> findAll() {
		return fr.findAll();

	}

	public List<FundFiles> GeteventFiles(Long id) {
		List<FundFiles> list = new ArrayList<FundFiles>();
		fr.findAll().forEach(f -> {
			if (f.getF().getFundId() == id)
				list.add(f);
		});
		return list;
	}

}
