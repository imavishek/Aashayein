package org.avishek.aashayein.uniquekeyGenerator;

import org.avishek.aashayein.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniquekeyGenerator {

	@Autowired
	private EmployeeDao employeeDao;

	public String getNextEmployeeCode() {

		String employeeCode = null;
		Integer employeeId = null;

		String lastEmployeeId = employeeDao.getLastEmployeeId();

		/*
		 * For 1st employee the employeeCode is "asha-0001" after that it increased by 1
		 */
		if (lastEmployeeId == null) {
			employeeCode = "asha-00001";
		} else {
			employeeId = Integer.parseInt(lastEmployeeId);
			employeeId += 1;

			if (employeeId <= 9) {
				employeeCode = "asha-0000" + employeeId;
			} else if (employeeId <= 99) {
				employeeCode = "asha-000" + employeeId;
			} else if (employeeId <= 999) {
				employeeCode = "asha-00" + employeeId;
			} else if (employeeId <= 9999) {
				employeeCode = "asha-0" + employeeId;
			} else {
				employeeCode = "asha-" + employeeId;
			}
		}

		return employeeCode;
	}
}
