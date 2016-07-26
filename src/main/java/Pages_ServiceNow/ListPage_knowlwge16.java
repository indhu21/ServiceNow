package Pages_ServiceNow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testng.SuiteMethods;
import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class ListPage_knowlwge16 extends SuiteMethods{

	private final RemoteWebDriver driver;

	public ListPage_knowlwge16(RemoteWebDriver driver) {

		this.driver = driver;
		switchToMainFrame();
	}

	public ListPage_knowlwge16 switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main", 5);

		return this;
	}

	public ApplicationPage_knowlwge16 searchandClickFirstApplication(String ciName) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
			Reporter_ServiceNow.reportStep("The Name could not be selected, hence failure", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",ciName))
			Reporter_ServiceNow.reportStep("The Created CI: "+ciName+" could not be entered in searchbox, hence failure.", "FAILURE");

//		verifyData();

		// click the first Incident Link
		if(clickLink(ciName, false))
			Reporter_ServiceNow.reportStep("The Given CI:"+ciName+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Given CI:"+ciName+" could not be found.", "FAILURE");

		return new ApplicationPage_knowlwge16(driver);
	}
	public ListPage_knowlwge16 verifyData(){
		if(isExistByXpath("NoRecords_xpath")){
			//if(isExistByXpath("//*[text()='No records to display']", false)){		
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		return this;
	}
	public ListPage_knowlwge16 searchIncidentforDemo(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber)){
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is created successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		return this;
	}

	public ListPage_knowlwge16 verifyApplicationTableColumnValue(String operationalstatus){

		int index=getColumnIndex1("K1DemoCmdb_ApplicationTable_xpath", "Operational status");

		scrollToElementByXpath("(//*[@class='vt'])["+index+"]", false);

		
		
		String text=getTextByXpath("(//*[@class='vt'])["+index+"]", false);	

		System.out.println(text);
		
		if(text.equals(operationalstatus))
			Reporter_ServiceNow.reportStep("The updated operational status value is "+operationalstatus, "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The operational status value is not updated", "FAILURE");

		return this; 

	}


}


