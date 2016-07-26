package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_EO11_TC08 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_EO11_TC08",groups="GLFSS")
	public void endUserValidationWatchlist(String regUser, String regPwd) throws COSVisitorException,
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
	
	if(snW.clickByXpath("FSS_MySPARC_ViewMessagesIcon_Xpath"))
		Reporter.reportStep("Step 2: View Messages icon of red circle clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 2: View Messages icon of red circle not clicked successfully", "FAILURE");
	
	String enteredNumber="FSS0001416";
	System.out.println(enteredNumber);
	
	if(snW.getTextByXpath("FSS_MySPARC_SPARCMessages_Xpath").contains(enteredNumber))
		Reporter.reportStep("Step 3: New message with ticket number"+enteredNumber+"is received", "SUCCESS");
	else 
		Reporter.reportStep("Step 3: You have no new messages", "FAILURE");
	
	Thread.sleep(2000);

	snW.getDriver().get("https://sparctest.service-now.com/sparc/logout.do");

	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_EO11_TC08")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_EO11_TC08");
		return arrayObject;
	}
}