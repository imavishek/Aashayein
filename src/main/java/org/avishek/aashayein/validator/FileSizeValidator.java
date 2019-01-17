/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.validator;
 * @FileName ProfilePictureValidator.java
 * @CreatedDate 10-Jan-2019
 * Modified by @author avishekdas last on 2019-01-10 23:52:50
 */

package org.avishek.aashayein.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private Long size;

	@Override
	public void initialize(FileSize fileSize) {
		this.size = fileSize.size();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

		if (!file.isEmpty() && (file.getSize() > size)) {
			return false;
		} else {
			return true;
		}
	}

}
