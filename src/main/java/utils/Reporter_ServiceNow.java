package utils;

import testng.SuiteMethods_ServiceNowFrontEnd;
import wrapper.ServiceNowWrappers;

public class Reporter_ServiceNow extends SuiteMethods_ServiceNowFrontEnd{
	private static String errorDescription;
	static ServiceNowWrappers snW = new ServiceNowWrappers();	
	
	public static void reportStep(String desc, String status, boolean bSnapshot) {
		
		//System.out.println(desc);
		
		setErrorDescription("");
		String pdf_name=QueryDB_ServiceNowFrontEnd.readPDFname(ServiceNowWrappers.testcaseName);
		
		PdfWrite_ServiceNowFrontEndIndhu.appendReport(pdf_name+".pdf", desc, bSnapshot);
		
		// Write if it is successful or failure
		if(status.toUpperCase().equals("SUCCESS")){
			//ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else if(status.toUpperCase().equals("WARNING")){
			status = "FAIL";
		
			//ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else if(status.toUpperCase().equals("INFO")){
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
		PdfWrite_ServiceNowFrontEndIndhu.createNewPdf(QueryDB_ServiceNowFrontEnd.readPDFname(testcaseName)+".pdf");
		//ATUReports.setWebDriver(new ServiceNowWrappers().getDriver());
		//ATUReports.indexPageDescription = "Service Now Project";
		//ATUReports.setAuthorInfo("Rajkumar",  Utils.getCurrentTime(), "1.0");
	}

	public static String getErrorDescription() {
		return errorDescription;
	}

	// Babu added for getting first PDF page	
	public static void appendFirstPage(String testcaseName,String description, String startDate, String endDate, long time, String status){
		PdfWrite_ServiceNowFrontEndIndhu.appendFirstPage(testcaseName,description, startDate, endDate, time, status);
	}

	
	private static void setErrorDescription(String errorDescription) {
		Reporter_ServiceNow.errorDescription = errorDescription;
	}

}

	