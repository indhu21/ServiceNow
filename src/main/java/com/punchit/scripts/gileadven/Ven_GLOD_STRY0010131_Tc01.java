package com.punchit.scripts.gileadven;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0010131_Tc01 extends SuiteMethods {
	
	//Test Case 130
		// Create Instance
			ServiceNowWrappers snW;
			
			@Test(dataProvider = "Ven_GLOD_STRY0010131_Tc01",groups="OpsDirector")
			public void appProperties(String regUser, String regPwd, String CloseCode) throws COSVisitorException, IOException, InterruptedException {
				
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

					// Step 2: In application navigator expand Ops Consoles and select My Alerts.
					if (snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
						Reporter.reportStep("My Alerts menu selected successfully","SUCCESS");
					else
						Reporter.reportStep("My Alerts menu could not be selected","FAILURE");
					
					
					//Wait for the Alerts to load
					snW.Wait(3000);
					// Switch to the main frame
					snW.switchToFrame("Frame_Main");
					
					//Step 3a: Select the first alert profile to right click on it.
					String My_Alert_no = (snW.getTextByXpath("Myalert_FirstAlert_xpath"));
					if(My_Alert_no == "")
					{
						status = "INSUFFICIENT DATA";
						Reporter.reportStep("No data available under My Alerts, ","FAILURE");
					
					}
					//Step 3b: Right Click on the the first alert available.
					if(snW.rightClickByXpath("Myalert_FirstAlert_xpath"))
						Reporter.reportStep("Right Click was performed on the alert ticket--"+ My_Alert_no+ "--in My alerts", "SUCCESS");
					else
						Reporter.reportStep("Right Click was not performed on the alert ticket--"+ My_Alert_no+ "--in My alerts", "FAILURE");
					
					//Switch to active element to Link to Parent.
					Thread.sleep(1000);
					snW.getDriver().switchTo().activeElement();
						
				   //Step 4: Click on the Magnification Symbol to display the alert tickets to make a parent ticket.
					if(snW.clickByXpath("Link_to_parent_Xpath"))
						Reporter.reportStep("'Link to parent' was Successfully clicked", "SUCCESS");
					else
						Reporter.reportStep("'Link to parent' could not clicked", "FAILURE");
					
					
					//Switch to active element to Link to Parent.
					Thread.sleep(2000);
					snW.getDriver().switchTo().activeElement();
                    Thread.sleep(3000);
					
					//Enter an alert number to link to parent
					if(snW.enterByXpath("MyAlerts_Linktoparent_entry_Xpath", "ALT"))
					{
					Thread.sleep(1000);
					snW.pressKey(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					snW.pressKey(Keys.TAB);
					Reporter.reportStep("Parent alert was selected sucessfully", "SUCCESS");
					}
					else
					Reporter.reportStep("Parent alert was not selected successfully", "FAILURE");
					
					String Parent_Alert_no= snW.getAttributeByXpath("MyAlerts_Linktoparent_entry_Xpath", "value");
					//System.out.println(Parent_Alert_no);
					snW.clickByXpath("Ok_Button_Xpath");	
					
					
					Thread.sleep(2000);
					//Step 6: Check the Child alert is not available in My Alerts menu.
			       snW.getDriver().findElementByLinkText("My Alerts").click(); 
			       Thread.sleep(2000);
			       snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
			    	snW.enterAndChoose("Alert_Num_Filter_Xpath", My_Alert_no);
			    	
			        if(snW.IsElementNotPresentByXpath("Paralt_xpath"))
			        	Reporter.reportStep("The Child alert no--"+My_Alert_no+"--is not displayed under My Alerts as expected", "SUCCESS");	
			        else
			        	Reporter.reportStep("The Child alert no--"+My_Alert_no+"--is displayed under My Alerts", "FAILURE");
			        
			    
			        
			        // Step 6a GO to Alerts under data.
			        if(snW.selectMenu("Ops_Director","Data", "Alerts"))
			        	Reporter.reportStep("The Alerts menu was selected sucessfully", "SUCCESS");
			        else
			        	Reporter.reportStep("The Alerts menu was not selected sucessfully", "FAILURE");
					
			        //Switch to main frame.
			        Thread.sleep(3000);
			        snW.switchToFrame("Frame_Main");
			        
			        //Select the Alert Number.
			        if(snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number"))
						Reporter.reportStep("Number is selected as dropdown option","SUCCESS");
					else
						Reporter.reportStep("Number is not selected as dropdown option","FAILURE");
			        
			        if(snW.enterAndChoose("Alert_Num_Filter_Xpath",Parent_Alert_no ))
						Reporter.reportStep("The Parent Alert No is entered successfully","SUCCESS");
					else
						Reporter.reportStep("The Parent Alert No Could not be entered","FAILURE");
			        
			        Thread.sleep(3000);
			        
			        if(snW.clickByXpath("Paralt_xpath"))
						Reporter.reportStep("Parent Alert No--"+Parent_Alert_no+"-- is selected", "SUCCESS");
					else
						Reporter.reportStep("Parent alert Could no be selected", "FAILURE");
				
				    Thread.sleep(3000);

				    //Step 7 Verify the child alerts under the child alerts table.
				   
				    //enter number as the search Criteria.
			    	snW.selectByVisibleTextByXpath("Alerts_ChildAlert_rellist_Search_Xpath", "Number");
			    	snW.enterAndChoose("Alerts_ChildAlert_rellist_SearchValue_Xpath", My_Alert_no);
			  
			        Thread.sleep(2000);
			   
			     if(snW.VerifyByLink(My_Alert_no, true))
			    	   Reporter.reportStep("Child Alert no --"+My_Alert_no+"-- is present under Parent alert", "SUCCESS");
			       else
			    	   Reporter.reportStep("Child Alert no --"+My_Alert_no+"-- is not present under Parent alert", "FAILURE");
			   

				    
				    //Step 8: Close the Parent alert
				    if(snW.selectMenu("Ops_Director","Data", "Alerts"))
			        	Reporter.reportStep("The Alerts menu was selected sucessfully", "SUCCESS");
			        else
			        	Reporter.reportStep("The Alerts menu was not selected sucessfully", "FAILURE");
					
				  //Switch to main frame.
			        Thread.sleep(3000);
			        snW.switchToFrame("Frame_Main");
				     
			        Thread.sleep(1000);
				    //Select the Alert Number.
			        if(snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number"))
						Reporter.reportStep("Number is selected as dropdown option","SUCCESS");
					else
						Reporter.reportStep("Number is not selected as dropdown option","FAILURE");
			        
			        if(snW.enterAndChoose("Alert_Num_Filter_Xpath",Parent_Alert_no ))
						Reporter.reportStep("The Parent alert is selected successfully","SUCCESS");
					else
						Reporter.reportStep("The Parent Alert  could not be selected","FAILURE");
			        
			        Thread.sleep(3000);  
				
			        //Step9: Right click on the alert number and select close as.
			        if(snW.rightClickByXpath("Paralt_xpath"))
						Reporter.reportStep("Right Click was performed on the alert ticket--"+ Parent_Alert_no+ "--in alerts", "SUCCESS");
					else
						Reporter.reportStep("Right Click was not performed on the alert ticket--"+ Parent_Alert_no+ "--in alerts", "FAILURE");
	 
			        //Step 9a: Select Close as
			        if(snW.clickByXpath("Close_xpath"))
			        	Reporter.reportStep("Right Click was performed and close is Selected on the alert ticket--"+ Parent_Alert_no+ "--in alerts", "SUCCESS");
					else
						Reporter.reportStep("Right Click was not performed and close on the alert ticket--"+ Parent_Alert_no+ "--in alerts not performed", "FAILURE");
	 
			        //Step 9b: click on close as other
			        if(snW.clickByXpath("Closeas_other_xpath"))
			        	Reporter.reportStep("Close as other was performed", "SUCCESS");
					else
						Reporter.reportStep("Close as other was not performed", "FAILURE");
			        
			        //Switch to active element.
			        Thread.sleep(1000);
			        
			        //Enter the input text.
			      
	 
			        if(snW.enterById("Close_value_ID", "PUNCH IT"))
			        Reporter.reportStep("Text was sucessfully entered", "SUCCESS");
					else
						Reporter.reportStep("Text entry was not possible", "FAILURE");
			        
			        
			        if(snW.clickByXpath("Ok_Button_Xpath"))
			           Reporter.reportStep("Close as other sucessfully Performed", "SUCCESS");
					else
						Reporter.reportStep("Close as other was not successful", "FAILURE");
	 
			       Thread.sleep(3000);
			        snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
	                snW.enterAndChoose("Alert_Num_Filter_Xpath",Parent_Alert_no );
			        
			        
			        //Step 10 Open the Paretn alert and check the State.
			        if(snW.clickByXpath("Paralt_xpath"))
			        	Reporter.reportStep("Parent alert displayed", "SUCCESS");
					else
						Reporter.reportStep("Parent alert not displayed", "FAILURE");
			        
			        //Step 10a: check the state is closed
			       String parent_state = snW.getFirstSelectedValueByXpath("//*[@id='sys_readonly.x_tori2_opd_alert.state']");
			       System.out.println(parent_state);
			       if(parent_state.equalsIgnoreCase("Closed"))
			        	Reporter.reportStep("Parent alert state is closed", "SUCCESS");
					else
						Reporter.reportStep("Parent alert state is not closed", "FAILURE");
			       
				//Step 11: Check child alert state is Closed.
			        snW.selectMenu("Ops_Director","Data", "Alerts");
			      //Switch to main frame.
			        Thread.sleep(3000);
			        snW.switchToFrame("Frame_Main");
			        
			        snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
			        snW.enterAndChoose("Alert_Num_Filter_Xpath",My_Alert_no);
			        
			        Thread.sleep(2000);

			        if(snW.clickByXpath("Paralt_xpath"))
			        	Reporter.reportStep("Child  alert displayed", "SUCCESS");
					else
						Reporter.reportStep("Child alert not displayed", "FAILURE");
			        
		         //Check the child alert state.
			       String Child_state = snW.getFirstSelectedValueByXpath("//*[@id='sys_readonly.x_tori2_opd_alert.state']");
			       System.out.println(Child_state);
			      if(Child_state.equalsIgnoreCase("Closed"))
			        	Reporter.reportStep("Child alert state is closed", "SUCCESS");
					else
						Reporter.reportStep("Child alert state is not closed", "FAILURE");
				
				
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

				@DataProvider(name = "Ven_GLOD_STRY0010131_Tc01")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010131_Tc01");
					return arrayObject;
				
				}
	}

