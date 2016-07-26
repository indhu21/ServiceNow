package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_APO4_TC03 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_APO4_TC03",groups="GLFSS")
	public void updateCustomerComments(String regUser, String regPwd, String customerComments) throws COSVisitorException,
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
	if (snW.login(regUser, regPwd))
		Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
	else
		Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
	
	if (snW.selectMenuFromMainHeader("FSS_Task", "Open_Records"))
		Reporter.reportStep("Step 2: The Open Records - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 2: The Open Records - menu could not be selected","FAILURE");

	snW.switchToFrame("Frame_Main");
	String ticketNumberAPO1TC01="FSS0001435";
	System.out.println(ticketNumberAPO1TC01);
	snW.selectByVisibleTextByXpath("FSS_FSSTasks_SelectNumberDropDown_Xpath", "Number");
	Thread.sleep(2000);
	snW.enterByXpath("FSS_FSSTasks_SearchBox_Xpath", ticketNumberAPO1TC01);
	Thread.sleep(2000);
	snW.PresEnter();
	
	String searchedNumber=snW.getTextByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath");
	System.out.println(searchedNumber);
	
	if(searchedNumber.equalsIgnoreCase(ticketNumberAPO1TC01))
		Reporter.reportStep("Step 3: The ticket created by FSS_APO2_TC01 is listed in Open Records", "SUCCESS");
	else
		Reporter.reportStep("Step 3: The ticket created by FSS_APO2_TC01 is not listed in Open Records", "FAILURE");
	
	if(snW.clickByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath"))
		Reporter.reportStep("Step 4: User is able to open the ticket", "SUCCESS");
	else
		Reporter.reportStep("Step 4: User not able to open the ticket", "FAILURE");
	
	if(snW.enterByXpath("FSS_FSSTasks_CustomerComments_Xpath", customerComments))
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

	  if(snW.getTextByXpath("FSS_FSSTasks_LatestCustomerComments_Xpath").contains(customerComments))
	   Reporter.reportStep("Step 7: The value :"+customerComments+"  does exist in Latest Customer Comments field successfully", "SUCCESS");
	  else 
	   Reporter.reportStep("Step 7: The value :"+customerComments+"  does not exist in Latest Customer Comments field", "FAILURE");
	
	snW.switchToDefault();
	
	if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
		Reporter.reportStep("Step 8: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 8: The Log out could not be clicked.", "FAILURE");
	
	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_APO4_TC03")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_APO4_TC03");
		return arrayObject;
	}
}