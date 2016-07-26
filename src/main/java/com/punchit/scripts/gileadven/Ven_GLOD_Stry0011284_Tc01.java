package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_Stry0011284_Tc01 extends SuiteMethods{
	
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_Stry0011284_Tc01",groups="OpsDirector")
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
		
	
		snW.Wait(5000);
		
		if(snW.selectByVisibleTextById("CorrelatedProfile_GroupBy_Id", "CI"))
			Reporter.reportStep("Step 10: CI selected for Group By", "SUCCESS");
		else
			Reporter.reportStep("Step 10: CI not selected for Group By", "FAILURE");
		
		//validating the required field is available
		String[] element = {"-- None --","Location","CI","Application","Business Service"};

		if(snW.verifyListContent("CorrelatedProfile_GroupBy_Xpath", element))
           Reporter.reportStep("Step 10a:All the elements :"+snW.convertStringArrayToString(element)+" do exists in group by list", "SUCCESS");
		  
		if(snW.selectByVisibleTextByXpath("CorrelatedProfile_StartConditionGrouping_Xpath", "Any"))
			Reporter.reportStep("Step 11: Any selected for Start Condition grouping", "SUCCESS");
		else
			Reporter.reportStep("Step 11: Any not selected for Start Condition grouping", "FAILURE");
		
		//Verify the elements present in the start condition listbox
		String[] StartCondElement = {"-- None --","Any","All"};

		if(snW.verifyListContent("CorrelatedProfile_StartConditionGrouping_Xpath", StartCondElement))
			Reporter.reportStep("Step 11a: All the elements :"+snW.convertStringArrayToString(element)+" do exists in start Condition list", "SUCCESS");
		  
		if(snW.selectByVisibleTextByXpath("CorrelatedProfile_StopConditionGrouping_Xpath", "-- None --"))
			Reporter.reportStep("Step 12: None selected for Stop Condition grouping", "SUCCESS");
		else
			Reporter.reportStep("Step 12: None not selected for Stop Condition grouping", "FAILURE");
		
		//Verify the elements present in the stop condition listbox
		String[] StopCondElement = {"-- None --","Any","All"};

		
		
		if(snW.verifyListContent("CorrelatedProfile_StopConditionGrouping_Xpath", StopCondElement))
		    Reporter.reportStep("Step 12a: All the elements :"+snW.convertStringArrayToString(element)+" do exists in stop Condition list", "SUCCESS");
		  	
		if(snW.enterById("CorrelatedProfile_MaxActDurationHours_Id", "01"))
			Reporter.reportStep("Step 13: Hours set to 1", "SUCCESS");
		else
			Reporter.reportStep("Step 13: Hours not set to 1", "FAILURE");
		
		
		// Clicking on reaction tab
		
		if(snW.selectByVisibleTextById("CorrelatedProfile_ReactionType_Id", "Create Incident"))
			Reporter.reportStep("Step 14: Reaction Type selected as Create Incident", "SUCCESS");
		else
			Reporter.reportStep("Step 14: Reaction Type not selected as Create Incident", "FAILURE");

		//Clicking on grouped alert tab
		
		
	  if(snW.clickByXpath("CorrelatonProfiles_ImpactedProfiles_Xpath"))
			Reporter.reportStep("Step 15: Impacted profiles edit button is clicked sucessful", "SUCCESS");
		else
			Reporter.reportStep("Step 15: Impacted profiles edit button could not be clicked", "FAILURE");
		
		if(snW.enterAndChoose("CorrelatonProfiles_ImpactedProfilesTextField_Xpath", "**"))
			Reporter.reportStep("Step 16: Impacted profiles selected sucessful", "SUCCESS");
		else
	        Reporter.reportStep("Step 16: Impacted profiles could not be selected", "FAILURE");
			
		if(!snW.clickByXpath("CorrelatonProfiles_ImpactedProfilesLockbutton_Xpath"))
			 Reporter.reportStep("Step 17: Impacted profiles lock button could not be selected", "FAILURE");
		
		//Impacted CIs
		if(snW.clickByXpath("CorrelatonProfiles_ImpactedCIs_Xpath"))
			Reporter.reportStep("Step 18: Impacted profiles edit button is clicked sucessful", "SUCCESS");
		else
			Reporter.reportStep("Step 18: Impacted profiles edit button could not be clicked", "FAILURE");
		
		if(snW.enterAndChoose("CorrelatonProfiles_ImpactedCIsTextField_Xpath", "**"))
			Reporter.reportStep("Step 19: Impacted CIs selected sucessful", "SUCCESS");
		else
	        Reporter.reportStep("Step 19: Impacted CIs could not be selected", "FAILURE");
			
		if(!snW.clickByXpath("CorrelatonProfiles_ImpactedCIsLockbuton_Xpath"))
			 Reporter.reportStep("Step 20: Impacted CIs lock button could not be selected", "FAILURE");
		
		//Impacted attributes
		if(snW.clickByXpath("CorrelatonProfiles_ImpactedAttributes_Xpath"))
			Reporter.reportStep("Step 21: Impacted attribute edit button is clicked sucessful", "SUCCESS");
		else
			Reporter.reportStep("Step 21: Impacted attribute edit button could not be clicked", "FAILURE");
		
		if(snW.enterAndChoose("CorrelatonProfiles_ImpactedAttributesTextField_Xpath", "**"))
			Reporter.reportStep("Step 22: Impacted profiles selected sucessful", "SUCCESS");
		else
	        Reporter.reportStep("Step 22: Impacted profiles could not be selected", "FAILURE");
			
		if(!snW.clickByXpath("CorrelatonProfiles_ImpactedAttributesLockbuton_Xpath"))
			 Reporter.reportStep("Step 23: Impacted profiles lock button could not be selected", "FAILURE");
		
		//creating a new row in start condition row
		WebElement Cond = snW.getDriver().findElement(By.xpath(".//*[contains(@class, 'vt list_add list_edit_new_row')]"));
	     
	     
	      if(Cond != null){
	      
	       snW.doubleCick(Cond);
	       Thread.sleep(2000);
	       snW.sendKey("Applications");
	       Thread.sleep(2000);
	       snW.pressKey(Keys.TAB);
	       Thread.sleep(3000);
	       snW.sendKey("Contains");
	       snW.pressKey(Keys.TAB);
	       Thread.sleep(3000);
	       snW.sendKey("2");
	       Thread.sleep(2000);
	       snW.pressKey(Keys.RETURN);
	          Thread.sleep(1000);
	          Reporter.reportStep("Step 23: Inserted a new Start Condition", "SUCCESS");
	      }
	                
	          else
	       Reporter.reportStep("Step 23: New row could not be added in start condition", "Failure");
		
		// Stop condition
		snW.scrollToElementByXpath("CorrelatonProfiles_StopConditionNewRow_Xpath");
		//creating a new row in start condition row
			WebElement StartCond = snW.getDriver().findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table[1]/tbody/tr/td/div/table/tbody/tr/td[3]"));
			if(StartCond != null){
			      
			snW.doubleCick(StartCond);
			Thread.sleep(2000);
			snW.sendKey("Alert Object");
			Thread.sleep(2000);
			snW.pressKey(Keys.TAB);
			Thread.sleep(2000);
			//snW.sendKey("Contains");
			snW.pressKey(Keys.TAB);
			//Thread.sleep(3000);
			//snW.sendKey("2");
			Thread.sleep(2000);
			snW.pressKey(Keys.RETURN);
			Thread.sleep(1000);
			     Reporter.reportStep("Step 24: Inserted a new row in Stop Condition", "SUCCESS");
			}
			  else
			       Reporter.reportStep("Step 24: New row could not be added in stop condition", "Failure");
				
		snW.scrollToElementByXpath("CorrelatonProfiles_GroupedCIs_Xpath");
		//creating a new row in group CIs
		WebElement GroupCIs = snW.getDriver().findElement(By.xpath("/html/body/div[2]/div[2]/div/div[3]/span/div[2]/div[4]/table[1]/tbody/tr/td/div/table/tbody/tr/td[3]"));
		if(GroupCIs != null){
		      
		snW.doubleCick(GroupCIs);
		Thread.sleep(2000);
		snW.sendKey("Connected by::Connects");
		Thread.sleep(2000);
		snW.pressKey(Keys.TAB);
		/*Thread.sleep(2000);
		snW.sendKey("Contains");
		snW.pressKey(Keys.TAB);
		Thread.sleep(3000);
		snW.sendKey("2");
		Thread.sleep(2000);
		*/
		snW.pressKey(Keys.RETURN);
		Thread.sleep(1000);
		     Reporter.reportStep("Step 25: Inserted a new row in Grouped CIs", "SUCCESS");
		}
		  else
		       Reporter.reportStep("Step 25: New row could not be added in grouped CIs", "Failure");
			
		
		
		
		
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
	@DataProvider(name = "Ven_GLOD_Stry0011284_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_Stry0011284_Tc01");
		return arrayObject;
	}
}



