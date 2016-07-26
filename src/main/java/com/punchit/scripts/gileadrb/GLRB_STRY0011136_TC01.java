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

public class GLRB_STRY0011136_TC01 extends SuiteMethods{

	// Create Instance
				ServiceNowWrappers snW;

				@Test(dataProvider = "RB_STRY0011136_TC01",groups="Runbook")
				public void closeFailRunbook(String regUser, String regPwd,String regUser1, 
						String regPwd1, String Filter1,String Filter2,String FilterValue, 
						String Response,String Response2, String comments) throws COSVisitorException, InterruptedException {

					// Prerequisites
					snW = new ServiceNowWrappers(entityId);
	
					try{
						if (snW.launchApp(browserName, false))
						    Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
						   else
						    Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

				            //Step 2 Login As a Manager and
						if (snW.login(regUser, regPwd))
						    Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
						   else
						    Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

						Thread.sleep(2000);
						
						if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
						    Reporter.reportStep("Step 2: The All open runbooks - menu selected successfully","SUCCESS");
						   else
						    Reporter.reportStep("Step 2: The All open runbooks - menu could not be selected","FAILURE");
						
						 // Switch to the main frame
						   snW.switchToFrame("Frame_Main");
						   snW.Wait(2000);
						
						  //Add Filter to Get Runbook with Steps available.
					      snW.clickByXpath("Runbook_Asignedtome_Filter_Icon_Xpath");
					      Thread.sleep(3000);
					      snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath");
					      Thread.sleep(2000);
					    
					      snW.selectByVisibleTextByXpath("Runbook_AllOpen_Filter1_Xpath", Filter1);
				            Thread.sleep(2000);
				            if(!snW.selectByVisibleTextByXpath("Runbook_AllOpen_Filter1_Xpath", Filter2))
				             Reporter.reportStep("Step3: The Filter Input could not be selected", "FAILURE");
				            
				            Thread.sleep(2000);
				            snW.pressKey(Keys.TAB);
				           
				            if(!snW.selectByVisibleTextByXpath("Runbook_FilterValue1_Xpath",FilterValue))
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
				           if (snW.login(regUser1, regPwd1))
				            Reporter.reportStep("Step 5: The login with username:"+ regUser1 + " is successful", "SUCCESS");
				           else
				            Reporter.reportStep("Step 5: The login with username:"+ regUser1 + " is not successful", "FAILURE");
				           
				           Thread.sleep(3000);
						   
						   // Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
						   if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
						    Reporter.reportStep("Step 5: All open runbooks - menu selected successfully","SUCCESS");
						   else
						    Reporter.reportStep("Step 5: All open runbooks - menu could not be selected","FAILURE");

						   // Switch to the main frame
						   snW.switchToFrame("Frame_Main");
						 
						   snW.Wait(3000);
						   
						   //Step 3: Select the Runbook with multiple CIs as per reference data.
						   
						   snW.selectByVisibleTextByXpath("Runbook_AllOpenRB_Searchbox_Xpath", "Number");
						   snW.enterByXpath("Runbook_AllOpenRB_SearchValue_Xpath", RunbookNo);

						   //Click Enter
						   snW.pressKey(Keys.RETURN);
						   Thread.sleep(2000);

						   // Right CLick the record with multiple CIs
						   if(snW.rightClickByXpath("Runbook_AllOpenRB_FirstRecord_Xpath"))
							   Reporter.reportStep("Step6: Right click performed on the Runboook record", "SUCCESS");
						   else
							   Reporter.reportStep("Step6: Right click was not performed on the record", "FAILURE");
						   
						   if(snW.clickByXpath("Runbook_AllOpenRB_AssignToMe_Xpath"))
							   Reporter.reportStep("Step 6a: Assign To Me was successfull ", "SUCCESS");
						   else
							   Reporter.reportStep("Step 6a: Assign To Me could no be clicked successfully ", "FAILURE");
						   
						   //Step 4 Go to All Assigned to Me Menu and Select the Runbook and Take Runbook.
						   
						   if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
							    Reporter.reportStep("Step 7: Assigned_To_Me - menu selected successfully","SUCCESS");
							   else
							    Reporter.reportStep("Step 7: Assigned_To_Me - menu could not be selected","FAILURE");

							   // Switch to the main frame
							   snW.switchToFrame("Frame_Main");
							 
							   snW.Wait(3000);
							   
							   //Find the Runbook Number.
						   snW.selectByVisibleTextByXpath("Runbook_AllRunBooks_Filter_Search_Xpath", "Number");
						   snW.enterByXpath("Runbook_AllRunBooks_Filter_Search_Value_Xpath", RunbookNo);
						   snW.pressKey(Keys.RETURN);
						   
						   snW.Wait(3000);
						   
						   //Step4a: Right click the record and Take Runbook.
						   
							   if(snW.rightClickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
								    Reporter.reportStep("Step 8a: Right click on the Runbook is successful ","SUCCESS");
								   else
								   {   status = "Insufficient Data";
								    Reporter.reportStep("Step 8a: Right click on the Runbook could not be performed ","FAILURE");
								   }
						   //Step 4b: Click on Take Runbook.
							   if(snW.clickByXpath("RunBook_assignedtome_rightclicTakeRunbook_xpath"))
								   Reporter.reportStep("Step 8b: Take Runbook is successfully selected ","SUCCESS");
								   else
								   Reporter.reportStep("Step 8b: Take Runbook could not be selected","FAILURE");
							   
							   snW.Wait(3000);
							   
						   //Step 5: 
							   String step=snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
							   System.out.println("text is "+step);
							   
							   if(step.equalsIgnoreCase("Step"))
								   Reporter.reportStep("Step 9a: Runbook page with steps of selected Runbook is displayed successfully","SUCCESS");
								   else
								   Reporter.reportStep("Step 9a: Runbook page with steps of selected Runbook is  not displayed","FAILURE");
							  
							   if(snW.selectByVisibleTextByXpath("Runbook_AssignedToMe_Response_Xpath", Response))
							   { snW.Wait(2000);
								    Reporter.reportStep("Step 10: Step Response is Selected as Fail","SUCCESS");
							   }   else
							   {   Reporter.reportStep("Step 10: Step Response could not be selected as Fail","FAILURE");
							   }
							   snW.Wait(1000);
							   
							   snW.getDriver().switchTo().activeElement();
							   
							   //Step 6:Select the First CI and Click n Done.
							   if(snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Left_FirstValue_Xpath"))
								   Reporter.reportStep("Step 11: The CI is Selected", "SUCCESS");
							   else
								   Reporter.reportStep("Step 11: Runbook CI Selection window is not displayed or Runbook CI could not be selected", "FAILURE");
							   
							   if(!snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Select_Xpath"))
								    Reporter.reportStep("Step 11a: The CI was not selected successfully", "FAILURE");
							   
							   //Click Done.
							   if(snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Done_Xpath"))
							   Reporter.reportStep("Step 11a: The CI to fail the step was selected successfully", "SUCCESS");
							   else
								   Reporter.reportStep("Step 11a: Done button was not clicked successfully", "FAILURE");
						   
							   
							   //Click on Close Runbook and Fill in the Comments block.
							 
							   //Step 8: Close the Run book
							   if(snW.clickByXpath("Runbook_CloseRunbook_Xpath"))
							    Reporter.reportStep("Step 12: Close button is clicked successfully","SUCCESS");
							   else
							   Reporter.reportStep("Step 12: Runbook Could not be closed, Close button was not Clicked","FAILURE");
							         
							   //Step 8: Fill in the comments
							   snW.getDriver().switchTo().activeElement();
							   
							// enter the comments 
							   if(snW.enterById("RunBook_assigntome_comments_ID", comments))
							    Reporter.reportStep("Step 13: Comments field value is entered successfully","SUCCESS");
							   else
							    Reporter.reportStep("Step 13: Comments field value could not be entered","FAILURE");
							   
							   //Step 9: Click on Close
							   if(snW.clickByXpath("Runbook_AssignToMe_Close_Xpath"))
							    Reporter.reportStep("Step 14: Close clicked successfully, and Runbook is Closed","SUCCESS");
							   else
							    Reporter.reportStep("Step 14: Close could not be clicked","FAILURE");
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
				
				@DataProvider(name = "RB_STRY0011136_TC01")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011136_TC01");
					return arrayObject;
				}
	}