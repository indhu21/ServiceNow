package ServiceNow_Integration;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ServiceNow_Integration.suitemethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class testcase extends suitemethods {
	// Create Instance
	ServiceNowWrappers snW;
	
	@Test(dataProvider = "GLOD_Stry_0010130_Tc01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");

			// Step 2: In application navigator expand Ops Consoles and select My Alerts.
			if (snW.selectMenu("Ops_Director","Ops_Consoles", "My_Alerts"))
				Reporter.reportStep("Step 2: The My Alerts menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 2: The My Alerts menu could not be selected","FAILURE");
			
			
			//Wait for the Alerts to load
			snW.Wait(3000);
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			//Step 3a: Select the first alert profile to right click on it.
			String My_Alert_no = (snW.getTextByXpath("Myalert_FirstAlert_xpath"));
			if(My_Alert_no == null)
				Reporter.reportStep("Step 3a: No Alerts Present under My Alerts, ","FAILURE");
			
			//Step 3b: Right Click on the the first alert available.
			if(snW.rightClickByXpath("Myalert_FirstAlert_xpath"))
				Reporter.reportStep("Step 3b: Right Click is performed on the alert ticket--"+ My_Alert_no+ "--in My alerts", "SUCCESS");
			else
			{
				status="INSUFFICIENT DATA";
				Reporter.reportStep("Step 3b: Right Click could not be performed on the alert ticket--"+ My_Alert_no+ "--in My alerts", "FAILURE");
			}
			//Switch to active element to Link to Parent.
			Thread.sleep(1000);
			snW.getDriver().switchTo().activeElement();
				
		   //Step 4: Click on the Magnification Symbol to display the alert tickets to make a parent ticket.
			if(snW.clickByXpath("Link_to_parent_Xpath"))
				Reporter.reportStep("Step 4: Link to parent is Successfully clicked", "SUCCESS");
			else
				Reporter.reportStep("Step 4: Link to parent could not be clicked", "FAILURE");
			
			
			//Switch to active element to Link to Parent.
			Thread.sleep(2000);
			snW.getDriver().switchTo().activeElement();

			
			Thread.sleep(2000);
			
			//Enter an alert number to link to parent
			if(snW.enterByXpath("MyAlerts_Linktoparent_entry_Xpath", "ALT"))
			{
			
			
			Thread.sleep(1000);
			snW.pressKey(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			snW.pressKey(Keys.TAB);
			Reporter.reportStep("Step 5a: Parent alert is selected successfully", "SUCCESS");
			}
			else
				Reporter.reportStep("Step 5a: Parent alert could not be selected ", "FAILURE");
			
			String Parent_Alert_no= snW.getAttributeByXpath("MyAlerts_Linktoparent_entry_Xpath", "value");
			System.out.println(Parent_Alert_no);
			snW.clickByXpath("Ok_Button_Xpath");
			//Step 5a: Click OK to confirm Parent alert Selection.
		
	        
			Thread.sleep(2000);
			//Step 6: Check the Child alert is not available in My Alerts menu.
	       snW.getDriver().findElementByLinkText("My Alerts").click(); 
	       Thread.sleep(2000);
	       snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number");
	    	snW.enterAndChoose("Alert_Num_Filter_Xpath", My_Alert_no);
	    	
	        if(snW.IsElementNotPresentByXpath("Paralt_xpath"))
	        	Reporter.reportStep("Step 6: The Child alert no--"+My_Alert_no+"--is not displayed under My Alerts as expected", "SUCCESS");	
	        else
	        	Reporter.reportStep("Step 6: The Child alert no--"+My_Alert_no+"--is displayed under My Alerts", "FAILURE");
	        
	        
	        // Step 6a GO to Alerts under data.
	        if(snW.selectMenu("Ops_Director","Data", "Alerts"))
	        	Reporter.reportStep("Step 6a: The Alerts menu is selected successfully", "SUCCESS");
	        else
	        	Reporter.reportStep("Step 6a: The Alerts menu could  not be selected ", "FAILURE");
			
	        //Switch to main frame.
	        Thread.sleep(3000);
	        snW.switchToFrame("Frame_Main");
	        
	        //Select the Alert Number.
	        if(snW.selectByVisibleTextByXpath("Num_dropdown_Xpath", "Number"))
				Reporter.reportStep("Step 6b: Number is selected as dropdown option","SUCCESS");
			else
				Reporter.reportStep("Step 6b: Number is not selected as dropdown option","FAILURE");
	        
	        if(snW.enterAndChoose("Alert_Num_Filter_Xpath",Parent_Alert_no ))
				Reporter.reportStep("Step 6c: The Parent alert is selected successfully","SUCCESS");
			else
				Reporter.reportStep("Step 6c: The Parent Alert  could not be selected","FAILURE");
	        
	        Thread.sleep(3000);
	        
	        if(snW.clickByXpath("Paralt_xpath"))
				Reporter.reportStep("Step 6d: Alert No--"+Parent_Alert_no+"-- is selected as a parent alert", "SUCCESS");
			else
				Reporter.reportStep("Step 6d: Parent alert Could no be selected", "FAILURE");
		
		    Thread.sleep(5000);

		    
		    
		    //enter number as the search Criteria.
		    	snW.selectByVisibleTextByXpath("Alerts_ChildAlert_rellist_Search_Xpath", "Number");
		    	snW.enterAndChoose("Alerts_ChildAlert_rellist_SearchValue_Xpath", My_Alert_no);
		  
		        Thread.sleep(2000);
		   
		     if(snW.VerifyByLink(My_Alert_no, true))
		    	   Reporter.reportStep("Step7: Child Alert no --"+My_Alert_no+"-- is present under Parent alert", "SUCCESS");
		       else
		    	   Reporter.reportStep("Step7: Child Alert no --"+My_Alert_no+"-- is not present under Parent alert", "FAILURE");
		   
		  // go out of the frame
	            snW.switchToDefault();

	            // Log out
	            if(!snW.clickByXpath("Logout_Xpath"))
	             Reporter.reportStep("The logout Failed", "FAILURE");
	    
	            status="PASS";
			
		
		}
		finally{
			// close the browser
			snW.quitBrowser();
			
		}
}
	@DataProvider(name = "GLOD_Stry_0010130_Tc01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLOD_Stry_0010130_Tc01");
		return arrayObject;
	}
}

