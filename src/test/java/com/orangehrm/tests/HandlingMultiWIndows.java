package com.orangehrm.tests;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.utilities.PropertyUtil;

public class HandlingMultiWIndows {

	public static void main(String[] args) throws IOException, InterruptedException {
		PropertyUtil.intializePropertyFile();
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + PropertyUtil.readProperty("firefoxDriverPath"));
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/intl/en-GB/gmail/about/");
		// xpath for sign in link-//li[@class='h-c-header__nav-li
		// g-mail-nav-links']/child::a[@class='h-c-header__nav-li-link ' and
		// contains(text(),'Sign in')]
		WebElement signInLink = driver.findElement(By.xpath("//li[@class='h-c-header__nav-li g-mail-nav-links']/"
				+ "child::a[@class='h-c-header__nav-li-link ' and contains(text(),'Sign in')]"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(signInLink));
		// signInLink.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", signInLink);

		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> ids = windowIDs.iterator();
		String parentId = ids.next();
		System.out.println(parentId);
		String secondWindowId = ids.next();
		System.out.println(secondWindowId);
		driver.switchTo().window(secondWindowId);

		// wait.until(ExpectedConditions.titleContains("Gmail"));
		Thread.sleep(5000);
		WebElement emailidInputBox = driver.findElement(By.xpath("//input[@id='identifierId']"));
		wait.until(ExpectedConditions.visibilityOf(emailidInputBox));
		js.executeScript("arguments[0].value='sikun2008@gmail.com';", emailidInputBox);
		// emailidInputBox.sendKeys("sikun2008@gmail.com");
		WebElement btnNext = driver.findElement(By.xpath(
				"//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc qIypjc TrZEUc']"));
		btnNext.click();
		
		Thread.sleep(5000);//bond to use or else it's interacting of elements of the page where we are giving inputs like email and clicking
		//Learn more link xpath-//a[text()='Learn more']
		
		List<WebElement> divForLinkLearnmore = driver.findElements(By.xpath("//div[@class='PrDSKc']"));
		WebElement linkLearnmore=driver.findElement(By.xpath("//a[text()='Learn more']"));
		for(WebElement div:divForLinkLearnmore) {
			String divText=div.getText();
			System.out.println(divText);
			if(divText.equalsIgnoreCase("This browser or app may not be secure.Learn more"))
			{
				break;
				
			}
			
		}
		linkLearnmore.click();	
		
		String secondId=driver.getWindowHandle();//always returns the current window id from which we have navigated to next window(just checking it's returning which window id)
		System.out.println(secondId);
		
		
		Set<String> windowIDsAfterClickingSecondWindow = driver.getWindowHandles();
		Iterator<String> idsForInteractingThirdWindow = windowIDsAfterClickingSecondWindow.iterator();
		String firstWindowID=idsForInteractingThirdWindow.next();
		System.out.println(firstWindowID);
		String secondWindowId_ParentForThird=idsForInteractingThirdWindow.next();
		System.out.println(secondWindowId_ParentForThird);
		String thirdWindowId=idsForInteractingThirdWindow.next();//retrieving thied window id
		System.out.println(thirdWindowId);
		driver.switchTo().window(thirdWindowId);//swithcing to third window of browser
		
		Thread.sleep(4000);
		WebElement menu=driver.findElement(By.xpath("//div[@class='gb_Cc']"));//actions in third window
		menu.click();
		
		

	}

}
