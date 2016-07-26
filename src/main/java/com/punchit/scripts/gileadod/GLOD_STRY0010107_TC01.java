package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010107_TC01 extends SuiteMethods	 {

				
				@Test(dataProvider = "GLOD_STRY0010107_TC01",groups="OpsDirector")
				public void appProperties(String regUser, String regPwd, String Attribute, String threshold, String val1, String val2, String val3, String val4) throws COSVisitorException, IOException, InterruptedException {
						
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
						
						// Step 2: In application navigator expand OpsDirector/Administration to select Application Properties
						if (snW.selectMenu("Ops_Director", "Configurations", "Alert_Profiles"))
							Reporter.reportStep("The Alert Profiles menu selected successfully","SUCCESS");
						else
							Reporter.reportStep("The Alert Profiles menu could not be selected","FAILURE");
						
						
						//Wait for the Alerts to load
						snW.Wait(3000);
						// Switch to the main frame
						snW.switchToFrame("Frame_Main");
						
						
						//Step 3: Search the Number Filter and filter out only Alert Profiles starting with PROF.
						      //select Number as the drop-down option.
						if(snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number"))
							Reporter.reportStep("The Number is selected as dropdown option","SUCCESS");
						else
							Reporter.reportStep("The Number is not selected as dropdown option","FAILURE");
						
						if(snW.enterAndChoose("Alert_Num_Filter_Xpath", "*PROF"))
							Reporter.reportStep("The Alert Profiles starting with PROF filtered successfully","SUCCESS");
						else
							Reporter.reportStep("The Alert Profiles starting with PROF filtered could not be selected","FAILURE");
		                
						//Step 3.1 Press Enter
					    
						snW.pressKey(Keys.ENTER);// Click Enter for the search to complete
						
						
						//Wait for the Alerts tickets starting with PROF to load
						snW.Wait(3000);
						
						//Step 4: Note the Alert ticket Profile No.
						String AlertProf_no = (snW.getTextByXpath("Select_alert"));
						if(AlertProf_no != null)
							Reporter.reportStep("The alert profile Nsumber verified "+AlertProf_no, "SUCCESS");
						else
							Reporter.reportStep("The alert profile Number not verified "+AlertProf_no, "FAILURE");
						
						
						
						//Step 5: Click the alert Profile.
						if(snW.clickByXpath("Select_alert"))
							Reporter.reportStep("The alert Profile Number: "+ AlertProf_no+" is Displayed", "SUCCESS");
						else
							Reporter.reportStep("The alert Profile Number: "+ AlertProf_no+" is could not be displayed", "FAILURE");
						
						//Wait for the Alerts Profile to load 
						Thread.sleep(8000);
					
						//Step 6: Find alert configurations table and add new row element.
						WebElement abc = snW.getDriver().findElement(By.xpath(".//*[contains(@class, 'vt list_add list_edit_new_row')]"));
					
					
						if(abc != null){
						
							snW.doubleCick(abc);
							Thread.sleep(2000);
							snW.sendKey(Attribute);
							Thread.sleep(3000);
							snW.pressKey(Keys.TAB);
							Thread.sleep(3000);
							snW.sendKey(threshold);
							snW.pressKey(Keys.TAB);
							Thread.sleep(3000);
							snW.sendKey(val1);
							Thread.sleep(1000);
							snW.pressKey(Keys.TAB);
							snW.sendKey(val2);
							Thread.sleep(1000);
							snW.pressKey(Keys.TAB);
							snW.sendKey(val3);
							Thread.sleep(1000);
							snW.pressKey(Keys.TAB);
							snW.sendKey(val4);
							snW.pressKey(Keys.RETURN);
						    Thread.sleep(1000);
						    Reporter.reportStep("The Inserted a new Alert Configuration", "SUCCESS");
						}
						    						
						    else
							Reporter.reportStep("The insert new row not found", "Failure");				
					
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
					@DataProvider(name = "GLOD_STRY0010107_TC01")
					public Object[][] fetchData() throws IOException {
						Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010107_TC01");
						return arrayObject;
					}
		}
				
