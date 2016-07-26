package com.punchit.scripts.gileadfss;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_EO1_TC01 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_EO1_TC01",groups="GLFSS")
	public void endUserValidation(String regUser, String regPwd, String shortDescription) throws COSVisitorException,
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
	
		if(snW.clickByXpath("FSS_MySPARC_INeedSomething_Xpath"))
		Reporter.reportStep("Step 2: I need something tab clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 2: User not able to click I need something tab", "FAILURE");
		
		if(snW.clickByXpath("FSS_MySPARC_FinancialSharedServices_Xpath"))
			Reporter.reportStep("Step 3: Financial Shared Services tab clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 3: User not able to click Financial Shared Services tab", "FAILURE");
		
		if(snW.clickByXpath("FSS_MySPARC_Invoice_Xpath"))
			Reporter.reportStep("Step 4: Invoice tab clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 4: User not able to click the Invoice tab", "FAILURE");
		
		if(snW.clickByXpath("FSS_MySPARC_ReportAnIssue_Xpath"))
			Reporter.reportStep("Step 5: Report an Issue tab clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 5: User not able to click Report an Issue tab", "FAILURE");
		
		if(snW.clickByXpath("FSS_MySPARC_Coding_Xpath"))
			Reporter.reportStep("Step 6: Coding tab clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 6: User not able to click Coding tab", "FAILURE");
		
		Thread.sleep(2000);

		snW.switchToFrame("Frame_GenericRequest");
		
		Thread.sleep(2000);
	
		if(snW.enterByXpath("FSS_MySPARC_InvoiceNumber_Xpath", "1234"))
			Reporter.reportStep("Step 7: Invoice number entered successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 7: User not able to enter the invoice number", "FAILURE");
		
		Thread.sleep(2000);
		
		if(snW.enterByXpath("FSS_MySPARC_ShortDescription_Xpath", shortDescription))
			Reporter.reportStep("Step 8: Short Description entered successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 8: User not able to enter the short description", "FAILURE");
		
		Thread.sleep(2000);
		
		if(snW.clickByXpath("FSS_MySPARC_SubmitButton_Xpath"))
			Reporter.reportStep("Step 9: Submit Button clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 9: User not able to click Submit button", "FAILURE");
		
		snW.scrollToElementByXpath("FSS_FSSTasks_Number_Xpath");
		
		String ticketNumberEO1TC01=snW.getAttributeByXpath("FSS_FSSTasks_Number_Xpath","value");
		System.out.println(ticketNumberEO1TC01);
		
		Thread.sleep(2000);

		snW.getDriver().get("https://sparctest.service-now.com/sparc/logout.do");

		status = "PASS";

}
	finally {
		// close the browser
		snW.quitBrowser();
	}
	}
		@DataProvider(name = "GLFSS_EO1_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_EO1_TC01");
			return arrayObject;
		}
	}