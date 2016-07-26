package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLOD_STRY0011432_TC01_V1 extends SuiteMethods {

				@Test(dataProvider = "GLOD_STRY0011432_TC01_V1",groups="OpsDirector")
				public void profreg (String regUser, String regPwd, String tcon, String sevded, String auto, 
						String name, String dyAssGrp, String inAssGrp, String ownGrp,
						String reactionType, String desc){
					
					try {

						snW.launchApp(browserName, true);
						
						MenuPage home = 
									new LoginPage()
									.loginAs(regUser, regPwd);
								home.clickAlertProfileRegistration()	
									.verifyDefaultValues(tcon, sevded, auto)	
									.verifyMandatoryfields()
									.profileCreationWithDygrpNo(name, dyAssGrp, inAssGrp, ownGrp, reactionType, desc);
								home.clickLogout();	
										
						status = "PASS";
					
					} finally {
						// close the browser
						snW.quitBrowser();
					}

				}
				
				@DataProvider(name = "GLOD_STRY0011432_TC01_V1")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0011432_TC01_V1");
					return arrayObject;
				}
}
