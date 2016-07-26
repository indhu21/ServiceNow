package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010703_TC01 extends SuiteMethods {
	// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "GLOD_STRY0010703_TC01",groups="OpsDirector")
		public void AcknowledgeAlerts(String regUser, String regPwd) throws Exception {

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

				Thread.sleep(3000);
				// Step 2: In application navigator expand OpsDirector/Ops_Consoles to select Alert Console
				if (snW.selectMenu("Ops_Director","Ops_Consoles", "ALERT_CONSOLE"))
					Reporter.reportStep("The Alert Console - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("The Alert Console - menu could not be selected","FAILURE");

				// Switch to the main frame
				snW.switchToFrame("Frame_Main");
				if(!clickLink("New_Alerts"))
					Reporter.reportStep("The New Alerts link in the alert console is not clicked or not found", "FAILURE");
				
				String AlertProfile1 = null;

				AlertProfile1=snW.getTextByXpath("AlertConsole_FirstNewAlert_Xpath");
				if(AlertProfile1.equalsIgnoreCase(""))
				{
					status = "Insufficient Data";
					Reporter.reportStep("There is no records to display for new Alerts","FAILURE");
				}

				if(snW.rightClickByXpath("AlertConsole_FirstNewAlert_Xpath"))
					Reporter.reportStep("Right Click on New Alert is successful ","SUCCESS");
				else
				{
					//status = "INSUFFICIENT DATA";
					Reporter.reportStep("Right Click on New Alert could not be performed","FAILURE");
				}

				// step 4:  Acknowledge the Alert
				if(snW.clickByXpath("ALERT_Acknowledge_Xpath"))
					Reporter.reportStep("Alert is Acknowledged successfully ","SUCCESS");
				else
					Reporter.reportStep("Alert could not be  Acknowledged ","FAILURE");

				snW.refresh();

				// Step 5: In application navigator expand OpsDirector/Ops_Consoles to select My Alerts
				if (snW.selectMenu("Ops_Consoles", "My_Alerts"))
					Reporter.reportStep("The My Alerts - menu selected successfully","SUCCESS");
				else
					//status="Insufficient Data";
					Reporter.reportStep("The My Alerts - menu could not be selected","FAILURE");

				snW.switchToFrame("Frame_Main");

				// Step 6 : Search for Acknowledged Alert in My Alerts
				FindAckAlert(AlertProfile1);

				snW.refresh();

				// Step 7 : In application navigator expand OpsDirector/Ops_Consoles to select Alert Console
				if (snW.selectMenu("Ops_Director","Ops_Consoles", "ALERT_CONSOLE"))
					Reporter.reportStep("The Alert Console - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("The Alert Console - menu could not be selected","FAILURE");

				// Switch to the main frame
				snW.switchToFrame("Frame_Main");

				// Step 8:Acknowledge Two alerts
				String AlertProfile2=null;;

				AlertProfile2=snW.getTextByXpath("AlertConsole_FirstNewAlert_Xpath");
				if(AlertProfile2.equalsIgnoreCase(""))
				{
					status = "Insufficient Data";	

					Reporter.reportStep("First Alert Left Box could not be selected ","FAILURE");      
				}
				if(!snW.clickByXpath("AlertConsole_FirstLeftBox_xpath"))
					Reporter.reportStep("First Alert Left Box could not be selected ","FAILURE");

				String AlertProfile3=null;

				AlertProfile3=snW.getTextByXpath("AlertConsole_SecondNewAlert_Xpath");
				if(AlertProfile3.equalsIgnoreCase(""))
				{
					status = "Insufficient Data";	
					Reporter.reportStep("Second Alert Left Box could not be selected ","FAILURE");

				}

				if(snW.clickByXpath("AlertConsole_SecondLeftBox_xpath"))
					Reporter.reportStep("FIrst and second Alert Left Box is Selected successfully ","SUCCESS");
				else
					Reporter.reportStep("Second Alert Left Box could not be selected ","FAILURE");

				// Step 9:Acknowledge two Alerts
				if(snW.SelectDropown_byXpath("AlertConsole_ActionsonSelectedrows_xpath"))
					Reporter.reportStep("Acknowledge is selected successfully from Action on Selected Rows Drop Down","SUCCESS");
				else
					Reporter.reportStep("Acknowledge could not be selected from Action on Selected Rows Drop Down","FAILURE");

				snW.refresh();

				// Step 10 : In application navigator expand OpsDirector/Ops_Consoles to select Alert Console
				if (snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
					Reporter.reportStep("The My Alerts - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("The My Alerts - menu could not be selected","FAILURE");
				// Switch to the main frame
				snW.switchToFrame("Frame_Main");

				// Search for Second Acknowledged alerts in My Alerts
				FindAckAlert(AlertProfile2);

				snW.refresh();

				// Step 11 : In application navigator expand OpsDirector/Ops_Consoles to select Alert Console
				if (snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
					Reporter.reportStep("The My Alerts - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("The My Alerts - menu could not be selected","FAILURE");
				// Switch to the main frame
				snW.switchToFrame("Frame_Main");

				FindAckAlert(AlertProfile3);


				status = "PASS";
			} finally {
				// close the browser
				snW.quitBrowser();
			}

		}


		@DataProvider(name = "GLOD_STRY0010703_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010703_TC01");
			return arrayObject;
		}
		/**Author : Aman 
		 * Search for the Acknowledged Alert
		 */
		public  void FindAckAlert(String Alertno) throws Exception  
		{
			try
			{
				boolean a=find_alertprofile(Alertno);
				if(!a)
				{
					((JavascriptExecutor) snW.getDriver()).executeScript("scrollTo(3000,2000)"); 
					String sLastrecord=snW.getDriver().findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td[1]/div[1]/table/tbody/tr[2]/td/span/span[2]/table/tbody/tr/td/table/tbody/tr/td/div/div/span/div/div/div/table/tbody/tr/td[2]/span[1]/div/span/span[2]")).getText();
					System.out.println("Last row " +sLastrecord);
					int iLastrecord=Integer.parseInt(sLastrecord);
					int iPage=iLastrecord/20;
					System.out.println(iPage);
					int iPagecount=iPage+1;
					System.out.println(iPagecount);
					int j=0;
					while(j<iPagecount)
					{
						((JavascriptExecutor) snW.getDriver()).executeScript("scrollTo(3000,2000)"); 
						snW.getDriver().findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td[1]/div[1]/table/tbody/tr[2]/td/span/span[2]/table/tbody/tr/td/table/tbody/tr/td/div/div/span/div/div/div/table/tbody/tr/td[2]/span[1]/button[3]")).click();   // click on next button
						boolean b=find_alertprofile(Alertno);;
						j++;
						if(b)
						{
							Reporter.reportStep(" Acknowledged Alert "+ Alertno + " is found","SUCCESS");
							break;
						}
						if(j==iPagecount)
						{
							
							Reporter.reportStep("Acknowledged alert " + Alertno + " could not be Found - hence Failed","FAILURE");	
						}
					}
				}
			}
			catch(Exception e)
			{
				Reporter.reportStep("Acknowledged alert " + Alertno + " could not be Found - hence Failed","FAILURE");
			}
		}
		
		public  boolean find_alertprofile(String Alertno)
		{ 
			try {
				snW.getDriver().findElementByLinkText(Alertno).click();
			Thread.sleep(2000);
			Reporter.reportStep(" Acknowledged Alert "+ Alertno + " is found","SUCCESS");
			}
			catch(Exception e)
			{
				return false;
			}
			return true;
			}
				
			
		


		
	

}
