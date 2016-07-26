package demo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import pages.IncidentPage;
import pages.ListPage;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class IncidentsListPage_ServiceNowDemo extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public IncidentsListPage_ServiceNowDemo(RemoteWebDriver driver) {
		this.driver = driver;
		switchToMain();
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar1_Xpath")) {
			Reporter.reportStep("This is not the Incident List page", "FAILURE");
		}

		resetImplicitWait(30);
	}
	public IncidentsListPage_ServiceNowDemo searchIncidentforDemo(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber)){
			Reporter.reportStep("The Incident number:"+incNumber+" is created successfully", "SUCCESS");}
		else
			Reporter.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		return this;
	}
	public String getIncidentNumber() {
		
		String incidentNumber = getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
		
		System.out.println(incidentNumber);
		
		if(incidentNumber.equals(""))
			Reporter.reportStep("The Incident Number is Blank", "FAILURE");
		return incidentNumber;
	}
	public IncidentsListPage_ServiceNowDemo rightClickonFirstIncident(){

		if(rightClickByXpath("First_Searched_Record_Xpath"))		
			Reporter.reportStep("Right click on the first Incident is performed successfully ", "SUCCESS");
		else
			Reporter.reportStep("Right click could not be clicked ", "FAILURE");
		return this;
	}
	public IncidentsListPage_ServiceNowDemo clickAssignToMe(){

		// Verify the tabs exists
		if(clickByXpath("ALERT_AssignToMe_Xpath"))		
			Reporter.reportStep("The Assign to me clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The Assign to me could not be clicked", "FAILURE");
		return this;
	}

	public IncidentsListPage_ServiceNowDemo searchAndOpenIncidentAssign(String incNumber, String user) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(getTextByXpath("ALERTPROFILE_FirstAlert_Xpath").equals(incNumber))
			Reporter.reportStep("The Incident "+incNumber+" successfully assigned to "+user+" as expected", "SUCCESS");
		else
			Reporter.reportStep("Incident could not be assigned to "+user,"FAILURE");

		return this;
	}
	public IncidentsListPage_ServiceNowDemo clickFunnelWithoutReport(){  

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)", "");
		Wait(500);

		if(clickByXpath("EditFilter_Xpath")){
			Wait(2000);}
		else   
			Reporter.reportStep("Funnel icon could not been clicked", "FAILURE");
		return this;
	}
	public IncidentsListPage_ServiceNowDemo clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}
	public IncidentsListPage_ServiceNowDemo addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValue2_Xpath", filterValue))
			Reporter.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public IncidentsListPage_ServiceNowDemo clickRun(){  

		if(!clickByXpath("Run_Xpath"))
			Reporter.reportStep("Run Button could not been clicked", "FAILURE");

		return this;
	}
	public IncidentPage_ServiceNowDemo clickFirstIncident(String incNumber){
		
	
		
		if(clickLink(incNumber, false))
			Reporter.reportStep("The First Incident is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First Incident could not be clicked", "FAILURE");

		return new IncidentPage_ServiceNowDemo(driver);
	}
	public IncidentsListPage_ServiceNowDemo searchAndOpenIncidentResolve(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		
		if(getTextByXpath("ALERTPROFILE_FirstAlert_Xpath").equals(incNumber))
			Reporter.reportStep("The Incident "+incNumber+" successfully Resolved as expected", "SUCCESS");
		else
			Reporter.reportStep("Incident could not be resolved, hence failure","FAILURE");

		return this;
	}

	public IncidentsListPage_ServiceNowDemo searchandClickIncidentDemoPositive(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		
		// click the first Incident Link
		if(getTextByXpath("ALERTPROFILE_FirstAlert_Xpath").equals(incNumber))
			Reporter.reportStep("The Incident number:"+incNumber+" is found as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Incident number:"+incNumber+" is not found, hence failure.", "FAILURE");

		return this;
	}
	public IncidentsListPage_ServiceNowDemo searchandClickIncidentDemoNegative(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
				Reporter.reportStep("The Incident number:"+incNumber+" is not found as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Incident number:"+incNumber+" is found, hence failure.", "FAILURE");

		return this;
	}	
}
