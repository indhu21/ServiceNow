package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class GLIM_V2_Stry000000_Tc16 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc16",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String causingCI ,String CI, String Reportingcustomer,
			String Group, String Description, String Assignto, String causingCI1 ,String CI1,String Reportingcustomer1,
			String Group1,String Description1, String Assignto1, String CI2,String Reportingcustomer2,
			String Group2,String Description2, String Assignto2, String filter1, String filter2, String filter3, String causingCI2, 			
			String configItem , String repCust, String asgGroup,
			String desc, String assTo, String causingCI3) {

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);

			IncidentPage incident = 
					home.clickCreateNewforFailure();

			// Take a note of the INC number.
			String incNumber = 
					incident.getIncidentNumber();

			System.out.println(incNumber);

			incident.createIncidentWithWorkInProcessAndOpenIncident(CI, Reportingcustomer, Group, Description, incNumber, regUser)
			.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI)
			//						.verifyCIComponentBusinessServiceLookUpValues();
			.verifyCIComponentBusinessServiceLookUpCIcountsWithWarning();
/*
			snW.clickById("Save_Id");

			home.clickCreateNewforFailure();

			// Take a note of the INC number.
			String incNumber1 = 
					incident.getIncidentNumber();

			System.out.println(incNumber1);

			incident.createIncidentWithWorkInProcessAndOpenIncident(CI1, Reportingcustomer1, Group1, Description1, incNumber1, regUser)
			
	*/		
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI1)
			//				.verifyCIComponentApplicationLookUpValues();
			.verifyCIComponentApplicationLookUpValuesCIcountsWithWarning();

/*
			snW.clickById("Save_Id");

			home.clickCreateNewforFailure();

			// Take a note of the INC number.
			String incNumber2 = 
					incident.getIncidentNumber();

			System.out.println(incNumber2);



			incident.createIncidentWithWorkInProcessAndOpenIncident(CI2, Reportingcustomer2, Group2, Description2, incNumber2, regUser)
			
			
	*/		
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI3)
			//				.verifyCausingCIComponentFieldLookUpValuesForServer();
			.verifyCausingCIComponentFieldLookUpValuesForServerCIcountsWithWarning();

/*
			snW.clickById("Save_Id");

			home.clickCreateNewforFailure();

			// Take a note of the INC number.
			String incNumber3 = 
					incident.getIncidentNumber();

			System.out.println(incNumber3);


			incident.createIncidentWithWorkInProcessAndOpenIncident(configItem, repCust, asgGroup, desc, incNumber3, assTo)	
*/			
			
			incident.enterCausingCIandClickCausingCIComponentSpyGlass(causingCI2)

			//				// Step 2: click on WIP		
			//					home.clickWIP()
			//						.clickFunnel()
			//						.clickANDCondition()
			//						.addThirdFilterbyEnterValues(filterType, filterCondition, filterValue)
			//						.clickRun()
			//						.clickFirstIncident()
			//						.clickResolutionInformation()
			////						.clickCausingCISpyGlass()
			////						.selectFilter(filter1, filter2, filter3)
			////						.clickFirstCIClass()
			//						.enterAndChooseCausingCI(filter3)
			//						.clickCausingCIComponentSpyGlass()
			//						.verifyCIComponentDatabaseLookUpValues();
			.verifyCausingCIComponentFieldLookUpValuesForDatabaseCIcountsWithWarning();


			//			// Step 8: Enter all Mandatory fields
			//			incident.populateMandatoryFields(CI, Reportingcustomer, Group, Description)
			//	          .enterAssignedTo(Assignto)
			//	          .submitIncident();
			//			
			//			home.clickOpen().searchIncident(incNumber).clickFirstIncident();
			//
			//		    if(snW.clickByXpath("CREATEINC_WIP_Xpath"))
			//		    	Reporter.reportStep("Work in Progress Button clicked successfully", "SUCCESS");
			//		    else 
			//		    	Reporter.reportStep("Work in Progress Button could not be clicked", "FAILURE");
			//		
			//			
			//			// Step 2: click on WIP		
			//		    home.clickWIP().searchIncident(incNumber).clickFirstIncident();
			//		   // incident.clickFirstIncident()			
			//
			//			// Step 2: click on WIP		
			//			//home.clickWIP()
			//		   // .clickFirstIncident()
			//			incident.clickResolutionInformation()
			//			.enterAndChooseCausingCI(causingCI)
			//			.clickCausingCIComponentSpyGlass()
			//			.verifyCIComponentBusinessServiceLookUpValues();



			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc16")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc16");
		return arrayObject;
	}
}
