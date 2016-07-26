package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ColorUtils;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class CmdbListPage extends ListPage{

	private final RemoteWebDriver driver;

	public CmdbListPage(RemoteWebDriver driver) {
		super(driver);
		
		this.driver = driver;
		resetImplicitWait(5);
		// Switch to the main frame
		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar1_Xpath")) {
			Reporter.reportStep("This is not the CMDB List page", "FAILURE");
		}
		resetImplicitWait(30);
	}
	public OracleInstancesPage clickNewButtonforOracle(){	
		clickNewButton();
		return new OracleInstancesPage(driver);
	}

	public OracleInstancesPage clickCMDBTasklinkforOracleIns(String task){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The Created task: "+task+" is clicked Successfully and The task selected as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created task: "+task+" could not be clicked", "FAILURE");

		return new OracleInstancesPage(driver);
	}

	public ServerWindowsPage clickWinServerNewButton(){	
		switchToMainFrame();

		if(clickById("New_Button"))
			Reporter.reportStep("The New button is clicked sucessfully and New CI opened as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new ServerWindowsPage(driver);
	}

	public StoragePoolsPage clickStrPoolsNewButton(){	

		if(clickById("New_Button"))
			Reporter.reportStep("The New button is clicked sucessfully and New CI opened as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new StoragePoolsPage(driver);
	}

	public StoragePoolsPage clickFirstStrPoolsNamelink(String name){

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The "+name+" link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The "+name+" link could not be clicked", "FAILURE");

		return new StoragePoolsPage(driver);
	}
	public CmdbListPage searchCMDB(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "CMDB Task"))
			Reporter.reportStep("The CMDB Task could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);
			Reporter.reportStep("The Created Task: "+CMDBTask+" is searched successfully", "SUCCESS");}
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		return this;
	}	

	public SystemApplicationsPage clickCMDBTasklink(String task){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The Created Task: "+task+" is selected as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created Task: "+task+" could not be clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}

	public PDUsPage clickNewButtonforPDUs(){	

		clickNewButton();
		return new PDUsPage(driver);
	}

	public PDUsPage clickCMDBTasklinkforPDUs(String task){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The Created Task:"+task+" is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The Created Task:"+task+" could not be clicked.", "FAILURE");

		return new PDUsPage(driver);
	}
	public StoragePoolsPage clickStoragePoolsCMDBTasklink(){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The First CI link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First CI link could not be clicked", "FAILURE");

		return new StoragePoolsPage(driver);
	}

	public ServerWindowsPage clickServerWindowsCMDBTasklink(){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The First CI link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First CI link could not be clicked", "FAILURE");

		return new ServerWindowsPage(driver);
	}

	public NetworkSwitchesPage clickNwSwitchesNewButton(){	

		if(clickById("New_Button"))
			Reporter.reportStep("The New button is clicked sucessfully and New CI opened as expected.", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new NetworkSwitchesPage(driver);
	}

	public NetworkSwitchesPage clickFirstNwSwitchesNamelink(String name){

		verifyData();
		// click the first Incident Link			
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The "+name+" link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The "+name+" link could not be clicked", "FAILURE");

		return new NetworkSwitchesPage(driver);
	}

	public NetworkSwitchesPage clickFirstNwSwitcheslink(){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The First link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First link could not be clicked", "FAILURE");

		return new NetworkSwitchesPage(driver);
	}

	public CmdbListPage verifyISRecordDisplayed(){

		// click the first Incident Link
		if(getTextByXpath("PDus_RecordDisplay_Xpath").contains("No records to display"))
			Reporter.reportStep("No records to display", "FAILURE");
		else
			Reporter.reportStep("The User is presented with a list of Messages ", "SUCCESS");

		return this;
	}

	
	public CriticalAttributesPage clickFirstCriticalAttributelink(){

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The First link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First link could not be clicked", "FAILURE");

		return new CriticalAttributesPage(driver);
	}

	public CriticalAttributesPage clickNewButtonforCriticalAttributes(){	
		if(clickById("New_Button"))
			Reporter.reportStep("The New button is clicked sucessfully and Critical Attribute form is opened as expected", "SUCCESS");				
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new CriticalAttributesPage(driver);
	}

	public CmdbListPage isNewButtonExists() {
		if(IsElementPresentByXpath("New_xpath"))
			Reporter.reportStep("The New Button is available on the top of the list as expected", "SUCCESS");
		else	
			Reporter.reportStep("The New Button could not be found in the page", "FAILURE");

		return  this;
	}

	public StoragePoolsPage clickStoragePoolsCMDBTasklink(String task){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The First link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First link could not be clicked", "FAILURE");

		return new StoragePoolsPage(driver);
	}

	public CmdbListPage clickAll() {
		Wait(2000);
		if(!clickByXpath("ListPage_ReconciliationCIsAll_Xpath"))
			Reporter.reportStep("The All  could not be clicked", "FAILURE");

		return this;

	}	

	//Raj 11-12
	public UPSPage clickNewButtonforUPS(){	

		if(clickById("New_Button"))
			Reporter.reportStep("The New button is clicked sucessfully and New CI page opened as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");
		return new UPSPage(driver);
	}

	public UPSPage clickUPSCMDBTasklink(){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The First link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First link could not be clicked", "FAILURE");

		return new UPSPage(driver);
	}


	public CmdbListPage searchConfigItem(String ConfigItem) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Configuration Item"))
			Reporter.reportStep("The Configuration Item could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",ConfigItem)){
			pressKey(Keys.ENTER);
			Wait(2000);
			Reporter.reportStep("The Configuration Item: "+ConfigItem+" is searched successfully", "SUCCESS");}
		else
			Reporter.reportStep("The Configuration Item: "+ConfigItem+" could not be clicked", "FAILURE");

		return this;
	}	

	public CmdbListPage clickReconciliationCIsAll() {

		if(!clickByXpath("ListPage_ReconciliationCIsAll_Xpath"))
			Reporter.reportStep("The All could not be clicked", "FAILURE");

		return this;

	}
	public SystemApplicationsPage searchCMDBandSelectforSysApp(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "CMDB Task"))
			Reporter.reportStep("The CMDB Task could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be entered for search, hence failure.", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The Created Task: "+CMDBTask+" is searched and selected as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}

	public OracleInstancesPage searchCMDBandSelectOracle(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "CMDB Task"))
			Reporter.reportStep("The CMDB Task could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be entered for search, hence failure.", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The Created Task: "+CMDBTask+" is searched and selected as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		return new OracleInstancesPage(driver);
	}
	public NetworkSwitchesPage searchCMDBandSelectNW(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "CMDB Task"))
			Reporter.reportStep("The CMDB Task could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be entered for search, hence failure.", "FAILURE");

		verifyData();
		
		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The Created Task: "+CMDBTask+" is searched and selected as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		return new NetworkSwitchesPage(driver);
	}

	public ServerWindowsPage searchCMDBandSelectWS(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "CMDB Task"))
			Reporter.reportStep("The CMDB Task could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))

			Reporter.reportStep("The Created Task: "+CMDBTask+" is searched and selected as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		return new ServerWindowsPage(driver);
	}

	public StoragePoolsPage searchCMDBandSelectSP(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "CMDB Task"))
			Reporter.reportStep("The CMDB Task could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))

			Reporter.reportStep("The Created Task: "+CMDBTask+" is searched and selected as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		return new StoragePoolsPage(driver);
	}
	public UPSPage searchCMDBandSelectUPS(String CMDBTask) {

		if(!selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "CMDB Task"))
			Reporter.reportStep("The CMDB Task could not be selected", "FAILURE");

		if(enterByXpath("CIS_SearchReferenceData_Xpath",CMDBTask)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		verifyData();
		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))

			Reporter.reportStep("The Created Task: "+CMDBTask+" is searched and selected as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created Task: "+CMDBTask+" could not be clicked", "FAILURE");

		return new UPSPage(driver);
	}
	public SystemApplicationsPage clickCMDBTasklink(){

		// click the first Incident Link
		if(clickByXpath("ALERT_FirstAlert_Xpath"))
			Reporter.reportStep("The First link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First link could not be clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}
	public CmdbListPage isDeleteButtonPresentUsingSelectedRows(){

		verifyData();
		// Verify the tabs exists
		clickFirstAlertCheckBox();
		
		scrollToElementByXpath("All_Select_Xpath");
		//clickByXpath("ALERTPROFILE_ActionsOn_SelectedRows_Xpath");

		//Reporter.reportStep("The delete action on the selected incident is read only as expected", "SUCCESS");

		if(selectByVisibleTextByXpath("All_Select_Xpath","   Delete"))
			Reporter.reportStep("The Delete option is found as expected" , "SUCCESS");
		else
			Reporter.reportStep("The Delete option is not found, hence failed","FAILURE");	

		if(!clickByXpath("Cancel_Xpath"))
			Reporter.reportStep("The Cancel option is not clicked or not found, hence failed","FAILURE");	

		return this;
	}
	public ControllersPage clickCI(String ci){

		
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)", "");
		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objRep.getProperty("NewAlerts_GotoSelect_Xpath"))));
