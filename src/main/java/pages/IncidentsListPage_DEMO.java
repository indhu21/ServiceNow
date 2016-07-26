package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class IncidentsListPage_DEMO extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public IncidentsListPage_DEMO(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();
		// Check that we're on the right page.
//		if (!isExistByXpath("NavBar_Xpath")) {
//			Reporter1.reportStep("This is not the Incident List page", "FAILURE");
//		}
	}
	public IncidentsListPage_DEMO switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	
	public IncidentPage_DEMO clickCreatedIncident(String incNum){

		if(!selectByVisibleTextByXpath("GotoSelect_Xpath", "Number"))
			Reporter1.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("GotoSearch_Xpath",incNum)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter1.reportStep("The Incident: "+incNum+" could not be entered", "FAILURE");

		// click the first Incident Link
		if(clickByXpath("FirstLink_Xpath"))
			Reporter1.reportStep("The Incident: "+incNum+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter1.reportStep("The Incident is not clicked or not found", "FAILURE");

		return new IncidentPage_DEMO(driver);
	}	
	public IncidentPage_DEMO clickFirstIncident(){

		verifyData();

		if(clickByXpath("FirstLink_Xpath"))
			Reporter1.reportStep("The First Incident is clicked Successfully", "SUCCESS");
		else
			Reporter1.reportStep("The First Incident could not be clicked", "FAILURE");

		return new IncidentPage_DEMO(driver);
	}

	public IncidentsListPage_DEMO searchIncident(String incNumber) {

		if(!selectByVisibleTextByXpath("GotoSelect_Xpath", "Number"))
			Reporter1.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("GotoSearch_Xpath",incNumber)){
			pressKey(Keys.ENTER);
			Reporter1.reportStep("The Incident number:"+incNumber+" is searched successfully", "SUCCESS");}
		else
			Reporter1.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		return this;
	}

	public IncidentsListPage_DEMO rightClickonHeader(){


		// Verify the tabs exists
		if(rightClickByXpath("Inc_TableHeading_Xpath"))		
			Reporter1.reportStep("Right click on the Incident header is performed successfully ", "SUCCESS");
		else if(isExistByXpath("NoRecords_xpath"))
			Reporter1.reportStep("Insufficient Data, hence failure.", "FAILURE");
		else
			Reporter1.reportStep("Right click could not be clicked ", "FAILURE");
		return this;
	}

	public IncidentsListPage_DEMO uploadExcel(){

		// Verify the tabs exists
		if(!clickByXpath("Inc_Export_Xpath"))				
			Reporter1.reportStep("The Export could not be clicked", "FAILURE");

		if(!mouseOverByXpath("Inc_Excel_Xpath"))
			Reporter1.reportStep("The Excel could not be found", "FAILURE");

		if(clickByXpath("Inc_Excel_Xpath"))
			Reporter1.reportStep("The Excel is clicked successfully", "SUCCESS");
		else
			Reporter1.reportStep("The Excel could not be clicked", "FAILURE");

		Wait(5000);

		if(IsElementPresentById("Inc_Upload_Id"))
			Reporter1.reportStep("The pop up saying 'Export Complete' is displayed as expected", "SUCCESS");
		else
			Reporter1.reportStep("A pop up did not appear", "FAILURE");


		return this;
	}

	public IncidentsListPage_DEMO rightClickonNumber(){


		// Verify the tabs exists
		if(rightClickByXpath("Inc_Number_Xpath"))		
			Reporter1.reportStep("Right click on the Incident header is performed successfully ", "SUCCESS");
		else if(isExistByXpath("NoRecords_xpath"))
			Reporter1.reportStep("Insufficient Data, hence failure.", "FAILURE");
		else
			Reporter1.reportStep("Right click could not be clicked ", "FAILURE");
		return this;
	}

	public IncidentsListPage_DEMO sortAscending(){

		String CMDBNum =getIncTaskNumber();

		// Verify the tabs exists
		if(clickByXpath("Inc_Sort(atoz)_Xpath"))
			Reporter1.reportStep("Sort ( a to z ) option is selected successfully", "SUCCESS");	
		else
			Reporter1.reportStep("Sort option could not be selected", "FAILURE");

		Wait(5000);	

		String CMDBNum2 =getIncTaskNumber();
		
		if(CMDBNum.contains(CMDBNum2)){
			Reporter1.reportStep("Sort is done in ascending order as expected.", "SUCCESS");}
		else
			Reporter1.reportStep("Sorting could not be done", "FAILURE");

		return this;
	}

	public IncidentsListPage_DEMO sortDescending(){

		String CMDBNum =getIncTaskNumber();

		if(clickByXpath("Inc_Sort(ztoa)_Xpath"))
			Reporter1.reportStep("Sort ( z to a ) option is selected successfully", "SUCCESS");	
		else
			Reporter1.reportStep("Sort option could not be selected", "FAILURE");
		
		Wait(5000);	
		
		String CMDBNum2 =getIncTaskNumber();

		if(!CMDBNum.contains(CMDBNum2)){
			Reporter1.reportStep("Sort is done in descending order as expected.", "SUCCESS");}
		else
			Reporter1.reportStep("Sorting could not be done", "FAILURE");

		return this;
	}

	public IncidentsListPage_DEMO verifySorting(){

		// Verify the tabs exists
		if(IsElementPresentByXpath("Inc_NumberDown_Xpath")){		

			clickByXpath("Inc_NumberDown_Xpath");
		}
		return this;
	}

	public String getIncTaskNumber() {

		String CMDBTaskNumber = getTextByXpath("FirstLink_Xpath");

		if(CMDBTaskNumber.equals(""))
			Reporter1.reportStep("The Inc number is blank or No records to display", "FAILURE");

		return CMDBTaskNumber;
	}	

	public IncidentsListPage_DEMO verifyData() {

		if(isExistByXpath("NoRecords_Xpath"))
			Reporter1.reportStep("Insufficient Data, hence failure.", "FAILURE");

		return this;	
	}

}
