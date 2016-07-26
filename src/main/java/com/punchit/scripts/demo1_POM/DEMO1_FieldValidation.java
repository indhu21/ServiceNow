package com.punchit.scripts.demo1_POM;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import demo.LoginPage_ServiceNowDemo;
import demo.MenuPage_ServiceNowDemo;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class DEMO1_FieldValidation extends SuiteMethods{
	

	@Test(dataProvider = "DEMO1_STRY0000002_TC01",groups="DemoIncident")
	public void fieldValidation (String regUser, String regPwd){
		
		
		try {
			
			launchApp(browserName, true);

			MenuPage_ServiceNowDemo home = 
							new LoginPage_ServiceNowDemo()
							.loginAs(regUser, regPwd);
	
					home.clickCreateNew()
						.verifyAllReadOnlyFieldsForDemo()
						.verifyAllMandatoryFieldsforDemo();
//						.verifyAllMandatoryFieldsforDemo1();
					
					home.clickLogout();
			
					status = "PASS";
			
		} finally {
			snW.switchToDefault();
			if(snW.isExistByXpath("Logout_Xpath"))
				{if (!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The Log out could not be clicked","FAILURE");}
			snW.quitBrowser();
			}

}

	@DataProvider(name = "DEMO1_STRY0000002_TC01")
	public Object[][] fetchData() throws IOException{
		Object[][] arrayObject = DataInputProvider.getSheet("DEMO1_STRY0000002_TC01");
		return arrayObject;
	}
}