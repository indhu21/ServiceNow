package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_EO5_TC04 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_EO5_TC04",groups="GLFSS")
	public void updateCommentsNotes(String regUser, String regPwd, String notesCustomerComments) throws COSVisitorException,
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
	
	if(snW.clickByXpath("FSS_FSSTasks_SearchedNumber_Xpath"))
		Reporter.reportStep("Step 4: Incident created in EO-1 clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 4: User not able to click the incident created in EO-1", "FAILURE");
	
	if(snW.clickByXpath("FSS_MySPARC_ActivityLog_Xpath"))
		Reporter.reportStep("Step 5: Activity Log clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 5: User not able to click Activity Log", "FAILURE");
	
	String customerComments="Beginning work on ticket, will get back shortly.";
	
	if(snW.getTextByXpath("FSS_MySPARC_CustomerComms_Xpath").contains(customerComments))
		Reporter.reportStep("Step 6: The comments :"+customerComments+"  entered by the ITIL user is seen", "SUCCESS");
	else 
		Reporter.reportStep("Step 6: The comments :"+customerComments+"  entered by the ITIL user is not seen", "FAILURE");
	
	if(snW.clickByXpath("FSS_MySPARC_Notes_Xpath"))
		Reporter.reportStep("Step 7: Notes tab clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 7: Notes tab not clicked successfully", "FAILURE");
	
	if(snW.enterByXpath("FSS_MySPARC_NotesCustomerComments_Xpath", notesCustomerComments))
		Reporter.reportStep("Step 8: Customer comments entered successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 8: Customer comments not entered successfully", "FAILURE");
	
	if(snW.clickByXpath("FSS_MySPARC_SaveButton_Xpath"))
		Reporter.reportStep("Step 9: Save button clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 9: Save button not clicked successfully", "FAILURE");
	
	Thread.sleep(2000);

	snW.getDriver().get("https://sparctest.service-now.com/sparc/logout.do");

	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_EO5_TC04")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_EO5_TC04");
		return arrayObject;
	}
}
