package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011056_TC01 extends SuiteMethods {
	
	// Create Instance
	ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011056_TC01",groups="Runbook")
	public void previewRunbook(String regUser, String regPwd, String temp) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
			
			if (snW.selectMenu("RunBook", "RunBook_Definition", "Templates"))
				Reporter.reportStep("Step 2: The Templates - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Templates - menu could not be selected","FAILURE");
			
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			if(snW.clickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Step 3: First Template clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: First Template could not be selected","FAILURE");
			
			String tempname = snW.getTextByXpath("Runbook_Templates_new_name_entervalue_id");
			if(snW.clickByXpath("RunBook_TemplatePreview_Xpath"))
				Reporter.reportStep("Step 4: Preview link clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 4: Preview link could not be clicked","FAILURE");
			
			snW.Wait(3000);
			String temprunbook = snW.getTextByXpath("Runbook_TempNum_Xpath");
			String[] tempnum = temprunbook.split(" ");
			
			if(tempnum[1].equals(temp))
				Reporter.reportStep("Step 5: Temporary Runbook opened successfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: Temporary Runbook could not be opened","FAILURE");
			
			if(!snW.clickById("Update_Button"))
				Reporter.reportStep("Step 6: Update button could not be clicked","FAILURE");
			
			snW.Wait(3000);
			
			if(snW.getTextById("Runbook_Templates_new_name_entervalue_id").equals(tempname))
				Reporter.reportStep("Step 6: Template form reloaded successfully","SUCCESS");
			else
				Reporter.reportStep("Step 6: Template form could not be reloaded","FAILURE");
			
			if(snW.clickByXpath("RunBook_TemplatePreview_Xpath"))
				Reporter.reportStep("Step 7: Preview link clicked again successfully","SUCCESS");
			else
				Reporter.reportStep("Step 7: Preview link could not be clicked again","FAILURE");
			
			snW.Wait(3000);
			String temprunbook1 = snW.getTextByXpath("Runbook_TempNum_Xpath");
			String[] tempnum1 = temprunbook1.split(" ");
			
			if(tempnum1[1].equals(temp))
				Reporter.reportStep("Step 8: Temporary Runbook opened again successfully","SUCCESS");
			else
				Reporter.reportStep("Step 8: Temporary Runbook could not be opened again","FAILURE");
			
			if(!snW.clickById("Update_Button"))
				Reporter.reportStep("Step 9: Update button could not be clicked","FAILURE");
			
			snW.Wait(3000);
			
			if(snW.getTextById("Runbook_Templates_new_name_entervalue_id").equals(tempname))
				Reporter.reportStep("Step 9: Template form reloaded successfully","SUCCESS");
			else
				Reporter.reportStep("Step 9: Template form could not be reloaded","FAILURE");
			
			snW.Wait(3000);
			snW.switchToFrame("Frame_Nav");
			
			if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
				Reporter.reportStep("Step 10: All open runbooks - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 10: All open runbooks - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			snW.Wait(3000);
			String supname = "Number";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname))
				Reporter.reportStep("Step 11: Runbook - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("Header_SearchBox_Xpath", temp))
				Reporter.reportStep("Step 11: Runbook -" +temp+ " could not be entered in search box","FAILURE");
			snW.Wait(3000);
			
			if(snW.getTextByXpath("First_Searched_Record_Xpath").equals(temp))
				Reporter.reportStep("Step 11: Runbook - "+temp+ " found","FAILURE");
			else
				Reporter.reportStep("Step 11: Runbook - "+temp+ " is not found","SUCCESS");
			
			snW.switchToDefault();
			
			if (!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("Step 12: The Log out could not be clicked","FAILURE");

			// Wait for few seconds
			snW.Wait(5000);
			
			status="PASS";
			
		} finally {
			// close the browser
			snW.quitBrowser();
			}

}

	@DataProvider(name = "RB_STRY0011056_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011056_TC01");
		return arrayObject;
	}

}

