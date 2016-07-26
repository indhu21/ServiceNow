package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_Stry0010128_Tc01 extends SuiteMethods{
	

	@Test(dataProvider = "GLOD_Stry0010128_Tc01",groups="OpsDirector")
	public void attachToCI(String regUser, String regPwd) throws COSVisitorException,
	IOException, InterruptedException {


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
	
	if (snW.selectMenu("Ops_Director","Data", "Events"))
		Reporter.reportStep("Step 2: The Events - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 2: The Events - menu could not be selected","FAILURE");

	// Switch to the main frame
	snW.switchToFrame("Frame_Main");
	
	//if(snW.getTextByXpath("ALERT_AlertId_Xpath").equals("No records to display")){
		//status = "Insufficient Data";
		//Reporter.reportStep("Step 3: There is no records to display for new Alerts","FAILURE");
	//}
	int totalCountRecordsBefore = 0;
	try {
	String countRecordBefore=snW.getTextByXpath("EVENTS_LastRecord_Xpath");

	totalCountRecordsBefore=Integer.valueOf(countRecordBefore);
	System.out.println("Total Records " +totalCountRecordsBefore);
	}catch(Exception e)
	{
		status = "Insufficient Data";
		Reporter.reportStep("Step 3: There are no Events to be selected ", "FAILURE");	
	}
	
//	snW.enterAndChoose("EVENTS_Search_Xpath", "Unknown CI");
//	snW.enterAndChoose("EVENTS_TypeSearch_Xpath", "Unknown CI");
//	Thread.sleep(3000);
//	
//	int totalCountRecords = 0;
//	try {
//	String countRecord=snW.getTextByXpath("EVENTS_LastRecord_Xpath");
//
//	totalCountRecords=Integer.valueOf(countRecord);
//	System.out.println("Total Records " +totalCountRecords);
//	}catch(Exception e)
//	{
//		status = "Insufficient Data";
//		Reporter.reportStep("Step 3: No records with Unknown CI Type", "FAILURE");	
//	}
//
	
	if (snW.rightClickByXpath("EVENTS_CreatedLink_Xpath"))
		Reporter.reportStep("Step 4: Right Click Successful", "SUCCESS");
	else
	Reporter.reportStep("Step 4: Right Click Unsuccessful ", "FAILURE");
	
	if(snW.clickByXpath("EVENTS_AttachToCI_Xpath"))
		Reporter.reportStep("Step 5: Attach to CI option selected", "SUCCESS");
	else
	
		Reporter.reportStep("Step 5: Attach to CI option not selected ", "FAILURE");
	
	snW.getDriver().switchTo().activeElement();
	
	if(snW.clickById("EVENTS_NewCI_Id"))
		Reporter.reportStep("Step 6: New CI selected", "SUCCESS");
	else
		Reporter.reportStep("Step 6: New CI not selected", "FAILURE");
	
	if(snW.selectByVisibleTextById("EVENTS_NewCIClass_Id", "Application Server"))
		Reporter.reportStep("Step 7: New CI Class is selected successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 7: New CI Class could not be  selected", "FAILURE");
	
	Thread.sleep(3000);
	
	if(snW.enterById("EVENTS_CINewName_Id", "server99.punch.com"))
		Reporter.reportStep("Step 8: New Name successfully given to CI", "SUCCESS");
	else
		Reporter.reportStep("Step 8: New Name not given to CI", "FAILURE");
	
//	String newNameText=snW.getTextByXpath("EVENTS_CINewName_Xpath");
//	System.out.println("Name is "+newNameText);
//	if(newNameText!="")
//	
//		Reporter.reportStep("Step 7: New name successfully given to CI", "SUCCESS");
//		else
//	{
//		flag=false;
//		Reporter.reportStep("Step 7: New name not auto populated", "WARNING");
//	}	
	if(snW.selectByVisibleTextById("EVENTS_IDFieldSource_Id", "Name"))
		Reporter.reportStep("Step 9: Value selected from Source Field", "SUCCESS");
	else
		Reporter.reportStep("Step 9: Value not selected from Source Field", "FAILURE");
	
	if(snW.clickByXpath("EVENTS_OkButton_Xpath"))
		Reporter.reportStep("Step 10: Ok button clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 10: Ok button not clicked", "FAILURE");
	
	Thread.sleep(3000);
	
	if (snW.selectMenu("Ops_Director","Data", "Events"))
		Reporter.reportStep("Step 11: The Events - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 11: The Events - menu could not be selected","FAILURE");
	
	snW.switchToFrame("Frame_Main");
	Thread.sleep(2000);

//	if(!snW.IsElementPresentByXpath("EVENTS_LastRecord_Xpath"))
//		Reporter.reportStep("Step 10: Event is removed from the Data hence Passed","SUCCESS");
//	
	if(snW.isExistByXpath("EVENTS_LastRecord_Xpath"))
	{
		String countRecordAfter=snW.getTextByXpath("EVENTS_LastRecord_Xpath");
		int totalCountRecordsAfter=Integer.valueOf(countRecordAfter);
		System.out.println("Total Records " +totalCountRecordsAfter);

		if(totalCountRecordsAfter<totalCountRecordsBefore)
			Reporter.reportStep("Step 12: Event is removed from the Data - As Expected", "SUCCESS");
		else
			Reporter.reportStep("Step 12: Event count is not decremented  hence Failed", "FAILURE");
	}
	else if(totalCountRecordsBefore==1)
	{
		Reporter.reportStep("Step 12: Event is removed from the Data - As Expected", "SUCCESS");
	}
	else
	{
		Reporter.reportStep("Step 12: All the Events are removed", "FAILURE");
	}
	snW.switchToDefault();
	
	if (snW.clickByXpath("Logout_Xpath"))
		Reporter.reportStep("Step 13: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 13: The Log out could not be clicked.", "FAILURE");
	
	
	status = "PASS";
	}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLOD_Stry0010128_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry0010128_Tc01");
		return arrayObject;
	}
}
