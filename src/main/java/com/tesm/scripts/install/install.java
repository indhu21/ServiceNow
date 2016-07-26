package com.tesm.scripts.install;

import org.testng.annotations.Test;

import pages.LoginPage_knowlwge16;
import pages.MenuPage_knowlwge16;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.Reporter;
import utils.Reporter_ServiceNow;

public class install extends SuiteMethods_ServiceNowFrontEnd {
	
	@Test
	public void install () throws Exception {

		try {
			if(snW.launchApp_ServiceNow(browserName, true,entityId))
				Reporter_ServiceNow.reportStep("Application is Launched Successfully", "SUCCESS");
				else
				Reporter_ServiceNow.reportStep("Application could not be Launched", "FAILURE");

				status="PASS";

		} finally {
			//		snW.logout();
			snW.quitBrowser();
		}

	}

}
