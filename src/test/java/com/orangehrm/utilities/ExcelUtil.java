package com.orangehrm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static Sheet sheet;

	public static void initilizeExcel() {
		Workbook wbook = null;

		String filePath = "D:\\PracticeForInterview\\Codebase\\src\\test\\resources\\testdata\\TestDataForOrangeHrm.xlsx";
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);

			if (filePath.endsWith(".xlsx")) {
				wbook = new XSSFWorkbook(fis);
			} else if (filePath.endsWith(".xls")) {
				wbook = new HSSFWorkbook();
			} else {
				System.out.println("Invalid file format");
			}
		} catch (IOException e) {
			// Log.error("Failed to read the excel file"+e.getCause());
		}

		sheet = wbook.getSheet("AddEmployee");
	}

	public static String readData(int row, int col) {

		String value = sheet.getRow(row).getCell(col).getStringCellValue();
		return value;
	}

	public static ArrayList<Object[]> getDataFromAddEmployeeSheet() {

		ArrayList<Object[]> dataList = new ArrayList<Object[]>();
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(0).getLastCellNum();

		for (int rownum = 2; rownum <= rowcount; rownum++) {
			for (int colnum = 0; colnum <= colcount; colnum++) {
				String firstName = readData(rownum, colnum);
				String lastName = readData(rownum, colnum);
				String username = readData(rownum, colnum);
				String password = readData(rownum, colnum);
				String cnfPassword = readData(rownum, colnum);
				String status = readData(rownum, colnum);

				Object[] dataRowWise = { firstName, lastName, username, password, cnfPassword, status };
				dataList.add(dataRowWise);

			}

		}
		return dataList;

	}

}
