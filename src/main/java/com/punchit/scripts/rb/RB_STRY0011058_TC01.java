package com.punchit.scripts.rb;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011058_TC01 extends SuiteMethods{
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011058_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd,String name,String regUser1,String regPwd1) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			//			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");

			//			// Step 2: check Data menu
			if (snW.selectMenu("RunBook","RunBook_Definition", "Data"))
				Reporter.reportStep("The Data  menu is selected Successfully","SUCCESS");
			else
				Reporter.reportStep("The Data  menu could not be selected","FAILURE");
			//
			//			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//			// click on New button
			if(snW.clickById("RunBook_Data_NewButton_ID"))
				Reporter.reportStep("The New Button  is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("The New Button could  not be clicked ","FAILURE");

			//			
			// Check the active box 
			String status1=snW.getAttributeById("RunBook_Data_Activecheckbox_ID_forattribute", "checked");
			System.out.println(status1);
			if(!status1.equalsIgnoreCase("true"))
			{
				if(snW.clickById("RunBook_Data_Activecheckbox_ID1_forclick"))

					Reporter.reportStep("Active box is clicked successfully","SUCCESS");
				else
					Reporter.reportStep("Active box could not be clicked ","FAILURE");

			}
			// Enter the name 
			String time=snW.getCurrentTime();
			//		//	String name="test";
			String name1=name+time;
			System.out.println(name1);
			if(snW.enterById("RunBook_Data_Name_ID",name1))
				Reporter.reportStep("The Name is entered and active button is checked successfully","SUCCESS");
			else
				Reporter.reportStep("The Name could not  be entered ","FAILURE");

			
			// click on submit button
			if(snW.clickById("RunBook_Data_SubmitButton_ID"))
				Reporter.reportStep("Submit button is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Submit button could not be clicked ","FAILURE");
			//  
			snW.switchToDefault();
			
			// click on log out
			
			if(snW.clickByXpath("Logout_Xpath"))
			{changeUrlForIM();
				Reporter.reportStep("Log out  is clicked successfully","SUCCESS");}
			else
				Reporter.reportStep("Log out could not be clicked ","FAILURE");

			// Step 8: Log in to application
			if (snW.login(regUser1, regPwd1))
				Reporter.reportStep("The login with username:"+ regUser1 + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser1 + " is not successful", "FAILURE");
			
			// step 9 :check if Templates link is available
			if (snW.selectMenu("RunBook","RunBook_Definition", "Templates"))
				Reporter.reportStep("The Templates  - menu selected successfully ","SUCCESS");
			else
				Reporter.reportStep("The Templates  - menu is not  displayed ","FAILURE");	
						
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			// step 10: click on new Button
			
			if(snW.clickById("Runbook_Templates_newbutton_id"))
				Reporter.reportStep("New Button is clicked successully ","SUCCESS");
			else
				Reporter.reportStep("New Button could not be clicked","FAILURE");	
            
			Thread.sleep(2000);
		//	/html/body/div[2]/form/span[1]/span/div[5]/div[2]/div[1]/div[2]/div[2]/select
			// check for the type
			if(snW.enterAndChoose("Runbook_Templates_new_TYPE_entervalue_xpath", name1))
		//	if(snW.selectByVisibleTextByXpath("Runbook_Templates_new_TYPE_entervalue_xpath", name1))
				Reporter.reportStep("Template type created in above steps found hence passed ","SUCCESS");
			else
				Reporter.reportStep("Template type created in above steps could not be found hence failed","FAILURE");	
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
	@DataProvider(name = "RB_STRY0011058_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011058_TC01");
		return arrayObject;
	}
}




