package pages;


import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class CreateNewPage_DEMO extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public CreateNewPage_DEMO(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("Back_Xpath")) {
			Reporter1.reportStep("This is not the Create New page", "FAILURE");
		}
	}

	public CreateNewPage_DEMO switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	

	public CreateNewPage_DEMO verifyAllMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"INC_AssignmentGroupMand_Xpath",
				"INC_AssignedToMand_Xpath",
		"INC_ConfigItemMand_Xpath"};

		String[] mandatoryFieldsDesc = {"Assignment Group",
				"Assigned To","Configuration Item"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}
	
}





