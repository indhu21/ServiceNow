package com.punchit.scripts.demo;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class DEMO_ResolveIncident extends SuiteMethods_1 {
	
	
		// Create Instance
		ServiceNowWrappers snW;

		@Test(dataProvider = "DEMO_STRY0000003_TC01",groups="DemoIncident")
		public void resolveIncident (String regUser, String regPwd, String addcomments,
				String clcode, String clnotes) throws COSVisitorException, IOException {
			
			// Prerequisites
			snW = new ServiceNowWrappers(entityId);
			
			try {
				
				// Step 0: Launch the application
				snW.launchApp(browserName, true);

				// Step 1: Log in to application
				if (snW.login(regUser, regPwd))
					Reporter1.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
				else
					Reporter1.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
				
//				snW.clickByXpath("Banner_Min_Xpath");
//				snW.Wait(500);
//				snW.clickByXpath("Banner_Min_Xpath");
//				
//				snW.switchToFrame("Frame_Nav");
//				snW.Wait(3000);
//				
//				if(snW.clickLink("INCMENU_OPEN"))
//					Reporter.reportStep("Step 2: The Open incidents menu selected successfully","SUCCESS");
//				else if(snW.clickById("INCMENU_ALLMENU_Id")){
//					snW.clickLink("INCMENU_OPEN");
//					Reporter.reportStep("Step 2: The Open incidents menu selected successfully","SUCCESS");
//				}else
//					Reporter.reportStep("Step 2: The Open incidents menu could not be selected","FAILURE");
				if (snW.selectMenuFromMainHeader("Incident", "INCMENU_OPEN"))
					Reporter1.reportStep("Step 2: The Open - menu selected successfully","SUCCESS");
				else
					Reporter1.reportStep("Step 2: The Open - menu could not be selected","FAILURE");
				
				snW.switchToFrame("Frame_Main");
				if(snW.clickByXpath("First_Searched_Record_Xpath")){
					snW.Wait(5000);
					Reporter1.reportStep("Step 3: Incident to be Resolved opened","SUCCESS");
				}else
					Reporter1.reportStep("Step 3: Incident to be Resolved could not be opened","FAILURE");
				
				String incnum = snW.getAttributeByXpath("CREATEINC_IncidentNumberDEMO_Xpath", "value");
				System.out.print("Incidnet number is"+incnum);
				
				if(snW.clickById("Resolve_Button_Id")){
					snW.Wait(500);
					snW.enterByXpath("CREATEINC_CustomerComms_Xpath", addcomments);
					snW.Wait(2000);
					Reporter1.reportStep("Step 4: Resolve button clicked and Additional Comments filled","SUCCESS");
				}else
					Reporter1.reportStep("Step 4: Resolve button could not be clicked","FAILURE");
				
				if(!snW.clickById("Resolve_Button_Id"))
					Reporter1.reportStep("Step 5: Resolve button could not be clicked","FAILURE");
				
				snW.Wait(1000);
				
				String alertText = snW.getTextAndAcceptAlert();
				if(alertText != null)
					Reporter1.reportStep("Step 5: Alert displayed with message - "+alertText,"SUCCESS");
				else
					Reporter1.reportStep("Step 5: Alert was not displayed","FAILURE");
				
				if(!snW.selectByVisibleTextById("CREATEINC_CloseCode_Id", clcode))
					Reporter1.reportStep("Step 6: Close code could not be selected","FAILURE");
				
				if(snW.enterById("CREATEINC_CloseNotes_Id", clnotes)){
					snW.Wait(2000);
					Reporter1.reportStep("Step 6: Close code and Close notes filled according to reference data","SUCCESS");
				}else
					Reporter1.reportStep("Step 6: Close notes could not be selected","FAILURE");
				
				if(snW.clickById("Resolve_Button_Id"))
					Reporter1.reportStep("Step 7: Resolve button clicked","SUCCESS");
				else
					Reporter1.reportStep("Step 7: Resolve button could not be clicked","FAILURE");
				
				snW.switchToFrame("Frame_Nav");
				if(snW.clickLink("INCMENU_RESOLVED")){
					snW.Wait(3000);
					Reporter1.reportStep("Step 8: The Resolved incidents menu selected successfully","SUCCESS");
				}else
					Reporter1.reportStep("Step 8: The Resolved incidents menu could not be selected","FAILURE");
				
				snW.switchToFrame("Frame_Main");
				String supname = "Number";
				if(!snW.selectByVisibleTextByXpath("Search_Key_Xpath", supname))
					Reporter1.reportStep("Step 9: Incident - Search Key could not be selected","FAILURE");
				snW.Wait(500);
				if(!snW.enterAndChoose("Header_SearchBox_Xpath", incnum))
					Reporter1.reportStep("Step 9: Incident -" +incnum+ " could not be entered in search box","FAILURE");
				snW.Wait(3000);
				
				if(snW.getTextByXpath("First_Searched_Record_Xpath").equals(incnum))
					Reporter1.reportStep("Step 9: Incident - " +incnum+ " resolved successfully","SUCCESS");
				else
					Reporter1.reportStep("Step 9: Incident resolution failed","FAILURE");
				
				if(!snW.clickByXpath("First_Searched_Record_Xpath"))
					Reporter1.reportStep("Resolved Incident could not be clicked","FAILURE");
				
				if(!snW.clickByXpath("CREATEINC_CloseIncident_xpath"))
					Reporter1.reportStep("Resolved Incident could not be closed","FAILURE");
				
				snW.switchToDefault();
				
				if (!snW.clickByXpath("Logout_Xpath"))
					Reporter1.reportStep("Step 10: The Log out could not be clicked","FAILURE");

				// Wait for few seconds
				snW.Wait(5000);
				
				status="PASS";
					
				
			} finally {
				// close the browser
				snW.quitBrowser();
				}

	}

		@DataProvider(name = "DEMO_STRY0000003_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("DEMO_STRY0000003_TC01");
			return arrayObject;
		}
}
