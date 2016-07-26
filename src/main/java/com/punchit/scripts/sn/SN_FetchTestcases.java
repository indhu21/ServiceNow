package com.punchit.scripts.sn;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.QueryDB;
import utils.Reporter;
import wrapper.ServiceNowWrappers;


public class SN_FetchTestcases extends ServiceNowWrappers{

	@Parameters
	@Test
	public void fetchTests(){
		
		// Launch application using phantomjs
		launchApp("chrome", true);
		
		try {
			// login
			login("Ashish.RW", "ashishrw1");
			
			// expand Testcase Run and click New
			selectMenuFromMainHeader("SN_Testcases", "SN_New");
			
			// Get all the Test Numbers, Browser Name
			RemoteWebDriver driver = getDriver();
			switchToFrame("Frame_Main");

			List<WebElement> tableRows = driver.findElementsByXPath("//tr[contains(@id,'row_u_testcase_run_')]");
			
			for (WebElement tableRow : tableRows) {
				List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
				if(tableData.get(3).getText().trim().equals("New")){
					System.out.println("The client id :"+tableData.get(5).getText());
					System.out.println("The test name :"+tableData.get(2).getText());
					System.out.println("The browser name :"+tableData.get(4).getText());
					System.out.println("The class name :"+tableData.get(8).getText());
					
					// Update database only when 
					QueryDB.insertTestcases(tableData.get(2).getText(),Integer.parseInt(tableData.get(5).getText()),tableData.get(8).getText(),tableData.get(4).getText());
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally{
			quitBrowser();
		}
		
		
	}

}