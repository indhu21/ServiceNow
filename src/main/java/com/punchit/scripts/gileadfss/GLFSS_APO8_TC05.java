package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_APO8_TC05 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_APO8_TC05",groups="GLFSS")
	public void addUserCustomerWatchlist(String regUser, String regPwd,String customerWatchlist) throws COSVisitorException,
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
	
	if(snW.clickByXpath("FSS_FSSTasks_ActivityLog_Xpath"))
		Reporter.reportStep("Step 5: Activity Log clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 5: Activity Log not clicked successfully", "FAILURE");
	
	String customerComments="Please provide update";
	
	snW.scrollToElementByXpath("FSS_FSSTasks_CustomerComms_Xpath");
	Thread.sleep(2000);
	
	if(snW.getTextByXpath("FSS_FSSTasks_CustomerComms_Xpath").contains(customerComments))
		Reporter.reportStep("Step 6: The new comments :"+customerComments+"  from user are updated", "SUCCESS");
	else 
		Reporter.reportStep("Step 6: The new comments :"+customerComments+"  from user are not updated", "FAILURE");
	
	if(snW.clickByXpath("FSS_FSSTasks_Notes_Xpath"))
		Reporter.reportStep("Step 7: Notes tab clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 7: Notes tab not clicked successfully", "FAILURE");
	
	if(snW.clickByXpath("FSS_FSSTasks_CustomerWatchlist_Xpath"))
		Reporter.reportStep("Step 8: Customer Watchlist clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 8: User not able to click Customer Watchlist", "FAILURE");
	
	if(snW.enterByXpath("FSS_FSSTasks_EditCustomerWatchlist_Xpath", customerWatchlist))
		Reporter.reportStep("Step 9: End User added to Customer Watchlist successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 9: End User added to Customer Watchlist successfully", "FAILURE");
	
	Thread.sleep(2000);
	
	if(snW.clickByXpath("FSS_FSSTasks_Lock_Xpath"))
		Reporter.reportStep("Step 10: Lock Button clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 10: User not able to click Lock Button", "FAILURE");
	
	if(snW.clickByXpath("FSS_FSSTasks_SaveButton_Xpath"))
		Reporter.reportStep("Step 11: Record saved successfully. Customer Watchlist has added the name", "SUCCESS");
	else
		Reporter.reportStep("Step 11: Record not saved successfully. Customer Watchlist has not added the name", "FAILURE");
	
	snW.switchToDefault();
	
	if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
		Reporter.reportStep("Step 12: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 12: The Log out could not be clicked.", "FAILURE");
	
	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_APO8_TC05")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_APO8_TC05");
		return arrayObject;
	}
}