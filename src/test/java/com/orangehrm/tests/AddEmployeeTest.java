package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.EmployeelistPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.reports.ExtentReportUtil;
import com.orangehrm.utilities.PropertyUtil;

public class AddEmployeeTest extends BaseTest {
	@Test(dataProvider = "AddEmployeeData", dataProviderClass = com.orangehrm.utilities.DataProviderMethods.class)
	public void addEmployeeDataAndVerify(String fstName, String lstName, String userName, String pwd, String cnfPwd,
			String status) throws InterruptedException {

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(PropertyUtil.readProperty("userName"), PropertyUtil.readProperty("password"));
		ExtentReportUtil.logStep(Status.INFO, "Successfully logged in to the Application");

		AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());

		addEmployeePage.navigateToPim(getDriver());

		addEmployeePage.navigateToAddEmployee(getDriver());

		String expectedEmpId = addEmployeePage.addEmployeeDetails(fstName, lstName, userName, pwd, cnfPwd, status,
				getDriver());

		EmployeelistPage employeeListPage = new EmployeelistPage(getDriver());
		Thread.sleep(3000);
		employeeListPage.navigateToEmployeeList();
		String actualEmpId = employeeListPage.getEmpRecord(expectedEmpId);

		Assert.assertEquals(actualEmpId, expectedEmpId, "Verify employee record in Web Table");
		ExtentReportUtil.logStep(Status.INFO, "Successfully added and verified the added employee");

	}

}
