package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_Tc28 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc28",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String configItem, String repCust, String asgGroup,
			String desc, String asgTo, String configItem1, String repCust1, String asgGroup1, String desc1,
			String asgTo1, String filter1, String filter2, String filter3, String CI, String reportCus,
			String assGroup, String shortDec, String assTo, String filter4,	String filter5, String filter6,
			String configItem2, String repCust2, String asgGroup2, String desc2, String aUser2, String filter7,
			String filter8, String filter9, String configItem3, String repCust3, String asgGroup3, String desc3,
			String aUser3, String filter10, String filter11, String filter12, String selectBy, String searchItem){

		// Pre-requisities

		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);

			//Step 1A: Navigate to incident and click assign to me 
			IncidentPage incident =
					home.clickCreateNewforFailure();
			//								.populateMandatoryFields(CI, reportCus, assGroup, shortDec)
			//								.enterAssignedTo(assTo);
			String incNumber=incident.getIncidentNumber();

			incident.createIncidentWithAssignedToAndOpenIncident(configItem, repCust, asgGroup, desc,incNumber,asgTo)
			.VerifyResolutionInformationAndCodefieldExists()
			//		 
			//						incident.submitIncident()
			//								.searchIncident(IncNum)
			//								.clickFirstIncident()
			//								.clickResolveIncidentWithAlertAccept()
			//								.alertAcceptforResolve()
			//								.isExistResolutionInformation()
			//								.isExistResolutionCodefield()
			//								.clickResolveIncident()
			.isAlertPresentforClickResolve();
