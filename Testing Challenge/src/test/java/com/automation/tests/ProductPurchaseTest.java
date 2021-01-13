package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.pages.DressSelectionPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginAndRegistrationPage;
import com.automation.pages.OrderDetailPage;
import com.automation.pages.ShippingAndPaymentPage;
import com.automation.utilities.OrderDetails;

public class ProductPurchaseTest extends BaseTest {

	private HomePage homepage = new HomePage();
	private DressSelectionPage dressSelection = new DressSelectionPage();
	private OrderDetailPage orderDetail = new OrderDetailPage();
	private LoginAndRegistrationPage loginPage = new LoginAndRegistrationPage();
	private ShippingAndPaymentPage shipAndPay = new ShippingAndPaymentPage();

	@BeforeMethod
	public void initialize() {
		// Test Step 1
		loadHomePage();
		Assert.assertEquals(homepage.getPageTitle(), "My Store");
	}

	@Test
	public void TC_01_01_ProductCheckOut() throws InterruptedException {
		try {
			// TestStep 2 : Navigate/MouseHover to the 'Women' tab and click on 'Summer
			// Dresses' option
			homepage.mousehoverMenuWomen();
			homepage.clickOnSummerDressesMenu();
			
			Assert.assertEquals(homepage.getPageTitle(), "Summer Dresses - My Store");
			
			// Test Step 3 : Mouse over 'Printed Summer Dress' and click 'Quick view' button
			dressSelection.mousehoverPrintedDressAndClickQuickView();
			// Test Step 4 - Select 'S' size from the drop down and click on 'Add to Cart'
			// button
			OrderDetails order = dressSelection.selectSizeAndAddToCart();
			
			Assert.assertEquals(dressSelection.checkForProdAddedConfirmMsg(),
					"Product successfully added to your shopping cart");
			
			
			// Test Step 5 - Click on Continue Shopping button
			dressSelection.clickOnContinueShoppingButton();
			// Test Step 6 - Hover over to 'Cart’ section and click 'Check Out' button
			homepage.mouseHoverOnCart();
			Assert.assertEquals(orderDetail.getOrderPageTitle(), "Order - My Store", "Incorrect Page displayed");
			
			
			// TestStep 7 - Click 'Proceed to checkout' button
			orderDetail.clickOnProceedToCheckOut();
			Assert.assertEquals(homepage.getPageTitle(), "Login - My Store", "Incorrect Page Displayed");
			// Test Step 8 - Enter an email and click on 'Create an Account' button
			// Test Step 9 - Fill in mandatory fields and click 'Register' button
			loginPage.enterValInMadatoryFields();
			// Test Step 10 - Click 'Proceed to checkout' button on Address tab
			loginPage.clickOnProceedtoCheckOut();
			// Test Step 11- Agree to 'Terms of service' and Click on 'Proceed to checkout'
			// button on Shipping tab
			shipAndPay.agreeToTerms();
			shipAndPay.clickOnProceedToCheckOut();
			
			// verifying the model details of the order
			Assert.assertEquals(shipAndPay.verifyModelDetails(order.model), true,"Model displayed is incorrect");
			// Verify size details of the selected order
			Assert.assertEquals(shipAndPay.verifySizeDetails(order.size), true, "Size selected in incorrect");
			// Verify the quantity detail of the order
			Assert.assertEquals(shipAndPay.verifyQtyDetails(order.quantity), true,"Quantity selected in incorrect");
			// Verify the price details
			Assert.assertEquals(shipAndPay.verifyPriceDetails(order.price), true, "Price displayed is in correct");
		} catch (Exception e) {
			Assert.fail("Test 'TC_01_01_ProductCheckOut' failed with Exception", e);
		}
	}
}
