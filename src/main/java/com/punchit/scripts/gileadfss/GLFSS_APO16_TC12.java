package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_APO16_TC12 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_APO16_TC12",groups="GLFSS")
	public void validateTicketMyGroupsWork(String regUser, String regPwd) throws COSVisitorException,
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
			Reporter.reportStep("Step 2: The My Groups Work - menu could not be selected","FAILURE");

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
		
		snW.switchToDefault();
		
		if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
			Reporter.reportStep("Step 4: The Log out is clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("Step 4: The Log out could not be clicked.", "FAILURE");
		
		status = "PASS";
	}

finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_APO16_TC12")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_APO16_TC12");
		return arrayObject;
	}
}
