package testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.QueryDB;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class SuiteMethods_Indhu extends ServiceNowWrappers {
	
	protected String browserName = "chrome";
	public static String entityId;
	protected String status = "FAIL";
	protected static boolean bWarning = false;
	protected ITestContext context;
	protected long startTime;
	
	// Create Instance
	protected ServiceNowWrappers snW;

	@BeforeClass
	public void getBrowserName(ITestContext context) {
		bWarning = false;
		this.context = context;
		startTime = context.getStartDate().getTime();
//	    entityId = context.getCurrentXmlTest().getSuite().getName();
		entityId = "10353";
		snW = new ServiceNowWrappers(entityId);

		browserName = QueryDB.getBrowserName(entityId);
		browserName = "chrome";
		Reporter.setResult(ServiceNowWrappers.testcaseName);
		
		Reporter.reportStep("The testcase execution started on :"+snW.getCurrentTime() +".","INFO", false);
		
		sStepNumber = 1;
			
	}
	
	@AfterClass(alwaysRun = true)
	public void updateAndClose(ITestContext context) {
		updateResult(context);
		
		// close the browser
		snW.quitBrowser();
	}
	
	public void updateResult(ITestContext context) {
		String errorMsg = Reporter.getErrorDescription();
		long executionTime = (System.currentTimeMillis() - startTime)/1000;
		if(status.equals("FAIL") && errorMsg.trim().equals(""))
			status = "ERROR";
		
		if(bWarning)
			status = "PARTIALLY PASSED";
		if(errorMsg.contains("Insufficient Data"))
			   status = "INSUFFICIENT DATA";
		
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
		
		
		QueryDB.updateResult(entityId, status, errorMsg, executionTime);

	}
	
	public void setEntityId(String entityId) {
		updateResult(context);
		startTime = System.currentTimeMillis();
		snW = new ServiceNowWrappers(entityId);
		Reporter.setResult(ServiceNowWrappers.testcaseName);
		Reporter.reportStep("The testcase execution started on :"+snW.getCurrentTime() +".","INFO", false);
			
	}

}
