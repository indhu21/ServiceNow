package com.punchit.scripts.gileadven;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class Ven_GLOD_STRY0010165_TC01 extends SuiteMethods{
	ServiceNowWrappers snW;

	@Test(dataProvider = "Ven_GLOD_STRY0010165_TC01",groups="OpsDirector")
	public void alertProfileIncidentAssignmentGroup(String regUser, String regPwd, String alertProfileName, String alertProfileDescription, String incidentAssignmentGroup, String owningGroup, String attribute) throws COSVisitorException,
	IOException, InterruptedException {

// Pre-requisities
snW = new ServiceNowWrappers(entityId);

try {
	// Step 0: Launch the application
	if (snW.launchApp(browserName, true))
		Reporter.reportStep("The browser:" + browserName + " launched successfully", "SUCCESS");
	else
		Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

	// Step 1: Log in to application
	if (snW.login(regUser, regPwd))
		Reporter.reportStep("Step 1: The login with username:"+ regUser + " is successful", "SUCCESS");
	else
		Reporter.reportStep("Step 1: The login with username:"+ regUser + " is not successful", "FAILURE");
	
	if (snW.selectMenu("Ops_Director","Registration_Menu", "Prof_Reg"))
		Reporter.reportStep("Step 2: The Alert Profile - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 2: The Alert Profile - menu could not be selected","FAILURE");

	// Switch to the main frame
	snW.switchToFrame("Frame_Main");
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss");
	String formattedDate = sdf.format(date);
	String a=alertProfileName+" "+formattedDate;
	
	if(snW.enterByXpath("Alert_Profile_Name_Xpath", a))
		Reporter.reportStep("Step 3: Name given to the new alert profile", "SUCCESS");
	else
		Reporter.reportStep("Step 3: Name not given to the new alert profile", "FAILURE");
	
	if(snW.enterByXpath("Alert_Profile_Description_Xpath1", alertProfileDescription))
		Reporter.reportStep("Step 4: Description given for the new alert profile", "SUCCESS");
	else
		Reporter.reportStep("Step 4: Description not given for the new alert profile", "FAILURE");
	
	if(snW.selectByVisibleTextById("autoClose", "Yes"))
		Reporter.reportStep("Step 5: Yes selected for Autoclose", "SUCCESS");
	else
		Reporter.reportStep("Step 5: Yes not selected for Autoclose", "FAILURE");
	
	//if(snW.selectByVisibleTextById("Alert_Profile_CIScope_Id", "CIAnythingScope"))
		if(snW.doubleCickByXpath("ALERTPROFILE_Registartion_CIscopes_xpath"))
		Reporter.reportStep("Step 6:  CI Scope is selected successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 6: CI Scope could not be selected", "FAILURE");

	if(snW.selectByVisibleTextById("Dy_Inc_Asn_Grp", "No"))
		Reporter.reportStep("Step 7: No selected for Dynamic Assignment Group", "SUCCESS");
	else
		Reporter.reportStep("Step 7: No not selected for Dynamic Assignment Group", "FAILURE");

	//if(snW.enterByXpath("Alert_Profile_IncidentAssignmentGroup_Xpath", incidentAssignmentGroup))
	if(snW.enterAndChoose("Alert_Profile_IncidentAssignmentGroup_Xpath_new_sparc", "**"))
		Reporter.reportStep("Step 8: Incident Assignment Group selected", "SUCCESS");
	else
		Reporter.reportStep("Step 8: Incident Assignment Group not selected", "FAILURE");

	if(snW.enterAndChoose("Alert_Profile_OwningGroup_Xpath_new_sparc", "**"))
		Reporter.reportStep("Step 9: Owning Group selected", "SUCCESS");
	else
		Reporter.reportStep("Step 9: Owning Group could not be selected", "FAILURE");
	
	Thread.sleep(3000);
	
	if(snW.selectByVisibleTextById("Alert_Reaction", "Create Incident"))
		Reporter.reportStep("Step 10: Default value selected", "SUCCESS");
	else
		Reporter.reportStep("Step 10: Default value not selected", "FAILURE");
	
	if(snW.clickByXpath("Alert_Profile_SubmitButton_Xpath"))
		Reporter.reportStep("Step 11: Submit Button clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 11: Submit Button not clicked", "FAILURE");
	
	Thread.sleep(2000);
	
	String numberText=snW.getAttributeById("Prof_Num","value");
	System.out.println(numberText);
	
	Actions action = new Actions(snW.getDriver());
	action.moveToElement(snW.getDriver().findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/span/div[2]/div[4]/table[1]/tbody/tr/td/div/table/tbody/tr/td[3]"))).doubleClick().perform();

	Thread.sleep(3000);
	
	try
	{
		//action.sendKeys(attribute).build().perform();
		snW.enterAndChoose("ALERTPROFILE_attribute_xpath", "**");
		Reporter.reportStep("Step 12: Attribute added", "SUCCESS");
	}
	catch(Exception e)
	{
			System.out.println(e);
			Reporter.reportStep("Step 12: Attribute not added", "FAILURE");
	}
	
//	snW.ActionsDriver("RETURN");
	if(!snW.clickById("ALERTPROFILE_attribute_right_id"))
		Reporter.reportStep("Right button could not be clicked", "FAILURE");
	
	if(!snW.clickById("CIS_UpdateButton_Id"))
		Reporter.reportStep("Update button could not be clicked", "FAILURE");
		
			
	
	Thread.sleep(3000);
	
	//String numberText=snW.getTextById("Alert_Profile_Number_Id");
	snW.switchToFrame("Frame_Nav");
	
	if (snW.selectMenu("Ops_Director","Configurations", "Alert_Profiles"))
		Reporter.reportStep("Step 13: The Alert Profile - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 13: The Alert Profile - menu could not be selected","FAILURE");
	
	snW.switchToFrame("Frame_Main");
	Thread.sleep(2000);
	//snW.clickLink(numberText, false)
	snW.selectByVisibleTextByXpath("Alert_Profiles_SearchDropdown_Xpath", "Number");
	snW.enterAndChoose("Alert_Profiles_Search_Xpath", numberText);
	
	if(snW.clickLink(numberText, false))
		Reporter.reportStep("Step 14: Profile selected", "SUCCESS");
	else
		Reporter.reportStep("Step 14: Profile not selected", "FAILURE");
		
			
	
	if(snW.clickByXpath("Alert_Profiles_SendForApproval_Xpath"))
		Reporter.reportStep("Step 15: Send For Approval clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 15: Send For Approval not clicked", "FAILURE");

	snW.switchToDefault();
	
	if (snW.clickByXpath("Logout_Xpath"))
		Reporter.reportStep("Step 16: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 16: The Log out could not be clicked.", "FAILURE");

	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "Ven_GLOD_STRY0010165_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("Ven_GLOD_STRY0010165_TC01");
		return arrayObject;
	}
}


