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

public class DEMO_CreateIncident extends  SuiteMethods_1  {
	
	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "DEMO_STRY0000001_TC01",groups="DemoIncident")
	public void createIncident (String regUser, String regPwd, String affuser, String cat,
			String subcat, String ci, String impact, String urgency, String asngrp,
			String shrtdes) throws COSVisitorException, IOException {
		
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
			
			/*snW.clickByXpath("Banner_Min_Xpath");
			snW.Wait(500);
			snW.clickByXpath("Banner_Min_Xpath");
			
			snW.switchToFrame("Frame_Nav");
			snW.Wait(3000);
			
			if(snW.clickLink("INCMENU_NEW"))
				Reporter.reportStep("Step 2: The Create Incident menu selected successfully","SUCCESS");
			else if(snW.clickById("INCMENU_ALLMENU_Id")){
				snW.clickLink("INCMENU_NEW");
				Reporter.reportStep("Step 2: The Create Incident menu selected successfully","SUCCESS");
			}
			else
				Reporter.reportStep("Step 2: The Create Incident menu could not be selected","FAILURE");
			*/
			if (snW.selectMenuFromMainHeader("Incident", "Create_New"))
				Reporter1.reportStep("Step 2: The Create New - menu selected successfully","SUCCESS");
			else
				Reporter1.reportStep("Step 2: The Create New - menu could not be selected","FAILURE");

			// Switch to the main frame
			//snW.switchToFrame("Frame_Main");
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			String incnum = snW.getAttributeByXpath("CREATEINC_IncidentNumberDEMO_Xpath", "value");
			System.out.print("Incidnet number is"+incnum);
			snW.enterByXpath("CREATEINC_AffectedUser_Xpath", affuser);
			snW.selectByVisibleTextById("CREATEINC_Category_Id", cat);
			snW.selectByVisibleTextById("CREATEINC_SubCategory_Id", subcat);
			snW.selectByVisibleTextById("CREATEINC_Impact_Id", impact);
			snW.selectByVisibleTextById("CREATEINC_Urgency_Id", urgency);
			snW.enterAndChoose("CREATEINC_AsgGroup_Xpath", asngrp);
			snW.enterByXpath("CREATEINC_shortDesc_Xpath", shrtdes);
			
			snW.Wait(3000);
			
			Reporter1.reportStep("Step 3: Field values entered as per reference data","SUCCESS");
			
			if(!snW.clickByXpath("Submit_Incident_Xpath"))
				Reporter1.reportStep("Step 4: The Submit button could not be clicked","FAILURE");
			snW.Wait(5000);
			
//			String supname = "Number";
//			if(!snW.selectByVisibleTextByXpath("Search_Key_Xpath", supname))
//				Reporter.reportStep("Step 4: Incident - Search Key could not be selected","FAILURE");
//			snW.Wait(500);
      		if(!snW.enterAndChoose("Header_SearchBox_Xpath", incnum))
				Reporter1.reportStep("Step 4: Incident -" +incnum+ " could not be entered in search box","FAILURE");
			snW.Wait(3000);
			
			if(snW.getTextByXpath("First_Searched_Record_Xpath").equals(incnum))
				snW.clickByXpath("First_Searched_Record_Xpath");
			else
				Reporter1.reportStep("Step 4: Incident creation failed","FAILURE");
			
			snW.Wait(5000);
			Reporter1.reportStep("Step 4: Incident - " +incnum+ " created successfully","SUCCESS");
			
			snW.switchToDefault();
			
			if (!snW.clickByXpath("Logout_Xpath"))
				Reporter1.reportStep("Step 5: The Log out could not be clicked","FAILURE");

			// Wait for few seconds
			snW.Wait(5000);
			
			status="PASS";
			
		} finally {
			// close the browser
			snW.quitBrowser();
			}

}

	@DataProvider(name = "DEMO_STRY0000001_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("DEMO_STRY0000001_TC01");
		return arrayObject;
	}
}