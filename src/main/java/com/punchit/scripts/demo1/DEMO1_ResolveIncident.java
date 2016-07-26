package com.punchit.scripts.demo1;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import testng.SuiteMethods_demo1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class DEMO1_ResolveIncident extends SuiteMethods {


	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "DEMO1_STRY0000003_TC01",groups="DemoIncident")
	public void resolveIncident (String regUser, String regPwd, String addcomments,
			String clcode, String clnotes) throws COSVisitorException, IOException {

		// Prerequisites
		snW = new ServiceNowWrappers(entityId);

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");

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
//			if (snW.selectMenuFromMainHeader("Incident", "INCMENU_OPEN"))
		
			snW.switchToMenu();
			
			if (snW.clickLink("INCMENU_OPEN"))
				Reporter.reportStep("The Open - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Open - menu could not be selected","FAILURE");


			snW.switchToFrame("Frame_Main");

			if(!snW.clickByXpath("EditFilter_Xpath"))
				Reporter.reportStep("Filter icon could not be selected", "FAILURE");

			if(!snW.clickByXpath("AndCondition_Xpath"))
				Reporter.reportStep("The AND button is not clicked.", "FAILURE");

			if(!snW.addFilters("List_FirstFilterTypeByselect2_Xpath", "State", "List_FilterConditionByselect2_Xpath", 
					"is not", "List_FilterValueByselect2_Xpath", "Resolved"))

			snW.Wait(5000);
			
			if(!snW.clickByXpath("Worldpay_RunFilter_Xpath"))
				Reporter.reportStep("Run button could not be clicked", "FAILURE");

			if(snW.clickByXpath("FirstLink_Xpath")){
				snW.Wait(5000);
				Reporter.reportStep("Incident to be 'Resolved' opened","SUCCESS");
			}else
				Reporter.reportStep("Incident to be 'Resolved' could not be opened","FAILURE");

			String incnum = snW.getAttributeByXpath("CREATEINC_IncidentNumberDEMO_Xpath", "value");
			System.out.print("Incidnet number is"+incnum);

			if(snW.clickById("Resolve_Button_Id")){
				snW.Wait(500);
				snW.enterByXpath("CREATEINC_CustomerComms_Xpath", addcomments);
				snW.Wait(2000);
				Reporter.reportStep("Resolve button clicked and Additional Comments filled","SUCCESS");
			}else
				Reporter.reportStep("Resolve button could not be clicked","FAILURE");

			if(!snW.clickById("Resolve_Button_Id"))
				Reporter.reportStep("Resolve button could not be clicked","FAILURE");

			snW.Wait(1000);

			String alertText = snW.getTextAndAcceptAlert();
			if(alertText != null)
				Reporter.reportStep("Alert displayed with message - "+alertText,"SUCCESS");
			else
				Reporter.reportStep("Alert was not displayed","FAILURE");

			if(!snW.selectByVisibleTextById("CREATEINC_CloseCode_Id", clcode))
				Reporter.reportStep("Close code could not be selected","FAILURE");

			if(snW.enterById("CREATEINC_CloseNotes_Id", clnotes)){
				snW.Wait(2000);
				Reporter.reportStep("Close code and Close notes filled according to reference data","SUCCESS");
			}else
				Reporter.reportStep("Close notes could not be selected","FAILURE");

			if(snW.clickById("Resolve_Button_Id"))
				Reporter.reportStep("Resolve button clicked","SUCCESS");
			else
				Reporter.reportStep("Resolve button could not be clicked","FAILURE");

			snW.switchToFrame("Frame_Nav");
			if(snW.clickLink("INCMENU_RESOLVED")){
				snW.Wait(3000);
				Reporter.reportStep("The Resolved incidents menu selected successfully","SUCCESS");
			}else
				Reporter.reportStep("The Resolved incidents menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");
			/*				String supname = "Number";
				if(!snW.selectByVisibleTextByXpath("Search_Key_Xpath", supname))
					Reporter.reportStep("Incident - Search Key could not be selected","FAILURE");
			 */				snW.Wait(500);
			 if(!snW.enterAndChoose("Demo_SearchReferenceData_Xpath", incnum))
				 Reporter.reportStep("Incident -" +incnum+ " could not be entered in search box","FAILURE");
			 snW.PresEnter();
			 snW.Wait(3000);

			 if(snW.getTextByXpath("FirstLink_Xpath").equals(incnum))
				 Reporter.reportStep("Incident - " +incnum+ " resolved successfully","SUCCESS");
			 else
				 Reporter.reportStep("Incident resolution failed","FAILURE");

			 if(!snW.clickByXpath("FirstLink_Xpath"))
				 Reporter.reportStep("Resolved Incident could not be clicked","FAILURE");

			 if(!snW.clickByXpath("CREATEINC_CloseIncident_xpath"))
				 Reporter.reportStep("Resolved Incident could not be closed","FAILURE");

			 snW.switchToDefault();

			 if (!snW.clickByXpath("Logout_Xpath"))
				 Reporter.reportStep("The Log out could not be clicked","FAILURE");

			 // Wait for few seconds
			 snW.Wait(5000);

			 status="PASS";


		} finally {
			snW.switchToDefault();
			if(snW.isExistByXpath("Logout_Xpath"))
				{if (!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The Log out could not be clicked","FAILURE");}

			snW.quitBrowser();
		}

	}

	@DataProvider(name = "DEMO1_STRY0000003_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMO1_STRY0000003_TC01");
		return arrayObject;
	}
}
