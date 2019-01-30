package org.avishek.aashayein.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

	private static final Logger logger = LogManager.getLogger(FileUploadServiceImpl.class);

	// Upload Profile Picture Into Server
	@Override
	@Transactional
	public String uploadProfilePictureIntoServer(MultipartFile profilePhotoFile, String employeeCode) {

		String fileName = null;
		String webContentPath = null;
		String extension = null;

		try {

			// Creating the directory to store file
			webContentPath = getRootPath();
			File directory = new File(webContentPath + File.separator + "profilePictures");
			System.out.println(directory);

			// If directory not exists then create the same
			if (!directory.exists())
				directory.mkdirs();

			extension = FilenameUtils.getExtension(profilePhotoFile.getOriginalFilename());
			fileName = UUID.randomUUID().toString() + "." + extension;

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

	@Override
	public String getRootPath() throws UnsupportedEncodingException {

		String rootPath = null;

		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/WEB-INF/classes/");

		rootPath = pathArr[0];

		return rootPath;
	}
}
