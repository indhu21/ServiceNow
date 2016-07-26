package com.punchit.scripts.worldpaydemo;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Worldpay_TC03 extends SuiteMethods{


	@Test(dataProvider = "WorldpayTC03")
	public void createAssets(String regUser, String regPwd, String usr, String filterData, String manufacturer, String model,
			String purchase, String state, String company, String sNum, String model1, String server,
			String quantity, String state1) {

		snW = new ServiceNowWrappers(entityId);


		try {

			snW.launchApp(browserName, true);
			
			if (!snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username: "+ regUser + " is not successful", "FAILURE");
 
			if(snW.selectUser(usr))
				Reporter.reportStep("The login with username: "+ usr + " is successful", "SUCCESS");

			snW.switchToMenu();
			
			if(!snW.enterByXpath("Filter_Xpath", filterData))
				Reporter.reportStep("The Data "+filterData+" is not entered in Filter Box, hence failure.", "FAILURE");

			if(snW.isExistByLinkText("Receivinggoods_Link", true))
				Reporter.reportStep("The Asset subsection is located and Asset related links are available as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Asset menu is not available, hence failure.", "FAILURE");

			String[] expectedMenus={"Receivinggoods_Xpath", "AllAssets_Xpath", "EUC_Xpath", "PhysicalServers_Xpath", 
									"Network_Xpath", "LoadBalancer_Xpath", "DataCenter_Xpath", "KVM_Xpath", "Storage_Xpath",
									"Stockrooms_Xpath"};
			String[] expectedMenusDesc={"Receiving goods", "All Assets", "EUC", "Physical Servers", "Network", 
										"Load Balancer", "Data Center", "KVM", "Storage", "Stockrooms"};
			snW.verifyFieldsExistByXpath(expectedMenus, expectedMenusDesc);

			if(!snW.clickLink("Receivinggoods_Link", true))
				Reporter.reportStep("The Receiving goods link is not clicked, hence failure.", "FAILURE");
			snW.switchToMain();
			if(snW.isExistByXpath("ReceivingBodyText_Xpath"))
				Reporter.reportStep("The Receiving goods link clicked and The Receiving goods form appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Receiving goods form not visible, hence failure.", "FAILURE");


			String[] fields={"Receiving_Modelcategory_Xpath", "Receiving_Quantity_Xpath", "Receiving_State_Xpath", "Receiving_Asset_Xpath",
					"Receiving_SerialNumb_Xpath", "Receiving_Company_Xpath", "Receiving_Purchase_Xpath", "Receiving_Manufacturer_Xpath"};
			String[] desc={"Model category", "Quantity", "State", "Asset", "Serial Number", "Company", "Purchase Order", "Manufacturer"};	
			snW.verifyFieldsExistByXpath(fields, desc);

			String[] fields1={"Receiving_Modelcategory_Xpath"};
			String[] desc1={"Model category"};
			snW.verifyDisabledFieldsByXpath(fields1, desc1);

			String[] fields2={"Receiving_MabufacturerMan_Xpath", "Receiving_SerialNumbMan_Xpath"};
			String[] desc2={"Manufacturer", "Serial Number"};
			snW.verifyMandatoryFields(fields2, desc2);

			if(!snW.enterAndChoose("Receiving_Manufacturer_Xpath", manufacturer))
				Reporter.reportStep("The Value: "+manufacturer+" is entered in Manufacturer, hence failure.", "FAILURE");

			if(!snW.selectByVisibleTextByXpath("Receiving_Model_Xpath", model))
				Reporter.reportStep("The Value: "+model+" is selected in Model, hence failure.", "FAILURE");

			if(!snW.enterAndChoose("Receiving_Purchase_Xpath", purchase))
				Reporter.reportStep("The Value: "+purchase+" s entered in Purchase Order, hence failure.", "FAILURE");

			if(!snW.selectByVisibleTextByXpath("Receiving_State_Xpath", state))
				Reporter.reportStep("The Value: "+state+" is selected in State, hence failure.", "FAILURE");

			if(!snW.enterAndChoose("Receiving_Company_Xpath", company))
				Reporter.reportStep("The Value: "+company+" s entered in Company, hence failure.", "FAILURE");

			if(snW.enterAndChoose("Receiving_SerialNumb_Xpath", sNum))
				Reporter.reportStep("The Values Manufacturer: "+manufacturer+" Model: "+model+" Purchase Order: "+purchase+" State: "+state+" and Company: "+company+" selected in respective fields successfully.", "SUCCESS");
			else
				Reporter.reportStep("The Value: "+sNum+" s entered in Serial Number, hence failure.", "FAILURE");

			if(snW.clickByXpath("Receiving_Submit_Xpath"))
				Reporter.reportStep("The Asset is created successfully and Asset form appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Submit Button is not clicked, hence failure.", "FAILURE");

			
		/*	String[] recevingflds={"Receiving_DisplayReadonly_Xpath","Receiving_ModelCategoryReadonly_Xpath", "Receiving_ConfigurationReadonly_Xpath",
					"Receiving_ModelReadonly_Xpath","Receiving_QuantityReadonly_Xpath", "Receiving_AssetTagReadonly_Xpath","Receiving_StateReadonly_Xpath",
					"Receiving_SerialNoReadonly_Xpath", "Receiving_SupportGroupReadOnly_Xpath", "Receiving_DisposalDateReadonly_Xpath","Receiving_LocationReadonly_Xpath",
					"Receiving_CompanyReadonly_Xpath", "Receiving_AssignedReadonly_Xpath", "Receiving_CommentsReadonly_Xpath", "Receiving_RequestReadonly_Xpath",
					"Receiving_OpenedReadonly_Xpath", "Receiving_PONumberReadonly_Xpath", "Receiving_GLAccountReadonly_Xpath", "Receiving_CostReadonly_Xpath",
					"Receiving_AquisitionReadonly_Xpath", "Receiving_VendorReadonly_Xpath", "Receiving_LeaseReadonly_Xpath", "Receiving_WarrantyReadonly_Xpath"};
			
			String[] recevingdes={"Display Name", "Model Category", "Configuration", "Model", "Quantity", "Asset Tag", "State",
									"Serial Number", "Support Group", "Disposal Date", "Location", "Company", "Assigned", "Comments",
									"Request", "Opened", "PO Number", "GLAccount", "CostReadonly", "Aquisition", "Vendor", "Lease",
									"Warranty"};

			
			if(snW.verifyDisabledFieldsByXpathwithoutReport(recevingflds, recevingdes))
				Reporter.reportStep("The Asset is created successfully and values are read only as expected.", "SUCCESS");*/
						
			snW.switchToMenu();
			
			if(!snW.clickLink("Receivinggoods_Link", true))
				Reporter.reportStep("The Receiving goods links are not clicked, hence failure.", "FAILURE");
			snW.switchToMain();
			if(snW.isExistByXpath("ReceivingBodyText_Xpath"))
				Reporter.reportStep("The Receiving goods link clicked and The Receiving goods form appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Receiving goods form not visible, hence failure.", "FAILURE");

			
			if(!snW.enterAndChoose("Receiving_Manufacturer_Xpath", manufacturer))
				Reporter.reportStep("The Value: "+manufacturer+" is entered in Manufacturer, hence failure.", "FAILURE");

			if(!snW.selectByVisibleTextByXpath("Receiving_Model_Xpath", model1))
				Reporter.reportStep("The Value: "+model1+" is selected in Model, hence failure.", "FAILURE");

			if(!snW.isExistByXpath("Receiving_ChassisBayLocation1_Xpath"))
				Reporter.reportStep("The Chassis Bay Location 1 not visible, hence failure.", "FAILURE");

			if(!snW.isExistByXpath("Receiving_Chassis1_Xpath"))
				Reporter.reportStep("The Chassis 1_ not visible, hence failure.", "FAILURE");

			if(snW.getAttributeByXpath("Receiving_ModelcategoryAttri_Xpath", "value").equals(server))
				Reporter.reportStep("The Chassis 1 and  Chassis Bay Location1 appeared and Model category auto populated with value: "+server+" as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Receiving goods form not visible, hence failure.", "FAILURE");

			if(!snW.selectByVisibleTextByXpath("Receiving_Quantity_Xpath", quantity))
				Reporter.reportStep("The Quantity not selected, hence failure.", "FAILURE");


			if(!snW.isExistByXpath("(//*[contains(text(),'Chassis Bay Location "+quantity+"')])",false))
				Reporter.reportStep("The Chassis Bay Location "+quantity+"is not visible, hence failure.", "FAILURE");
			if(!snW.isExistByXpath("(//*[contains(text(),'Chassis "+quantity+"')])",false))
				Reporter.reportStep("The Chassis "+quantity+"is not visible, hence failure.", "FAILURE");
			if(!snW.isExistByXpath("(//*[contains(text(),'Serial number "+quantity+"')])",false))
				Reporter.reportStep("The Serial number "+quantity+" is not visible, hence failure.", "FAILURE");
			if(snW.isExistByXpath("(//*[contains(text(),'Asset tag "+quantity+"')])",false))
				Reporter.reportStep("New quantity dependent Serial number, Asset tag, Chassis and Chassis Bay Location fields appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Asset tag "+quantity+"is not visible, hence failure.", "FAILURE");

			if(!snW.selectByVisibleTextByXpath("Receiving_State_Xpath", state1))
				Reporter.reportStep("The Value: "+state+" is selected in State, hence failure.", "FAILURE");

			if(snW.isExistByXpath("Receiving_AssignedTo_Xpath"))
				Reporter.reportStep("The State is selected with value: "+state+" and Assigned To field appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Assigned To field not appeared, hence failure.", "FAILURE");

			status = "PASS";

		}		
		finally {
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "WorldpayTC03")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("WorldpayTC03");
		return arrayObject;
	}
}		

