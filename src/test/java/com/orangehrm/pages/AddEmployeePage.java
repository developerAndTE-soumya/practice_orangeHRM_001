package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

	@FindBy(id = "btnSave")
	private WebElement btnSave;
	
	@FindBy(id="employeeId")
	private WebElement txtEmployeeId;
	
	public AddEmployeePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	public void navigateToPim(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(linkPim));
		Actions obj= new Actions(driver);
		obj.moveToElement(linkPim).build().perform();
		Log.info("Successfully navigate to PIM");
	}

	public void navigateToAddEmployee(WebDriver driver) {
		Actions obj= new Actions(driver);
		obj.moveToElement(linkAddEmployee).build().perform();
		linkAddEmployee.click();
		Log.info("Successfully navigate to Add Employee page");
	}
	
	public String addEmployeeDetails(String fname,String lname,WebDriver driver){
		WebDriverWait wait=new WebDriverWait(driver, 10);
		btnSave=wait.until(ExpectedConditions.visibilityOf(btnSave));
		txtFirstName.sendKeys(fname);
		txtLastName.sendKeys(lname);
		String expectedEmpId= txtEmployeeId.getAttribute("value");
		btnSave.click();
		Log.info("Successfully added employee details");
	    return expectedEmpId;
	}


}   
