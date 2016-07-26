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

public class ListPage extends SuiteMethods{

	private final RemoteWebDriver driver;

	public ListPage(RemoteWebDriver driver) {
	
		this.driver = driver;
		switchToMainFrame();
	}

	public ListPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main", 5);

		return this;
	}

	public ListPage searchIncident(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",incNumber)){
			pressKey(Keys.ENTER);
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is searched successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		return this;
	}

	public IncidentPage clickFirstIncident(){

		verifyData();
		// click the first Incident Link
		scrollToElementByXpath("ALERTPROFILE_FirstAlert_Xpath");
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First Incident is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Incident could not be clicked", "FAILURE");

		return new IncidentPage(driver);
	}

	// incorrect
	public ListPage isSparcActive(){

		// Verify the tabs exists
		if(verifyTextByXpath("ATMe_SPARCActive_Xpath", "SPARC Active = true"))		
			Reporter_ServiceNow.reportStep("The Active field is verified successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Active field is not matched", "FAILURE");
		return this;
	}


	public ListPage rightClickonFirstAlert(){

		verifyData();
		// Verify the tabs exists
		if(rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))		
			Reporter_ServiceNow.reportStep("Right click on the first Incident is performed successfully ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Right click could not be clicked ", "FAILURE");
		return this;
	}

	public ListPage ClickAssignToMe(){

		// Verify the tabs exists
		if(clickByXpath("ALERT_AssignToMe_Xpath"))		
			Reporter_ServiceNow.reportStep("The Assign to me clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assign to me could not be clicked", "FAILURE");
		return this;
	}

	public ListPage verifyAssignedToError(String username){

		// Verify the tabs exists
		if(verifyTextByXpath("ATMe_ErrorMessage_Xpath", "Not able to assign this ticket to yourself."))		
			Reporter_ServiceNow.reportStep("Assign To Me not possible as expected for the user: "+username, "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Assign To Me was possible for the user: "+username, "FAILURE");
		return this;
	}

	public ListPage isFieldEditable(){

		// Verify the tabs exists
		String[] fields = {"ATMe_StateEdit_Xpath"};
		String[] desc = {"State"};

		// double click on that field
		//doubleCickByXpath(fields[0]);

		// check the fields are disable
		if(driver.findElementByXPath(objRep.getProperty("ATMe_StateEdit_Xpath")).getTagName().equals("input"))
			verifyDisabledFieldsByXpath(fields,desc);
		else
			Reporter_ServiceNow.reportStep("The fields in the incident list view could not be edited which is as expected.", "SUCCESS");
		return this;
	}

	public ListPage deleteIncidents(){

		// Verify the tabs exists
		if(selectByVisibleTextById("ATMe_SelectDelete_Id", "Delete"))		
			Reporter_ServiceNow.reportStep("The delete option is enabled and selectable", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The delete option is read only as expected", "SUCCESS");
		return this;
	}

	public ListPage changeDisplayColumns (){

		/*
		// Verify the tabs exists
		if(!clickByXpath("ATMe_TaskSLA_SettingsIcon_Xpath"))
			Reporter_ServiceNow.reportStep("Setting could not be clicked ", "FAILURE");

		if(isExistById("ATMe_Reset_Id"))
			Reporter_ServiceNow.reportStep("Change display columns is performed successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Reset to column defaults do not exists!", "FAILURE");

		clickById("Ok_Id");
		 */

		if(clickByXpath("ATMe_TaskSLA_SettingsIcon_Xpath"))		{
			isExistById("ATMe_Reset_Id");
			clickById("Ok_Id");
			Reporter_ServiceNow.reportStep("Setting is clicked and able to change display columns successfully ", "SUCCESS");

		}

		else			
			Reporter_ServiceNow.reportStep("Setting could not be clicked ", "FAILURE");
		return this;
	}

	public ListPage deleteFilters(){	
		if(!clickByXpath("EditFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter icon could not be clicked", "FAILURE");

		deleteAllFilters();
		return this;
	}

	public ListPage addAssignedToFilter(String username){		
		addNewFilterUsingInput1("Assigned To", "is", username);
		return this;
	}

	public ListPage verifyFilterresettedForAssignedtoMe(){

		//Click Filter icon
		if(!clickByXpath("EditFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter icon could not be clicked", "FAILURE");


		// Verify the tabs exists
		if(isExistByXpath("CIS_FirstFilterType1_Xpath"))		
			Reporter_ServiceNow.reportStep("The Filter is resetted to default on clicking Assigned to me link ", "SUCCESS");
		else

			Reporter_ServiceNow.reportStep("The Filter could not be resetted to default on clicking Assigned to me link", "FAILURE");

		return this;
	}

	public ListPage isNewButtonExists(){	
		if(IsElementNotPresentByXpath("New_xpath"))
			Reporter_ServiceNow.reportStep("The New button is not found on the top of the list view which is as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The New button is found on the top of the view", "FAILURE");

		return this;
	}

	public ListPage addPersonalizedListColumn(){

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)", "");
		Wait(500);

		if(clickByXpath("INCLIST_ToggleSettings_Xpath")){
			Wait(3000);
			Reporter_ServiceNow.reportStep("The Cog Button is clicked successfully", "SUCCESS");
		}else
			Reporter_ServiceNow.reportStep("The Cog Button could not be clicked", "FAILURE");

		Wait(5000);

		String[] optionValues = getTextByXpath("ALERT_BreachinAvailable_Xpath").split("\n");
		System.out.println(convertStringArrayToString(optionValues));


		if(!selectByIndexByXpath("ALERT_BreachinAvailable_Xpath",0))
			Reporter_ServiceNow.reportStep("The "+optionValues[1]+" select value could not be selected", "FAILURE");


		if(!clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
			Reporter_ServiceNow.reportStep("The first data of the Available drop down could not be selected", "FAILURE");
		else{
			Wait(2000);
			Reporter_ServiceNow.reportStep("The column:"+optionValues[1]+" has been moved to Selected successfully", "SUCCESS");
		}

		String listValues = getTextByXpath("ALERT_SlushSelected_Xpath");

		if(!clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The Ok Button of the Personalized List Columns could not be clicked", "FAILURE");

		if(compareListAndTable(listValues,"INCLIST_TableHeader_Xpath")){
			((JavascriptExecutor) driver).executeScript("window.scrollBy(10000,0)", "");
			Wait(5000);
			Reporter_ServiceNow.reportStep("The "+optionValues[1]+" column is displayed in the Incident List View and able to change the display column as expected.", "SUCCESS");
		}else
			Reporter_ServiceNow.reportStep("The "+optionValues[1]+" column was not added to the List View.", "FAILURE");

		clickByXpath("INCLIST_ToggleSettings_Xpath");
		Wait(2000);

		clickById("ATMe_Reset_Id");
		Wait(2000);

		clickById("Ok_Id");

		return this;
	}
	public ListPage isDeleteIncidentUsingActionsOnSelectedRows(){

		//		// Verify the tabs exists
		//		clickFirstAlertCheckBox();
		//		Wait(5000);
		//		//scrollToElementByXpath("ALERTPROFILE_ActionsOn_SelectedRows_Xpath");


		if(selectByVisibleTextByXpath("ALERTPROFILE_ActionsOn_SelectedRows_Xpath","Delete"))
			Reporter_ServiceNow.reportStep("The delete action on the selected incident can be performed.", "FAILURE");

		if(clickAndHold("ALERTPROFILE_ActionsOn_SelectedRows_Xpath")){
			Wait(5000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,10000)", "");
			Wait(500);
			Reporter_ServiceNow.reportStep("The delete action on the selected incident is read only as expected", "SUCCESS");}
		return this;
	}

	public ListPage clickFunnel(){  

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)", "");
		Wait(500);

		if(clickByXpath("EditFilter_Xpath")){
			Wait(2000);
			Reporter_ServiceNow.reportStep("Funnel icon is clicked successfully", "SUCCESS");
		}else   
			Reporter_ServiceNow.reportStep("Funnel icon could not been clicked", "FAILURE");
		return this;
	}


	public ListPage isFilterEditable(){	

		clickFunnelWithoutReport();

		deleteAllFilters();
		if(addNewFilterUsingSelect("Priority", "is", "1 - Critical")){
			clickRun();
			Wait(2000);
			Reporter_ServiceNow.reportStep("The user able to change the Filters as expected", "SUCCESS");
		} else
			Reporter_ServiceNow.reportStep("The Filter did not be edited","FAILURE");			


		return this;
	}

	public ListPage clickRun(){  

		if(!clickByXpath("Run_Xpath"))
			Reporter_ServiceNow.reportStep("Run Button could not been clicked", "FAILURE");

		return this;
	}

	public ListPage verifyFilterText(String[] filterText){

		// Verify the filter has
		if(verifyAllTextsUsingArray("WIP_ConditionPresent_Xpath", filterText)){
			Wait(2000);
			Reporter_ServiceNow.reportStep("The Incident filters appeared with values :"+convertStringArrayToString(filterText)+" as expected", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The Incident filters did not appear with values :"+convertStringArrayToString(filterText), "FAILURE");

		return this;
	}

	public ListPage verifyNonResolved(String[] filterText, String userName){

		// Verify the filter has
		if(verifyAllTextsUsingArray("WIP_ConditionPresent_Xpath", filterText)){
			Wait(2000);
			Reporter_ServiceNow.reportStep("The Incident filters appeared with Active but not in a state of Resolved for the user: "+userName+" as expected", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The Incident filters did not appear with values :"+convertStringArrayToString(filterText), "FAILURE");

		return this;
	}

	public ListPage verifyAssigned(String username){

		// Verify the tabs exists
		if(!verifyTextByXpath("ATMe_ErrorMessage_Xpath", "Not able to assign this ticket to yourself."))		
			Reporter_ServiceNow.reportStep("The incident is assigned to the user:"+username+" successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Unable to assign ticket to myself, hence failure", "FAILURE");

		return this;
	}
	public ListPage verifyFilterresettedtodefault(){

		//Click Filter icon
		if(!clickByXpath("EditFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter icon could not be clicked", "FAILURE");


		// Verify the tabs exists
		if(IsElementPresentByXpath("CIS_FirstFilterType1_Xpath"))  
			Reporter_ServiceNow.reportStep("The Filter is resetted to default value ", "SUCCESS");
		else

			Reporter_ServiceNow.reportStep("The Filter could not be resetted to default value", "FAILURE");

		return this;
	}
	public ListPage clickFirstAlertCheckBox(){

		// Verify the tabs exists
		if(!clickByXpath("ALERTPROFILE_FirstAlert_CheckBox_Xpath"))  
			Reporter_ServiceNow.reportStep("The Check box of first alert could not be clicked ", "FAILURE");

		return this;
	}
	public ListPage isVipFlagExistsNextToIncidentNumber (){

		if(isExistByXpath("ALERTPROFILE_FirstAlert_VIP_Flag_Xpath"))
			Reporter_ServiceNow.reportStep("The VIP Flag displayed next to the Incident number field", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The VIP Flag not appeared next to the Incident number in number field", "FAILURE");

		return this;

	}
	public SystemApplicationsPage clickNewButtonforSA(){	
		switchToMainFrame();

		if(clickById("New_Button"))
			Reporter_ServiceNow.reportStep("The New button is clicked successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The New button could not been found or clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}

	public SystemApplicationsPage clickFirstNamelink(){

		verifyData();

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First CI link is clicked Successfully and CI page opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First CI link could not be clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}

	public SystemApplicationsPage clickLinkName(String linkName){

		// click the first Incident Link
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item is clicked successfully", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The Value: "+ linkName +" under Configuration Item could not be clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}
	public ListPage verifyNonResolvedforGroup(String[] filterText, String userName, String groupName){

		// Verify the filter has
		if(verifyAllTextsUsingArray("WIP_ConditionPresent_Xpath", filterText)){
			Wait(2000);
			Reporter_ServiceNow.reportStep("The Incident filters appeared with Active but not in a state of Resolved for the user: "+userName+", Group: "+groupName+" as expected", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The Incident filters did not appear with values :"+convertStringArrayToString(filterText), "FAILURE");

		return this;
	}

	public OracleInstancesPage clickLinkNameforOracle(String linkName){

		// click the first Incident Link
		Wait(2000);
		if(clickLink(linkName, false))
			Reporter_ServiceNow.reportStep("The Created Task:"+linkName+" is clicked Successfully and CI page opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created Task:"+linkName+" could not be clicked.", "FAILURE");

		return new OracleInstancesPage(driver);
	}

	public CmdbListPage clickFirstNamelink(String CMDBTask){

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created Task:"+CMDBTask+" is opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created Task:"+CMDBTask+" could not be clicked.", "FAILURE");
		return new CmdbListPage(driver);
	}

	public SystemApplicationsPage clickSysAppNewButton(){	

		if(clickById("New_Button"))
			Reporter_ServiceNow.reportStep("The New button is clicked successfully and New CI opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The New button could not been found or clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}

	public ListPage selectFilter(String filter1, String filter2, String filter3){	

		Wait(3000);
		if(!clickByXpath("CI_EditFilter_Xpath"))
			Reporter_ServiceNow.reportStep("The Funnel icon could not be clicked", "FAILURE");
		if(!addNewFilterUsingSelect(filter1, filter2, filter3))
			Reporter_ServiceNow.reportStep("The Fliter value: "+filter1+" "+filter2+" "+filter3+" could not be selected", "FAILURE");
		if(clickByXpath("Run_Xpath")){
			Wait(3000);
			Reporter_ServiceNow.reportStep("The Fliter value: "+filter1+" "+filter2+" "+filter3+" is selected Successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Fliter value "+filter1+" "+filter2+" "+filter3+" could not be selected", "FAILURE");
		return this;
	}

	public IncidentPage clickFirstName(){
		Wait(2000);

		if(isExistByXpath("CI_NoRecords_Xpath"))
			Reporter_ServiceNow.reportStep("There is no matching records for selected value, hence failure", "FAILURE");
		// click the first Incident Link
		if(clickByXpath("CI_FirstCI_Xpath")){
			switchToPrimary();
			Wait(2000);
			Reporter_ServiceNow.reportStep("The First link in Causing CI is clicked Successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The First link in Causing CI could not be clicked", "FAILURE");
		return new IncidentPage(driver);
	}

	public ListPage verifyResolutionCodeSpyGlass(String[] ele){
		Boolean bSuccess = true;
		for (int i = 0; i < ele.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(ele[i])))){
					Reporter_ServiceNow.reportStep("The field in MetaData CI: "+ele[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in MetaData CI: "+ele[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(ele)+" do exists in Resolution Code Spyglass list", "SUCCESS");

		return this;

	}		

	public ListPage verifyResolutionCodeSpyGlass(String[] ele, boolean bSwitch){
		verifyResolutionCodeSpyGlass(ele);

		if(bSwitch)
			switchToPrimary();

		return this;

	}	
	public ListPage switchToNewWindow(){

		// Switch to the menu frame
		switchToSecondWindow();
		return this;
	}		

	public ServerWindowsPage clickFirstWindowServerNamelink(String name){

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The "+name+" link is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+name+" link could not be clicked", "FAILURE");

		return new ServerWindowsPage(driver);
	}

	public IncidentPage clickMissingCI(){

		// click the first Incident Link
		if(clickByXpath("INC_MissingCI_Xpath")){
			switchToPrimary();
			Wait(2000);
			Reporter_ServiceNow.reportStep("The Missing CI Button is clicked Successfully and Add CI Description Dialog box appeared as expectd.", "SUCCESS");}		
		else
			Reporter_ServiceNow.reportStep("The Missing CI Button could not be clicked", "FAILURE");

		return new IncidentPage(driver);
	}


	public PDUsPage clickLinkNamePDUs(String task){

		verifyData();
		Wait(3000);
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created Task: "+task+" is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created Task: "+task+" could not be clicked", "FAILURE");

		return new PDUsPage(driver);
	}
	public ServerWindowsPage clickFirstSerWinNamelink(String CMDBTask){

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The "+CMDBTask+" is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+CMDBTask+" could not be clicked.", "FAILURE");
		return new ServerWindowsPage(driver);
	}

	public StoragePoolsPage clickFirstStoragePoolsNamelink(String name){

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The "+name+" link is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+name+" link could not be clicked", "FAILURE");

		return new StoragePoolsPage(driver);
	}


	public NetworkSwitchesPage clickFirstNwSwitchesNamelink(String name){

		verifyData();

		Wait(3000);
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created Task:"+name+" is clicked Successfully and CI page opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+name+" link could not be clicked", "FAILURE");

		return new NetworkSwitchesPage(driver);
	}
	public ListPage clickANDCondition() {

		if(!clickByXpath("CIS_AndCondition1_Xpath"))				
			Reporter_ServiceNow.reportStep("The AND button  could not be clicked or not found", "FAILURE");
		return this;
	}
	public ListPage addGXPasNone(String filterType){
		if(!selectByVisibleTextByXpath("CIS_FirstFilterType5_Xpath", filterType))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" could not be selected","FAILURE");

		return this;
	}

	public ListPage qaOwnerisEmpty(String filterType, String filterCondition){
		if(!addFilters("CIS_FirstFilterType5_Xpath", filterType, "CIS_FilterCondition5_Xpath", filterCondition))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" could not be selected","FAILURE");

		return this;
	}

	public OracleInstancesPage clickFirstNamelinkforOracle(){

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First CI link is clicked Successfully and CI page opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI link could not be clicked", "FAILURE");

		return new OracleInstancesPage(driver);
	}
	public NetworkSwitchesPage clickFirstNamelinkforNw(){

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First CI link is clicked Successfully and CI page opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First CI Name link could not be clicked", "FAILURE");

		return new NetworkSwitchesPage(driver);
	}

	// Raj added on 01/12/2015
	public ListPage clickORCondition() {

		if(!clickByXpath("CIS_ORCondition_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}


	public SystemApplicationsPage clickFirstlink(){

		verifyData();

		Wait(5000);		
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First link is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First link could not be clicked.", "FAILURE");
		return new SystemApplicationsPage(driver);
	}

	public StoragePoolsPage clickFirstStoragePoolsNamelink(){

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First CI link is clicked Successfully and CI page opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First CI link could not be clicked", "FAILURE");

		return new StoragePoolsPage(driver);
	}


	public PDUsPage clickLinkNamePDUs(){

		verifyData();

		Wait(3000);
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created Task is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created Task could not be clicked", "FAILURE");

		return new PDUsPage(driver);
	}

	public ServerWindowsPage clickFirstWindowServerNamelink(){

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First CI link is clicked Successfully and CI page opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First CI link could not be clicked", "FAILURE");

		return new ServerWindowsPage(driver);
	}


	public ListPage addThirdFilterbyEnterValues(String filterType, String filterCondition, String filterValue){
		if(addFilterstoEnterValue("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValueEnterText3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" is entered successfully", "SUCCESS");
		else					
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be entered","FAILURE");

		return this;
	}
	public UPSPage clickFirstUPSPageNamelink(String name){

		verifyData();
		Wait(3000);
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The "+name+" link is clicked Successfully and CI page opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+name+" link could not be clicked", "FAILURE");

		return new UPSPage(driver);
	}

	public UPSPage clickFirstlinkofUPS(String name){
		Wait(3000);
		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The "+name+" link is clicked Successfully and CI page opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+name+" link could not be clicked", "FAILURE");

		return new UPSPage(driver);
	}


	public IncidentPage clickFirstCIClass(){
		Wait(5000);
		if(clickByXpath("CI_FirstCI_Xpath")){
			switchToPrimary();
			Wait(2000);
			Reporter_ServiceNow.reportStep("The First link in Configuration Item is clicked Successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The First link in Configuration Item could not be clicked", "FAILURE");
		return new IncidentPage(driver);
	}
	public IncidentPage clickFirstBusinessServiceName(){
		Wait(2000);

		if(isExistByXpath("CI_NoRecords_Xpath"))
			Reporter_ServiceNow.reportStep("There is no matching records for selected value, hence failure", "FAILURE");
		// click the first Incident Link
		if(clickByXpath("CI_FirstCI_Xpath")){
			switchToPrimary();
			Wait(2000);
			Reporter_ServiceNow.reportStep("The First Business Service link is clicked Successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The First Business Service link could not be clicked", "FAILURE");
		return new IncidentPage(driver);
	}

	//Update
	public ListPage addFilterForCAR2(String filterType1, String filterCondition1, String filterValue1,String filterType2, String filterCondition2, String filterValue2,String filterType3, String filterCondition3, String filterValue3){
		deleteFilters();
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType1, "CIS_FilterCondition1_Xpath", filterCondition1, "CIS_FilterValueSelect1_Xpath", filterValue1))       
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType1+" "+ filterCondition1+" "+ filterValue1 +" could not be selected","FAILURE");
		if(!clickByXpath("CIS_AndCondition1_Xpath"))    
			Reporter_ServiceNow.reportStep("The AND button could not be clicked or not found", "FAILURE");
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType2, "CIS_FilterCondition2_Xpath", filterCondition2, "CIS_FilterValue2_Xpath", filterValue2))     
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType2+" "+ filterCondition2+" "+ filterValue2 +" could not be selected","FAILURE");
		if(!clickByXpath("CIS_AndCondition1_Xpath"))    
			Reporter_ServiceNow.reportStep("The AND button could not be clicked or not found", "FAILURE");

		if(addFilterstoEnterValue("CIS_FirstFilterType3_Xpath", filterType3, "CIS_FilterCondition3_Xpath", filterCondition3, "CIS_FilterValueText3_Xpath", filterValue3))
			Reporter_ServiceNow.reportStep("The Filter value: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+filterType2+" "+filterCondition2+" "+ filterValue2+","+filterType3+" "+filterCondition3+" "+filterValue3 +" are entered successfully", "SUCCESS");
		else     
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType3+" "+ filterCondition3+" "+ filterValue3+" could not be entered","FAILURE");  
		return this;
	}

	public ListPage addFilterforFourValues(String filterType1, String filterCondition1, String filterValue1,String filterType2, String filterCondition2, String filterValue2,String filterType3, String filterCondition3, String filterValue3, String filterType4, String filterCondition4, String filterValue4){
		deleteFilters();
		addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilterbyEnteringvalues(filterType2, filterCondition2, filterValue2)
		.clickANDCondition()
		.addThirdFilter(filterType3, filterCondition3, filterValue3)
		.clickANDCondition()
		.addFourthFilterbyEnteringvalues(filterType4, filterCondition4, filterValue4)
		.clickRun();
		Reporter_ServiceNow.reportStep("The Filter values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+filterType2+" "+filterCondition2+" "+ filterValue2+", "+filterType3+" "+filterCondition3+" "+filterValue3 +", "+filterType4+" "+filterCondition4+" "+filterValue4+" are selected successfully", "SUCCESS");
		return this;
	}
	public ListPage addFirstFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueSelect1_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ListPage addSecondFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValue2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ListPage addThirdFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType3_Xpath", filterType, "CIS_FilterCondition3_Xpath", filterCondition, "CIS_FilterValue3_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public ListPage addFourthFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterCondition, "CIS_FilterValue4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public ListPage addFourthFilterbyEnteringvalues(String filterType, String filterCondition, String filterValue){
		if(!addFilterstoEnterValue("CIS_FirstFilterType4_Xpath", filterType, "CIS_FilterCondition4_Xpath", filterCondition, "CIS_FilterValueEnterText4_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be entered","FAILURE");

		return this;
	}

	public ListPage addSecondFilterbyEnteringvalues(String filterType, String filterCondition, String filterValue){
		if(!addFilterstoEnterValue("CIS_FirstFilterType2_Xpath", filterType, "CIS_FilterCondition2_Xpath", filterCondition, "CIS_FilterValueEnterText2_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be entered","FAILURE");

		return this;
	}


	public NetworkSwitchesPage searchandClickFirstnameforNW(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created CI:"+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI link could not be clicked", "FAILURE");

		return new NetworkSwitchesPage(driver);
	}

	public SystemApplicationsPage searchandClickFirstnameforSyApp(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI link could not be clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}

	public ServerWindowsPage searchNameandClickFirstNameWS(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);}
		else
			Reporter_ServiceNow.reportStep("The created CI: "+name+" could not be entered in searchbox.", "FAILURE");

		Wait(3000);

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI link could not be clicked", "FAILURE");

		return new ServerWindowsPage(driver);
	}
	public StoragePoolsPage searchandClickFirstnameforSP(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
			Reporter_ServiceNow.reportStep("The CMDB Name could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+CMDBTask+" could not be clicked", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))

			Reporter_ServiceNow.reportStep("The Created CI: "+CMDBTask+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+CMDBTask+" could not be clicked", "FAILURE");

		return new StoragePoolsPage(driver);
	}
	public UPSPage searchandClickFirstnameforUPS(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
			Reporter_ServiceNow.reportStep("The Configuration Item could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+CMDBTask+" could not be clicked", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created CI: "+CMDBTask+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+CMDBTask+" could not be clicked", "FAILURE");

		return new UPSPage(driver);
	}
	public OracleInstancesPage searchandClickFirstnameforOracle(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI link could not be clicked", "FAILURE");

		return new OracleInstancesPage(driver);
	}
	public PDUsPage searchandClickFirstnameforPDU(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI link could not be clicked", "FAILURE");

		return new PDUsPage(driver);
	}
	public  PDUsPage searchNameAndClick(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);	
		}
		else
			Reporter_ServiceNow.reportStep("The name: "+name+" could not be clicked", "FAILURE");

		verifyData();

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created Task: "+name+" is searched and clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created Task: "+name+" could not be clicked", "FAILURE");

		return new PDUsPage(driver);
	}

	public ListPage addFilterforThreeValues(String filterType1, String filterCondition1, String filterValue1,String filterType2, String filterCondition2, String filterValue2,String filterType3, String filterCondition3, String filterValue3){
		deleteFilters()
		.addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilter(filterType2, filterCondition2, filterValue2)
		.clickANDCondition()
		.addThirdFilter(filterType3, filterCondition3, filterValue3)
		.clickRun();
		Reporter_ServiceNow.reportStep("The Filter values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+filterType2+" "+filterCondition2+" "+ filterValue2+", "+filterType3+" "+filterCondition3+" "+filterValue3 +" are selected successfully", "SUCCESS");
		return this;
	}
	public ListPage addFilterforClassOperStatusSysMangEnviron(String filterType1, String filterCondition1, String filterValue1,String filterType2, String filterCondition2, String filterValue2,String filterType3, String filterCondition3, String filterValue3, String filterType4, String filterCondition4, String filterValue4){
		deleteFilters();
		addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilterbyEnteringvalues(filterType2, filterCondition2, filterValue2)
		.clickANDCondition()
		.addThirdFilter(filterType3, filterCondition3, filterValue3)
		.clickANDCondition()
		.addFourthFilter(filterType4, filterCondition4, filterValue4)
		.clickRun();
		Reporter_ServiceNow.reportStep("The Filter values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+filterType2+" "+filterCondition2+" "+ filterValue2+", "+filterType3+" "+filterCondition3+" "+filterValue3 +", "+filterType4+" "+filterCondition4+" "+filterValue4+" are entered successfully", "SUCCESS");
		return this;
	}
	//	public PDUsPage verifyISRecordDisplayed(){
	//
	//		// click the first Incident Link
	//		if(getTextByXpath("PDus_RecordDisplay_Xpath").contains("No records to display"))
	//			Reporter_ServiceNow.reportStep("No records to display", "FAILURE");
	//		else
	//			Reporter_ServiceNow.reportStep("The User is presented with a list of Messages ", "SUCCESS");
	//
	//		return new PDUsPage(driver);
	//	}

	public ListPage addFilterforFiveValues(	String filterType1, String filterCondition1, String filterValue1, 
			String filterType2, String filterCondition2, String filterValue2,
			String filterType3, String filterCondition3, String filterValue3,
			String filterType4, String filterCondition4, String filterValue4,
			String filterType5, String filterCondition5, String filterValue5){
		deleteFilters()
		.addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilter(filterType2, filterCondition2, filterValue2)
		.clickANDCondition()
		.addThirdFilter(filterType3, filterCondition3, filterValue3)
		.clickANDCondition()
		.addFourthFilter(filterType4, filterCondition4, filterValue4)
		.clickANDCondition()
		.addFifthFilter(filterType5, filterCondition5, filterValue5)
		.clickRun();
		Reporter_ServiceNow.reportStep("The Filter values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+filterType2+" "+filterCondition2+" "+ filterValue2+", "+filterType3+" "+filterCondition3+" "+filterValue3 +" are selected successfully", "SUCCESS");
		return this;
	}


	public ListPage addFifthFilterByEnterValues(String filterType, String filterCondition, String filterValue){
		if(!addFilterstoEnterValue("CIS_FirstFilterType5_Xpath", filterType, "CIS_FilterCondition5_Xpath", filterCondition, "CIS_FilterValueEnterText5_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	public LinuxPage clickFirstNamelinkforLinux(){
		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First CI link is clicked Successfully and CI page opened as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI link could not be clicked", "FAILURE");

		return new LinuxPage(driver);
	}

	public ListPage addFirstFilterByEnterValues(String filterType, String filterCondition, String filterValue){
		if(!addFilterstoEnterValue("CIS_FirstFilterType1_Xpath", filterType, "CIS_FilterCondition1_Xpath", filterCondition, "CIS_FilterValueEnterText1_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}

	// updated by Sneha on 22-12-2015
	public FSSTaskPage clickCreatedFSSTask(String FSSTask){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",FSSTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Created Ticket: "+FSSTask+" could not be entered", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created Ticket: "+FSSTask+" is searched and the Task opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ticket is not clicked or not found", "FAILURE");

		return new FSSTaskPage(driver);
	}

	public FSSTaskPage clickNew()
	{
		switchToMainFrame();
		//switchToFrame("Frame_Main");

		if(clickById("FSS_FSSTasks_NewButton_Id"))
			Reporter_ServiceNow.reportStep("New Button clicked successfully and New FSS Task Page opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User not able to click New Button", "FAILURE");

		return new FSSTaskPage(driver);
	}

	public FSSTaskPage clickFSSTaskForMyRequest(String FSSTask){


		switchToFrame("Frame_MyRequests", 10);
		if(!selectByVisibleTextByXpath("FSS_MyRequestSearchSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("FSS_MyRequestSearchInput_Xpath",FSSTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Ticket: "+FSSTask+" could not be entered", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Ticket: "+FSSTask+" is clicked Successfully and the Task opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ticket is not clicked or not found", "FAILURE");

		return new FSSTaskPage(driver);
	}

	public WatchedItemsPage clickFSSTaskForWatchedItems(String FSSTask){


		switchToFrame("Frame_WatchedItems", 5);
		if(!selectByVisibleTextByXpath("FSS_WatchedItemsSearchSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("FSS_WatchedItemsSearchInput_Xpath",FSSTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Ticket: "+FSSTask+" could not be entered", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Ticket: "+FSSTask+" is clicked Successfully and the Task opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ticket is not clicked or not found", "FAILURE");

		return new WatchedItemsPage(driver);
	}


	public ListPage verifycolumnValue(String colName, String value) {


		int column=getColumnIndex1("FSSTask_TableHeading_Xpath", colName);

		String text=getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false);

		scrollToElementByXpath("FSSTask_TableHeading_Xpath");

		//	System.out.println(getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false));
		if(text.equals(value))
			Reporter_ServiceNow.reportStep("The "+colName+" value: "+value+" is matched as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The "+colName+" value: "+value+" is not matched, check snapshot.", "FAILURE");


		return this;

	}

	public FSSTaskPage validateFSSTaskNumberisNotVisible(String FSSTask, String user){

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",FSSTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Ticket: "+FSSTask+" could not be entered", "FAILURE");

		// click the first Incident Link
		if(getTextByXpath("CI_NoRecords_Xpath").equals("No records to display"))
			Reporter_ServiceNow.reportStep("The Ticket: "+FSSTask+" is not visible in My Groups Work List for the user: "+user+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ticket is not clicked or not found", "FAILURE");

		return new FSSTaskPage(driver);
	}

	public FSSTaskPage validateFSSTaskisNotVisibleForMyRequest(String FSSTask, String user){


		switchToFrame("Frame_MyRequests", 5);
		if(!selectByVisibleTextByXpath("FSS_MyRequestSearchSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("FSS_MyRequestSearchInput_Xpath",FSSTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Ticket: "+FSSTask+" could not be entered", "FAILURE");

		// click the first Incident Link
		if(getTextByXpath("CI_NoRecords_Xpath").equals("No records to display"))
			Reporter_ServiceNow.reportStep("The Ticket: "+FSSTask+" is not visible in My Requests List for the user "+user+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Ticket is not clicked or not found", "FAILURE");

		return new FSSTaskPage(driver);
	}

	public ListPage searchConfigItem(String ci, String value) {
		switchToFrame("Frame_Main", 5);
		Wait(3000);
		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", ci))
			Reporter_ServiceNow.reportStep("The Class could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",value)){
			pressKey(Keys.ENTER);
			Reporter_ServiceNow.reportStep("The CI "+ci+" is "+value+" is searched successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The CI "+ci+" is "+value+" could not be clicked", "FAILURE");

		return this;

	}

	public IncidentPage verifyNORecordsDisplayed(String ci){
		Wait(5000);
		if(isExistByXpath("CI_NoRecords_Xpath")){				
			Reporter_ServiceNow.reportStep("The Records are not available on the list for the CI Value: "+ci+" as expected.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Records are available on the list for the CI Value: "+ci+", hence failure.", "FAILURE");

		getDriver().close();
		switchToPrimary();
		return new IncidentPage(driver);
	}

	public IncidentPage verifyRecordsDisplayed(String ci){
		Wait(5000);
		if(!isExistByXpath("CI_NoRecords_Xpath")){				
			Reporter_ServiceNow.reportStep("The Records available on the list for the CI Value: "+ci+" as expected.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Records not available on the list for the CI Value: "+ci+", hence failure.", "FAILURE");

		getDriver().close();
		switchToPrimary();
		return new IncidentPage(driver);
	}

	public ListPage verifyData(){
		if(isExistByXpath("NoRecords_xpath")){
			//if(isExistByXpath("//*[text()='No records to display']", false)){		
			status="Insuffient Data";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		return this;
	}

	//indhu updated on 12-01-2016
	public MSSQLInstancesPage clickFirstNamelinkforMSSQL(){

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First CI link is clicked Successfully and CI page opened as expected", "SUCCESS");
		else if (isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The CI link could not be clicked", "FAILURE");

		return new MSSQLInstancesPage(driver);
	}
	public MSSQLInstancesPage searchandClickFirstnameforMSSQL(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is searched and opened as expected.", "SUCCESS");
		else if (isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The Created CI link could not be clicked", "FAILURE");

		return new MSSQLInstancesPage(driver);
	}
	public ListPage addFilterforFiveValuesWithEnteringValues(String filterType1, String filterCondition1, String filterValue1,String filterType2, String filterCondition2, String filterValue2,String filterType3, String filterCondition3, String filterValue3, String filterType4, String filterCondition4, String filterValue4,String filterType5,String filterCondition5,String filterValue5){
		deleteFilters();
		addFirstFilter(filterType1, filterCondition1, filterValue1)       
		.clickANDCondition()
		.addSecondFilter(filterType2, filterCondition2, filterValue2)
		.clickANDCondition()
		.addThirdFilter(filterType3, filterCondition3, filterValue3)
		.clickANDCondition()
		.addFourthFilterbyEnteringvalues(filterType4, filterCondition4, filterValue4)
		.clickANDCondition()
		.addFifthFilterbyEnteringvalues(filterType5, filterCondition5, filterValue5)
		.clickRun();
		Reporter_ServiceNow.reportStep("The Filter values: "+filterType1+" "+filterCondition1+" "+ filterValue1+","+filterType2+" "+filterCondition2+" "+ filterValue2+", "+filterType3+" "+filterCondition3+" "+filterValue3 +", "+filterType4+" "+filterCondition4+" "+filterValue4+" "+filterType5+" "+filterCondition5+" "+filterValue5+" are selected successfully", "SUCCESS");
		return this;
	}

	public ListPage addFifthFilterbyEnteringvalues(String filterType, String filterCondition, String filterValue){
		if(!addFilterstoEnterValue("CIS_FirstFilterType5_Xpath", filterType, "CIS_FilterCondition5_Xpath", filterCondition, "CIS_FilterValueEnterText5_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be entered","FAILURE");

		return this;
	}
	public ListPage addFifthFilter(String filterType, String filterCondition, String filterValue){
		if(!addFilters("CIS_FirstFilterType5_Xpath", filterType, "CIS_FilterCondition5_Xpath", filterCondition, "CIS_FilterValue5_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter value "+ filterType+" "+ filterCondition+" "+ filterValue +" could not be selected","FAILURE");

		return this;
	}
	public ControllersPage clickFirstCI(){

		//scrollToElementByXpath("NavBar_Xpath");

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)", "");
		Wait(500);

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First CI link is clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First CI link could not be clicked", "FAILURE");

		return new ControllersPage(driver);
	}
	public UPSPage searchandClickFirstUPSName(String name) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
			Reporter_ServiceNow.reportStep("The Name could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be clicked", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickLink(name, false))

			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is not found or not clicked.", "FAILURE");

		return new UPSPage(driver);
	}
	public SystemApplicationsPage searchandClickSyAppName(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickLink(name, false))
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is not is not found or not clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}

	public NetworkSwitchesPage searchandClickNWName(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickLink(name, false))
			Reporter_ServiceNow.reportStep("The Created CI:"+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is not found or not clicked.", "FAILURE");

		return new NetworkSwitchesPage(driver);
	}

	public ServerWindowsPage searchandClickSWName(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickLink(name, false))
			Reporter_ServiceNow.reportStep("The Created CI:"+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is not found or not clicked.", "FAILURE");

		return new ServerWindowsPage(driver);
	}
	public OracleInstancesPage searchandClickOracleName(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickLink(name, false))
			Reporter_ServiceNow.reportStep("The Created CI:"+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is not found or not clicked.", "FAILURE");

		return new OracleInstancesPage(driver);
	}
	public StoragePoolsPage searchandClickPoolsName(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickLink(name, false))
			Reporter_ServiceNow.reportStep("The Created CI:"+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is not found or not clicked.", "FAILURE");

		return new StoragePoolsPage(driver);
	}
	public MSSQLInstancesPage searchandClickMSSQLName(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickLink(name, false))
			Reporter_ServiceNow.reportStep("The Created CI:"+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is not found or not clicked.", "FAILURE");

		return new MSSQLInstancesPage(driver);
	}
	public LinuxPage searchandClickLinuxName(String name) {
		if(enterByXpath("CIS_SearchReferenceData_Xpath",name)){
			pressKey(Keys.ENTER);
			Wait(3000);
		}
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickLink(name, false))
			Reporter_ServiceNow.reportStep("The Created CI:"+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" is not found or not clicked.", "FAILURE");

		return new LinuxPage(driver);
	}
	public IncidentPage verifyUserInformation(){

		verifyData();
		// click the first Incident Link
		if(!clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The First Incident could not be clicked", "FAILURE");

		mouseOverById("CREATEINC_Caller_Id");

		if(isExistById("CREATEINC_SysUserName_Id"))
			Reporter_ServiceNow.reportStep("The User Information displayed for opened Incident as expected.", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The User Information is not on mouse Over", "FAILURE");

		return new IncidentPage(driver);
	}
	public ListPage verifyCIComponentBusinessServiceLookUpValues(){

		Boolean bSuccess = true;
		String[] elements = {"Automated Job Failure",
				"Connectivity",				
				"Integration Issue",					 
				"Performance degradation",
		"Security breach"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}
	public ListPage verifyCIComponentApplicationLookUpValues(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		//			switchToDefault();

		String[] elements = {"Account locked",	
				"Automated Job Failure","Connectivity","Data Issue","Error message"	,
				"Integration Issue","Login failure","Memory","Not responding/Frozen",	
				"Performance degradation","Security breach","Storage","UI issue","Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}
	public ListPage verifyCausingCIComponentFieldLookUpValuesForServer(){
		Boolean bSuccess = true;

		switchToSecondWindow();
		//			switchToDefault();

		String[] elements = {"Account locked", "Automated Job Failure", "Connectivity", "Data Issue", "Disk", "Error message", "IP Address", "Login failure", "Memory", "Not responding/Frozen", "Performance degradation", "Power", "Security breach", "Storage", "Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in Causing CI component Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}
	public ListPage verifyCIComponentStorageLookUpValues(){

		Boolean bSuccess = true;
		String[] elements = {"Automated Job Failure",
				"Connectivity",	
				"Data Issue",	
				"Disk",
				"Error message",	
				"Integration Issue",	
				"IP Address",	
				"Login failure",
				"Memory",	
				"Performance degradation",	
				"Power",
				"Security breach",
		"Storage"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public ListPage verifyCIComponentLookUpValues_Networks(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Automated Job Failure", "Connectivity","Data Issue","Error message", "Integration Issue","IP Address","Login failure","Memory","Performance degradation","Power","Security breach","Virus"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "WARNING");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep("All the fields :"+convertStringArrayToString(elements)+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;
	}

	public ListPage verifyCIComponentDatabaseLookUpValues(){

		Boolean bSuccess = true;
		switchToSecondWindow();
		switchToDefault();

		String[] elements = {"Account locked",	
				"Automated Job Failure","Connectivity","Data Issue","Disk","Error message","Integration Issue",
				"IP Address","Login failure","Memory",	
				"Performance degradation","Security breach","Storage"};

		for (int i = 0; i < elements.length; i++) {
			try {
				if(!isExist(driver.findElement(By.linkText(elements[i])))){
					Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
					bSuccess = false;
				}
			} catch (Exception e) {
				Reporter_ServiceNow.reportStep("The field in CI component: "+elements[i]+" is not available.", "FAILURE");
				bSuccess = false;
			}
		}

		if(bSuccess)
			Reporter_ServiceNow.reportStep(" All the fields :"+convertStringArrayToString(elements).replaceAll(",", ", ")+" do exists in CI configuration Look up list", "SUCCESS");

		switchToPrimary();
		return this;

	}

	public IncidentPage searchAndOpenTask(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The created Task: "+incNumber+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The created Task: "+incNumber+" could not be clicked.", "FAILURE");

		return new IncidentPage(driver);
	}
	public IncidentPage searchAndOpenIncident(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The created Incident: "+incNumber+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The created Incident: "+incNumber+" could not be clicked.", "FAILURE");

		return new IncidentPage(driver);
	}
	public IncidentPage searchandClickIncident(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",incNumber)){
			pressKey(Keys.ENTER);
			//				Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is searched successfully", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is searched and clicked Successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Incident could not be clicked", "FAILURE");

		return new IncidentPage(driver);
	}
	public ListPage rightClickAndAssignToMe(){

		verifyData();
		// Verify the tabs exists
		if(!rightClickByXpath("ALERTPROFILE_FirstAlert_Xpath"))		
			Reporter_ServiceNow.reportStep("Right click could not be clicked ", "FAILURE");

		// Verify the tabs exists
		if(clickByXpath("ALERT_AssignToMe_Xpath"))		
			Reporter_ServiceNow.reportStep("The Right click on First Incident is successful and Assign to me clicked as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assign to me could not be clicked", "FAILURE");
		return this;
	}
	public ListPage verifyFilterTextAterReset(String[] filterText){

		// Verify the filter has
		if(verifyAllTextsUsingArray("WIP_ConditionPresent_Xpath", filterText)){
			Wait(2000);
			Reporter_ServiceNow.reportStep("The Incident filters appeared with values :"+convertStringArrayToString(filterText)+" after filters reseted as expected.", "SUCCESS");
		}
		else
			Reporter_ServiceNow.reportStep("The Incident filters did not appear with values :"+convertStringArrayToString(filterText), "FAILURE");

		return this;
	}

	public ListPage clickFunnelWithoutReport(){  

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)", "");
		Wait(500);

		if(clickByXpath("EditFilter_Xpath")){
			Wait(2000);}
		else   
			Reporter_ServiceNow.reportStep("Funnel icon could not been clicked", "FAILURE");
		return this;
	}
	public ListPage verifyNonResolvedforthirdFilter(String userName){

		clickFunnelWithoutReport();
		clickANDCondition();
		addThirdFilter("State", "is", "Resolved");
		clickORCondition3();
		addFourthFilter("State", "is", "Closed");
		clickRun();
		if(isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Only Incidents assigned to "+userName+" that are Active but not in a state of Resolved Ticket/Closed as expected.", "SUCCESS");

		return this;
	}
	public ListPage clickORCondition3() {

		if(!clickByXpath("CIS_ORCondition3_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}
	public ListPage clickORCondition2() {

		if(!clickByXpath("CIS_ORCondition2_Xpath"))				
			Reporter_ServiceNow.reportStep("The OR button  could not be clicked or not found", "FAILURE");
		return this;
	}

	public ListPage verifyNonResolvedforsecondFilter(String userName){

		clickFunnelWithoutReport();
		clickANDCondition();
		addSecondFilter("State", "is", "Resolved");
		clickORCondition2();
		addThirdFilter("State", "is", "Closed");
		clickRun();
		if(isExistByXpath("NoRecords_xpath"))
			Reporter_ServiceNow.reportStep("Only Incidents assigned to "+userName+" that are Active but not in a state of Resolved Ticket/Closed as expected.", "SUCCESS");

		return this;
	}
	public ListPage searchandSelectCI(String name) {
		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",name))
			Reporter_ServiceNow.reportStep("The Created CI: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("Checkbox_Xpath"))
			Reporter_ServiceNow.reportStep("The Systems/Applications is clicked and The CI "+name+" is selected as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI "+name+" could not be selected.", "FAILURE");

		return this;
	}	
	public ListPage isDeletenotPresent(){

		if(selectByVisibleTextByXpath("All_Select_Xpath","Delete"))
			Reporter_ServiceNow.reportStep("The delete action on the selected incident can be performed.", "FAILURE");

		if(clickAndHold("All_Select_Xpath")){
			Wait(5000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,10000)", "");
			Wait(500);
			Reporter_ServiceNow.reportStep("The delete action on the selected CI is read only as expected", "SUCCESS");}
		return this;
	}
	
	public ListPage searchIncidentforDemo(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber)){
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is created successfully", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");
		return this;
	}
	
	public ListPage searchAndOpenIncidentAssign(String incNumber, String user) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(getTextByXpath("ALERTPROFILE_FirstAlert_Xpath").equals(incNumber))
			Reporter_ServiceNow.reportStep("The Incident "+incNumber+" successfully assigned to "+user+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Incident could not be assigned to "+user,"FAILURE");

		return this;
	}

	public ListPage searchAndOpenIncidentResolve(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(getTextByXpath("ALERTPROFILE_FirstAlert_Xpath").equals(incNumber))
			Reporter_ServiceNow.reportStep("The Incident "+incNumber+" successfully Resolved as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Incident could not be resolved, hence failure","FAILURE");

		return this;
	}
	public ListPage searchandClickIncidentDemoPositive(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(getTextByXpath("ALERTPROFILE_FirstAlert_Xpath").equals(incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is found as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is not found, hence failure.", "FAILURE");

		return this;
	}
	public ListPage searchandClickIncidentDemoNegative(String incNumber) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",incNumber))
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" could not be clicked", "FAILURE");

		if(isExistByXpath("NoRecords_xpath")){
				Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is not found as expected.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Incident number:"+incNumber+" is found, hence failure.", "FAILURE");

		return this;
	}
	public ApplicationPage searchandClickFirstApplication(String ciName) {
		
		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Name"))
			Reporter_ServiceNow.reportStep("The Name could not be selected, hence failure", "FAILURE");
		
		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",ciName))
			Reporter_ServiceNow.reportStep("The Created CI: "+ciName+" could not be entered in searchbox, hence failure.", "FAILURE");

		verifyData();
		
		// click the first Incident Link
		if(clickLink(ciName, false))
			Reporter_ServiceNow.reportStep("The Given CI:"+ciName+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Given CI could not be clicked or not found.", "FAILURE");

		return new ApplicationPage(driver);
	}
	public ListPage verifyCIComponentBusinessServiceLookUpCIcounts(){


		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and The values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and The values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }
	public ListPage verifyCIComponentApplicationLookUpValuesCIcounts(){


		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }
	public ListPage verifyCausingCIComponentFieldLookUpValuesForServerCIcounts(){


		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }

	public ListPage verifyCausingCIComponentFieldLookUpValuesForDatabaseCIcounts(){


		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }

	public ListPage verifyCIComponentLookUpValues_NetworksCIcounts(){


		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }

	public ListPage verifyCIComponentStorageLookUpValuesCIcounts(){


		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }
	public ListPage verifyResolutionCodeSpyGlassCIcounts(){


		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The Resolution Code Spyglass is clicked and the values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The Resolution Code Spyglass is clicked and the values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }
	public ListPage verifyCIComponentSpyGlassCIcounts(){


		  if(getCountOfElementsByXpath("Inc_CIcomponentValues_Xpath")>0)
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values are listed in the CI configuration Look up list as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The CI Component Spyglass is clicked and the values is not listed in CI configuration Look up list, hence failure.", "FAILURE");

		  switchToPrimary();
		  
		  return new ListPage(driver);

		 }


}


