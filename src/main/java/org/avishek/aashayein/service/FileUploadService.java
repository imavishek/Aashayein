package org.avishek.aashayein.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	String uploadProfilePictureIntoServer(MultipartFile profilePhotoFile, String employeeCode);

}
