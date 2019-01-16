package org.avishek.aashayein.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ModuleIdValidator implements ConstraintValidator<ModuleId, String[]> {

	private int max;

	@Override
	public void initialize(ModuleId moduleId) {
		this.max = moduleId.max();
	}

	@Override
	public boolean isValid(String[] moduleIds, ConstraintValidatorContext context) {

		// Check permissions is selected or not
		if (moduleIds.length == 0) {
			return false;
		}

		// Validate the moduleId
		for (String module : moduleIds) {
			try {
				Integer moduleId = Integer.parseInt(module);
				// Checking if the moduleId exists in the database or not
				if (moduleId > max) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}

			break;
		}

		return true;
	}
}