//		
		if(selectByVisibleTextByXpath("NewAlerts_GotoSelect_Xpath", "Number"))
			Reporter.reportStep("The Number could not be selected", "FAILURE");

		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",ci))
			Reporter.reportStep("The Incident number:"+ci+" could not be clicked", "FAILURE");

		// click the first Incident Link
		if(clickLink(ci, false))
			Reporter.reportStep("The CI: "+ci+" link is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The CI  "+ci+" link could not be clicked", "FAILURE");

		return new ControllersPage(driver);
	}
	public CmdbListPage clickFirstAlertCheckBox(){

		// Verify the tabs exists
		if(!clickByXpath("ALERTPROFILE_FirstAlert_CheckBox_Xpath"))  
			Reporter.reportStep("The Check box of first alert could not be clicked ", "FAILURE");

		return this;
	}
	public CmdbListPage clickFirstCraetedMessage(String user){

		// Verify the tabs exists
		if(!clickByXpath("CMDB_FirstMessange_Xpath"))  
			Reporter.reportStep("The First Message not be clicked or not found, hence failure.", "FAILURE");

		scrollToElementByXpath("(//*[text()='CMDB task  has been assigned to you/your group for review and approval.'])", false);
		
		if(isExistByXpath("(//*[text()='CMDB task  has been assigned to you/your group for review and approval.'])", false))
			Reporter.reportStep("The First Message is clicked and The Mesage informed that CMDB task has been assigned to "+user+" as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Mesage could not be matched, hence failure.", "FAILURE");
			return this;
	}
	
	
	
}
