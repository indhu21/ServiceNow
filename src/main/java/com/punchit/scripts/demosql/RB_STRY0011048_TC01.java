/*import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.DataInputProvide_MySQL;
import testng.SuiteMethods;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011048_TC01 extends SuiteMethods {
	ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011048_TC01")
	public void appProperties(String Password, String User_ID,String Assignedtome_filtercondition,String Unassigned_filtercondition,String Allopenrunbook_filtercondition,String Allrunbook_filter) throws COSVisitorException, IOException, InterruptedException {
		//User_ID
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			//System.out.println("user id is"+regUser);
			
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");
//
//			// Step 1: Log in to application
			if (snW.login(User_ID, Password))
				Reporter.reportStep("The login with username:"+ User_ID + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ Password + " is not successful", "FAILURE");
			snW.Wait(2000);
//			
			// Step 2: In application navigator expand OpsDirector/Registration to select Assigned_to_me
			if (snW.selectMenuFromMainHeader("RunBook", "Assigned_to_me"))
				Reporter.reportStep("Runbook app button is opened and the Assigned_To_Me - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("The Assigned_To_Me - menu could not be selected","FAILURE");

			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			
			//step 3: get the filter condition
			String x=snW.getTextById("RunBook_Assignedtome_filter_id");
			//System.out.println("text is "+ x);
			if(x.equalsIgnoreCase(Assignedtome_filtercondition))
				Reporter.reportStep("Filter conditin are set as expected : "  + x ,"SUCCESS");
			else
				Reporter.reportStep("Filter conditions for Assigned to me are not set as Expected","FAILURE");
			  
			//step 4:In application navigator expand OpsDirector/Registration to select unassigned	
			if (snW.selectMenuFromMainHeader("RunBook", "Unassigned"))
			Reporter.reportStep("Unassigned - menu selected successfully","SUCCESS");
		    else
			Reporter.reportStep("Unassigned - menu could not be selected","FAILURE");
			
			snW.switchToFrame("Frame_Main");
			
			//step 5 : check the filter condition
			String x1=snW.getTextById("RunBook_Assignedtome_filter_id");
			//System.out.println(x1);
			if(x1.equalsIgnoreCase(Unassigned_filtercondition))
				Reporter.reportStep("Filter condition  for Unassigned are set as expected : "  + x1 ,"SUCCESS");
			else
				Reporter.reportStep("Filter condition  for Unassigned are not set as expected :" + x1,"FAILURE");
			//status="Pass";
			
			// step 6 : SelectAll_open_runbooks
			if (snW.selectMenuFromMainHeader("RunBook", "All_open_runbooks"))
		     Reporter.reportStep("All open Runbook  - menu selected successfully","SUCCESS");
		   else
     		Reporter.reportStep("All open Runbook - menu could not be selected","FAILURE");
				
			snW.switchToFrame("Frame_Main");
				
			//step 7 : check the filter condition for all open run books
			String x2=snW.getTextById("RunBook_Assignedtome_filter_id");
			System.out.println(x2);
			if(x2.equalsIgnoreCase(Allopenrunbook_filtercondition))
			Reporter.reportStep("Filter condition  for All open Runbook are set as expected : "  + x2 ,"SUCCESS");
			else
			Reporter.reportStep("Filter condition  for All open Runbook are  not set as expected :" + x2,"FAILURE");
			
			// step 8: select all runbooks
			if (snW.selectMenuFromMainHeader("RunBook", "All_runbook"))
				Reporter.reportStep("All  Runbook  - menu selected successfully","SUCCESS");
			else
				Reporter.reportStep("All Runbook - menu could not be selected","FAILURE");

			snW.switchToFrame("Frame_Main");
			
			// step 9 : check the filter condition for all run book
			String x3=snW.getTextById("RunBook_Assignedtome_filter_id");
			System.out.println(x3);
			if(x3.equalsIgnoreCase(Allrunbook_filter))
			Reporter.reportStep("Filter condition  for All Runbook are set as expected : "  + x3 ,"SUCCESS");
			else
			Reporter.reportStep("Filter condition  for All Runbook are  not set as expected :" + x3,"FAILURE");
			
			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");


			status = "PASS";

		}
		finally
		{
			// close the browser
		 snW.quitBrowser();		 
		}
	}
		
	@DataProvider(name = "RB_STRY0011048_TC01")
		public String[][] fetchData() throws IOException 
		{
			String[] Parameters={"Password", "User_ID","Assignedtome_filtercondition","Unassigned_filtercondition","Allopenrunbook_filtercondition","Allrunbook_filter"};
			String[][] arrayObject = DataInputProvide_MySQL.getDataValues("RB_STRY0011048_TC01",Parameters);
			return arrayObject;
			//Password
		}
}
	



*/