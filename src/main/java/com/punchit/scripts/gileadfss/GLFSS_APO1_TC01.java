package com.punchit.scripts.gileadfss;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_APO1_TC01 extends SuiteMethods{
	//private static final String EVENTS_CreatedLink_Xpath = null;
	ServiceNowWrappers snW;

	@Test(dataProvider = "GLFSS_APO1_TC01",groups="GLFSS")
	public void createNewTicket(String regUser, String regPwd, String affectedUser, String category, String type, String item, String shortDescription) throws COSVisitorException,
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
	
	if (snW.selectMenuFromMainHeader("FSS_Task", "FSS_Tasks"))
		Reporter.reportStep("Step 2: The FSS Tasks - menu selected successfully","SUCCESS");
	else
		Reporter.reportStep("Step 2: The FSS Tasks - menu could not be selected","FAILURE");

	snW.switchToFrame("Frame_Main");
	
	if(snW.clickById("FSS_FSSTasks_NewButton_Id"))
		Reporter.reportStep("Step 3: New Button clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 3: User not able to click New Button", "FAILURE");
	
	String ticketNumberAPO1TC01=snW.getAttributeByXpath("FSS_FSSTasks_Number_Xpath","value");
	System.out.println(ticketNumberAPO1TC01);
	
	if(snW.enterByXpath("FSS_FSSTasks_AffectedUser_Xpath", affectedUser))
		Reporter.reportStep("Step 4: Affected User field selected successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 4: User not able to select the Affected User field", "FAILURE");
	
	Thread.sleep(2000);
	
	if(snW.enterByXpath("FSS_FSSTasks_Category_Xpath", category))
		Reporter.reportStep("Step 5: Category field entered successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 5: User not able to select the Category field", "FAILURE");
	
	Thread.sleep(2000);
	
	if(snW.enterByXpath("FSS_FSSTasks_Type_Xpath", type))
		Reporter.reportStep("Step 6: Type field entered successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 6: User not able to select the Type field", "FAILURE");
	
	Thread.sleep(2000);
	
	if(snW.enterById("FSS_FSSTasks_Item_Id", item))
		Reporter.reportStep("Step 7: Item field entered successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 7: User not able to select the Item field", "FAILURE");
	
	Thread.sleep(2000);
	
	if(snW.enterByXpath("FSS_FSSTasks_ShortDescription_Xpath", shortDescription))
		Reporter.reportStep("Step 8: Short Description field entered successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 8: User not able to enter the Short Description field", "FAILURE");
	
	Thread.sleep(5000);
	//snW.sendKeys("FSS_FSSTasks_InvoiceNumber_Xpath", invoiceNumber)
	if(snW.enterByXpath("FSS_FSSTasks_InvoiceNumber_Xpath", "1234"))
		Reporter.reportStep("Step 9: Invoice Number field entered successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 9: User not able to enter the Invoice Number field", "FAILURE");
	
	if(snW.clickById("FSS_FSSTasks_SubmitButton_Id"))
		Reporter.reportStep("Step 10: Submit Button clicked successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 10: User not able to click Submit Button", "FAILURE");

	snW.selectByVisibleTextByXpath("FSS_FSSTasks_SelectNumberDropDown_Xpath", "Number");
	Thread.sleep(2000);
	snW.enterByXpath("FSS_FSSTasks_SearchBox_Xpath", ticketNumberAPO1TC01);
	Thread.sleep(2000);
	snW.PresEnter();
	
	String searchedNumber=snW.getTextByXpath("FSS_FSSTasks_SearchedNumberAPO2_Xpath");
	System.out.println(searchedNumber);
	
	if(searchedNumber.equalsIgnoreCase(ticketNumberAPO1TC01))
		Reporter.reportStep("Step 11: The new FSS task is saved successfully", "SUCCESS");
	else
		Reporter.reportStep("Step 11: User not able to create new FSS task", "FAILURE");
	
	snW.switchToDefault();
	
	if (snW.clickByXpath("FSS_FSSTasks_LogoutButton_Xpath"))
		Reporter.reportStep("Step 12: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter.reportStep("Step 12: The Log out could not be clicked.", "FAILURE");
	
	status = "PASS";
}
finally {
	// close the browser
	snW.quitBrowser();
}
}
	@DataProvider(name = "GLFSS_APO1_TC01")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_APO1_TC01");
		return arrayObject;
	}
}