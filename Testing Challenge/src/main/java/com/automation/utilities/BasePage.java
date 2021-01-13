package com.automation.utilities;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

	static Logger log = Logger.getLogger(BasePage.class.getName());
	private static Configuration config = new Configuration();

	public static WebDriver getDriver() {
		return config.getDriverInstance();
	}

	public static void initializeBrowser() {
		config.initalizeDriver();
		getDriver().manage().window().maximize();
	}

	public static Configuration getConfig() {
		return config;
	}

	public static void loadHomePage() {
		getDriver().get(config.getURL());
	}

	public static void quitBrowser() throws InterruptedException {
		if (getDriver() != null)
			getDriver().quit();
	}

	public boolean isCurrentUrlEndsWith(String text) {
		return getDriver().getCurrentUrl().endsWith(text);
	}

	public String getPageTitle() {
		return getDriver().getTitle();
	}

	// Locate webPage element
	public WebElement findElement(ElementBy type, String attribute) {

		WebElement webElement = null;
		try {
			switch (type) {
			case xPath:
				webElement = getDriver().findElement(By.xpath(attribute));
				break;
			case iD:
				webElement = getDriver().findElement(By.id(attribute));
				break;
			case className:
				webElement = getDriver().findElement(By.className(attribute));
				break;
			case linkText:
				webElement = getDriver().findElement(By.linkText(attribute));
				break;
			case name:
				webElement = getDriver().findElement(By.name(attribute));
				break;
			case cssSelector:
				webElement = getDriver().findElement(By.cssSelector(attribute));
				break;
			}
		} catch (NoSuchElementException e) {
			log.error("Unable to find element" + type + ":" + attribute);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return webElement;
	}

	// Locate a list of web page elements
	public List<WebElement> findElements(ElementBy type, String attribute) {

		List<WebElement> webElements = new ArrayList<WebElement>();

		try {
			switch (type) {
			case xPath:
				webElements = getDriver().findElements(By.xpath(attribute));
				break;
			case iD:
				webElements = getDriver().findElements(By.id(attribute));
				break;
			case className:
				webElements = getDriver().findElements(By.className(attribute));
				break;
			case linkText:
				webElements = getDriver().findElements(By.linkText(attribute));
				break;
			case name:
				webElements = getDriver().findElements(By.name(attribute));
				break;
			case cssSelector:
				webElements = getDriver().findElements(By.cssSelector(attribute));
				break;
			}
		} catch (NoSuchElementException e) {
			log.error("Unable to find element" + type + ":" + attribute);
			throw (e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return webElements;
	}

	public static void implicitWait(int sec) {
		getDriver().manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

	// Wait till the web element is visible in the page/ time-out in seconds
	public void explicitWaitForVisibilityOfElement(int sec, WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), sec);
			wait.until(ExpectedConditions.visibilityOf(webElement));
		} catch (Exception e) {
			log.error("Element not visible after waiting for" + sec);
			throw e;
		}
	}

	// Wait till the web element is invisible in the page/ time-out in seconds
	public void explicitWaitForInVisibilityOfElement(int sec, WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), sec);
			wait.until(ExpectedConditions.invisibilityOf(webElement));
		} catch (Exception e) {
			log.error("Element "+webElement.getTagName()+"is still visible after waiting for" + sec);
			throw e;
		}
	}

	// Wait till the web element is clickable in the page/ time-out in seconds
	public WebElement explicitWaitForElementToBeClickable(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 50);
			return wait.until(ExpectedConditions.elementToBeClickable(webElement));
		} catch (Exception e) {
			log.error("Element "+webElement.getTagName()+"not clickable after waiting for 50 secs");
			throw e;
		}
	}

	// Wait till the web element is clickable in the page/ time-out in seconds and
	// then click
	public void explicitWaitForElementToBeClickableAndClick(WebElement webElement) {
		try {
			explicitWaitForElementToBeClickable(webElement);
			webElement.click();
		} catch (Exception e) {
			log.error("Error trying to click on element " + webElement.getTagName());
			throw e;
		}
	}

	// move hover function
	public static void hoverElement(WebElement element) {
		Actions builder = new Actions(getDriver());
		builder.moveToElement(element).build().perform();
	}

	// Function to generate Randon number used to append the email val read from
	// excel

	public int generateRandomNumber() {
		Random rand = new Random();
		int randNum = rand.nextInt(1000);
		return (randNum);
	}

	public String getColumnValueFromRow(WebElement baseTableElement, int colNum) {
		try {
			if (baseTableElement.isDisplayed()) {
				List<WebElement> rows_table = baseTableElement.findElements(By.tagName("tr"));
				List<WebElement> columns_per_row = rows_table.get(1).findElements(By.tagName("td"));
				String colTypeText = columns_per_row.get(colNum).getText();
				return colTypeText;
			}
		} catch (Exception e) {
			log.error("Not able to reterive the column value from :"+baseTableElement.getTagName());
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	public boolean compareValues(String str1, String str2) {
		if (str1.equals(str2))
			return true;
		else
			return false;
	}

	public String subStringCreation(String str, int index) {
		return (str.substring(index));
	}

}
