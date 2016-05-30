package com.dss.test.dss.subscription;

import org.testng.annotations.Test;
import com.dss.test.dataproviders.DSSDataProvider;
import com.dss.test.dss.pageobject.OSentinelCheckoutPageObject;
import com.dss.test.dss.pageobject.OSentinelHomepagePageObject;
import com.dss.test.dss.pageobject.OSentinelSubscriptionPageObject;
import com.dss.test.properties.DSSProperties;
import junit.framework.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;

/**
 * ------- SubscriptionTests ------- Author: QA-DART Created on: 17-May-2016
 * History of Changes: File created for holding TestNG tests
 */

public class SubscriptionTests {

	private WebDriver driver;
	private OSentinelHomepagePageObject OSHomePage;
	private OSentinelSubscriptionPageObject OSSubscriptionPage;
	private OSentinelCheckoutPageObject OSCheckoutPage;

	String hubUrl;

	@Parameters({ "browser", "version" })
	@BeforeMethod(alwaysRun = true)
	public void beforeTest(String browser, String version) throws MalformedURLException {

		hubUrl = "http://10.20.121.78:4444/wd/hub";

		DesiredCapabilities caps = new DesiredCapabilities();

		if (browser.equalsIgnoreCase("firefox")) {

			caps.setBrowserName("firefox");
		}

		if (browser.equalsIgnoreCase("chrome")) {

			caps.setBrowserName("chrome");

		}

		if (browser.equalsIgnoreCase("internet explorer")) {

			caps.setBrowserName("internet explorer");

		}

		caps.setPlatform(Platform.WINDOWS);
		caps.setVersion(version);

		driver = new RemoteWebDriver(new URL(hubUrl), caps);

		driver.manage().window().maximize();
		driver.get(DSSProperties.URL);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		OSHomePage = new OSentinelHomepagePageObject(driver);
		OSCheckoutPage = new OSentinelCheckoutPageObject(driver);

	}

	@Test(dataProvider = "dssDataProviderWithInAreaZIPSSOR", dataProviderClass = DSSDataProvider.class, enabled = true)
	public void BuyPrintPlusDigitalSubscriptionWithinAreaZIPWithSSOR(String withInAreaZIP, String email, String CCName,
			String CCNumber, String CCMonth, String CCYear, String userFirstName, String userLastName,
			String userAddress1, String userAddress2, String UserZIP, String UserCity, String UserState,
			String userPhonenmum, String pass) throws InterruptedException {

		String thankYouMessage;

		OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addPrintDigitalPlusAccessWithinArea(withInAreaZIP);
		OSCheckoutPage.enterDigitalAccessSSOR(email);
		OSCheckoutPage.payWithCreditCard(CCName, CCNumber, CCMonth, CCYear);

		OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(userFirstName, userLastName, userAddress1,
				userAddress2, UserZIP, UserCity, UserState, userPhonenmum);
		OSCheckoutPage.placeOrder();
		OSCheckoutPage.navigateToHomepageStory(email, pass);

		thankYouMessage = OSHomePage.getThankYouPanelMessage();
		Assert.assertEquals("Welcome to orlandosentinel.com. You are now subscribed.", thankYouMessage);

	}

	@Test(dataProvider = "dssDataProviderWithInAreaZIPNonSSOR", dataProviderClass = DSSDataProvider.class, enabled = true)
	public void BuyPrintPlusDigitalSubscriptionWithinAreaZIPWithNonSSOR(String withInAreaZIP, String pass,
			String BankName, String BankAccountNumber, String BankRoutingNumber, String userFirstName,
			String userLastName, String userAddress1, String userAddress2, String UserZIP, String UserCity,
			String UserState, String userPhonenmum) throws InterruptedException {

		String thankYouMessage;
		Random random = new Random();
		int randomnum = random.nextInt();

		String email = "jan" + randomnum + "@gmail.com";

		OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addPrintDigitalPlusAccessWithinArea(withInAreaZIP);
		OSCheckoutPage.enterDigitalAccessNonSSOR(email, pass, pass);
		OSCheckoutPage.payWithMyBankAccount(BankName, BankAccountNumber, BankRoutingNumber);

		OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(userFirstName, userLastName, userAddress1,
				userAddress2, UserZIP, UserCity, UserState, userPhonenmum);
		OSCheckoutPage.placeOrder();
		OSCheckoutPage.navigateToHomepageStory(email, pass);

		thankYouMessage = OSHomePage.getThankYouPanelMessage();
		Assert.assertEquals("Welcome to orlandosentinel.com. You are now subscribed.", thankYouMessage);

	}

