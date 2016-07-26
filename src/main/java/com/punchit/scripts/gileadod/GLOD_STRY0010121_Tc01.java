package com.punchit.scripts.gileadod;

import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AlertPage;
import pages.CIScopePage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010121_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_Stry0010121_Tc01",groups="OpsDirector")
	public void createCIScope(	String regUser, String regPwd, String name,
								String shortDescription, String filter, String owningGroup,
								String fSection, String fCondition, String fValue,
								String verUser, String verPwd){


		try {

			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
			CIScopePage	cipage = 
				home.clickCIScope()
					.enterAllFields(name, shortDescription, filter, owningGroup)
					.selectFilterandSave(fSection, fCondition, fValue);
						
			String scopeNum = 
					cipage.getCINumber();
				home.clickLogout();
				
					new LoginPage()
					.loginAs(verUser, regPwd);
				home.clickCIScopes()
					.addFirstFilterInput1("Number", "is", scopeNum)
					.clickOnLink(scopeNum)
					.verifyUpdateButtonAvl()
					.clickBackButton()
					.rightClickIsSaveAvl();
					
				home.clickLogout();
		
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLOD_Stry0010121_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry0010121_Tc01");
		return arrayObject;
	}
}
