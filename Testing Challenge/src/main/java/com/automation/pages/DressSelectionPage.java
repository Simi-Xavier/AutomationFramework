package com.automation.pages;

import static com.automation.utilities.ElementBy.xPath;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import static com.automation.utilities.ElementBy.name;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.automation.utilities.BasePage;
import com.automation.utilities.OrderDetails;

public class DressSelectionPage extends BasePage {

	public void mousehoverPrintedDressAndClickQuickView() {
		WebElement menuPrintedDress = findElement(xPath,
				"//*[@src='http://automationpractice.com/img/p/1/2/12-home_default.jpg']");
		explicitWaitForElementToBeClickable(menuPrintedDress);
		hoverElement(menuPrintedDress);
		findElement(xPath, "//a[@class='quick-view']").click();

	}

	public String getPageHeader() {

		String pageHeader = findElement(xPath, "//*[@id='product']/div/div/div[2]/h1").getText();
		return pageHeader;
	}

	public OrderDetails selectSizeAndAddToCart() {
		WebElement fancyBox = findElement(xPath, "//*[@id='category']/div[2]");
		explicitWaitForVisibilityOfElement(2, fancyBox);

		WebElement framesample = findElement(xPath, "//*[@id='category']/div[2]/div/div/div/div");
		WebElement ee = framesample.findElement(By.tagName("iframe"));
		getDriver().switchTo().frame(ee);
		Select sizeOption = new Select(findElement(xPath, "//*[@id='group_1']"));
		sizeOption.selectByValue("1");

		OrderDetails order = new OrderDetails();
		order.model = findElement(xPath, "//*[@id='product_reference']/span").getText();
		order.size = findElement(xPath, "//*[@id='uniform-group_1']/span").getText();
		order.quantity = Integer.parseInt(findElement(xPath, "//*[@id='quantity_wanted']").getAttribute("value"));
		String amount = findElement(xPath, "//*[@id='our_price_display']").getText();
		order.price = Double.parseDouble(amount.substring(1));

		findElement(name, "Submit").click();
		getDriver().switchTo().parentFrame();
		return order;
	}

	public String checkForProdAddedConfirmMsg() {
		explicitWaitForVisibilityOfElement(30, findElement(xPath, "//*[@id='layer_cart']/div[1]/div[1]/h2"));
		WebElement confirmMsg = findElement(xPath, "//*[@id='layer_cart']/div[1]/div[1]/h2");
		return (confirmMsg.getText());
	}

	public void clickOnContinueShoppingButton() {

		try {
			WebElement fancyBox = findElement(xPath, "//*[@id='category']/div[2]");
			explicitWaitForInVisibilityOfElement(2, fancyBox);
		}
		catch (NoSuchElementException | StaleElementReferenceException e) {
			// if this element is not found , we can ignore.
		}
		WebElement continueShoppingBtn = findElement(xPath, "//*[@id='layer_cart']/div[1]/div[2]/div[4]/span/span");

		explicitWaitForElementToBeClickableAndClick(continueShoppingBtn);
	}

}
