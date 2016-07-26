package pages_OutOfBox;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class IncidentsListPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public IncidentsListPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the Request List page", "FAILURE");
		}
	}
	public IncidentsListPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}
	public IncidentPage clickCreatedIncident(String incNum){

		if(!selectByVisibleTextByXpath("GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("GotoSearch_Xpath",incNum)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Incident: "+incNum+" could not be entered", "FAILURE");

		// click the first Incident Link
		if(clickByXpath("FirstLink_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNum+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Incident is not clicked or not found", "FAILURE");

		return new IncidentPage(driver);
	}	
	public IncidentPage clickFirstIncident(){

		verifyData();

		if(clickByXpath("FirstLink_Xpath"))
			Reporter_ServiceNow.reportStep("The First Incident is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Incident could not be clicked", "FAILURE");

		return new IncidentPage(driver);
	}

	public IncidentsListPage searchIncident(String incNumber) {

		if(!selectByVisibleTextByXpath("GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("GotoSearch_Xpath",incNumber)){
			pressKey(Keys.ENTER);
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is searched successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		return this;
	}

	public IncidentsListPage rightClickonHeader(){


		// Verify the tabs exists
		if(rightClickByXpath("Inc_TableHeading_Xpath"))		
			Reporter_ServiceNow.reportStep("Right click on the Incident header is performed sucessfully ", "SUCCESS");
		else if(isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("Right click could not be clicked ", "FAILURE");
		return this;
	}

	public IncidentsListPage uploadExcel(){

		// Verify the tabs exists
		if(!clickByXpath("Inc_Export_Xpath"))				
			Reporter_ServiceNow.reportStep("The Export could not be clicked", "FAILURE");

		if(!mouseOverByXpath("Inc_Excel_Xpath"))
			Reporter_ServiceNow.reportStep("The Excel could not be found", "FAILURE");

		if(clickByXpath("Inc_Excel_Xpath"))
			Reporter_ServiceNow.reportStep("The Excel is clicked sucessfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Excel could not be clicked", "FAILURE");

		Wait(5000);

		if(IsElementPresentById("Inc_Upload_Id"))
			Reporter_ServiceNow.reportStep("The pop up saying 'Export Complete' is displayed as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("A pop up did not appear", "FAILURE");


		return this;
	}

	public IncidentsListPage rightClickonNumber(){


		// Verify the tabs exists
		if(rightClickByXpath("Inc_Number_Xpath"))		
			Reporter_ServiceNow.reportStep("Right click on the Incident header is performed sucessfully ", "SUCCESS");
		else if(isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("Right click could not be clicked ", "FAILURE");
		return this;
	}

	public IncidentsListPage sortAscending(){

		String CMDBNum =getIncTaskNumber();

		// Verify the tabs exists
		if(clickByXpath("Inc_Sort(atoz)_Xpath"))
			Reporter_ServiceNow.reportStep("Sort ( a to z ) option is selected sucessfully", "SUCCESS");	
		else
			Reporter_ServiceNow.reportStep("Sort option could not be selected", "FAILURE");

		Wait(5000);	

		String CMDBNum2 =getIncTaskNumber();

		if(CMDBNum.contains(CMDBNum2)){
			Reporter_ServiceNow.reportStep("Sort is done in ascending order as expected.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("Sorting could not be done", "FAILURE");

		return this;
	}

	public IncidentsListPage sortDescending(){

		String CMDBNum =getIncTaskNumber();

		if(clickByXpath("Inc_Sort(ztoa)_Xpath"))
			Reporter_ServiceNow.reportStep("Sort ( z to a ) option is selected sucessfully", "SUCCESS");	
		else
			Reporter_ServiceNow.reportStep("Sort option could not be selected", "FAILURE");

		Wait(5000);	

		String CMDBNum2 =getIncTaskNumber();

		if(!CMDBNum.contains(CMDBNum2)){
			Reporter_ServiceNow.reportStep("Sort is done in descending order as expected.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("Sorting could not be done", "FAILURE");

		return this;
	}

	public IncidentsListPage verifySorting(){

		// Verify the tabs exists
		if(IsElementPresentByXpath("Inc_NumberDown_Xpath")){		

			clickByXpath("Inc_NumberDown_Xpath");
		}
		return this;
	}

	public String getIncTaskNumber() {

		String CMDBTaskNumber = getTextByXpath("FirstLink_Xpath");

		if(CMDBTaskNumber.equals(""))
			Reporter_ServiceNow.reportStep("The Inc number is blank or No records to display", "FAILURE");

		return CMDBTaskNumber;
	}	

	public IncidentsListPage verifyData() {

		if(isExistByXpath("NoRecords_Xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");

		return this;	
	}

	public IncidentsListPage verifycolumnValue(String colName, String[] expectedValues) {


		int column=getColumnIndex("INC_TableHead_Xpath", colName);

		column=column+2;

		if(verifyAllTextsUsingContains("(//table[contains(@id,'incident_table')])/tbody/tr/td"+"["+column+"]", expectedValues))
			Reporter_ServiceNow.reportStep("The values: "+convertStringArrayToString(expectedValues)+" is matched under "+colName+" column as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The values: "+convertStringArrayToString(expectedValues)+" is not matched under "+colName+" column, hence failure.", "FAILURE");


		return this;

	}
	
	public IncidentsListPage verifyOpenIncidents() {

		if(getTextByXpath("INC_FilterActive_Xpath").contains("Active = true"))
			Reporter_ServiceNow.reportStep("A list of all the Open incidents are listed as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("A list of all the Open incidents does not listed", "SUCCESS");

		return this;	
	}
	public IncidentsListPage clickCreatedIncidentAndVerifyColumnValue(String incNum, String priority){

		  if(!selectByVisibleTextByXpath("GotoSelect_Xpath", "Number"))
		   Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		  if(enterByXpath("GotoSearch_Xpath",incNum)){
		   pressKey(Keys.ENTER);
		   Wait(3000);}
		  else
		   Reporter_ServiceNow.reportStep("The Incident: "+incNum+" could not be entered", "FAILURE");

		  String[] priority1={priority};
		  
		  int column=getColumnIndex("INC_TableHead_Xpath", "Priority");

		  column=column+2;
		  
		  if(verifyAllTextsUsingContains("(//table[contains(@id,'incident_table')])/tbody/tr/td"+"["+column+"]", priority1))
		   Reporter_ServiceNow.reportStep("The Created Incident: "+incNum+" is present in the list and Priority: "+convertStringArrayToString(priority1)+" is macthed as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The value Priority: "+convertStringArrayToString(priority1)+" is not matched, check snapshot.", "FAILURE");

		  return this;
		 }
}
