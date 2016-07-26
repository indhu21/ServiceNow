package com.punchit.scripts.OOBIncident;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.sun.org.apache.xerces.internal.util.Status;


import pages_OutOfBox.IncidentPage;
import pages_OutOfBox.LoginPage;
import pages_OutOfBox.MenuPage;
import testng.SuiteMethods_ServiceNowFrontEnd;
import utils.DataInputProvider;

public class OOBIncident_Stry000000_TC05 extends SuiteMethods_ServiceNowFrontEnd{
	
	@Test(dataProvider = "OOBIncident_Stry000000_TC05", groups="OutOfBox")
	public void createIncident(	String regUser, String regPwd, String category, String caller,
								String subCategory, String ciValue, String contactType,
								String state, String assGroup, String assignTo, String impact,
								String urgency, String priority, String shortDes){
		
				snW.launchApp(browserName, true);
					new LoginPage()
					.loginAs(regUser, regPwd)
					.createIncident(caller, category, subCategory, ciValue, contactType, 
							state, assGroup, assignTo, impact, urgency, shortDes, 
							regUser, priority);
//					.clickLogout();
					status="Pass";
	}
	
	@DataProvider(name = "OOBIncident_Stry000000_TC05")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("OOBIncident_Stry000000_TC05");
		return arrayObject;
	}
}
