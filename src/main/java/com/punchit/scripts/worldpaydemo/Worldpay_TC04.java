package com.punchit.scripts.worldpaydemo;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;

public class Worldpay_TC04 extends SuiteMethods {
	
		@Test(dataProvider = "WorldpayTC04",groups="Worldpay")
		public void createAssets(String regUser, String regPwd, String user, String User2, String Filter_type, String Filter_condition,
								 String Filter_value, String Filter_condition2, String Status1, String Status2, String Status3,
								 String filterType, String filterCondition, String filterValue) {

			try {
				
				snW.launchApp(browserName, true);
				
				if (!snW.login(regUser, regPwd))
					Reporter.reportStep("The login with username: "+ regUser + " is not successful", "FAILURE");
	 
				if(snW.selectUser(user))
					Reporter.reportStep("The login with username: "+ user + " is successful", "SUCCESS");
			
				snW.switchToMenu();
				
//				if(!snW.enterByXpath("Filter_Xpath", "Base Items"))
//					Reporter.reportStep("The Data Configuration is not entered in Filter Box, hence failure.", "FAILURE");
//				
				if(snW.selectMenu("Configuration","Base_Items","Computers"))
					Reporter.reportStep("Computers is  selected successfully", "SUCCESS");
				else
					Reporter.reportStep("Computers could not be selected", "FAILURE");
				
				//Switch to Main Frame
				snW.switchToFrame("Frame_Main");
				snW.Wait(2000);
				
				//Setting the filter 
              if(!snW.clickByXpath("Computer_Filtericon_xpath"))
                	Reporter.reportStep("Filter icon could not be selected", "FAILURE");
					
				if(snW.addNewFilterUsingInput2(Filter_type, Filter_condition, Filter_value))
					Reporter.reportStep("Filter is set as CI is not part of EUC support group", "SUCCESS");
				else
					Reporter.reportStep("Filter is not set as CI whcih are not part of EUC support group", "FAILURE");
				
				if(!snW.clickByXpath("AndCondition_Xpath"))
					Reporter.reportStep("The AND button is not clicked.", "FAILURE");
				
				if(!snW.addFilters("List_FirstFilterTypeByselect2_Xpath", "Class", "List_FilterConditionByselect2_Xpath", 
						"is", "List_FilterValueByselect2_Xpath", "Server"))
					Reporter.reportStep("Filter is not set as CI whcih are not In Use.", "FAILURE");
				
				if(!snW.clickByXpath("Worldpay_RunFilter_Xpath"))
					Reporter.reportStep("Run button could not be clicked", "FAILURE");
				
				//Selecting the first CI
				if(snW.clickByXpath("Computer_Firstlink_Xpath"))
					Reporter.reportStep("First CI is selected successfully", "SUCCESS");
				else
					Reporter.reportStep("First CI could not be selected", "FAILURE");
				
				// Readonly code
				
				String[] descs	= {"Name","Company","Comments","Most recent discovery","Approval group","Support group","Type",
							"Status","Model","Manufacturer","Serial number","Chassis Bay Location","Disposal Date","Order received",
							"Installed","Domain Name","Host name","DNS Domain","Environment","Operating System","OS Version","OS Service Pack",
							"PCI Relevant","CPU core count","CPU count","CPU speed(MHz)","CPU type","CPU name","RAM(MB)","Location","Floor","Room",
							"Front Upper","Front Lower","Rear Upper","Rear Lower"};
				String[] fields={"Computers_Name_Xpath","Computers_Company_Xpath","Computers_Comments_Xpath","Computers_Mostrecentdiscovery_Xpath",
						"Computers_Approvalgroup_Xpath","Computers_Supportgroup_Xpath","Computers_Type_Xpath","Computers_Status_Xpath","Computers_Model_Xpath","Computers_Manufacturer_Xpath",
						"Computers_Serialnumber_Xpath","Computers_ChassisBayLocation_Xpath","Computers_DisposalDate_Xpath","Computers_Orderreceived_Xpath","Computers_Installed_Xpath",
						"Computers_DomainName_Xpath","Computers_Hostname_Xpath","Computers_DNSDomain_Xpath","Computers_Environment_Xpath","Computers_OperatingSystem_Xpath","Computers_OSVersion_Xpath",
						"Computers_OSServicePack_Xpath","Computers_PCIRelevant_Xpath","Computers_CPUcorecount_Xpath","Computers_CPUcount_Xpath","Computers_CPUspeed(MHz)_Xpath",
						"Computers_CPUtype_Xpath","Computers_CPUname_Xpath","Computers_RAM(MB)_Xpath","Computers_Location_Xpath","Computers_Floor_Xpath","Computers_Room_Xpath",
						"Computers_FrontUpper_Xpath","Computers_FrontLower_Xpath","Computers_RearUpper_Xpath","Computers_RearLower_Xpath"};
				
				if(snW.verifyDisabledFieldsByXpathwithoutReport(fields, descs))
					Reporter.reportStep("The form is read only as expected.", "SUCCESS");
				
				
				if(!snW.clickByXpath("Worldpay_Back_Xpath"))
					Reporter.reportStep("backbutton could not be clicked", "FAILURE");
				
				//Setting the filter for EUC support group
				if(!snW.clickByXpath("Computer_Filtericon_xpath"))
                	Reporter.reportStep("Filter icon could not be selected", "FAILURE");
				
				if(snW.addNewFilterUsingInput2(Filter_type, Filter_condition2, Filter_value))
					Reporter.reportStep("Filter is set as CI is a part of EUC support group", "SUCCESS");
				else
					Reporter.reportStep("Filter is a set as CI whcih are not part of EUC support group", "FAILURE");
				/*
				if(!snW.clickByXpath("Worldpay_RunFilter_Xpath"))
					Reporter.reportStep("Run button could not be clicked", "FAILURE");
				*/
				if(!snW.clickByXpath("AndCondition_Xpath"))
					Reporter.reportStep("The AND button is not clicked.", "FAILURE");
				
				if(!snW.addFilters("List_FirstFilterTypeByselect2_Xpath", filterType, "List_FilterConditionByselect2_Xpath", filterCondition, "List_FilterValueByselect2_Xpath", filterValue))
					Reporter.reportStep("Filter is not set as CI whcih are not In Use.", "FAILURE");
				
				if(!snW.clickByXpath("Worldpay_RunFilter_Xpath"))
					Reporter.reportStep("Run button could not be clicked", "FAILURE");
				
				//Selecting the first CI
				if(snW.clickByXpath("Computer_Firstlink_Xpath"))
					Reporter.reportStep("First CI is selected successfully", "SUCCESS");
				else
					Reporter.reportStep("First CI could not be selected", "FAILURE");
				
				String[] descs1	= {"Name", "Support group", "Approval group", "Fully qualified domain name", "Host name",
						"DNS Domain", "Environment", "Operating System", "OS Version", "OS Service Pack", "RAM (MB)", "CPU name",
						"CPU type", "CPU speed (MHz)", "CPU count", "CPU core count", "PCI Relevant"};
			String[] fields1={"LapTop_Name_Xpath", "LapTop_Group_Xpath", "LapTop_Control_Xpath", "LapTop_DomainName_Xpath",
					"LapTop_HostName_Xpath", "LapTop_DNSDomain_Xpath", "LapTop_Environment_Xpath", "LapTop_OS_Xpath", "LapTop_OSVersion_Xpath",
					"LapTop_PacService_Xpath", "LapTop_RAM_Xpath", "LapTop_CPU_Xpath", "LapTop_Type_Xpath", "LapTop_Speed_Xpath",
					"LapTop_Count_Xpath", "LapTop_CoreCount_Xpath", "LapTop_Relevant_Xpath"};
		
				
				snW.verifyEnabledFieldsByXpath(fields1, descs1);
				
				
				//Checking the CI Status as In use
				String CurrStatus = snW.getDefaultValueByXpath("CI_Status_Xpath");
				System.out.println("Current Ci status is "+CurrStatus);
				
				if(!CurrStatus.equals("In use"))
					Reporter.reportStep("Status field is not set to In use", "FAILURE");
				
				//Changing the status field from in use to Pending repair
				if(snW.selectByVisibleTextByXpath("CI_Status_Xpath", Status1))
					Reporter.reportStep("Status field is set to status as pending repairs", "SUCCESS");
				else
					Reporter.reportStep("Status field is not set to status as Pending repair as it is disabled", "FAILURE");
				
				//Save Record by clicking update at top of the screen
                snW.Wait(2000);
				if (!snW.rightClickById("Menu_Id"))
					Reporter.reportStep("Right click could not be performed","FAILURE");

				if (!snW.clickByXpath("Computers_SaveRecord_Xpath"))
					Reporter.reportStep("The Save button could not be clicked","FAILURE");

				snW.Wait(5000);	
				
				//Checking the status is set to pending repairs
				String CurrStatus1 = snW.getDefaultValueByXpath("CI_Status_Xpath");
				System.out.println("Current Ci status is"+CurrStatus);
				
				if(CurrStatus1.equals("Pending repair"))
					Reporter.reportStep("Save button is clicked successfully and status field is set to Pending repair", "SUCCESS");
				else
					Reporter.reportStep("Status field is not set to Pending repair", "FAILURE");
				
				//Checking the values in status listbox as In use and Pending repairs should only be available
				
				if(!snW.clickByXpath("CI_Status_Xpath"))
					Reporter.reportStep("The Status field not cliked or not found, hence failure","FAILURE");

				String[] statusfields={"In use"};
				
				snW.verifyUnselectLists("CI_Status_Xpath", statusfields);
					
				
				 
				// Changing the Status value back to In use
				if(!snW.selectByVisibleTextByXpath("CI_Status_Xpath", Status3))
					Reporter.reportStep("Status field is not set to status as "+Status3, "FAILURE");
				
				//Save Record by clicking update at top of the screen
                snW.Wait(2000);
				if (!snW.rightClickById("Menu_Id"))
					Reporter.reportStep("Right click could not be performed","FAILURE");

				if (snW.clickByXpath("Computers_SaveRecord_Xpath"))
					Reporter.reportStep("The Save button is clicked successfully and status field is again set to In use status ","SUCCESS");
				else
					Reporter.reportStep("The Save button could not be clicked","FAILURE");

				snW.Wait(500);	
				String CurrStatus2 = snW.getDefaultValueByXpath("CI_Status_Xpath");
				System.out.println("Current Ci status is "+CurrStatus2);
				
				if(!CurrStatus2.equals("In use"))
					Reporter.reportStep("Status field is not set to In use", "FAILURE");
				
				// Changing the Status value to Retired
				if(!snW.selectByVisibleTextByXpath("CI_Status_Xpath", Status2))
					Reporter.reportStep("Status field is not set to status as "+Status2, "FAILURE");
				
				//Save Record by clicking update at top of the screen
                snW.Wait(2000);
				if (!snW.rightClickById("Menu_Id"))
					Reporter.reportStep("Right click could not be performed","FAILURE");

				if (snW.clickByXpath("Computers_SaveRecord_Xpath"))
					Reporter.reportStep("The Save button is clicked successfully and staus is set with Retired ","SUCCESS");
				else
					Reporter.reportStep("The Save button could not be clicked","FAILURE");

				snW.Wait(5000);
				
				//Checking the status is set to retired
				String CurrStatus3 = snW.getDefaultValueByXpath("CI_Status_Xpath");
				System.out.println("Current Ci status is"+CurrStatus3);
				
				if(!CurrStatus3.equals(Status2))
					Reporter.reportStep("Status field is not set to "+Status2, "FAILURE");
				
				//Checking on retired and decommissioned are available
				if(!snW.clickByXpath("CI_Status_Xpath"))
					Reporter.reportStep("The Status field not cliked or not found, hence failure","FAILURE");

				String[] statusfields1={"Decomissioned"};
				
//				if(snW.verifyselectLists("CI_Status_Xpath", statusfields1))
//					Reporter.reportStep("The Status field values are matched with "+convertStringArrayToString(statusfields1)+" as expected.","SUCCESS");
//			
				snW.verifyUnselectLists("CI_Status_Xpath", statusfields1);
				
				// Capturing the name of the CI
				String CIName = snW.getAttributeByXpath("Computer_CIName_Xpath", "value");
				System.out.println("CI name is "+CIName);
				
				
				// Reloading the CI as Asset manager
				snW.switchToDefault();
				if(snW.selectUser(User2))
					Reporter.reportStep("The login with username: "+ User2 + " is successful", "SUCCESS");
			
				//snW.switchToMenu();
				
				if(!snW.selectMenu("Configuration","Base_Items","Computers"))
					Reporter.reportStep("Computers could not be selected", "FAILURE");
				
				//Switch to Main Frame
				snW.switchToFrame("Frame_Main");
				snW.Wait(2000);
				
				if(snW.enterAndChoose("Computer_SearchReferenceData_Xpath", CIName))
					Reporter.reportStep("CI name entered in the search field successfully", "SUCCESS");
				else
					Reporter.reportStep("CI name could not be entered in the search field", "FAILURE");
				
				if(snW.clickByXpath("Computer_Firstlink_Xpath"))
					Reporter.reportStep("First CI selected successfully", "SUCCESS");
				else
					Reporter.reportStep("First CI could not be selected successfully", "FAILURE");
				
				snW.Wait(1000);
				// Changing the Status value back to In use
				if(!snW.selectByVisibleTextByXpath("CI_Status_Xpath", Status3))
					Reporter.reportStep("Status field is not set to status as "+Status3, "FAILURE");
				
				//Save Record by clicking update at top of the screen
                snW.Wait(2000);
				if (!snW.rightClickById("Menu_Id"))
					Reporter.reportStep("Right click could not be performed","FAILURE");

				if (snW.clickByXpath("Computers_SaveRecord_Xpath"))
					Reporter.reportStep("The Save button is clicked successfully","SUCCESS");
				else
					Reporter.reportStep("The Save button could not be clicked","FAILURE");

				if(!snW.clickByXpath("CI_Status_Xpath"))
					Reporter.reportStep("The Status field not cliked or not found, hence failure","FAILURE");

				String[] statusfields2={"New", "In build", "Pending repair", "Retired", "Decomissioned"};
				
//				if(snW.verifyselectLists("CI_Status_Xpath", statusfields2))
//					Reporter.reportStep("The Status field values are matched with "+convertStringArrayToString(statusfields2)+" as expected.","SUCCESS");
//		
				snW.verifyUnselectLists("CI_Status_Xpath", statusfields2);
					
			
				
				status = "PASS";
					
		}		
			finally {
				snW.quitBrowser();
			}

		}

		@DataProvider(name = "WorldpayTC04")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider
					.getSheet("WorldpayTC04");
			return arrayObject;
		}
	}
	
