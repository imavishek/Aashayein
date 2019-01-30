package org.avishek.aashayein.service;

import java.io.UnsupportedEncodingException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	String uploadProfilePictureIntoServer(MultipartFile profilePhotoFile, String employeeCode);
	
	String getRootPath() throws UnsupportedEncodingException;

}
