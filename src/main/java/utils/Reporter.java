package utils;

import java.util.ArrayList;
import java.util.List;

import ServiceNow_Integration.suitemethods;
import testng.SuiteMethods;
import wrapper.ServiceNowWrappers;

public class Reporter extends SuiteMethods{
	
	private static String errorDescription;
	
	static ServiceNowWrappers snW = new ServiceNowWrappers();	
	
	
	public static void reportStep(String desc, String status, boolean bSnapshot) {
		
		
		//System.out.println(desc);
		setErrorDescription("");
		
		PdfWrite.appendReport(ServiceNowWrappers.testcaseName+".pdf", desc, bSnapshot);
		
		// Write if it is successful or failure
		if(status.toUpperCase().equals("SUCCESS")){
			//ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else if(status.toUpperCase().equals("WARNING")){
			status = "WARN";
			bWarning = true;
			//ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		else if(status.toUpperCase().equals("INFO")){
			//ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else {
			setErrorDescription(desc);	
			throw new RuntimeException("FAILED");
		}
		
	}
	
	public static void reportStep(String desc, String status) {
		desc=desc.replace("[", "");
		desc=desc.replace("]", "");
		reportStep(desc, status, true);	
		
	}
	
	public static void setResult(String testcaseName){
		PdfWrite.createNewPdf(testcaseName+".pdf");
		//ATUReports.setWebDriver(new ServiceNowWrappers().getDriver());
		//ATUReports.indexPageDescription = "Service Now Project";
		//ATUReports.setAuthorInfo("Rajkumar",  Utils.getCurrentTime(), "1.0");
	}
	

	public static String getErrorDescription() {
		return errorDescription;
	}

	private static void setErrorDescription(String errorDescription) {
		Reporter.errorDescription = errorDescription;
	}

}
