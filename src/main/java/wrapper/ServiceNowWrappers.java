package wrapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TimeZone;

//import org.apache.commons.lang3.ArrayUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.IncidentPage;
import pages.ListPage;
import pages.MenuPage;

import pages.SystemApplicationsPage;
import utils.Reporter;




public class ServiceNowWrappers extends GenericWrappers {

	public static RemoteWebDriver driver;
	public static String testcaseName;
	public static String testDescription; 	// Babu added for getting first PDF page - test description
	private String CMDBTaskNumber;
	private String Firstlinkname;


	public ServiceNowWrappers() {

	}

	public ServiceNowWrappers(String testcaseName) {
		super();
		ServiceNowWrappers.testcaseName = testcaseName;
	}

	/**
	 * login to the site using the data provided through arguments
	 * through entering user name, password and by submit click. The method
	 * waits until the loading of page.
	 * @author Rajkumar
	 * @param  user
	 *            The user name of the login (any type of login - admin,
	 *            reviewer)
	 * @param  pwd
	 *            The password as text (not in encrypted format)
	 * @return 
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean login(String user, String pwd)  {
		boolean bReturn = false;
		{
			changeUrlForIM();

			switchToFrame("Frame_Main");

			enterById("UserName_Id", user);
			enterById("Password_Id", pwd);
			clickById("LoginButton_Id");

			// If logout exists, send true
			// If logout exists, send true
			Wait(3000);

			goOutOfFrame();

			if(!isExistByXpath("Logout_Xpath"))
				Reporter.reportStep("The login with username:"+ user + " is successful but this is not the expected page", "FAILURE");

			if(!isExistById("Frame_Nav")){   
				clickByXpath("ToggleNav_Xpath"); // Click on the toggle navigator
			}
			bReturn = true;
		}
		return bReturn;
	}
	public boolean login_DEMO(String user, String pwd)  {
		boolean bReturn = false;
		{
			// switch to the main frame
			switchToFrame("Frame_Main");

			// enter username, password and click login
			enterById("UserName_Id", user);
			enterById("Password_Id", pwd);
			clickById("LoginButton_Id");

			// If logout exists, send true
			if(!VerifyByLink("Admin Demonstrator",true)){
				if(!isExistById("Frame_Nav")) /* Rajkumar -- Oct 12 2015 -- Code added to check if the frame is visible; if not, click on toggle */
					clickByXpath("ToggleNav_Xpath"); // Click on the toggle navigator
				bReturn = true;

			}

			return bReturn;

		}
	}


	/**
	 * select the menu item from the menu header
	 * @author Rajkumar
	 * @param  menuHeader - the name of the menu header
	 * @param  menu - the menu item 
	 *            
	 */
	public boolean selectMenu(String menuHeader, String menuSubHeader, String menu) {
		boolean bReturn = false;

		if(expandMainHeader(menuHeader))
			if(expandSubHeader(menuSubHeader))
			{
				clickLink(menu);
				bReturn = true;
			}	
		return bReturn;
	}

	public boolean selectMenu(String menuSubHeader, String menu) {
		boolean bReturn = false;

		if(expandSubHeader(menuSubHeader))
			clickLink(menu);
		bReturn = true;
		return bReturn;

	}

	public boolean selectMenuFromMainHeader(String menuHeader, String menu) {
		boolean bReturn = false;

		if(expandMainHeader(menuHeader))
			clickLink(menu);
		bReturn = true;
		return bReturn;

	}

	public boolean expandMainHeader(String menuMainHeader){
		boolean bReturn = false;
		try{

			// Get the driver handle
			driver = getDriver();

			// Go inside the frame
			switchToFrame("Frame_Nav");

			// find the menu and check if it is open
			List<WebElement> menuHeaders = getAllElementsByXpath("MenuMainHeader_Xpath");
			// Check the menu name and see if it is expanded
			for (WebElement menuHeaderEle : menuHeaders) {
				if(menuHeaderEle.getText().equals(objRep.getProperty(menuMainHeader))){
					// Get the attribute name of the id
					if(driver.findElementById("app_"+menuHeaderEle.getAttribute("for")).getAttribute("class").equals("nav-app submenu"))

						menuHeaderEle.click();
					bReturn = true;
					break;

				}
			}
			///  bReturn = true;



		}catch(Exception e){

		}

		return bReturn;

	}

	public boolean expandSubHeader(String menuSubHeader){
		boolean bReturn = false;
		try{

			// Get the driver handle
			driver = getDriver();

			// Go inside the frame
			switchToFrame("Frame_Nav");

			// find the menu and check if it is open
			List<WebElement> menuHeaders = getAllElementsByXpath("MenuSubHeader_Xpath");
			// Check the menu name and see if it is expanded
			for (WebElement menuHeaderEle : menuHeaders) {
				System.out.println(menuHeaderEle.getText());
				if(menuHeaderEle.getText().equals(objRep.getProperty(menuSubHeader))){
					// Get the attribute name of the id
					if(driver.findElementById("app_"+menuHeaderEle.getAttribute("data-id")).getAttribute("data-open").equals("false"))
						menuHeaderEle.click();

					break;
				}
			}

			bReturn = true;

		}catch(Exception e){

		}

		return bReturn;
	}

	/**
	 * select the menu item from the Ops Director
	 * @author Rajkumar
	 * @param  menuHeader - the name of the menu header
	 * @param  pwd
	 *            The password as text (not in encrypted format)
	 */
	public boolean chooseFromSearch(String searchIcon, String data) {
		boolean bReturn = false;
		{


			// Click search icon
			clickByXpath(searchIcon);

			// Wait for a while
			Wait(5000);

			// Move the control to the new window
			switchToSecondWindow();

			// Search for the data
			enterByXpath("//*[@placeholder='Search']/../input[@class='form-control']", data);

			// Click on the link
			clickLink(data);
			bReturn = true;
		}

		return bReturn;
	}

	/**
	 * verify the mandatory fields
	 */
	public boolean verifyMandatoryFields(String[] fields, String[] desc){
		Boolean bReturn = true;

		List<String> trueValue= new ArrayList<String>();
		List<String> falseValue= new ArrayList<String>();


		for (int i=0; i < fields.length; i++) {
			if(!verifyAttributeTextByXpath(fields[i], "mandatory", "true")){
				bReturn = false;
				falseValue.add(desc[i]);
				//				Reporter.reportStep("The field :"+desc[i]+" is not displayed as mandatory. Hence Failed.","WARNING");
			}
			else
				trueValue.add(desc[i]);	
		}

		if(bReturn)
			Reporter.reportStep("All the fields:"+convertStringArrayToString(desc).replaceAll(",", ", ")+" are mandatory as expected","SUCCESS");
		else
		{
			Reporter.reportStep("The following fields: "+falseValue+" are Non Mandatory, please check.","WARNING");
			Reporter.reportStep("The following fields: "+trueValue+" are Mandatory as expected","SUCCESS");
		}

		return bReturn;
	}
	/**
	 * verify the non mandatory fields
	 */
	public boolean verifyNonMandatoryFields(String[] fields, String[] desc){
		Boolean bReturn = true;

		List<String> trueValue= new ArrayList<String>();
		List<String> falseValue= new ArrayList<String>();

		for (int i=0; i < fields.length; i++) {
			if(verifyAttributeTextByXpath(fields[i], "mandatory", "true")){
				bReturn = false;
				falseValue.add(desc[i]);
				//				Reporter.reportStep("The field :"+desc[i]+" is not displayed as non - mandatory. Hence Failed.","FAILURE");
			}
			else
				trueValue.add(desc[i]);
		}

		if(bReturn){
			Reporter.reportStep("All the fields: "+convertStringArrayToString(desc).replaceAll(",", ", ")+" are non mandatory as expected","SUCCESS");
		}
		else
		{
			Reporter.reportStep("The following fields: "+falseValue+" are Mandatory, please check.","WARNING");
			Reporter.reportStep("The following fields: "+trueValue+" are Non Mandatory as expected","SUCCESS");

		}

		return bReturn;
	}

	/**
	 * verify the read only fields
	 */
	public boolean verifyDisabledFieldsByXpath(String[] fields, String[] desc){
		Boolean bReturn = true;

		List<String> trueValue= new ArrayList<String>();
		List<String> falseValue= new ArrayList<String>();

		for (int i=0; i < fields.length; i++) {
			if(verifyAttributeTextByXpath(fields[i], "readonly", "true")){
				trueValue.add(desc[i]);
			}else if(verifyAttributeTextByXpath(fields[i], "disabled", "true")){
				trueValue.add(desc[i]);
			}else{	
				bReturn = false;
				falseValue.add(desc[i]);
				//Reporter.reportStep("The field :"+desc[i]+" is editable; hence failed","FAILURE");
			}		
		}
		if(bReturn){
			Reporter.reportStep("All the fields: "+convertStringArrayToString(desc).replaceAll(",", ", ")+" are Read Only as expected.","SUCCESS");
		}

		else{		
			Reporter.reportStep("The following fields: "+falseValue+" are Editable, please check.","WARNING");
			Reporter.reportStep("The following fields: "+trueValue+" are Read Only as expected","SUCCESS");
		}
		return bReturn;
	}

	/**
	 * verify the read only fields
	 */
	public boolean verifyEnabledFieldsByXpath(String[] fields, String[] desc){
		Boolean bReturn = true;

		List<String> trueValue= new ArrayList<String>();
		List<String> falseValue= new ArrayList<String>();

		for (int i=0; i < fields.length; i++) {
			if(verifyAttributeTextByXpath(fields[i], "readonly", "true") || verifyAttributeTextByXpath(fields[i], "disabled", "true")){
				bReturn = false;
				trueValue.add(desc[i]);}
			if(!bReturn){
				falseValue.add(desc[i]);

				//				Reporter.reportStep("The field :"+desc[i]+" is Read Only ","FAILURE");
				//				break;
			}

		}

		if(bReturn)	
			Reporter.reportStep("All the fields:"+convertStringArrayToString(desc).replaceAll(",", ", ")+" are editable as expected","SUCCESS");		
		else
		{
			Reporter.reportStep("The following fields: "+falseValue+" are Read Only, please check.","WARNING");
			Reporter.reportStep("The following fields: "+trueValue+" are Editable as expected","SUCCESS");

		}
		return bReturn;
	}

	/**
	 * verify the existance of fields
	 */
	public boolean verifyFieldsExistByXpath(String[] fields, String desc[]){
		Boolean bReturn = true;

		List<String> trueValue= new ArrayList<String>();
		List<String> falseValue= new ArrayList<String>();


		for (int i=0; i < fields.length; i++) {
			if(!isExistByXpath(fields[i])){
				bReturn = false;
				falseValue.add(desc[i]);
				//				Reporter.reportStep("The field :"+desc[i]+" is not displayed; hence failed","WARNING");
			}
			trueValue.add(desc[i]);
		}

		if(bReturn)			
			Reporter.reportStep("All the fields:"+convertStringArrayToString(desc).replaceAll(",", ", ")+" are displayed as expected","SUCCESS");
		else{
			Reporter.reportStep("The following fields: "+falseValue+" are not displayeds, please check.","WARNING");
			Reporter.reportStep("The following fields: "+trueValue+" are displayed as expected","SUCCESS");}

		return bReturn;
	}

	/**
	 * verify the non mandatory fields
	 */
	public boolean verifyMenuItems(String menuHeader, String[] expectedMenus, String[] expectedMenusDesc){
		Boolean bReturn = true;

		List<WebElement> actualMenus = getAllElementsByXpath(menuHeader);
		if(expectedMenus.length != actualMenus.size())
			Reporter.reportStep("The menus expected: do not match with actual menus on the given page.", "FAILURE");

		for (int i = 0; i < actualMenus.size(); i++) {
			//System.out.println(actualMenus.get(i).getText().trim());
			if(!objRep.getProperty(expectedMenus[i]).trim().equals(actualMenus.get(i).getText().trim())){
				Reporter.reportStep("The menu at index:"+i+" with expected value:"+expectedMenusDesc[i]+" do not match with actual menu text: "+actualMenus.get(i).getText()+" on the incident home page.", "FAILURE");
				bReturn = false;
			}
		}

		if(bReturn)
			Reporter.reportStep("The menus expected: "+convertStringArrayToString(expectedMenusDesc).replaceAll(",", ", ")+" matched with actual menus on the given page.", "SUCCESS");

		return bReturn;


	}

	public Boolean deleteAllFilters(){
		Boolean bReturn = false;
		try {
			List<WebElement> filterDeletes = getAllElementsByXpath("ALERT_MyGroups_Delete_Xpath");
			for (WebElement filterDelete : filterDeletes) {	
				if(isExist(filterDelete))
					filterDelete.click();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}

		if(getCountOfElementsByXpath("ALERT_MyGroups_Delete_Xpath") == 1)
			bReturn = true;

		return bReturn;

	}

	public boolean addNewFilter(String filterType,String filterCondition,String filterValue){
		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath",filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");

			if(!selectByVisibleTextByXpath("CIS_FilterCondition1_Xpath",filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");

			new Actions(driver).sendKeys(Keys.TAB, Keys.TAB).build().perform(); // Move to the next element
			if(driver.switchTo().activeElement().getTagName().equals("select")){ // check if it is drop down
				if(!selectByVisibleTextByXpath("CIS_FilterValueSelect1_Xpath",filterValue))
					Reporter.reportStep("The Filter value "+ filterValue+ " could not be selected","FAILURE");
			} else { // if it is edit field
				if(!enterByXpath("CIS_FilterValueText1_Xpath",filterValue))
					Reporter.reportStep("The Filter value "+ filterValue+ " could not be entered","FAILURE");
			}				
			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bReturn;
	}

	public boolean verifyListContents(String xPathVal, String[] expectedValues){

		Boolean bReturn = true;
		List<String> optionValues = getOptionsByXpath(xPathVal);

		System.out.println(optionValues);

		for (String expectedValue : expectedValues) {
			Boolean bMatch = false;
			for (String option : optionValues) {
				if(option.equals(expectedValue)){
					bMatch = true;
					optionValues.remove(option);
					break;
				}						

			}
			if(!bMatch){
				bReturn = false;
				break;
			}
		}

		System.out.println(optionValues);
		return bReturn;
	}

	public boolean verifyTableHeaders(String xPathVal, String[] expectedValues){

		Boolean bReturn = true;
		String optionValues = getTextByXpath(xPathVal);
		System.out.println(optionValues);
		for (String expectedValue : expectedValues) {
			Boolean bMatch = false;

			if(optionValues.contains(expectedValue)){
				bMatch = true;
				break;
			}						

			if(!bMatch){
				bReturn = false;
				break;
			}
		}

		return bReturn;
	}

	public int getColumnIndex(String xPathVal, String columnValue){

		int colIndex = 0;

		String[] optionValues = getTextByXpath(xPathVal).split("\n");

		for (int i=0; i < optionValues.length; i++) {
			if(optionValues[i].equals(columnValue)){
				colIndex = i+1;
				break;
			}			
		}
		System.out.println(colIndex);
		return colIndex;
	}

	// indumathi 
	public boolean addNewFilterUsingSelect(String filterType,String filterCondition,String filterValue){
		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath",filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");

			if(!selectByVisibleTextByXpath("CIS_FilterCondition1_Xpath",filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");

			if(!selectByVisibleTextByXpath("CIS_FilterValueSelect1_Xpath",filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be selected","FAILURE");

			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bReturn;
	}

	public boolean addNewFilterUsingInput(String filterType,String filterCondition,String filterValue){
		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath",filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");

			Wait(500);

			if(!selectByVisibleTextByXpath("CIS_FilterCondition1_Xpath",filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");

			Wait(500);

			if(!enterByXpath("CIS_FilterValueText1_Xpath",filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be selected","FAILURE");

			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		return bReturn;
	}

	public boolean addNewFilterUsingInput1(String filterType,String filterCondition,String filterValue){
		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath",filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");

			Wait(500);

			if(!selectByVisibleTextByXpath("CIS_FilterCondition1_Xpath",filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");

			Wait(500);

			if(!enterByXpath("CIS_FilterValue1_Xpath",filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be selected","FAILURE");

			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bReturn;
	}

	public boolean compareListAndTable(String listValues, String xpathTable){
		String[] optionValues = listValues.split("\n");
		return verifyTableHeaders(xpathTable, optionValues);		
	}

	/*	public boolean verifyAllTexts(String xPathVal, String[] expectedValues){
		Boolean bReturn = true;
		List<WebElement> optionValues = getAllElementsByXpath(xPathVal);

		System.out.println(optionValues);
		if(optionValues.size() == 0)
			bReturn = false;
		for (String expectedValue : expectedValues) {
			for (WebElement option : optionValues) {
//				System.out.println(option.getText());
//				System.out.println(expectedValue);
				if(option.getText().equals(expectedValue)){
					bReturn = true;
					break;
				}						
			}
		}

		return bReturn;
	}
	 */
	public boolean selectByIndexByXpath(String xpathVal, int index) {
		boolean bReturn = false;
		try{
			new Select(getDriver().findElement(By.xpath(objRep.getProperty(xpathVal)))).selectByIndex(index);
			bReturn = true;
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
			//e1.printStackTrace();
		}
		return bReturn;
	}
	public boolean clickNewButton(){	
		Boolean bReturn = false;
		switchToFrame("Frame_Main");
		if(clickById("New_Button")){
			Reporter.reportStep("The New button is clicked successfully and New CI opened as expected", "SUCCESS");
			bReturn = true;}
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return bReturn;
	}

	public boolean playScenario(String scenario) {
		boolean bReturn = false;

		switchToFrame("Frame_Nav");

		if(!enterById("filter_Id", "Ops Director Testing"))
			Reporter.reportStep("'Ops Director Testing' could not be entered in filter box","FAILURE");

		if(!selectMenu("Ops_Director_Testing", "Scenarios"))
			Reporter.reportStep("The 'Scenarios' under Ops Director Testing - menu could not be selected","FAILURE");

		if(!clickById("filter_Clear_Id"))
			Reporter.reportStep("'Filter Box' could not be cleared","FAILURE");

		switchToFrame("Frame_Main");
		//Step 7: Run a Scenario from reference data
		if(!clickLink(scenario, false))
			Reporter.reportStep(scenario +" under Scenarios could not be clicked","FAILURE");
		Wait(5000);
		if(!clickByXpath("SCENARIOS_PlayScenarios_Xpath"))
			Reporter.reportStep("The 'Play Scenarios' could not be clicked","FAILURE");
		Wait(5000);
		if(!clickByXpath("SCENARIOS_Close_Xpath"))
			Reporter.reportStep("The 'Close Button' could not be clicked","FAILURE");
		switchToFrame("Frame_Nav");
		if(!enterById("filter_Id", "Ops Consoles"))
			Reporter.reportStep("'Ops Consoles' could not be entered in filter box","FAILURE");
		if(!selectMenu("Ops_Consoles", "Alert_Console"))
			Reporter.reportStep("The 'Alert Console' under OpsConsole - menu could not be selected","FAILURE");
		if(!clickById("filter_Clear_Id")){
			Reporter.reportStep("'Filter Box' could not be cleared","FAILURE");}
		switchToFrame("Frame_Main");
		bReturn = true;


		return bReturn;

	}
	public boolean acknowledgeAlert() {
		boolean bReturn = false;

		switchToFrame("Frame_Main");
		String alertId = getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");
		if(!rightClickByLinkText(alertId, false))
			Reporter.reportStep("Right click on the alert could not be clicked","FAILURE");
		if (clickByXpath("ALERT_Acknowledge_Xpath"))
			bReturn=true;
		else
			Reporter.reportStep("Acknowledge could not be clicked","FAILURE");
		switchToFrame("Frame_Nav");
		if(!selectMenu("Ops_Consoles", "My_Alert_Console"))
			Reporter.reportStep("Step 4: My Alert Console under OpsConsole - menu could not be selected","FAILURE");
		Wait(1000);

		switchToFrame("Frame_Main");

		return bReturn;
	}

	public boolean clickTableHeading(String xPathVal, String tableHeading){
		Boolean bReturn = false;
		List<WebElement> optionValues = getAllElementsByXpath(xPathVal);
		for (WebElement option : optionValues) {
			if(option.getText().equals(tableHeading))
				option.click();
			//System.out.println(option);
			bReturn = true;
		}						

		return bReturn;
	}

	public String getCMDBTaskNumber() {
		CMDBTaskNumber = getTextByXpath("SA_ApplicationNumber_Xpath");
		if(CMDBTaskNumber.equals(""))
			Reporter.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskNumber;
	}	

	public String getCMDBTaskNumberforSA() {
		CMDBTaskNumber = getTextByXpath("INC_missingCausingNumber_Xpath");
		if(CMDBTaskNumber.equals(""))
			Reporter.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskNumber;
	}	
	//indhu added 20-11
	public boolean addFilters(String loc1, String filterType , String loc2, String filterCondition, String loc3, String filterValue){

		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath(loc1,filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");
			Wait(500);
			if(!selectByVisibleTextByXpath(loc2,filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");
			Wait(500);
			if(!selectByVisibleTextByXpath(loc3,filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be selected","FAILURE");
			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bReturn;
	}

	public boolean addFilterstoEnterValue(String loc1, String filterType , String loc2, String filterCondition, String loc3, String filterValue){

		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath(loc1,filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");
			Wait(500);
			if(!selectByVisibleTextByXpath(loc2,filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");

			isExistByXpath(loc3);

			if(!enterByXpath(loc3,filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be entered","FAILURE");
			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bReturn;
	}
	public String getCMDBTaskNumberforPDUs() {
		CMDBTaskNumber = getTextByXpath("PDUs_ApplicationNumber_Xpath");
		if(CMDBTaskNumber.equals(""))
			Reporter.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskNumber;
	}	

	public String getCMDBTaskNumberforOracle() {
		CMDBTaskNumber = getTextByXpath("Oracle_ApplicationNumber_Xpath");
		if(CMDBTaskNumber.equals(""))
			Reporter.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskNumber;
	}	

	public boolean addFilters(String loc1, String filterType , String loc2, String filterCondition){

		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath(loc1,filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");

			if(!selectByVisibleTextByXpath(loc2,filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");


			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bReturn;
	}
	/*//Divya
	public boolean verifyList(String xPathVal, String[] expectedValues){

		List<String> list = new ArrayList<String>(Arrays.asList(expectedValues));

		Boolean bReturn = true;

		List<String> optionValues = getOptionsByXpath(xPathVal);

		for (String expectedValue : expectedValues) {
			Boolean bMatch = false;
			for (String option : optionValues) {
				if(option.equals(expectedValue)){
					bMatch = true;
					list.remove(expectedValue);
				}      
			}

			if(!bMatch){
				bReturn = false;
				break;
			}

		}

		System.out.println(list);
		return bReturn;
	}
	 */
	public String getCMDBTaskNumberforUPS() {
		CMDBTaskNumber = getTextByXpath("UPS_ApplicationNumber_Xpath");
		if(CMDBTaskNumber.equals(""))
			Reporter.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskNumber;
	}

	public String getFirstlink(){

		Wait(5000);		
		// click the first Incident Link
		Firstlinkname= getTextByXpath("ALERTPROFILE_FirstAlert_Xpath");			
		return Firstlinkname;
	}
	public boolean verifyallElementText(String xPathVal, String expectedValues1){
		Boolean bReturn=false;
		List<WebElement> CIclasses=getAllElementsByXpath(xPathVal);
		for(WebElement CIclass:CIclasses){
			if(CIclass.getText().equals(expectedValues1))
				return true;
			else
				return false;
		}
		return bReturn;	

	}
	public boolean verifyallElementText(String xPathVal, String expectedValues1, String expectedValues2){
		Boolean bReturn=false;
		List<WebElement> CIclasses=getAllElementsByXpath(xPathVal);
		for(WebElement CIclass:CIclasses){
			if(CIclass.getText().equals(expectedValues1) || CIclass.getText().equals(expectedValues2))
				return true;
			else
				return false;
		}
		return bReturn;	

	}
	public boolean verifyEnabledFieldsByXpath1(String[] fields, String[] desc){
		Boolean bReturn = true;
		for (int i=0; i < fields.length; i++) {
			if(verifyAttributeTextByXpath(fields[i], "readonly", "true") || verifyAttributeTextByXpath(fields[i], "disabled", "true"))
				bReturn = false;

			if(!bReturn){
				Reporter.reportStep("The field :"+desc[i]+" is Read Only ","SUCCESS");
				break;
			}

		}

		if(bReturn)	
			Reporter.reportStep("All the fields:"+convertStringArrayToString(desc).replaceAll(",", ", ")+" are editable as expected","FAILURE");		

		return bReturn;
	}

	public boolean verifyListContent(String xPathVal, String[] expectedValues){

		List<String> list = new ArrayList<String>(Arrays.asList(expectedValues));

		Boolean bReturn = true;

		List<String> optionValues = getOptionsByXpath(xPathVal);

		for (String expectedValue : expectedValues) {
			Boolean bMatch = false;
			for (String option : optionValues) {
				if(option.equals(expectedValue)){
					bMatch = true;
					list.remove(expectedValue);
				}      
			}

			if(!bMatch){
				bReturn = false;
				Reporter.reportStep(" Elements :"+list+" do not exists in Group by list", "WARNING");
				break;
			}

		}
		//	    Reporter.reportStep(" Elements :"+optionValues+" do not exists in Group by list", "WARNING");
		//System.out.println(list);
		return bReturn;
	}

	public boolean loginFSS(String user, String pwd)  {
		boolean bReturn = true;
		{
			// switch to the main frame
			switchToFrame("Frame_Main");

			// enter username, password and click login
			enterById("UserName_Id", user);
			enterById("Password_Id", pwd);
			clickById("LoginButton_Id");

			// If logout exists, send true
			//if(!IsElementNotPresentByXpath("Logout_Xpath")){
			//if(!isExistById("Frame_Nav")) / Rajkumar -- Oct 12 2015 -- Code added to check if the frame is visible; if not, click on toggle /
			//clickByXpath("ToggleNav_Xpath"); // Click on the toggle navigator
			//bReturn = true;
			//}
		}

		return bReturn;

	}
	public String getCMDBTask() {
		CMDBTaskNumber = getTextByXpath("INC_missingCausingNumber_Xpath");
		if(CMDBTaskNumber.equals(""))
			Reporter.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskNumber;
	}

	public int getColumnIndex1(String xPathVal, String columnValue){

		int colIndex =1 ;
		List<WebElement> optionValues = getAllElementsByXpath(xPathVal);

		for (WebElement optionValue:optionValues) {
			if(optionValue.getText().equals(columnValue)){
				break;}
			colIndex = colIndex+1;
		}			
		System.out.println(colIndex);
		return colIndex;
	}

	public String getCMDBTaskNumberforRP() {
		CMDBTaskNumber = getTextByXpath("SA_ApplicationNumber_Xpath1");
		if(CMDBTaskNumber.equals(""))
			Reporter.reportStep("The task number is blank for newly created CMDB Task", "FAILURE");
		return CMDBTaskNumber;
	}
	public String getFSSTaskNumber()
	{
		scrollToElementByXpath("FSS_FSSTasks_Number_Xpath");
		String ticketNumberAPO1TC01=getAttributeByXpath("FSS_FSSTasks_Number_Xpath","value");
		System.out.println(ticketNumberAPO1TC01);

		return ticketNumberAPO1TC01;
	}
	/*public boolean createincident_DEMO(String caller, String category, String subCategory, String ciValue, String contactType,
			String state, String assGroup, String assignTo, String impact, String urgency, String shortDes,
			String regUser, String priority) {
		Boolean breturn=false;
		new MenuPage_DEMO(driver)
		.selectCreateNew();

		IncidentPage_DEMO inc = new IncidentPage_DEMO(driver);

		String incNum = 
				inc.getIncident();
		inc.verifyCategoryIsAlreadySelected()
		.enterCaller(caller)
		.verifyLocationIsAlreadySelected()
		.enterAllFields(category, subCategory, ciValue, contactType, state, assGroup, assignTo)
		.selectImpactAndUrgency(impact, urgency)
		//.verifyPriorityIsReadonly()
		.enterSortDes(shortDes)
		.clickSubmit(regUser)
		.clickCreatedIncident(incNum);
		//.verifyPriority(priority);
		breturn=true;

		return breturn;

	}
	public boolean verifyMandatoryFields_DEMO(String[] fields, String[] desc){
		Boolean bReturn = true;
		for (int i=0; i < fields.length; i++) {
			if(!verifyAttributeTextByXpath(fields[i], "mandatory", "true")){
				bReturn = false;
				Reporter1.reportStep("The field :"+desc[i]+" is not displayed as mandatory. Hence Failed.","FAILURE");
			}		
		}

		if(bReturn)
			Reporter1.reportStep("All the fields:"+convertStringArrayToString(desc)+" are mandatory as expected","SUCCESS");
		return bReturn;
	}*/
	public Boolean verifyAlert(){
		Boolean bReturn=true;

		if(isAlertPresent()){
			String text=getTextAlert();
			alertAccept(); 
			Reporter.reportStep(text+", hence failure.", "FAILURE");
			bReturn=false; 
		}

		return bReturn;
	}
	public boolean verifyAllTextsUsingArray(String xPathVal, String[] expectedValues){
		Boolean bReturn = false;

		String optionValues = getTextByXpath(xPathVal);
		System.out.println(optionValues);

		//String[] list=optionValues.split(">");		

		for (String expectedValue : expectedValues) {
			//for (String lis : list){ 
			System.out.println(expectedValue);
			//System.out.println(lis);	
			if(optionValues.contains(expectedValue))
				bReturn = true;
			else
				bReturn = false;
			break;
		}


		return bReturn;
	}

	public boolean verifyAllTexts(String xPathVal, String[] expectedValues){
		Boolean bReturn = false;
		List<String> list = new ArrayList<String>(Arrays.asList(expectedValues));
		List<WebElement> optionValues = getAllElementByXpath(xPathVal);
		if(optionValues.size() == 0)
			bReturn = false;
		for (WebElement option : optionValues) {
			for (String expectedValue : list) {
				//				System.out.println(option.getText());
				//				System.out.println(expectedValue);
				if(option.getText().equals(expectedValue))
					return true;
				else
					return false;
			}
		}
		return bReturn;
	}	
	public boolean verifyAllTextsUsingContains(String xPathVal, String[] expectedValues){
		Boolean bReturn = false;
		List<String> list = new ArrayList<String>(Arrays.asList(expectedValues));
		List<WebElement> optionValues = getAllElementByXpath(xPathVal);
		if(optionValues.size() == 0)
			bReturn = false;
		for (WebElement option : optionValues) {
			for (String expectedValue : list) {
				System.out.println(option.getText());
				System.out.println(expectedValue);
				if(option.getText().contains(expectedValue))
					return true;
				else
					return false;
			}
		}
		return bReturn;
	}	

	public boolean verifyTextInGroupTexts(String xPathVal, String expectedValues1){

		List<WebElement> CIclasses=getAllElementsByXpath(xPathVal);
		System.out.println(CIclasses.size());
		int count=CIclasses.size();
		System.out.println(CIclasses.get(count-1).getText());
		if(CIclasses.get(count-1).getText().equals(expectedValues1))
			return true;
		else
			return false;

	}
	public boolean addFilterstoEnterValues(String loc1, String filterType , String loc2, String filterCondition, String loc3, String filterValue){

		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath(loc1,filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");
			Wait(500);
			if(!selectByVisibleTextByXpath(loc2,filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");
			Wait(500);
			if(!enterByXpath(loc3,filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be entered","FAILURE");
			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bReturn;
	}
	public boolean verifyAllText(String xPathVal, String[] expectedValues){
		Boolean bReturn = true;
		List<WebElement> optionValues = getAllElementsByXpath(xPathVal);

		if(optionValues.size() == 0)
			bReturn = false;
		for (String expectedValue : expectedValues) {
			for (WebElement option : optionValues) {
				System.out.println(option.getText());
				System.out.println(expectedValue);
				if(option.getText().equals(expectedValue)){
					bReturn = true;
					break;
				}						
			}
		}

		return bReturn;
	}
	public boolean verifyallElementTexts(String xPathVal, String[] expectedValues){
		Boolean bReturn=false;

		List<WebElement> CIclasses=getAllElementsByXpath(xPathVal);
		for(WebElement CIclass:CIclasses){
			System.out.println(CIclass.getText());

			for (int i = 0; i < expectedValues.length; i++) {
				System.out.println(expectedValues[i]);
				if(CIclass.getText().equals(expectedValues[i]))
					return true;
			}
		}
		return bReturn;	
	}
	public Boolean verifyAlertandenterValue(){
		Boolean bReturn=false;

		if(isAlertPresent()){
			alertAccept(); 
			bReturn=true;
		}
		return bReturn;
	}

	//WorldPay
	public Boolean selectUser(String user) {
		Boolean bReturn=false;

		if(!clickByXpath("AdministratorsIcon_Xpath"))
			Reporter.reportStep("The Administrators Icon could not be clicked.","FAILURE");
		if(!enterAndChoose("SelectUser_Xpath", user))
			Reporter.reportStep("The User "+user+" could not be entered.","FAILURE");
		if(!clickByXpath("OkButton_Xpath"))
			Reporter.reportStep("The OK Button could not be clicked.","FAILURE");
		if(verifyTextByXpath("FullName_Xpath", user)){
			bReturn=true;}
		else
			Reporter.reportStep("The Login user not changed hence failure.","FAILURE");

		return bReturn;
	}	
	public void switchToMenu(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

	}

	public void switchToMain(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

	}
	public boolean verifyDisabledFieldsByXpathwithoutReport(String[] fields, String[] desc){
		Boolean bReturn = true;
		for (int i=0; i < fields.length; i++) {
			if(verifyAttributeTextByXpath(fields[i], "readonly", "true")){
			}else if(verifyAttributeTextByXpath(fields[i], "disabled", "true")){
			}else{	
				bReturn = false;
				Reporter.reportStep("The field :"+desc[i]+" is editable; hence failed","FAILURE");
			}		
		}

		return bReturn;
	}

	public boolean addNewFilterUsingInput2(String filterType,String filterCondition,String filterValue){
		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath("CIS_FirstFilterType1_Xpath",filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");

			Wait(500);

			if(!selectByVisibleTextByXpath("CIS_FilterCondition1_Xpath",filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");

			Wait(500);

			if(!enterAndChoose("CIS_FilterValue1_Xpath",filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be selected","FAILURE");
			Wait(500);
			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bReturn;
	}

	public boolean verifyselectLists(String xpath, String[] expectedvalues){
		Boolean bReturn = true;

		List<String> list = getOptionsByXpath(xpath);
		System.out.println(expectedvalues.length);
		System.out.println(list.size());
		if(expectedvalues.length != list.size())
			Reporter.reportStep("The list expected values do not match with actual values on the given drop down.", "FAILURE");

		for (int i = 0; i < list.size(); i++) {

			if(!expectedvalues[i].equals(list.get(i).trim()))
				Reporter.reportStep("The value: "+expectedvalues[i]+" is not match with actual value "+list.get(i).trim()+" on the given drop down.", "FAILURE");
			bReturn = false;
		}

		Reporter.reportStep("The Status field values are matched with "+convertStringArrayToString(expectedvalues).replaceAll(",", ", ")+" as expected.","SUCCESS");
		return bReturn;


	}
	public boolean verifyUnselectLists(String xpath, String[] expectedvalues){
		Boolean bReturn = true;

		List<String> list = getOptionsByXpath(xpath);

		String deString=getDefaultValueByXpath(xpath);

		System.out.println(expectedvalues.length);
		System.out.println(list.size());

		list.remove(deString);

		if(expectedvalues.length != list.size())
			Reporter.reportStep("The list expected values do not match with actual values on the given drop down.", "FAILURE");

		for (int i = 0; i < list.size(); i++) {

			if(!expectedvalues[i].equals(list.get(i).trim()))
				Reporter.reportStep("The value: "+expectedvalues[i]+" is not match with actual value "+list.get(i).trim()+" on the given drop down.", "FAILURE");
			bReturn = false;
		}

		Reporter.reportStep("The Status other options "+convertStringArrayToString(expectedvalues).replaceAll(",", ", ")+" matched while status "+deString,"SUCCESS");
		return bReturn;

	}

	public boolean opentheAsset(String assetTag) {

		boolean bReturn=false;

		if(!clickByXpath("List_filterIcon_Xpath"))
			Reporter.reportStep("The Filter Icon is not clicked or not visible, hence failure.", "FAILURE");

		addFilterstoEnterValue("List_FirstFilterTypeByselect_Xpath", "Asset tag", "List_FilterConditionByselect_Xpath", 
				"is", "List_FirstFilterTypeByInput1_Xpath", assetTag);

		if(!clickByXpath("List_FilterRun_Xpath"))
			Reporter.reportStep("The Run Button is not clicked or not visible, hence failure.", "FAILURE");

		if(clickByXpath("List_AllLinks_Xpath"))
			bReturn=true;
		else{
			Reporter.reportStep("The First Asset Tag is not clicked, hence failure.", "FAILURE");}

		return bReturn;		
	}

	public boolean addFilterstoEnterAndChooseValues(String loc1, String filterType , String loc2, String filterCondition, String loc3, String filterValue){

		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath(loc1,filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");
			Wait(500);
			if(!selectByVisibleTextByXpath(loc2,filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");
			Wait(500);
			if(!enterAndChoose(loc3,filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be entered","FAILURE");
			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bReturn;
	}
	public boolean verifycolumnValue(String colName, String value, String xPathVal) {

		boolean bReturn = false;
		int column=getColumnIndex1(xPathVal, colName);

		String text=getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false);

		System.out.println(text);
		scrollToElementByXpath(xPathVal);

		//	System.out.println(getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false));
		if(text.equalsIgnoreCase(value)){
			bReturn = true;}
		else
			Reporter.reportStep("The "+colName+" value: "+value+" is not matched, check snapshot.", "FAILURE");


		return bReturn;
	}
	public boolean verifyEnabledFieldsByXpath2(String[] fields, String[] desc){
		Boolean bReturn = true;
		for (int i=0; i < fields.length; i++) {
			if(verifyAttributeTextByXpath(fields[i], "readonly", "true") || verifyAttributeTextByXpath(fields[i], "disabled", "true"))
				bReturn = false;

			if(!bReturn){
				Reporter.reportStep("The field :"+desc[i]+" is Read Only, hence failure.","FAILURE");
				break;
			}

		}

		if(bReturn)	
			Reporter.reportStep("All the fields:"+convertStringArrayToString(desc).replaceAll(",", ", ")+" are editable as expected","SUCCESS");		

		return bReturn;
	}
	public boolean verifyallElementTexts1(String xPathVal, String[] expectedValues){
		Boolean bReturn=false;

		List<WebElement> CIclasses=getAllElementsByXpath(xPathVal, false);
		for(WebElement CIclass:CIclasses){
			System.out.println(CIclass.getText());

			for (int i = 0; i < expectedValues.length; i++) {
				System.out.println(expectedValues[i]);
				if(CIclass.getText().equals(expectedValues[i]))
					return true;
			}
		}
		return bReturn;	
	}
	public boolean addFilterstoEnterAndChooseValue(String loc1, String filterType , String loc2, String filterCondition, String loc3, String filterValue){

		boolean bReturn = false;
		try {
			if(!selectByVisibleTextByXpath(loc1,filterType))
				Reporter.reportStep("The Filter type "+ filterType+ " could not be selected","FAILURE");
			Wait(500);
			if(!selectByVisibleTextByXpath(loc2,filterCondition))
				Reporter.reportStep("The Filter condition "+ filterCondition+ " could not be selected","FAILURE");

			isExistByXpath(loc3);

			if(!enterAndChoose(loc3,filterValue))
				Reporter.reportStep("The Filter value "+ filterValue+ " could not be entered","FAILURE");
			bReturn = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bReturn;
	}
	public boolean verifycolumnValue1(String colName, String value, String xPathVal) {

		boolean bReturn = false;
		int column=getColumnIndex1(xPathVal, colName);

		column=column+1;
		String text=getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false);

		System.out.println(text);
		scrollToElementByXpath(xPathVal);

		//	System.out.println(getTextByXpath("(//*[@class='vt'])"+"["+column+"]", false));
		if(text.equalsIgnoreCase(value)){
			bReturn = true;}
		else
			Reporter.reportStep("The "+colName+" value: "+value+" is not matched, check snapshot.", "FAILURE");


		return bReturn;
	}
	public String getColumnValue(String value, String xPathVal) {

		int column=getColumnIndex1(xPathVal, value);

		String text=getTextByXpath("(//*[@class='vt'])["+column+"]", false);

		if(text.equals(""))
			Reporter.reportStep("The value "+value+" is Blank, hence failure", "FAILURE");
		else
			Reporter.reportStep("The "+value+" Value: "+text+" is noted as expected.", "SUCCESS");

		return text;
	}


	public boolean verifycolumnValues(String colName, String[] value, String xPathVal) {

		boolean bReturn = false;
		int column=getColumnIndex1(xPathVal, colName);
		String valu="";
		column=column+2;
		System.out.println(column);
		//		List<WebElement> list=getAllElementsByXpath("(//*[@class='vt'])"+"["+column+"]", false);

		List<WebElement> list=getAllElementsByXpath("(//table[contains(@id,'x_tori2_opd_ci_scopes.REL:')])/tbody/tr/td["+column+"]", false);
		for (WebElement webElement : list) {
			boolean bMatch = false;
			for (String val : value) {
				valu=webElement.getText();
				System.out.println(webElement.getText());
				if(webElement.getText().contains(val)){
					bMatch = true;
					break;}

			}
			if(!bMatch){
				bReturn = false;
				break;
			}
			else{
				bReturn = true;
			}
		}

		scrollToElementByXpath(xPathVal);
		System.out.println(bReturn);
		if(!bReturn) 
			Reporter.reportStep("The "+colName+" value: "+valu+" is not matched with given value ["+convertStringArrayToString(value)+"], check snapshot.", "FAILURE");

		return bReturn;
	}

	// Raj added on 23rd May 2016

	public boolean submitIdea(String title, String description)  {
		boolean bReturn = false;
		{
			switchToFrame("Frame_Main");

			enterByXpath("ServiceCata_Title_Xpath", title);
			enterByXpath("ServiceCata_Description_Xpath", description);
			clickById("ServiceCata_Submit_Id");


			String ideaNo =  getTextByXpath("ServiceCata_IdeaNo_Xpath");


			if(!ideaNo.equals(""))
				Reporter.reportStep("The Idea :"+ideaNo+" is submitted successful ", "SUCCESS");
			else
				Reporter.reportStep("The Idea could not submitted", "FAILURE");
			bReturn = true;
		}
		return bReturn;
	}
	public String calculateSLADiffAndAdd(String dateStart, String dateStop) throws ParseException {
		//		String dateStart = "07-07-2016 03:36:10";
		//  	String dateStop = "07-18-2016 06:36:10";


		String year= dateStart.substring(6, 10);
		System.out.println(year);
		String month=dateStart.substring(0, 2);
		System.out.println(month);
		String dayOfMonth=dateStart.substring(3, 5);
		System.out.println(dayOfMonth);
		String hourOfDay=dateStart.substring(11, 13);
		System.out.println(hourOfDay);
		String minute=dateStart.substring(14, 16);
		System.out.println(minute);
		String second=dateStart.substring(17, 19);
		System.out.println(second);



		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");


		Calendar calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month)-1,
				Integer.parseInt(dayOfMonth), Integer.parseInt(hourOfDay), Integer.parseInt(minute), Integer.parseInt(second));	
		Date date=format.parse(dateStart);

		System.out.println(date);
		try {	
			Date d1 = null;
			Date d2 = null;

			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.println(diffDays + " days, ");
			System.out.println(diffHours + " hours, ");
			System.out.println(diffMinutes + " minutes, ");
			System.out.println(diffSeconds + " seconds.");


			calendar.add(Calendar.SECOND, -((int)diffSeconds));
			System.out.println("Date : " + format.format(calendar.getTime()));

			calendar.add(Calendar.HOUR, -((int)diffHours));
			System.out.println("Date : " + format.format(calendar.getTime()));

			calendar.add(Calendar.MINUTE, -((int)diffMinutes));
			System.out.println("Date : " + format.format(calendar.getTime()));

			calendar.add(Calendar.DAY_OF_YEAR, -((int)diffDays));
			System.out.println("Date : " + format.format(calendar.getTime()));


		}
		catch(Exception e){

		}
		return format.format(calendar.getTime());

	}

	public boolean clickSLAInformationIcon(String slA) {

		boolean bReturn=false;

		scrollToElementByXpath("ALERT_TaskSLATableHead_Xpath");

		int i=1;
		int slaColumn = 
				getColumnIndex1("ALERT_TaskSLATableHead_Xpath", "SLA");

		slaColumn = slaColumn+2;

		System.out.println( "SLA column"+slaColumn);

		List<WebElement> slaValues = getAllElementsByXpath(""
				+ "//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr/td["+slaColumn+"]", false);

		for (WebElement sla : slaValues) {
			System.out.println(sla.getText());
			System.out.println("Passin "+slA);
			if(sla.getText().equals(slA)){
				i=slaValues.indexOf(sla);
				System.out.println("i value"+i);
				break;}

		}
		i=i+1;
		System.out.println("//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr["+(i)+"]/td[2]");

		if(clickByXpath("//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr["+(i)+"]/td[2]", false))
			bReturn=true;
		else
			Reporter.reportStep("The Preview button is not clicked, hence failed.", "FAILURE");

		return bReturn;
	}
	public boolean clickSLAInformationIcon1(String slA) {

		boolean bReturn=false;

		scrollToElementByXpath("ALERT_TaskSLATableHead_Xpath");

		int i=0;
		int slaColumn = 
				getColumnIndex1("ALERT_TaskSLATableHead_Xpath", "SLA");

		slaColumn = slaColumn+2;

		System.out.println( "SLA column"+slaColumn);

		List<WebElement> slaValues = getAllElementsByXpath(""
				+ "//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr/td["+slaColumn+"]", false);

		System.out.println(slaValues);
		for (WebElement sla : slaValues) {
			System.out.println(sla.getText());
			System.out.println("Passin "+slA);
			if(sla.getText().equals(slA)){
				System.out.println("i value"+i);
				break;}
			else{
				i++;
				System.out.println("i value"+ i);}
		}

		System.out.println("//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr["+(i)+"]/td[2]");

		if(clickByXpath("//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr["+(i)+"]/td[2]", false))
			bReturn=true;
		else
			Reporter.reportStep("The Preview button is not clicked, hence failed.", "FAILURE");

		return bReturn;
	}
	public boolean slAFieldsVerification(String[] values) {

		String[] fields={"Inc_SLALabel_Id","Inc_SLAStage_Id",
				"Inc_SLASchedule_Id","Inc_SLATimezone_Id",
				"Inc_SLATaskLabel_Id","Inc_SLAUpdatedOn_Id",
				"Inc_SLAStartTime1_Id","Inc_SLAPlannedEndTime_Id",
		"Inc_SLAEndTime1_Id"};
		String[] desc={"SLA","Stage","Schedule","Time zone",
				"Task","Updated","Start time","Planned end time",
		"End Time"};

		boolean bReturn=false;

		List<String> trueValue= new ArrayList<String>();
		List<String> falseValue= new ArrayList<String>();

		for (int i=0; i < fields.length-1; i++) {
			if(verifyAttributeTextById(fields[i], "readonly", "true")){
				if(getAttributeById(fields[i], "value").equals(values[i]))
					trueValue.add(desc[i]);
				bReturn = true;
			}else if(verifyAttributeTextById(fields[i], "disabled", "true")){
				if(getDefaultValueById(fields[i]).equals(values[i]))
					trueValue.add(desc[i]);
				bReturn = true;
			}else{	
				bReturn = false;
				falseValue.add(desc[i]);
				//Reporter.reportStep("The field :"+desc[i]+" is editable; hence failed","FAILURE");
			}		
		}
		if(bReturn){
			Reporter.reportStep("All the fields: "+convertStringArrayToString(desc).replaceAll(",", ", ")+" are Read Only and values matched as expected.","SUCCESS");
		}

		else{		
			Reporter.reportStep("The following fields: "+falseValue+" are Editable, please check.","WARNING");
			Reporter.reportStep("The following fields: "+trueValue+" are Read Only as expected","SUCCESS");
		}

		return bReturn;

	}
	public String getInstanceTime() {

		goOutOfFrame();

		if(!clickById("ServiceNow_Setting_Id"))
			Reporter.reportStep("The Setting Icon is not found or clicked, hence failure.", "FAILURE");

		String timezonw=getDefaultValueById("ServiceNow_TimeZone_Id");

		System.out.println(timezonw);

		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

		DateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");     
		formatter.setTimeZone(TimeZone.getTimeZone(timezonw));  

		String newZealandTime = formatter.format(calendar.getTime());
		System.out.println(newZealandTime);


		return newZealandTime;
	}
	public boolean getTimesAndVerifyMorethan(String idVal, String actTime) throws ParseException {

		boolean bValue=false;

		String time="";
		time = getTextById(idVal);
		System.out.println(time);
		String intValue = time.replaceAll("[^0-9]", " ");
		String[] values=intValue.split(" ");
		StringBuilder builder = new StringBuilder();
		List<String> list=new ArrayList<String>();
		for(String s : values) {
			s=s.trim();
			if(!s.isEmpty()){
				builder.append(s).append(",");
				list.add(s);
				System.out.println(s);}
		}

		String date="";
		
		if(time.contains("Hours")&&time.contains("Minutes")&&time.contains("Seconds")){
			if(list.size()==3){
				System.out.println(list);
				date=list.get(0)+":"+list.get(1)+":"+list.get(2);}}
		
		else if(time.contains("Hours")&&time.contains("Minutes")){
			if(list.size()==2){
				System.out.println(list);
				date=list.get(0)+":"+list.get(1)+":00";}
			}

		else if(time.contains("Minutes")&&time.contains("Seconds")){
			if(list.size()==2){
				System.out.println(list);
				date="00:"+list.get(0)+":"+list.get(1);}}
		
		else if(time.contains("Minutes")){
			if(list.size()==1){
				System.out.println(list);
				date="00:"+list.get(0)+":00";}}
		
		else if(time.contains("Seconds")){
			if(list.size()==1){
				System.out.println(list);
				date="00:00:"+list.get(0);}}
		
		/*if(list.size()==3){
			System.out.println(list);
			//		String date=list.get(list.size()-2)+":"+list.get(list.size()-1)+":"+list.get(list.size()-1);
			date=list.get(0)+":"+list.get(1)+":"+list.get(2);}
		else if(list.size()==2){
		System.out.println(date);
		date="00:"+list.get(0)+":"+list.get(1);}
		 
		else if(list.size()==2){
			System.out.println(date);
			date=list.get(0)+":"+list.get(1)+":00";}

			else if(list.size()==1){
		System.out.println(date);
		date="00:00:"+list.get(0);}
		 	else if(list.size()==1){
			 System.out.println(date);
			 date="00:"+list.get(0)+":00";}
*/
		if(compareMorethanTimes(actTime, date))
			bValue=true;
		else
			Reporter.reportStep("The Time could not matched, please check.","WARNING");

		return bValue;
	}
	public boolean getTimesAndVerifyLessthan(String idVal, String actTime) throws ParseException {

		boolean bValue=false;

		String time="";
		time = getTextById(idVal);
		System.out.println(time);
		String intValue = time.replaceAll("[^0-9]", " ");
		System.out.println(intValue);
		String[] values=intValue.split(" ");

		StringBuilder builder = new StringBuilder();

		List<String> list=new ArrayList<String>();

		for(String s : values) {
			s=s.trim();
			if(!s.isEmpty()){
				builder.append(s).append(",");
				list.add(s);
				System.out.println(s);
			}
		}

		String date="";
		if(time.contains("Hours")&&time.contains("Minutes")&&time.contains("Seconds")){
			if(list.size()==3){
				System.out.println(list);
				date=list.get(0)+":"+list.get(1)+":"+list.get(2);}}
		
		else if(time.contains("Hours")&&time.contains("Minutes")){
			if(list.size()==2){
				System.out.println(list);
				date=list.get(0)+":"+list.get(1)+":00";}
			}

		else if(time.contains("Minutes")&&time.contains("Seconds")){
			if(list.size()==2){
				System.out.println(list);
				date="00:"+list.get(0)+":"+list.get(1);}}
		
		else if(time.contains("Minutes")){
			if(list.size()==1){
				System.out.println(list);
				date="00:"+list.get(0)+":00";}}
		
		else if(time.contains("Seconds")){
			if(list.size()==1){
				System.out.println(list);
				date="00:00:"+list.get(0);}}
		
		
		/*else if(list.size()==2){
			System.out.println(date);
			date="00:"+list.get(0)+":"+list.get(1);}

		else if(list.size()==1){
			System.out.println(date);
			date="00:00:"+list.get(0);
			System.out.println(date);
		}
*/
		try {
			if(compareLessthanTimes(actTime, date))
				bValue=true;
			else
				Reporter.reportStep("The Time could not matched, please check.","WARNING");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bValue;
	}

}	



