package com.punchit.scripts.lc;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class LC_RP_Stry000000_Tc02_3 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_RP_Stry000000_Tc02_3",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd, String filterType1, String filterCondition1, 
								String filterValue1, String filterType2, String filterCondition2, String filterValue2,
								String filterType3, String filterCondition3, String filterValue3, String filterType4,
								String filterCondition4, String filterValue4,String manufacturer1, String text, 
								String manufacturer2, String ciName){

		// Pre-requisities
		try {

			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Login to the application
			MenuPage home = 
				new LoginSparcLCPage()
					.loginAs(regUser, regPwd);	

		SystemApplicationsPage sa=
				home.clickSystemApplications()
//					.addFilterforFourValues(filterType1,  filterCondition1, filterValue1, filterType2, filterCondition2, filterValue2, 
//							filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4)
//					.clickFirstNamelink()
					.searchandClickSyAppName(ciName)
					.verifyAllReadOnlyFields()
					.isDeletButtonNotPresent(regUser.toUpperCase())
					.enterandChooseManufacturer(manufacturer1)
					.clickSave();
//					.getBuildConfirmation(text);
		String CMDBNum = 
					sa.getCMDBTaskNumberforRP();
		String appName	=	
				sa.getCMDBName();

				sa.clickLinkName(CMDBNum)
				  .verifyCMDBTaskInformation(CMDBNum);
			
				home.clickSystemApplications()
					.searchandClickFirstnameforSyApp(appName)
					.enterandChooseManufacturer(manufacturer2)
					.clickSave()
					.verifySecSystemError();
	
		home.clickSparcLCPageLogout();
				status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_RP_Stry000000_Tc02_3")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_RP_Stry000000_Tc02_3");
		return arrayObject;
	}
}
