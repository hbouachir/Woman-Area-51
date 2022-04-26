package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.FundFiles;
import tn.esprit.spring.womanarea51.entities.User;

public interface IFundFilesService {

	public FundFiles addFile(MultipartFile file, Long id, User U);

	public void removeFile(Long f, Long id, User U);

	public List<FundFiles> findAll();

	public List<FundFiles> GetFundFiles(Long id);
	

}
