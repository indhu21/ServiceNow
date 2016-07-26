package com.punchit.scripts.demosuite2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class DEMORB_STRY0011066_TC01 extends SuiteMethods_1{
	ServiceNowWrappers snW;

	@Test(dataProvider = "RB_STRY0011066_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd
			) throws COSVisitorException, InterruptedException {

		// Prerequisites
		snW = new ServiceNowWrappers(entityId);

		try {

			if (snW.launchApp(browserName, true))
				Reporter1.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter1.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter1.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter1.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
			/*
			// get the system time 
			//if (!snW.selectMenuFromMainHeader("Incident", "Create_New"))
      		//	Reporter.reportStep("System Time could not be found","SUCCESS");
			snW.switchToFrame("Frame_Main");
            String time=snW.getAttributeById("INcident_CreateNew_Opened_ID", "value");
            System.out.println(time);
            String[] time1=time.split(" ");
            String[] time2=time1[1].split(":");
            System.out.println(time2[0]);
            System.out.println(time2[1]);
            System.out.println(time2[2]);
            Integer x = Integer.valueOf(time2[1]);
            x=x+1;
            System.out.println(x);
            String str = Integer.toString(x);
			*/
			String a1;
			Date date = new Date();
			{
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			Date currentDate = new Date(System.currentTimeMillis() - 48600 * 1000);
			System.out.println(currentDate);
			a1=sdf.format(currentDate);
			System.out.print(a1);
			}

			//String[] time1=a1.split(":");
	        String[] time2=a1.split(":");
	        System.out.println(time2[0]);
	        System.out.println(time2[1]);
	        System.out.println(time2[2]);
	        Integer x = Integer.valueOf(time2[1]);
	        x=x+1;
	        System.out.println(x);
	        String str = Integer.toString(x);
						
			// Step 2: Select Schedules
			if (snW.selectMenu("RunBook","RunBook_Definition", "Schedules"))
				Reporter1.reportStep("Step 2: The Schedules - menu selected successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 2: The Schedules - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
			//click on the first schedule 
			String Scheduleno=snW.getTextByXpath("RunBook_AssignedToMe_SecondRunbook_xpath");
			System.out.println("Run book is " +Scheduleno);
			
			
			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter1.reportStep("Step 3: Schedule number " + Scheduleno + " is clicked successfully","SUCCESS");
			else
			{   status = "Insufficient Data";
				Reporter1.reportStep("Step 3: Schedule number could not be clicked ","FAILURE");
			}
			
            // click on calender icon
			if(snW.clickByXpath("RunBook_Schedules_NextActionTime_Calendericon_xpath"))
				Reporter1.reportStep("Step 4: The Calendar icon is clicked successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 4: The Calendar icon could not clicked ","FAILURE");


			snW.getDriver().switchTo().activeElement();
			
			// enter the next action time 
			if(!snW.enterById("RunBook_Schedules_NextActionTime_Calendericon_hh_ID", time2[0]))
				Reporter1.reportStep("Step 5: Hours could not be entered ","FAILURE");
			// enter the next action  minutes
			if(!snW.enterById("RunBook_Schedules_NextActionTime_Calendericon_mm_ID", str))
				Reporter1.reportStep("Step 5: Minutes could not be entered ","FAILURE");
			// enter the next action seconds
			if(snW.enterById("RunBook_Schedules_NextActionTime_Calendericon_ss_ID", time2[2]))
				Reporter1.reportStep("Step 5: The next Action time is entered successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 5: The next Action time could not be entered","FAILURE");
			
			// click on OK button 
			if(!snW.clickById("RunBook_Schedules_NextActionTime_Calendericon_Rightbutton_ID"))
				Reporter1.reportStep("OK button to set the next action could not be clicked ","FAILURE");
			
			// click on Update button
			if(snW.clickById("RunBook_Schedules_Updatebutton_ID"))
				Reporter1.reportStep("Step 6: Update button is clicked successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 6: Update button could not clicked ","FAILURE");
			
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
				Reporter1.reportStep("Step 7: The All open runbooks - menu selected successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 7: The All open runbooks - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
			Thread.sleep(60000);
			
			if(!snW.rightClickByLinkText("RunBook_AllOpenRunbook_Link_Runbooks"))
				Reporter1.reportStep("Step 8: Right Click could not be performed to refresh the page","FAILURE");
			
			if(snW.clickByXpath("RunBook_AllOpenRunbook_Refresh_xpath"))
				Reporter1.reportStep("Step 8: All Runbook list is refreshed successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 8: All Runbook list could not refreshed","FAILURE");
			Thread.sleep(60000);
			if(!snW.rightClickByLinkText("RunBook_AllOpenRunbook_Link_Runbooks"))
				Reporter1.reportStep("Step 8: Right Click could not be performed to refresh the page","FAILURE");
			
			if(snW.clickByXpath("RunBook_AllOpenRunbook_Refresh_xpath"))
				Reporter1.reportStep("Step 8: All Runbook list is refreshed successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 8: All Runbook list could not refreshed","FAILURE");
			
			String x1=snW.getTextByXpath("RunBook_AllOpenRunbook_DueDate_xpath");
			System.out.println(x1);
			String[] time3=x1.split(" ");
            String[] time4=time3[1].split(":");
            System.out.println(time4[0]);
            System.out.println(time4[1]);
            System.out.println(time4[2]);
            if(!time4[1].equals(str))
            	Reporter1.reportStep("Step 9: Runbook is not created at the Scheduled time","FAILURE");
            
            if(time4[2].equalsIgnoreCase(time2[2]))
            	Reporter1.reportStep("Step 9: RunBook is created at the Scheduled time hence passed","SUCCESS");
			else
				Reporter1.reportStep("Step 8: RunBook is not created at the Scheduled time hence failed","FAILURE");
			
         // Log out
            if(!snW.clickByXpath("Logout_Xpath"))
            	Reporter1.reportStep("The logout Failed", "FAILURE");

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

