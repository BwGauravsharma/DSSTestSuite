package com.dss.test.dss.subscription;

import org.testng.annotations.Test;

import com.dss.test.dataproviders.DSSDataProvider;
import com.dss.test.dss.pageobject.OSentinelCheckoutPageObject;
import com.dss.test.dss.pageobject.OSentinelHomepagePageObject;
import com.dss.test.dss.pageobject.OSentinelSubscriptionPageObject;
import com.dss.test.properties.DSSProperties;

import junit.framework.Assert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * ------- SubscriptionTests ------- 
 * Author: QA-DART 
 * Created on: 17-May-2016
 * History of Changes: File created for holding TestNG tests
 */
public class SubscriptionTests {

	private WebDriver driver;
	private OSentinelHomepagePageObject OSHomePage;
	private OSentinelSubscriptionPageObject OSSubscriptionPage;
	private OSentinelCheckoutPageObject OSCheckoutPage;

	@BeforeMethod
	public void beforeTest() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(DSSProperties.URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		OSHomePage = new OSentinelHomepagePageObject(driver);
		OSSubscriptionPage = new OSentinelSubscriptionPageObject(driver);
		OSCheckoutPage = new OSentinelCheckoutPageObject(driver);

	}

	@Test(enabled = false)
	public void BuyPrintPlusDigitalSubscriptionWithinAreaZIPWithSSOR() throws InterruptedException {

		String thankYouMessage;

		OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addPrintDigitalPlusAccessWithinArea("32801");
		OSCheckoutPage.enterDigitalAccessSSOR("jan12@gmail.com");
		OSCheckoutPage.payWithCreditCard("TestCreditCard", "4111 1111 1111 1111", "05 - May", "2018");

		OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame("James", "Hogger", "125 nimes rd st church",
				"", "32801", "Orlando", "FL", "8888888888");
		OSCheckoutPage.placeOrder();
		OSCheckoutPage.navigateToHomepageStory("jan12@gmail.com", "tribune1");

		thankYouMessage = OSHomePage.getThankYouPanelMessage();
		Assert.assertEquals("Welcome to orlandosentinel.com. You are now subscribed.", thankYouMessage);

	}

	@Test(enabled = false)
	public void BuyPrintPlusDigitalSubscriptionWithinAreaZIPWithNonSSOR() throws InterruptedException {

		String thankYouMessage;
		Random random = new Random();
		int randomnum = random.nextInt();

		String email = "jan" + randomnum + "@gmail.com";

		OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addPrintDigitalPlusAccessWithinArea("32801");
		OSCheckoutPage.enterDigitalAccessNonSSOR(email, "tribune1", "tribune1");
		OSCheckoutPage.payWithMyBankAccount("USSA", "1234567895", "071923909");

		OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame("James", "Hogger", "125 nimes rd st church",
				"", "32801", "Orlando", "FL", "8888888888");
		OSCheckoutPage.placeOrder();
		OSCheckoutPage.navigateToHomepageStory(email, "tribune1");

		thankYouMessage = OSHomePage.getThankYouPanelMessage();
		Assert.assertEquals("Welcome to orlandosentinel.com. You are now subscribed.", thankYouMessage);

	}

	@Test(enabled = false)
	public void BuyPrintPlusDigitalSubscriptionOutSideAreaZIPWithSSOR() throws InterruptedException {

		String thankYouMessage;

		OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addPrintDigitalPlusAccessOutsideArea("90012");
		OSCheckoutPage.enterDigitalAccessSSOR("jan12@gmail.com");
		OSCheckoutPage.payWithMyBankAccount("USSA", "1234567895", "071923909");

		OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame("James", "Hogger", "125 nimes rd st church",
				"", "32801", "Orlando", "FL", "8888888888");
		OSCheckoutPage.placeOrder();
		OSCheckoutPage.navigateToHomepageStory("jan12@gmail.com", "tribune1");

		thankYouMessage = OSHomePage.getThankYouPanelMessage();
		Assert.assertEquals("Welcome to orlandosentinel.com. You are now subscribed.", thankYouMessage);

	}

	@Test(dataProvider="dssDataProvider", dataProviderClass=DSSDataProvider.class ,enabled = true)
	public void BuyPrintPlusDigitalSubscriptionOutSideAreaZIPWithNonSSOR(String outSideAreaZIP, String pass, String CCName, String CCNumber, String CCMonth, String CCYear, String userFirstName, String userLastName, String userAddress1, String userAddress2, String UserZIP,String UserCity, String UserState, String userPhonenmum) throws InterruptedException {

		String thankYouMessage;

		Random random = new Random();
		int randomnum = random.nextInt();

		String email = "jan" + randomnum + "@gmail.com";

		OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addPrintDigitalPlusAccessOutsideArea(outSideAreaZIP);
		OSCheckoutPage.enterDigitalAccessNonSSOR(email, pass, pass);
		OSCheckoutPage.payWithCreditCard(CCName, CCNumber, CCMonth, CCYear);

		OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(userFirstName, userLastName, userAddress1,
				userAddress2, UserZIP, UserCity, UserState, userPhonenmum);
		OSCheckoutPage.placeOrder();
		OSCheckoutPage.navigateToHomepageStory(email, pass);
		

		thankYouMessage = OSHomePage.getThankYouPanelMessage();
		Assert.assertEquals("Welcome to orlandosentinel.com. You are now subscribed.", thankYouMessage);

	}

	@AfterMethod
	public void afterTest() {

		driver.quit();

	}

}
