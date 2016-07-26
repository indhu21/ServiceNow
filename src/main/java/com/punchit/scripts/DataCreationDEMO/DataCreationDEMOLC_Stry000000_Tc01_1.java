package com.punchit.scripts.DataCreationDEMO;

import java.io.IOException;

import org.apache.http.HttpException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.SystemApplicationsPage;
import testng.SuiteMethods;
import utils_DataCreation.DataInputProvide_MySQL;
import utils.DataInputProvider;
import utils_DataCreation.CreateAutoData;
import utils_DataCreation.ModifyJSON;

public class DataCreationDEMOLC_Stry000000_Tc01_1 extends SuiteMethods {

	// Create Instance

	@Test(dataProvider = "DataCreationDEMOLC_Stry000000_Tc01_1",groups="IncidentManagement")
	public void createIncident(	String User_ID, String Password, String filterType1,String filterCondition1,
								String filterValue1,String filterType2,String filterCondition2,String filterValue2,
								String filterType3,String filterCondition3,String filterValue3, String filterType4,
								String filterCondition4,String filterValue4,String gxp,String sox,
								String environment, String location, String businessCriticality,
								String text, String systemManager, String operationalstatus) throws IOException, HttpException{

		// Pre-requisities
		try {
			String ciName=""; 
//			ciName=CreateAutoData.CreateCI("DataCreationDEMOLC_Stry000000_Tc01_1");
			//String 
			//System.out.println("CI name is "+ciName);
			// Step 0: Launch the application
      		snW.launchApp(browserName, true);

			// Step 1: Login to the application
      		MenuPage home = 
    				new LoginSparcLCPage()
    					.loginAs(User_ID, Password);	


    			// Step 2: Verify the Menus
    			SystemApplicationsPage sa =
    					home.clickSystemApplications()
//    						.addFilterforFourValues(filterType1,  filterCondition1, filterValue1, filterType2, filterCondition2, filterValue2, 
//    								filterType3, filterCondition3, filterValue3, filterType4, filterCondition4, filterValue4)
//    						.clickFirstNamelink()
    						.searchandClickSyAppName(ciName)
    						.sysAppPageValues(gxp, sox, environment, location, businessCriticality)
    						.clickSetBuild()
    						.getBuildConfirmation(text);
    						
    			String CMDBNum =
    					sa.getCMDBTaskNumber();
    					home.clickSparcLCPageLogout();
    					
    				new LoginSparcLCPage()
    					.loginAs(systemManager, Password)
    					.expandCMDBControl()
    					.clickMyCMDBApprovals()
    					.searchCMDBandSelectforSysApp(CMDBNum)
    					.clickApprove()
    					.clickLinkName(ciName)	
    					.verifyOperationalstatus(operationalstatus)
    					.verifyEnteredFileds(businessCriticality, sox,gxp, location, environment);

    					status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "DataCreationDEMOLC_Stry000000_Tc01_1")
	public Object[][] fetchData() throws IOException {
		String[] Parameters={"User_ID","Password","Filter type 1","Filter Condition 1",
				 "Filter Value 1","Filter type 2","Filter Condition 2","Filter Value 2",
				 "Filter type 3","Filter Condition 3","Filter Value 3","Filter type 4",
				 "Filter Condition 4","Filter Value 4","Gxp","Sox",
				 "Environment","Location","Business Criticality",
				 "Text","SystemManager","Operationalstatus"};
		Object[][] arrayObject = DataInputProvide_MySQL.getDataValues("DataCreationDEMOLC_Stry000000_Tc01_1",Parameters);
		return arrayObject;
	}
}
