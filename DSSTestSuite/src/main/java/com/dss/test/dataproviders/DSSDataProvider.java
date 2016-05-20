package com.dss.test.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * ------- DSSDataProvider ------- 
 * Author: QA-DART 
 * Created on: 20-May-2016
 * History of Changes: Data Provider for Subscription Tests
 */
public class DSSDataProvider {

    @DataProvider(name="dssDataProvider")
    public static Object[][] getDSSTestData() {

	return new Object[][] {
		{ "90012", "tribune1", "TestCreditCard", "4111 1111 1111 1111", "05 - May", "2018", "James", "Hogger",
			"125 nimes rd st church", "", "32801", "Orlando", "FL", "651-628-4488" },
		 };
    }

}