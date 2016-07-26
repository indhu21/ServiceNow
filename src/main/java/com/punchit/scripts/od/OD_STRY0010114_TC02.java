package com.punchit.scripts.od;

import java.io.IOException;



import org.apache.pdfbox.exceptions.COSVisitorException;

import org.openqa.selenium.Keys;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;



public class OD_STRY0010114_TC02 extends SuiteMethods{

	
	// Create Instance
	ServiceNowWrappers snW;
	
	@Test(dataProvider = "OD_STRY0010114_TC02",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd, String Filter1, String Filter2) throws COSVisitorException, IOException, InterruptedException {
	
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
					// go out of the frame
		            snW.switchToDefault();
		            
		           if(snW.selectMenu("Ops_Director","Data","Alerts"))
		          //  snW.selectMenu(menuHeader, menuSubHeader, menu)
						Reporter.reportStep("Step2: Alert Console was successfully selected", "SUCCESS");
					else
						Reporter.reportStep("Step2: Alert Console could not be selected", "FAILURE");
					
					//Switch to Main Frame
					snW.switchToFrame("Frame_Main");
					Thread.sleep(2000);// wait for the alerts page to load.
					
					//Check the Alert Console table for the given condition for the alers to exist.
				     //Add Filter to Get Runbook with Steps available.
					      snW.clickByXpath("Filter_Icon_Xpath");
					      
					      Thread.sleep(3000);
					      
					      snW.clickByXpath("Filter_AND_Xpath");
					      
					      Thread.sleep(2000);
					      
					      snW.selectByVisibleTextByXpath("Data_Alert_FilterAttribute_Xpath", Filter1);
					      Thread.sleep(1000);
					      
					      snW.selectByVisibleTextByXpath("Data_Alert_FilterAttribute_Xpath", Filter2);
					      snW.pressKey(Keys.TAB);
					      snW.pressKey(Keys.TAB);
					      snW.sendKey("Create Incident");
					      snW.pressKey(Keys.RETURN);
					      Thread.sleep(1000);
					      snW.clickByXpath("Filter_AND_Xpath");
					      
					      snW.selectByVisibleTextByXpath("Data_Alert_FilterAttribute2_Xpath", "State");
					      snW.pressKey(Keys.TAB);
					      snW.pressKey(Keys.TAB);
					      snW.selectByVisibleTextByXpath("Data_Alert_FilterValue2_Xpath", "In Progress");
					     
					      snW.clickByXpath("Runfilter_Xpath");
						     Thread.sleep(3000);
					     
						//Select the alert ticket and check for run reaction button.
						     String Alert_no = (snW.getTextByXpath("Select_alert"));
								if(Alert_no == "")
								{
									status="INSUFFICIENT DATA";
									Reporter.reportStep("Step 3a: No Alerts Present under Alert Console ","FAILURE");
								}
								//Step 3b: Right Click on the the first alert available.
								if(snW.clickByXpath("Select_alert"))
									Reporter.reportStep("Step 3b: Alert ticket--"+ Alert_no+ "--in Alert Console is selected", "SUCCESS");
								else
									Reporter.reportStep("Step 3b: Alert ticket--"+ Alert_no+ "--could not be selected", "FAILURE");
								
								Thread.sleep(3000);
								//Check for reaction button availability.
								if(snW.IsElementPresentByXpath("MyAlerts_RunReactionButton_Xpath"))
									Reporter.reportStep("Step 4: Run Reaction button is available", "FAILURE");
								else
									Reporter.reportStep("Step 4: Run Reaction button is not availble", "SUCCESS");
								
								
						     
		           
		            
		            
		            
								// go out of the frame
					            snW.switchToDefault();

		            
		            
		            
		            // Log out
		            if(!snW.clickByXpath("Logout_Xpath"))
		             Reporter.reportStep("The logout Failed", "FAILURE");
		    
		            status="PASS";
		
		}
					finally {
						// close the browser
						snW.quitBrowser();
					}
		}
					@DataProvider(name = "OD_STRY0010114_TC02")
					public Object[][] fetchData() throws IOException {
						Object[][] arrayObject = DataInputProvider.getSheet("OD_STRY0010114_TC02");
						return arrayObject;
					}
		}
		
