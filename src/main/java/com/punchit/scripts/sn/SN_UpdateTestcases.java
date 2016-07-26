package com.punchit.scripts.sn;

import java.util.List;

import java.util.Map;
import java.util.Set;

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


public class SN_UpdateTestcases extends ServiceNowWrappers {

	@Parameters
	@Test
	public void updateTests(){

		// Fetch the top records to be updated
		Set<Map<String,String>> testcases = QueryDB.fetchTestcasesToBeUpdated();
		RemoteWebDriver driver;
		if(testcases.size() > 0 ) {
			// Launch application using phantomjs
			launchApp("chrome", true);

			// login
			login("Ashish.RW", "ashishrw1");			

			for (Map<String, String> testcase : testcases) {
				
				// expand Testcase Run and click New
				selectMenuFromMainHeader("SN_Testcases", "SN_New");

				// Get all the Test Numbers, Browser Name
				driver = getDriver();
				switchToFrame("Frame_Main");

				try {
					// Search the Testcase runs
					enterByXpathAndClick("ALERTPROFILE_Search_Xpath", testcase.get("runid"));

					// Click on the testcase
					clickByXpath("ALERTPROFILE_FirstAlert_Xpath");	

					// Enter execution time
					enterById("SN_ExecutionTime_Id", testcase.get("execTime"));

					// Enter the execution status
					selectByVisibleTextById("SN_ExecutionStatus_Id", "Completed");

					// Select the run status
					String status = testcase.get("exeStatus");
					String execStatus = "Fail";
					if(status.equals("1"))
						execStatus = "Pass";
					else if(status.equals("3"))
						execStatus = "Error In Execution";
					else if(status.equals("4"))
						execStatus = "Insufficient Data";

					selectByVisibleTextById("SN_RunStatus_Id", execStatus);
					
					// Click Attachment
					clickByXpath("Attachment_Xpath");
					
					//String filePath = "C:/ServiceNow/reports/"+testcase.get("runid")+".pdf";
					String filePath = "G:/Punch it/DEMO/ServiceNow/reports/"+testcase.get("runid")+".pdf";
					//G:\Punch it\DEMO\ServiceNow\reports
					enterById("ChooseFiles_Id", filePath);

					clickByXpath("AttachFile_Xpath");
					Wait(15000);

					isExistByXpath("IsAttachmentUploaded");
					clickByXpath("CREATEINC_CloseUpload_Xpath");

					// Click on the update button
					clickById("Update_Button");
					QueryDB.updateUiStatus(testcase.get("runid"));
				} catch (Exception e) {
					
				}

				

			//}
			quitBrowser();
		}

	}
	}
}

//}//
