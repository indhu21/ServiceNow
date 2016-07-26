package com.punchit.scripts.od;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_Stry0011284_Tc01 extends SuiteMethods{
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0011284_Tc01",groups="OpsDirector")
	public void alertCorrelation(String regUser, String regPwd, String correlatedProfileName, String owningGroup, String incidentAssignmentGroup, String shortDescription) throws COSVisitorException,
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
	
	if (snW.selectMenu("Ops_Director","Registration_Menu", "Correlated_Profile"))
		Reporter.reportStep("Step 2: The Correlated Profile - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 2: The Correlated Profile - menu could not be selected","FAILURE");
	
	// Switch to the main frame
		snW.switchToFrame("Frame_Main");
		
		if(snW.enterById("CorrelatedProfile_Name_Id", correlatedProfileName))
			Reporter.reportStep("Step 3: Name given to the correlated profile", "SUCCESS");
		else
			Reporter.reportStep("Step 3: Name not given to the correlated profile", "FAILURE");
		
		if(snW.enterById("CorrelatedProfile_OwningGroup_Id", owningGroup))
			Reporter.reportStep("Step 4: Owning group given to the correlated profile", "SUCCESS");
		else
			Reporter.reportStep("Step 4: Owning group not given to the correlated profile", "FAILURE");
		
		if(snW.enterById("CorrelatedProfile_IncidentAssignmentGroup_Id", incidentAssignmentGroup))
			Reporter.reportStep("Step 5: Incident Assignment group given to the correlated profile", "SUCCESS");
		else
			Reporter.reportStep("Step 5: Incident Assignment group not given to the correlated profile", "FAILURE");
		
		if(snW.selectByVisibleTextById("CorrelatedProfile_CorrelatedAlertSeverity_Id", "Clear"))
			Reporter.reportStep("Step 6: Clear selected for Severity", "SUCCESS");
		else
			Reporter.reportStep("Step 6: Clear not selected for Severity", "FAILURE");
		
		if(snW.selectByVisibleTextById("CorrelatedProfile_ImpactToService_Id", "Outage"))
			Reporter.reportStep("Step 7: Outage selected for Impact", "SUCCESS");
		else
			Reporter.reportStep("Step 7: Outage not selected for Impact", "FAILURE");
		
		if(snW.enterById("CorrelatedProfile_ShortDescription_Id", shortDescription))
			Reporter.reportStep("Step 8: Short Description given to the correlated profile", "SUCCESS");
		else
			Reporter.reportStep("Step 8: Short Description not given to the correlated profile", "FAILURE");
		
		if(snW.clickById("CorrelatedProfile_SubmitButton_Id"))
			Reporter.reportStep("Step 9: Submit Button clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("Step 9: Submit Button not clicked", "FAILURE");
		
//		List<String> option=snW.verifyListContents("CorrelatedProfile_GroupBy_Id");
//		for(int i=0;i<option.size();i++)
//		{
//		if(option.get(i).toString().equalsIgnoreCase("Location"))
//		{
//			String message="Location is available in the Dropdown";
//		}
//		if(option.get(i).toString().equalsIgnoreCase("CI"))
//	//		|| "CI" || "Application" || "Business Service"
//		}
		
		if(snW.selectByVisibleTextById("CorrelatedProfile_GroupBy_Id", "CI"))
			Reporter.reportStep("Step 12: CI selected for Group By", "SUCCESS");
		else
			Reporter.reportStep("Step 12: CI not selected for Group By", "FAILURE");
		/*
		if(snW.selectByVisibleTextById("CorrelatedProfile_StartConditionGrouping_Id", "Any"))
			Reporter.reportStep("Step 10: Any selected for Start Condition grouping", "SUCCESS");
		else
			Reporter.reportStep("Step 10: Any not selected for Start Condition grouping", "FAILURE");
		
		if(snW.selectByVisibleTextById("CorrelatedProfile_StopConditionGrouping_Id", "None"))
			Reporter.reportStep("Step 11: None selected for Stop Condition grouping", "SUCCESS");
		else
			Reporter.reportStep("Step 11: None not selected for Stop Condition grouping", "FAILURE");
		
		
		
		if(snW.enterById("CorrelatedProfile_MaxActDurationHours_Id", "01"))
			Reporter.reportStep("Step 13: Hours set to 1", "SUCCESS");
		else
			Reporter.reportStep("Step 13: Hours not set to 1", "FAILURE");
		
		if(snW.selectByVisibleTextById("CorrelatedProfile_ReactionType_Id", "Create Incident"))
			Reporter.reportStep("Step 14: Reaction Type selected as Create Incident", "SUCCESS");
		else
			Reporter.reportStep("Step 14: Reaction Type not selected as Create Incident", "FAILURE");
*/
		// go out of the frame
		snW.switchToDefault();

		// Log out
		if(snW.clickByXpath("Logout_Xpath"))
			Reporter.reportStep("Step 17: The Log out is clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("Step 17: The logout Failed", "FAILURE");		


		status = "PASS";


}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "OD_Stry0011284_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_Stry0011284_Tc01");
		return arrayObject;
	}
}


