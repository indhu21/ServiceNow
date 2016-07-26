package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_EO13_TC010 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_EO13_TC010",groups="GLFSS")
	public void changeState(String regUser, String regPwd, String WIP) throws COSVisitorException,
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
	String ticketNumberEO1TC01="FSS0001416";
	System.out.println(ticketNumberEO1TC01);
	snW.selectByVisibleTextByXpath("FSS_FSSTasks_SelectNumberDropDown_Xpath", "Number");
	Thread.sleep(2000);
	snW.enterByXpath("FSS_FSSTasks_SearchBox_Xpath", ticketNumberEO1TC01);
	Thread.sleep(2000);
	snW.PresEnter();
	
	String searchedNumber=snW.getTextByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath");
	System.out.println(searchedNumber);
	
	if(searchedNumber.equalsIgnoreCase(ticketNumberEO1TC01))
		Reporter.reportStep("Step 3: The ticket created by FSS_EO1_TC01 is listed in Open Records", "SUCCESS");
	else
		Reporter.reportStep("Step 3: The ticket created by FSS_EO1_TC01 is not listed in Open Records", "FAILURE");
	
	if(snW.clickByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath"))
		Reporter.reportStep("Step 4: User is able to open the ticket", "SUCCESS");
	else
		Reporter.reportStep("Step 4: User not able to open the ticket", "FAILURE");
	
	if(snW.getTextByXpath("FSS_FSSTasks_EntireForm_Xpath").contains("Open"))
		Reporter.reportStep("Step 5: The value of the State field is Open", "SUCCESS");
	else
		Reporter.reportStep("Step 5: The value of the State field is not Open", "FAILURE");
	
		//Change the State from  open to Work in progress
		if(snW.selectByVisibleTextByXpath("FSS_MyGroupsWork_State_Xpath", WIP))
			Reporter.reportStep("Step 6: State changed to Work in Progress", "SUCCESS");
		else
			Reporter.reportStep("Step 6: State not changed to Work in Progress", "FAILURE");
		
		if(snW.clickByXpath("FSS_MyGroupsWork_UpdateButton_Xpath"))
			Reporter.reportStep("Step 7: Update button clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 7: Update button not clicked successfully", "FAILURE");
        
        snW.selectByVisibleTextByXpath("FSS_FSSTasks_SelectNumberDropDown_Xpath", "Number");
    	Thread.sleep(2000);
    	snW.enterByXpath("FSS_FSSTasks_SearchBox_Xpath", ticketNumberEO1TC01);
    	Thread.sleep(2000);
    	snW.PresEnter();
    	
    	if(snW.getTextByXpath("FSS_MyGroupsWork_AllRecords_Xpath").contains(WIP))
			Reporter.reportStep("Step 8: Changed state is set successfully to "+WIP, "SUCCESS");
		else
			Reporter.reportStep("Step 8: Changed state is not set successfully", "FAILURE");
		
	
	snW.switchToDefault();
	
	if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
		Reporter.reportStep("Step 9: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 9: The Log out could not be clicked.", "FAILURE");
	
//	snW.getDriver().get("https://sparctest.service-now.com/sparc/logout.do");

	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_EO13_TC010")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_EO13_TC010");
		return arrayObject;
	}
}