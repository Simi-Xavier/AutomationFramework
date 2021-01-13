package com.automation.pages;

import com.automation.utilities.BasePage;
import com.automation.utilities.ExcelUtils;
import static com.automation.utilities.ElementBy.xPath;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginAndRegistrationPage extends BasePage {

	private ExcelUtils excelUtils = new ExcelUtils();

	public void inputEmail(String val) {

		WebElement emailElmt = findElement(xPath, "//*[@id='email_create']");
		String newEmailVal = val + generateRandomNumber() + "@techhub.com";
		emailElmt.sendKeys(newEmailVal);
		findElement(xPath, "//*[@id='SubmitCreate']/span").click();
	}


	public void enterValInMadatoryFields() {
		String worksheetName = "TestData";
		String sheet1 = "Registration_TestData";
		try {
			excelUtils.openExcel(worksheetName);
			excelUtils.loadSheet(sheet1);

			String val = excelUtils.getCellData(1, 0);
			WebElement emailElmt = findElement(xPath, "//*[@id='email_create']");
			String newEmailVal = val + generateRandomNumber() + "@techhub.com";
			emailElmt.sendKeys(newEmailVal);

			findElement(xPath, "//*[@id='SubmitCreate']/span").click();

			implicitWait(30);
			// Input firstName
			explicitWaitForElementToBeClickable(findElement(xPath, "//*[@id='customer_firstname']"));
			String FName = excelUtils.getCellData(1, 1).toString();
			findElement(xPath, "//*[@id='customer_firstname']").sendKeys(FName);
			findElement(xPath, "//*[@id='customer_lastname']").sendKeys(excelUtils.getCellData(1, 2));
			findElement(xPath, "//*[@id='passwd']").sendKeys(excelUtils.getCellData(1, 3));
			findElement(xPath, "//*[@id='address1']").sendKeys(excelUtils.getCellData(1, 4));
			findElement(xPath, "//*[@id='city']").sendKeys(excelUtils.getCellData(1, 5));

			Select stateOpt = new Select(findElement(xPath, "//*[@id='id_state']"));
			stateOpt.selectByVisibleText(excelUtils.getCellData(1, 6));

			findElement(xPath, "//*[@id='postcode']").sendKeys(excelUtils.getCellData(1, 7).toString());

			Select countryOpt = new Select(findElement(xPath, "//*[@id='id_country']"));
			countryOpt.selectByVisibleText(excelUtils.getCellData(1, 8));

			findElement(xPath, "//*[@id='phone_mobile']").sendKeys(excelUtils.getCellData(1, 9));

			findElement(xPath, "//*[@id='alias']").sendKeys(excelUtils.getCellData(1, 10));

			findElement(xPath, "//*[@id='submitAccount']").click();
		} finally {
			excelUtils.closeWorkBook();
		}

	}

	public void clickOnProceedtoCheckOut() {
		findElement(xPath, "//*[@id='center_column']/form/p/button").click();
	}
}
