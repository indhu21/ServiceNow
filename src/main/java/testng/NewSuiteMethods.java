package testng;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.QueryDB;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class NewSuiteMethods {
	
	protected String browserName = "firefox";
	protected String entityId;
	protected String status = "FAIL";
	protected int dbStatus = 2;

	protected ITestContext context;
	
	// Create Instance
	protected ServiceNowWrappers snW;

	@BeforeClass
	public void getBrowserName(ITestContext context) {
		this.context = context;
		entityId = context.getCurrentXmlTest().getSuite().getName();
		snW = new ServiceNowWrappers(entityId);

		browserName = QueryDB.getBrowserNameFromNew(entityId);
		browserName = "chrome";
		Reporter.setResult(ServiceNowWrappers.testcaseName);
		Reporter.reportStep("The testcase execution started on :"+snW.getCurrentTime() +".","INFO", false);
			
	}
	
	@AfterClass(alwaysRun = true)
	public void updateResult(ITestContext context) {
		String errorMsg = Reporter.getErrorDescription();
		long executionTime = (System.currentTimeMillis() - context.getStartDate().getTime())/1000;
		if(status.equals("PASS"))
			dbStatus = 1;
		else if(status.equals("FAIL") && errorMsg.trim().equals(""))
			dbStatus = 3;
		else if(status.equals("Insufficient Data"))
			dbStatus = 4;
		else
			dbStatus = 2;

		System.out.println(context.getCurrentXmlTest().getSuite().getName());
		System.out.println(status);
		System.out.println(errorMsg);
		System.out.println(executionTime);
		
		String statusMsg = status;
		if(status.equals("FAIL"))
			statusMsg = "FAIL and the error message is :"+errorMsg;
			
		Reporter.reportStep("The testcase execution completed on :"+snW.getCurrentTime()+". "
				+ "\n The time taken to execute this testcase is :"+executionTime+" seconds. "
						+ "\n The status is: "+statusMsg,"INFO", false);
		
		QueryDB.updateResultNew(entityId, dbStatus,executionTime);
		
		// close the browser
		snW.quitBrowser();
	}

}
