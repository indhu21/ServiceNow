package testng;

import java.io.IOException;

import org.apache.http.HttpException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import ServiceNow_Integration.DataBase_Interaction;
import utils.QueryDB;
import utils.QueryDB_1;
import utils.Reporter;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class SuiteMethods_demo1 extends ServiceNowWrappers{
	protected String browserName = "chrome";
	protected String entityId;
	protected String status = "FAIL";
	protected ITestContext context;
	protected long startTime;
	
	// Create Instance
	protected ServiceNowWrappers snW;

	@BeforeClass
	public void getBrowserName(ITestContext context) {
		this.context = context;
		startTime = context.getStartDate().getTime();
	    entityId = context.getCurrentXmlTest().getSuite().getName();
		entityId = "7418";
		//snW = new ServiceNowWrappers(entityId);

		//browserName = QueryDB.getBrowserName(entityId);
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
