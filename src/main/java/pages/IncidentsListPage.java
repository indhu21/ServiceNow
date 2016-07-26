package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class IncidentsListPage extends ListPage{

	private final RemoteWebDriver driver;


	public IncidentsListPage(RemoteWebDriver driver) {
		  
		  super(driver);
		  this.driver = driver;
		  resetImplicitWait(5);
		  switchToDefault();
		  switchToMainFrame();
		  // Check that we're on the right page.
		  if (!isExistByXpath("NavBar1_Xpath")) {
		   Reporter.reportStep("This is not the Incident List page", "FAILURE");
		  }
		 
		  resetImplicitWait(30);
		 }
	public IncidentsListPage verifyCIComponentFieldLookUpValuesForDatabase(){
		Boolean bSuccess = true;
		String[] elements = {	"Account locked",
				"Automated Job Failure", 
				"Connectivity", 
				"Data Issue", 
				"Disk", 
				"Error message", 
				"Integration Issue",
				"IP Address", 
				"Login failure", 
				"Memory", 
				"Performance degradation",
				"Security breach", 
		"Storage"};


		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter.reportStep("The field in MetaData CI: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter.reportStep("The field in MetaData CI: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}
		if(bSuccess)
			Reporter.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI Component Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentsListPage verifyCausingCIComponentFieldLookUpValuesForNetwork(){
		Boolean bSuccess = true;

		switchToSecondWindow();
		switchToDefault();

		String[] elements = {	"Automated job failure",
				"Connectivity",
				"Data issue",
				"Error message", 
				"Integration issue", 
				"IP Address",
				"Login failure", 
				"Memory",
				"Performance degradation", 
				"Power", 
				"Security breach",
		"Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentsListPage searchConfigItem(String ci, String value) {
		//switchToFrame("Frame_Main", 5);
		Wait(3000);
		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", ci))
			Reporter.reportStep("The Class could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",value)){
			pressKey(Keys.ENTER);
			Reporter.reportStep("The CI "+ci+" is "+value+" is searched successfully", "SUCCESS");}
		else
			Reporter.reportStep("The CI "+ci+" is "+value+" could not be clicked", "FAILURE");

		return new IncidentsListPage(driver);

	}
	public ListPage verifyCIComponetSpyGlass(String[] elements){
//		if(verifyAllTexts("CI_FirstCI_Xpath", ele))
//			Reporter.reportStep("All the fields :"+convertStringArrayToString(ele)+" do exists in CI Component Spyglass list", "SUCCESS");
//		else
//			Reporter.reportStep("All the fields :"+convertStringArrayToString(ele)+" does not exists in CI Component Spyglass list, hence failure", "FAILURE");

		Boolean bSuccess = true;
				
		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;
	}	
	public IncidentsListPage clickClassLink(String linkName){

		Wait(5000);
		// click the first Incident Link
		if(!clickLink(linkName, false))
			Reporter.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "FAILURE");
		return new IncidentsListPage(driver);
	}
	public IncidentsListPage verifyBusinessService() {

		System.out.println(getCountOfElementsByXpath("CI_FirstCI_Xpath"));

		if((getCountOfElementsByXpath("CI_FirstCI_Xpath"))>=1)			
			Reporter.reportStep("The Multiple business services are available as expected", "SUCCESS");
		else
			Reporter.reportStep("The Multiple business services could not be found", "FAILURE");
		return this;
	}
	public IncidentPage clickCreatedIncident(String incNum){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",incNum)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Incident: "+incNum+" could not be entered", "FAILURE");
		
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The Incident: "+incNum+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Incident is not clicked or not found", "FAILURE");

		return new IncidentPage(driver);
	}	
	public String getIncidentNumber() {
		String alertId = getTextByXpath("First_Searched_Record_Xpath");
		System.out.println(alertId);
		if(alertId.equals(""))
			Reporter.reportStep("The Incident Number is Blank", "FAILURE");
		return alertId;
	}
	public ListPage rightClickonFirstIncident(){

		verifyData();
		// Verify the tabs exists
		if(rightClickByXpath("First_Searched_Record_Xpath"))		
			Reporter.reportStep("Right click on the first Incident is performed successfully ", "SUCCESS");
		else
			Reporter.reportStep("Right click could not be clicked ", "FAILURE");
		return this;
	}
	public IncidentPage clickCreatedMasterIncident(String incNum){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",incNum)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Incident: "+incNum+" could not be entered", "FAILURE");
		
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The Master Incident: "+incNum+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Incident is not clicked or not found", "FAILURE");

		return new IncidentPage(driver);
	}
	public IncidentPage clickCreatedChildIncident(String incNum){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",incNum)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Incident: "+incNum+" could not be entered", "FAILURE");
		
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The Child Incident: "+incNum+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Incident is not clicked or not found", "FAILURE");

		return new IncidentPage(driver);
	}	
}
