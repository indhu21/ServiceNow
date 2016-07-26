package com.punchit.scripts.gileadod;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AlertsListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;
 
public class GLOD_STRY0010130_Tc01 extends SuiteMethods {
	
	@Test(dataProvider = "GLOD_Stry_0010130_Tc01",groups="OpsDirector")
		public void appProperties(String regUser, String regPwd){
		
		
		try {
			
			snW.launchApp(browserName, true);
			
			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
					
				AlertsListPage list = 
						home.clickOpsAlertConsole()
							.verifyData();
				
				String child =
						list.getParentAlert();
				
				String parent = 
						list.getParentAlert();
				
				    list.clickNewAlert()
					    .rightclickAndLinktoParent(parent, child)
						.verifyChildCountInListView(parent)
						.verifyChildAlert(child, parent);
	            
					home.clickLogout();
					
					status="PASS";
			
		
		}
		finally{
			// close the browser
			snW.quitBrowser();
			
		}
}
	@DataProvider(name = "GLOD_Stry_0010130_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry_0010130_Tc01");
		return arrayObject;
	}
}

