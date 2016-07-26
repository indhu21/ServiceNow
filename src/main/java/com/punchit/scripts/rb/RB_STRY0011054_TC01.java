package com.punchit.scripts.rb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011054_TC01  extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011054_TC01",groups="Runbook")
	public void appProperties(String regUser, String regPwd) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
			
			snW.Wait(2000);
			// Step 2: In application navigator expand Runbook to select All open runbooks
			if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
				Reporter.reportStep("The All open runbooks - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The All open runbooks - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			snW.Wait(3000);
			String supname = "Number";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname))
				Reporter.reportStep("Runbook - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("Header_SearchBox_Xpath", "!=RB0000000"))
				Reporter.reportStep("!=RB0000000 could not be entered in search box","FAILURE");
			snW.Wait(3000);
			
			// click on first open runbook
			String runbookno=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("The First Open Runbook is clicked successfully and assigned to field is displayed","SUCCESS");
			else
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("The First Open Runbook coult not  clicked ","FAILURE");
			}
			// check the data for Assigned to field 
			String oldAssignedtodata=snW.getAttributeById("Runbook_allrunbook_Assignedto_id","value");
			
			if(!snW.clickByXpath("Runbook_AssignedTo_SearchIcon_Xpath"))
				Reporter.reportStep(" Assigned To search icon could not be clicked","FAILURE");
			
			snW.switchToSecondWindow();
			snW.Wait(1000);
			String supname1 = "Name";
			if(snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname1))
				Reporter.reportStep("Search icon is selected and list of user is available","SUCCESS");
			else
				Reporter.reportStep(" Runbook - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("Header_SearchBox_Xpath", "!="+oldAssignedtodata))
				Reporter.reportStep("!="+oldAssignedtodata+" could not be entered in search box","FAILURE");
			snW.Wait(3000);
			if(!snW.clickByXpath("First_Searched_Record_Xpath"))
				Reporter.reportStep("First searched user could not be selected","FAILURE");
			
			snW.switchToPrimary();
			snW.switchToFrame("Frame_Main");
			snW.Wait(1000);
			
			String NewAssignment=snW.getAttributeById("Runbook_allrunbook_Assignedto_id","value");
			if(NewAssignment.contentEquals(oldAssignedtodata))
				Reporter.reportStep("Another user could not be selected in Assigned to field","FAILURE");
			else
				Reporter.reportStep("Another user is successfully selected in Assigned to field","SUCCESS");
			
			// click on Update button
			if(snW.clickById("Runbook_allrunbook_Update_id"))
				Reporter.reportStep("Update button  is clicked successfully ","SUCCESS");
			else
				Reporter.reportStep("Update button could not clicked ","FAILURE");
			
			// search for the updatd runbook
			//Search with name
			if(snW.enterByXpath("RunBook_Templates_Searchbox_xpath", runbookno))
				Reporter.reportStep("Runbook number is entered successfully to be searched ","SUCCESS");
			else
				Reporter.reportStep("Runbook number could not entered  to be searched ","FAILURE");
			// Press enter key
            snW.PresEnter();
            
            // check the first run book
            String runbookno1=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			if(!runbookno1.equalsIgnoreCase(runbookno))
				Reporter.reportStep("Updated runbook could not be found ","FAILURE");
			
			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Updated runbook is found and clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Updated runbook could not be clicked ","FAILURE");
			
			// check for the updates 
			String UpdatedAssignedtodata=snW.getAttributeById("Runbook_allrunbook_Assignedto_id","value");
			if(UpdatedAssignedtodata.equalsIgnoreCase(NewAssignment))
				Reporter.reportStep("Updated are saved successfully hence passed","SUCCESS");
			else
				Reporter.reportStep("Updated are not saved hence failed ","FAILURE");
			
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
		@DataProvider(name = "RB_STRY0011054_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011054_TC01");
			return arrayObject;
		}
}
	