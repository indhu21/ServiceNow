package com.punchit.scripts.demosuite3;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import testng.SuiteMethods_1;
import utils.DataInputProvider;
import utils.Reporter;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class INC_Stry000000_Tc13 extends SuiteMethods_1	 {

    // Create Instance
	   ServiceNowWrappers snW;
			
@Test(dataProvider = "DEMOINC_Stry000000_Tc13",groups="Incident")
public void appProperties(String regUser, String regPwd, String Impact1, String Urgency1,
		  String Priority1, String Impact2, String Urgency2,
		  String Priority2, String Impact3, String Urgency3,
		  String Priority3, String Impact4, String Urgency4,
		  String Priority4, String Impact5, String Urgency5,
		  String Priority5, String Impact6, String Urgency6,
		  String Priority6, String Impact7, String Urgency7,
		  String Priority7, String Impact8, String Urgency8,
		  String Priority8, String Impact9, String Urgency9,
		  String Priority9 ) throws COSVisitorException, IOException, InterruptedException {
				
// Prerequisites
snW = new ServiceNowWrappers(entityId);
				
try {
					
	// Pre-requisities 
	snW = new ServiceNowWrappers(entityId);

	// Step 0: Launch the application
		snW.launchApp(browserName, true);

	// Step 1: Log in to application
	if (snW.login(regUser, regPwd))
		Reporter1.reportStep("Step 1: The login with username:"
							+ regUser + " is successful", "SUCCESS");
	else
		Reporter1.reportStep("Step 1: The login with username:"
							+ regUser + " is not successful", "FAILURE");

	// Step 2: Open Incident menu and click create new incident
	
	if(snW.selectMenu("INCMENU_ALLMENU", "INCMENU_NEW"))
	   Reporter1.reportStep("Step 2: The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
	else
		Reporter1.reportStep("Step 2: The Alert Console under OpsConsole - menu could not be selected","FAILURE");

	// Switch to the main frame
	     snW.switchToFrame("Frame_Main");
	     
	//Click on Set priority button
		//snW.clickByXpath("Incident_SetPriorityButton_Xpath");
		
	// Enter impact Extensive/Widespread(1) and urgency 3 - Low with priority 3 - Moderate
	     
	if(!snW.selectByVisibleTextById("CREATEINC_Impact_Id", Impact1))
		Reporter1.reportStep("Step 3: Impact with value +Impact1+ could not be selected", "FAILURE");
	
	snW.Wait(2000);
	if(!snW.selectByVisibleTextById("CREATEINC_Urgency_Id", Urgency1))
		Reporter1.reportStep("Step 3: Urgency with value +Urgency1+ could not be selected", "FAILURE");
	
	snW.Wait(2000);
 String P1=snW.getDefaultValueById("CREATEINC_Priority_Id");
	System.out.println(P1);

	
 if((Priority1).equalsIgnoreCase(P1))
 	Reporter1.reportStep("Step 3: Priority field is set as "+P1, "SUCCESS");
	else
		Reporter1.reportStep("Step 3: Priority field is not set as expected", "FAILURE");
 
// snW.Wait(2000);
// 
////Enter impact Extensive/Widespread(1) and urgency 2 - Medium with priority 2 - High
// 
//  if(!snW.selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact2))
//		Reporter.reportStep("Step 4: Impact with value" +Impact2+"could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	if(!snW.selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency2))
//		Reporter.reportStep("Step 4: Urgency with value "+Urgency2+ "could not be selected", "FAILURE");
//	
//	snW.Wait(4000);
// String P2=snW.getDefaultValueByXpath("Incident_Priority_Xpath");
//	System.out.println(P2);
//	
// if((Priority2).equalsIgnoreCase(P2))
// 	Reporter.reportStep("Step 4: Priority field is set as "+P2, "SUCCESS");
//	else
//		Reporter.reportStep("Step 4: Priority field is not set as expected", "FAILURE");
// 
// snW.Wait(2000);
// 
////Enter impact Extensive/Widespread(1) and urgency 1 - High with priority 1 - Critical  alert
// 
// if(!snW.selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact3))
//		Reporter.reportStep("Step 5: Impact with value" +Impact3+"could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	if(!snW.selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency3))
//		Reporter.reportStep("Step 5: Urgency with value"+Urgency3+"could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	 try{   
//		snW.getDriver().switchTo().alert().accept();  
//  }catch(Exception e){ 
//		System.out.println("unexpected alert not present");   
//  }
// String P3=snW.getDefaultValueByXpath("Incident_Priority_Xpath");
//	System.out.println(P3);
//	
// if((Priority3).equalsIgnoreCase(P3))
// 	Reporter.reportStep("Step 5: Priority field is set as "+P3, "SUCCESS");
//	else
//		Reporter.reportStep("Step 5: Priority field is not set as expected", "FAILURE");
// 
// snW.Wait(2000);
//
// // Enter impact Significant/Large(2) and urgency 3 - Low with priority 4 - Low
// 
// if(!snW.selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact4))
//		Reporter.reportStep("Step 6: Impact with value "+Impact4+" could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	if(!snW.selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency4))
//		Reporter.reportStep("Step 6: Urgency with value "+Urgency4+"could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
// String P4=snW.getDefaultValueByXpath("Incident_Priority_Xpath");
//	System.out.println(P4);
//	
// if((Priority4).equalsIgnoreCase(P4))
// 	Reporter.reportStep("Step 6: Priority field is set as "+P4, "SUCCESS");
//	else
//		Reporter.reportStep("Step 6: Priority field is not set as expected", "FAILURE");
// 
// snW.Wait(2000);
//
// // Enter impact Significant/Large(2) and urgency 2 - Medium with priority 3 - Moderate
//
// if(!snW.selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact5))
//		Reporter.reportStep("Step 7: Impact with value "+Impact5+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	if(!snW.selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency5))
//		Reporter.reportStep("Step 7: Urgency with value "+Urgency5+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
// String P5=snW.getDefaultValueByXpath("Incident_Priority_Xpath");
//	System.out.println(P5);
//	
// if((Priority5).equalsIgnoreCase(P5))
// 	Reporter.reportStep("Step 7: Priority field is set as "+P5, "SUCCESS");
//	else
//		Reporter.reportStep("Step 7: Priority field is not set as expected", "FAILURE");
// 
// snW.Wait(2000);
//
// // Enter impact Significant/Large(2) and urgency 1 - High with priority 2 - High
// 
// if(!snW.selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact6))
//		Reporter.reportStep("Step 8: Impact with value "+Impact6+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	if(!snW.selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency6))
//		Reporter.reportStep("Step 8: Urgency with value" +Urgency6+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
// String P6=snW.getDefaultValueByXpath("Incident_Priority_Xpath");
//	System.out.println(P6);
//	
// if((Priority6).equalsIgnoreCase(P6))
// 	Reporter.reportStep("Step 8: Priority field is set as "+P6, "SUCCESS");
//	else
//		Reporter.reportStep("Step 8: Priority field is not set as expected", "FAILURE");
// 
// snW.Wait(2000);
//// Enter impact Moderate/Limited (3) and urgency 3 - Low with priority 4 - Low
//
// if(!snW.selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact7))
//		Reporter.reportStep("Step 9: Impact with value "+Impact7+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	if(!snW.selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency7))
//		Reporter.reportStep("Step 9: Urgency with value" +Urgency7+" could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
// String P7=snW.getDefaultValueByXpath("Incident_Priority_Xpath");
//	System.out.println(P7);
//	
// if((Priority7).equalsIgnoreCase(P7))
// 	Reporter.reportStep("Step 9: Priority field is set as "+P7, "SUCCESS");
//	else
//		Reporter.reportStep("Step 9: Priority field is not set as expected", "FAILURE");
// 
// snW.Wait(2000);
//
////Enter impact Moderate/Limited (3) and urgency 2 - Medium with priority 4 - Low
//
// if(!snW.selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact8))
//		Reporter.reportStep("Step 10: Impact with value "+Impact8+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	if(!snW.selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency8))
//		Reporter.reportStep("Step 10: Urgency with value "+Urgency8+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
// String P8=snW.getDefaultValueByXpath("Incident_Priority_Xpath");
//	System.out.println(P8);
//	
// if((Priority8).equalsIgnoreCase(P8))
// 	Reporter.reportStep("Step 10: Priority field is set as "+P8, "SUCCESS");
//	else
//		Reporter.reportStep("Step 10: Priority field is not set as expected", "FAILURE");
// 
// snW.Wait(2000);
//
//// Enter impact Moderate/Limited (3) and urgency 1 - High with priority 3 - Moderate
//
// if(!snW.selectByVisibleTextByXpath("Incident_Impact_Xpath", Impact9))
//		Reporter.reportStep("Step 11: Impact with value "+Impact9+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
//	if(!snW.selectByVisibleTextByXpath("Incident_Urgency_Xpath", Urgency9))
//		Reporter.reportStep("Step 11: Urgency with value "+Urgency9+ "could not be selected", "FAILURE");
//	
//	snW.Wait(2000);
// String P9=snW.getDefaultValueByXpath("Incident_Priority_Xpath");
//	System.out.println(P9);
//	
// if((Priority9).equalsIgnoreCase(P9))
// 	Reporter.reportStep("Step 11: Priority field is set as "+P9, "SUCCESS");
//	else
//		Reporter.reportStep("Step 11: Priority field is not set as expected", "FAILURE");
// 
// snW.Wait(2000);     
				
// go out of the frame
	snW.switchToDefault();

// Log out
	if(snW.clickByXpath("Logout_Xpath"))
		Reporter1.reportStep("Step 5: The Log out is clicked successfully.","SUCCESS");
	else
		Reporter1.reportStep("Step 5: The logout Failed", "FAILURE");		
						
	status = "PASS";

}
	finally{
	// close the browser
	snW.quitBrowser();		
}
}
				

@DataProvider(name = "DEMOINC_Stry000000_Tc13")
public Object[][] fetchData() throws IOException {
Object[][] arrayObject = DataInputProvider.getSheet("DEMOINC_Stry000000_Tc13");
return arrayObject;
				
}
}
			


