package org.avishek.aashayein.serviceImpl;

import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.service.EmployeeService;
import org.avishek.aashayein.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExportServiceImpl implements ExportService {

	@Autowired
	EmployeeService employeeService;

	@Override
	public Workbook buildWorkbookForEmployees() {

		XSSFWorkbook workbook = null;

		// Create Blank workbook
		workbook = new XSSFWorkbook();

		// Create a blank spreadsheet
		XSSFSheet spreadsheet = workbook.createSheet("Employees");

		// create style for header cells
		CellStyle headerStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex());
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBottomBorderColor(IndexedColors.GREEN.getIndex());
		headerStyle.setFont(font);

		// create style for content cell
		CellStyle contentStyle = workbook.createCellStyle();
		font = workbook.createFont();
		font.setFontName("Arial");
		font.setColor(IndexedColors.BLACK.getIndex());
		contentStyle.setAlignment(HorizontalAlignment.CENTER);
		contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		contentStyle.setFont(font);

		// create style for content cell
		CellStyle dateStyle = workbook.createCellStyle();
		font = workbook.createFont();
		font.setFontName("Arial");
		font.setColor(IndexedColors.BLACK.getIndex());
		dateStyle.setAlignment(HorizontalAlignment.CENTER);
		dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		dateStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd/MM/yyyy"));
		dateStyle.setFont(font);

		// create style for empty cell
		CellStyle recordNotFoundStyle = workbook.createCellStyle();
		font = workbook.createFont();
		font.setFontName("Arial");
		font.setBold(true);
		font.setColor(IndexedColors.RED.getIndex());
		recordNotFoundStyle.setAlignment(HorizontalAlignment.CENTER);
		recordNotFoundStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		recordNotFoundStyle.setFont(font);

		// create header row
		Row header = spreadsheet.createRow(0);

		header.createCell(0).setCellValue("Employee Code");
		header.createCell(1).setCellValue("Employee Name");
		header.createCell(2).setCellValue("Gender");
		header.createCell(3).setCellValue("Mobile No.");
		header.createCell(4).setCellValue("Email Id");
		header.createCell(5).setCellValue("Job Title");
		header.createCell(6).setCellValue("Role");
		header.createCell(7).setCellValue("Joining Date");
		header.createCell(8).setCellValue("Archived");
		header.createCell(9).setCellValue("Country");
		header.createCell(10).setCellValue("State");
		header.createCell(11).setCellValue("City");
		header.createCell(12).setCellValue("PinCode");
		header.createCell(13).setCellValue("Address");

		// Getting all the employee details
		List<EmployeeTO> employees = employeeService.getAllEmployees();

		if (employees.isEmpty()) {
			Row empty = spreadsheet.createRow(1);
			empty.createCell(2).setCellValue("No Employee Record Found");
			empty.getCell(2).setCellStyle(recordNotFoundStyle);
		} else {
			int rowNumber = 1;
			Row row = null;

			for (EmployeeTO employee : employees) {
				row = spreadsheet.createRow(rowNumber);

				row.createCell(0).setCellValue(employee.getEmployeeCode());
				row.getCell(0).setCellStyle(contentStyle);
				row.createCell(1).setCellValue(employee.getFullName());
				row.getCell(1).setCellStyle(contentStyle);
				row.createCell(2).setCellValue(employee.getGender());
				row.getCell(2).setCellStyle(contentStyle);
				row.createCell(3).setCellValue(employee.getMobileNumber());
				row.getCell(3).setCellStyle(contentStyle);
				row.createCell(4).setCellValue(employee.getEmail());
				row.getCell(4).setCellStyle(contentStyle);
				row.createCell(5).setCellValue(employee.getJobTitleName());
				row.getCell(5).setCellStyle(contentStyle);
				row.createCell(6).setCellValue(employee.getRoleName());
				row.getCell(6).setCellStyle(contentStyle);
				row.createCell(7).setCellValue(employee.getJoiningDate());
				row.getCell(7).setCellStyle(dateStyle);
				row.createCell(8).setCellValue((employee.getArchive() == 1) ? "Yes" : "No");
				row.getCell(8).setCellStyle(contentStyle);
				row.createCell(9).setCellValue((employee.getCountryName() == null) ? "N/A" : employee.getCountryName());
				row.getCell(9).setCellStyle(contentStyle);
				row.createCell(10).setCellValue((employee.getStateName() == null) ? "N/A" : employee.getStateName());
				row.getCell(10).setCellStyle(contentStyle);
				row.createCell(11).setCellValue((employee.getCityName() == null) ? "N/A" : employee.getCityName());
				row.getCell(11).setCellStyle(contentStyle);
				row.createCell(12).setCellValue((employee.getPostalCode() == null) ? "N/A" : employee.getPostalCode());
				row.getCell(12).setCellStyle(contentStyle);
				row.createCell(13)
						.setCellValue((employee.getAddressLine1() == null) ? "N/A" : employee.getAddressLine1());
				row.getCell(13).setCellStyle(contentStyle);

				rowNumber++;
			}
		}

		for (int cell = 0; cell < header.getLastCellNum(); cell++) {
			header.getCell(cell).setCellStyle(headerStyle);
			spreadsheet.autoSizeColumn(cell);
		}

		return workbook;
	}

}
