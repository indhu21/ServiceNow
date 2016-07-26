package utils;

import wrapper.ServiceNowWrappers;

public class Reporter1 {
	private static String errorDescription;
	static ServiceNowWrappers snW = new ServiceNowWrappers();	
	
	public static void reportStep(String desc, String status, boolean bSnapshot) {
		
		System.out.println(desc);
		setErrorDescription("");
		String pdf=QueryDB_1.readPDFname(ServiceNowWrappers.testcaseName);
		
		PdfWrite.appendReport(pdf+".pdf", desc, bSnapshot);

		// Write if it is successful or failure
		if(status.toUpperCase().equals("SUCCESS")){
			//ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else if(status.toUpperCase().equals("WARNING")){
			 status = "FAIL";
			//ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else if(status.toUpperCase().equals("INFO")){
			//ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
//		}
//		else if(status.toUpperCase().equals("INSUFFICIENT DATA")){
//			status="FAIL";
		}else {
			setErrorDescription(desc);	
		   throw new RuntimeException("FAILED");
			//throw new RuntimeException("FAIL");
		}
	}

	public static void reportStep(String desc, String status) {
		reportStep(desc, status, true);		
	}
	
	public static void setResult(String testcaseName){
	    String pdf1=QueryDB_1.readPDFname(testcaseName);
		PdfWrite.createNewPdf(pdf1+".pdf");
		//ATUReports.setWebDriver(new ServiceNowWrappers().getDriver());
		//ATUReports.indexPageDescription = "Service Now Project";
		//ATUReports.setAuthorInfo("Rajkumar",  Utils.getCurrentTime(), "1.0");
	}

	public static String getErrorDescription() {
		return errorDescription;
	}

	private static void setErrorDescription(String errorDescription) {
		Reporter1.errorDescription = errorDescription;
	}

}



