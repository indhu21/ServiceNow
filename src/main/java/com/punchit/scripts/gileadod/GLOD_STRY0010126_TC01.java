package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertProfilePage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010126_TC01 extends SuiteMethods{

	
	
	@Test(dataProvider = "GLOD_STRY0010126_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd,String name, String dyAssGrp, String inAssGrp,
							  String ownGrp, String reactionType, String desc, String label, String User2, 
							  String Pwd2){
		
		
		try {
			label=label+getCurrentTime();
			
//			label=label+getCurrentTime();
			
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
							.clickAlertReactionTab()
							.selectReactionType()
							.doubleClickAlertReaction()
							.enterlable(label)
							.clickAlertReactionDefault()
							.clickUpdate();
						home.clickLogout();
							
						new LoginPage()
						.loginAs(User2, Pwd2);
					home.clickAlertProfileRegistration()
						.verifyReactionType(label);
					home.clickLogout();	
						
						
			
		
        status="PASS";
		
		} 
		finally{
			// close the browser
			snW.quitBrowser();
			
		}
	}
		@DataProvider(name = "GLOD_STRY0010126_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010126_TC01");
			return arrayObject;
		}
}
