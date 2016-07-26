package pages;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

//import org.apache.bcel.generic.RETURN;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;


import testng.SuiteMethods;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class MenuPage extends SuiteMethods{

	private final RemoteWebDriver driver;

	public MenuPage(RemoteWebDriver driver) {
		this.driver = driver;
		goOutOfFrame();

		// Check that we're on the right page.
		//		if (!isExistById("Welcome_Id")) {
		//			Reporter.reportStep("This is not the home page", "FAILURE");
		//		}
	}

	public MenuPage switchToMenuFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

		return this;
	}

	public ListPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main",5);

		return new ListPage(driver);
	}

	public MenuPage expandIncidentMenu(){
		//		expandSubHeader("IncidentMenu");
		expandMainHeader("IncidentMenu");
		return this;
	}

	public MenuPage verifyExistanceOfIncidentMenus(){

		// Switch to the menu frame
		switchToMenuFrame();

		// you need to change the menu names when the application changes
		// also maintain the order of the links as in screen
		String[] expectedMenus = {	"INCMENU_NEW",
				"INCMENU_ASSIGN",
				"INCMENU_WIP",
				"INCMENU_OPEN",		 
				"INCMENU_UNASSIGN",	
				"INCMENU_RESOLVED",	
				"INCMENU_CLOSED",	
				"INCMENU_ALL",	
				"INCMENU_OVERVIEW",	
		"INCMENU_CIM"};

		String[] expectedMenusDesc = {	"Create New",
				"Assigned to me",
				"Work in Progress",
				"Open",		 
				"Open - Unassigned",	
				"Resolved",	
				"Closed",	
				"All",	
				"Overview",	
		"Critical Incidents Map"};




		// Verify the menus of the incident
		verifyMenuItems("INCMENU_ALLMENU",expectedMenus, expectedMenusDesc);

		return this;

	}

	public IncidentPage clickCreateNew(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_NEW"))
			Reporter.reportStep("The create new link is clicked and New Incident window opened as expected.", "SUCCESS");
		else
			Reporter.reportStep("The create new link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentPage(driver);
	}

	public IncidentsListPage clickWIP(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_WIP")){
			verifyData();
			Reporter.reportStep("The Work In Progress link is clicked and All the respective Incident displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Work In Progress link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage(driver);
	}

	public IncidentsListPage clickOpen(){

		// Switch to the menu frame

		switchToMenuFrame();
		Wait(2000);
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_OPEN")){
			//			verifyData();
			Reporter.reportStep("The Open link is clicked and All the respective Incidents displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Open is not found or clicked.", "FAILURE");

		clearFilter(); 

		return new IncidentsListPage(driver);
	}

	public SPARCPortalPage clickSPARCPortal(){ 	

		switchToDefault();
		// Click create new
		if(clickByXpath("SPARCPORTAL_Xpath"))
			Reporter.reportStep("The SPARC Portal link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The SPARC Portal link is not found or clicked.", "FAILURE");

		return new SPARCPortalPage(driver);
	}

	public MyProfilePage selectMyProfile(){ 

		// Switch to the menu frame
		switchToMenuFrame();

		// Click My Profile
		if(selectMenuFromMainHeader("Self_Service","My_Profile"))
			Reporter.reportStep("The My Profile in Self-Service is clicked", "SUCCESS");
		else
			Reporter.reportStep("The My Profile in Self-Service not found or clicked.", "FAILURE");

		return new MyProfilePage(driver);

	}

	public IncidentsListPage clickAssignedToMe(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click Assigned to me - menu
		if(clickLink("INCMENU_ASSIGN")){
			//			verifyData();
			Reporter.reportStep("The Assigned to me - menu is clicked and All the respective Incidents displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Assigned to me - menu is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage(driver);
	}

	public LoginPage clickLogout(){
		// go out of the frame

		changeUrlForIMFornaNavigation();

		switchToDefault();
		// Step 15: Log out
		if (clickByXpath("Logout_Xpath"))
			Reporter.reportStep("The Log out is successful.","SUCCESS");
		else
			Reporter.reportStep("The Log out link could not be clicked.", "FAILURE");

		changeUrlForIM();
		return new LoginPage();
	}


	public LoginPage clickLogoutwithoutReport(){
		// go out of the frame
		changeUrlForIM();

		switchToDefault();
		// Step 15: Log out
		if (!clickByXpath("Logout_Xpath"))
			Reporter.reportStep("The Log out link could not be clicked.", "FAILURE");

		changeUrlForIM();
		return new LoginPage();
	}

	public MenuPage VerifyIncidentMenuDoNotExists(){
		switchToMenuFrame();

		if(IsElementNotPresentByXpath("INCMENU_Title"))
			Reporter.reportStep("Incident Menu does not exist as expected for this user", "SUCCESS");
		else
			Reporter.reportStep("Incident Menu exists for this user", "WARNING");

		return this;
	}

	public MenuPage loadApplication(){
		driver.get(sUrl);  
		return this;

	}

	public IncidentsListPage clickOpenUnAssigned(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_UNASSIGN"))
			Reporter.reportStep("The Open - Unassigned link is clicked and all the respective incidents displayed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Open - Unassigned is not found or clicked.", "FAILURE");
		clearFilter();

		return new IncidentsListPage(driver);
	}


	public IncidentsListPage clickAll(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click Assigned to me - menu
		if(clickLink("INCMENU_ALL")){
			//			verifyData();
			Reporter.reportStep("The ALL - menu is clicked and all the respective incident displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The ALL - menu is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage(driver);
	}
	public IncidentsListPage clickClosed(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("incident");
		// Click Assigned to me - menu
		if(clickLink("INCMENU_CLOSED")){
			verifyData();
			Reporter.reportStep("The Closed - menu is clicked and All the respective Incidents displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Closed - menu is not found or clicked.", "FAILURE");
		clearFilter();

		return new IncidentsListPage(driver);
	}


	public IncidentsMapPage clickIncidentsMap(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click Assigned to me - menu
		if(clickLink("INCMENU_CIM")){
			Reporter.reportStep("The Critical Incidents Map menu is clicked", "SUCCESS");}
		else
			Reporter.reportStep("The Critical Incidents Map menu is not found or clicked.", "FAILURE");

		return new IncidentsMapPage(driver);
	}

	public IncidentPage searchIncident(String data) {

		// Switch to the out frame
		switchToDefault();

		// enter search    
		if(!enterByXpathAndClick("INCMENU_TopSearch_Xpath", data))
			Reporter.reportStep("Incident: "+data+" could not been entered in search box of the home page", "FAILURE");
		switchToMainFrame();

		if (clickByXpath("INC_SetPriority_Xpath"))
			Reporter.reportStep("The Incident: "+data+" is entered successfully in search box of the home page and The Set priority Icon is clicked sucessfully", "SUCCESS");
		else
			Reporter.reportStep("The Set priority Icon  could not be clicked", "FAILURE");

		return new IncidentPage(driver);
	}
	public IncidentPage searchIncidentFromMenu(String data) {

		// Switch to the out frame
		switchToDefault();

		Wait(5000);
		// enter search    
		if(enterByXpathAndClick("INCMENU_TopSearch_Xpath", data))
			Reporter.reportStep("The Incident: "+data+" is searched and opened as expected.", "SUCCESS");
		else	
			Reporter.reportStep("Incident: "+data+" could not been entered in search box of the home page", "FAILURE");

		switchToMainFrame();
		return new IncidentPage(driver);
	}
	public MenuPage expandCMDBMenu(){
		expandMainHeader("CMDB");
		return this;
	}

	public MenuPage verifyExistanceOfCMDBOptions(){

		// Switch to the menu frame
		switchToMenuFrame();

		// you need to change the menu names when the application changes
		// also maintain the order of the links as in screen
		String[] expectedMenus = {"CMDB_BS_Xpath",
				"CMDB_SA_Xpath",
				"CMDB_SubSystems_Xpath",
				"CMDB_Servers_Xpath", 
				"CMDB_DatabaseInstances_Xpath",
				"CMDB_Network_Xpath",
				"CMDB_Storage_Xpath",
		"CMDB_DataCenter_Xpath"};

		String[] expectedMenusDesc = {"Business Services",
				"Systems/Applications",
				"Sub Systems",
				"Servers",
				"Database Instances",
				"Network",
				"Storage",
				"Data Center"
		};

		// Verify the menus of the CMDB
		verifyFieldsExistByXpath(expectedMenus, expectedMenusDesc);

		return this;
	}
	public CmdbListPage clickSystemApplications(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(clickLink("CMDB_SA"))
			Reporter.reportStep("The System/Applications link is clicked and All the respective CIs are listed as expected", "SUCCESS");
		else
			Reporter.reportStep("The System/Applications link is not found or clicked.", "FAILURE");

		return new CmdbListPage(driver);
	}

	public MenuPage expandCMDBControl(){
		expandSubHeader("CMDB_Control");
		return this;
	}

	public CmdbListPage clickMyCMDBApprovals(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(clickLink("My_CMDB_Approvals"))
			Reporter.reportStep("The My CMDB Approvals link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The My CMDB Approvals link is not found or clicked.", "FAILURE");

		return new CmdbListPage(driver);
	}

	public LoginSparcLCPage clickSparcLCPageLogout(){
		// go out of the frame
		switchToDefault();

		// Step 15: Log out
		if (clickByXpath("Logout_Xpath"))
			Reporter.reportStep("The Log out is successful.","SUCCESS");
		else
			Reporter.reportStep("The Log out link could not be clicked.", "FAILURE");

		Wait(5000);
		changeUrlForIM();
		return new LoginSparcLCPage();
	}
	public MenuPage expandReportsMenu(){
		expandSubHeader("ReportsMenu");
		return this;
	}	
	public ViewRunPage clickViewRun(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("View / Run");
		// Click create new
		if(clickLink("View_Run"))
			Reporter.reportStep("The View / Run is clicked", "SUCCESS");
		else
			Reporter.reportStep("The View / Run  is not found or clicked.", "FAILURE");
		clearFilter();

		return new ViewRunPage(driver);
	}
	public MenuPage expandCMDBDatabaseInstances(){
		expandSubHeader("CMDB_DatabaseInstances");
		verifyDBInstancesMenuItems();
		return this;
	}	

	public MenuPage verifyDBInstancesMenuItems(){
		String[] expectedMenus = {"CMDB_All", "CMDB_MSSQL", "CMDB_Oracle"};
		String[] expectedMenusDesc = {"All", "MSSQL", "Oracle"};
		verifyMenuItems("CMDB_DatabaseInstances_AllMenu", expectedMenus, expectedMenusDesc);
		return this;
	}	

	public CmdbListPage clickOracle(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Database Instances");
		// Click create new
		if(clickLink("CMDB_Oracle"))
			Reporter.reportStep("The Oracle link is clicked and All the respective CIs are listed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Oracle link is not found or clicked.", "FAILURE");

		clearFilter();

		return new CmdbListPage(driver);
	}

	public CmdbListPage clickWinserver(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Servers");
		// Click create new
		if(clickLink("CMDBServer_Windows"))
			Reporter.reportStep("The Windows under Server link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The Windows under Server link is not found or clicked.", "FAILURE");

		clearFilter();

		return new CmdbListPage(driver);
	}

	public CmdbListPage clickStoragePools(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Storage");
		// Click create new
		if(clickLink("StrPools_Pools"))
			Reporter.reportStep("The Pools under Storage link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The Pools under Storage link is not found or clicked.", "FAILURE");

		clearFilter();

		return new CmdbListPage(driver);
	}

	public MenuPage expandServers(){		
		expandSubHeader("Servers");
		return this;
	}
	public IncidentsListPage clickMissingCITasks(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("CMDB Control");
		// Click create new
		if(clickLink("MissingCITasks"))
			Reporter.reportStep("The Missing CI Tasks link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The Missing CI Tasks link is not found or clicked.", "FAILURE");
		clearFilter();

		return new IncidentsListPage(driver);
	}

	public MenuPage enterFilter(String value){

		// Switch to the menu frame
		switchToMenuFrame();

		if(!enterById("filter_Id", value))
			Reporter.reportStep("The "+value+" could not be entered in filter box","FAILURE");
		return this;
	}

	public MenuPage clearFilter(){

		// Switch to the menu frame
		switchToMenuFrame();

		if(!clickById("filter_Clear_Id"))
			Reporter.reportStep("Filter Box could not be cleared","FAILURE");

		return this;
	}

	//Indhu added 20-11
	public MenuPage expandDataCenter(){		
		expandSubHeader("CMDB_DataCenter");
		verifyDataCenterMenuItems();
		return this;
	}

	public MenuPage verifyDataCenterMenuItems(){

		String[] expectedMenus = {"CMDB_UPS", "CMDB_PDU"};
		String[] expectedMenusDesc = {"UPS", "PDU"};
		verifyMenuItems("CMDB_DataCenter_AllMenu", expectedMenus, expectedMenusDesc);
		return this;
	}	


	public CmdbListPage clickPDUs(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(clickLink("CMDB_PDU"))
			Reporter.reportStep("The PDU link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The PDU link is not found or clicked.", "FAILURE");

		return new CmdbListPage(driver);
	}
	public CmdbListPage clickControllers(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(clickLink("Storage_Controllers"))
			Reporter.reportStep("The Controllers link is clicked successfully and A list of Storage Controller CIs appeared as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Controllers link is not found or clicked.", "FAILURE");

		return new CmdbListPage(driver);
	}
	public MenuPage expandStorage(){		
		expandSubHeader("Storage");
		verifyStorageMenuItems();
		return this;
	}

	public MenuPage verifyStorageMenuItems(){
		String[] expectedMenus = {
				"Storage_All","Storage_StorageServers","Storage_FileShares","Storage_Pools","Storage_Volumes","Storage_Controllers","Storage_StorageAreaNetwork"};
		String[] expectedMenusDesc = {"All","Storage Servers","File Shares","Pools","Volumes","Controllers","Storage Area Network"};
		verifyMenuItems("CMDB_Storage_AllMenu", expectedMenus, expectedMenusDesc);
		return this;
	}	
	public MenuPage expandNetwork(){		
		expandSubHeader("Network");
		verifyNetworkMenuItems();
		return this;
	}

	public MenuPage verifyNetworkMenuItems(){
		String[] expectedMenus = {
				"NetworkAll",
				"NetworkFirewall",
				"NetworkRouter",
				"NetworkSwitches",
				"NetworkWLANControllers",
				"NetworkWLANAccessPoints",
				"NetworkLoadBalancers",
		"NetworkCircuits"};
		String[] expectedMenusDesc = {
				"All",
				"Firewall",
				"Router",
				"Switches",
				"WLAN Controllers",
				"WLAN Access Points",
				"Load Balancers",
		"Circuits"};
		verifyMenuItems("CMDB_Network_AllMenu", expectedMenus, expectedMenusDesc);
		return this;
	}

	public CmdbListPage clickNetworkSwitches(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Network");

		// Click create new
		if(clickLink("NetworkSwitches"))
			Reporter.reportStep("The Switches under Network link is clicked and All the respective CIs are listed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Switches under Network link is not found or clicked.", "FAILURE");

		clearFilter();
		return new CmdbListPage(driver);
	}

	public IncidentsListPage clickMyGroupsWork(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click Assigned to me - menu
		if(clickLink("MyGroupsWork"))
			Reporter.reportStep("The My Groups Work - menu is clicked", "SUCCESS");
		else
			Reporter.reportStep("The My Groups Work - menu is not found or clicked.", "FAILURE");

		return new IncidentsListPage(driver);
	}

	public MenuPage expandSPARCMessagesMenu(){
		expandSubHeader("SPARC Messages");
		return this;
	}
	public CmdbListPage clickMySPARCMessages(){

		// Switch to the menu frame
		switchToMenuFrame();
		expandMainHeader("SPARC_Messages");
		// Click create new
		if(clickLink("PDUs_MySPARCMessages"))
			Reporter.reportStep("The My SPARC Messages link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The My SPARC Messages link could not be found or clicked.", "FAILURE");

		return new CmdbListPage(driver);
	}

	// Raj added on 26/11/2015

	public MenuPage expandCMDBControlMenu(){
		expandSubHeader("CMDB Control");
		return this;
	}

	public CmdbListPage clickCriticalAttributes(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Critical Attributes");
		// Click create new
		if(clickLink("CMDBControl_CriticalAttributes"))
			Reporter.reportStep("The Critical Attributes link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The Critical Attributes link is not found or clicked.", "FAILURE");

		clearFilter();
		return new CmdbListPage(driver);
	}

	public CmdbListPage clickUPS(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Data Center");

		// Click create new
		if(clickLink("CMDB_UPS"))
			Reporter.reportStep("The UPSs link is clicked", "SUCCESS");
		else
			Reporter.reportStep("The UPSs link is not found or clicked.", "FAILURE");

		clearFilter();

		return new CmdbListPage(driver);
	}

	//Update

	public ListPage clickSystemApplicationsAndVerifyNewButtonExists(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("CMDB_SA"))    
			Reporter.reportStep("The System/Applications link is not found or clicked.", "FAILURE"); 

		switchToFrame("Frame_Main");

		if(isExistById("New_Button"))
			Reporter.reportStep("The System/Applications link is clicked successfully and New button is available at the top of the list as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found", "FAILURE");

		return new ListPage(driver);
	}

	public SystemApplicationsPage clickSystemApplicationsandClickNewButton(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("CMDB_SA"))
			Reporter.reportStep("The System/Applications link is not found or clicked.", "FAILURE");

		switchToFrame("Frame_Main");

		if(clickById("New_Button"))
			Reporter.reportStep("The System/Applications, New button is clicked sucessfully and New CI opened as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new SystemApplicationsPage(driver);
	}
	public CmdbListPage clickStoragePoolsPageAndVerifyNewButtonExists(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("StrPools_Pools"))
			Reporter.reportStep("The Pools under Storage link is not found or clicked.", "FAILURE");

		switchToFrame("Frame_Main");

		if(isExistById("New_Button"))
			Reporter.reportStep("The Pools under Storage link is clicked successfully and New button is available at the top of the list as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found", "FAILURE");

		return new CmdbListPage(driver);
	}
	public ServerWindowsPage clickWinServerandClickNewButton(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("CMDBServer_Windows"))
			Reporter.reportStep("The Windows under Server link is not found or clicked.", "FAILURE");

		switchToFrame("Frame_Main");

		if(clickById("New_Button"))
			Reporter.reportStep("The Windows under Server link and New button is clicked sucessfully, The New CI opened as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new ServerWindowsPage(driver);
	}
	public OracleInstancesPage clickOracleandClickNewButton(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("CMDB_Oracle"))
			Reporter.reportStep("The Oracle link is not found or clicked.", "FAILURE");

		switchToFrame("Frame_Main");

		if(clickById("New_Button"))
			Reporter.reportStep("The Oracle link under DataBase and New button is clicked sucessfully, The New CI opened as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new OracleInstancesPage(driver);
	}

	public NetworkSwitchesPage clickNetworkSwitchesandClickNewButton(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("NetworkSwitches"))
			Reporter.reportStep("The Switches under Network link is not found or clicked.", "FAILURE");

		switchToFrame("Frame_Main");

		if(clickById("New_Button"))
			Reporter.reportStep("The Switches under Network link and New button is clicked sucessfully, The New CI opened as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new NetworkSwitchesPage(driver);

	}
	public UPSPage clickUPSPageandClickNewButton(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("CMDB_UPS"))
			Reporter.reportStep("The UPS link is not found or clicked.", "FAILURE");
		switchToFrame("Frame_Main");

		if(clickById("New_Button"))
			Reporter.reportStep("The UPS link and New button is clicked sucessfully, The New CI opened as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new UPSPage(driver);
	}
	public CmdbListPage clickPDUAndVerifyNewButtonExists(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("CMDB_PDU"))				
			Reporter.reportStep("The PDU link is not found or clicked.", "FAILURE");	

		switchToMainFrame();

		if(isExistById("New_Button"))
			Reporter.reportStep("The PDU link is clicked successfully and New button is available at the top of the list as expected", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found", "FAILURE");

		return new CmdbListPage(driver);
	}
	//22-12-2015 indhu update

	public ListPage clickOpenRecords(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(clickLink("Open_Records"))
			Reporter.reportStep("The Open Records link is clicked and All the respective Tickets are listed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Open Records link is not found or clicked.", "FAILURE");

		return new ListPage(driver);
	}

	public CmdbListPage clickLinux(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Server");
		// Click create new
		if(clickLink("CMDBServer_Linux"))
			Reporter.reportStep("The Linux link is clicked and All the respective CIs are listed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Linux link is not found or clicked.", "FAILURE");

		clearFilter();

		return new CmdbListPage(driver);
	}

	public CmdbListPage clickServerAll(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Server");
		// Click Assigned to me - menu
		if(clickLink("CMDB_All"))
			Reporter.reportStep("The ALL link is clicked and All the respective CIs are listed as expected", "SUCCESS");
		else
			Reporter.reportStep("The ALL - menu is not found or clicked.", "FAILURE");
		clearFilter();

		return new CmdbListPage(driver);
	}

	//Updated by Sneha on 22-12-2015
	public FSSTaskPage clickFSSTaskandNewButton()
	{
		switchToMenuFrame();

		if (!clickLink("FSS_Tasks"))
			Reporter.reportStep("The FSS Tasks - menu  is not found or clicked.","FAILURE");

		switchToMainFrame();

		if(clickById("FSS_FSSTasks_NewButton_Id"))
			Reporter.reportStep("The FSS Tasks link is clicked and New FSS Task Page opened as expected.", "SUCCESS");
		else
			Reporter.reportStep("User not able to click New Button", "FAILURE");

		return new FSSTaskPage(driver);
	}
	// indhu updated on 28-12-2015

	public MyItemsPage clickMyItems()
	{
		switchToMenuFrame();

		if (clickLink("My_Items"))
			Reporter.reportStep("The My Items - menu selected successfully.","SUCCESS");
		else
			Reporter.reportStep("The My Items - menu  is not found or clicked.","FAILURE");

		return new MyItemsPage(driver);
	}

	public ListPage clickMyGroupWorksInFSS(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("FSS Task");

		// Click create new
		if(clickLink("MyGroupsWork"))
			Reporter.reportStep("The My Groups Work link is clicked and All the respective Tickets are listed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The My Groups Work link is not found or clicked.", "FAILURE");

		return new ListPage(driver);
	}

	// Updated by Raj on Jan 5

	public MenuPage isIncidentMenuExists(String menu){

		if(IsElementNotPresentByXpath(menu))
			Reporter.reportStep("The Incident Menu does not appear as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Incident Menu does appear", "FAILURE");

		return this;
	}

	public MenuPage clickReportA(){

		if (!isExistByXpath("ReportA")) 
			Reporter.reportStep("Report A could not be found or Clicked", "FAILURE");

		return this;
	}
	//indhu updated on 12-01-2015
	public CmdbListPage clickMSSQL(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Database Instances");
		// Click create new
		if(clickLink("CMDB_MSSQL"))
			Reporter.reportStep("The MSSQL link is clicked and All the respective CIs are listed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The MSSQL link is not found or clicked.", "FAILURE");

		clearFilter();

		return new CmdbListPage(driver);
	}
	public MenuPage verifyData(){
		switchToMainFrame();
		if(isExistByXpath("NoRecords_xpath")){
			//if(isExistByXpath("//*[text()='No records to display']", false)){		
			status = "INSUFFICIENT DATA";
			Reporter.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		return this;
	}

	public IncidentPage clickCreateNewforFailure(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(!clickLink("INCMENU_NEW"))
			Reporter.reportStep("The create new link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentPage(driver);
	}
	public SPARCPortalPage clickSPARCPortalForFailure(){ 	

		switchToDefault();
		// Click create new
		if(!clickByXpath("SPARCPORTAL_Xpath"))
			Reporter.reportStep("The SPARC Portal link is not found or clicked.", "FAILURE");
		return new SPARCPortalPage(driver);
	}
	public IncidentsListPage clickAssignedToMeWithoutReport(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click Assigned to me - menu
		if(clickLink("INCMENU_ASSIGN")){
			verifyData();}
		else
			Reporter.reportStep("The Assigned to me - menu is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage(driver);
	}	
	public IncidentsListPage clickWIPWithoutReport(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_WIP")){
			verifyData();}

		else
			Reporter.reportStep("The Work In Progress link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage(driver);
	}
	public IncidentsListPage clickOpenWithoutReport(){

		// Switch to the menu frame

		switchToMenuFrame();
		Wait(2000);
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_OPEN")){
			verifyData();}
		else
			Reporter.reportStep("The Open is not found or clicked.", "FAILURE");
		clearFilter(); 
		return new IncidentsListPage(driver);
	}
	public IncidentsListPage clickOpenUnAssignedWithoutReport(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_UNASSIGN"))
			verifyData();
		else
			Reporter.reportStep("The Open - Unassigned is not found or clicked.", "FAILURE");
		clearFilter();

		return new IncidentsListPage(driver);
	}
	public IncidentsListPage clickClosedWithoutReport(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("incident");
		// Click Assigned to me - menu
		if(clickLink("INCMENU_CLOSED")){
			clearFilter();
			verifyData();}
		else
			Reporter.reportStep("The Closed - menu is not found or clicked.", "FAILURE");


		return new IncidentsListPage(driver);
	}
	public IncidentsListPage clickAllWithoutReport(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click Assigned to me - menu
		if(clickLink("INCMENU_ALL"))
		{
			//			verifyData();
		}

		else
			Reporter.reportStep("The ALL - menu is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage(driver);
	}
	public MenuPage verifyOverviewDashBoard(){

		// Switch to the menu frame
		switchToMenuFrame();

		if (!selectMenuFromMainHeader("IncidentMenu", "INCMENU_OVERVIEW"))
			//			Reporter.reportStep("The Overview  menu selected successfully","SUCCESS");
			//		else
			Reporter.reportStep("The Alert Console  menu could not be selected","FAILURE");

		switchToMainFrame();

		if(IsElementPresentById("Incident_Overview_pagetitle_id"))
			Reporter.reportStep("The Overview  menu is selected and the Incident Overview dashboard displayed as expected","SUCCESS");
		else
			Reporter.reportStep("The Incident Overview dashboard could not be displayed","FAILURE");
		return this;
	}

	public IncidentsMapPage clickIncidentsMapandVerifyMapExists(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click Assigned to me - menu
		if(!clickLink("INCMENU_CIM"))		
			//			Reporter.reportStep("The Critical Incidents Map menu is clicked", "SUCCESS");
			//			else		

			Reporter.reportStep("The Critical Incidents Map menu is not found or clicked.", "FAILURE");

		switchToMainFrame();

		if (isExistByXpath("INC_Map_Xpath")) {
			Wait(2000);
			Reporter.reportStep("The Critical Incidents Map menu is clicked and Critical Incidents Map is displayed", "SUCCESS");}
		else
			Reporter.reportStep("Critical Incidents Map could not be displayed", "FAILURE");

		return new IncidentsMapPage(driver);
	}

	public SPARCPortalPage clickMyProfileofSPARCPortal(){ 	

		switchToDefault();
		// Click create new
		if(!clickByXpath("SPARCPORTAL_Xpath"))
			Reporter.reportStep("The SPARC Portal link is not found or clicked.", "FAILURE");

		//		switchToMainFrame();

		if(clickByXpath("SPARCPORTAL_MyProfile_Xpath"))
			Reporter.reportStep("The My Profile of SPARC Portal is clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The My Profile of SPARC Portal could not be clicked", "FAILURE");


		return new SPARCPortalPage(driver);
	}

	public IncidentPage clickOpenandClickFirstIncident(){

		// Switch to the menu frame

		switchToMenuFrame();
		Wait(2000);
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_OPEN")){
			//			verifyData();
			//			Reporter.reportStep("The Open link is clicked and All the respective Incidents displayed as expected.", "SUCCESS");
		}
		else
			Reporter.reportStep("The Open is not found or clicked.", "FAILURE");
		clearFilter(); 

		switchToMainFrame();

		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The Open link is clicked and All the respective Incidents displayed and the First Incident is clicked Successfully", "SUCCESS");
		else
			Reporter.reportStep("The First Incident could not be clicked", "FAILURE");
		return new IncidentPage(driver);
	}
	public AlertsListPage clickMyAlert(){

		switchToMenuFrame();
		if(clickLink("My_Alerts"))
			Reporter.reportStep("The My Alerts under OpsConsole - menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("The My Alerts under OpsConsole - menu could not be selected","FAILURE");

		return new AlertsListPage(driver);
	}

	public AlertPage selectRunBookandUpdate(String alertId){

		if(!selectByVisibleTextByXpath("ALERT_ReationType_Xpath", "Run Runbook"))
			Reporter.reportStep("Create Incident for Alert "+ alertId +" could not be selected","FAILURE");

		if(!clickById("CIS_UpdateButton_Id"))
			Reporter.reportStep("Update for Alert "+ alertId +" could not be clicked","FAILURE");

		Wait(5000);

		return new AlertPage(driver);
	}	

	public AlertsListPage clickOpsAlertConsole(){

		enterFilter("Ops Consoles");

		if(selectMenu("Ops_Consoles", "Alert_Console")){
			Reporter.reportStep("The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			//			verifyData();
		}
		else
			Reporter.reportStep("The Alert Console under OpsConsole - menu could not be selected","FAILURE");

		clearFilter();

		return new AlertsListPage(driver);
	}

	public AlertsListPage selectScenarios(){

		if(!enterById("filter_Id", "Ops Director Testing"))
			Reporter.reportStep("Alert Console could not be entered in filter box","FAILURE");

		if(selectMenu("Ops_Director_Testing", "Scenarios"))
			Reporter.reportStep("The Scenarios under Ops Director Testing - menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("The Scenarios under Ops Director Testing - menu could not be selected","FAILURE");

		if(!clickById("filter_Clear_Id"))
			Reporter.reportStep("Filter Box could not be cleared","FAILURE");
		return new AlertsListPage(driver);
	}

	public AlertsListPage selectData(){
		//Step 4: Navigate to Scenarios  under Ops Director Testing to select Scenarios

		if(!enterById("filter_Id", "Alerts"))
			Reporter.reportStep("The Alerts could not be entered in filter box","FAILURE"); 

		if(selectMenu("Data", "Alerts"))
			Reporter.reportStep("The Alerts under Data - menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("The Alert under Data - menu could not be selected","FAILURE");

		if(!clickById("filter_Clear_Id"))
			Reporter.reportStep("The Filter Box could not be cleared","FAILURE");
		return new AlertsListPage(driver);
	}

	public AlertPropertiesPage clickAdminApplicationProperties(){

		if(selectMenu("Administration", "Application_Properties"))
			Reporter.reportStep("The Application Properties under OpsConsole - menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("The Application Properties under OpsConsole - menu could not be selected","FAILURE");

		return new AlertPropertiesPage(driver);
	}

	public AlertsListPage clickMyAlertConsole(){

		if(selectMenu("Ops_Consoles", "My_Alert_Console"))
			Reporter.reportStep("My Alerts Console under OpsConsole - menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("My Alerts Console under OpsConsole - menu could not be selected","FAILURE");


		return new AlertsListPage(driver);
	}

	public AlertsListPage clickUserAlertConsole(){

		enterFilter("User Consoles");
		if(selectMenu("User_Consoles", "Alert_Console")){
			Reporter.reportStep("The Alert Console link under User Consoles is clicked successfully","SUCCESS");
		}
		else
			Reporter.reportStep("The Alert Console link under User Console is not clicked or not found.","FAILURE");

		clearFilter();
		return new AlertsListPage(driver);
	}

	public AlertsEnrichersListPage clickAdminAlertEnrichers(){

		if(selectMenu("Administration", "Alert_Enrichers"))
			Reporter.reportStep("The Alert Enrichers link under Administration is clicked successfully","SUCCESS");
		else
			Reporter.reportStep("The Alert Enrichers link under Administration is not clicked or not found.","FAILURE");

		return new AlertsEnrichersListPage(driver);
	}

	public MenuPage playScenarios(String sceData){
		if(playScenario(sceData))
			Reporter.reportStep(""+sceData+" under Scenario is clicked successful.","SUCCESS");
		else
			Reporter.reportStep(""+sceData+" under Scenario could not be clicked.","FAILURE");

		return this;
	}

	public MenuPage verifyOpsConsolesMenu() {

		enterFilter("Ops Consoles");
		String[] menuItems={/*"Service_Dashboard",*/
				"My_Alert_Console",
				"Alert_Console",
				"Alert_Dashboard",
				/*"Alerts_By_State"*/
		}; //,
		//"Service_Whizzy"};

		String[] menuItemsDesc={/*"Service Dashboard",*/
				"My Alerts",
				"Alert Console",
				"Alert Dashboard",
				/*"Alerts By State"*/
		};

		verifyMenuItems("OPS_Console_All_Menu",menuItems, menuItemsDesc);

		clearFilter();
		return this;

	}
	public IncidentsListPage clickIncidents(){

		switchToMenuFrame();

		enterFilter("Incidents");
		if(clickLink("Incidents_Link"))
			Reporter.reportStep("The Incidents link is clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("The Incidents link is not clicked or not found.","FAILURE");

		clearFilter();
		return new IncidentsListPage(driver);
	}
	public AlertsListPage clickAlertsByState(){

		Wait(1000);
		switchToMenuFrame();
		enterFilter("User Consoles");
		if(clickLink("Alerts_By_State"))
			Reporter.reportStep("The Alerts By State link is clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("The Alerts By State link is not clicked or not found.","FAILURE");

		clearFilter();
		return new AlertsListPage(driver);
	}

	public AlertsListPage clickAlertsByState1(){

		switchToMenuFrame();
		enterFilter("User Consoles");
		if(!clickLink("Alerts_By_State"))
			Reporter.reportStep("The Alerts By State link is not clicked or not found.","FAILURE");
		clearFilter();
		return new AlertsListPage(driver);
	}
	public AlertsSuppressorsListPage clickAlertSuppressors(){

		switchToMenuFrame();

		if(clickLink("Alert_Suppressors"))
			Reporter.reportStep("The Alert Suppressors link is clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("The Alert Suppressors link is not clicked or not found.","FAILURE");

		return new AlertsSuppressorsListPage(driver);
	}
	public AlertsProfilesListPage clickAlertProfiles(){

		switchToMenuFrame();

		if(selectMenu("Configurations", "Alert_Profiles"))
			Reporter.reportStep("The Alert Profiles link is clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("The Alert Profiles link is not clicked or not found.","FAILURE");

		return new AlertsProfilesListPage(driver);
	}
	public AlertDashBoardPage clickOPSAlertDashBoard(){

		switchToFrame("Frame_Nav");

		if(!enterById("filter_Id", "Ops Consoles"))
			Reporter.reportStep("Ops Consoles could not be entered in filter box.","FAILURE");

		if(clickLink("Alert_Dashboard"))
			Reporter.reportStep("The Alert Dashboard  link clicked successfully.","SUCCESS");
		else
			Reporter.reportStep("The Alert Dashboard link is not clicked or not found.","FAILURE");

		if(!clickById("filter_Clear_Id"))
			Reporter.reportStep("Filter Box could not be cleared.","FAILURE");

		return new AlertDashBoardPage(driver);
	}

	public CIScopePage clickCIScope(){

		enterFilter("CI Scope");

		if(clickLink("CIS_Registration"))
			Reporter.reportStep("The CI Scope under Registration - menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("The CI Scope under Registration - menu is not selected or not found, hence failure.","FAILURE");

		clearFilter();

		return new CIScopePage(driver);
	}

	public CIScopesListPage clickCIScopes(){

		enterFilter("OpsDirector");

		if(selectMenu("Configurations", "CIS_Scopes"))
			Reporter.reportStep("The CI Scopes under Configurations - menu selected successfully.","SUCCESS");
		else
			Reporter.reportStep("The CI Scopes under Configurations - menu is not selected or not found.","FAILURE");

		clearFilter();
		return new CIScopesListPage(driver);
	}

	public String  getUserName() {
		switchToDefault();
		String username=getTextByXpath("Name_Xpath");
		if(username.equals(""))
			Reporter.reportStep("The User Name is blank.","FAILURE");

		return username;
	} 		
	public AlertsListPage clickAlerts(){

		if(selectMenu("Data", "Alerts"))
			Reporter.reportStep("The Alerts under Data - menu selected successfully.","SUCCESS");
		else
			Reporter.reportStep("The Alerts under Data - menu is not selected or not found.","FAILURE");
		return new AlertsListPage(driver);
	}
	public LoginPage clickLogoutWithAlertAccept(){
		// go out of the frame
		switchToDefault();

		// Step 15: Log out
		if (clickByXpath("Logout_Xpath")){
			alertAccept();
			Reporter.reportStep("The Log out is successful.","SUCCESS");}
		else
			Reporter.reportStep("The Log out link could not be clicked.", "FAILURE");

		return new LoginPage();
	}
	public IncidentPage clickCreateNewwithAlertAccept(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_NEW")){
			alertAccept();
			Reporter.reportStep("The create new link is clicked and New Incident window opened as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The create new link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentPage(driver);
	}
	//	
	public AlertProfilePage clickAlertProfileRegistration(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Registration");
		// Click create new
		if (clickLink("Prof_Reg"))
			Reporter.reportStep("The Profile Registration menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("The Profile Registration menu could not be selected or not found","FAILURE");

		clearFilter();

		return new AlertProfilePage(driver);
	}
	public CmdbListPage clickDataBaseAll(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Database Instance");
		// Click Assigned to me - menu
		if(clickLink("CMDB_All"))
			Reporter.reportStep("The ALL link is clicked and All the respective CIs are listed as expected", "SUCCESS");
		else
			Reporter.reportStep("The ALL - menu is not found or clicked.", "FAILURE");
		clearFilter();

		return new CmdbListPage(driver);
	}	
	public CmdbListPage clickNetWorksAll(){

		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("NetWork");
		// Click Assigned to me - menu
		if(clickLink("CMDB_All"))
			Reporter.reportStep("The ALL link is clicked and All the respective CIs are listed as expected", "SUCCESS");
		else
			Reporter.reportStep("The ALL - menu is not found or clicked.", "FAILURE");
		clearFilter();

		return new CmdbListPage(driver);
	}	
	public AlertsListPage playScenarios1(String sceData){
		if(playScenario(sceData))
			Reporter.reportStep(""+sceData+" under Scenario is clicked successful.","SUCCESS");
		else
			Reporter.reportStep(""+sceData+" under Scenario could not be clicked.","FAILURE");

		return new AlertsListPage(driver);
	}
	public CorrelationProfilesPage clickCorrelatedProfile() {

		enterFilter("Registration");

		if (selectMenu("Ops_Director","Registration_Menu", "Correlated_Profile"))
			Reporter.reportStep("The Correlated Profile - menu selected successfully","SUCCESS");
		else
			Reporter.reportStep("The Correlated Profile - menu could not be selected","FAILURE");

		clearFilter();

		return new CorrelationProfilesPage(driver);
	}
	public AlertsListPage clickMyAlertConsoleWithoutReport(){

		enterFilter("Ops Consoles");
		if(!selectMenu("Ops_Consoles", "My_Alert_Console")){
			Reporter.reportStep("My Alerts Console under OpsConsole - menu could not be selected","FAILURE");
			verifyData();
		}

		clearFilter();	
		return new AlertsListPage(driver);
	}
	public AlertsListPage clickOpsAlertConsoleWithoutReport(){

		enterFilter("Ops Consoles");

		if(selectMenu("Ops_Consoles", "Alert_Console")){
			Reporter.reportStep("The Alert Console under OpsConsole - menu selected successfully","SUCCESS");
			verifyData();
		}
		clearFilter();

		return new AlertsListPage(driver);
	}
	public AlertProfilePage clickAlertProfileRegistrationWithoutReport(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("opsDirector");
		// Click create new
		if (!selectMenu("Ops_Director", "Registration", "Prof_Reg"))
			Reporter.reportStep("The Profile Registration menu could not be selected","FAILURE");

		clearFilter();

		return new AlertProfilePage(driver);
	}
	public AlertsListPage clickMyAlertWithouReport(){

		switchToMenuFrame();
		enterFilter("My Alerts");
		if(!clickLink("My_Alerts"))
			Reporter.reportStep("The My Alerts under OpsConsole - menu could not be selected","FAILURE");

		return new AlertsListPage(driver);
	}
	public CmdbListPage clickSystemApplicationsWithoutReport(){

		// Switch to the menu frame
		switchToMenuFrame();

		// Click create new
		if(!clickLink("CMDB_SA"))
			Reporter.reportStep("The System/Applications link is not found or clicked.", "FAILURE");

		return new CmdbListPage(driver);
	}
	public MenuPage clickAlertProfileRegistrationNegative(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Registration");
		// Click create new
		if (!clickLink("Prof_Reg"))
			Reporter.reportStep("The Profile Registration menu not found as expected.","SUCCESS");
		else
			Reporter.reportStep("The Profile Registration menu selected, hence failure","FAILURE");

		clearFilter();

		return this;
	}

	public TemplatesListPage clickTemplatesMenu(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Templates");
		// Click create new
		if (clickLink("Templates"))
			Reporter.reportStep("The Templates selected menu is selected successfully.","SUCCESS");
		else
			Reporter.reportStep("The Templates is not selected, hence failure","FAILURE");

		clearFilter();

		return new TemplatesListPage(driver);
	}
	public RunbookListPage clickUnassignedMenu(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Runbook");
		// Click create new
		if (clickLink("Unassigned"))
			Reporter.reportStep("The Unassigned menu under Runbook is selected successfully.","SUCCESS");
		else
			Reporter.reportStep("The Unassigned menu under Runbook  is not selected, hence failure","FAILURE");

		clearFilter();

		return new RunbookListPage(driver);
	}

	public RunbookListPage clickAssignedtomeMenu(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Runbook");
		// Click create new
		if (clickLink("Assigned_to_me"))
			Reporter.reportStep("The Assigned to me menu under Runbook is selected successfully.","SUCCESS");
		else
			Reporter.reportStep("The Assigned to me menu under Runbook  is not selected, hence failure","FAILURE");

		clearFilter();

		return new RunbookListPage(driver);
	}
	public IncidentsListPage clickResolved(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_RESOLVED")){
			Reporter.reportStep("The Resolved link is clicked and All the respective Incidents displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Work In Progress link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage(driver);
	}
	public ListPage clickApplication(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Configuration");
		// Click create new
		if(clickLink("Demo_Applications_Link")){
			verifyData();
			Reporter.reportStep("The Application link is clicked successfully.", "SUCCESS");}
		else
			Reporter.reportStep("The Application link is not found or clicked, hence failure.", "FAILURE");
		clearFilter();

		return new ListPage(driver);
	}

	public LoginPage clickLogoutdemo(){
		// go out of the frame
		switchToDefault();
		if (!clickByXpath("Demo_Logout_xpath"))
			Reporter.reportStep("The Log out link is not clicked, hence failure.","FAILURE");


		// Step 15: Log out
		if (clickLink("Demo_LogoutLink_xpath"))
			Reporter.reportStep("The Log out is successful.","SUCCESS");
		else
			Reporter.reportStep("The Log out link could not be clicked.", "FAILURE");

		return new LoginPage();
	}
	public ListPage clickEvents() {
		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Event");
		// Click create new
		if(clickLink("Events")){
			verifyData();
			Reporter.reportStep("The Events link is clicked successfully.", "SUCCESS");}
		else
			Reporter.reportStep("The Events link is not found or clicked, hence failure.", "FAILURE");

		clearFilter();

		return new ListPage(driver);
	}

	//14-06-2016
	public ListPage clickIncomingAlerts() {
		// Switch to the menu frame
		switchToMenuFrame();

		enterFilter("Incoming Alerts");
		// Click create new
		if(clickLink("Alerts_IncomingAlerts_Link")){
			Reporter.reportStep("The Incoming Alerts menu is clicked successfully.", "SUCCESS");}
		else
			Reporter.reportStep("The Incoming Alerts menu is not found or clicked, hence failure.", "FAILURE");

		clearFilter();

		return new ListPage(driver);
	}


	public IncidentPage verifyHasBreached(String incNumber, String slA, String regUser1, String regPwd1) throws ParseException {

		clickLogout();
		new LoginPage()
		.loginAs("Punch_Local_Admin", "Punchtest3132016");

		clickAllWithoutReport()
		.searchAndOpenIncidentWithoutReport(incNumber)
		.runSLACalculations(slA);
		clickLogout();		
		new LoginPage()
		.loginAs(regUser1, regPwd1);
		clickAllWithoutReport()
		.searchAndOpenIncidentWithoutReport(incNumber)	
		.verifyHasBreached(slA);
		return new IncidentPage(driver);		

	}

	public IncidentPage addNumberofDays(String incNumber, String slA, String regUser1, String regPwd1, int day) throws ParseException {
		System.out.println(slA);
		clickLogoutwithoutReport();
		new LoginPage()
		.loginAswithoutreport("Punch_Local_Admin", "Punchtest3132016");

		clickAllWithoutReport()
		.searchAndOpenIncidentWithoutReport(incNumber)
		.runSLACalculationsforNoOfDays(slA, day);
		clickLogoutwithoutReport();		
		new LoginPage()
		.loginAswithoutreport(regUser1, regPwd1);
		clickAllWithoutReport()
		.searchAndOpenIncidentWithoutReport(incNumber);	

		return new IncidentPage(driver);		

	}
	
	public ListPage clickScheduleDefinitions(){

		switchToMenuFrame();
		enterFilter("Schedules");
		
		if(clickLink("Schedules_ScheduleDefinitions_Link"))
			Reporter.reportStep("The Schedule Definitions link clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The Schedule Definitions link is not found or clicked.", "FAILURE");

		clearFilter();
		
		return new ListPage(driver);
	}
	public ListPage clickMyTasks(){

		switchToMenuFrame();
		enterFilter("My Tasks");
		
		if(clickLink("DataCertification_MyTasks_Link"))
			Reporter.reportStep("The My Tasks link clicked successfully.", "SUCCESS");
		else
			Reporter.reportStep("The My Tasks link is not found or clicked.", "FAILURE");

		clearFilter();
		
		return new ListPage(driver);
	}
}
