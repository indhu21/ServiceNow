package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0010165_TC02 extends SuiteMethods{
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_STRY0010165_TC02",groups="OpsDirector")
	public void alertProfileIncidentAssignmentGroup(String regUser, String regPwd,
			String revUser, String revPwd, String f2Section,String f3Section, 
			String f2Condition, String f2Value, String Caller) throws COSVisitorException,
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
	
	//Step :2 Click on Alert Console menu
	if (snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
		Reporter.reportStep("Step 2: The Alert Console - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 2: The Alert Console - menu could not be selected","FAILURE");

	snW.Wait(3000);
	// Switch to the main frame
	snW.switchToFrame("Frame_Main");
	
	// Step 3: Clicking on new alert 
	if(!snW.clickLink("NEW_ALERTS_LINK"))
	    Reporter.reportStep("Step 3: New alert link could be clicked","FAILURE");
	
	snW.Wait(3000);
	// Setting filter condition
	if(!snW.clickById("ALERT_FunnelIcon_Id"))
		Reporter.reportStep("Step 3: Filter icon couldn't be clicked","FAILURE");
	 snW.Wait(2000);
	// snW.clickByXpath("ALERT_Actionsandselcet_Xpath");
	if(!snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath"))
		Reporter.reportStep("Step 3: AND button couldn't be clicked","FAILURE");
	
	if(!snW.selectByVisibleTextByXpath("NEW_ALERT_FirstFilterType2_Xpath",f2Section))
		Reporter.reportStep("Step 3: CI Filter section  could not be selected","FAILURE");
          
	snW.Wait(2000);
	if(!snW.selectByVisibleTextByXpath("NEW_ALERT_FirstFilterType2_Xpath",f3Section))
		Reporter.reportStep("Step 3: second CI Filter section  could not be selected","FAILURE");
     
	snW.pressKey(Keys.TAB);
	/*snW.Wait(1000);
	if(!snW.sendKey(f2Condition))
	    Reporter.reportStep("Step 3: Filter condition could not be selected","FAILURE");*/
    snW.pressKey(Keys.TAB);
    snW.Wait(1000);
    if(snW.sendKey(f2Value))
        Reporter.reportStep("Step 3: Filter is set successfully ", "SUCCESS");
    else
        Reporter.reportStep("Step 3: Filter value could not be selected","FAILURE");
    snW.Wait(3000);
	
   snW.scrollToElementByXpath("Runfilter_Xpath", 0, -600);
    snW.Wait(3000);
   if(!snW.clickByXpath("Runfilter_Xpath"))
         Reporter.reportStep("Step 3: Run button could not be selected","FAILURE");
    
	//Step 4:Capturing the first element value and acknowledging
	String numberAlertText = snW.getTextByXpath("Paralt_xpath");
	System.out.println(numberAlertText);
	if (!snW.rightClickByXpath("Paralt_xpath"))
	{
		status="INSUFFICIENT DATA";
		Reporter.reportStep("Step 4: Right Click Unsuccessful", "FAILURE");
	}

	if(snW.clickByXpath("AlertConsole_Rightclick_selectAcknowledge_xpath"))
		Reporter.reportStep("Step 4: Acknowledge option selected", "SUCCESS");
	else
		Reporter.reportStep("Step 4: Acknowledge option not selected", "FAILURE");
	
	snW.switchToFrame("Frame_Nav");
	
	// Step 5: Slecting My alerts from my altert menu
	if (snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
		Reporter.reportStep("Step 5: My Alerts - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 5: My Alerts - menu could not be selected","FAILURE");
	
	snW.switchToFrame("Frame_Main");
	
	// Step 6: CLicking on my alert link
	if(snW.clickLink("MY_ALERTS_LINK"))
		Reporter.reportStep("Step 6: My Alerts link selected successfully","SUCCESS");
	else
	     Reporter.reportStep("Step 6: My Alerts lik culd not be clicked","FAILURE");
	
	// Step 7: Search the acknowledged alert number in the search box
	if(!snW.enterAndChoose("MyAlerts_Number_Search_Xpath", numberAlertText))
		Reporter.reportStep("Step 7: knowleged alert number could not be filled in the search box","FAILURE");
		Thread.sleep(2000);
	
    //Step 7: selecting the acknowledged alert
	if(snW.clickByXpath("MyAlerts_SelectedAcknowledgedAlert_Xpath"))
		Reporter.reportStep("Step 7: Acknowledged Alert found in My Alerts", "SUCCESS");
	else
		Reporter.reportStep("Step 7: Acknowledged Alert not found in My Alerts", "FAILURE");
	Thread.sleep(5000);
	
	//Step 8:Verifying the reaction type as create incident
	String reactionTypeText=snW.getAttributeByXpath("MyAlerts_ReactionType_Xpath","value");
	System.out.println(reactionTypeText);
	if(reactionTypeText.equalsIgnoreCase("Create Incident"))
		Reporter.reportStep("Step 8: Reaction Type is Create Incident", "SUCCESS");
	else
		Reporter.reportStep("Step 8: Reaction Type is other than Create Incident", "Failure");
	
	//Step 9: Clicking on Run Reaction button
	if(snW.clickByXpath("RunReaction_Xpath"))
		Reporter.reportStep("Step 9: Run Reaction Button clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 9: Run Reaction Button not clicked", "FAILURE");
	Thread.sleep(2000);
	
	//Step 10: Verifying the assignment group
	String incidentAssignmentGroup=snW.getAttributeByXpath("MyAlerts_AssignmentGroup_Xpath", "value");
    if(incidentAssignmentGroup.equalsIgnoreCase("Punch Group 2.1.1"))
		Reporter.reportStep("Step 9: Incident Assignment Group is Punch Group 2.1.1", "SUCCESS");
	else
		Reporter.reportStep("Step 9: Incident Assignment Group is other than Punch Group 2.1.1", "FAILURE");

	if(!snW.enterByXpath("MyAlerts_Caller_Xpath", Caller))
		Reporter.reportStep("Step 9A: Caller field not filled sucessfully", "FAILURE");
	Thread.sleep(4000);
	String incidentNumber=snW.getAttributeById("MyAlerts_IncidentNumber_Id", "value");
	System.out.println(incidentNumber);
	
	// Clicking on update button
	if(!snW.clickById("MyAlerts_UpdateButton_Id"))
		Reporter.reportStep("Step 9A: Update button could not be clicked", "FAILURE");
	
	snW.switchToDefault();
	
	//Step 10: Loging out 
	if (snW.clickByXpath("Logout_Xpath"))
		Reporter.reportStep("Step 10: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 10: The Log out could not be clicked.", "FAILURE");
	
	//Step 11: Logging in as user
	if (snW.login(revUser, revPwd))
		Reporter.reportStep("Step 11: The login with username:"+ revUser + " is successful", "SUCCESS");
	else
		Reporter.reportStep("Step 11: The login with username:"+ revUser + " is not successful", "FAILURE");
	
	//Step 12: Selecting Incident from the menu
	Thread.sleep(2000);
	if (snW.selectMenuFromMainHeader("Self-Service", "Incidents"))
		Reporter.reportStep("Step 12: The Incidents - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 12: The Incidents - menu could not be selected","FAILURE");

	// Step 13 Find the incident that was created
	snW.switchToFrame("Frame_Main");
	if(!snW.enterAndChoose("Incidents_NumberSearch_Xpath", incidentNumber))
		Reporter.reportStep("Step 13: The Incidents number couldnt be inserted in the searchbox field","SUCCESS");
	
	if(snW.clickLink(incidentNumber, false))
		Reporter.reportStep("Step 13: Incident Found", "SUCCESS");
	else
		Reporter.reportStep("Step 13: Incident not Found", "FAILURE");
	
	if (snW.clickByXpath("Logout_Xpath"))
		Reporter.reportStep("Step 14: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 14: The Log out could not be clicked.", "FAILURE");

	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "Ven_GLOD_STRY0010165_TC02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010165_TC02");
		return arrayObject;
	}
}
