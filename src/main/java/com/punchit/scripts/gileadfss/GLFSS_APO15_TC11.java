package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_APO15_TC11 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_APO15_TC11",groups="GLFSS")
	public void changeAssignmentGroup(String regUser, String regPwd, String assignmentGroup, String User2, String Pwd2) throws COSVisitorException,
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
	
	if(snW.getTextByXpath("FSS_MyGroupsWork_AllRecords_Xpath").contains("PTP L1"))
		Reporter.reportStep("Step 4: The value of the Assignment Group field is PTP L1", "SUCCESS");
	else
		Reporter.reportStep("Step 4: The value of the Assignment Group field is not PTP L1", "FAILURE");
	
	if(snW.clickByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath"))
		Reporter.reportStep("Step 5: User is able to open the ticket", "SUCCESS");
	else
		Reporter.reportStep("Step 5: User not able to open the ticket", "FAILURE");
	
	Thread.sleep(2000);
		
		//Change the priority 
		if(snW.enterAndChoose("FSS_MyGroupsWork_AssignmentGroup_Xpath", assignmentGroup))
			Reporter.reportStep("Step 6: Assignment Group field is set successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 6: Assignment Group field could not be set successfully", "FAILURE");
		
		Thread.sleep(2000);
		
        //Save the ticket		
		if(snW.clickByXpath("FSS_MyGroupsWork_UpdateButton_Xpath"))
			Reporter.reportStep("Step 7: Update button clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 7: Update button not clicked successfully", "FAILURE");
		
		snW.selectByVisibleTextByXpath("FSS_FSSTasks_SelectNumberDropDown_Xpath", "Number");
		Thread.sleep(2000);
		snW.enterByXpath("FSS_FSSTasks_SearchBox_Xpath", ticketNumberAPO1TC01);
		Thread.sleep(2000);
		snW.PresEnter();
		
		if(snW.getTextByXpath("FSS_MyGroupsWork_AllRecords_Xpath").contains(assignmentGroup))
			Reporter.reportStep("Step 8: Assignment group is set successfully to "+assignmentGroup, "SUCCESS");
		else
			Reporter.reportStep("Step 8: Assignment group is not set successfully", "FAILURE");
		
        
	snW.switchToDefault();
	
	if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
		Reporter.reportStep("Step 9: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 9: The Log out could not be clicked.", "FAILURE");
	// Step 1: Log in to application
	if (snW.loginFSS(User2, Pwd2))
		Reporter.reportStep("Step 10: The login with username:"+ User2 + " is successful", "SUCCESS");
	else
		Reporter.reportStep("Step 10: The login with username:"+ User2 + " is not successful", "FAILURE");
	
	snW.switchToFrame("Frame_Main");
	
		if(snW.clickByXpath("FSS_MySPARC_MyItems_Xpath"))
		Reporter.reportStep("Step 11: My Items clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 11: User not able to click My Items", "FAILURE");
	
	if(snW.clickByXpath("FSS_MySPARC_MyRequests_Xpath"))
		Reporter.reportStep("Step 12: My Requests clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 12: User not able to click My Requests", "FAILURE");
	
	Thread.sleep(2000);
	
	snW.getDriver().switchTo().parentFrame().switchTo().frame(1);
	snW.enterByXpath("FSS_MySPARC_MyRequests_Searchbox_Xpath",ticketNumberAPO1TC01);		
	snW.PresEnter();
	String searchedNumber1=snW.getTextByXpath("FSS_FSSTasks_SearchedNumber_Xpath");
	System.out.println(searchedNumber1);
	
	if(searchedNumber.equalsIgnoreCase(ticketNumberAPO1TC01))
		Reporter.reportStep("Step 13: The ticket that is assigned to PTP L2 is visible", "FAILURE");
	else
		Reporter.reportStep("Step 13: The ticket that is assigned to PTP L2 is not visible", "SUCCESS");
	
	Thread.sleep(2000);

	snW.getDriver().get("https://sparctest.service-now.com/sparc/logout.do");

	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_APO15_TC11")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_APO15_TC11");
		return arrayObject;
	}
}
