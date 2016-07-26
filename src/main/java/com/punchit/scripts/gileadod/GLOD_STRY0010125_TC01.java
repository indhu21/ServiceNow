package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertProfilePage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010125_TC01 extends SuiteMethods {
	
	@Test(dataProvider = "GLOD_STRY0010125_TC01",groups="OpsDirector")
	public void ModifyCI(String regUser, String regPwd, String name, String dyAssGrp, 
						 String inAssGrp, String ownGrp, String reactionType, String desc){

	
		try {
			
			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
					
			AlertProfilePage profile = 
					home.clickAlertProfileRegistrationWithoutReport()
					.profileCreationWithoutReport(name, dyAssGrp, inAssGrp, ownGrp, reactionType, desc);
				
				String prfName = 
						profile.getProfileName();
			
						home.clickAlertProfiles()
							.searchAndclickSameProfile(prfName)
							.selectCI()
							.clickUpdate();
						
						home.clickLogout();
						
						
						status = "PASS";



		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLOD_STRY0010125_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010125_TC01");
		return arrayObject;
	}

}
