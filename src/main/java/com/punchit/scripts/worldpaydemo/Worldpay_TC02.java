
package com.punchit.scripts.worldpaydemo;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Worldpay_TC02 extends SuiteMethods{


	@Test(dataProvider = "WorldpayTC02",groups="Worldpay")
	public void createAssets(String regUser, String regPwd, String usr, String filterData, String filterType, String filterCondition,
							String filterValue, String suppoGroup, String status1, String stockroom, String filter1, String filter2,
							String filter3, String filter4, String status2, String reservedFor, String assignTo,
							String status3, String status4, String stockRoom, String status5, String status6, String status7,
							String manger, String status8) {

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
				Reporter.reportStep("The Asset subsection is located as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Asset menu is not available, hence failure.", "FAILURE");

			String[] expectedMenus={"Receivinggoods_Xpath", "AllAssets_Xpath", "EUC_Xpath", "PhysicalServers_Xpath", "Network_Xpath",
									"LoadBalancer_Xpath", "DataCenter_Xpath","KVM_Xpath", "Storage_Xpath", "Stockrooms_Xpath"};
			String[] expectedMenusDesc={"Receiving goods", "All Assets", "EUC", "Physical Servers", "Network", "Load Balancer", 
										"Data Center", "KVM", "Storage", "Stockrooms"};
			snW.verifyFieldsExistByXpath(expectedMenus, expectedMenusDesc);

			snW.Wait(1000);
			if(!snW.clickLink("AllAssets_Link", true))
				Reporter.reportStep("The Assets link is not clicked, hence failure.", "FAILURE");
			
			snW.switchToMain();
			
			if(snW.isExistByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The All Assets link clicked and The Asset list appeared as expected.", "SUCCESS");
			else
				Reporter.reportStep("The All Assets list not visible, hence failure.", "FAILURE");

			
			if(!snW.clickByXpath("List_filterIcon_Xpath"))
				Reporter.reportStep("The Filter Icon is not clicked or not visible, hence failure.", "FAILURE");
			
			snW.addFilters("List_FirstFilterTypeByselect_Xpath", filterType, "List_FilterConditionByselect_Xpath", filterCondition, "List_FilterValueByselect_Xpath", filterValue);
			
			if(!snW.clickByXpath("AndCondition_Xpath"))
				Reporter.reportStep("The AND button is not clicked.", "FAILURE");
	
			snW.addFilters("List_FirstFilterTypeByselect2_Xpath", filter1, "List_FilterConditionByselect2_Xpath", "is empty");
			
			if(!snW.clickByXpath("AndCondition_Xpath"))
				Reporter.reportStep("The AND button is not clicked.", "FAILURE");
			
			String text = snW.getTextByXpath("List_FirstFilterTypeByselect3_Xpath");
			
			System.out.println(text);
			
			if(text.contains("Show Related Fields")){
				snW.selectByVisibleTextByXpath("List_FirstFilterTypeByselect3_Xpath", "Show Related Fields");}
			
			snW.selectByValueTextByXpath("List_FirstFilterTypeByselect3_Xpath", "ci...");
			
			snW.addFilters("List_FirstFilterTypeByselect3_Xpath", "   Status", "List_FilterConditionByselect3_Xpath", "is", 
					"List_FilterValueByselect3_Xpath", "New");
			
			if(!snW.clickByXpath("AndCondition_Xpath"))
				Reporter.reportStep("The AND button is not clicked.", "FAILURE");
	
			snW.Wait(5000);
			
			snW.selectByValueTextByXpath("List_FirstFilterTypeByselect4_Xpath", "ci...");
			
			snW.Wait(5000);
			
			snW.addFilters("List_FirstFilterTypeByselect4_Xpath", "   Support group", "List_FilterConditionByselect4_Xpath", "is empty");
			
			
//     		snW.addFilterstoEnterAndChooseValues("List_FirstFilterTypeByselect2_Xpath", filter1, "List_FilterConditionByselect2_Xpath",
//					filter2, "List_FilterValueByInput2_Xpath", filter3);
////			
//			snW.addFilterstoEnterValue("List_FirstFilterTypeByselect2_Xpath", "Asset tag", "List_FilterConditionByselect2_Xpath",
//					"is", "List_FilterValueByInput1_Xpath", "DC-123456");
			
			
			if(snW.clickByXpath("List_FilterRun_Xpath"))
				Reporter.reportStep("The filter set with value: "+filterType+" "+filterCondition+" "+filterValue+" and all Assets state matched with "+filterValue+" as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Run Button is not clicked or not visible, hence failure.", "FAILURE");
			
			if(!snW.clickByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The Run Button is not clicked or not visible, hence failure.", "FAILURE");
			
			String[] recevingflds={"Receiving_DisplayReadonly_Xpath","Receiving_ModelCategoryReadonly_Xpath", "Receiving_ConfigurationReadonly_Xpath",
					"Receiving_ModelReadonly_Xpath","Receiving_QuantityReadonly_Xpath"};
			
			String[] recevingdes={ "Display Name", "Model Category", "Configuration", "Model", "Quantity"};

			snW.verifyDisabledFieldsByXpath(recevingflds, recevingdes);
			
			if(!isExistByXpath("Asset_ConfigurationInformation_Xpath"))
				Reporter.reportStep("Information button associated with the Configuration Item is not visible, hence failure.", "FAILURE");
			
			if(clickByXpath("Asset_ConfigurationInformation_Xpath"))
				Reporter.reportStep("Information button associated with the Configuration Item clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("Information button associated with the Configuration Item is not clicked, hence failure.", "FAILURE");
			
			if(snW.isExistByXpath("RecordNotFoundXpath"))
				Reporter.reportStep("Record not found , hence failure.", "FAILURE");
			
			if(enterAndChoose("All_Configuration_Xpath", suppoGroup))
				Reporter.reportStep("The corresponding configuration form is editable as expected.", "SUCCESS");
			else
				Reporter.reportStep("The value: "+suppoGroup+" is not entered or support group field is disable, hence failure.", "FAILURE");
			
			
			if(clickByXpath("UpdateButton_Xpath"))
				Reporter.reportStep("The value: "+suppoGroup+" is entered in Support Group and the update Button is clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("The update button is not foundor not clicked, hence failure.", "FAILURE");
			
			if(!enterByXpath("AllAsset_AssetTag_Xapth", "Test"))
				Reporter.reportStep("The value: Test is not entered in Asset Tag field, hence failure.", "FAILURE");

			if(!selectByVisibleTextByXpath("AllAsset_Status_Xapth", status1))
				Reporter.reportStep("The value: "+status1+" is not selected in status field, hence failure.", "FAILURE");
			
			if(enterAndChoose("AllAsset_StockRoom_Xapth", stockroom))
				Reporter.reportStep("The Values Status "+status1+",  Stockroom: "+stockroom+" is selected in respective fields", "SUCCESS");
			else
				Reporter.reportStep("The value: "+stockroom+" is not entered in stockroom field, hence failure.", "FAILURE");
			
			snW.waitUntillValueBecomeNotNull("Location_ValueAttr_Xpath");
			
			if(clickByXpath("UpdateButton_Xpath"))
				Reporter.reportStep("The Update button is clicked and the form is updated as expected.", "SUCCESS");
			else
				Reporter.reportStep("The update button is not foundor not clicked, hence failure.", "FAILURE");
			
			
			snW.switchToMenu();
			
			if(!snW.clickLink("AllAssets_Link", true))
				Reporter.reportStep("The Assets link is not clicked, hence failure.", "FAILURE");
			
			snW.switchToMain();
			
			if(!snW.clickByXpath("List_filterIcon_Xpath"))
				Reporter.reportStep("The Filter Icon is not clicked or not visible, hence failure.", "FAILURE");
			
			snW.addFilters("List_FirstFilterTypeByselect_Xpath", filterType, "List_FilterConditionByselect_Xpath",
					filterCondition, "List_FilterValueByselect_Xpath", status1);
			
			if(!snW.clickByXpath("AndCondition_Xpath"))
				Reporter.reportStep("The AND button is not clicked.", "FAILURE");
			
			snW.addFilterstoEnterAndChooseValues("List_FirstFilterTypeByselect2_Xpath", filter1, "List_FilterConditionByselect2_Xpath",
					filter4, "List_FilterValueByInput2_Xpath", filter3);
			
			if(snW.clickByXpath("List_FilterRun_Xpath"))
				Reporter.reportStep("The filter set with values: "+filterType+" "+filterCondition+" "+status1+" and "+filter1+" "+filter4+" "+filter3+" successfully.",  "SUCCESS");
			else
				Reporter.reportStep("The Run Button is not clicked or not visible, hence failure.", "FAILURE");
			
			if(snW.clickByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The First Asset Tag is clicked successfully.", "SUCCESS");
			else
				Reporter.reportStep("The First Asset Tag is not clicked, hence failure.", "FAILURE");
			
			String assetTag=getAttributeByXpath("AllAsset_AssetTag_Xapth", "value");
			
			System.out.println(assetTag);

			assetTag=assetTag+snW.getCurrentTime();
			
			System.out.println(assetTag);
			
			if(!snW.enterByXpath("AllAsset_AssetTag_Xapth", assetTag))
				Reporter.reportStep("The Asset "+assetTag+"is not entered, hence failure.", "FAILURE");
			
			if(selectByVisibleTextByXpath("AllAsset_Status_Xapth", status8))
				Reporter.reportStep("The value: "+status8+" is selected in status field successfully.", "SUCCESS");
			else
				Reporter.reportStep("The value: "+status8+" is not selected in status field, hence failure.", "FAILURE");
			
			if(!isExistByXpath("AllAsset_ReservedFor_Xapth"))
				Reporter.reportStep("The Field Reserved for is not visible, hence failure.", "FAILURE");
			
			if(!enterAndChoose("AllAsset_ReservedFor_Xapth", reservedFor))
				Reporter.reportStep("The value is not entered in Reserved For field, hence failure.", "FAILURE");
			
			if (!snW.rightClickById("Menu_Id"))
				Reporter.reportStep("Right click could not be performed","FAILURE");

			if(snW.clickByXpath("Computers_SaveRecord_Xpath"))
				Reporter.reportStep("The field Reserved for appeared and first value is selected and Asset updated successfully.", "SUCCESS");
			else
				Reporter.reportStep("The update button is not foundor not clicked, hence failure.", "FAILURE");
			
//			snW.opentheAsset(assetTag);
			
			String[] list1={"In use", "Lost or Stolen"};
			
			snW.verifyUnselectLists("AllAsset_Status_Xapth", list1);
			
			if(selectByVisibleTextByXpath("AllAsset_Status_Xapth", status3))
				Reporter.reportStep("The value: "+status3+" is selected in status field successfully.", "SUCCESS");
			else
				Reporter.reportStep("The value: "+status3+" is not selected in status field, hence failure.", "FAILURE");
						
			if(!isExistByXpath("AllAsset_AssignTo_Xapth"))
				Reporter.reportStep("The Field Reserved for is not visible, hence failure.", "FAILURE");
			
			if(!enterAndChoose("AllAsset_AssignTo_Xapth", assignTo))
				Reporter.reportStep("The value is not entered in Assign To field, hence failure.", "FAILURE");
			
			
			if (!snW.rightClickById("Menu_Id"))
				Reporter.reportStep("Right click could not be performed","FAILURE");

			if (snW.clickByXpath("Computers_SaveRecord_Xpath"))
				Reporter.reportStep("The field Assign To appeared and first value is selected and Asset updated successfully.", "SUCCESS");
			else
				Reporter.reportStep("The update button is not foundor not clicked, hence failure.", "FAILURE");
		
//			snW.opentheAsset(assetTag);
			
			String[] list2={"Pending repair in stock", "Ready for disposal", "Lost or Stolen"};
			
			snW.verifyUnselectLists("AllAsset_Status_Xapth", list2);
			
			if(selectByVisibleTextByXpath("AllAsset_Status_Xapth", status4))
				Reporter.reportStep("The value: "+status4+" is selected in status field successfully.", "SUCCESS");
			else
				Reporter.reportStep("The value: "+status4+" is not selected in status field, hence failure.", "FAILURE");
						
			if(!isExistByXpath("AllAsset_StockRoom_Xapth"))
				Reporter.reportStep("The Field Reserved for is not visible, hence failure.", "FAILURE");
			
			if(!enterAndChoose("AllAsset_StockRoom_Xapth", stockRoom))
				Reporter.reportStep("The value is not entered in Assign To field, hence failure.", "FAILURE");
			
			
			if (!snW.rightClickById("Menu_Id"))
				Reporter.reportStep("Right click could not be performed","FAILURE");

			if (snW.clickByXpath("Computers_SaveRecord_Xpath"))
				Reporter.reportStep("The field Stockroom appeared and first value is selected and Asset updated successfully.", "SUCCESS");
			else
				Reporter.reportStep("The update button is not foundor not clicked, hence failure.", "FAILURE");
		
//			snW.opentheAsset(assetTag);
			
			String[] list3={"Reserved in stock","In use", "Lost or Stolen"};
			
			snW.verifyUnselectLists("AllAsset_Status_Xapth", list3);
			
			if(!selectByVisibleTextByXpath("AllAsset_Status_Xapth", status5))
				Reporter.reportStep("The value: "+status5+" is not selected in status field, hence failure.", "FAILURE");
			
			if (!snW.rightClickById("Menu_Id"))
				Reporter.reportStep("Right click could not be performed","FAILURE");

			if (snW.clickByXpath("Computers_SaveRecord_Xpath"))
				Reporter.reportStep("The value: "+status5+" is selected in state field and Asset updated successfully.", "SUCCESS");
			else
				Reporter.reportStep("The Save button is not foundor not clicked, hence failure.", "FAILURE");
		
//			snW.opentheAsset(assetTag);
			System.out.println();
			System.err.println();
			if(snW.verifyAttributeTextByXpath("AllAsset_Status_Xapth", "readonly", "true"))
				Reporter.reportStep("The state field is Read only and functionality suspended as expected.", "SUCCESS");
			else
				Reporter.reportStep("The state field is not disable, hence failure.", "WARNING");
		
			snW.switchToDefault();
			
			if(snW.selectUser(manger))
				Reporter.reportStep("The login with username: "+ manger + " is successful", "SUCCESS");
			
			snW.switchToMenu();
			
			snW.Wait(1000);
			if(!snW.clickLink("AllAssets_Link", true))
				Reporter.reportStep("The Assets link is not clicked, hence failure.", "FAILURE");
			
			snW.switchToMain();
			
			if(!snW.clickByXpath("List_filterIcon_Xpath"))
				Reporter.reportStep("The Filter Icon is not clicked or not visible, hence failure.", "FAILURE");
			
			snW.addFilterstoEnterValue("List_FirstFilterTypeByselect_Xpath", "Asset tag", "List_FilterConditionByselect_Xpath",
					"is", "List_FirstFilterTypeByInput1_Xpath", assetTag);
			
			if(!snW.clickByXpath("List_FilterRun_Xpath"))
				Reporter.reportStep("The Run Button is not clicked or not visible, hence failure.", "FAILURE");

			if(!snW.clickByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The Asset link is not clicked or not visible, hence failure.", "FAILURE");
			
			if(!selectByVisibleTextByXpath("AllAsset_Status_Xapth", "In use"))
				Reporter.reportStep("The value: "+status6+" is not selected in status field, hence failure.", "FAILURE");
			
			
			if(!isExistByXpath("AllAsset_AssignTo_Xapth"))
				Reporter.reportStep("The Field Reserved for is not visible, hence failure.", "FAILURE");
			
			if(!enterAndChoose("AllAsset_AssignTo_Xapth", assignTo))
				Reporter.reportStep("The value is not entered in Assign To field, hence failure.", "FAILURE");
			
			
			if(clickByXpath("UpdateButton_Xpath"))
				Reporter.reportStep("The value: "+status6+" is selected in state field and Asset updated successfully.", "SUCCESS");
			else
				Reporter.reportStep("The update button is not foundor not clicked, hence failure.", "FAILURE");
		
			snW.switchToDefault();
			
			if(snW.selectUser(usr))
				Reporter.reportStep("The login with username: "+ usr + " is successful", "SUCCESS");
			
			snW.switchToMenu();
			
			if(!snW.enterByXpath("Filter_Xpath", filterData))
				Reporter.reportStep("The Data "+filterData+" is not entered in Filter Box, hence failure.", "FAILURE");
			
			if(!snW.clickLink("AllAssets_Link", true))
				Reporter.reportStep("The Assets link is not clicked, hence failure.", "FAILURE");
			
			snW.switchToMain();
			
			if(!snW.clickByXpath("List_filterIcon_Xpath"))
				Reporter.reportStep("The Filter Icon is not clicked or not visible, hence failure.", "FAILURE");
			
			snW.addFilterstoEnterValue("List_FirstFilterTypeByselect_Xpath", "Asset tag", "List_FilterConditionByselect_Xpath",
					"is", "List_FirstFilterTypeByInput1_Xpath", assetTag);
			
			if(!snW.clickByXpath("List_FilterRun_Xpath"))
				Reporter.reportStep("The Run Button is not clicked or not visible, hence failure.", "FAILURE");

			if(!snW.clickByXpath("List_AllLinks_Xpath"))
				Reporter.reportStep("The Run Button is not clicked or not visible, hence failure.", "FAILURE");
			
			if(!selectByVisibleTextByXpath("AllAsset_Status_Xapth", status7))
				Reporter.reportStep("The value: "+status7+" is not selected in status field, hence failure.", "FAILURE");
			
			
			if (!snW.rightClickById("Menu_Id"))
				Reporter.reportStep("Right click could not be performed","FAILURE");

			if (snW.clickByXpath("Computers_SaveRecord_Xpath"))
				Reporter.reportStep("The value: "+status7+" is selected in state field and Asset updated successfully.", "SUCCESS");
			else
				Reporter.reportStep("The update button is not foundor not clicked, hence failure.", "FAILURE");
			
//			snW.opentheAsset(assetTag);
			
			String[] list4={"Lost or Stolen", "Disposed"};
			 
			snW.verifyUnselectLists("AllAsset_Status_Xapth", list4);
			
			status = "PASS";

		}		
		finally {
//			snW.quitBrowser();
		}

	}

	@DataProvider(name = "WorldpayTC02")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("WorldpayTC02");
		return arrayObject;
	}
}		

