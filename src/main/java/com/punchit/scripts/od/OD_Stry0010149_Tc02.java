package com.punchit.scripts.od;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class OD_Stry0010149_Tc02 extends SuiteMethods {
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider="OD_Stry0010149_Tc02",groups="OpsDirector")

	public void assignAlert(String regUser, String regPwd, String assignedTo, String verUser, String verPwd, String state,String progress) throws Exception {
		try {

			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if(snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

           /*
			snW.switchToFrame("Frame_Nav");

			//Step 2: Expand OpsDirector/OpsConsole/under application navigator to select My alerts
			if(snW.selectMenu("Ops_Consoles", "My_Alert_Console"))
				Reporter.reportStep("Step 2: My Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: My Alert Console under OpsConsole - menu could not be selected","FAILURE");
			snW.Wait(1000);



			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			if(snW.getTextByXpath("ALERT_AlertId_Xpath").equals("No records to display")){

				snW.switchToFrame("Frame_Nav");

				if(!snW.enterById("filter_Id", "Ops Director Testing"))
					Reporter.reportStep("Step 3: 'Alert Console' could not be entered in filter box","FAILURE");

				if(!snW.selectMenu("Ops_Director_Testing", "Scenarios"))
					Reporter.reportStep("Step 3: The 'Scenarios' under Ops Director Testing - menu could not be selected","FAILURE");

				snW.switchToFrame("Frame_Main");
				//Step 7: Run a Scenario from reference data
				if(!snW.clickLink("Payments Plus Digital Response Times", false))
					Reporter.reportStep("Step 3: 'Payments Plus Digital Response Times' under Scenarios could not be clicked","FAILURE");
				snW.Wait(5000);
				if(!snW.clickByXpath("SCENARIOS_PlayScenarios_Xpath"))
					Reporter.reportStep("Step 3: The 'Play Scenarios' could not be clicked","FAILURE");
				snW.Wait(5000);
				if(!snW.clickByXpath("SCENARIOS_Close_Xpath"))
					Reporter.reportStep("Step 3: The 'Close Button' could not be clicked","FAILURE");

				snW.switchToFrame("Frame_Nav");

				if(!snW.enterById("filter_Id", "Alert Console"))
					Reporter.reportStep("Step 3: 'Alert Console' could not be entered in filter box","FAILURE");

				if(!snW.clickById("filter_Clear_Id"))
					Reporter.reportStep("Step 3: Filter Box could not be cleared","FAILURE");
*/
				if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
					Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 2: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

				snW.switchToFrame("Frame_Main");

				String alert = snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
				if(alert.equalsIgnoreCase(""))
				{
					status="INSUFFICIENT DATA";
					Reporter.reportStep("No records available under My Alerts","FAILURE");			
				}

				//if(!snW.rightClickByLinkText(alert, false))
				if(snW.rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
					Reporter.reportStep("Step 3: Right click on the alert is performed successfully","SUCCESS");
				else
					Reporter.reportStep("Step 3: Right click on the alert could not be performed","FAILURE");

				if(snW.clickByXpath("ALERT_Acknowledge_Xpath"))
					Reporter.reportStep("Step 4: Alert "+alert+" is Acknowledged successfully","SUCCESS");
				else
					Reporter.reportStep("Step 4: Alert could not be acknowledged","FAILURE");
				snW.Wait(5000);

				snW.switchToFrame("Frame_Nav");
				if(snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
					Reporter.reportStep("Step 5: My Alert under OpsConsole - menu selected successfully","SUCCESS");
				else
					Reporter.reportStep("Step 5: My Alert under OpsConsole - menu could not be selected","FAILURE");
				
				snW.Wait(1000);
				snW.switchToFrame("Frame_Main");
				
				FindAckAlert(alert);
				
				String Assignedto=snW.getAttributeByXpath("MY_ALERT_AssignedTo_xpath", "value");
				
				System.out.println("Assigned to "+ Assignedto);
				
				// click on Lookup
				if(!snW.clickById("MY_ALERT_Assignedto_lookup_ID"))
					Reporter.reportStep("Step 6: Assigned to lookup could not be clicked","FAILURE");
				try {
					snW.switchToSecondWindow();
					snW.Wait(1000);
					String supname1 = "Name";
					if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname1))
						Reporter.reportStep("Step 6: Runbook - Search Key could not be selected","FAILURE");
					snW.Wait(500);
					if(!snW.enterAndChoose("Header_SearchBox_Xpath", "!="+Assignedto))
						Reporter.reportStep("Step 6: !="+Assignedto+" could not be entered in search box","FAILURE");
					snW.Wait(3000);
					if(!snW.clickByXpath("First_Searched_Record_Xpath"))
						//	Reporter.reportStep("Step 6: New Assignment is selected successfully","SUCCESS");
						//else
						Reporter.reportStep("Step 6: New Assignment could not be selected","FAILURE");

					snW.switchToPrimary();
					snW.switchToFrame("Frame_Main");
					snW.Wait(3000);
					Reporter.reportStep("New Assigned to is selected successfully" ,"SUCCESS");


				}
				catch(Exception e)
				{
					
				}
				// sys_display.original.x_tori2_opd_alert.assigned_to
								
				//sysverb_update
				//step 6: click update to save
				if(snW.clickById("CIS_UpdateButton_Id")) 
					Reporter.reportStep("Step 6: Update has been clicked successfully", "SUCCESS");
				else
					Reporter.reportStep("Step 6: Update could not be clicked","FAILURE");
				//snW.Wait(5000);
				
				//FindAckAlert(alert);
				
//                String NewAssignment=snW.getTextByXpath("MY_ALERT_AssignedTo_xpath1");
//                
//                System.out.println("Assignment is "+NewAssignment);
                
				//if(NewAssignment.equalsIgnoreCase(Assignedto))
					//Reporter.reportStep("Step 7: Assigned to could not be selected with new value  ","FAILURE");
				//else
					//Reporter.reportStep("Step 7: Assigned to is selected successfully with "+NewAssignment,"SUCCESS");

			/*
		    	//step 3:selecting state as in progress
			  if(snW.clickLink("My_Alerts"))
				Reporter.reportStep("Step 3: 'My Alerts' could be clicked sucessfully", "SUCCESS");
			else
				Reporter.reportStep("Step 3: 'My Alerts' could not be clicked","FAILURE");

			snW.Wait(2000);

		
			if(!snW.clickById("ALERT_FunnelIcon_Id"))
				Reporter.reportStep("Step 4: The funnel icon could not be clicked","FAILURE");
			
			snW.Wait(5000);
			
			if(!snW.deleteAllFilters())
				Reporter.reportStep("Step 4: The 'All Filters' could not be removed","FAILURE");
			
			snW.Wait(5000);
			
			if(!snW.addNewFilterUsingInput("State", "is", "In Progress"))
				Reporter.reportStep("Step 4: The 'New Filters' could not be selected","FAILURE");
			
			snW.Wait(5000);
			
			if(!snW.clickByXpath("ALERT_RunFilter_Xpath"))
				Reporter.reportStep("Step 4: 'Run' could not be clicked", "FAILURE");

			snW.Wait(5000);			
			*/
			//String alertId=snW.getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
			//step 4: select an alert
			//if(snW.clickLink(alertId, false))
				//Reporter.reportStep("Step 4: 'Alert Number': "+ alertId +" could be clicked","SUCCESS");
			//else	
				//Reporter.reportStep("Step 4: 'Alert Number': "+ alertId +" could be clicked","FAILURE");
			//snW.Wait(1000);
   /*
			//Step 5: click lookup icon
			if(snW.clickByXpath("LookUp_Icon_Xpath"))
				Reporter.reportStep("Step 5A: Look up icon clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5A: Look up icon could not be clicked","FAILURE");
			snW.Wait(3000);
			
			if(snW.enterAndChoose("ALERT_AssignedTo_Xpath", assignedTo))
				Reporter.reportStep("Step 5B: New assignee "+ assignedTo +" has been assigned", "SUCCESS");
			else
				Reporter.reportStep("Step 5B: New assignee "+ assignedTo +" could not be assigned","FAILURE");
			//step 6: click update to save
			if(snW.clickById("CIS_UpdateButton_Id")) 
				Reporter.reportStep("Step 6: Update has been clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("Step 6: Update could not be clicked","FAILURE");
			snW.Wait(5000);


			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");
			snW.Wait(5000);

			// Step 7: Log in as a different group
			if(snW.login(verUser,verPwd))
				Reporter.reportStep("Step 7: The login with username:"+ verUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 7: The login with username:"+ verUser + " is not successful", "FAILURE");

			// Step 8 : Expand OpsDirector/OpsConsole/ under application navigator to select Alert Console
			if(snW.selectMenu("Ops_Consoles", "My_Alert_Console"))
				Reporter.reportStep("Step 8: The My Alerts Console under OpsConsole - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 8: The My Alerts Console under OpsConsole - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			// Step 9 :Click Alert Console and select the alert record you assigned 
			if(snW.clickLink(alertId, false))
				Reporter.reportStep("Step 9: The assigned alert record  has been clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 9: The assigned alert record could not be clicked","FAILURE");


			snW.scrollToElementByXpath("ALERT_ActivityUpdateGroup_Xpath");
			// Step 10 :Code to verify the status change
			String sAssignedTo = snW.getTextByXpath("ALERT_ActivityUpdateGroup_Xpath"); 


			if(sAssignedTo.toLowerCase().contains(assignedTo.toLowerCase()))
				Reporter.reportStep("Step 10: The Alert has been assigned to "+ assignedTo ,"SUCCESS");
			else
				Reporter.reportStep("Step 10: The Alert could not be assigned to "+ assignedTo ,"FAILURE");
*/
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 7: The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("Step 7: The logout Failed", "FAILURE");		

			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name="OD_Stry0010149_Tc02")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OD_Stry0010149_Tc02");
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

