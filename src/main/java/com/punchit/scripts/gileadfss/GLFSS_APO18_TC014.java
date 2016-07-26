package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_APO18_TC014 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_APO18_TC014",groups="GLFSS")
	public void itilUserTicket(String regUser, String regPwd, String customerComments, String closureCode) throws COSVisitorException,
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
	
	if(snW.selectByVisibleTextByXpath("FSS_MyGroupsWork_State_Xpath", "Closed"))
		Reporter.reportStep("Step 5: Ticket state changed to Closed successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 5: User not able to change the ticket state", "FAILURE");
	
	String[] ReadField1={"FSS_MyGroupsWork_CustomerCommentsMandatory_Xpath"};
	String[] ReadLabel1={"Customer Comms"};
	
	snW.scrollToElementByXpath("FSS_MyGroupsWork_CustomerCommentsMandatory_Xpath");
	
	if(snW.verifyMandatoryFields(ReadField1, ReadLabel1))
		Reporter.reportStep("Step 6: Customer Comms field is mandatory", "SUCCESS");
	else
		Reporter.reportStep("Step 6: Customer Comms field is not mandatory", "FAILURE");
	
	snW.clickByXpath("FSS_MyGroupsWork_ClosureInformation_Xpath");
	
	String[] ReadField2={"FSS_MyGroupsWork_ClosureCodeMandatory_Xpath"};
	String[] ReadLabel2={"Closure Code"};
	
	//snW.scrollToElementByXpath("FSS_MyGroupsWork_CustomerCommentsMandatory_Xpath");
	
	if(snW.verifyMandatoryFields(ReadField2, ReadLabel2))
		Reporter.reportStep("Step 7: Closure Code field is mandatory", "SUCCESS");
	else
		Reporter.reportStep("Step 7: Closure Code field is not mandatory", "FAILURE");
	
	if(snW.enterByXpath("FSS_FSSTasks_CustomerComments_Xpath", customerComments))
		Reporter.reportStep("Step 8: Customer comments entered successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 8: Customer comments not entered", "FAILURE");
	
	snW.clickByXpath("FSS_MyGroupsWork_ClosureInformation_Xpath");
	
	if(snW.selectByVisibleTextByXpath("FSS_MyGroupsWork_ClosureCode_Xpath", closureCode))
		Reporter.reportStep("Step 9: Closure code selected successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 9: Closure code not selected", "FAILURE");
	
Thread.sleep(2000);
	
	if(snW.clickByXpath("FSS_MyGroupsWork_UpdateButton_Xpath"))
		Reporter.reportStep("Step 10: Update Button clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 10: Update Button not clicked successfully", "FAILURE");
	
	Thread.sleep(2000);
	
	snW.selectByVisibleTextByXpath("FSS_FSSTasks_SelectNumberDropDown_Xpath", "Number");
	Thread.sleep(2000);
	snW.enterByXpath("FSS_FSSTasks_SearchBox_Xpath", ticketNumberAPO1TC01);
	Thread.sleep(2000);
	snW.PresEnter();
	
	String searchedNumber1=snW.getTextByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath");
	System.out.println(searchedNumber);
	
	if(searchedNumber1.equalsIgnoreCase(ticketNumberAPO1TC01))
		Reporter.reportStep("Step 11: The ticket is listed in My Groups Work", "FAILURE");
	else
		Reporter.reportStep("Step 11: The ticket is not listed in My Groups Work", "SUCCESS");
	
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
	@DataProvider(name = "GLFSS_APO18_TC014")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_APO18_TC014");
		return arrayObject;
	}
}