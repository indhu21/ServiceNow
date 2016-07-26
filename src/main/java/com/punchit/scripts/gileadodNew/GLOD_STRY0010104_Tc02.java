package com.punchit.scripts.gileadodNew;

import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AlertPage;
import pages.AlertProfilePage;
import pages.CIScopePage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010104_Tc02 extends SuiteMethods {

	@Test(dataProvider = "GLOD_STRY0010104_Tc02",groups="OpsDirector")
	public void createCIScope(	String regUser, String regPwd, String triggervalue,
								String Severity, String autoClose, String name,
								String dyAssGrp, String inAssGrp, String ownGrp,
								String reactionType, String desc, String verUser, 
								String verPwd){


		try {

			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
			
			AlertProfilePage profilepage
				=home.clickAlertProfileRegistration()
					 .verifyDefaultValues(triggervalue, Severity, autoClose)
					 .profileCreationWithDygrpYes(name, dyAssGrp, inAssGrp, ownGrp, reactionType, desc);
						
			String profilename = 
						profilepage.getProfileName();
				
						home.clickLogout();
				
					new LoginPage()
					.loginAs(verUser, verPwd);
				home.clickAlertProfiles()
					.searchAndclickProfile(profilename)
					.shouldUpdateButtonAvailable();
					
				home.clickLogout();
		
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLOD_STRY0010104_Tc02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010104_Tc02");
		return arrayObject;
	}
}
