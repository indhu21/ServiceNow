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

public class GLOD_STRY0011172_TC01 extends SuiteMethods {

			@Test(dataProvider = "GLOD_STRY0011172_TC01",groups="OpsDirector")
			public void Configure_Business_CI_Scope(String regUser, String regPwd, String name, String shortDescription, 
													String Type, String Group,String CI_Class, String CI_Filter, String CI_Service, 
													String Target_CI, String User2, String Pwd2,String F1field, String F1Operator,
													String F1Value) {

				try {

					String[] value={"Linux Server"};
					
					snW.launchApp(browserName, true);

					MenuPage home = 
								new LoginPage()
								.loginAs(regUser, regPwd);
						CIScopePage ci=	
						home.clickCIScope()
								.enterAllFieldsWithoutSubmit(name, shortDescription, Type, Group)
								.clickSubmit()
								.selectCIScopeType(CI_Class, CI_Filter)
								.selectBusinessService(CI_Service)
								.clickUpdate()
								.addClassColumn("Class")
								.verifyValues1(value);
							String ciScopNum=ci.getCINumber();	
								
							home.clickLogout();	
								new LoginPage()
								.loginAs(User2, Pwd2);
							home.clickCIScopes()
								.searchAndclickScopeNumber(ciScopNum)
								.verifyBusinessServiceReadOnly(User2);
						
							status = "PASS";
					
			} 
				finally {
					// close the browser
					snW.quitBrowser();
				}	
			}

				@DataProvider(name = "GLOD_STRY0011172_TC01")
				public Object[][] fetchData() throws IOException {
					Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0011172_TC01");
					return arrayObject;
				}
	}
