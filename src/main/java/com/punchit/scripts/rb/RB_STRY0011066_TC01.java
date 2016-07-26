package com.punchit.scripts.rb;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011066_TC01 extends SuiteMethods{
	ServiceNowWrappers snW;

	@Test(dataProvider = "RB_STRY0011066_TC01",groups="CMDB")
	public void appProperties(String regUser, String regPwd, String Filtervalue, String Condition
			) throws COSVisitorException, InterruptedException {

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
			String a1;

			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(currentDate);
			//GregorianCalendar gcal = new GregorianCalendar(tz);
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			//a1=sdf.format(currentDate);
			System.out.println(currentDate);
			System.out.println(currentDate.getHours());
			System.out.println(currentDate.getMinutes());
			int minutes=currentDate.getMinutes();
			System.out.println(currentDate.getSeconds());
			//Integer x = Integer.valueOf(time2[1]);
			int x=minutes+4;
			String str = Integer.toString(x);
			int hh=currentDate.getHours();

			int ss=currentDate.getSeconds();
			String h = Integer.toString(hh);
			String s = Integer.toString(ss);

			// Step 2: Select Schedules
			if (snW.selectMenu("RunBook","RunBook_Definition", "Schedules"))
				Reporter.reportStep("The Schedules - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Schedules - menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");

			WebDriverWait wait = new WebDriverWait(getDriver(), 30);

			//click on the first schedule 
			String Scheduleno=snW.getTextByXpath("RunBook_AssignedToMe_SecondRunbook_xpath");
			System.out.println("Run book is " +Scheduleno);


			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Schedule number " + Scheduleno + " is clicked successfully","SUCCESS");
			else
			{   status = "Insufficient Data";
			Reporter.reportStep("Schedule number could not be clicked ","FAILURE");
			}


			// click on calender icon
			if(snW.clickByXpath("RunBook_Schedules_NextActionTime_Calendericon_xpath"))
			{	snW.Wait(2000);
			Reporter.reportStep("The Calendar icon is clicked successfully","SUCCESS");
			}
			else
				Reporter.reportStep("The Calendar icon could not clicked ","FAILURE");


			snW.getDriver().switchTo().activeElement();

			// enter the next action time 
			if(!snW.enterById("RunBook_Schedules_NextActionTime_Calendericon_hh_ID", h))
				Reporter.reportStep("Hours could not be entered ","FAILURE");
			// enter the next action  minutes
			if(!snW.enterById("RunBook_Schedules_NextActionTime_Calendericon_mm_ID", str))
				Reporter.reportStep("Minutes could not be entered ","FAILURE");
			// enter the next action seconds
			if(snW.enterById("RunBook_Schedules_NextActionTime_Calendericon_ss_ID", s))
				Reporter.reportStep("The next Action time is entered successfully","SUCCESS");
			else
				Reporter.reportStep("The next Action time could not be entered","FAILURE");

			// click on OK button 
			if(!snW.clickById("RunBook_Schedules_NextActionTime_Calendericon_Rightbutton_ID"))
				Reporter.reportStep("OK button to set the next action could not be clicked ","FAILURE");

			String data="Testing_"+snW.getCurrentTime();
			// Step 10: Right and Save Profile
			if (!snW.enterById("Runbook_Desc_Id", data))
				Reporter.reportStep("The description could not be entered","FAILURE");

			if (!snW.rightClickById("Prof_Rclick"))
				Reporter.reportStep("The Right click could not be clicked","FAILURE");

			wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(objRep.getProperty("Prof_Save_Xpath")))));

			if (snW.clickByXpath("Prof_Save_Xpath"))
				Reporter.reportStep("Save button is clicked successfully","SUCCESS");
			else
				Reporter.reportStep("The Save could not be clicked","FAILURE");

			snW.switchToMain();
			
			wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(objRep.getProperty("Runbook_Schedule_NextActionTime_Xpath")))));

			if(!snW.rightClickById("Prof_Rclick"))
				Reporter.reportStep("Right Click could not be performed to refresh the page","FAILURE");

			if (!snW.clickByXpath("RunBook_AllOpenRunbook_Reload_xpath"))
				Reporter.reportStep("The Save could not be clicked","FAILURE");

			switchToMain();
			
			wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(objRep.getProperty("Runbook_Schedule_NextActionTime_Xpath")))));

			if(!snW.rightClickById("Prof_Rclick"))
				Reporter.reportStep("Right Click could not be performed to refresh the page","FAILURE");

			if (!snW.clickByXpath("RunBook_AllOpenRunbook_Reload_xpath"))
				Reporter.reportStep("The Save could not be clicked","FAILURE");

			switchToMain();
			
			wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(objRep.getProperty("Runbook_Schedule_NextActionTime_Xpath")))));

			if(!snW.rightClickById("Prof_Rclick"))
				Reporter.reportStep("Right Click could not be performed to refresh the page","FAILURE");

			if (!snW.clickByXpath("RunBook_AllOpenRunbook_Reload_xpath"))
				Reporter.reportStep("The Save could not be clicked","FAILURE");

			switchToMain();
			
			if(!snW.scrollToElementById("Runbook_Schedule_RunbookNavID"))
				Reporter.reportStep("Runbook nav is not present","FAILURE");

			String elements="Description";

			if(!snW.clickByXpath("Runbook_Setting_Xpath"))
				Reporter.reportStep("Setting Icon could not be clicked.","FAILURE");

			snW.Wait(5000);

			String text=getTextByXpath("ALERT_SlushSelected_Xpath");

			System.out.println(text);
			if(!text.contains(elements)){
				if(selectByVisibleTextByXpath("ALERT_SlushAvailable_Xpath", elements)) 
					Reporter.reportStep("The Value: "+elements+" is not selected from available list.", "FAILURE");

				if(!clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
					Reporter.reportStep("The Right Arrow is not clicked or not found", "FAILURE");

				if(!clickByXpath("Runbook_Runbooksettingup_Xpath"))
					Reporter.reportStep("The Up Button is not clicked or not found", "FAILURE");

				if(!clickByXpath("Runbook_Runbooksettingup_Xpath"))
					Reporter.reportStep("The Up Button is not clicked or not found", "FAILURE");

				if(!clickByXpath("Runbook_Runbooksettingup_Xpath"))
					Reporter.reportStep("The Up Button is not clicked or not found", "FAILURE");

				if(!clickByXpath("Runbook_Runbooksettingup_Xpath"))
					Reporter.reportStep("The Up Button is not clicked or not found", "FAILURE");

			}
			if(!clickByXpath("OkButton_Xpath"))
				Reporter.reportStep("The Save Button is not clicked or not found", "FAILURE");

			Wait(5000);
			
			snW.scrollToElementByXpath("Runbook_RunbookTabSelect_Xpath");
			
			if(!snW.selectByVisibleTextByXpath("Runbook_RunbookTabSelect_Xpath", "Description"))
				Reporter.reportStep("The Description could not be selected, hence failure. ","FAILURE");

			if(!snW.enterByXpathAndClick("Runbook_RunbookTabInput_Xpath", data))
				Reporter.reportStep("The Description could not be entered, hence failure. ","FAILURE");

			snW.Wait(5000);

			String RunbookNo = getColumnValue("Number", "Alerts_TableHeading1_Xpath");

			System.out.println(RunbookNo);

			snW.switchToMenu();
			
			if(!snW.enterById("filter_Id", "RunBook"))
				Reporter.reportStep("The Value Runbook could not be entered","FAILURE");
			
			snW.Wait(5000);
			
			if (snW.clickLink("All_open_runbooks"))
				Reporter.reportStep("The All open runbooks - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The All open runbooks - menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");

			snW.Wait(500);

			if(!snW.selectByVisibleTextByXpath("ALERT_Goto_Xpath","Number"))
				Reporter.reportStep("Number could not be selected from the searchbox","FAILURE");

			if(!snW.enterByXpathAndClick("Runbook_AllOpenRB_SearchValue_Xpath",RunbookNo))
				Reporter.reportStep("Runbook number could not be entered in the search value field","FAILURE");

			String RunbookNo1=getTextByXpath("Alert_Profiles_LastCreatedProfile_Xpath");

			System.out.println(RunbookNo1);	

			if(RunbookNo.equals(RunbookNo1))
				Reporter.reportStep("Runbook is created at the Scheduled time","SUCCESS");
			else
				Reporter.reportStep("Runbook is not created at the Scheduled time","FAILURE");

			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");

			status = "PASS";


		}
		finally{
			// close the browser
			snW.quitBrowser();		
		}
	}
	@DataProvider(name = "RB_STRY0011066_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011066_TC01");
		return arrayObject;
	}
}

