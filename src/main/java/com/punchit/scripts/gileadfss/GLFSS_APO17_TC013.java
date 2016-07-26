package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_APO17_TC013 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_APO17_TC013",groups="GLFSS")
	public void seeEscalatedTicket(String regUser, String regPwd, String workNotes, String assignmentGroup) throws COSVisitorException,
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
	
	if (snW.selectMenuFromMainHeader("FSS_Task", "My_Groups_Work"))
		Reporter.reportStep("Step 2: The My Groups Work - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 2: The My_Groups_Work - menu could not be selected","FAILURE");

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
		Reporter.reportStep("Step 3: The escalated ticket is listed in My Groups Work", "SUCCESS");
	else
		Reporter.reportStep("Step 3: The escalated ticket is not listed in My Groups Work", "FAILURE");
	
	if(snW.clickByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath"))
		Reporter.reportStep("Step 4: User is able to open the ticket", "SUCCESS");
	else
		Reporter.reportStep("Step 4: User not able to open the ticket", "FAILURE");
	
	snW.scrollToElementByXpath("FSS_FSSTasks_WorkNotes_Xpath");
	
	if(snW.enterByXpath("FSS_FSSTasks_WorkNotes_Xpath", workNotes))
		Reporter.reportStep("Step 5: Comments updated in Work Notes", "SUCCESS");
	else
		Reporter.reportStep("Step 5: Comments not updated in Work Notes", "FAILURE");
	
	if(snW.enterByXpath("FSS_MyGroupsWork_AssignmentGroup_Xpath", assignmentGroup))
		Reporter.reportStep("Step 6: Assignment Group updated", "SUCCESS");
	else
		Reporter.reportStep("Step 6: Assignment Group not updated", "FAILURE");
	
	Thread.sleep(2000);
	
	if(snW.clickByXpath("FSS_MyGroupsWork_UpdateButton_Xpath"))
		Reporter.reportStep("Step 7: Work Notes Updated", "SUCCESS");
	else
		Reporter.reportStep("Step 7: Work Notes not updated", "FAILURE");
	
	Thread.sleep(2000);
	
	if(snW.getTextByXpath("FSS_MyGroupsWork_AllRecords_Xpath").contains(ticketNumberAPO1TC01))
		Reporter.reportStep("Step 8: The ticket is listed in My Groups Work", "FAILURE");
	else
		Reporter.reportStep("Step 8: The ticket is not listed in My Groups Work", "SUCCESS");
	
	snW.switchToDefault();
	
	if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
		Reporter.reportStep("Step 9: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 9: The Log out could not be clicked.", "FAILURE");
	
	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_APO17_TC013")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_APO17_TC013");
		return arrayObject;
	}
}