package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.EmployeelistPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.reports.ExtentReportUtil;
import com.orangehrm.utilities.PropertyUtil;

public class AddEmployeeTest extends BaseTest  {
	@Test
	public void verifyAddEmployee() throws InterruptedException {
		
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(PropertyUtil.readProperty("userName"),PropertyUtil.readProperty("password"));
		ExtentReportUtil.logStep(Status.INFO, "Successfully logged in to the Application");
		
		AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());
		//Thread.sleep(3000);
		addEmployeePage.navigateToPim(getDriver());
		//Thread.sleep(3000);
		addEmployeePage.navigateToAddEmployee(getDriver());
		
		String expectedEmpId = addEmployeePage.addEmployeeDetails("Mona","mama",getDriver());
		ExtentReportUtil.logStep(Status.INFO, "Successfully Added the employee");
		
		EmployeelistPage employeeListPage = new EmployeelistPage(getDriver());
		employeeListPage.navigateToEmployeeList();
		String actualEmpId = employeeListPage.getEmpRecord(expectedEmpId);
		
		Assert.assertEquals(actualEmpId, expectedEmpId, "Verify employee record in Web Table");
	   
	}

}
