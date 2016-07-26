package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class MyWorkListPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public MyWorkListPage(RemoteWebDriver driver) {

		this.driver = driver;

	}

	public MyWorkListPage switchToListFrame(){

		switchToFrame("listData");
		return this;
	}

	public MyWorkListPage clickMyWorkTab(){

		switchToDefault();

		clickByXpath("Sparc_MyWorkTab_Xpath");
		
		if(clickByXpath("Sparc_MyWorkTab_Xpath"))
			Reporter.reportStep("The My Work Tab is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The My Work Tab could not be clicked", "FAILURE");

		return this;
	}
	public MyWorkListPage rightClickStateHeadForMyWorkTab() {

		switchToFrame("Sparc_MyWork_Name");

		if(!rightClickByXpath("Sparc_StateHeader_Xpath"))
			Reporter.reportStep("The Right click for State Header is not performed, check snapshot.", "FAILURE");

		if(clickByXpath("Sparc_GroupByState_Xpath"))
			Reporter.reportStep("The Right click for State Header is performed and Group By State is selected successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Right click for Group By State is not clicked, check snapshot.", "FAILURE");

		return this;

	}	
	public MyWorkListPage rightClickStateHeadForMyGroupWorkTab() {

		switchToFrame("Sparc_MyGroupWork_Name");

		if(!rightClickByXpath("Sparc_StateHeader_Xpath"))
			Reporter.reportStep("The Right click for State Header is not performed, check snapshot.", "FAILURE");

		if(clickByXpath("Sparc_GroupByState_Xpath"))
			Reporter.reportStep("The Right click for State Header is performed and Group By State is selected successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Right click for Group By State is not clicked, check snapshot.", "FAILURE");


		return this;

	}
	public MyWorkListPage isClosedGroupAvailable() {

		if(!isExistByXpath("Sparc_ClosedGroup_Xpath"))
			Reporter.reportStep("The Closed Group is not available as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Closed Group is available hence failure, check snapshot.", "FAILURE");


		return this;

	}
	public MyWorkListPage clickMyWorkGroupTab(){

		switchToDefault();

		if(clickByXpath("Sparc_MyGroupsTab_Xpath"))
			Reporter.reportStep("The My Work Group Tab is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The My Work Group Tab could not be clicked", "FAILURE");

		return this;
	}
	public IncidentPage searchAndVerifyTask(String taskNumber) {

		switchToFrame("Sparc_MyGroupWork_Name");

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",taskNumber))
			Reporter.reportStep("The Incident number:"+taskNumber+" could not be clicked", "FAILURE");

		if(isExistByLinkText(taskNumber, false))
			Reporter.reportStep("The created Task: "+taskNumber+" is available under My Groups Work as expected.", "SUCCESS");
		else
			Reporter.reportStep("The created Task: "+taskNumber+" could not be available.", "FAILURE");

		return new IncidentPage(driver);
	}

	public IncidentPage clickFirstIncident() {

		switchToFrame("Sparc_MyGroupWork_Name");

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The First Incident is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First Incident could not be clicked", "FAILURE");


		return new IncidentPage(driver);

	}
}
