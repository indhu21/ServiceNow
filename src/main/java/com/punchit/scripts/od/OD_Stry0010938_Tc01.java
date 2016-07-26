package com.punchit.scripts.od;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

// for using ATU reporting -- added the listeners

public class OD_Stry0010938_Tc01 extends SuiteMethods {

	// Create Instance
	ServiceNowWrappers snW;

	@Test(dataProvider = "OD_Stry0010938_Tc01")
	public void testName(String devRegUser, String devRegPwd, String label, 
			String order, String decoratorType, String recurBehav,
			String colletionType, String dotwalk_Expre, String sceData) {

		try {
			
			// Pre-requisities 
			snW = new ServiceNowWrappers(entityId);
						
			// Step 0: Launch the application
			snW.launchApp(browserName, true);

			// Step 1: Log in to application
			if (snW.login(devRegUser, devRegPwd))
				Reporter.reportStep("Step 1: The login with username:"
						+ devRegUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"
						+ devRegUser + " is not successful", "FAILURE");


			// Step 2: In Ops Director>Administration click on Alert Enrichers
			if(snW.selectMenu("Ops_Director","Administration", "Alert_Enrichers"))
				Reporter.reportStep("Step 2: The 'Alert Enrichers' under Administration - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The 'Alert Enrichers' under Administration - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			//Step 3: Click on New
			if(snW.clickById("New_Button"))
				Reporter.reportStep("Step 3: The 'New Button' in Alert Enrichers could be clicked successfully","SUCCESS");
			else
				Reporter.reportStep("Step 3: The 'New Button'  in Alert Enrichers could not be clicked","FAILURE");

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-h-mm-ss");
			String formattedDate = sdf.format(date);	
			String labelTm = label+" - "+formattedDate;

			//Step 4: Fill the form with reference data 
			if(!snW.enterById("ALERT_Label_Id", labelTm))
				Reporter.reportStep("Step 4: The 'Label'  in Alert Enrichers could not be entered","FAILURE");

			if(!snW.clickById("ALERT_Active_Id"))
				Reporter.reportStep("Step 4: The 'Active'  in Alert Enrichers could not be clicked","FAILURE");

			if(!snW.enterById("ALERT_Order_Id", order))
				Reporter.reportStep("Step 4: The 'Order'  in Alert Enrichers could not be entered","FAILURE");

			if(!snW.selectByVisibleTextById("ALERT_DecoratorType_Id", decoratorType))
				Reporter.reportStep("Step 4: The 'Decorator Type'  in Alert Enrichers could not be selected","FAILURE");

			if(!snW.selectByVisibleTextById("ALERT_RecurrenceBehaviour_Id", recurBehav ))
				Reporter.reportStep("Step 4: The 'Recurrence Behaviour'  in Alert Enrichers could not be selected","FAILURE");

			if(!snW.selectByVisibleTextById("ALERT_CollectionType_Id", colletionType))
				Reporter.reportStep("Step 4: The 'Colletion Type'  in Alert Enrichers could not be selected","FAILURE");


			if(snW.enterById("ALERT_DotwalkExpression_Id", dotwalk_Expre))
				Reporter.reportStep("Step 4: The 'Alert Enrichers'  form could be filled all the fields sucessfully","SUCCESS");
			else
				Reporter.reportStep("Step 4: The 'Alert Enrichers'  form could not be filled all the fields sucessfully","FAILURE");

			//Step 5: Click Submit to save to Enricher
			if(snW.clickById("Submit_Id"))
				Reporter.reportStep("Step 5: The 'Submit Button' in Alert Enrichers could be clicked sucessfully","SUCCESS");
			else
				Reporter.reportStep("Step 5: The 'Submit Button' in Alert Enrichers could not be clicked ","FAILURE");

///*			snW.switchToDefault();
//			// Step 6: Logout  
//			if(snW.clickByXpath("Logout_Xpath"))
//				Reporter.reportStep("Step 6A: The Log out is clicked successfully.","SUCCESS");
//			else
//				Reporter.reportStep("Step 6A: The logout Failed", "FAILURE");		
//
//			// Step 6: Log in to application
//			if (snW.login(oprRegUser, oprRegPwd))
//				Reporter.reportStep("Step 6B: The login with username:"
//						+ oprRegUser + " is successful", "SUCCESS");
//			else
//				Reporter.reportStep("Step 6B: The login with username:"
//						+ oprRegUser + " is not successful", "FAILURE");
//
//
//*/			//Step 6: Navigate to Ops Director Testing in the application Navigator
//			*/
//			if(snW.selectMenuFromMainHeader("Ops_Director_Testing", "Scenarios"))
//				Reporter.reportStep("Step 5: The 'Scenarios' under Ops Director Testing - menu selected successfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 5: The 'Scenarios' under Ops Director Testing - menu could not be selected","FAILURE");
//
//			snW.switchToFrame("Frame_Main");
//			//Step 7: Run a Scenario from reference data
//			if(!snW.enterAndChoose("Scenario_Search_Xpath", sceData))
//		          Reporter.reportStep("Step 6: The "+ sceData +" under Scenarios could not be clicked","FAILURE");
//
//			snW.Wait(2000);
//			if(snW.clickByXpath("Select_alert"))
//				Reporter.reportStep("Step 6: New York Datacentre Unreachable scenario clicked sucessfully","SUCCESS");
//			else	
//			    Reporter.reportStep("Step 6: New York Datacentre Unreachable scenariocould not be clicked","FAILURE");
//
//			if(snW.clickByXpath("SCENARIOS_PlayScenarios_Xpath"))
//				Reporter.reportStep("Step 7: The 'Play Scenarios' clicked sucessfully","SUCCESS");
//			else	
//				Reporter.reportStep("Step 7: The 'Play Scenarios' could not be clicked","FAILURE");
//
//			snW.Wait(10000);
//				
///*			if(snW.clickByXpath("SCENARIOS_Close_Xpath"))
//				Reporter.reportStep("Step 7: The 'Close Button' could be clicked sucessfully","SUCCESS");
//			else	
//				Reporter.reportStep("Step 7: The 'Close Button' could not be clicked","FAILURE");
//
//			snW.Wait(5000);
//
//*/			
//			snW.switchToFrame("Frame_Nav");
//			//Step 13: Click Incidents in the Application Navigator Search box 
//			if(snW.enterById("filter_Id", "Alert Console"))
//				Reporter.reportStep("Step 8: 'Alert Console' could be entered in filter box sucessfully","SUCCESS");
//			else	
//				Reporter.reportStep("Step 8: 'Alert Console' could not be entered in filter box","FAILURE");
//
//
//			// Step 8: Open Alert Console under user consoles
//			if(snW.selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
//				Reporter.reportStep("Step 9: The 'Alert Console' under OpsConsole - menu selected successfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 9: The 'Alert Console' under OpsConsole - menu could not be selected","FAILURE");
//
//			snW.Wait(5000);
//			snW.switchToFrame("Frame_Main");
//
//			//Step 9: Select the top most alert
//
//			if(!snW.clickLink("New_Alerts"))
//				Reporter.reportStep("Step 10: The 'New Alerts' could not be clicked","FAILURE");
//			snW.Wait(5000);
//				
//            //Click on last occurrence
//			
//			snW.rightClickByLinkText("Last_Occurrence_link");
//			snW.Wait(2000);
//			if(snW.clickByXpath("Last_Occurrence_SortZtoA_Xpath"))
//				Reporter.reportStep("Step 10: Last occurrence clicked sucessfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 10: Last occurrence could not be clicked","FAILURE");
//            
//			snW.Wait(2000);
//			//Click on the topmost alert
//			if(!snW.clickByXpath("Paralt_xpath"))
//			    Reporter.reportStep("Step 11: Alert could not be clicked", "FAILURE");	
//			
//			snW.scrollToElementByXpath("AlertConsole_Metadata_Xpath");
//			
//			if(!snW.enterAndChoose("AlertConsole_Metadata_SearchValue_Xpath", labelTm))
//				Reporter.reportStep("Step 11: Label could not be entered in the searchbox","FAILURE");
//			System.out.println("Label name"+labelTm);
//			
//			snW.Wait(2000);
//			String Metadata=snW.getTextByXpath("AlertConsole_Metadata_FirstValue_Xpath");
//			System.out.println(Metadata);
//			if(Metadata.equalsIgnoreCase(labelTm))
//				Reporter.reportStep("Step 12: Alert decorator label matched successfully","SUCCESS");
//			else
//				Reporter.reportStep("Step 12: Alert decorator label did not match , hence failed","FAILURE");
//
//			
//			
			
			// Step 9: Log out
			// go out of the frame
			snW.switchToDefault();

			if(snW.clickByXpath("Logout_Xpath"))

				Reporter.reportStep("The Log out is clicked successfully.","SUCCESS");
			else
				Reporter.reportStep("The logout Failed", "FAILURE");


			status = "PASS";

		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

	@DataProvider(name = "OD_Stry0010938_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider
				.getSheet("OD_Stry0010938_Tc01");
		return arrayObject;
	}



}