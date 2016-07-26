package Pages_ServiceNow;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class CIScopesListPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public CIScopesListPage(RemoteWebDriver driver) {

		this.driver = driver;
		
		switchToMainFrame();
		
		// Check that we're on the right page.
		if(!(isExistByXpath("HomeTitle_Xpath")||isExistByXpath("NavBar2_Xpath"))) {
			Reporter_ServiceNow.reportStep("This is not the CI Scopes List Page.", "FAILURE");
		}
	}
	public CIScopesListPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public CIScopePage searchAndclickScopeNumber(String scopeNum){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");
		
		if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", scopeNum))
			Reporter_ServiceNow.reportStep("The created Data: " + scopeNum + " could not be found", "FAILURE");

		Wait(5000);

		if (clickLink(scopeNum, false))
			Reporter_ServiceNow.reportStep("The Scope Number : " + scopeNum + " has been found and clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Scope Number : " + scopeNum + " could not be found", "FAILURE");

		return new CIScopePage(driver);
	}

	public CIScopesListPage clickFunnelIcon(){
		if(!clickByXpath("CIS_FunnelIcon_Xpath"))
			Reporter_ServiceNow.reportStep("The funnel icon could not be clicked","FAILURE");

		return this;
	}

	public CIScopesListPage addFirstFilter(String filterType, String filterCondition, String filterValue){

		clickFunnelIcon();
		
		deleteAllFilters();
		
		if(!addNewFilterUsingInput1(filterType, filterCondition, filterValue))
			Reporter_ServiceNow.reportStep("The New Filters could not be selected","FAILURE");
		Wait(5000);

		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Values:" +filterType+" "+filterCondition+" "+filterValue+" selected successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");

		Wait(5000);	

		return this;
	}
	public CIScopesListPage addFirstFilterInput1(String filterType, String filterCondition, String filterValue){

		clickFunnelIcon();
		
		deleteAllFilters();
		
		if(!addNewFilter(filterType, filterCondition, filterValue))
			Reporter_ServiceNow.reportStep("The New Filters could not be selected","FAILURE");
		Wait(5000);

		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Values:" +filterType+" "+filterCondition+" "+filterValue+" selected successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");

		Wait(5000);	

		return this;
	}

	public CIScopesListPage verifyRightclickNotFound(){
	
		if (!rightClickById("CIS_Menu_Id"))
		Reporter_ServiceNow.reportStep("The Right click on menu could not be performed.","FAILURE");
	
		if (IsElementNotPresentByXpath("CIS_SaveRecord_Xpath"))
			Reporter_ServiceNow.reportStep("The Save option could not been found as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save option has been found.","FAILURE");		
	
		return this;
}
	public CIScopePage clickOnLink(String scopeNum){
	
		if (clickLink(scopeNum, false))
		Reporter_ServiceNow.reportStep("The Scope Number: " + scopeNum + " has been found and clicked successfully", "SUCCESS");
	else
		Reporter_ServiceNow.reportStep("The Scope Number: " + scopeNum + " could not be found", "FAILURE");
	
		return new CIScopePage(driver); 

	}

	public CIScopePage clickFirstCIScope() {
		
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The first CI Scope selected successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The first CI Scope not clicked or not found", "FAILURE");

		return new CIScopePage(driver);
		
	}
	public CIScopesListPage rightClickIsSaveAvl(){
		
		if (!rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Update button has been found, hence failure.", "FAILURE");
	
		if (IsElementNotPresentById("RightClickSave_Xpath"))
			Reporter_ServiceNow.reportStep("The Save Option is not found as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Save Option has been found, hence failure.", "FAILURE");
		
		return this;
	}
	public CIScopesListPage verifyData(){
		if(isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		
		return this;
	}
	public CIScopesListPage addFirstFilterInput(String filterType, String filterCondition, String filterValue){

		clickFunnelIcon();
		
		deleteAllFilters();
		
		if(!addNewFilterUsingInput(filterType, filterCondition, filterValue))
			Reporter_ServiceNow.reportStep("The New Filters could not be selected","FAILURE");
		Wait(5000);

		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Values:" +filterType+" "+filterCondition+" "+filterValue+" selected successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");

		Wait(5000);	

		return this;
	}
}


