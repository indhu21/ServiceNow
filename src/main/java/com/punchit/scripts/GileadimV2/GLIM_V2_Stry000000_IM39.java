package com.punchit.scripts.GileadimV2;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.IncidentPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SPARCPortalPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM39 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM39",groups="IncidentManagement")
	public void createIncident( String regUser, String regPwd, String assGrp, String isNoT,
								String contactCenter, String is, String assTo, String state,
								String wip,String hold,String regUser1, String regPwd1){

		try {

				launchApp(browserName, true);
				
				MenuPage home=	
						new LoginPage()
						.loginAs(regUser, regPwd);
					
					home.clickOpen()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assignment Group", "is not", "TESM_INC_GROUP2")
						.clickANDCondition()
						.addThirdFilter("State", "is", "Open")
						.clickRun()
						.clickFirstIncident()
						.IsTakeTicketAvailable(regUser);
					
					home.clickOpen()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assignment Group", "is", "TESM_INC_GROUP2")
						.clickANDCondition()
						.addThirdFilter("State", "is", "Open")
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser);
					home.clickOpen()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assigned To", "is not", regUser)
						.clickANDCondition()
						.addThirdFilterbyEnterAndChooseWithReport("Assignment Group", "is not", "TESM_INC_GROUP2")
						.clickANDCondition()
						.addFourthFilter("State", "is", "Open")
						.clickRun()
						.clickFirstIncident()
						.IsTakeTicketAvailable(regUser);
					home.clickOpen()
						.clickFunnelWithoutReport()
						/*.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assigned To", "is not", regUser)*/
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assignment Group", "is", "TESM_INC_GROUP2")
						.clickANDCondition()
						.addThirdFilter("State", "is", "Open")
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser);	
					home.clickAll()
						.clickFunnelWithoutReport()
						.addFirstFilterWithReport("State", "is", "Work in Progress")
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assigned To", "is not", regUser)
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser);
					home.clickAll()
						.clickFunnelWithoutReport()
						.addFirstFilterWithReport("State", "is", "On Hold")
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assigned To", "is not", regUser)
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser);
					home.clickResolved()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addThirdFilterbyEnterAndChooseWithReport("Assigned To", "is not", regUser)
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser);
					home.clickClosed()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addThirdFilterbyEnterAndChooseWithReport("Assigned To", "is not", regUser)
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser);
					home.clickLogout();
					
					new LoginPage()
						.loginAs(regUser1, regPwd1);
					
					home.clickOpen()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assignment Group", "is not", "TESM_INC_GROUP2")
						.clickANDCondition()
						.addThirdFilter("State", "is", "Open")
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser1);
					
					home.clickAll()
						.clickFunnelWithoutReport()
						.addFirstFilterByEnterAndChooseWithReport("Assigned To", "is not", regUser1)
						.clickANDCondition()
						.addSecondFilterbyEnterandChoose("Assignment Group", "is not", "TESM_INC_GROUP2")
						.clickANDCondition()
						.addThirdFilter("State", "is", "Open")
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser1);	
					home.clickAll()
						.clickFunnelWithoutReport()
						.addFirstFilterWithReport("State", "is", "Work in Progress")
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assigned To", "is not", regUser1)
						.clickANDCondition()
						.addThirdFilter("State", "is", "Open")
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser1);
					home.clickAll()
						.clickFunnelWithoutReport()
						.addFirstFilterWithReport("State", "is", "On Hold")
						.clickANDCondition()
						.addSecondFilterbyEnterandChooseWithReport("Assigned To", "is not", regUser1)
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser1);
					home.clickResolved()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addThirdFilterbyEnterAndChooseWithReport("Assigned To", "is not", regUser1)
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser1);
					home.clickClosed()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addThirdFilterbyEnterAndChooseWithReport("Assigned To", "is not", regUser1)
						.clickRun()
						.clickFirstIncident()
						.IsNotTakeTicketAvailable(regUser1);
					
					status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM39")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM39");
		return arrayObject;
	}





}



