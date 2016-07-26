package ServiceNow_Integration;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.QueryDB;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class suitemethods {
	
	protected String browserName = "firefox";
	protected String entityId;
	protected String status = "FAIL";
	protected ITestContext context;
	
	// Create Instance
	protected ServiceNowWrappers snW;

	@BeforeClass
	public void getBrowserName(ITestContext context) {
		this.context = context;
		//entityId = context.getCurrentXmlTest().getSuite().getName();
		entityId = "bb254b91b0730e40b74a113014848f77";
		snW = new ServiceNowWrappers(entityId);

		browserName = DataBase_Interaction.getBrowserName(entityId);
		Reporter.setResult(ServiceNowWrappers.testcaseName);
		Reporter.reportStep("The testcase execution started on :"+snW.getCurrentTime() +".","INFO", false);
			
	}
	@AfterClass(alwaysRun = true)
	public void updateResult(ITestContext context) {
		String errorMsg = Reporter.getErrorDescription();
		long executionTime = (System.currentTimeMillis() - context.getStartDate().getTime())/1000;
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
		
		QueryDB.updateResult(entityId, status,errorMsg, executionTime);
		
		// close the browser
		snW.quitBrowser();
	
	}
}
