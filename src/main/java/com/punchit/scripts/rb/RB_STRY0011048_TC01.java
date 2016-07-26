package com.punchit.scripts.rb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011048_TC01 extends SuiteMethods {
	ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011048_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd,String AssignedtomeFilter,String unassignedfilter,String Allopenrunbookfilter,String allrunbook) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			snW.login(regUser, regPwd);
/*				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
*/			snW.Wait(2000);
			
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
				Reporter.reportStep("Runbook app button is opened and the Assigned_To_Me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Assigned_To_Me - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			//step 3: get the filter condition
			String x=snW.getTextById("RunBook_Assignedtome_filter_id");
			//System.out.println("text is "+ x);
			if(x.equalsIgnoreCase(AssignedtomeFilter))
				Reporter.reportStep("Filter conditin are set as expected : "  + x ,"SUCCESS");
			else
				Reporter.reportStep("Filter conditions for Assigned to me are not set as Expected","FAILURE");
			  
			//step 4:In application navigator expand OpsDirector/Registration to select unassigned	
			if (snW.selectMenuFromMainHeader("RunBook", "Unassigned"))
			Reporter.reportStep("Unassigned - menu selected successfully","SUCCESS");
		    else
			Reporter.reportStep("Unassigned - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
			//step 5 : check the filter condition
			String x1=snW.getTextById("RunBook_Assignedtome_filter_id");
			//System.out.println(x1);
			if(x1.equalsIgnoreCase(unassignedfilter))
				Reporter.reportStep("Filter condition  for Unassigned are set as expected : "  + x1 ,"SUCCESS");
			else
				Reporter.reportStep("Filter condition  for Unassigned are not set as expected :" + x1,"FAILURE");
			//status="Pass";
			
			// step 6 : SelectAll_open_runbooks
			if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
		     Reporter.reportStep("All open Runbook  - menu selected successfully","SUCCESS");
		   else
     		Reporter.reportStep("All open Runbook - menu could not be selected","FAILURE");
				
			snW.switchToFrame("Frame_Main");
				
			//step 7 : check the filter condition for all open run books
			String x2=snW.getTextById("RunBook_Assignedtome_filter_id");
			System.out.println(x2);
			if(x2.equalsIgnoreCase(Allopenrunbookfilter))
			Reporter.reportStep("Filter condition  for All open Runbook are set as expected : "  + x2 ,"SUCCESS");
			else
			Reporter.reportStep("Filter condition  for All open Runbook are  not set as expected :" + x2,"FAILURE");
			
			// step 8: select all runbooks
			if (snW.selectMenuFromMainHeader("RunBook", "All_runbook"))
				Reporter.reportStep("All  Runbook  - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("All Runbook - menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");
			
			// step 9 : check the filter condition for all run book
			String x3=snW.getTextById("RunBook_Assignedtome_filter_id");
			System.out.println(x3);
			if(x3.equalsIgnoreCase(allrunbook))
			Reporter.reportStep("Filter condition  for All Runbook are set as expected : "  + x3 ,"SUCCESS");
			else
			Reporter.reportStep("Filter condition  for All Runbook are  not set as expected :" + x3,"FAILURE");
			
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");

				status = "PASS";

		}
		finally{
			// close the browser
		 snW.quitBrowser();		
		}
	}
		@DataProvider(name = "RB_STRY0011048_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011048_TC01");
			return arrayObject;
		}
}
	



