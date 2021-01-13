package com.automation.pages;
import com.automation.utilities.BasePage;
import static com.automation.utilities.ElementBy.xPath;
import static com.automation.utilities.ElementBy.iD;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
	
	public String getCurrentPageTitle() {
		return getPageTitle();
	}

	public void mousehoverMenuWomen() {
		WebElement menuWomen = findElement(xPath,"//*[@id='block_top_menu']/ul/li[1]/a");
		explicitWaitForElementToBeClickable(menuWomen);
		hoverElement(menuWomen);
	}
	
	public void clickOnSummerDressesMenu() {
		WebElement  menuSummerDresses = findElement(xPath, "//a[contains(text(),'Summer Dresses')]");
		
		explicitWaitForElementToBeClickableAndClick(menuSummerDresses);
	}
	
	public void mouseHoverOnCart() {
		WebElement shoppingCart = findElement(xPath, "//*[@id='header']/div[3]/div/div/div[3]/div/a");
		hoverElement(shoppingCart);
		WebElement orderCartButton = findElement(iD, "button_order_cart");
		
		explicitWaitForElementToBeClickableAndClick(orderCartButton);
	}

}
