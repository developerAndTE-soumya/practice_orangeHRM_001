package com.orangehrm.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderMethods {

	@DataProvider(name = "LoginData")
	String[][] getLoginData() throws IOException {
		String path = "D:\\PracticeForInterview\\Codebase\\src\\test\\resources\\testdata\\TestDataForOrangeHrm.xlsx";

		int rownum = ExcelUtilTest.getRowCount(path, "LoginData");
		int colcount = ExcelUtilTest.getCellCount(path, "LoginData", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {

				logindata[i - 1][j] = ExcelUtilTest.getCellData(path, "LoginData", i, j);
			}

		}
		return logindata;

	}
	
	@DataProvider(name = "AddEmployeeData")
	String[][] getEmployeeData() throws IOException {
		String path = "D:\\PracticeForInterview\\Codebase\\src\\test\\resources\\testdata\\TestDataForOrangeHrm.xlsx";

		int rownum = ExcelUtilTest.getRowCount(path, "AddEmployee");
		int colcount = ExcelUtilTest.getCellCount(path, "AddEmployee", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {

				logindata[i - 1][j] = ExcelUtilTest.getCellData(path, "AddEmployee", i, j);
			}

		}
		return logindata;

	}


}
