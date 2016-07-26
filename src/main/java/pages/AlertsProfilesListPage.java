package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class AlertsProfilesListPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;
	private String profileName;
	
	public AlertsProfilesListPage(RemoteWebDriver driver) {

		this.driver = driver;
		resetImplicitWait(5);
		switchToMainFrame();
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar2_Xpath")) {
			Reporter.reportStep("This is not the Alerts Profiles List Page.", "FAILURE");
		}
		resetImplicitWait(30);
	}
	public AlertsProfilesListPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public AlertProfilePage clickFirstAlertProfile(String alertProfile ){

		if(clickLink(alertProfile, false))
			Reporter.reportStep("The First Alert Profile Number: "+ alertProfile +" is selected successfully.","SUCCESS");
		else
			Reporter.reportStep("The First Alert Profile Number: t:"+ alertProfile +" is not selected or not found.","FAILURE");

		Wait(3000);

		return new AlertProfilePage(driver);
	}

	public String getAlertProfileName(){
		
		profileName=getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
		if(profileName.equals(""))
			Reporter.reportStep("The Alert Profile Name is blank.","FAILURE");
		return profileName;
	}

	public AlertsProfilesListPage deleteFilter(){
		
		if(!deleteAllFilters())
			Reporter.reportStep("The All Filters could not be removed", "FAILURE");		
		
		return this;
	}

	
	public AlertsProfilesListPage addNewFilterinAlertUsingSelect(String filterType, String filterCondition, String filterValue){
	
		if(!clickByXpath("CIS_FunnelIcon_Xpath"))
			Reporter.reportStep("The funnel icon could not be clicked","FAILURE");
		Wait(10000);

		deleteAllFilters();

		if(!addNewFilterUsingInput1(filterType, filterCondition, filterValue))		
			Reporter.reportStep("The Filter Values is not selected.", "FAILURE");
		
		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter.reportStep("The filter values selected with "+filterType+" "+filterCondition+" "+filterValue+" and Run is clicked", "SUCCESS");
		else
			Reporter.reportStep("Run could not be clicked", "FAILURE");
		return this;
	}
		public AlertProfilePage clickColumnValue(String value, String alertId) {

		int column=getColumnIndex1("Alerts_TableHeading1_Xpath", value);

		String alertProfile=getTextByXpath("(//*[@class='vt'])["+column+"]", false);

		if(clickLink(alertProfile, false))
			Reporter.reportStep("The "+value+": "+alertProfile+" for Alert "+ alertId +" is selected as expected.","SUCCESS");
		else
			Reporter.reportStep("The "+value+": "+alertProfile+" for Alert "+ alertId +" is not selected, check snapshot.","FAILURE");

		return new AlertProfilePage(driver);
	}	

		public AlertProfilePage clickFirstProfile(){

			if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
				Reporter.reportStep("The First Alert Profile  is selected successfully.","SUCCESS");
			else
				Reporter.reportStep("The First Alert Profile is not selected or not found.","FAILURE");

			Wait(3000);

			return new AlertProfilePage(driver);
		}

		public AlertsProfilesListPage addNewFilterinAlertUsingEnter(String filterType, String filterCondition, String filterValue){
			
			if(!clickByXpath("CIS_FunnelIcon_Xpath"))
				Reporter.reportStep("The funnel icon could not be clicked","FAILURE");
			Wait(10000);

			deleteAllFilters();

			if(!addNewFilterUsingInput(filterType, filterCondition, filterValue))		
				Reporter.reportStep("The Filter Values is not selected.", "FAILURE");
			
			if(clickByXpath("ALERT_RunFilter_Xpath"))
				Reporter.reportStep("The filter values selected with "+filterType+" "+filterCondition+" "+filterValue+" and Run is clicked", "SUCCESS");
			else
				Reporter.reportStep("Run could not be clicked", "FAILURE");
			return this;
		}
		
		public AlertProfilePage searchAndclickProfile(String profileNum){

			if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
				Reporter.reportStep("The Number could not be selected", "FAILURE");
			
			if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", profileNum))
				Reporter.reportStep("The created Profile: " + profileNum + " could not be found", "FAILURE");

			Wait(5000);

			if (clickLink(profileNum, false))
				Reporter.reportStep("The Alert Profile: " + profileNum + " has been found and clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("The Alert Profile: " + profileNum + " could not be found", "FAILURE");

			return new AlertProfilePage(driver);
		}
		public AlertProfilePage searchAndclickProfileNum(String profileNum){

			if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
				Reporter.reportStep("The Number could not be selected", "FAILURE");
			
			if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", profileNum))
				Reporter.reportStep("The created Profile: " + profileNum + " could not be found", "FAILURE");

			Wait(5000);

			if (clickLink(profileNum, false))
				Reporter.reportStep("The Draft State Alert Profile: " + profileNum + " has been clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("The Draft State Alert Profile: " + profileNum + " could not be found.", "FAILURE");

			return new AlertProfilePage(driver);
		}
		public AlertProfilePage searchAndclickSameProfile(String profileNum){

			if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
				Reporter.reportStep("The Number could not be selected", "FAILURE");
			
			if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", profileNum))
				Reporter.reportStep("The created Profile: " + profileNum + " could not be found", "FAILURE");

			Wait(5000);

			if (clickLink(profileNum, false))
				Reporter.reportStep("The Alert Profile: " + profileNum + " has been clicked successfully", "SUCCESS");
			else
				Reporter.reportStep("The Alert Profile: " + profileNum + " could not be found", "FAILURE");

			return new AlertProfilePage(driver);
		}		
}


