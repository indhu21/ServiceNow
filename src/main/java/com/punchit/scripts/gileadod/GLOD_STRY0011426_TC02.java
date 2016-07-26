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

public class GLOD_STRY0011426_TC02  extends SuiteMethods{

	@Test(dataProvider = "GLOD_STRY0011426_TC02",groups="OpsDirector")
	public void createCIScope(	String regUser, String regPwd, String name,
								String shortDescription, String filter, String owningGroup,
								String f2Section, String f2Condition, String f2Value,
								String CI_Class) {

		try {

						String[] expectedValues={"ESX Server", "Linux Server"};
						String[] expectedValues1={"ESX Server"};
				
						snW.launchApp(browserName, true);
				
						MenuPage home = 
								new LoginPage()
								.loginAs(regUser, regPwd);
						CIScopePage	cipage =
								home.clickCIScope()
								.enterAllFields(name, shortDescription, filter, owningGroup);
						String cinum = 
								cipage.getCINumber();
						cipage.selectCIClassAndClickUpdate(CI_Class)
//						.addClassColumn("Class")
						.verifyValues(expectedValues)
						.addNewFilterinAlertUsingSelect("Class", "is", "ESX Server")
						.clickUpdate()
						.verifyValues(expectedValues1);
						home.clickCIScopes()
						.searchAndclickScopeNumber(cinum)
						.verifyValues(expectedValues1);
						
						status = "PASS";


	} finally {
		// close the browser
		snW.quitBrowser();
	}

}


@DataProvider(name = "GLOD_STRY0011426_TC02")
public Object[][] fetchData() throws IOException {
	Object[][] arrayObject = DataInputProvider.getSheet("GLOD_STRY0011426_TC02");
	return arrayObject;
}
}

