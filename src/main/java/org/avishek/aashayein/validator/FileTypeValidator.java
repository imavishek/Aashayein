/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.validator;
 * @FileName FileTypeValidator.java
 * @CreatedDate 11-Jan-2019
 * Modified by @author avishekdas last on 2019-01-11 00:43:50
 */

package org.avishek.aashayein.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileTypeValidator implements ConstraintValidator<FileType, MultipartFile> {

	private String extension;

	@Override
	public void initialize(FileType fileType) {
		this.extension = fileType.extension();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

		if (file == null) {
			return false;
		} else if (!file.isEmpty() && !FilenameUtils.getExtension(file.getOriginalFilename()).matches(extension)) {
			return false;
		} else {
			return true;
		}
	}

}
