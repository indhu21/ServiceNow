package pages_OutOfBox;


import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class ProblemCreateNewPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public ProblemCreateNewPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("Back_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the Create New page", "FAILURE");
		}
	}

	public ProblemCreateNewPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	

	public ProblemCreateNewPage verifylistofProblemState(){

		String[] expectedValues = {"Open","Pending Change","Known Error","Closed/Resolved"};		

		if(verifyListContents("PRTC_State_Xpath", expectedValues))
			Reporter_ServiceNow.reportStep("The value: "+convertStringArrayToString(expectedValues)+" are the list of values in State drop down","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The default list of values does not match with the State drop down","FAILURE");			

		return this;
	}
	
	public ProblemCreateNewPage createProblem(String configItem, String assignmentGroup, String assignedTo, String priority, String state, String description) {

		if (!enterAndChoose("PRTC_ConfigurationItem_Xpath", configItem))
			Reporter_ServiceNow.reportStep("The value: "+configItem+" could not entered in Configuration Item field",
					"FAILURE");

		if (!enterAndChoose("PRTC_AssignmentGroup_Xpath", assignmentGroup))
			Reporter_ServiceNow.reportStep("The value: "+assignmentGroup+"  could not be entered in Assignment Group field",
					"FAILURE");

		if (!enterAndChoose("PRTC_AssignedTo_Xpath",assignedTo ))
			Reporter_ServiceNow.reportStep("The value: "+assignedTo+" could not be entered in Assigned To field",
					"FAILURE");


		if (!selectByVisibleTextByXpath("PRTC_Priority_Xpath", priority))			
			Reporter_ServiceNow.reportStep("The value: "+priority+"  could not be selected in Priority field",
					"FAILURE");

		if (!selectByVisibleTextByXpath("PRTC_State_Xpath",state ))			
			Reporter_ServiceNow.reportStep("The value: "+state+"  could not be selected in Priority field",
					"FAILURE");

		if (!enterByXpath("PRTC_Description_Xpath",description ))			
			Reporter_ServiceNow.reportStep("The value: "+description+"  could not be selected in Priority field",
					"FAILURE");


		if (!rightClickByXpath("CHN_Header_Xpath"))
			Reporter_ServiceNow.reportStep("Right click could not be done on the header",
					"FAILURE");

		if (clickByXpath("CHN_Save_Xpath"))
			Reporter_ServiceNow.reportStep("The "+state+", "+configItem+" ,"+priority+" , "+assignmentGroup+", "+assignedTo+", "+description+" are entered in the corresponding fields and Saved successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The save button could not been clicked", "FAILURE");
		return this;

	}
	
	public ProblemCreateNewPage verifyTicketCreated(){
		// Verify the tabs exists
		if(getAttributeByXpath("PRTC_Number_Xpath", "value").contains("PRB"))

			Reporter_ServiceNow.reportStep("Ticket is created successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Ticket could not be created", "FAILURE");
		return this;
	}
	
}




