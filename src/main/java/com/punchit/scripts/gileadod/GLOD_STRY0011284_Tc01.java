package com.punchit.scripts.gileadod;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLOD_STRY0011284_Tc01 extends SuiteMethods{
	


	@Test(dataProvider = "GLOD_Stry0011284_Tc01",groups="OpsDirector")
		public void alertCorrelation(String regUser, String regPwd, String name, 
									 String ownGrp, String inAssGrp, String desc, String severity,
									 String impact, String reactionType1) {



try {
	String[] groupBy = {"-- None --","Location","CI", "Application","Business Service"};
	String[] startCond = {"-- None --","Any","All"};
	
		snW.launchApp(browserName, true);

			MenuPage home = 
						new LoginPage()
						.loginAs(regUser, regPwd);
	
					home.clickCorrelatedProfile()
						.enterMandatoryFields(name, severity, inAssGrp, ownGrp, desc, impact)
						.clickSubmit()
						.verifyGroupByList(groupBy)
						.verifyStartCond(startCond)
						.selectReactionType(reactionType1)
						.selectGroupValues()
						.clickInsertedNewRowStartCondition()
						.clickInsertedNewRowforStopCondtion()
						.clickInsertedNewRowforGroupedCIs();
						
	
		status = "PASS";


}

finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLOD_Stry0011284_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry0011284_Tc01");
		return arrayObject;
	}
}



