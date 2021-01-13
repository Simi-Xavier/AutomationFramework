package com.automation.pages;
import static com.automation.utilities.ElementBy.xPath;

import org.openqa.selenium.WebElement;

import com.automation.utilities.BasePage;

public class OrderDetailPage extends BasePage {

	public void clickOnProceedToCheckOut() {
		WebElement proccedBtn = findElement(xPath, "//*[@id='center_column']/p[2]/a[1]/span");
		explicitWaitForElementToBeClickableAndClick(proccedBtn);
	}
	
	public String getOrderPageTitle() {
		return getPageTitle();
	}
	
}
