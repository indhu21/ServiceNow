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

public class GLOD_STRY0010121_TC01_V1 extends SuiteMethods {
	// Create Instance
				ServiceNowWrappers snW;

				@Test(dataProvider = "GLOD_STRY0010121_TC01_V1",groups="OpsDirector")
				public void createCIScope(String regUser, String regPwd, String name,
						String shortDescription, String filter, String owningGroup,
						String f2Section, String f2Condition, String f2Value,
						String verUser, String verPwd, String CI_Class) throws COSVisitorException,
						IOException {

					// Pre-requisities
					snW = new ServiceNowWrappers(entityId);

					try {

						snW.launchApp(browserName, true);

						MenuPage home =
								new LoginPage()
								.loginAs(regUser, regPwd);
						
							home.clickCIScope()
								.enterNameAndShortDesc(name, shortDescription)
								.selectCIScopeType(filter)
								.enterOwmingGrop(owningGroup)
								.clickSubmit()
								.selectFilter(f2Section, f2Condition, f2Value)
								.clickUpdate();
						home.clickLogout();
						
						
						status = "PASS";

					} finally {
						// close the browser
						snW.quitBrowser();
					}

				}


				@DataProvider(name = "GLOD_STRY0010121_TC01_V1")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0010121_TC01_V1");
					return arrayObject;
				}
			}



