package org.avishek.aashayein.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadUtil {

	@Autowired
	private ServletContext servletContext;

	private static String ABS_PATH = null;
	private static String REAL_PATH = null;
	private static final Logger logger = LogManager.getLogger(FileUploadUtil.class);

	// Upload Profile Picture Into Server
	public String uploadProfilePictureIntoServer(MultipartFile profilePhotoFile, String employeeCode) {

		String fileName = null;
		String extension = null;

		// Return null if file is empty
		if (profilePhotoFile.isEmpty()) {
			return null;
		}

		// Development path
		if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {

			ABS_PATH = "/DEBIAN/Aashayein/src/main/webapp/assets/upload/profilePictures/";
		} else {
			ABS_PATH = "C:/Aashayein/src/main/webapp/assets/upload/profilePictures/";
		}

		// get the real server path
		REAL_PATH = servletContext.getRealPath("/assets/upload/profilePictures/");

		logger.info("Development Upload Path For ProfilePictures " + ABS_PATH);
		logger.info("Server Upload Path For ProfilePictures " + REAL_PATH);

		// Upload ProfilePicture in both development and server

		try {

			// create the directories if it does not exist
			if (!new File(REAL_PATH).exists()) {
				new File(REAL_PATH).mkdirs();
			}

			if (!new File(ABS_PATH).exists()) {
				new File(ABS_PATH).mkdirs();
			}

			/*
			 * Get the file extension and rename the file PP- ProfilePicture
			 */
			extension = FilenameUtils.getExtension(profilePhotoFile.getOriginalFilename());
			fileName = "PP_" + UUID.randomUUID().toString()+ "." + extension;

			// transfer the file to both the location
			profilePhotoFile.transferTo(new File(REAL_PATH + fileName));

			/*
			 * As file already transfered in above. You can't use MultipartFile#transferTo
			 * method twice, second call of transferTo method should be replaced with a
			 * logic of file copying.
			 */
			Files.copy(Paths.get(REAL_PATH + fileName), Paths.get(ABS_PATH + fileName));

			logger.info(
					"Profile Picture Uploaded Successfully For The Employee Having EmployeeCode :- " + employeeCode);

			return fileName;
		} catch (Exception e) {

			logger.error(e.getMessage() + " [Exception " + e.getClass() + "]");

			return null;
		}
	}
}
