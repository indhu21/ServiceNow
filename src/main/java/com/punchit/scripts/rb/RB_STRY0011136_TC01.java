package com.punchit.scripts.rb;

import java.io.IOException;


import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;
	
public class RB_STRY0011136_TC01 extends SuiteMethods{

	
				@Test(dataProvider = "RB_STRY0011136_TC01",groups="Runbook")
				public void closeFailRunbook(String regUser, String regPwd,String regUser1, 
						String regPwd1, String Filter1,String Filter2,String FilterValue, 
						String Response,String Response2, String comments,String FilterCondition1, String FilterValue1) throws COSVisitorException, InterruptedException {

	
					try{
						if (snW.launchApp(browserName, true))
						    Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
						   else
						    Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

				            //Step 2 Login As a Manager and
						if (snW.login(regUser1, regPwd1))
						    Reporter.reportStep("The login with username:"+ regUser1 + " is successful", "SUCCESS");
						   else
						    Reporter.reportStep("The login with username:"+ regUser1 + " is not successful", "FAILURE");

						Thread.sleep(2000);
						
						if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
						    Reporter.reportStep("The All open runbooks - menu selected successfully","SUCCESS");
						   else
						    Reporter.reportStep("The All open runbooks - menu could not be selected","FAILURE");
						
						 // Switch to the main frame
						   snW.switchToFrame("Frame_Main");
						   snW.Wait(2000);
						
						  //Add Filter to Get Runbook with Steps available.
					       snW.clickByXpath("Runbook_Asignedtome_Filter_Icon_Xpath");
					       Thread.sleep(3000);
					       snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath");
					       Thread.sleep(2000);
					    
					       if(getOptionsByXpath("List_FirstFilterTypeByselect3_Xpath").contains("Show Related Fields"))
					       snW.selectByVisibleTextByXpath("List_FirstFilterTypeByselect3_Xpath", "Show Related Fields");
					       
					       if(!snW.selectByVisibleTextByXpath("List_FirstFilterTypeByselect3_Xpath", Filter1))
					    	   Reporter.reportStep("The Filter "+Filter1+" could not be selected", "FAILURE");
					       
				           snW.Wait(5000);
				           
				           if(!snW.selectByVisibleTextByXpath("List_FirstFilterTypeByselect3_Xpath", Filter2))
				        	   Reporter.reportStep("The Filter "+Filter2+" could not be selected", "FAILURE");
				            
				            				           
				           if(!snW.selectByVisibleTextByXpath("List_FilterValueByselect3_Xpath",FilterValue))
				               Reporter.reportStep("The Filter "+FilterValue+" could not be selected ", "FAILURE");
				           Thread.sleep(2000);
				           
				           snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath");
					       Thread.sleep(2000);
					       
				           if(!snW.selectByVisibleTextByXpath("Runbook_Asignedtome_FilterCond1_Xpath",FilterCondition1))
				        	   Reporter.reportStep("Assignmnet group could not be selected in the filter condition ", "FAILURE");
				           Thread.sleep(1000);
				           snW.pressKey(Keys.TAB);
				           snW.Wait(1000);
				           snW.pressKey(Keys.TAB);
				           snW.Wait(2000);
				           
				           if(!snW.enterAndChoose("Runbook_AllRunBooks_Filter_Search_Value1_Xpath",FilterValue1))
				        	   Reporter.reportStep("Assignmnet group as TESM_GRP_GENERIC1 could not be selected ", "FAILURE");
				           Thread.sleep(2000); 
				           
				           snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath");
					       Thread.sleep(2000);
						
					       addFilterstoEnterValue("CIS_FirstFilterType5_Xpath", "Template", "CIS_FilterCondition5_Xpath", 
									"starts with", "CIS_FilterValueEnterText5_Xpath", "TESM_Template_Multiple_CI");
								
				           
				           if(snW.clickById("Runbook_AllopenRB_Runfilter_ID"))
				               Reporter.reportStep("Run button is clicked and filter is set sucessfully ", "SUCCESS");
				           else
				               Reporter.reportStep("Run button is not clicked ", "FAILURE");
				           Thread.sleep(2000);
				            
				           //Select the First Runbook with Single CI's
				           String RunbookNo = snW.getTextByXpath("Runbook_AllOpenRB_FirstRecord_Xpath");
				           System.out.println(RunbookNo);
				            
/*				           // go out of the frame
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

*/						   // Right CLick the record with multiple CIs
						   if(snW.rightClickByXpath("Runbook_AllOpenRB_FirstRecord_Xpath"))
							   Reporter.reportStep("Right click performed on the Runboook record", "SUCCESS");
						   else
						   {   status="INSUFFICIENT DATA";
							   Reporter.reportStep("Right click was not performed on the record", "FAILURE");
						   }
						   if(snW.clickByXpath("Runbook_AllOpenRB_AssignToMe_Xpath"))
							   Reporter.reportStep("Assign To Me was successfull ", "SUCCESS");
						   else
							   Reporter.reportStep("Assign To Me could no be clicked successfully ", "FAILURE");
						   
						   //Step 4 Go to All Assigned to Me Menu and Select the Runbook and Take Runbook.
						   
						   if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
							    Reporter.reportStep("Assigned_To_Me - menu selected successfully","SUCCESS");
							   else
							    Reporter.reportStep("Assigned_To_Me - menu could not be selected","FAILURE");

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
								    Reporter.reportStep("Right click on the Runbook is successful ","SUCCESS");
								   else
								   {   status = "Insufficient Data";
								    Reporter.reportStep("Right click on the Runbook could not be performed ","FAILURE");
								   }
						   //Step 4b: Click on Take Runbook.
							   if(snW.clickByXpath("RunBook_assignedtome_rightclicTakeRunbook_xpath"))
								   Reporter.reportStep("Take Runbook is successfully selected ","SUCCESS");
								   else
								   Reporter.reportStep("Take Runbook could not be selected","FAILURE");
							   
							   snW.Wait(3000);
							   
						   //Step 5: 
							   String step=snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
							   System.out.println("text is "+step);
							   
							   if(step.equalsIgnoreCase("Step"))
								   Reporter.reportStep("Runbook page with steps of selected Runbook is displayed successfully","SUCCESS");
								   else
								   Reporter.reportStep("Runbook page with steps of selected Runbook is  not displayed","FAILURE");
							  
							   if(snW.selectByVisibleTextByXpath("Runbook_AssignedToMe_Response_Xpath", Response))
							   { snW.Wait(2000);
								    Reporter.reportStep("Step Response is Selected as Fail","SUCCESS");
							   }   else
							   {    Reporter.reportStep("Step Response could not be selected as Fail","FAILURE");
							   }
							   snW.Wait(1000);
							   
							   snW.getDriver().switchTo().activeElement();
							   
							   //Step 6:Select the First CI and Click n Done.
							   if(snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Left_FirstValue_Xpath"))
								   Reporter.reportStep("The CI is Selected", "SUCCESS");
							   else
								   Reporter.reportStep("Runbook CI Selection window is not displayed or Runbook CI could not be selected", "FAILURE");
							   
							   if(!snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Select_Xpath"))
								    Reporter.reportStep("The CI was not selected successfully", "FAILURE");
							   
							   //Click Done.
							   if(snW.clickByXpath("Runbook_AssignToMe_Slushbucket_Done_Xpath"))
							       Reporter.reportStep("The CI to fail the step was selected successfully", "SUCCESS");
							   else
								   Reporter.reportStep("Done button was not clicked successfully", "FAILURE");
						   
							   
							   //Click on Close Runbook and Fill in the Comments block.
							 
							   //Step 8: Close the Run book
							   if(snW.clickByXpath("Runbook_CloseRunbook_Xpath"))
							    Reporter.reportStep("Close button is clicked successfully","SUCCESS");
							   else
							    Reporter.reportStep("Runbook Could not be closed, Close button was not Clicked","FAILURE");
							         
							   //Step 8: Fill in the comments
							   snW.getDriver().switchTo().activeElement();
							   
							// enter the comments 
							   if(snW.enterById("RunBook_assigntome_comments_ID", comments))
							    Reporter.reportStep("Comments field value is entered successfully","SUCCESS");
							   else
							    Reporter.reportStep("Comments field value could not be entered","FAILURE");
							   
							   //Step 9: Click on Close
							   if(snW.clickByXpath("Runbook_AssignToMe_Close_Xpath"))
							    Reporter.reportStep("Close clicked successfully, and Runbook is Closed","SUCCESS");
							   else
							    Reporter.reportStep("Close could not be clicked","FAILURE");
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