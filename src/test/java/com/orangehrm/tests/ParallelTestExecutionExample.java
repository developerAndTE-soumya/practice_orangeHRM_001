package com.orangehrm.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangehrm.utilities.PropertyUtil;

public class ParallelTestExecutionExample {
	private WebDriver driver;
	@BeforeClass
	public void initialization() throws IOException {
		PropertyUtil.intializePropertyFile();
	}
	@Test
	public void executeInChrome() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + PropertyUtil.readProperty("chromeDriverPath"));
		driver = new ChromeDriver();
		driver.get(PropertyUtil.readProperty("url"));
		driver.quit();
	}
	@Test
	public void executeInFireFox() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + PropertyUtil.readProperty("firefoxDriverPath"));
		driver = new FirefoxDriver();
		driver.get(PropertyUtil.readProperty("url"));
		driver.quit();
	}

}
