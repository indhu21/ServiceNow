package com.punchit.scripts.gileadodNew;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLOD_STRY0010146_Tc01 extends SuiteMethods {

	@Test(dataProvider="GLOD_STRY0010146_Tc01",groups="OpsDirector")

	public void assignAlert(String regUser, String regPwd, String name , String dyAssGrp, String inAssGrp,String ownGrp 
			,String reactionType,String desc,String state ) {
		try {

			snW.launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd)
					.clickAlertProfileRegistrationWithoutReport()
					.profileCreationWithDygrpNo(name, dyAssGrp, inAssGrp, ownGrp, reactionType, desc)
					.verifyState(state);
			snW.switchToMain();
			String alertProNumber = getAttributeById("ALERTPROFILE_alertProfileNumber_Id", "value");
			System.out.println(alertProNumber);
			
			home.clickAlertProfiles()
					.searchAndclickProfile(alertProNumber);


			status = "PASS";

		} finally{

			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name="GLOD_STRY0010146_Tc01")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010146_Tc01");
		return arrayObject;
	}	
}