/*

			home.clickCreateNewforFailure();
			//								.populateMandatoryFields(CI, reportCus, assGroup, shortDec)
			//								.enterAssignedTo(assTo);
			incNumber=incident.getIncidentNumber();
			//						incident.submitIncident()

			incident.createIncidentWithAssignedToAndOpenIncident(configItem1, repCust1, asgGroup1, desc1,incNumber,asgTo1)
			//								.searchIncident(incNumber)
			//								.clickFirstIncident()
			//								.clickResolveIncidentWithAlertAccept()
			////								.alertAcceptforResolve()
			//								.isExistResolutionInformation()
			//								.isExistResolutionCodefield()
			
*/			
			
			incident.VerifyResolutionInformationAndCodefieldExists()
			//								.enterAndChooseCausingCI(filter3)
			//								.clickCausingCISpyGlass()
			//								.selectFilter(filter1, filter2, filter3)
			//								.clickFirstName()
			//								.clickResolutioCodeSpyGlass()
			.enterCausingCIandClickResolutionCodeSpyglass(filter3)
			//								.verifyResolutionCodeSpyGlass(ele);
			.verifyResolutionCodeSpyGlassCIcounts()

			/*

					switchToMain();
					snW.clickById("Save_Id");


					String[] ele1={"Account unlocked", 
							"Cancelled - SR",
							"Cancelled by User",
							"Capacity Management",
							"Data retrieved/restored",
							"DNS restored",
					"Documentation updated"};
					String[] ele2={"Emergency Fix",
							"Passed to External Vendor",
							"Pwd Change", 
							"Software upgrade/install",
							"Training suggested",
							"Training/Knowledge provided",
					"Unable to reproduce"};
					//Step 1A: Navigate to incident and click assign to me 

							home.clickCreateNewforFailure();

					String IncNum = 
							incident.getIncidentNumber();

					incident.createIncidentWithAssignedToAndOpenIncident(CI, reportCus, assGroup, shortDec, IncNum, assTo)
					.VerifyResolutionInformationAndCodefieldExists()


			 */		
			.enterCausingCIandClickResolutionCodeSpyglass(filter6)
			/*.verifyResolutionCodeSpyGlass(ele1, false)
										.verifyResolutionCodeSpyGlass(ele2, true);*/
			.verifyResolutionCodeSpyGlassCIcounts()
			/*			
					switchToMain();
					snW.clickById("Save_Id");

					String [] ele3 = {"Account unlocked",	
							"Cancelled - SR",
							"Cancelled by User",	
							"Capacity Management",	
							"Cooling restored",	
							"Data retrieved/restored",	
							"DHCP reset",	
					"DNS restored"};
					String[] ele4={"Documentation updated",
							"Emergency Fix",
							"Hardware replaced/fixed",	
							"Passed to External Vendor",	
							"Pwd Change",	
							"Software upgrade/install",	
							"Training suggested",	
							"Training/Knowledge provided",	
					"Unable to reproduce"};


					home.clickCreateNewforFailure();

					// Take a note of the INC number.
					String incNumber2 = 
							incident.getIncidentNumber();
					System.out.println(incNumber);

					incident.createIncidentWithAssignedToAndOpenIncident(configItem1, repCust1, asgGroup1, desc1, incNumber2, aUser1)
					.VerifyResolutionInformationAndCodefieldExists()

			 */		

			.enterCausingCIandClickResolutionCodeSpyglass(filter9)
			/*.verifyResolutionCodeSpyGlass(ele3, false)
										.verifyResolutionCodeSpyGlass(ele4, true);*/
			.verifyResolutionCodeSpyGlassCIcounts()

			/*			


					//						// Step 8: Enter all Mandatory fields
					//						incident
					//						.populateMandatoryFields(configItem, repCust, asgGroup, desc)
					//						.enterAssignedTo(aUser)
					//						.submit()
					//						.searchIncident(incNumber)
					//						.clickFirstIncident()
					//						.clickResolveIncidentWithAlertAccept()
					//						.alertAcceptforResolve()
					//						.isExistResolutionInformation()
					//						.isExistResolutionCodefield()
					//						.clickCausingCISpyGlass()
					//						.selectFilter(filter7, filter8, filter9)
					//						.clickFirstName()
					//						.clickResolutioCodeSpyGlass()
					//						.verifyResolutionCodeSpyGlass(ele3, false)
					//						.verifyResolutionCodeSpyGlass(ele4, true);

					switchToMain();
					snW.clickById("Save_Id");

					String [] ele5 = {"Account unlocked",
							"Cancelled - SR",
							"Cancelled by User",
							"Capacity Management", 
							"Data retrieved/restored",
							"DHCP reset",
							"DNS restored",
					"Documentation Updated"};
					String [] ele6={"Emergency Fix",
							"Passed to external vendor",
							"Pwd Change",
							"Software upgrade/install",
							"Training suggested",
							"Training/knowledge provided",
					"Unabled to reproduce"};

					home.clickCreateNew();

					// Take a note of the INC number.
					String incNumber3 = incident.getIncidentNumber();
					System.out.println(incNumber3);

					// Step 8: Enter all Mandatory fields
					//				incident
					//				.populateMandatoryFields(configItem, repCust, asgGroup, desc)
					//				.enterAssignedTo(aUser)
					//				.submit()
					//				.searchIncident(incNumber)
					//				.clickFirstIncident()
					incident.createIncidentWithAssignedToAndOpenIncident(configItem2, repCust2, asgGroup2, desc2, incNumber3, aUser2)
					//				.clickResolveIncidentWithAlertAccept()
					//				.alertAcceptforResolve()
					//				.isExistResolutionInformation()
					//				.isExistResolutionCodefield()
					.VerifyResolutionInformationAndCodefieldExists()
					//				.enterAndChooseCausingCI(filter3)
					//				.clickCausingCISpyGlass()
					//				.selectFilter(filter1, filter2, filter3)
					//				.selectFilter(filter1, filter2, filter3)
					//				.clickFirstName()
					//				.clickResolutioCodeSpyGlass()


			 */		
			.enterCausingCIandClickResolutionCodeSpyglass(filter12)
			/*.verifyResolutionCodeSpyGlass(ele)	
						.verifyResolutionCodeSpyGlass(ele1);*/
			.verifyResolutionCodeSpyGlassCIcounts();

			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc28")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc28");
		return arrayObject;
	}
}
