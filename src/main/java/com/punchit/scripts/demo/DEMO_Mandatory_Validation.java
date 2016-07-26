package com.punchit.scripts.demo;



import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import utils.DataInputProvider;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class DEMO_Mandatory_Validation extends SuiteMethods_1{


	@Test(dataProvider = "TesmDemo_TC01")
	public void createAssets(String regUser, String regPwd, String filterData) {

		snW = new ServiceNowWrappers(entityId);

		try {

			if(snW.launchApp(browserName, true))
				Reporter1.reportStep("The User: "+regUser+" is  able to launch the application successfully", "SUCCESS");
			else
				Reporter1.reportStep("The User: "+regUser+" is not able to launch the application successfully", "FAILURE");
			
			if (snW.login(regUser, regPwd))
				Reporter1.reportStep("The login with username: "+ regUser + " is successful", "SUCCESS");
			else
				Reporter1.reportStep("The login with username: "+ regUser + " is not successful", "FAILURE");
 
			if (snW.selectMenuFromMainHeader("Incident", "Create_New"))
				Reporter1.reportStep("The Create New - menu selected successfully","SUCCESS");
			else
				Reporter1.reportStep("The Create New - menu could not be selected","FAILURE");

			// Switch to the main frame
			//snW.switchToFrame("Frame_Main");
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			String incnum = snW.getAttributeByXpath("CREATEINC_ConfigItemStar_Xpath", "mandatory");
			System.out.println("Value is "+incnum);
			if(incnum.equalsIgnoreCase("true"))
			       Reporter1.reportStep("The Configuration Item field is mandatory","SUCCESS");
			else
			       Reporter1.reportStep("Configuration Item field is not mandatory","FAILURE");	
			
//			String[] mandatoryFields = {"CREATEINC_AsgGroupStar_Xpath",
//					"CREATEINC_ConfigItemStar_Xpath",
//			"CREATEINC_AssignedToStar_Xpath"};
//
//			String[] mandatoryFieldsDesc = {"Assignment Group",
//					"Configuration Item",
//			"Assign To"};
//
//			snW.verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);

			snW.Wait(5000);
			
			status = "PASS";

		}		
		finally {
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "TesmDemo_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("TesmDemo_TC01");
		return arrayObject;
	}
}		

