package com.orangehrm.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.utilities.Log;

public class DeleteEmployee {
	@FindBy(xpath = "//*[@id=\"resultTable\"]/tbody/tr/td[2]/a")
	private WebElement webTableColumnForId;

	@FindBy(xpath = "//input[@name='chkSelectRow[]']")
	private WebElement webTableColumnForChkBox;

	@FindBy(id = "personal_txtEmployeeId")
	private WebElement txtEmpId; // in employee details page

	@FindBy(id = "menu_pim_viewEmployeeList")
	private WebElement linkEmployeeList; // employeelist page link

	@FindBy(xpath = "//input[@id='empsearch_id']")
	private WebElement txtEmpIdForSearch; // in employeelist page

	@FindBy(id = "searchBtn")
	private WebElement btnSearch; // in employeelist page

	@FindBy(id = "dialogDeleteBtn")
	private WebElement btnOkDialogueDelete; // in employeelist page

	@FindBy(id = "btnDelete")
	private WebElement btnDelete; // in employeelist page

	public DeleteEmployee(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void search(String empId, WebDriver driver) {
		linkEmployeeList.click();
		Log.info("Added EmpID is :" + empId);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		txtEmpIdForSearch = wait.until(ExpectedConditions.visibilityOf(txtEmpIdForSearch));
		txtEmpIdForSearch.clear();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + empId + "';", txtEmpIdForSearch); // to input the value in the text
																					// field
		// js.executeScript("document.getElementById('empsearch_id').value='"+empId+"';");
		// txtEmpIdForSearch.sendKeys(empId);
		// String text = (String) js.executeScript("return arguments[0].text;",
		// txtEmpIdForSearch);
		js.executeScript("arguments[0].click();", btnSearch); // to click on a button

		// Log.info(" EmpID to search is :" + text);
		// btnSearch.click();

	}

	public boolean verifyEmployee(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		webTableColumnForId = wait.until(ExpectedConditions.visibilityOf(webTableColumnForId));
		String id_WebTable = webTableColumnForId.getText();
		if (txtEmpIdForSearch.getText() == id_WebTable) {
			Log.info("Record Found and matched");
		} else {
			Log.warn("data mismatched");
		}
		return true;

	}

	public void deleteEmployee(WebDriver driver) {
		if (verifyEmployee(driver)) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			webTableColumnForChkBox = wait.until(ExpectedConditions.visibilityOf(webTableColumnForChkBox));
			webTableColumnForChkBox.click();
			btnDelete.click();
			btnOkDialogueDelete.click();
			Log.info("data Deleted Successfully");
		}
	}

}
