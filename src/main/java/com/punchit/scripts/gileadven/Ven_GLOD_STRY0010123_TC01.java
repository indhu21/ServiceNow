package com.punchit.scripts.gileadven;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0010123_TC01 extends SuiteMethods{
	// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "Ven_GLOD_STRY0010123_TC01",groups="OpsDirector")
		public void createCIScope(String regUser, String regPwd) throws COSVisitorException, InterruptedException
				 {

			// Pre-requisities
			snW = new ServiceNowWrappers(entityId);

			try {
				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Log in to application
				if (snW.login(regUser, regPwd))
					Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
				else
					Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");

				// Step 2: In application navigator expand OpsDirector/Registration to select CI Scope Registration
				if (snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
					Reporter.reportStep("The My Alerts - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("The My Alerts - menu could not be selected","FAILURE");

				snW.switchToFrame("Frame_Main"); // switch to main farame
				String AlertProfile1=snW.getTextByXpath("AlertConsole_FirstNewAlert_Xpath");
				System.out.print("Alert Profile is " + AlertProfile1 );
				if(AlertProfile1.equalsIgnoreCase(""))
				{
					status = "INSUFFICIENT DATA";
					Reporter.reportStep("There is no records available to be displayed My Alerts","FAILURE");
				}

				//step 3: Check if Related priority field is in view
				if(!snW.clickByXpath("MY_Alerts_UpdatePersonalizedList_xpath"))
					Reporter.reportStep("Update Personalized List icon could not  be clicked","FAILURE");

				snW.getDriver().switchTo().activeElement();
				Thread.sleep(5000);
				WebElement Search= snW.getDriver().findElement(By.id("slush_right"));
				List<WebElement> Search1 = Search.findElements(By.tagName("option"));
				System.out.println("Size is "+ Search1.size());
				int i;
				for(i=0;i<Search1.size();i++)
				{
					System.out.println(Search1.get(i).getText());
					if(Search1.get(i).getText().equalsIgnoreCase("Related Task.Priority"))
					{
						System.out.println(Search1.get(i).getText());
						break;
					}
				}

				if(!snW.clickById("UpdatePersonalizedList_window_OKButton_ID"))
					Reporter.reportStep("Update Personalized window could not be closed","FAILURE");

				if(i==Search1.size())
					Reporter.reportStep("Related_task.priority is not the part of view","FAILURE");

				//Step 4: to get the Priority in sorted 
				if(!snW.clickLink("MY_Alert_Priority_Col_link"))
					Reporter.reportStep("Priority link could not be selected","FAILURE");

				Thread.sleep(3000);

				// to pickup the profile which has Related task 
				//MY_ALERT_FirstRelatedTask_xpath
				String Relatedtask=snW.getTextByXpath("MY_ALERT_FirstRelatedTask_xpath");
				if(Relatedtask.equalsIgnoreCase(""))
				{
				snW.clickLink("MY_Alert_RelatedTask_Col_link");
				Thread.sleep(2000);
				String Relatedtask1=snW.getTextByXpath("MY_ALERT_FirstRelatedTask_xpath");
				if(Relatedtask1.equalsIgnoreCase(""))
				{
					status = "INSUFFICIENT DATA";
					Reporter.reportStep("Alerts with Related Task are not available in the list","FAILURE");
				}
				}
				
				//Step 5 : to check if priority is assigned 

				String za=snW.getTextByXpath("MY_ALERT_FirstPriority_xpath");
				System.out.print("Value is "+ za);
				if(za.equalsIgnoreCase(""))
					Reporter.reportStep("Priority Field is in Display but it does not contain the Priority","FAILURE");
				else
					Reporter.reportStep("Priority Field is in Display also it contain the Priority hence Passed","SUCCESS");

				status = "PASS";
				

			} finally {
				// close the browser
			snW.quitBrowser();
			}

		}


		@DataProvider(name = "Ven_GLOD_STRY0010123_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010123_TC01");
			return arrayObject;
		}
	}

