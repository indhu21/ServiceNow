package com.punchit.scripts.ServiceNowGLIM;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages_ServiceNow.IncidentPage;
import Pages_ServiceNow.LoginPage;
import Pages_ServiceNow.MenuPage;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class GLIM_Stry000000_Tc02 extends SuiteMethods_ServiceNowFrontEnd {



	@Test(dataProvider = "GLIM_Stry000000_Tc02",groups="Incident")
	public void appProperties(	String regUser, String regPwd, String Impact1, String Urgency1,
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
				String incNum = 
					incident.getIncidentNumber();
			incident.createIncidentAndOpenWithoutReport(configItem, repCust, asgGroup, desc, incNum)
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


	@DataProvider(name = "GLIM_Stry000000_Tc02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_Stry000000_Tc02");
		return arrayObject;

	}
}



