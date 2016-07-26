package testng;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.apache.http.HttpException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.QueryDB;
import utils.QueryDB_ServiceNowFrontEnd;
import utils.Reporter;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class SuiteMethods_ServiceNowFrontEnd extends ServiceNowWrappers{
	protected String browserName = "chrome";
	protected static String entityId;
	protected String status = "FAIL";
	protected ITestContext context;
	long startTime = 0;
	// Create Instance
	protected ServiceNowWrappers snW;

	@BeforeClass
	public void getBrowserName(ITestContext context) {
		this.context = context;
//		entityId = context.getCurrentXmlTest().getSuite().getName();
	    entityId = "1fae7cbd4f4796005bf37d218110c779";
		snW = new ServiceNowWrappers(entityId);
		startTime = context.getStartDate().getTime();
//		browserName = QueryDB_ServiceNowFrontEnd.getBrowserName(entityId);
		browserName="chrome";
		Reporter_ServiceNow.setResult(ServiceNowWrappers.testcaseName);
		
//		Reporter_ServiceNow.reportStep("The testcase execution started on :"+snW.getCurrentTime() +".","INFO", false);

		sStepNumber = 1;
	}
	
	@AfterClass(alwaysRun = true)
	public void updateAndClose(ITestContext context) throws AddressException, IOException, HttpException, InterruptedException, MessagingException {
		updateResult(context);
		
		// close the browser
		snW.quitBrowser();
	}
	
	public void updateResult(ITestContext context) throws AddressException, IOException, HttpException, InterruptedException, MessagingException {
		String errorMsg = Reporter_ServiceNow.getErrorDescription();
		
		long executionTime = (System.currentTimeMillis() - startTime)/1000;
		if(status.equals("FAIL") && errorMsg.trim().equals(""))
			status = "ERROR";
		
		boolean bWarning = false;
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
		
		
		//Babu commented these for getting first PDF page
	/*			Reporter.reportStep("The testcase execution completed on :"+snW.getCurrentTime()+". "
						+ "\n The time taken to execute this testcase is :"+executionTime+" seconds. "
								+ "\n The status is: "+statusMsg,"INFO", false);*/
				
				
				
				// Babu added for getting first PDF page
				Date date = new Date(startTime);
				DateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm:ss aaa");
				String startDate = formatter.format(date);
				String endDate = formatter.format(new Date(System.currentTimeMillis()));
				
				// Babu added for getting first PDF page
				Reporter_ServiceNow.appendFirstPage(QueryDB_ServiceNowFrontEnd.readPDFname(testcaseName), testDescription, startDate, endDate, executionTime, status);
				
				QueryDB.updateResult(entityId, status, errorMsg, executionTime);

	}
	
	public void setEntityId(String entityId) throws AddressException, IOException, HttpException, InterruptedException, MessagingException {
		updateResult(context);
		long startTime = System.currentTimeMillis();
		snW = new ServiceNowWrappers(entityId);
		Reporter_ServiceNow.setResult(ServiceNowWrappers.testcaseName);
		Reporter_ServiceNow.reportStep("The testcase execution started on :"+snW.getCurrentTime() +".","INFO", false);
			
	}

}


