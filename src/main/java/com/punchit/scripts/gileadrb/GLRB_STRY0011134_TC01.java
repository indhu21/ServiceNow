package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011134_TC01 extends SuiteMethods {

	
	// Create Instance
			ServiceNowWrappers snW;

			@Test(dataProvider = "RB_STRY0011134_TC01",groups="OpsDirector")
			public void appProperties(String regUser, String regPwd, String Response,String comments,String User2, String Pwd2,String filter1, String filter2,String filtervalue
					) throws COSVisitorException, InterruptedException {

				// Prerequisites
				snW = new ServiceNowWrappers(entityId);
     try{
    	 // Step 1:launch the browser
	       if (snW.launchApp(browserName, true))
	          Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
	       else
	          Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

	      //Step 2 Login As a Manager
	      if (snW.login(User2, Pwd2))
	          Reporter.reportStep("Step 1: The login with username:"+ User2 + " is successful", "SUCCESS");
	         else
	          Reporter.reportStep("Step 1: The login with username:"+ User2 + " is not successful", "FAILURE");

	      Thread.sleep(2000);
	      
	      // Open All runbook under Runbook Definition.
	      if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
	          Reporter.reportStep("Step 2: The All open runbooks - menu selected successfully","SUCCESS");
	         else
	          Reporter.reportStep("Step 2: The All open runbooks - menu could not be selected","FAILURE");
	      
	       // Switch to the main frame
	         snW.switchToFrame("Frame_Main");
	     
	      Thread.sleep(2000);
	        
	      //Step 3: Setting filer to check the CI type is single
	        //Add Filter to Get Runbook with Steps available.
	           if(!snW.clickByXpath("Runbook_Asignedtome_Filter_Icon_Xpath"))
	        	   Reporter.reportStep("Step3: The Filter icon could not be clicked", "FAILURE");
	           Thread.sleep(3000);
	           
	           if(!snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath"))
	        	   Reporter.reportStep("Step3: And button could not be clicked", "FAILURE");
	           Thread.sleep(2000);
	         
	           snW.selectByVisibleTextByXpath("Runbook_AllOpen_Filter1_Xpath", filter1);
	           Thread.sleep(2000);
	           if(!snW.selectByVisibleTextByXpath("Runbook_AllOpen_Filter1_Xpath", filter2))
	            Reporter.reportStep("Step3: The Filter Input could not be selected", "FAILURE");
	           
	           Thread.sleep(2000);
	           snW.pressKey(Keys.TAB);
	          
	           if(!snW.selectByVisibleTextByXpath("Runbook_FilterValue1_Xpath",filtervalue))
	              Reporter.reportStep("Step3: The Filter Condition could not be selected ", "FAILURE");
	           Thread.sleep(2000);
	           
	           if(snW.clickById("Runbook_AllopenRB_Runfilter_ID"))
	              Reporter.reportStep("Step3: Run button is clicked and filter is set sucessfully ", "SUCCESS");
	           else
	              Reporter.reportStep("Step3: Run button is not clicked ", "FAILURE");
	           Thread.sleep(2000);
	           
	           //Select the First Runbook with Single CI's
	           String RunbookNo = snW.getTextByXpath("Runbook_AllOpenRB_FirstRecord_Xpath");
	           System.out.println(RunbookNo);
	           
	           // go out of the frame
	             snW.switchToDefault();

	           // Step 4: Logging out as a manager
	           if(snW.clickByXpath("Logout_Xpath"))
	               Reporter.reportStep("Step4: Logged out sucessfully ", "SUCCESS");
		        else
	               Reporter.reportStep("Step4: The logout Failed", "FAILURE");
	             
	          // Step 5: Log in to application as operator
	      	   if (snW.login(regUser, regPwd))
	      	    Reporter.reportStep("Step 5: The login with username:"+ regUser + " is successful", "SUCCESS");
	      	   else
	      	    Reporter.reportStep("Step 5: The login with username:"+ regUser + " is not successful", "FAILURE");
	      	   
	      	   Thread.sleep(3000);
	      	 
	      	   //Step 6: All open Runbook menu selected
	      	   if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
		          Reporter.reportStep("Step 6: The All open runbooks - menu selected successfully","SUCCESS");
		         else
		          Reporter.reportStep("Step 6: The All open runbooks - menu could not be selected","FAILURE");
		      
		       // Switch to the main frame
		         snW.switchToFrame("Frame_Main");
		     
		       Thread.sleep(2000);
		   
		       //Step 7: Select the Runbook with Single CIs as per reference data.
		         
		         if(!snW.selectByVisibleTextByXpath("Runbook_AllOpenRB_Searchbox_Xpath", "Number"))
		        	 Reporter.reportStep("Step 7:Number could not be selected in searchbox","FAILURE");
		         if(!snW.enterByXpath("Runbook_AllOpenRB_SearchValue_Xpath", RunbookNo))
		             Reporter.reportStep("Step 7:Runbook number was not inserted in search value","FAILURE"); 

		         //Click Enter
		         snW.pressKey(Keys.RETURN);
		         Thread.sleep(2000);
		         
		         //Step 8: Right CLick the record with Single CIs
		         if(!snW.rightClickByXpath("Runbook_AllOpenRB_FirstRecord_Xpath"))
		             Reporter.reportStep("Step8: Right click was not performed on the record", "FAILURE");
		         
		         if(snW.clickByXpath("Runbook_AllOpenRB_AssignToMe_Xpath"))
		             Reporter.reportStep("Step 8: Assign To Me was successfull ", "SUCCESS");
		         else
		             Reporter.reportStep("Step 8: Assign To Me could no be clicked successfully ", "FAILURE");
		         
		         //Step 9:Go to All Assigned to Me Menu and Select the Runbook and Take Runbook.
		          if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
		              Reporter.reportStep("Step 9: Assigned_To_Me - menu selected successfully","SUCCESS");
		          else
		              Reporter.reportStep("Step 9: Assigned_To_Me - menu could not be selected","FAILURE");

		          // Switch to the main frame
		          snW.switchToFrame("Frame_Main");
		        
		          Thread.sleep(3000);
		          
		         //Step 10: Find the Runbook Number.
		         if(!snW.selectByVisibleTextByXpath("Runbook_AllRunBooks_Filter_Search_Xpath", "Number"))
		        	 Reporter.reportStep("Step 10:Number could not be selectd in serachbox ", "FAILURE");
		         if(!snW.enterByXpath("Runbook_AllRunBooks_Filter_Search_Value_Xpath", RunbookNo))
		        	 Reporter.reportStep("Step 10: Runbook number is not filled correctly in the seachvalue", "FAILURE");
		         snW.pressKey(Keys.RETURN);
		         
		         Thread.sleep(2000);
		         
		          //Step 11: Right click the record and Take Runbook.
		          if(!snW.rightClickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
		           {  
		        	  status = "Insufficient Data";
		            Reporter.reportStep("Step 11: Right click on the Runbook could not be performed ","FAILURE");
		           }
		         //Step 11: Click on Take Runbook.
		          if(snW.clickByXpath("RunBook_assignedtome_rightclicTakeRunbook_xpath"))
		           Reporter.reportStep("Step 11B: Take Runbook is successfully selected ","SUCCESS");
		           else
		           Reporter.reportStep("Step :11B Take Runbook could not be selected","FAILURE");
		          
		          Thread.sleep(3000);
		      
	            //Step 12: check if run book steps are displayed
	            String step=snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
	            System.out.println("text is "+step);
	         
	            if(step.equalsIgnoreCase("Step"))
	                 Reporter.reportStep("Step 12: Runbook page with steps of selected Runbook is displayed successfully","SUCCESS");
	            else
	                 Reporter.reportStep("Step 12: Runbook page with steps of selected Runbook is  not displayed","FAILURE");

	           //Step 13: selecting response as fail
	            if(snW.selectByVisibleTextByXpath("Runbook_AssignedToMe_Response_Xpath", Response))
	                 Reporter.reportStep("Step 13: Step Response is Selected as Fail","SUCCESS");
	             else
	                 Reporter.reportStep("Step 13: Step Response could not be selected as Fail","FAILURE");
	         
	           //Step 14: Close the Run book
	            if(snW.clickByXpath("Runbook_CloseRunbook_Xpath"))
	                 Reporter.reportStep("Step 14: Close Runbook  button is clicked successfully","SUCCESS");
	             else
	                 Reporter.reportStep("Step 14: Runbook Could not be closed, Close button was not Clicked","FAILURE");
	         
	           //Step 15: Fill in the comments
	              snW.getDriver().switchTo().activeElement();
	             if(!snW.clickByXpath("Runbook_AssignToMe_Close_Xpath"))
	               Reporter.reportStep("Step 15: Close button could not be clicked", "FAILURE");
	              Thread.sleep(1000);
	   
	              snW.alertAccept();// Handling the alert popup.
	   
	           // Step 15A: Check For Comments to be mandatory
	            String Mandatory= snW.getAttributeById("RunBook_assigntome_comments_ID", "class");
	          if("mandatoryField".equalsIgnoreCase(Mandatory))
		             Reporter.reportStep("Step 15A : Comments Field is Verified as Mandatory","SUCCESS");
	           else
		             Reporter.reportStep("Step 15A: Comments Field is not Mandatory as per the requirement","WARNING");
	   
	           //Step 16:enter the comments 
	            if(snW.enterById("RunBook_assigntome_comments_ID", comments))
	                 Reporter.reportStep("Step 16: Comments field value is entered successfully","SUCCESS");
	             else
	                  Reporter.reportStep("Step 16: Comments field value could not be entered","FAILURE");
	   
	           //Step 17: Click on Close
	            if(snW.clickByXpath("Runbook_AssignToMe_Close_Xpath"))
	                 Reporter.reportStep("Step 17: Close clicked successfully, and Runbook is Closed","SUCCESS");
	            else
	                 Reporter.reportStep("Step 17: Close could not be clicked","FAILURE");
	   
	           //Step 18: Click on All Run books
	            if (snW.selectMenuFromMainHeader("RunBook", "All_runbook"))
		            Reporter.reportStep("Step 18: All Run Books - menu selected successfully","SUCCESS");
		        else
		            Reporter.reportStep("Step 18: All Run Books - menu could not be selected","FAILURE");
	   
	           //Wait for the page to load
	             Thread.sleep(3000);
	   
	           // Switch to the main frame
	             snW.switchToFrame("Frame_Main");
	  
	           // Step 19: Check for the Details of the Closed Run Books.
	            if(!snW.selectByVisibleTextByXpath("Runbook_AllRunBooks_Filter_Search_Xpath", "Number"))
	                Reporter.reportStep("Step 19:Number could not be selectd in serachbox ", "FAILURE");
		         
	            if(!snW.enterByXpath("Runbook_AllRunBooks_Filter_Search_Value_Xpath", RunbookNo ))
	                Reporter.reportStep("Step 19: Runbook number is not filled correctly in the seachvalue", "FAILURE");
		         
	            snW.pressKey(Keys.RETURN);
	             Thread.sleep(3000);
	             
	          //Step 19a: Check for the Closure code and Status of the 
	            String Status = snW.getTextByXpath("Runbook_AllRunBooks_Status_Xpath");
	            String ClosureCode = snW.getTextByXpath("Runbook_AllRunBooks_ClosureCode_Xpath");
	           if("Closed Complete".equalsIgnoreCase(Status))
		           Reporter.reportStep("Step19A: Status of RunBook--" +RunbookNo+ "-- is --" +Status, "SUCCESS");
	           else
		           Reporter.reportStep("Step19A: Status of RunBook--" +RunbookNo+ "-- is --" +Status, "WARNING");
	   
	           if("Test Failure".equalsIgnoreCase(ClosureCode))
		           Reporter.reportStep("Step 19B: Closure Code of RunBook--" +RunbookNo+ "-- is --" +ClosureCode, "SUCCESS");
	           else
		           Reporter.reportStep("Step 19B:Closure Code of RunBook--" +RunbookNo+ "-- is --" +ClosureCode, "WARNING");
		   
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


			@DataProvider(name = "RB_STRY0011134_TC01")
			public Object[][] fetchData() throws IOException {
				Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011134_TC01");
				return arrayObject;
			}



}
