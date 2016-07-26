package com.punchit.scripts.gileadim_V2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;


public class GLIM_V2_Stry000000_Tc19 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "GLIM_V2_Stry000000_Tc19",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String configItem, String repCust, 
								String asgGroup, String desc, String aUser, String causingCI,
								String causingCIComponent, String resolutionCode, String resolutionNotes,
								String regUser1, String regPwd1, String CustomerComms, String asgGroup1, String repoptName ) {

		// Pre-requisities

		try {

			String repName= repoptName+getCurrentTime();
			
			// Step 0: Launch the application
					snW.launchApp(browserName, true);

					MenuPage home = 
							new LoginPage()
							.loginAs(regUser, regPwd);
						
					IncidentPage incident = 
							home.clickCreateNew();

						String incNumber = 
								incident.getIncidentNumber();
						System.out.println(incNumber);
						
						
//						String[] colName ={"Resolved by","Reopen count","Reassignment count","Assigned To"};
//						
//						String[] value ={"TESM_INC_ITIL","0","0","TESM_INC_ITIL"};
			
							incident.createIncidentAndOpenIncident(configItem, repCust, asgGroup, desc, incNumber, aUser)
//							.populateMandatoryFields(configItem, repCust, asgGroup, desc)
//							.enterAssignedTo(aUser)
//							.submit()
//							.searchIncident(incNumber)
//							.clickFirstIncident()
							.clickResolveIncidentWithAlertAccept()
//							//			.alertAcceptforResolve()
//							.isExistResolutionInformation()
							.enterAllFieldsWithResolveButton(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
							.clickActivityLogForFailure();
					String date = incident.getTextByXpath("ALERT_ActivityDate_Xpath");
							
//							.MandatoryFieldsforResolutionTab(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
//							.clickResolveIncident();
					String[] elements ={"Resolved by [+]","Reopen count","Reassignment count","Assigned To [+]", "Resolved"};

					String[] colName ={"Resolved by","Reopen count","Reassignment count","Assigned To", "Resolved"};
					
					
					String[] value ={"TESM_INC_ITIL","0","0","TESM_INC_ITIL", date};
					
					
					
							home.expandReportsMenu()
							.clickViewRun()
							.clickCreateRecord()
							
//							.cliklink()
//							.verifyValueAndMoveToSelected(elements)
//							.verifycolumnValue(colName, value);
							
						
							.clickDataAndFilters(incNumber)
							.verifyValueAndMoveToSelected(elements)
							.verifycolumnValue(colName, value)
							.saveReport(repName);	
							home.clickLogout();
				
							new LoginPage()	
							.loginAs(regUser1, regPwd1)
							.clickAll()
							.searchAndOpenIncident(incNumber)
//							.searchIncident(incNumber)
//							.clickFirstIncident()	
							.clickReopen(CustomerComms)
							.enterAssignmentGroup(asgGroup1)
							.enterAssignedTo(regUser1)
							.clickWIP()
							.clickResolveIncidentWithAlertAccept()
							.enterAllFieldsWithResolveButton(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
							.clickActivityLogForFailure();
//							.MandatoryFieldsforResolutionTab(causingCI, causingCIComponent, resolutionCode, resolutionNotes)
//							.clickResolveIncident();
				String date1 = incident.getTextByXpath("ALERT_ActivityDate_Xpath");
							
						home.clickLogout();
						
						
						String[] value1 ={"TESM_INC_SD","1","1","TESM_INC_SD", date1};
						
							new LoginPage()	
							.loginAs(regUser, regPwd1)
							.expandReportsMenu()
							.clickViewRun()
							.searchReport(repName)
							.cliklink(repName)
//							.clickCreateRecord()
//							.clickDataAndFilters(incNumber)
//						    .verifyValueAndMoveToSelected(elements)
							.verifycolumnValue(colName, value1);	
						
						
							status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc19")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc19");
		return arrayObject;
	}
}
