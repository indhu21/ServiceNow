package com.punchit.scripts.gileadod;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertProfilePage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010165_TC01 extends SuiteMethods{
	

	@Test(dataProvider = "GLOD_STRY0010165_TC01",groups="OpsDirector")
		public void alertProfileIncidentAssignmentGroup(String regUser, String regPwd, String alertProfileName, String alertProfileDescription, 
														String incidentAssignmentGroup, String owningGroup, String attribute, String autoClose,
														String dynamic){

try {

				snW.launchApp(browserName, true);
			
				MenuPage home =
						new LoginPage()
						.loginAs(regUser, regPwd);
						
				AlertProfilePage profile =
					home.clickAlertProfileRegistration()
					.enterNameDesAutoCISco(alertProfileName, alertProfileDescription, autoClose)
					.selectDynamic(dynamic)
					.selectIncandOwnGrp(owningGroup, incidentAssignmentGroup)
					.clickSubmit()
					.clickInsertedAlertConfigurations(attribute);
			String profileNum=
					profile.getProfileName();
				
				profile.clickUpdateButton();
				
				home.clickAlertProfiles()
					.searchAndclickProfile(profileNum)
					.clicksendForApp();
				home.clickLogout();
				
				status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLOD_STRY0010165_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010165_TC01");
		return arrayObject;
	}
}


