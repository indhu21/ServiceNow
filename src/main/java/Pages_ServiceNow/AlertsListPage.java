package Pages_ServiceNow;


import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.sun.mail.imap.protocol.Status;

import ServiceNow_Integration.suitemethods;
import testng.SuiteMethods;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class AlertsListPage extends SuiteMethods{

	private final RemoteWebDriver driver;

	public AlertsListPage(RemoteWebDriver driver) {

		this.driver = driver;
		switchToMainFrame();
		resetImplicitWait(5);
		// Check that we're on the right page.
//		if(!(isExistByXpath("HomeTitle_Xpath")||isExistByXpath("NavBar2_Xpath"))) {
//			Reporter_ServiceNow.reportStep("This is not the Alerts List page", "FAILURE");
//		}
		resetImplicitWait(30);
	}
	public AlertsListPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public AlertsListPage isPresntAlertVolumebyApplication(){
		scrollToElementByXpath("Alerts_AlertVolumebyApplication_Xpath");

		if(isExistByXpath("Alerts_AlertVolumebyApplication_Xpath"))
			Reporter_ServiceNow.reportStep("The Alert Volume by Application graph displayed as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Volume by Application graph is not found.", "FAILURE");
		return this;
	}

	public MenuPage refreshPage(){

		if(refresh())
			Reporter_ServiceNow.reportStep("The Page is refreshed as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The page could not be refreshed", "FAILURE");
		return new MenuPage(driver) ;
	}

	public LoginPage clickLogout(){
		// go out of the frame
		switchToDefault();

		// Step 15: Log out
		if (clickByXpath("Logout_Xpath"))
			Reporter_ServiceNow.reportStep("The Log out is successful.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Log out link could not be clicked.", "FAILURE");

		return new LoginPage();
	}

	public AlertsListPage verifyIsRecordDisplays(String scen){
		if(isExistByXpath("NoRecords_xpath")){			
			playScenario(scen);
			if(!selectMenu("Ops_Consoles", "Alert_Console"))
				Reporter_ServiceNow.reportStep("The Alert Console under OpsConsole - menu could not be selected","FAILURE");
			switchToMainFrame();
			if(isExistByXpath("NoRecords_xpath"))
				Reporter_ServiceNow.reportStep("Insufficient Data, hence failure..","INSUFFICIENT DATA");
		}				
		return this;
	}

	public AlertPage clickNewCI(){
		if(clickById("New_Button"))
			Reporter_ServiceNow.reportStep("The New button is clicked as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The New buttonh could not found.", "FAILURE");
		return new AlertPage (driver);
	}

	public AlertPage searchAndclickScopeNumber(String scopeNum){

		if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", scopeNum))
			Reporter_ServiceNow.reportStep("The created Data: " + scopeNum + " could not be found", "FAILURE");

		// Wait for few seconds
		Wait(5000);

		// Step 13B: Click on the scope Number
		if (clickLink(scopeNum, false))
			Reporter_ServiceNow.reportStep("The created scope number : " + scopeNum + " has been found and clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The created scope number : " + scopeNum + " could not be found", "FAILURE");

		return new AlertPage (driver);
	}

	public AlertsListPage deleteFilter(){
		if(!deleteAllFilters())
			Reporter_ServiceNow.reportStep("The All Filters could not be removed", "FAILURE");		
		return this;
	}

	public AlertsListPage clickNewAlert(){

		switchToFrame("Frame_Main");
		if(clickLink("New_Alerts"))
			Reporter_ServiceNow.reportStep("The New Alerts link in the alert console is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The New Alerts link in the alert console is not clicked or not found", "FAILURE");
		return this;
	}

	public AlertsListPage addFirstFilter(String type, String condition,String value ){

		clickAlertFunnelIcon();

		deleteFilter();

		if(!addNewFilterUsingInput(type, condition, value))
			Reporter_ServiceNow.reportStep("The New Filters could not be selected","FAILURE");

		Wait(5000);

		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Values: "+type+" "+condition+" "+value+" is selected successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");

		Wait(5000);	

		return this;
	}

	public AlertsListPage addFirstFilterEnter1(String type, String condition,String value ){

		clickAlertFunnelIcon();

		deleteFilter();

		if(!addNewFilterUsingInput1(type, condition, value))
			Reporter_ServiceNow.reportStep("The New Filters could not be selected","FAILURE");

		Wait(5000);

		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Values: "+type+" "+condition+" "+value+" is selected successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");

		Wait(5000);	

		return this;
	}

	public AlertsListPage verifyRecordsAvl(){

		if(getTextByXpath("ALERTPROFILE_FirstAlert_Xpath").equals("No records to display")){			
			Reporter_ServiceNow.reportStep("No Matching records available for above filter condtion, hence failed.","FAILURE");
		}

		return this;
	}

	public String getAlertId() {
		String alertId = getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
		System.out.println(alertId);
		if(alertId.equals(""))
			Reporter_ServiceNow.reportStep("The Alert Id is Blank", "FAILURE");
		return alertId;
	}

	public AlertPage clickFirstAlertId(String alertId ){

		verifyData();

		//		if(clickLink(alertId, false))
		if(clickByXpath("FirstLink_Xpath"))
			Reporter_ServiceNow.reportStep("The First Alert:"+ alertId +" is selected successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Alert:"+ alertId +" could not be selected","FAILURE");

		Wait(3000);

		return new AlertPage(driver);
	}

	public AlertsListPage clickAlertFunnelIcon(){
		if(!clickByXpath("CIS_FunnelIcon_Xpath"))
			Reporter_ServiceNow.reportStep("The funnel icon could not be clicked","FAILURE");

		return this;
	}

	public AlertsListPage clickTableHeading(String Occurrence ){
		if(clickTableHeading("ALERTPROFILE_LastOccurenceSort_Xpath", "Last Occurrence"))
			Reporter_ServiceNow.reportStep("Alert Profiles is sorted Most recently","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Alert Profiles could not be sorted Most recently","FAILURE");
		return this;
	}

	private String state;
	public String getstate() {
		state = getDefaultValueByXpath("ALERTRECORD_AlertState_Xpath");
		if(state.equals(""))
			Reporter_ServiceNow.reportStep("The state is Blank", "FAILURE");
		return state;
	}

	private String severity;
	public String getseverity() {
		severity = getDefaultValueByXpath("ALERTRECORD_AlertSeverity_Xpath");
		if(severity.equals(""))
			Reporter_ServiceNow.reportStep("The severity is Blank", "FAILURE");
		return severity;
	}

	public MenuPage verifySeverityAndState(String severity, String state ){
		if(severity.contains("Critical") && state.equals("New"))
			Reporter_ServiceNow.reportStep("The Alert Severity: "+severity+" Alert State: "+ state +" is matched successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Severity: "+severity+" Alert State: "+ state +" could not be matched","FAILURE");
		return new MenuPage(driver);
	}

	public MenuPage clickSCENARIOSStatusClear(){

		if(clickLink("Punch Node status Clear", false))
			Reporter_ServiceNow.reportStep("The Punch Node Status Clear in Scenarios clicked successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Punch Node Status Clear in Scenarios could not be clicked","FAILURE");

		if(!clickByXpath("SCENARIOS_PlayScenarios_Xpath"))
			Reporter_ServiceNow.reportStep("The Play Scenarios could not be clicked","FAILURE");
		Wait(5000);
		if(clickByXpath("SCENARIOS_Close_Xpath"))
			Reporter_ServiceNow.reportStep("The Close Button is clicked successfully","SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Close Button could not be clicked","FAILURE");
		return new MenuPage(driver);
	}
	public MenuPage verifyUpdatedSeverityAndState(String severity, String state ){
		if(severity.equals("Clear") && state.equals("Closed"))
			Reporter_ServiceNow.reportStep("The Alert Severity: "+severity +" Alert State: "+ state +" is matched successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Severity: "+ severity +" Alert State: "+ state +" could not be matched","FAILURE");

		return new MenuPage(driver);
	}

	public AlertsListPage clickSettingIcon(){
		if(clickByXpath("ALERT_SettingsIcon_Xpath"))	
			Reporter_ServiceNow.reportStep("The settings in Alert section has been clicked successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The settings in Alert section could not be clicked","FAILURE");

		return this;
	}

	public AlertsListPage verifyListContents(){
		String[] expectedValues = {"Related Task","Related Task.State"};
		Wait(5000);

		if(verifyListContents("ALERT_SlushSelected_Xpath",expectedValues))
			Reporter_ServiceNow.reportStep("The fields :Related Task, State are found in the available list.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The fields :Related Task, Related Task.State are not found in the available list.", "FAILURE");

		// Click Ok
		if(!clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The Ok button could not be clicked","FAILURE");

		return this;
	}
	public MenuPage verifyTableHeaders(){
		String[] expectedTableValues = {"Related Task","State"};

		Wait(2000);

		if(verifyTableHeaders("Alerts_MAlertTableHead_Xpath",expectedTableValues))
			Reporter_ServiceNow.reportStep("The fields :Related Task, State are found in the table columns.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The fields :Related Task, State are not found in the table columns.", "FAILURE");

		return new MenuPage(driver);
	}


	public AlertsListPage verifyRefreshCycle(String refRate){

		switchToFrame("Frame_Main");
		//		if(getTextById("ALERT_GetRefresh_Xpath").equals(refRate))
		if(getDefaultValueByXpath("ALERT_SelectRefresh_Xpath").equals(refRate))
			Reporter_ServiceNow.reportStep("The alert refresh rate is set to the minimum value:"+refRate+" as expected", "SUCCESS");
		else{
			if(selectByVisibleTextByXpath("ALERT_SelectRefresh_Xpath", refRate))
				Reporter_ServiceNow.reportStep("The alert refresh rate is set to the minimum value:"+refRate+" as expected", "SUCCESS");
			else
				Reporter_ServiceNow.reportStep("The alert refresh rate is not set to the minimum value:"+refRate+", hence failure.", "FAILURE");
		}

		return this;
	}

	public AlertsListPage clickMyGroupAlert(){

		if(clickByXpath("ALERT_MyGroupAlertHeader_Xpath"))
			Reporter_ServiceNow.reportStep("My Group Alerts link in the Alert Console is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("My Group Alerts in the alert console is not clicked or not found.", "FAILURE");

		return new AlertsListPage(driver);
	}
	public AlertsListPage rightClicktoacknowledge(String alert){

		Wait(5000);
		if(!rightClickByLinkText(alert, false))
			Reporter_ServiceNow.reportStep("The Right click on Alert is failed.","FAILURE");

		if(clickByXpath("ALERT_Acknowledge_Xpath"))			
			Reporter_ServiceNow.reportStep("The Alert: "+alert+" acknowledged successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert: "+alert+" is not acknowledged","FAILURE");
		return this;
	}

	public AlertsListPage rightClickAlertAndacknowledge(String alertId){

		if(!rightClickByLinkText(alertId, false))
			Reporter_ServiceNow.reportStep("The Right click on the alert could not be clicked","FAILURE");

		if (IsElementNotPresentByXpath("ALERT_Acknowledge_Xpath"))
			Reporter_ServiceNow.reportStep("The Acknowledge button could not been found as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Acknowledge button has been found.", "FAILURE");
		return this;
	}

	public AlertsListPage checkAllAlertAndVerifyAcknowledge(){

		if(!clickByXpath("ALERT_AllAlertsChkBox_Xpath"))
			Reporter_ServiceNow.reportStep("The All alerts checkbox could not be checked","FAILURE");

		clickByXpath("ALERT_AllAlertsSelect_Xpath");

		if(!selectByVisibleTextByXpath("ALERT_AllAlertsSelect_Xpath","Acknowledgment"))
			Reporter_ServiceNow.reportStep("The All alerts checkbox is checked successfully and All alerts do not have Acknowledgment options as expected","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The All alerts has Acknowledgment options; hence failed","FAILURE");
		return new AlertsListPage(driver);
	}

	public AlertsListPage clickMyAlertslink(){

		if(clickByXpath("ALERT_MyAlertHeader_Xpath"))
			Reporter_ServiceNow.reportStep("The My Alerts Link has been clicked successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The My Alerts Link could not be clicked","FAILURE");


		return this;
	}

	public AlertsListPage verifyFilterCondition(String fullName){

		String val = getTextByXpath("ALERT_BREADCRUMB_Xpath");		
		System.out.println(val);

		if((val.toLowerCase()).contains(("Assigned to is "+fullName).toLowerCase()))
			Reporter_ServiceNow.reportStep("The filter Assigned to is set with the value: "+fullName+" as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The filter Assigned to is not set with the value: "+fullName, "FAILURE");

		if(val.contains("State != Closed .or. State != Resolved"))
			Reporter_ServiceNow.reportStep("The filter State is set with the value: State is not closed or state is not resolved as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The filter State is not set with the value: State is not closed or state is not resolved", "FAILURE");

		/*		if(val.contains("Related Task != Resolved .or. Related Task != Closed"))
			Reporter_ServiceNow.reportStep("The filter Related Task is set with the value: Resolved or Closed as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The filter Related Task is not set with the value: Resolved or Closed", "FAILURE");

		 */		return this;
	}

	public AlertsListPage selectAllAlertAndClose(){

		//switchToFrame("Frame_Main");
		if(!clickByXpath("ALERT_AllCheck_Xath"))
			Reporter_ServiceNow.reportStep("All alerts could not be selected","FAILURE");

		if(!selectByVisibleTextByXpath("ALERT_Actionsandselcet_Xpath", "   Close..."))
			Reporter_ServiceNow.reportStep("Close could not be selected","FAILURE");

		Wait(2000);
		if(!clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("Close OK for all Alert could not be clicked.","FAILURE");

		return this;
	}


	public boolean acknowledgeAlert() {

		boolean bReturn = false;

		switchToFrame("Frame_Main");

		String alertId = getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");

		if(!rightClickByLinkText(alertId, false))
			Reporter_ServiceNow.reportStep("Right click on the alert could not be clicked","FAILURE");

		if (clickByXpath("ALERT_Acknowledge_Xpath"))
			bReturn=true;
		else
			Reporter_ServiceNow.reportStep("Acknowledge could not be clicked","FAILURE");

		switchToFrame("Frame_Nav");

		if(!selectMenu("Ops_Consoles", "My_Alert_Console"))
			Reporter_ServiceNow.reportStep("Step 4: My Alert Console under OpsConsole - menu could not be selected","FAILURE");

		Wait(1000);

		switchToFrame("Frame_Main");

		return bReturn;
	}

	public AlertsListPage createChildAlert(String scenario) {

		playScenario(scenario);

		if(!enterById("filter_Id", "Alert Console"))
			Reporter_ServiceNow.reportStep("The Alert Console could not be entered in filter box","FAILURE");

		if(!clickById("filter_Clear_Id"))
			Reporter_ServiceNow.reportStep("The Filter Box could not be cleared","FAILURE");

		if(!selectMenu("Ops_Director","Ops_Consoles", "Alert_Console"))
			Reporter_ServiceNow.reportStep("The Alert Console under OpsConsole - menu could not be selected","FAILURE");

		switchToFrame("Frame_Main");

		String parent = getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
		String child = getTextByXpath("ALERTPROFILE_SecondAlert_Xpath");


		if(!rightClickByLinkText(parent, false))
			Reporter_ServiceNow.reportStep("The Alert Number on the alert "+ parent +" could not be clicked","FAILURE");

		if(!clickByXpath("ALERT_LinktoParent_Xpath"))	
			Reporter_ServiceNow.reportStep("The Click on the Link to Parent for Alert Number: "+ parent +" could not be clicked","FAILURE");

		if(!enterById("ALERT_Parent_Id", child))
			Reporter_ServiceNow.reportStep("The Child Alert : "+ child +" could not be entered","FAILURE");

		if(!clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The Ok Button : could not be clicked","FAILURE");

		if(!clickLink("New_Alerts"))
			Reporter_ServiceNow.reportStep("The New Alerts in the alert console could not be clicked", "FAILURE");

		if(!clickByXpath("CIS_FunnelIcon_Xpath"))
			Reporter_ServiceNow.reportStep("The funnel icon could not be clicked","FAILURE");

		if(!deleteAllFilters())
			Reporter_ServiceNow.reportStep("The filter could not be removed","FAILURE");

		if(!addNewFilterUsingInput("Child Count", "greater than", "0"))
			Reporter_ServiceNow.reportStep("The Filter could not be set to Alerts that has Child count greater than 0", "FAILURE");

		if(!clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Run button is not clicked", "FAILURE");

		return this;

	}


	public AlertsListPage verifyChildCount() {

		verifyData();

		int column=getColumnIndex1("Alerts_TableHeading_Xpath", "Child Count");

		String childCount=getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false);

		//scrollToElementByXpath("Alerts_TableHeading_Xpath");

		if(!clickByXpath("ALERTPROFILE_FirstHierList_Xpath"))
			Reporter_ServiceNow.reportStep("The Hierarchical lists is not Clicked or not found.", "FAILURE");

		waitUntillElementTobeVisible("ALERTPROFILE_FirstNavBar_Xpath");

		if(clickByXpath("ALERTPROFILE_FirstNavBar_Xpath"))
			Reporter_ServiceNow.reportStep("The Navigation Bar is Clicked successfully.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Navigation Bar could is not Clicked or not found.", "FAILURE");

		String NavBarchild=getTextByXpath("ALERTPROFILE_FirstNavBarChildCount_Xpath");

		if(NavBarchild.contains(childCount))
			Reporter_ServiceNow.reportStep("The Child Count is matched with Child Alerts section as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Child Count is not be matched with Child Alerts section, hence failure.","FAILURE");

		return this;	
	}
	public MenuPage switchToMenuFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

		return new MenuPage(driver);
	}

	public AlertProfilePage clickColumnValue(String value, String alertId) {

		int column=getColumnIndex1("Alerts_TableHeading1_Xpath", value);

		String alertProfile=getTextByXpath("(//*[@class='vt'])["+column+"]", false);

		if(clickLink(alertProfile, false))
			Reporter_ServiceNow.reportStep("The "+value+": "+alertProfile+" for Alert "+ alertId +" is selected as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+value+": "+alertProfile+" for Alert "+ alertId +" is not selected, check snapshot.","FAILURE");

		return new AlertProfilePage(driver);

	}
	public AlertsListPage clickStateClosed() {

		if(!clickByXpath("Alert_StateClosed_Xpath"))
			Reporter_ServiceNow.reportStep("The State: Closed link is not clicked.","FAILURE");
		return this;

	}

	public AlertPage clickCreatedAlert(String alert){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",alert))
			Reporter_ServiceNow.reportStep("The Alert Number: "+alert+" could not be entered", "FAILURE");

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Alert Number: "+alert+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Number: "+alert+" is not clicked or not found", "FAILURE");

		return new AlertPage(driver);
	}	

	public AlertsListPage rightclickAlert(String alertId) {

		switchToMainFrame();
		if(!rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Right click on the alert could not be clicked","FAILURE");

		return this;
	}

	public AlertsListPage rightClickAcknowledgeAlert(String alertId) {

		if(clickByXpath("ALERT_Acknowledge_Xpath"))
			Reporter_ServiceNow.reportStep("The Alert Number: "+ alertId +" is acknowledged as expected.","SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Acknowledge alert is not clicked","FAILURE");

		return this;
	}

	public AlertsListPage rightClickCloseAlert(String alertId, String closeMess) {

		if(!rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Right click on the alert could not be clicked","FAILURE");

		if(!clickByXpath("ALERT_Close_Xpath"))
			Reporter_ServiceNow.reportStep("The Close alert could not be clicked","FAILURE");

		if(clickByXpath("ALERT_CloseAsOther_Xpath"))
			Reporter_ServiceNow.reportStep("The Close as Other for same Alert: "+ alertId +" is clicked successfully","SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Close as Other for same Alert: "+ alertId +" could not be clicked","FAILURE");

		if(enterById("close_Id", closeMess))
			Reporter_ServiceNow.reportStep("The Value: "+ closeMess +" is entered in Closure Remark field as expected.","SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ closeMess +" is not entered, check snapshot.","FAILURE");

		if(clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The OK Button is clicked successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The OK Button is not clicked or not found.","FAILURE");
		return this;	
	}
	public AlertsListPage verifyIsAlertNotDisplayed(String alert) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",alert)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Alert Number: "+alert+" could not be entered", "FAILURE");

		if(getTextByXpath("ALERTPROFILE_FirstAlert_Xpath").equals("No records to display"))
			Reporter_ServiceNow.reportStep("The Alert Number: "+ alert +" is not listed in My Alerts section as expected.","SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Alert Number: "+ alert +" is listed in My Alerts section, hence failure","FAILURE");

		return this;
	}

	public String getColumnValue(String value) {

		int column=getColumnIndex1("Alerts_TableHeading1_Xpath", value);

		String alertProfile=getTextByXpath("(//*[@class='vt'])["+column+"]", false);

		if(alertProfile.equals(""))
			Reporter_ServiceNow.reportStep("The Alert Profile Name is Blank", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The "+value+" Value: "+alertProfile+" is noted as expected.", "SUCCESS");

		return alertProfile;
	}	

	public String getNewAlertsColumnValue(String value) {

		int column=getColumnIndex1("Alerts_TableHeading_Xpath", value);

		String alertProfile=getTextByXpath("(//*[@class='vt'])["+column+"]", false);

		if(alertProfile.equals(""))
			Reporter_ServiceNow.reportStep("The Alert Profile Name is Blank", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The "+value+" Value: "+alertProfile+" is noted as expected.", "SUCCESS");
		return alertProfile;
	}	

	public AlertSuppressorsPage suppressAlertUsingProfile(String alert) {

		if(!clickByXpath("ALERT_SupressAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Supress alert could not be clicked","FAILURE");

		if(clickByXpath("ALERT_SupressByProfile_Xpath"))
			Reporter_ServiceNow.reportStep("The Right click on an alert: "+ alert +" and suppress alerts from Alert Profile is successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Supress by alert profile could not be clicked","FAILURE");

		return new AlertSuppressorsPage(driver);
	}





	public AlertSuppressorsPage suppressAlertUsingCI(String alert) {

		if(!clickByXpath("ALERT_SupressAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Supress alert could not be clicked","FAILURE");

		if(clickByXpath("ALERT_SupressByCI_Xpath"))
			Reporter_ServiceNow.reportStep("The Right click on an alert: "+ alert +" and suppress alerts from Alert CI is successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Supress by alert profile could not be clicked","FAILURE");

		return new AlertSuppressorsPage(driver);
	}
	public AlertSuppressorsPage suppressAlertUsingAlertScope(String alert) {

		if(!clickByXpath("ALERT_SupressAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Supress alert could not be clicked","FAILURE");

		if(clickByXpath("ALERT_SupressByScope_Xpath"))
			Reporter_ServiceNow.reportStep("The Right click on an alert: "+ alert +" and suppress alerts from Alert CI is successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Supress by alert profile could not be clicked","FAILURE");

		return new AlertSuppressorsPage(driver);
	}
	public AlertsListPage deleteAllAlerts() {


		if(!clickByXpath("ALERT_AllCheck_Xath"))
			Reporter_ServiceNow.reportStep("All alerts could not be selected","FAILURE");

		if(!selectByVisibleTextByXpath("ALERT_Actionsandselcet_Xpath", "   Close..."))
			Reporter_ServiceNow.reportStep("The close could not be selected","FAILURE");

		if(clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("All Alerts is deleted from AlertList as expected..","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The OK Button is not clicked or not found.","FAILURE");

		return this;
	}

	public AlertPage clickFirstAlert (){		

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First Alert has been clicked successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Alert could not be clicked","FAILURE");

		return new AlertPage(driver);
	}	
	public AlertPage clickFirstLink(){		

		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First Alert has been clicked successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Alert could not be clicked","FAILURE");

		return new AlertPage(driver);
	}	

	public AlertsListPage verifyValCondition(String groupName){

		String valCondition = getTextByXpath("ALERT_BREADCRUMB_Xpath");

		System.out.println(valCondition);
		if(valCondition.contains(groupName))
			Reporter_ServiceNow.reportStep("The Value: "+groupName+" is matched with the Owning group as expected.", "SUCCESS");				
		else
			Reporter_ServiceNow.reportStep("The Value: "+groupName+" does not matched with the Owning group, hence failure", "FAILURE");

		return this;
	}

	public AlertsListPage addNewFilterinAlertUsingSelect(String filterType, String filterCondition, String filterValue){
		if(!clickByXpath("CIS_FunnelIcon_Xpath"))
			Reporter_ServiceNow.reportStep("The funnel icon could not be clicked","FAILURE");
		Wait(10000);

		deleteAllFilters();

		addNewFilterUsingSelect(filterType, filterCondition, filterValue);		

		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The filter values selected with "+filterType+" "+filterCondition+" "+filterValue+" and Run is clicked", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");
		return this;
	}
	public MenuPage verifycountOfSeverity(String filterValue){

		int countOfSeverity = getCountOfElementsByXpath("//td[contains(text(),'"+filterValue+"')]", false);			
		String numOfRows = getTextByXpath("ALERT_RowCount_Xpath");

		Wait(3000);
		if((""+countOfSeverity).equals(numOfRows))
			Reporter_ServiceNow.reportStep("The list of Alerts have only "+filterValue+" severity as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The list of Alerts does not match with "+filterValue+" severity, hence failure.","FAILURE");
		return new MenuPage(driver);
	}
	public AlertsListPage rightClickCloseAsNoise(String alert) {

		if(!clickByXpath("ALERT_Close_Xpath"))
			Reporter_ServiceNow.reportStep("Close alert could not be clicked","FAILURE");

		if(!clickByXpath("ALERT_CloseAsNoise_Xpath"))
			Reporter_ServiceNow.reportStep("Right click on Close as Noise from Alert could not be clicked","FAILURE");

		if(!enterById("close_Id", "Automation Test"))
			Reporter_ServiceNow.reportStep("The Value: Test is not entered, check snapshot.","FAILURE");

		if(clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The Right click on Close as Noise for Alert: "+alert+" is successful.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ok Button is not clicked or not found .","FAILURE");


		return this;
	}

	public AlertsListPage clickAllLink() {

		Wait(3000);
		if(!clickByXpath("ALERTRECORD_All_Xpath"))
			Reporter_ServiceNow.reportStep("The All Link is not clicked hence failure.","FAILURE");

		return this;
	}
	public AlertsListPage verifyData(){
		if(isExistByXpath("NoRecords_xpath")){
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		return this;
	}

	public AlertsListPage addFirstFilterEnter(String type, String condition,String value ){

		Wait(1000);
		clickAlertFunnelIcon();

		deleteFilter();

		if(!addNewFilter(type, condition, value))
			Reporter_ServiceNow.reportStep("The New Filters could not be selected","FAILURE");

		Wait(5000);

		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Values: "+type+" "+condition+" "+value+" is selected successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");

		Wait(5000);	

		return this;
	}

	public AlertsListPage addTwoFilters(String type, String condition,String value, String type1, String condition1, String value1 ){

		clickAlertFunnelIcon();

		deleteFilter();

		if(!addNewFilterUsingInput1(type, condition, value))
			Reporter_ServiceNow.reportStep("The New Filters could not be selected","FAILURE");

		Wait(5000);

		clickANDCondition();

		addSecondFilter(type1, condition1, value1);

		if(clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Values: "+type+" "+condition+" "+value+" is selected successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");

		Wait(5000);	

		return this;
	}
	public AlertsListPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");

		return this;
	}
	public AlertsListPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValue2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public AlertsListPage sortAlertTable(String value) {

		int column=getColumnIndex1("NewAlerts_TableHead_Xpath", value);
		if(!clickByXpath("(//*[@id='hdr_x_tori2_opd_alert'])/following::a[@class='column_head list_hdrcell table-col-header']["+column+"]", false))
			Reporter_ServiceNow.reportStep("The "+value+" is not clicked, hence failure.","FAILURE");
		if(!isExistByXpath("((//a[text()='"+value+"'])/preceding::i)/following::span/a/i[@class='sort-icon-padding list-column-icon icon-vcr-up']", false)){
			clickByXpath("(//*[@id='hdr_x_tori2_opd_alert'])/following::a[@class='column_head list_hdrcell table-col-header']["+column+"]", false);
		}
		Reporter_ServiceNow.reportStep("The Alert Table is sorted by "+value+" as expected.","SUCCESS");

		return this;

	}
	public AlertPage clickTopMostAlert(){		

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Top Most Alert has been clicked successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Alert could not be clicked","FAILURE");

		return new AlertPage(driver);
	}
	public AlertsListPage verifyFilterConditions(String fullName){

		String val = getTextByXpath("ALERT_BREADCRUMB_Xpath");		
		System.out.println(val);

		if((val.contains("State = Flapping .or. State = New")))
			Reporter_ServiceNow.reportStep("The filter is set with the value: State is flapping or state is new as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The filter is not set with the value: State is flapping or state is new", "FAILURE");
		return this;
	}
	public AlertPage searchAndclickAlert(String alert){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", alert))
			Reporter_ServiceNow.reportStep("The Alert " + alert + " could not be entered", "FAILURE");

		// Wait for few seconds
		Wait(5000);

		// Step 13B: Click on the scope Number
		if (clickLink(alert, false))
			Reporter_ServiceNow.reportStep("The Alert : " + alert + " has been found and clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert : " + alert + " could not be found", "FAILURE");

		return new AlertPage (driver);
	}
	public AlertsListPage clickMyAlertslinkwithoutReport(){
		if(!clickByXpath("ALERT_MyAlertHeader_Xpath"))
			Reporter_ServiceNow.reportStep("The My Alerts Link could not be clicked","FAILURE");
		return this;
	}
	public AlertPage verifyAlertmovetoMyalerts(String alert){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", alert))
			Reporter_ServiceNow.reportStep("The Alert " + alert + " could not be entered", "FAILURE");

		// Wait for few seconds
		Wait(5000);

		// Step 13B: Click on the scope Number
		if (isExistByLinkText(alert, false))
			Reporter_ServiceNow.reportStep("The Alert : " + alert + " has been moved to My alerts section as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert : " + alert + " could not be found", "FAILURE");

		return new AlertPage (driver);
	}
	public AlertsListPage clickNewAlertwithoutReport(){

		if(!clickLink("New_Alerts"))
			Reporter_ServiceNow.reportStep("The New Alerts link in the alert console is not clicked or not found", "FAILURE");

		return this;
	}

	public AlertsListPage checkAllAlertAndAcknowledge(){

		if(!clickByXpath("ALERT_AllAlertsChkBox_Xpath"))
			Reporter_ServiceNow.reportStep("The All alerts checkbox could not be checked","FAILURE");

		//			if(!clickByXpath("ALERT_AllAlertsSelect_Xpath"))
		//				Reporter_ServiceNow.reportStep("The All alerts checkbox could not be checked","FAILURE");

		if(selectByVisibleTextByXpath("ALERT_AllAlertsSelect_Xpath","Acknowledgment")){
			if(isExistByXpath("NoRecords_xpath"))
				Reporter_ServiceNow.reportStep("The All alerts checkbox is checked successfully and All alerts Acknowledged as expected","SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The All alerts could not be Acknowledged; hence failed","FAILURE");
		return new AlertsListPage(driver);
	}

	public AlertsListPage verifyAllAlertsPresentMyalertsSection(List<WebElement> list){
		String alerts="";

		String alert="";

		for (WebElement webElement : list) {
			alert= webElement.getText();
			if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
				Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

			if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", alert))
				Reporter_ServiceNow.reportStep("The Alert " + alert + " could not be entered", "FAILURE");

			// Wait for few seconds
			Wait(5000);

			// Step 13B: Click on the scope Number
			if (!isExistByLinkText(alert, false))
				Reporter_ServiceNow.reportStep("The Alert : " + alert + " could not be found", "FAILURE");

			alerts=alerts+" ,"+alert;

		}

		Reporter_ServiceNow.reportStep("All Alerts "+alerts+" get listed in My Alerts with state acknowledged as expected.", "SUCCESS");
		return this;
	}
	public String  getParentAlert() {

		String parent = getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");

		if(parent.equals(""))
			Reporter_ServiceNow.reportStep("Could not fetch the Parent alert, hence failure","FAILURE");

		return parent;
	}

	public String  getChildAlert() {


		String child = getTextByXpath("ALERTPROFILE_SecondAlert_Xpath");

		if(child.equals(""))
			Reporter_ServiceNow.reportStep("Could not fetch the Child alert, hence failure","FAILURE");


		return child;
	}
	public AlertsListPage rightclickAndLinktoParent(String parent, String child) {

		if(!rightClickByLinkText(parent, false))
			Reporter_ServiceNow.reportStep("The Alert Number on the alert "+ parent +" could not be clicked","FAILURE");

		if(clickByXpath("ALERT_LinktoParent_Xpath"))	
			Reporter_ServiceNow.reportStep("The Link to Parent for Alert Number: "+ parent +" is clicked successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Click on the Link to Parent for Alert Number: "+ parent +" could not be clicked","FAILURE");

		if(!enterAndChoose("ALERT_Parent_Xpath", child))
			Reporter_ServiceNow.reportStep("The Child Alert: "+ child +" could not be entered","FAILURE");

		Wait(5000);
		if(clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The Child Alert: "+ child +" is entered and Ok Button is clicked succefully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ok Button : could not be clicked","FAILURE");

		return this;
	}
	public AlertsListPage verifyChildCountInListView(String alert) {

		clickAllLink();
		
		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if (!enterByXpathAndClick("CIS_SearchReferenceData_Xpath", alert))
			Reporter_ServiceNow.reportStep("The Alert " + alert + " could not be entered", "FAILURE");

		verifyData();

		int column=getColumnIndex1("Alerts_TableHeading_Xpath", "Child Count");

		String childCount=getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false);

		//scrollToElementByXpath("Alerts_TableHeading_Xpath");
		//			int num=Integer.parseInt(childCount);
		//			String s=Integer.toString(num);

		if(Integer.parseInt(childCount)>0)
			Reporter_ServiceNow.reportStep("The Child Count for Parent Alert "+alert+" greater than 1 as expected.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The Child Count for Parent Alert "+alert+" is not greater than, hence failure.", "FAILURE");

		return this;
	}

	public AlertsListPage verifyChildAlert(String child, String parent) {

		verifyData();

		int column=getColumnIndex1("Alerts_TableHeading_Xpath", "Child Count");

		String childCount=getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false);


		if(!clickByXpath("ALERTPROFILE_FirstHierList_Xpath"))
			Reporter_ServiceNow.reportStep("The Hierarchical lists is not Clicked or not found.", "FAILURE");
		
		if(isExistByLinkText(child, false))				
			Reporter_ServiceNow.reportStep("The Child Alert "+child+" is appeared under parent alert:"+parent+" section as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Child Alert "+child+" is not appeared under parent alert:"+parent+" section, hence failure..","FAILURE");

		return this;
	}

	public AlertProfilePage clickAlertProfileWithoutReport(String value, String alertId) {

		int column=getColumnIndex1("Alerts_TableHeading1_Xpath", value);

		String alertProfile=getTextByXpath("(//*[@class='vt'])["+column+"]", false);

		if(!clickLink(alertProfile, false))
			Reporter_ServiceNow.reportStep("The "+value+": "+alertProfile+" for Alert "+ alertId +" is not selected, check snapshot.","FAILURE");

		return new AlertProfilePage(driver);

	}
	public AlertsListPage clickMyAlertslinkWithoutReport(){
		if(!clickByXpath("ALERT_MyAlertHeader_Xpath"))
			Reporter_ServiceNow.reportStep("The My Alerts Link could not be clicked","FAILURE");


		return this;
	}

	public AlertsListPage createChildAlertwithoutReport(String parent, String child) {

		if(!rightClickByLinkText(parent, false))
			Reporter_ServiceNow.reportStep("The Alert Number on the alert "+ parent +" could not be clicked","FAILURE");

		if(!clickByXpath("ALERT_LinktoParent_Xpath"))	
			Reporter_ServiceNow.reportStep("The Click on the Link to Parent for Alert Number: "+ parent +" could not be clicked","FAILURE");

		if(!enterById("ALERT_Parent_Id", child))
			Reporter_ServiceNow.reportStep("The Child Alert : "+ child +" could not be entered","FAILURE");

		if(!clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The Ok Button : could not be clicked","FAILURE");

		if(!clickLink("New_Alerts"))
			Reporter_ServiceNow.reportStep("The New Alerts in the alert console could not be clicked", "FAILURE");

		if(!clickByXpath("CIS_FunnelIcon_Xpath"))
			Reporter_ServiceNow.reportStep("The funnel icon could not be clicked","FAILURE");

		if(!deleteAllFilters())
			Reporter_ServiceNow.reportStep("The filter could not be removed","FAILURE");

		if(!addNewFilterUsingInput("Number", "is", parent))
			Reporter_ServiceNow.reportStep("The Filter could not be set to Alerts that has number is "+parent+", hence failure. ", "FAILURE");

		if(!clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Run button is not clicked", "FAILURE");

		return this;

	}	
	public 	AlertsListPage verifyState(String state) {

		int column=getColumnIndex1("Alerts_TableHeading_Xpath", "State");

		String childCount=getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false);

		//scrollToElementByXpath("Alerts_TableHeading_Xpath");
		if(childCount.equalsIgnoreCase("Closed"))
			Reporter_ServiceNow.reportStep("The Alert State is matched with value "+state+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert State is not matched with value "+state+", hence failure.", "FAILURE");

		return this;
	}
	public 	AlertsListPage clickHideIcon() {
		if(clickByXpath("ALERTPROFILE_FirstHierList_Xpath"))
			Reporter_ServiceNow.reportStep("The Display/Hide arrow is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Display/Hide arrow is not clicked, hence failure.", "FAILURE");

		return this;
	}

	public AlertsListPage isChildCountClosed(String child) {



		int column=getColumnIndex1("Alerts_TableHeading_Xpath", "State");

		String childCount=getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false);

		//scrollToElementByXpath("Alerts_TableHeading_Xpath");
		if(childCount.equals("closed")){

			if(!clickByXpath("ALERTPROFILE_FirstHierList_Xpath"))
				Reporter_ServiceNow.reportStep("The Hierarchical lists is not Clicked or not found.", "FAILURE");

			if(isExistByLinkText(child, false))
				Reporter_ServiceNow.reportStep("The Child Alert "+child+" is in Closed State as expected.", "SUCCESS");
			else	
				Reporter_ServiceNow.reportStep("The Child Alert "+child+" is not in Closed State, hence failure..", "FAILURE");
		}
		else
			Reporter_ServiceNow.reportStep("The Parent alert not in closed state, hence failure.", "FAILURE");


		return this;	
	}
	public AlertsListPage rightClicktoacknowledge1(String alert){

		Wait(5000);
		if(!rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Right click on Alert is failed.","FAILURE");

		if(clickByXpath("ALERT_Acknowledge_Xpath"))			
			Reporter_ServiceNow.reportStep("The Alert: "+alert+" acknowledged successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert: "+alert+" is not acknowledged","FAILURE");
		return this;
	} 
	public AlertsListPage addNinethFilter(String filterType, String filterCondition){
		waitUntillElementTobeVisible("AlertList_FilterType9_Xpath");

		addFilters("AlertList_FilterType9_Xpath", filterType, "AlertList_FilterCondition9_Xpath", filterCondition);
		return this;
	} 
	public AlertsListPage clickRun(){

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)", "");
		Wait(500);

		if(!clickByXpath("ALERT_RunFilter_Xpath"))
			Reporter_ServiceNow.reportStep("Run could not be clicked", "FAILURE");

		return this;
	}
	public AlertsListPage addNinethFilterByEnterValue(String filterType, String filterCondition, String filterValue){


		waitUntillElementTobeVisible("AlertList_FilterType9_Xpath");

		String text = getTextByXpath("AlertList_FilterType9_Xpath");

		System.out.println(text);

		if(text.contains("Show Related Fields")){
			selectByVisibleTextByXpath("AlertList_FilterType9_Xpath", "Show Related Fields");}

		selectByValueTextByXpath("AlertList_FilterType9_Xpath", "alert_profile...");


		addFilterstoEnterAndChooseValue("AlertList_FilterType9_Xpath", filterType, "AlertList_FilterCondition9_Xpath", filterCondition, "AlertList_FilterValueInput9_Xpath", filterValue);


		return this;
	} 
	public AlertsListPage virifyCloseAsNoisePopup() {

		if(!clickByXpath("ALERT_Close_Xpath"))
			Reporter_ServiceNow.reportStep("Close alert could not be clicked","FAILURE");

		if(clickByXpath("ALERT_CloseAsNoise_Xpath"))
			Reporter_ServiceNow.reportStep("The Right click on First Alert performed and Close as Noise clicked successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Right click on Close as Noise from Alert could not be clicked","FAILURE");

		if(isExistById("close_Id"))
			Reporter_ServiceNow.reportStep("The Close As Noice popup appeared as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Close As Noice popup did not appeared, hence failure.","FAILURE");

		return this;
	}
	public AlertsListPage rightclickAlert() {

		switchToMainFrame();
		if(!rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Right click on the alert could not be clicked","FAILURE");

		return this;
	}

	public AlertsListPage addNinethFilter(){
		
		waitUntillElementTobeVisible("AlertList_FilterType9_Xpath");
		
		List<String> texts=getOptionsByXpath("AlertList_FilterType9_Xpath");
		
		if(texts.contains("Show Related Fields")){
			selectByValueTextByXpath("AlertList_FilterType9_Xpath", "Show Related Fields");}

		selectByValueTextByXpath("AlertList_FilterType9_Xpath", "alert_profile...");
		
		return this;
	} 
}

