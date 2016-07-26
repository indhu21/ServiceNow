
package com.punchit.scripts.rb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011063_TC01 extends SuiteMethods {
	ServiceNowWrappers snW;

	@Test(dataProvider = "RB_STRY0011063_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd,String comments
			) throws COSVisitorException {

		// Prerequisites
		snW = new ServiceNowWrappers(entityId);

		try {

			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
			snW.Wait(3000);			
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
				Reporter.reportStep("The Assigned To Me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Assigned To Me - menu could not be selected","FAILURE");
			//
			//			// Switch to the main frame
			
			snW.switchToFrame("Frame_Main");
			String Runbookno = null;
			//			
			Runbookno=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			System.out.println("Run book is " +Runbookno);
			//				

			//			// Right click 
			if(snW.rightClickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Right click on the Runbook is successful ","SUCCESS");
			else
			{   status = "INSUFFICIENT DATA";
			Reporter.reportStep("Right click on the Runbook could not be performed ","FAILURE");
			}
			//			
			//			
			//			//Take run book
			if(snW.clickByXpath("RunBook_assignedtome_rightclicTakeRunbook_xpath"))
				Reporter.reportStep("Take Runbook is successfully selected ","SUCCESS");
			else
				Reporter.reportStep("Take Runbook could not be selected","FAILURE");

			//			// check if run book steps are displayed
			String step1 = null;

			step1=snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
			System.out.println("text is "+step1);
			//				
			//			
			//			
			if(step1.equalsIgnoreCase("step"))
				Reporter.reportStep("Runbook page with steps of selected Runbook is displayed successfully","SUCCESS");
			else
				Reporter.reportStep("Runbook page with steps of selected Runbook is  not displayed","FAILURE");
			//			
			//			// click on cancel button
			if(snW.clickByXpath("RunBook_assigntome_cancelbutton_xpath"))
				Reporter.reportStep("Cancel button clicked  successfully","SUCCESS");
			else
				Reporter.reportStep("Cancel button could not be clicked","FAILURE");
			//			
			//			//switch to active window 
			//			
			snW.getDriver().switchTo().activeElement();
			//				
			//			
			//			// enter the comments 
			if(snW.enterById("RunBook_assigntome_comments_ID", comments))
				Reporter.reportStep("Comments are entered   successfully","SUCCESS");
			else
				Reporter.reportStep("Comments could not be entered","FAILURE");
			//			
			//			// click on close
			if(snW.clickById("RunBook_assigntome_closebutton_id"))
				Reporter.reportStep("Close button is clicked  successfully","SUCCESS");
			else
				Reporter.reportStep("Close button could not be clicked","FAILURE");

			if (snW.selectMenu("RunBook", "All_runbook"))
				Reporter.reportStep("All  Runbook  - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("All Runbook - menu could not be selected","FAILURE");
			//		
			//
			//			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			//			
			//			//Search with name
			if(snW.enterByXpath("RunBook_Templates_Searchbox_xpath", Runbookno))
				Reporter.reportStep("Runbook number is entered successfully to be searched ","SUCCESS");
			else
				Reporter.reportStep("Runbook number to be searcheded could not be entered","FAILURE");
			//			// Press enter key
			snW.PresEnter();
			//
			//			// check the first run book
			String runbookno1=null;
			////			
			runbookno1=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			System.out.println("first book" +runbookno1);


			if(runbookno1.equalsIgnoreCase(Runbookno))
				Reporter.reportStep("Closed runbook is found in All RunBooks ","SUCCESS");
			else
				Reporter.reportStep("Closed runbook could not be  found in All RunBooks ","FAILURE");

			//			// check the status
			String status1=null;

//			status1=snW.getTextByXpath("Runbook_allrunbook_firststatus_xpath");
			snW.verifycolumnValue("State", "Closed Incomplete", "Runbook_TableHead_Xpath");
//			System.out.println("Status is "+ status1);



/*			if(status1.equalsIgnoreCase("closed incomplete"))
           if(snW.verifycolumnValue("State", "closed incomplete"))
				Reporter.reportStep("Status is " + State+ " which is expected  , Hence Passed" ,"SUCCESS");
			else
				Reporter.reportStep("Status is " +State+ " which is not as expected , Hence Failed" ,"FAILURE");
	*/		//			
			//			// go out of the frame
			snW.switchToDefault();
			//
			//			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");

			status = "PASS";

		}
		finally{
			// close the browser
			snW.quitBrowser();		
		}
	}
	@DataProvider(name = "RB_STRY0011063_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011063_TC01");
		return arrayObject;
	}
}



