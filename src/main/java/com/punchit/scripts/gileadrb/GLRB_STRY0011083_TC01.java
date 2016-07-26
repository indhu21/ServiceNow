package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011083_TC01 extends SuiteMethods {
	ServiceNowWrappers snW;

	@Test(dataProvider = "RB_STRY0011083_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd,String name,String username, String pwd2
			) throws COSVisitorException, InterruptedException {

		// Prerequisites
		snW = new ServiceNowWrappers(entityId);

		try {

			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
			//			
			// Step 2: Select Schedules
			if (snW.selectMenu("RunBook","RunBook_Definition", "New_Active_Window"))
				Reporter.reportStep("Step 2: The New Active Window - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The New Active Window - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
										
			//step 3: enter name
			String new_name=name+snW.getCurrentTime(); 
		    if(snW.enterById("Runbook_NewActiveWindow_Name_id", new_name))
		    	Reporter.reportStep("Step 3: Name is enterd successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: Name could not be entered","FAILURE");
           
		    // step 4 : click on submit button
		    
		    if(snW.clickById("RunBook_Data_SubmitButton_ID"))
		    	Reporter.reportStep("Step 4: Submit button is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 4: Submit button could not be clicked ","FAILURE");
           
			snW.switchToDefault();
			
		    // log out 	
		    if(snW.clickByXpath("Logout_Xpath"))
		    	Reporter.reportStep("Step 5: Log out  is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: Log out could not be performed ","FAILURE");
           
		    // log in with user 2
		    if (snW.login(username, pwd2))
				Reporter.reportStep("Step 6: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 6: The login with username:"+ regUser + " is not successful", "FAILURE");
		    
		    if (snW.selectMenu("RunBook","RunBook_Definition", "Schedules"))
				Reporter.reportStep("Step 7: The Schedules - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 7: The Schedules - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
			//click on the first schedule 
			String Scheduleno=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			System.out.println("Run book is " +Scheduleno);
			
			
			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Step 8: Schedule number " + Scheduleno + " is clicked successfully","SUCCESS");
			else
			{   status = "Insufficient Data";
				Reporter.reportStep("Step 8: Schedule number could not be clicked ","FAILURE");
			}
			if(!snW.selectByVisibleTextByXpath("Runbook_Schedule_Frequency_Xpath", "Hourly"))
				Reporter.reportStep("Frequency could not be  selected","FAILURE");
			
			// click on button to open second window 
			if(!snW.clickById("Runbook_Schedule_activewindow_lookup_id"))
				Reporter.reportStep("Step 9: Active Window Search icon could not be clicked ","FAILURE");
				
						
			snW.switchToSecondWindow();
			snW.switchToDefault();
			
			snW.enterByXpath("Rumbook_Schedule_activewindow_secondwindow_search_xpath", new_name);
			snW.PresEnter();
			Thread.sleep(4000);
    		String a=snW.getTextByXpath("Rumbook_Schedule_activewindow_secondwindow_firstrow_xpath");
            System.out.println(a);
            if(a.equalsIgnoreCase(new_name))
            	Reporter.reportStep("Step 9: Active Window created in the above steps is available in the list hence passed","SUCCESS");
			else
				Reporter.reportStep("Step 9: Active Window created in the above steps is not  available in the list","FAILURE");
				
            snW.switchToPrimary();		
            if(!snW.clickByXpath("Logout_Xpath"))
            	Reporter.reportStep("The logout Failed", "FAILURE");


            status = "PASS";
            
			}
		finally{
			// close the browser
			snW.quitBrowser();		
		}
	}
	@DataProvider(name = "RB_STRY0011083_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011083_TC01");
		return arrayObject;
	}
}


