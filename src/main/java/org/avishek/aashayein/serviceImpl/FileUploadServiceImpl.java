package org.avishek.aashayein.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private ServletContext servletContext;

	private static final Logger logger = LogManager.getLogger(FileUploadServiceImpl.class);

	// Upload Profile Picture Into Server
	@Override
	@Transactional
	public String uploadProfilePictureIntoServer(MultipartFile profilePhotoFile, String employeeCode) {

		String fileName = null;
		String contextRoot = null;
		String extension = null;

		try {

			// Creating the directory to store file
			contextRoot = servletContext.getRealPath("/");
			File directory = new File(contextRoot + File.separator + "profilePictures");
			System.out.println(directory);

			// If directory not exists then create the same
			if (!directory.exists())
				directory.mkdirs();
			
			extension = FilenameUtils.getExtension(profilePhotoFile.getOriginalFilename());
			fileName = UUID.randomUUID().toString()+"."+extension;

			// Create a file in the directory with UUID name
			File file = new File(directory, fileName);
			
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());

			// Writing data into the file
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
			byte[] data = profilePhotoFile.getBytes();
			stream.write(data);
			stream.close();

			logger.info(
					"Profile Picture Uploaded Successfully For The Employee Having EmployeeCode :- " + employeeCode);

			return fileName;
		} catch (Exception e) {

			return null;
		}
	}

}
