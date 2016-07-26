package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CIScopePage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0010121_TC02_V1 extends SuiteMethods {
	
		@Test(dataProvider = "GLOD_STRY0010121_TC02_V1",groups="OpsDirector")
		public void createCIScope(String regUser, String regPwd, String name,
				String shortDescription, String filter, String owningGroup,
				String Application,String f2Section, String f2Condition, String f2Value,
				String verUser, String verPwd, String CI_Class){

			try {

				snW.launchApp(browserName, true);

				MenuPage home =
						new LoginPage()
						.loginAs(regUser, regPwd);
				CIScopePage	cipage = 
					home.clickCIScope()
						.enterAllFields(name, shortDescription, filter, owningGroup)
						.selectFilterandSave(f2Section, f2Condition, f2Value);
							
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


		@DataProvider(name = "GLOD_STRY0010121_TC02_V1")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010121_TC02_V1");
			return arrayObject;
		}
	}

