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
import wrapper.ServiceNowWrappers;

public class GLIM_V2_Stry000000_Tc02 extends SuiteMethods	 {



	@Test(dataProvider = "GLIM_V2_Stry000000_Tc02",groups="Incident")
	public void IncDiffererentPrioritiesCheck(	String regUser, String regPwd, String Impact1, String Urgency1,
								String Priority1, String Impact2, String Urgency2,
								String Priority2, String Impact3, String Urgency3,
								String Priority3, String Impact4, String Urgency4,
								String Priority4, String Impact5, String Urgency5,
								String Priority5, String Impact6, String Urgency6,
								String Priority6, String Impact7, String Urgency7,
								String Priority7, String Impact8, String Urgency8,
								String Priority8, String Impact9, String Urgency9,
								String Priority9, String configItem, String repCust,
								String asgGroup, String desc){

		
		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
					
				IncidentPage incident = 
						home.clickCreateNew();
//				String incNum = 
//					incident.getIncidentNumber();
			incident/*.createIncidentAndOpenWithoutReport(configItem, repCust, asgGroup, desc, incNum)*/
					.clickPriority()
					.verifyPriority(Impact1, Urgency1, Priority1)
					.verifyPriority(Impact2, Urgency2, Priority2)
					.verifyPriorityAndAcceptAlert(Impact3, Urgency3, Priority3)
					.verifyPriority(Impact4, Urgency4, Priority4)
					.verifyPriority(Impact5, Urgency5, Priority5)
					.verifyPriority(Impact6, Urgency6, Priority6)
					.verifyPriority(Impact7, Urgency7, Priority7)
					.verifyPriority(Impact8, Urgency8, Priority8)
					.verifyPriority(Impact9, Urgency9, Priority9);
//					.clickSaveButton();
					
				home.clickLogoutWithAlertAccept();
					
					
				status = "PASS";

		}
		finally{
			// close the browser
			snW.quitBrowser();		
		}
	}


	@DataProvider(name = "GLIM_V2_Stry000000_Tc02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_Tc02");
		return arrayObject;

	}
}



