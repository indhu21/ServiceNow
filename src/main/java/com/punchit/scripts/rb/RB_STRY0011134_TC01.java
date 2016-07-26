package com.punchit.scripts.rb;

import java.io.IOException;



import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;
	
public class RB_STRY0011134_TC01 extends SuiteMethods {

	
	

			@Test(dataProvider = "RB_STRY0011134_TC01",groups="OpsDirector")
			public void appProperties(String regUser, String regPwd, String Response,String comments,String User2, String Pwd2,String filter1, String filter2,String filtervalue
					) throws COSVisitorException, InterruptedException {

	 try{
    	 // Step 1:launch the browser
	       if (snW.launchApp(browserName, true))
	          Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
	       else
	          Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

      	   if (snW.login(regUser, regPwd))
	      	    Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
	      	   else
	      	    Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
	      	   
	      	   Thread.sleep(3000);
	      	 
	      	   //Step 6: All open Runbook menu selected
	      	   if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
		          Reporter.reportStep("The All open runbooks - menu selected successfully","SUCCESS");
		         else
		          Reporter.reportStep("The All open runbooks - menu could not be selected","FAILURE");
		      
		       // Switch to the main frame
		         snW.switchToFrame("Frame_Main");
		     
		        // snW.switchToFrame("Frame_Main");
				 
					snW.Wait(3000);
					if(!snW.clickByXpath("Runbook_AllopenRunbook_Filtericon_xpath"))
						Reporter.reportStep("Filter Icon could not be clcked","FAILURE");
					Wait(2000);
		            
					 if(!snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath"))
		        	   Reporter.reportStep("And button could not be clicked", "FAILURE");
			           Thread.sleep(2000);
					
					if(!snW.selectByVisibleTextByXpath("Runbook_AllopenRunbook_SelectFilter_xpath","Assignment Group"))
						Reporter.reportStep("The Filter type could not be selected","FAILURE");
		            
					 if(!enterAndChoose("CIS_FilterValueEnterText3_Xpath", "TESM_GRP_GENERIC1"))
						Reporter.reportStep("Filter value could not be set","FAILURE");	

					 if(!snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath"))
			        	   Reporter.reportStep("And button could not be clicked", "FAILURE");
				           Thread.sleep(2000);
				           
				      addFilterstoEnterValue("CIS_FirstFilterType4_Xpath", "Template", "CIS_FilterCondition4_Xpath", 
				        		   "starts with", "CIS_FilterValueEnterText4_Xpath", "TESM");     
				           
					Wait(3000);
					  if(!snW.clickById("Runbook_AllopenRB_Runfilter_ID"))
						//Reporter.reportStep("Flter condition to select the Configuration item of class "+ value + " is set successfully ","SUCCESS");
						//else
						Reporter.reportStep("Run button to Run the Filter conditions could not be set","FAILURE");						

		        
		       Thread.sleep(3000);
		   
		       //Step 7: Select the Runbook with Single CIs as per reference data.
		         
//		         if(!snW.selectByVisibleTextByXpath("Runbook_AllOpenRB_Searchbox_Xpath", "Number"))
//		        	 Reporter.reportStep("Step 7:Number could not be selected in searchbox","FAILURE");
//		         if(!snW.enterByXpath("Runbook_AllOpenRB_SearchValue_Xpath", RunbookNo))
//		             Reporter.reportStep("Step 7:Runbook number was not inserted in search value","FAILURE"); 
//
//		         //Click Enter
//		         snW.pressKey(Keys.RETURN);
//		         Thread.sleep(2000);
		         
		         //Step 8: Right CLick the record with Single CIs
		        String RunbookNo=snW.getTextByXpath("Runbook_AllOpenRB_FirstRecord_Xpath");
		        System.out.println("Runbook number is "+RunbookNo );
		         if(!snW.rightClickByXpath("Runbook_AllOpenRB_FirstRecord_Xpath"))
					{   status = "INSUFFICIENT DATA";
						Reporter.reportStep("Right click on the Runbook could not be performed ","FAILURE");
					}
		         if(snW.clickByXpath("Runbook_AllOpenRB_AssignToMe_Xpath"))
		             Reporter.reportStep("Assign To Me was successfull ", "SUCCESS");
		         else
		             Reporter.reportStep("Assign To Me could no be clicked successfully ", "FAILURE");
		         
		         //Step 9:Go to All Assigned to Me Menu and Select the Runbook and Take Runbook.
		          if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
		              Reporter.reportStep("Assigned_To_Me - menu selected successfully","SUCCESS");
		          else
		              Reporter.reportStep("Assigned_To_Me - menu could not be selected","FAILURE");

		          // Switch to the main frame
		          snW.switchToFrame("Frame_Main");
		        
		          Thread.sleep(3000);
		          
		         //Step 10: Find the Runbook Number.
		         if(!snW.selectByVisibleTextByXpath("Runbook_AllRunBooks_Filter_Search_Xpath", "Number"))
		        	 Reporter.reportStep("Number could not be selectd in serachbox ", "FAILURE");
		         if(!snW.enterByXpath("Runbook_AllRunBooks_Filter_Search_Value_Xpath", RunbookNo))
		        	 Reporter.reportStep("Runbook number is not filled correctly in the seachvalue", "FAILURE");
		         snW.pressKey(Keys.RETURN);
		         
		         Thread.sleep(2000);
		         
		          //Step 11: Right click the record and Take Runbook.
		          if(!snW.rightClickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
		           {  
		        	  status = "Insufficient Data";
		            Reporter.reportStep("Right click on the Runbook could not be performed ","FAILURE");
		           }
		         //Step 11: Click on Take Runbook.
		          if(snW.clickByXpath("RunBook_assignedtome_rightclicTakeRunbook_xpath"))
		           Reporter.reportStep("Take Runbook is successfully selected ","SUCCESS");
		           else
		           Reporter.reportStep("Take Runbook could not be selected","FAILURE");
		          
		          Thread.sleep(3000);
		      
	            //Step 12: check if run book steps are displayed
	            String step=snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
	            System.out.println("text is "+step);
	         
	            if(step.equalsIgnoreCase("Step"))
	                 Reporter.reportStep("Runbook page with steps of selected Runbook is displayed successfully","SUCCESS");
	            else
	                 Reporter.reportStep("Runbook page with steps of selected Runbook is  not displayed","FAILURE");

	           //Step 13: selecting response as fail
	            if(snW.selectByVisibleTextByXpath("Runbook_AssignedToMe_Response_Xpath", Response))
	                 Reporter.reportStep("Step Response is Selected as Fail","SUCCESS");
	             else
	                 Reporter.reportStep("Step Response could not be selected as Fail","FAILURE");
	         
	           //Step 14: Close the Run book
	            if(snW.clickByXpath("Runbook_CloseRunbook_Xpath"))
	                 Reporter.reportStep("Close Runbook  button is clicked successfully","SUCCESS");
	             else
	                 Reporter.reportStep("Runbook Could not be closed, Close button was not Clicked","FAILURE");
	         
	           //Step 15: Fill in the comments
	              snW.getDriver().switchTo().activeElement();
	             if(!snW.clickByXpath("Runbook_AssignToMe_Close_Xpath"))
	               Reporter.reportStep("Close button could not be clicked", "FAILURE");
	              Thread.sleep(1000);
	   
	              snW.alertAccept();// Handling the alert popup.
	   
	           // Step 15A: Check For Comments to be mandatory
	            String Mandatory= snW.getAttributeById("RunBook_assigntome_comments_ID", "class");
	          if("mandatoryField".equalsIgnoreCase(Mandatory))
		             Reporter.reportStep("Comments Field is Verified as Mandatory","SUCCESS");
	           else
		             Reporter.reportStep("Comments Field is not Mandatory as per the requirement","WARNING");
	   
	           //Step 16:enter the comments 
	            if(snW.enterById("RunBook_assigntome_comments_ID", comments))
	                 Reporter.reportStep("Comments field value is entered successfully","SUCCESS");
	             else
	                  Reporter.reportStep("Comments field value could not be entered","FAILURE");
	   
	           //Step 17: Click on Close
	            if(snW.clickByXpath("Runbook_AssignToMe_Close_Xpath"))
	                 Reporter.reportStep("Close clicked successfully, and Runbook is Closed","SUCCESS");
	            else
	                 Reporter.reportStep("Close could not be clicked","FAILURE");
	   
	           //Step 18: Click on All Run books
	            if (snW.selectMenuFromMainHeader("RunBook", "All_runbook"))
		            Reporter.reportStep("All Run Books - menu selected successfully","SUCCESS");
		        else
		            Reporter.reportStep("All Run Books - menu could not be selected","FAILURE");
	   
	           //Wait for the page to load
	             Thread.sleep(3000);
	   
	           // Switch to the main frame
	             snW.switchToFrame("Frame_Main");
	  
	           // Step 19: Check for the Details of the Closed Run Books.
	            if(!snW.selectByVisibleTextByXpath("Runbook_AllRunBooks_Filter_Search_Xpath", "Number"))
	                Reporter.reportStep("Number could not be selectd in serachbox ", "FAILURE");
		         
	            if(!snW.enterByXpath("Runbook_AllRunBooks_Filter_Search_Value_Xpath", RunbookNo ))
	                Reporter.reportStep("Runbook number is not filled correctly in the seachvalue", "FAILURE");
		         
	            snW.pressKey(Keys.RETURN);
	             Thread.sleep(3000);
	             
	          //Step 19a: Check for the Closure code and Status of the 
	            String Status = snW.getTextByXpath("Runbook_AllRunBooks_Status_Xpath");
	            String ClosureCode = snW.getTextByXpath("Runbook_AllRunBooks_ClosureCode_Xpath");
	           if("Closed Complete".equalsIgnoreCase(Status))
		           Reporter.reportStep("Status of RunBook--" +RunbookNo+ "-- is --" +Status, "SUCCESS");
	           else
		           Reporter.reportStep("Status of RunBook--" +RunbookNo+ "-- is --" +Status, "WARNING");
	   
	           if("Test Failure".equalsIgnoreCase(ClosureCode))
		           Reporter.reportStep("Closure Code of RunBook--" +RunbookNo+ "-- is --" +ClosureCode, "SUCCESS");
	           else
		           Reporter.reportStep("Closure Code of RunBook--" +RunbookNo+ "-- is --" +ClosureCode, "WARNING");
		   
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
