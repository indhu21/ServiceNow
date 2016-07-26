package pages;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class ViewRunPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;
	private String incidentNumber;

	public ViewRunPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		//Check that we're on the right page.
		if (!isExistByXpath("ReportHead_Xpath")) {
			Reporter.reportStep("This is not the View / Run Page.", "FAILURE");
		}
	}

	public ViewRunPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}

	public ViewRunPage clickReportA(){

		if (!isExistByXpath("ReportA")) 
			Reporter.reportStep("Report A could not be found or Clicked", "FAILURE");

		return this;
	}

	public ViewRunPage clickCreateRecord(){

		if (clickByXpath("INC_CreateRecord_Xpath")) 
			Reporter.reportStep("The Create a Report Button is clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The Create a Report Button is not clicked or not found.", "FAILURE");

		return this;
	}


	public ViewRunPage clickDataAndFilters(String number){

		if (!selectByVisibleTextByXpath("INC_ReportData_Xpath", "Table")) 
			Reporter.reportStep("The Value Table is not selected or not found from Data.", "FAILURE");

		if (!selectByVisibleTextByXpath("INC_ReportDataTable_Xpath", "Incident [incident]")) 
			Reporter.reportStep("The Value Incident [incident] is not selected or not found from Table.", "FAILURE");

		if (!selectByVisibleTextByXpath("INC_ReportType_Xpath", "List")) 
			Reporter.reportStep("The Value List is not selected or not found from Type.", "FAILURE");

		if (!clickByXpath("INC_ReportAddFilter_Xpath")) 
			Reporter.reportStep("The Add New Filter Button is not clicked or not found.", "FAILURE");

		addFilterstoEnterValues("INC_ReportAddFilterSelect1_Xpath", "Number", "INC_ReportAddFilterSelect2_Xpath", "is", "INC_ReportAddFilterInput1_Xpath", number);

		if (clickByXpath("INC_ReportRun_Xpath")) 
			Reporter.reportStep("The Add New Filter Button is clicked", "SUCCESS");
		else
			Reporter.reportStep("The Add New Filter Button is not clicked or not found.", "FAILURE");

		Wait(5000);
		return this;
	}
	//	public ViewRunPage verifycolumnValue(String colName, String[] expectedValues) {
	//
	//
	//		int column=getColumnIndex("CMDBTask_TableHeading_Xpath", colName);
	//
	//		column=column+2;
	//
	//		if(verifyAllTextsUsingContains("(//*[@class='data_list_table list_table table table-hover '])/tbody/tr/td"+"["+column+"]", expectedValues))
	//			Reporter.reportStep("The values: "+convertStringArrayToString(expectedValues)+" is matched under "+colName+" column as expected.", "SUCCESS");
	//		else
	//			Reporter.reportStep("The values: "+convertStringArrayToString(expectedValues)+" is not matched under "+colName+" column, hence failure.", "FAILURE");
	//
	//
	//		return this;
	//
	//	}


	public ViewRunPage verifycolumnValue(String[] colName, String[] value) {

		for (int i = 0; i < colName.length; i++){ 
			Wait(5000);
			int column=getColumnIndex1("CMDBTask_TableHeading_Xpath", colName[i]);

			//			column=column+2;

			scrollToElementByXpath("(//*[@class='list_header list_header_search_row']/following::*[@class='vt'])"+"["+column+"]", false);

			String text=getTextByXpath("(//*[@class='list_header list_header_search_row']/following::*[@class='vt'])"+"["+column+"]", false);

			// System.out.println(getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false));
			System.out.println(text);
			System.out.println(value[i]);
			if(colName[i].equals("Resolved")){
				System.out.println((value[i].substring(0, 16)));
				text.equalsIgnoreCase((value[i].substring(0, 16)));}
			else{

				if(!text.equalsIgnoreCase(value[i]))
					Reporter.reportStep("The "+colName[i]+" value: "+value[i]+" is not matched, check snapshot.", "FAILURE");}
			//			else
			//				Reporter.reportStep("The "+colName[i]+" value: "+value[i]+" is matched as expected.", "SUCCESS");
		}
		Reporter.reportStep("The Column headers  "+convertStringArrayToString(colName)+" matched with the values: "+convertStringArrayToString(value)+" respectively as expected.", "SUCCESS");  

		return this;
	}

	public ViewRunPage verifyValueAndMoveToSelected(String[] elements){


		List<String> selectedOpetions=getOptionsByXpath("SA_SelectedList_Xpath");

		for (String ele : elements) {
			if(!selectedOpetions.contains(ele)){
				if(!selectByVisibleTextByXpath("SA_AvailableList_Xpath", ele)) 
					Reporter.reportStep("The Value: "+ele+" is not selected from available list.", "FAILURE");
				if(!clickByXpath("RightArrow_Xpath"))
					Reporter.reportStep("The Right Arrow is not clicked or not found", "FAILURE");}
		}
		if(!clickByXpath("SaveReport_Xpath"))
			Reporter.reportStep("The Save Button is not clicked or not found", "FAILURE");
		return this;
	}

	public ViewRunPage cliklink(String repName) {

		if(clickLink(repName, false))
			Reporter.reportStep(""+repName+" is clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep(""+repName+" is could not be clicked or not found.", "SUCCESS");


		return this ;

	}

	public ViewRunPage saveReport(String name) {

		if(!enterByXpath("ReportTitle_Xpath", name))
			Reporter.reportStep("The Report Name "+name+" is not entered, hence failure.", "FAILURE");

		if(clickByXpath("SaveReport_Xpath"))
			Reporter.reportStep("Report with name "+name+" is saved successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Save Button is not clicked or not found.", "FAILURE");

		return this;
	}
	public ViewRunPage searchReport(String reportName) {

		if(!enterByXpathAndClick("ReportSearch_Xpath", reportName))
			Reporter.reportStep("The Report: "+reportName+" is not entered for search, hence failure.", "FAILURE");

		return this;
	}
	public ViewRunPage clickDataAndFiltersforVersion2(String number){

		if (!selectByVisibleTextByXpath("INC_ReportData_Xpath", "Table")) 
			Reporter.reportStep("The Value Table is not selected or not found from Data.", "FAILURE");

		if (!selectByVisibleTextByXpath("INC_ReportDataTable_Xpath", "Incident [incident]")) 
			Reporter.reportStep("The Value Incident [incident] is not selected or not found from Table.", "FAILURE");

		if (!selectByVisibleTextByXpath("INC_ReportType_Xpath", "List")) 
			Reporter.reportStep("The Value List is not selected or not found from Type.", "FAILURE");

		if (!clickByXpath("INC_ReportAddFilter_Xpath")) 
			Reporter.reportStep("The Add New Filter Button is not clicked or not found.", "FAILURE");

		if(addFilters("INC_ReportAddFilterSelect1_Xpath", "Incident state", "INC_ReportAddFilterSelect2_Xpath", "is", "INC_ReportAddFilter1Select2_Xpath", "Resolved"))
			Reporter.reportStep("The Filter value Incident state is Reoloved selected successfully.", "SUCCESS");

		if (!clickByXpath("INC_ReportAddFilter_Xpath")) 
			Reporter.reportStep("The Add New Filter Button is not clicked or not found.", "FAILURE");

		if(addFilterstoEnterValues("INC_ReportAddFilter2Select1_Xpath", "Number",
				"INC_ReportAddFilter2Select2_Xpath", "is", "INC_ReportAddFilter2Input1_Xpath", number))
			Reporter.reportStep("The Filter value Number is "+number+" selected successfully.", "SUCCESS");

		if (clickByXpath("INC_ReportRun_Xpath")) 
			Reporter.reportStep("The Run Filter Button is clicked", "SUCCESS");
		else
			Reporter.reportStep("The Run Filter Button is not clicked or not found.", "FAILURE");

		Wait(5000);
		return this;
	}
	public ViewRunPage verifyNoRecordsData(){

		if(isExistByXpath("NoRecords_xpath")){
			Reporter.reportStep("No Records to display as expected.", "SUCCESS");}
		else
			Reporter.reportStep("Some datas available, hence failure.", "FAILURE");

		return this;
	}
	public ViewRunPage verifyRecordsData(String number){

		if(isExistByLinkText(number, false)){
			Reporter.reportStep("The Number "+number+" displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("No Records to display, hence failure.", "FAILURE");

		return this;
	}

}

