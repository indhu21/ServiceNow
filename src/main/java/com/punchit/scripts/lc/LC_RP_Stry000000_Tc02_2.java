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

public class LC_RP_Stry000000_Tc02_2 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "LC_RP_Stry000000_Tc02_2",groups="IncidentManagement")
	public void createIncident(	String regUser, String regPwd,String filterType1, String filterCondition1,
								String filterValue1,String filterType2, String filterCondition2,
								String filterValue2,String filterType3, String filterCondition3,
								String filterValue3,String filterType4, String filterCondition4,
								String filterValue4, String gxp,String sox, String environment, 
								String location, String businessCriticality, String text, String Gxp1, String ciName){

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
//						.addFilterforFourValues(filterType1, filterCondition1, filterValue1,
//								filterType2, filterCondition2, filterValue2, filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4)
//						.clickFirstNamelink()
						.searchandClickSyAppName(ciName)
						.sysAppPageValues(gxp, sox, environment, location, businessCriticality)
//						.clickSetBuild()
						.clickSetActive()
//						.getBuildConfirmation(text)
						.selectGxP(Gxp1)
						.clickSave()
						.verifyErrorMessage();
					home.clickSparcLCPageLogout();
									
				status = "PASS";

		} finally {
			// close the browser
			//snW.quitBrowser();
		}

	}

	@DataProvider(name = "LC_RP_Stry000000_Tc02_2")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("LC_RP_Stry000000_Tc02_2");
		return arrayObject;
	}
}
