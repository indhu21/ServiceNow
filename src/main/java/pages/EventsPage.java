package pages;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class EventsPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;
	private String incidentNumber;

	public EventsPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		//Check that we're on the right page.
		/*if (!isExistByXpath("ReportHead_Xpath")) {
						Reporter.reportStep("This is not the Events Page.", "FAILURE");
		}*/
	}

	public EventsPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}


	
	
	
	
}

