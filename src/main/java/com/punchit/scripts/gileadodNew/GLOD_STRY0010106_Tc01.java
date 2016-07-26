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

public class GLOD_STRY0010106_Tc01 extends SuiteMethods {

	@Test(dataProvider = "GLOD_STRY0010106_Tc01",groups="OpsDirector")
	public void createCIScope(	String regUser, String regPwd,String name,
								String shortDescription, String filter, String owningGroup,
								String fSection, String fCondition, String fValue, String ciValue, 
								String verUser,String verPwd){


		try {

			snW.launchApp(browserName, true);

			MenuPage home =
					new LoginPage()
					.loginAs(regUser, regPwd);
			
					CIScopePage ciScope =
					home.clickCIScope()
						.enterAllFieldsWithoutSubmit(name, shortDescription, filter, owningGroup)
						.clickSubmit();
					String scopeNum = 
							ciScope.getCINumber();
				ciScope.selectFilterwithClass(fSection, fCondition, fValue, ciValue)
						.clickUpdate()
						.verifyCIPresent();
				home.clickLogout();
				
				new LoginPage()
					.loginAs(verUser, verPwd);
				
				home.clickCIScopes()
					.searchAndclickScopeNumber(scopeNum)
					.clickDeleteButton();
					
		
			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLOD_STRY0010106_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010106_Tc01");
		return arrayObject;
	}
}
