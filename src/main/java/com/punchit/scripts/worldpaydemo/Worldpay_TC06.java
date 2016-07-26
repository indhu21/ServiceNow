package com.punchit.scripts.worldpaydemo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Worldpay_TC06 extends SuiteMethods{


	@Test(dataProvider = "WorldpayTC06",groups="Worldpay")
	public void createAssets(String regUser, String regPwd, String usr, String filterData, String dName,
							String manufacturer, String modelcategories) {

		snW = new ServiceNowWrappers(entityId);


		try {

			snW.launchApp(browserName, false);
			
			if (!snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username: "+ regUser + " is not successful", "FAILURE");
 
			if(snW.selectUser(usr))
				Reporter.reportStep("The login with username: "+ usr + " is successful", "SUCCESS");

			snW.switchToMenu();
			
			if(!snW.enterByXpath("Filter_Xpath", filterData))
				Reporter.reportStep("The Data "+filterData+" is not entered in Filter Box, hence failure.", "FAILURE");

			if(snW.isExistByLinkText("Hardwaremodels_Link", true))
				Reporter.reportStep("The Hardware models subsection is located as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Hardware Models Menu is not available, hence failure.", "FAILURE");
			
			if(!snW.clickLink("Hardwaremodels_Link", true))
				Reporter.reportStep("The Hardware Models link is not available, hence failure.", "FAILURE");
				
			snW.switchToMain();
			
			if(snW.isExistByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The Hardware Models link clicked and The Hardware Models list appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The All the Hardware Models list not visible, hence failure.", "FAILURE");

			if(snW.clickByXpath("NewButton_Xpath"))
				Reporter.reportStep("The New Button is clicked and new Hardware Models form appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The All the Hardware Models list not visible, hence failure.", "FAILURE");
	
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			String Name= dName+dateFormat.format(date);
			System.out.println(Name);
			if(!snW.enterAndChoose("Hardware_Name_Xpath", Name))
				Reporter.reportStep("The Value "+dName+" is not entered in Display name field, hence failure.", "FAILURE");

			if(!snW.enterAndChoose("Hardware_Manufacturer_Xpath", manufacturer))
				Reporter.reportStep("The Value "+manufacturer+" is not entered in manufacturer field, hence failure.", "FAILURE");

			if(!snW.clickByXpath("Hardware_Unlock_Xpath"))
				Reporter.reportStep("The Value "+manufacturer+" is not entered in manufacturer field, hence failure.", "FAILURE");

			if(snW.enterAndChoose("Hardware_category_Xpath", modelcategories))
				Reporter.reportStep("All the mandatory fields are filled with respective fields and Submit button is clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("The Value "+modelcategories+" is not entered in Display name field, hence failure.", "FAILURE");

			if(!snW.clickByXpath("Hardware_Submit_Xpath"))
				Reporter.reportStep("The submit buttons is not clicked or not found, hence failure.", "FAILURE");

			snW.Wait(2000);
	/*		if(!snW.selectByVisibleTextByXpath("HardwareModel_SelectNumberDropDown_Xpath", "Name"))
				Reporter.reportStep("The name value could not be selected from the search drop down, hence failure.", "FAILURE");
	*/		
			if(!snW.enterByXpathAndClick("Hardware_Search_Xpath",Name))
				Reporter.reportStep("The value: "+Name+" is not entered, hence failure.", "FAILURE");
			
	//		if(snW.isExistByLinkText(dName, false))
			if(snW.isExistByXpath("Computer_Firstlink_Xpath"))
				Reporter.reportStep("Submit button clicked successfully and the Created Hardware Model is displayed"
						+ " as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Created Hardware Model not is displayed, hence failue.", "FAILURE");

			if(!snW.clickByXpath("HardwareModel_Checkbox_Xpath"))
				Reporter.reportStep("The Checkbox could not be selected, hence failue.", "FAILURE");

			if(!snW.selectByVisibleTextByXpath("HardwareModel_SelectNumberDropDown_Xpath", "   Delete"))
				Reporter.reportStep("The delete button is disabled hence could not be selected,.", "FAILURE");
			
			if(snW.clickByXpath("StockRoom_OkButton_Xpath"))
				Reporter.reportStep("The First Threshold link check Box is clicked and Delete Option is selected successfully.", "SUCCESS");
			else
				Reporter.reportStep("The First Threshold link check Box is not selected under Stockroom thresholds, hence failure.", "FAILURE");
			
			status = "PASS";

		}		
		finally {
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "WorldpayTC06")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("WorldpayTC06");
		return arrayObject;
	}
}		

