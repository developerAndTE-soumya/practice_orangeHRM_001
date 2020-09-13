package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.reports.ExtentReportUtil;
import com.orangehrm.utilities.Log;

public class LoginTest extends BaseTest {
	
	@Test(dataProvider="LoginData",dataProviderClass=com.orangehrm.utilities.DataProviderMethods.class)
	public void verifyLoginWithValidCredentials(String uname,String pwd) throws InterruptedException {

		LoginPage loginPage = new LoginPage(getDriver());
		//loginPage.login(PropertyUtil.readProperty("userName"),PropertyUtil.readProperty("password"));
		loginPage.login(uname, pwd);
		ExtentReportUtil.logStep(Status.INFO, "Successfully logged in to the Application");
		String actualMsg = loginPage.getWelComeMsg(getDriver());
		Assert.assertEquals(actualMsg, "Welcome Admin", "Verify Welcome Message");
		ExtentReportUtil.logStep(Status.INFO, "Successfully verified the welcome message");
		Log.info("Verified Welcome message");
		loginPage.logout(getDriver());
		ExtentReportUtil.logStep(Status.INFO, "Successfully logged out");

	} 


}