package com.orangehrm.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.utilities.PropertyUtil;

public class FileUploadWithAutoIT {

	public static void main(String[] args) throws IOException, InterruptedException {

		PropertyUtil.intializePropertyFile();
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + PropertyUtil.readProperty("chromeDriverPath"));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(PropertyUtil.readProperty("url"));
		Thread.sleep(5000);

		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Livetech@123");
		driver.findElement(By.id("btnLogin")).click();

		WebElement linkPim = driver.findElement(By.id("menu_pim_viewPimModule"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(linkPim));
		Actions obj = new Actions(driver);
		obj.moveToElement(linkPim).build().perform();

		WebElement linkAddEmployee = driver.findElement(By.id("menu_pim_addEmployee"));
		Actions obj1 = new Actions(driver);
		obj1.moveToElement(linkAddEmployee).build().perform();
		linkAddEmployee.click();

		Thread.sleep(5000);
		driver.findElement(By.id("firstName")).sendKeys("sikun");
		driver.findElement(By.id("lastName")).sendKeys("pani");
		WebElement btnUpload = driver.findElement(By.xpath("//input[@id='photofile']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnUpload);
		try {
			Runtime.getRuntime()
					.exec("D://PracticeForInterview//Codebase//src//test//resources//autoITFile//FileUpload.exe" + " "
							+ "D:\\Project_Testing_images\\Test_image.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement saveButton = driver.findElement(By.id("btnSave"));
		saveButton.click();
		

		// LoginPage loginPage = new LoginPage(driver);
		// loginPage.login(PropertyUtil.readProperty("userName"),
		// PropertyUtil.readProperty("password"));
		// AddEmployeePage addEmployeePage = new AddEmployeePage(driver);

		// addEmployeePage.navigateToPim(driver);
		//
		// addEmployeePage.navigateToAddEmployee(driver);

		// driver.findElement(By.id("firstName")).sendKeys("Sikun");
		// driver.findElement(By.id("lastName")).sendKeys("Pani");
		// Runtime.getRuntime().exec("D:\\PracticeForInterview\\Codebase\\src\\test\\resources\\autoITFile\\FileUploadScript.exe");
		// Thread.sleep(3000);
		// driver.findElement(By.id("btnSave")).click();

	}

}
