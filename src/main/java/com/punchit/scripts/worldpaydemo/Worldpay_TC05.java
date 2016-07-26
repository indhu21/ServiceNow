package com.punchit.scripts.worldpaydemo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Worldpay_TC05 extends SuiteMethods{


	@Test(dataProvider = "WorldpayTC05",groups="Worldpay")
	public void createAssets(String regUser, String regPwd, String usr, String filterData, 
							String model, String threshold, String stName, String stLocation, String stMana) {

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

			if(snW.isExistByLinkText("Stockrooms_Link", true))
				Reporter.reportStep("The Stockrooms subsection is located and Stockrooms related links are available as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Stockrooms Menu is not available, hence failure.", "FAILURE");

			if(!snW.clickLink("Stockrooms_Link", true))
				Reporter.reportStep("The Stockrooms link is not clicked, hence failure.", "FAILURE");
			snW.switchToMain();
			if(snW.isExistByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The Stockrooms link clicked and The Stockrooms list appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Stockrooms list not visible, hence failure.", "FAILURE");

			if(snW.clickByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The first link is clicked in Stockrooms list successfully.", "SUCCESS");
			else
				Reporter.reportStep("The first link is not clicked in Stockrooms list, hence failure.", "FAILURE");
            snW.Wait(5000);
			if(!snW.clickByXpath("StockroomThreshold_Xpath"))
				Reporter.reportStep("The Stockroom Thresholds Tab is not clicked, hence failure.", "FAILURE");
              
			if(snW.clickByXpath("StockRoom_ThresholdsNewButton_Xpath"))
				Reporter.reportStep("The Stockroom Thresholds link clicked and the new button is clicked successfully .", "SUCCESS");
			else
				Reporter.reportStep("The New Button is not clicked or not available, hence failure.", "FAILURE");

			if(!snW.enterAndChoose("StockRoom_ThresholdModel_Xpath", model))
				Reporter.reportStep("The value: "+model+" not is entered in Model field, hence failure.", "FAILURE");

			if(snW.enterByXpath("StockRoom_Thresholdvalue_Xpath", threshold))
				Reporter.reportStep("The values Model: "+model+", Threshold "+threshold+" is entered and submit button is clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("The values Model: "+model+" is entered successfully but the value: "+threshold+" could not be entered in Threshold field, hence failure.", "FAILURE");

			if(snW.clickByXpath("StockRoom_Submit_Xpath"))
				Reporter.reportStep("The submit button is clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("The Submit Button is not clicked or not found, hence failure.", "FAILURE");

			if(!snW.clickByXpath("StockroomThreshold_Xpath"))
				Reporter.reportStep("The Stockroom Thresholds Tab is not clicked, hence failure.", "FAILURE");

			if(snW.isExistByLinkText(model, false))
				Reporter.reportStep("A newly created threshold "+model+" is available under Stockroom thresholds tab as expected.", "SUCCESS");
			else
				Reporter.reportStep("A newly created threshold "+model+" is not available under Stockroom thresholds, hence failure.", "FAILURE");
			
			if(!snW.clickByXpath("StockRoom_ThresholdFirstLinkCheckBox_Xpath"))
				Reporter.reportStep("The First Threshold link check Box is not selected under Stockroom thresholds, hence failure.", "FAILURE");
			
			snW.scrollToElementByXpath("StockRoom_AllSelect_Xpath");
			
			if(!snW.selectByVisibleTextByXpath("StockRoom_AllSelect_Xpath", "   Delete"))
				Reporter.reportStep("The Delete Option is not selected under Stockroom thresholds, hence failure.", "FAILURE");
				
			if(snW.clickByXpath("StockRoom_OkButton_Xpath"))
				Reporter.reportStep("The First Threshold link check Box is clicked and Delete Option is selected successfully.", "SUCCESS");
			else
				Reporter.reportStep("The First Threshold link check Box is not selected under Stockroom thresholds, hence failure.", "FAILURE");
			
			
			
		/*	String name=snW.getAttributeByXpath("StockRoom_ThresholdName_Xpath", "value");
			
			name=name.trim();
			
			if(snW.clickByXpath("StockRoom_ThresholdDelete_Xpath"))
				Reporter.reportStep("The First Threshold link is selected under Stockroom thresholds and delete button is clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("The Delete Button is not clicked, hence failure.", "FAILURE");
			
			if(!snW.isExistByLinkText(name, false))
				Reporter.reportStep("The deleted Threshold "+name+" is not available Stockroom thresholds as expected.", "SUCCESS");
			else
				Reporter.reportStep("The deleted Threshold "+name+" is available, hence failure.", "FAILURE");*/
			
			
			snW.Wait(5000);
			
			snW.switchToMenu();
			
			if(!snW.clickLink("Stockrooms_Link", true))
				Reporter.reportStep("The Stockrooms link is not clicked, hence failure.", "FAILURE");
			
			snW.switchToMain();
			
			if(!snW.isExistByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The Stockrooms list not visible, hence failure.", "FAILURE");
			
			
			
			if(snW.clickByXpath("NewButton_Xpath"))
				Reporter.reportStep("The Stockrooms link is clicked and The New Button is clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("The New Button is not clicked or not found, hence failure.", "FAILURE");

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			String Name= stName+dateFormat.format(date);
			System.out.println(Name);
			
			if(!snW.enterByXpath("StockRoom_StName_Xpath", Name))
				Reporter.reportStep("The Name is not entered, hence failure.", "FAILURE");

			if(!snW.enterAndChoose("StockRoom_StLocation_Xpath", stLocation))
				Reporter.reportStep("The Location is not entered, hence failure.", "FAILURE");

			if(snW.enterAndChoose("StockRoom_StManager_Xpath", stMana))
				Reporter.reportStep("The Name, Location and Manager entered in respective fields.", "SUCCESS");
			else
				Reporter.reportStep("The Manager Name is not entered, hence failure.", "FAILURE");

			if(snW.clickByXpath("StockRoom_Submit_Xpath"))
				Reporter.reportStep("The submit button is clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("The Submit Button is not clicked or not found, hence failure.", "FAILURE");
			
			if(!snW.selectByVisibleTextByXpath("StockRoom_Select_Xpath", "Name"))
				Reporter.reportStep("The value Name is not selected, hence failure.", "FAILURE");
			
			if(!snW.enterByXpathAndClick("StockRoom_Search_Xpath", Name))
				Reporter.reportStep("The value "+Name+" is not enterd or not found, hence failure.", "FAILURE");
			
			if(snW.isExistByLinkText(Name, false))
				Reporter.reportStep("The created Threshold "+Name+" is available Stockroom list as expected.", "SUCCESS");
			else
				Reporter.reportStep("The created Threshold "+Name+" is not available Stockroom list, hence failure.", "FAILURE");
			
			
				status = "PASS";

		}		
		finally {
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "WorldpayTC05")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("WorldpayTC05");
		return arrayObject;
	}
}		

