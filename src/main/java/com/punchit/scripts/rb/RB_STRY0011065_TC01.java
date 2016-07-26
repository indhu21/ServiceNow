package com.punchit.scripts.rb;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011065_TC01 extends SuiteMethods {
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011065_TC01",groups="Runbook")
	public void Schedule_Runbook(String regUser, String regPwd,String Frequency,String CIType,String Configuration,String Assignment, String SearchID ) throws COSVisitorException, IOException, InterruptedException {
		
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
			
			// step 2 :check if Templates link is available
			if (snW.selectMenu("RunBook","RunBook_Definition", "Templates"))
				Reporter.reportStep("The Templates  - menu selected successfully ","SUCCESS");
			else
				Reporter.reportStep("The Templates  - menu is not  displayed ","FAILURE");	
						
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			 
			snW.Wait(3000);
			if(!snW.clickByXpath("Runbook_Template_Filtericon_xpath"))
				Reporter.reportStep("Filter Icon could not be clcked","FAILURE");
			Wait(2000);
            
			if(!snW.selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath","Default assignment group"))
				Reporter.reportStep("The Filter type "+ "Class" + " could not be selected","FAILURE");
            
			try {
				new Actions(snW.getDriver()).sendKeys(Keys.TAB,Keys.TAB).build().perform(); // Move to the next element
				WebElement ele = snW.getDriver().switchTo().activeElement();
				ele.sendKeys("TESM_GRP_GENERIC1", Keys.ENTER);
			}
			catch(Exception e){
				Reporter.reportStep("Filter value could not be set","FAILURE");	
			}

			Wait(3000);
			if(!clickByXpath("CreateInc_ConfigurationItem_spyglass_Filter_Run_xpath"))
				//Reporter.reportStep("Flter condition to select the Configuration item of class "+ value + " is set successfully ","SUCCESS");
				//else
				Reporter.reportStep("Run button to Run the Filter conditions could not be set","FAILURE");						
			if(snW.clickByXpath("Runbook_Templates_Firsttemplate_Xpath"))
				Reporter.reportStep("Already existing template is clicked ","SUCCESS");
			else
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("Already existing template could not be clicked because of insufficient data ","FAILURE");	
			}
			//Step 4:Searching schedule navigation is present or no Runbook_Templates_AddButton_Xpath
			
			if(snW.clickByXpath("Runbook_Template_Schedule_xpath"))
			    Reporter.reportStep("Schedule list is present","SUCCESS");
			else
				Reporter.reportStep("Schedule list is not present ","FAILURE");	
			
			//Step 5:Click new button 
			if(snW.clickByXpath("Runbook_Schedule_AddButton_Xpath"))
				Reporter.reportStep("New button is clicked","SUCCESS");
			else
				Reporter.reportStep("New button could not be clicked ","FAILURE");
			
			//Step 6:validating Number and Template are read only
			String ScheduleNumber=snW.getAttributeByXpath("Runbook_Schedule_ScheduleNumber_Xpath", "value");
			System.out.println(ScheduleNumber);
			
			String[] ReadField = {"Runbook_Schedule_ScheduleNumber_Xpath","Runbook_Schedule_Template_Xpath"};
			String[] ReadLabel = {"Number", "Template"};
			snW.verifyDisabledFieldsByXpath(ReadField, ReadLabel);
		/*		Reporter.reportStep("Step 6:Schedule number and templates are readonly fields","SUCCESS");
			else
				Reporter.reportStep("Step 6:Schedule number and templates are not readonly fields","FAILURE");
		*/	
			//Step 7: Click Active checkbox and checking next action time and assignment group are mandatory
			if(snW.clickByXpath("Runbook_Schedule_Active_Xpath"))
				Reporter.reportStep("Active field is clicked","SUCCESS");
			else
				Reporter.reportStep("Active field could not be clicked","FAILURE");
			
			//checking next action time and Assignment group are mandatory field
			String[] ReadField2 = {"Runbook_Schedule_NextActionTimeMandatory_Xpath","Runbook_Schedule_AssignmentGroupMandatory_Xpath"};
			String[] ReadLabel2 = {"Next action time", "Assignment group"};
			snW.verifyMandatoryFields(ReadField2, ReadLabel2);
				
			//Step 8:Selecting Frequency as weekly and filling Assignment group
			if(snW.selectByVisibleTextByXpath("Runbook_Schedule_Frequency_Xpath", Frequency))
				Reporter.reportStep("Frequency as weekly is selected","SUCCESS");
			else
				Reporter.reportStep("Frequency as weekly could not selected","FAILURE");
			snW.Wait(2000);
			//Active window should be hidden
			if(snW.isExistByXpath("Runbook_Schedule_ActiveWindow_Xpath"))
				Reporter.reportStep("Active window is present","FAILURE");
			else
				Reporter.reportStep("Active window is not present","SUCCESS");
			
			//Step 9 Entering assignment group
			if(snW.enterAndChoose("Runbook_Schedule_AssignmentGroup_Xpath", Assignment))
				Reporter.reportStep("Assignment group is selected sucessfully","SUCCESS");
			else
				Reporter.reportStep("Assignment group could not be selected","FAILURE");
		
			//Step 10 Enter next action time
			Date date = new Date();
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
			   String formattedDate = sdf.format(date);
			   
			   Calendar c = Calendar.getInstance();
			   c.add(Calendar.DATE, 1);  // number of days to add
			   String nextday = sdf.format(c.getTime());
			if(snW.enterByXpathAndClick("Runbook_Schedule_NextActionTime_Xpath", nextday))
				Reporter.reportStep("Next action date and time is selected sucessfully","SUCCESS");
			else
				Reporter.reportStep("Next action date and time could not selected","FAILURE");
			
			
			//Step 11: Entering CI type as single
				if(snW.selectByVisibleTextByXpath("Runbook_Schedule_CIType_Xpath", CIType))
					Reporter.reportStep("CI Type as single is selected","SUCCESS");
				else
					Reporter.reportStep("CI Type as single is not selected","FAILURE");
		
		   //Step 12: Configuration item is exist and is mandatory
			//Configuration item is exist
			if(snW.isExistByXpath("Runbook_Schedule_ConfigurationItem_Xpath"))
				Reporter.reportStep("Configuration item is present","SUCCESS");
			else
			    Reporter.reportStep("Configuration item is not present","FAILURE");
				
			//Check Congiguration item are Mandatory
			String[] ReadField3 = {"Runbook_Schedule_ConfigurationMandatory_Xpath"};
			String[] ReadLabel3 = {"Configuration item"};
			snW.verifyMandatoryFields(ReadField3, ReadLabel3);
			
			//Selecting the CI from the list
			if(!snW.enterByXpathAndClick("Runbook_Schedule_Configuration_Xpath", Configuration))
			/*	Reporter.reportStep("Configuration item selected sucessfully","SUCCESS");
			else
				*/Reporter.reportStep("Configuration item could not be selected","FAILURE");
		
			Thread.sleep(2000);
			//Step13 click on submit
			if(snW.clickByXpath("Runbook_Schedule_Submit_Xpath"))
				Reporter.reportStep("Configuration item selected and Submit button is clicked sucessfully","SUCCESS");
			else
				
				Reporter.reportStep("Submit button could not be clicked","FAILURE");
			
			snW.Wait(2000);	
			//Step 14 Validating the added Schedule is displayed
		     if(snW.selectByVisibleTextByXpath("Runbook_Schedule_SearchBox_Xpath", SearchID))
		     {}
		     else
		      Reporter.reportStep("Number is not selected", "FAILURE");
		     
		     if(snW.enterByXpathAndClick("Runbook_Schedule_SearchValue_Xpath", ScheduleNumber))
		     {}
		     else
		      Reporter.reportStep("The created schedule was not entered in the search field", "FAILURE");
		     
		     snW.Wait(4000);
		     // Validate the Ticket Number
		       String ValidateID = snW.getTextByXpath("Runbook_Schedule_FirstNumber_Xpath");
		       System.out.println(ValidateID);
		       if(ValidateID.equalsIgnoreCase(ScheduleNumber))
		     Reporter.reportStep("Newly created schedule is saved successfully", "SUCCESS");
		    else
		     Reporter.reportStep("Newly created schedule could not be saved", "FAILURE");
		     
			snW.Wait(2000);
			// go out of the frame
            snW.switchToDefault();

         // Log out
            if(!snW.clickByXpath("Logout_Xpath"))
            	Reporter.reportStep("The logout Failed", "FAILURE");


            status="PASS";
		}
		finally{
			// close the browser
		snW.quitBrowser();		
		}
	}
		@DataProvider(name = "RB_STRY0011065_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011065_TC01");
			return arrayObject;
		}
}


