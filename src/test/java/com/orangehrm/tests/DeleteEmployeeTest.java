package com.orangehrm.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.DeleteEmployee;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.reports.ExtentReportUtil;
import com.orangehrm.utilities.PropertyUtil;

public class DeleteEmployeeTest extends BaseTest {
	@Test
	public void deleteEmployee() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(PropertyUtil.readProperty("userName"), PropertyUtil.readProperty("password"));
		ExtentReportUtil.logStep(Status.INFO, "Successfully logged in to yhe Application");

		AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());
		addEmployeePage.navigateToPim(getDriver());
		addEmployeePage.navigateToAddEmployee(getDriver());

		String addedEmpId = addEmployeePage.addEmployeeDetails("Soumya", "Prakash", getDriver());
		ExtentReportUtil.logStep(Status.INFO, "Successfully Added the employee");

		DeleteEmployee deleteEmployeepageObj = new DeleteEmployee(getDriver());
		deleteEmployeepageObj.search(addedEmpId, getDriver());
		deleteEmployeepageObj.verifyEmployee(getDriver());
		ExtentReportUtil.logStep(Status.INFO, "Successfully verified the Employee");
		deleteEmployeepageObj.deleteEmployee(getDriver());
		ExtentReportUtil.logStep(Status.INFO, "Successfully deleted the Employee ");

	}

}