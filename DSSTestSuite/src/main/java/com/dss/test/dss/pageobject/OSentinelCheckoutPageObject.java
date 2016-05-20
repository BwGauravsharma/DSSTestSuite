package com.dss.test.dss.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * ------- OSentinelCheckoutPageObject ------- 
 * Author: QA-DART
 * Created on: 18-May-2016
 * History of Changes: Page Object for OS Checkout Page
 */
public class OSentinelCheckoutPageObject extends CheckoutPageObject {

	private WebDriver driver;

	public OSentinelCheckoutPageObject(WebDriver driver) {

		super(driver);

	}

	@Override
	public void selectPackage(WebDriver driver, String subscription) {
		// TODO Auto-generated method stub

		WebElement Digital = driver.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[1]/h3"));
		WebElement DigitalSuperSaver = driver
				.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/div[2]/div/div[1]/h3"));
		WebElement DigitalSaver = driver
				.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/div[3]/div/div[1]/h3"));

		WebElement SelectDigital = driver
				.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[3]/a"));
		WebElement SelectDigitalSuperSaver = driver
				.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/div[2]/div/div[3]/a"));
		WebElement SelectDigitalSaver = driver
				.findElement(By.xpath("html/body/div[1]/div[3]/div[1]/div[2]/div[3]/div/div[3]/a"));

		if ((Digital != null) && (Digital.getText().equals(subscription))) {

			SelectDigital.click();

		}

		if ((DigitalSuperSaver != null) && (DigitalSuperSaver.getText().equals(subscription))) {

			SelectDigitalSuperSaver.click();

		}

		if ((DigitalSaver != null) && (DigitalSaver.getText().equals(subscription))) {

			SelectDigitalSaver.click();

		}

	}

}