	@Test(dataProvider = "dssDataProviderOutsideAreaZIPSSOR", dataProviderClass = DSSDataProvider.class, enabled = true)
	public void BuyPrintPlusDigitalSubscriptionOutSideAreaZIPWithSSOR(String outSideAreaZIP, String email,
			String BankName, String BankAccountNumber, String BankRoutingNumber, String userFirstName,
			String userLastName, String userAddress1, String userAddress2, String UserZIP, String UserCity,
			String UserState, String userPhonenmum, String pass) throws InterruptedException {

		String thankYouMessage;

		OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addPrintDigitalPlusAccessOutsideArea(outSideAreaZIP);
		OSCheckoutPage.enterDigitalAccessSSOR(email);
		OSCheckoutPage.payWithMyBankAccount(BankName, BankAccountNumber, BankRoutingNumber);

		OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(userFirstName, userLastName, userAddress1,
				userAddress2, UserZIP, UserCity, UserState, userPhonenmum);
		OSCheckoutPage.placeOrder();
		OSCheckoutPage.navigateToHomepageStory(email, pass);

		thankYouMessage = OSHomePage.getThankYouPanelMessage();
		Assert.assertEquals("Welcome to orlandosentinel.com. You are now subscribed.", thankYouMessage);

	}

	@Test(dataProvider = "dssDataProviderOutsideAreaZIPNonSSOR", dataProviderClass = DSSDataProvider.class, enabled = true)
	public void BuyPrintPlusDigitalSubscriptionOutSideAreaZIPWithNonSSOR(String outSideAreaZIP, String pass,
			String CCName, String CCNumber, String CCMonth, String CCYear, String userFirstName, String userLastName,
			String userAddress1, String userAddress2, String UserZIP, String UserCity, String UserState,
			String userPhonenmum) throws InterruptedException {

		String thankYouMessage;

		Random random = new Random();
		int randomnum = random.nextInt();

		String email = "jan" + randomnum + "@gmail.com";

		OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
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

	@Test(dataProvider = "dssDataProviderDigitalPlusSSOR", dataProviderClass = DSSDataProvider.class, enabled = true)
	public void BuydigitalPlusSubscriptionWithSSOR(String subscription, String email, String CCName, String CCNumber,
			String CCMonth, String CCYear, String userFirstName, String userLastName, String userAddress1,
			String userAddress2, String UserZIP, String UserCity, String UserState, String userPhonenmum, String pass)
			throws InterruptedException {

		String thankYouMessage;

		OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addDigitalPlusSubscription();
		OSCheckoutPage.selectPackage(driver, subscription);
		OSCheckoutPage.enterDigitalAccessSSOR(email);
		OSCheckoutPage.payWithCreditCard(CCName, CCNumber, CCMonth, CCYear);

		OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(userFirstName, userLastName, userAddress1,
				userAddress2, UserZIP, UserCity, UserState, userPhonenmum);
		OSCheckoutPage.placeOrder();
		OSCheckoutPage.navigateToHomepageStory(email, pass);

		thankYouMessage = OSHomePage.getThankYouPanelMessage();
		Assert.assertEquals("Welcome to orlandosentinel.com. You are now subscribed.", thankYouMessage);

	}

	@Test(dataProvider = "dssDataProviderDigitalPlusNonSSOR", dataProviderClass = DSSDataProvider.class, enabled = true)
	public void BuydigitalPlusSubscriptionWithNonSSOR(String subscription, String pass, String BankName,
			String BankAccountNumber, String BankRoutingNumber, String userFirstName, String userLastName,
			String userAddress1, String userAddress2, String UserZIP, String UserCity, String UserState,
			String userPhonenmum) throws InterruptedException {

		String thankYouMessage;

		Random random = new Random();
		int randomnum = random.nextInt();

		String email = "jan" + randomnum + "@gmail.com";

		OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
		OSSubscriptionPage.addDigitalPlusSubscription();
		OSCheckoutPage.selectPackage(driver, subscription);
		OSCheckoutPage.enterDigitalAccessNonSSOR(email, pass, pass);
		OSCheckoutPage.payWithMyBankAccount(BankName, BankAccountNumber, BankRoutingNumber);

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
