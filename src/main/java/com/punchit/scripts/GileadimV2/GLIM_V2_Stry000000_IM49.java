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

public class GLIM_V2_Stry000000_IM49 extends SuiteMethods {

	@Test(dataProvider = "GLIM_V2_Stry000000_IM49",groups="IncidentManagement")

	public void createIncident(String regUser, String regPwd,
			String regUser2,String regPwd2){
	try {
			
		
			createIncident(regUser, regPwd);
			createIncident(regUser2, regPwd2);
			
			status = "PASS";

		} finally {

			
		}

	}

	public void createIncident( String regUser1, String regPwd1){

		try {
			launchApp(browserName, true);

			SPARCPortalPage home=	
					new LoginPage()
					.loginAs(regUser1, regPwd1)
					.clickSPARCPortal();
			home.clickMyWork()
				.clickMyWorkGroupTab()
				.clickFirstIncident()
				.myWorkGroupTabFrame()
				.rightClickAdditionalActions()
				.selectFromAddOptionclickView()
				.selectDefaultView()
				.enterAssigned(regUser1)
				.clickWIP()
				.rightClickAdditionalActions()
				.selectFromAddOptionclickView()
				.selectDefaultView()
				.isReSolvedButtonDisplayed("Default View")
				.rightClickAdditionalActions()
				.selectFromAddOptionclickView()
				.selectMetricsView()
				.isNotResolvedButtonDisplayed("Metrics View")
				.rightClickAdditionalActions()
				.selectFromAddOptionclickView()
				.selectMobileView()
				.isNotResolvedButtonDisplayed("Mobile View")
				.rightClickAdditionalActions()
				.selectFromAddOptionclickView()
				.selectPasswordView()
				.isNotResolvedButtonDisplayed("Password View")
				.rightClickAdditionalActions()
				.selectFromAddOptionclickView()
				.selectSelfServiceView()
				.isNotResolvedButtonDisplayed("Self Service View")
				.rightClickAdditionalActions()
				.selectFromAddOptionclickView()
				.selectVTBView()
				.isNotResolvedButtonDisplayed("VTB View")
				.rightClickAdditionalActions()
				.selectFromAddOptionclickViewwithoutReport()
				.selectDefaultViewwithoutReport();
			home.clickSPARCHomeMenu()
				.clickLogout();

			quitBrowser();

		} finally {
		
		}

	}

	@DataProvider(name = "GLIM_V2_Stry000000_IM49")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM49");
		return arrayObject;
	}





}



