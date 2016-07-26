package com.punchit.scripts.gileadrb;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLRB_STRY0011053_TC01 extends SuiteMethods{
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
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
			else
				Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
			
			Thread.sleep(2000);
			// step 3 :check if Templates link is available
			if (snW.selectMenu("RunBook","RunBook_Definition", "Templates"))
				Reporter.reportStep("Step 2: The Templates  - menu selected successfully ","SUCCESS");
			else
				Reporter.reportStep("Step 2: The Templates  - menu is not  displayed ","FAILURE");	
						
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");
			 
			// step 4: click on new Button
			Thread.sleep(2000);
			if(snW.clickById("Runbook_Templates_newbutton_id"))
				Reporter.reportStep("Step 3: New Button is clicked successully ","SUCCESS");
			else
				Reporter.reportStep("Step 3: New Button could not be clicked","FAILURE");	
			
			// step 5: check the mendatory fields 
			String x=snW.getAttributeById("Runbook_Templates_new_name_id","mandatory" );
			System.out.println("Attribute is"+x);
			if(!x.equalsIgnoreCase("true"))
				Reporter.reportStep("Step 4: Name  field is not mendatory","FAILURE");
			
			String x1=snW.getAttributeById("Runbook_Templates_new_SLA_id","mandatory" );
			System.out.println("Attribute is"+x);
			if(!x1.equalsIgnoreCase("true"))
				Reporter.reportStep("Step 4: Name  field is mendatory but SLA field is not Mendatory","FAILURE");
			
			String x2=snW.getAttributeById("Runbook_Templates_new_owner_id","mandatory" );
			System.out.println("Attribute is"+x);
			if(x2.equalsIgnoreCase("true"))
				Reporter.reportStep("Step 4: Name ,Owner and SLA fields are mendatory","SUCCESS");
			else
				Reporter.reportStep("Step 4: Name and Owner fields are mendatory but SLA field is not mendatory","SUCCESS");
			
			// verify non-mendat fields 
			String x3=snW.getAttributeById("Runbook_Templates_new_TYPE_id","mandatory" );
			System.out.println("Attribute is"+x3);
			if(x3.equalsIgnoreCase("true"))
				Reporter.reportStep("Step 5: Type  field is mendatory","FAILURE");
			
			String x4=snW.getAttributeById("Runbook_Templates_new_DefaultAssignmentgroup_id","mandatory" );
			System.out.println("Attribute is"+x4);
			if(x4.equalsIgnoreCase("true"))
				Reporter.reportStep("Step 5: Type field is not  mendatory but Default Assignment field is  Mendatory","FAILURE");
			
			String x5=snW.getAttributeById("Runbook_Templates_new_Active_id","mandatory" );
			System.out.println("Attribute is"+x5);
			if(x5.equalsIgnoreCase("true"))
				Reporter.reportStep("Step 5: Typw ,Default Assignment fields are not mendatory while Active ID field is Mendatory","FAILURE");
			
			String x6=snW.getAttributeById("Runbook_Templates_new_Description_id","mandatory" );
			System.out.println("Attribute is"+x6);
			if(x6.equalsIgnoreCase("true"))
				Reporter.reportStep("Step 5: Type , Default Assignment,Active ID  are non Mendatory as Expected while Description field is mendatory","FAILURE");
			
			else
				Reporter.reportStep("Step 5: Type , Default Assignment,Active ID and Description fields are non Mendatory as Expected","SUCCESS");
			
			// create a new template
			name=name+snW.getCurrentTime();
			if(!snW.enterById("Runbook_Templates_new_name_entervalue_id", name))
				Reporter.reportStep("Step 6:Name could not be entered ","FAILURE");
			
			if(!snW.enterAndChoose("Runbook_Templates_new_owner_entervalue_xpath", "**"))
				Reporter.reportStep("Step 6:Owner filed  could not be entered ","FAILURE");
			
			if(!snW.enterAndChoose("Runbook_Templates_new_SLA_entervalue_xpath", "**"))
				Reporter.reportStep("Step 6:SLA filed  could not be entered ","FAILURE");
			
			//if(!snW.SelectDropown_byXpath("Runbook_Templates_new_TYPE_entervalue_xpath"))
			//if(snW.selectByVisibleTextByXpath("Runbook_Templates_new_TYPE_entervalue_xpath", "NewType"))
			// if(snW.selectByVisibleTextByXpath("Runbook_Schedule_CIType_Xpath", citype))
			//	Reporter.reportStep("Step 6:Type dropdown could not be selected","FAILURE");
			
			if(!snW.enterAndChoose("Runbook_Templates_new_DefaultAssignmentgroup_entervalue_xpath", "**"))
				Reporter.reportStep("Step 6:Default Assignment Group  could not be selected","FAILURE");
			
			if(!snW.clickById("Runbook_Templates_new_Active_entervalue_id"))
				Reporter.reportStep("Step 6:Active box  could not be selected","FAILURE");
			
			if(snW.enterById("Runbook_Templates_new_Description_entervalue_id", description))
				Reporter.reportStep("Step 6: All the filed are entered successfully","SUCCESS");
			else
				Reporter.reportStep("Step 6: All the filed are enterd successfully except Description","SUCCESS");
			
			// click on submit
			if(snW.clickById("Runbook_Templates_new_submit_id"))
				Reporter.reportStep("Step 7: Submit button clicked ","SUCCESS");
			else
				Reporter.reportStep("Step 7: Submit button could not be clicked  ","FAILURE");
			
			
			//Search with name
			if(!snW.enterByXpath("RunBook_Templates_Searchbox_xpath", name))
				Reporter.reportStep("Step 7: Name could not be entered to be searched ","FAILURE");

			// Press enter key
            snW.PresEnter();
            
            String a=snW.getTextByXpath("RunBook_Unassigned_firstrunbbok_xpath");
            System.out.println(a);
            if(a.equalsIgnoreCase(name))
            	Reporter.reportStep("Step 8: New Template is available in the List hence Passed","SUCCESS");
            else 
            	Reporter.reportStep("Step 8: New Template is not  available in the List hence Failed","SUCCESS");
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
	










