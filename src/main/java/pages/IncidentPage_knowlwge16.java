package pages;


import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testng.SuiteMethods;
import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.GenericWrappers;
import wrapper.ServiceNowWrappers;

public class IncidentPage_knowlwge16 extends SuiteMethods{

	private final RemoteWebDriver driver;
	private String incidentNumber;
	private String incidentState;
	private String incidentPriority;
	private String missingCausingNumber;

	public IncidentPage_knowlwge16(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Incident page", "FAILURE");
		}
	}

	public IncidentPage_knowlwge16 switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	
	public String getIncidentNumber() {

		incidentNumber = getAttributeByXpath("CREATEINC_IncidentNumberDEMO_Xpath", "value");

		if(incidentNumber.equals(""))
			Reporter_ServiceNow.reportStep("The incident number is blank for newly created incident", "FAILURE");

		return incidentNumber;
	}
	public ListPage_knowlwge16 createIncidentForDemo(String affuser, String cat, String subcat, 
			String impact, String urgency, String asngrp, String shrtdes) {
		
		if(!enterByXpath("CREATEINC_AffectedUser_Xpath", affuser))
			Reporter_ServiceNow.reportStep("The Afftected User could not be entered.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Category_Id", cat))
			Reporter_ServiceNow.reportStep("The Category could not be selected.", "FAILURE");

		Wait(5000);
		
		if(!selectByVisibleTextById("CREATEINC_SubCategory_Id", subcat))
			Reporter_ServiceNow.reportStep("The Sub Category could not be selected.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Impact_Id", impact))
			Reporter_ServiceNow.reportStep("The Impact could not be selected.", "FAILURE");

		if(!selectByVisibleTextById("CREATEINC_Urgency_Id", urgency))
			Reporter_ServiceNow.reportStep("The Urgency could not be selected.", "FAILURE");

		if(!enterAndChoose("CREATEINC_AsgGroup_Xpath", asngrp))
			Reporter_ServiceNow.reportStep("The Assignment Group could not be entered.", "FAILURE");

		if(!enterByXpath("CREATEINC_shortDesc_Xpath", shrtdes))
			Reporter_ServiceNow.reportStep("The Update button could not be entered", "FAILURE");
		
		if(clickByXpath("Submit_Incident_Xpath"))
			Reporter_ServiceNow.reportStep("All the values are entered in respective fields and submit button is clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create Incident process failed. Check snapshot", "FAILURE");

		return new ListPage_knowlwge16(driver);
	}
}





