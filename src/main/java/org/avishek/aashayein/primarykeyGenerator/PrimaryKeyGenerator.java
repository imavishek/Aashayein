package org.avishek.aashayein.primarykeyGenerator;

import org.avishek.aashayein.dao.EmployeeDao;
import org.avishek.aashayein.entities.EmployeeId_Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrimaryKeyGenerator {

	@Autowired
	private EmployeeDao employeeDao;

	public EmployeeId_Code getNextEmployeeIdCode() {

		String employeeCode = null;
		Integer employeeId = null;
		EmployeeId_Code employeeId_Code = null;

		String lastEmployeeId = employeeDao.getLastEmployeeId();

		/*
		 * For 1st employee the employeeCode is "asha-0001" after that it increased by 1
		 */
		if (lastEmployeeId == null) {
			employeeId_Code = new EmployeeId_Code(1, "asha-00001");
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

			employeeId_Code = new EmployeeId_Code(employeeId, employeeCode);
		}

		return employeeId_Code;
	}
}
