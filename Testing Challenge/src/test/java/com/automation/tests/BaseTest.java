package com.automation.tests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.automation.utilities.BasePage;

public abstract class BaseTest {
	@BeforeTest
	public void openBrowser() {
		
		BasePage.initializeBrowser();
	}
	@AfterTest
	public void closeBrowser() throws InterruptedException {		
		BasePage.quitBrowser();		
	}
	
	public void loadHomePage() {
		BasePage.loadHomePage();
	}

}
