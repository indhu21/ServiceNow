package com.punchit.scripts.rb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class RB_STRY0011053_TC01 extends SuiteMethods{
ServiceNowWrappers snW;
	
	@Test(dataProvider = "RB_STRY0011053_TC01",groups="OpsDirector")
	public void appProperties(String regUser, String regPwd,String name,String description) throws COSVisitorException, IOException, InterruptedException {
		
		// Prerequisites
		snW = new ServiceNowWrappers(entityId);
		
		try {
			
			if (snW.launchApp(browserName, true))
				Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
			else
				Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			// Step 1: Log in to application
			if (snW.login(regUser, regPwd))
				Reporter.reportStep("The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("The login with username:"+ regUser + " is not successful", "FAILURE");
			
			Thread.sleep(2000);
			// step 3 :check if Templates link is available
			if (snW.selectMenu("RunBook","RunBook_Definition", "Templates"))
				Reporter.reportStep("The Templates  - menu selected successfully ","SUCCESS");
			else
				Reporter.reportStep("The Templates  - menu is not  displayed ","FAILURE");	
						
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			 
			// step 4: click on new Button
			Thread.sleep(2000);
			if(snW.clickById("Runbook_Templates_newbutton_id"))
				Reporter.reportStep("New Button is clicked successully ","SUCCESS");
			else
				Reporter.reportStep("New Button could not be clicked","FAILURE");	
			
			// step 5: check the mandatory fields 
			String x=snW.getAttributeById("Runbook_Templates_new_name_id","mandatory" );
			System.out.println("Attribute is"+x);
			if(!x.equalsIgnoreCase("true"))
				Reporter.reportStep("Name  field is not mandatory","FAILURE");
			
			String x1=snW.getAttributeById("Runbook_Templates_new_SLA_id","mandatory" );
			System.out.println("Attribute is"+x);
			if(!x1.equalsIgnoreCase("true"))
				Reporter.reportStep("Name  field is mandatory but SLA field is not mandatory","FAILURE");
			
			String x2=snW.getAttributeById("Runbook_Templates_new_owner_id","mandatory" );
			System.out.println("Attribute is"+x);
			if(x2.equalsIgnoreCase("true"))
				Reporter.reportStep("Name ,Owner and SLA fields are mandatory","SUCCESS");
			else
				Reporter.reportStep("Name and Owner fields are mandatory but SLA field is not mandatory","SUCCESS");
			
			// verify non-mendat fields 
			String x3=snW.getAttributeById("Runbook_Templates_new_TYPE_id","mandatory" );
			System.out.println("Attribute is"+x3);
			if(x3.equalsIgnoreCase("true"))
				Reporter.reportStep("Type  field is mandatory","FAILURE");
			
			String x4=snW.getAttributeById("Runbook_Templates_new_DefaultAssignmentgroup_id","mandatory" );
			System.out.println("Attribute is"+x4);
			if(x4.equalsIgnoreCase("true"))
				Reporter.reportStep("Type field is not  mandatory but Default Assignment field is  mandatory","FAILURE");
			
			String x5=snW.getAttributeById("Runbook_Templates_new_Active_id","mandatory" );
			System.out.println("Attribute is"+x5);
			if(x5.equalsIgnoreCase("true"))
				Reporter.reportStep("Type ,Default Assignment fields are not mandatory while Active ID field is mandatory","FAILURE");
			
			String x6=snW.getAttributeById("Runbook_Templates_new_Description_id","mandatory" );
			System.out.println("Attribute is"+x6);
			if(x6.equalsIgnoreCase("true"))
				Reporter.reportStep("Type , Default Assignment,Active ID  are non mandatory as Expected while Description field is mandatory","FAILURE");
			
			else
				Reporter.reportStep("Type , Default Assignment,Active ID and Description fields are non mandatory as Expected","SUCCESS");
			
			// create a new template
			name=name+snW.getCurrentTime();
			if(!snW.enterById("Runbook_Templates_new_name_entervalue_id", name))
				Reporter.reportStep("Name could not be entered ","FAILURE");
			
			if(!snW.enterAndChoose("Runbook_owningGroup_xpath", "**"))
				Reporter.reportStep("Owner filed  could not be entered ","FAILURE");
			
			if(!snW.enterAndChoose("Runbook_SLA_xpath", "**"))
				Reporter.reportStep("SLA filed  could not be entered ","FAILURE");
			
			//if(!snW.SelectDropown_byXpath("Runbook_Templates_new_TYPE_entervalue_xpath"))
			//if(snW.selectByVisibleTextByXpath("Runbook_Templates_new_TYPE_entervalue_xpath", "NewType"))
				//Reporter.reportStep("Step 6:Type dropdown could not be selected","FAILURE");
			
			if(!snW.enterAndChoose("Runbook_AssGroup_xpath", "**"))
				Reporter.reportStep("Default Assignment Group  could not be selected","FAILURE");
			
			if(!snW.clickById("Runbook_Templates_new_Active_entervalue_id"))
				Reporter.reportStep("Active box  could not be selected","FAILURE");
			
			if(!snW.enterById("Runbook_Templates_new_Description_entervalue_id", description))
				Reporter.reportStep("All the filled are enterd successfully except Description","FAILURE");
			
			// click on submit
			if(snW.clickById("Runbook_Templates_new_submit_id"))
				Reporter.reportStep("All Fields are entered and submit button is clicked successfully  ","SUCCESS");
			else
				Reporter.reportStep("Submit button could not be clicked  ","FAILURE");
			
			
			//Search with name
			if(!snW.enterByXpath("RunBook_Templates_Searchbox_xpath", name))
				Reporter.reportStep("Name could not be entered to be searched ","FAILURE");

			// Press enter key
            snW.PresEnter();
            
            String a=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
            System.out.println(a);
            if(a.equalsIgnoreCase(name))
            	Reporter.reportStep("New Template is available in the List hence Passed","SUCCESS");
            else 
            	Reporter.reportStep("New Template is not  available in the List hence Failed","SUCCESS");
            // go out of the frame
            snW.switchToDefault();

            // Log out
            if(!snW.clickByXpath("Logout_Xpath"))
            	Reporter.reportStep("The logout Failed", "FAILURE");


            status = "PASS";
		}
		finally{
			// close the browser
		snW.quitBrowser();		
		}
	}
		@DataProvider(name = "RB_STRY0011053_TC01")
		public Object[][] fetchData() throws IOException {
			Object[][] arrayObject = DataInputProvider.getSheet("RB_STRY0011053_TC01");
			return arrayObject;
		}
}
	










