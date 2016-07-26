package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011054_TC01 extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011054_TC01",groups="Runbook")
	public void appProperties(String regUser, String regPwd) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
			
			snW.Wait(2000);
			// Step 2: In application navigator expand Runbook to select All open runbooks
			if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
				Reporter.reportStep("Step 2: The All open runbooks - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The All open runbooks - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			snW.Wait(3000);
			String supname = "Number";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname))
				Reporter.reportStep("Step 3: Runbook - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("Header_SearchBox_Xpath", "!=RB0000000"))
				Reporter.reportStep("Step 3: !=RB0000000 could not be entered in search box","FAILURE");
			snW.Wait(3000);
			
			// click on first open runbook
			String runbookno=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Step 3: The First Open Runbook is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: The First Open Runbook coult not  clicked ","FAILURE");
			
			// check the data for Assigned to field 
			String oldAssignedtodata=snW.getAttributeById("Runbook_allrunbook_Assignedto_id","value");
			
			if(!snW.clickByXpath("Runbook_AssignedTo_SearchIcon_Xpath"))
				Reporter.reportStep("Step 4: Assigned To search icon could not br clicked","FAILURE");
			
			snW.switchToSecondWindow();
			snW.Wait(1000);
			String supname1 = "Name";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname1))
				Reporter.reportStep("Step 4: Runbook - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("Header_SearchBox_Xpath", "!="+oldAssignedtodata))
				Reporter.reportStep("Step 4: !="+oldAssignedtodata+" could not be entered in search box","FAILURE");
			snW.Wait(3000);
			if(!snW.clickByXpath("First_Searched_Record_Xpath"))
				Reporter.reportStep("Step 4: First searched user could not be selected","FAILURE");
			
			snW.switchToPrimary();
			snW.switchToFrame("Frame_Main");
			snW.Wait(1000);
			
			String NewAssignment=snW.getAttributeById("Runbook_allrunbook_Assignedto_id","value");
			if(NewAssignment.contentEquals(oldAssignedtodata))
				Reporter.reportStep("Step 4: Another user could not be selected in Assigned to field","FAILURE");
			else
				Reporter.reportStep("Step 4: Another user is successfully selected in Assigned to field","SUCCESS");
			
			// click on Update button
			if(snW.clickById("Runbook_allrunbook_Update_id"))
				Reporter.reportStep("Step 5: Update button  is clicked successfully ","SUCCESS");
			else
				Reporter.reportStep("Step 5: Update button could not clicked ","FAILURE");
			
			// search for the updatd runbook
			//Search with name
			if(snW.enterByXpath("RunBook_Templates_Searchbox_xpath", runbookno))
				Reporter.reportStep("Step 6: Runbook number is entered successfully to be searched ","SUCCESS");
			else
				Reporter.reportStep("Step 6: Runbook number could not entered  to be searched ","FAILURE");
			// Press enter key
            snW.PresEnter();
            
            // check the first run book
            String runbookno1=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			if(!runbookno1.equalsIgnoreCase(runbookno))
				Reporter.reportStep("Step 7: Updated runbook could not be found ","FAILURE");
			
			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Step 7: Updated runbook is found and clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 7: Updated runbook could not be clicked ","FAILURE");
			
			// check for the updates 
			String UpdatedAssignedtodata=snW.getAttributeById("Runbook_allrunbook_Assignedto_id","value");
			if(UpdatedAssignedtodata.equalsIgnoreCase(NewAssignment))
				Reporter.reportStep("Step 8: Updated are saved successfully hence passed","SUCCESS");
			else
				Reporter.reportStep("Step 8: Updated are not saved hence failed ","FAILURE");
			
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
	
