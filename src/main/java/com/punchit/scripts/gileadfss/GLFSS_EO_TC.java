package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_EO_TC extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_EO_TC",groups="GLFSS")
	public void endUserValidation(String regUser1, String regPwd1, String shortDescription1,String regUser3, String regPwd3, String customerComments3) throws COSVisitorException,
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
	if (snW.loginFSS(regUser1, regPwd1))
		Reporter.reportStep("Step 1: The login with username:"+ regUser1 + " is successful", "SUCCESS");
	else
		Reporter.reportStep("Step 1: The login with username:"+ regUser1 + " is not successful", "FAILURE");
	
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
		
		if(snW.enterByXpath("FSS_MySPARC_ShortDescription_Xpath", shortDescription1))
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

		snW.getDriver().get("https://sparctest.service-now.com/navpage.do");
		
		snW.switchToDefault();
			
			if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
				Reporter.reportStep("Step 10: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 10: The Log out could not be clicked.", "FAILURE");

		status = "PASS";
		setEntityId("403");
		
		if (snW.loginFSS(regUser1, regPwd1))
			Reporter.reportStep("Step 1: The login with username:"+ regUser1 + " is successful", "SUCCESS");
		else
			Reporter.reportStep("Step 1: The login with username:"+ regUser1 + " is not successful", "FAILURE");
		
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
		
		snW.getDriver().switchTo().parentFrame().switchTo().frame(1);
		snW.enterByXpath("FSS_MySPARC_MyRequests_Searchbox_Xpath",ticketNumberEO1TC01);		
		snW.PresEnter();
		String searchedNumber1=snW.getTextByXpath("FSS_FSSTasks_SearchedNumber_Xpath");
		System.out.println(searchedNumber1);
		
		if(searchedNumber1.equalsIgnoreCase(ticketNumberEO1TC01))
			Reporter.reportStep("Step 4: The ticket created by FSS_EO1_TC01 is listed in My Items", "SUCCESS");
		else
			Reporter.reportStep("Step 4: The ticket created by FSS_EO1_TC01 is not listed in My Items", "FAILURE");
		
		Thread.sleep(2000);

		snW.getDriver().get("https://sparctest.service-now.com/navpage.do");
		
		snW.switchToDefault();
			
			if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
				Reporter.reportStep("Step 5: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 5: The Log out could not be clicked.", "FAILURE");

		status = "PASS";
		setEntityId("404");
		
		if (snW.login(regUser3, regPwd3))
			Reporter.reportStep("Step 1: The login with username:"+ regUser3 + " is successful", "SUCCESS");
		else
			Reporter.reportStep("Step 1: The login with username:"+ regUser3 + " is not successful", "FAILURE");
		
		if (snW.selectMenuFromMainHeader("FSS_Task", "Open_Records"))
			Reporter.reportStep("Step 2: The Open Records - menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("Step 2: The Open Records - menu could not be selected","FAILURE");

		snW.switchToFrame("Frame_Main");
		snW.selectByVisibleTextByXpath("FSS_FSSTasks_SelectNumberDropDown_Xpath", "Number");
		Thread.sleep(2000);
		snW.enterByXpath("FSS_FSSTasks_SearchBox_Xpath", ticketNumberEO1TC01);
		Thread.sleep(2000);
		snW.PresEnter();
		
		String searchedNumber3=snW.getTextByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath");
		System.out.println(searchedNumber3);
		
		if(searchedNumber3.equalsIgnoreCase(ticketNumberEO1TC01))
			Reporter.reportStep("Step 3: The ticket created by FSS_EO1_TC01 is listed in Open Records", "SUCCESS");
		else
			Reporter.reportStep("Step 3: The ticket created by FSS_EO1_TC01 is not listed in Open Records", "FAILURE");
		
		if(snW.clickByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath"))
			Reporter.reportStep("Step 4: User is able to open the ticket", "SUCCESS");
		else
			Reporter.reportStep("Step 4: User not able to open the ticket", "FAILURE");
		
		if(snW.enterByXpath("FSS_FSSTasks_CustomerComments_Xpath", customerComments3))
			Reporter.reportStep("Step 5: Customer comments entered successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 5: Customer comments not entered successfully", "FAILURE");
		
		if(snW.clickByXpath("FSS_FSSTasks_SaveButton_Xpath"))
			Reporter.reportStep("Step 6: Save button clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 6: Save button not clicked successfully", "FAILURE");
		
		//snW.getAttributeByXpath("FSS_FSSTasks_LatestCustomerComms_Xpath", "value");
		
		  //scrollPageDown();
		snW.scrollToElementByXpath("FSS_FSSTasks_LatestCustomerComments_Xpath");

		  if(snW.getTextByXpath("FSS_FSSTasks_LatestCustomerComments_Xpath").contains(customerComments3))
		   Reporter.reportStep("Step 7: The value :"+customerComments3+"  does exist in Latest Customer Comments field successfully", "SUCCESS");
		  else 
		   Reporter.reportStep("Step 7: The value :"+customerComments3+"  does not exist in Latest Customer Comments field", "FAILURE");
		
		snW.switchToDefault();
		
		if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
			Reporter.reportStep("Step 8: The Log out is clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("Step 8: The Log out could not be clicked.", "FAILURE");
		
		status = "PASS";
		setEntityId("405");
		
		
}
	finally {
		// close the browser
		snW.quitBrowser();
	}
	}
		@DataProvider(name = "GLFSS_EO_TC")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_EO_TC");
			return arrayObject;
		}
	}