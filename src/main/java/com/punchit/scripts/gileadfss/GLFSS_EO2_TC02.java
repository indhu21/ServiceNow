package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_EO2_TC02 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_EO2_TC02",groups="GLFSS")
	public void endUserValidation(String regUser, String regPwd) throws COSVisitorException,
	IOException, InterruptedException {

// Pre-requisities
snW = new ServiceNowWrappers(entityId);

try {

	// Step 0: Launch the application
	if (snW.launchApp(browserName, true))
		Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
	else
		Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

	// Step 1: Log in to application
	if (snW.loginFSS(regUser, regPwd))
		Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
	else
		Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
	
	snW.switchToFrame("Frame_Main");
	
		if(snW.clickByXpath("FSS_MySPARC_MyItems_Xpath"))
		Reporter.reportStep("Step 2: My Items clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 3: User not able to click My Items", "FAILURE");
	
	if(snW.clickByXpath("FSS_MySPARC_MyRequests_Xpath"))
		Reporter.reportStep("Step 3: My Requests clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 3: User not able to click My Requests", "FAILURE");
	
	Thread.sleep(2000);
	
	String enteredNumber="FSS0001416";
	System.out.println(enteredNumber);
	
	snW.getDriver().switchTo().parentFrame().switchTo().frame(1);
	snW.enterByXpath("FSS_MySPARC_MyRequests_Searchbox_Xpath",enteredNumber);		
	snW.PresEnter();
	String searchedNumber=snW.getTextByXpath("FSS_FSSTasks_SearchedNumber_Xpath");
	System.out.println(searchedNumber);
	
	if(searchedNumber.equalsIgnoreCase(enteredNumber))
		Reporter.reportStep("Step 4: The ticket created by FSS_EO1_TC01 is listed in My Items", "SUCCESS");
	else
		Reporter.reportStep("Step 4: The ticket created by FSS_EO1_TC01 is not listed in My Items", "FAILURE");
	
	Thread.sleep(2000);

	snW.getDriver().get("https://sparctest.service-now.com/sparc/logout.do");

	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_EO2_TC02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_EO2_TC02");
		return arrayObject;
	}
}