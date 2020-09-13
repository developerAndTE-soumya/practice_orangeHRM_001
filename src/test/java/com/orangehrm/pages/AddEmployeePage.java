package com.orangehrm.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.utilities.Log;

public class AddEmployeePage {

	@FindBy(id = "menu_pim_viewPimModule")
	private WebElement linkPim;

	@FindBy(id = "menu_pim_addEmployee")
	private WebElement linkAddEmployee;

	@FindBy(id = "firstName")
	private WebElement txtFirstName;

	@FindBy(id = "lastName")
	private WebElement txtLastName;

	@FindBy(xpath = "//input[@id='photofile']")
	private WebElement fileUploadBtn;

	@FindBy(id = "btnSave")
	private WebElement btnSave;

	@FindBy(id = "employeeId")
	private WebElement txtEmployeeId;

	@FindBy(xpath = "//input[@id='chkLogin']")
	private WebElement chkBoxCrtLgnDtls;

	@FindBy(xpath = "//input[@id='user_name']")
	private WebElement txtUserName;

	@FindBy(xpath = "//input[@id='user_password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//input[@id='re_password']")
	private WebElement txtConfPassword;

	@FindBy(xpath = "//select[@id='status']")
	private WebElement drpDwnStatus;

	public AddEmployeePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void navigateToPim(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(linkPim));
		Actions obj = new Actions(driver);
		obj.moveToElement(linkPim).build().perform();
		Log.info("Successfully navigate to PIM");
	}

	public void navigateToAddEmployee(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(linkAddEmployee));
		Actions obj = new Actions(driver);
		obj.moveToElement(linkAddEmployee).build().perform();
		linkAddEmployee.click();
		Log.info("Successfully navigate to Add Employee page");
	}

	public String addEmployeeDetails(String fname, String lname, String userName, String password, String cnfpassword,
			String status, WebDriver driver) {

		txtFirstName.sendKeys(fname);
		txtLastName.sendKeys(lname);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", fileUploadBtn);
		try {
			Runtime.getRuntime()
					.exec("D://PracticeForInterview//Codebase//src//test//resources//autoITFile//FileUpload.exe" + " "
							+ "D:\\Project_Testing_images\\Test_image.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expectedEmpId = txtEmployeeId.getAttribute("value");
		clickCreateLoginDetailsChkBox();
		txtUserName.sendKeys(userName);
		txtPassword.sendKeys(password);
		txtConfPassword.sendKeys(cnfpassword);
		selectValueFromStatusDrpDwn(status);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		btnSave = wait.until(ExpectedConditions.visibilityOf(btnSave));
		btnSave.click();
		return expectedEmpId;
	}

	public void clickCreateLoginDetailsChkBox() {
		chkBoxCrtLgnDtls.click();
	}

	public void selectValueFromStatusDrpDwn(String value) {
		Select drpdwn_status = new Select(drpDwnStatus);
		drpdwn_status.selectByValue(value);
	}

}
