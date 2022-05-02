package tn.esprit.spring.womanarea51.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FundCatFTPService {
	static FTPClient ftp = new FTPClient();
	static String TMP_UPLOAD_FOLDER = "C:/tmp/";
	static String SERVER_DOMAIN = "ftpupload.net";
	static String SERVER_USERNAME = " ";
	static String SERVER_PASSWORD = " ";

	public static String uFileUpload(MultipartFile file, String Type, Long typeId) throws IOException {
		if (file.isEmpty()) {
			System.out.println("Empty File");
			return "Empty File";
		} else {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(TMP_UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			System.out.println("File successfully uploaded to local storage : " + file.getOriginalFilename());
			ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			int reply;
			ftp.connect(SERVER_DOMAIN);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.out.println("Exception in connecting to FTP Server");
			}
			ftp.login(SERVER_USERNAME, SERVER_PASSWORD);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			try {
				InputStream input = new FileInputStream(new File(TMP_UPLOAD_FOLDER + file.getOriginalFilename()));
				System.out.println(input);
				ftp.makeDirectory("/htdocs/"+Type);
				ftp.storeFile("/htdocs/" +Type+"/"+typeId.toString(), input);
				ftp.logout();
				ftp.disconnect();
				System.out.println("File Uploaded !");
				input.close();
				Files.delete(path);
				System.out.println("File deleted in C:\\tmp");

			} catch (Exception e) {
				System.out.println("Error uploading file to remote server");
			}

		}
		return "";

	}

}
