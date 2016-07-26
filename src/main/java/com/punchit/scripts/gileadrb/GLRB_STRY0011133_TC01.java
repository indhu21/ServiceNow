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

public class GLRB_STRY0011133_TC01 extends SuiteMethods {
	
	// Create Instance
	ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011133_TC01",groups="Runbook")
	public void assignToMe(String regUser, String regPwd, String resp,String comments) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
			
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
				Reporter.reportStep("Step 2: The Assigned To Me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Assigned To Me - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			//Add Filter to Get Runbook with Steps available.
		    snW.clickByXpath("Runbook_Asignedtome_Filter_Icon_Xpath");
		    Thread.sleep(4000);
		    snW.clickByXpath("Runbook_Asignedtome_Filter_AND_Xpath");
		    Thread.sleep(2000);
		    snW.selectByVisibleTextByXpath("Runbook_Asignedtome_FilterCond1_Xpath", "Template");
		    snW.pressKey(Keys.TAB);
		    snW.selectByVisibleTextByXpath("Runbook_Asignedtome_FilterCond2_Xpath", "is not empty");
		    snW.clickByXpath("Runbook_Asignedtome_Filter_RUN_Xpath");
		    snW.Wait(3000);
			
			String Runbookno=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
			
			// Right click 
			if(snW.rightClickByXpath("RunBook_Unassigned_firstrunbbok_xpath"))
				Reporter.reportStep("Step 3: Right click on the Runbook is successful ","SUCCESS");
			else
			{   status = "INSUFFICIENT DATA";
				Reporter.reportStep("Step 3: Right click on the Runbook could not be performed ","FAILURE");
			}
			
			
			//Take run book
			if(snW.clickByXpath("RunBook_assignedtome_rightclicTakeRunbook_xpath"))
			Reporter.reportStep("Step 4: Take Runbook is successfully selected ","SUCCESS");
			else
			Reporter.reportStep("Step 4: Take Runbook could not be selected","FAILURE");
			
			//Step 5: check if run book steps are displayed
			String step = snW.getTextByXpath("RunBook_assigntome_takerunbbokpage_stepcolumn_xpath");
	        
			if(step.equalsIgnoreCase("Step"))
			Reporter.reportStep("Step 5: Runbook page with steps of selected Runbook is displayed successfully","SUCCESS");
			else
			Reporter.reportStep("Step 5: Runbook page with steps of selected Runbook is  not displayed","FAILURE");
	        
			//Step 6: selecting response as fail
			if(!snW.selectByVisibleTextByXpath("Runbook_AssignedToMe_Response_Xpath", resp))
				Reporter.reportStep("Step 6:  Assgned to me: Take runbook is not selected as FAIL","FAILURE");
			
			
			Integer odd = snW.getCountOfElementsByXpath("Runbook_Odd_Steps_Xpath");
			Integer even = snW.getCountOfElementsByXpath("Runbook_Even_Steps_Xpath");
			Integer total = odd + even;
			
			for (int i=0; i<total-1; i++){
				snW.pressKey(Keys.TAB);
				snW.pressKey(Keys.DOWN);
				snW.pressKey(Keys.TAB);
			}
	        
			//Step 7: Close the runbook
			if(snW.clickByXpath("Runbook_CloseRunbook_Xpath"))
				Reporter.reportStep("Step 6: Take Runbook selected as PASS and Close runbook is clicked","SUCCESS");
			else
			Reporter.reportStep("Step 6:Close runbook could not be clicked","FAILURE");
	        
			//Step 8: Fill in the comments
			snW.getDriver().switchTo().activeElement();
			
			// enter the comments 
			if(snW.enterById("RunBook_assigntome_comments_ID", comments))
				Reporter.reportStep("Step 7: Comments are entered   successfully","SUCCESS");
			else
				Reporter.reportStep("Step 7: Comments could not be entered","FAILURE");
			
			snW.clickByXpath("Runbook_CommentsClose_Xpath");
			snW.Wait(3000);
			snW.switchToFrame("Frame_Nav");
			
			if (snW.selectMenuFromMainHeader("RunBook", "All_runbook"))
				Reporter.reportStep("Step 8: The All runbook - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 8: The All runbook - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
			snW.Wait(3000);
			String supname = "Number";
			if(!snW.selectByVisibleTextByXpath("Runbook_SearchKey_Xpath", supname))
				Reporter.reportStep("Step 9: Runbook - Search Key could not be selected","FAILURE");
			snW.Wait(500);
			if(!snW.enterAndChoose("Header_SearchBox_Xpath", Runbookno))
				Reporter.reportStep("Step 9: Runbook -" +Runbookno+ " could not be entered in search box","FAILURE");
			snW.Wait(3000);
			
			if(snW.getTextByXpath("First_Searched_Record_Xpath").equals(Runbookno)){
				snW.clickByXpath("First_Searched_Record_Xpath");
				snW.Wait(3000);
				Reporter.reportStep("Step 9: Runbook - "+Runbookno+ " found","SUCCESS");
			}else
				Reporter.reportStep("Step 9: Runbook - "+Runbookno+ " is not found","FAILURE");
			
			String state = snW.getDefaultValueByXpath("Runbook_State_Xpath");
			String clcode = snW.getDefaultValueByXpath("Runbook_ClosureCode_Xpath");
			String exstate = "Closed Complete";
			String exclcode = "Success";
			String exclcode1 = "Parent";
			
			if(state.equals(exstate))
				Reporter.reportStep("Step 10: Runbook - "+Runbookno+ " State is "+state,"SUCCESS");
			else
				Reporter.reportStep("Step 10: Runbook - "+Runbookno+ " State is "+state+", should be "+exstate+" ,hence fail","FAILURE");
			
			if(clcode.equals(exclcode) || clcode.equals(exclcode1))
				Reporter.reportStep("Step 11: Runbook - "+Runbookno+ " Closure code is "+clcode,"SUCCESS");
			else
				Reporter.reportStep("Step 11: Runbook - "+Runbookno+ " Closure code is "+clcode+", should be "+exclcode+ " or "+exclcode1+", hence fail","FAILURE");
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

	@DataProvider(name = "RB_STRY0011133_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011133_TC01");
		return arrayObject;
	}


}
