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

public class ChangeRequestListPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public ChangeRequestListPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the Incident List page", "FAILURE");
		}
	}
	public ChangeRequestListPage switchToMainFrame(){

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
	public ChangeRequestPage clickFirstRequest(){

		verifyData();

		if(clickByXpath("FirstLink_Xpath"))
			Reporter_ServiceNow.reportStep("The First Request is clicked Successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Request could not be clicked", "FAILURE");

		return new ChangeRequestPage(driver);
	}

	public ChangeRequestListPage verifyData() {

		resetImplicitWait(5);
		if(isExistByXpath("NoRecords_Xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		resetImplicitWait(30);
		return this;	
	}
	public ChangeRequestListPage verifycolumnValue(String colName, String[] expectedValues) {


		int column=getColumnIndex("CHN_TableHead_Xpath", colName);

		column=column+2;
		//System.out.println(column);
		//String[] text={"Open", "Work in Progress"};
		if(verifyAllTextsUsingContains("(//table[contains(@id,'change_request_table')])/tbody/tr/td"+"["+column+"]", expectedValues))
			Reporter_ServiceNow.reportStep("The values: "+convertStringArrayToString(expectedValues)+" is matched under "+colName+" column as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The values: "+convertStringArrayToString(expectedValues)+" is not matched under "+colName+" column, hence failure.", "FAILURE");


		return this;

	}
	public ChangeRequestListPage verifycolumnValuecontains(String colName, String[] expectedValues) {


		int column=getColumnIndex("CHN_TableHead_Xpath", colName);

		column=column+2;
		//System.out.println(column);
		//String[] text={"Open", "Work in Progress"};
		if(verifyAllTextsUsingContains("(//table[contains(@id,'change_request_table')])/tbody/tr/td"+"["+column+"]", expectedValues))
			Reporter_ServiceNow.reportStep("The values: "+convertStringArrayToString(expectedValues)+" is matched under "+colName+" column as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The values: "+convertStringArrayToString(expectedValues)+" is not matched under "+colName+" column, hence failure.", "FAILURE");


		return this;

	}

	
	
}
