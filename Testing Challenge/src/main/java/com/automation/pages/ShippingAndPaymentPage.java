package com.automation.pages;

import com.automation.utilities.BasePage;
import static com.automation.utilities.ElementBy.xPath;

import org.openqa.selenium.WebElement;

public class ShippingAndPaymentPage extends BasePage {

	public void agreeToTerms() {
		findElement(xPath, "//*[@id='cgv']").click();
	}

	public void clickOnProceedToCheckOut() {
		findElement(xPath, "//*[@id='form']/p/button").click();
	}
	// Verify the order displayed in the table

	// check the model of the item selected

	public boolean verifyModelDetails(String selectedModel) {
		String modeldetail = findElement(xPath, "//table/tbody/tr/td[2]/small[1]").getText();
		String modelVal = subStringCreation(modeldetail, 6);
		return (compareValues(modelVal, selectedModel));
	}

	public boolean verifySizeDetails(String selectedSize) {
		String sizedetail = findElement(xPath, "//table/tbody/tr/td[2]/small[2]/a").getText();
		System.out.println(sizedetail);
		String sizeVal = subStringCreation(sizedetail, 23);
		return (compareValues(sizeVal, selectedSize));
	}

	public boolean verifyQtyDetails(int quantity) {
		String quantitydetail = findElement(xPath, "//table/tbody/tr/td[5]/span").getText();
		int qtyVal = Integer.parseInt(quantitydetail);
		if (qtyVal == quantity)
			return true;
		else
			return false;
	}

	public boolean verifyPriceDetails(double totalPrice) {
		WebElement baseTable = findElement(xPath, "//*[@id='cart_summary']");
		String pricedetail = getColumnValueFromRow(baseTable, 1);
		double priceVal = Double.parseDouble(pricedetail.substring(1));

		if (priceVal == totalPrice)
			return true;
		else
			return false;
	}

}
