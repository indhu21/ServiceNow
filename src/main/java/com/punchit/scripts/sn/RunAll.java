package com.punchit.scripts.sn;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;


public class RunAll extends SuiteMethods {

	public static void main(String[] args) {
		
		fetchTestcases();		
		executeTestcases();
		updateTestcases();
	    
	}
	
	public static void fetchTestcases(){
		
		TestListenerAdapter tla = new TestListenerAdapter();
	    TestNG testng = new TestNG();
	    testng.setTestClasses(new Class[] {SN_FetchTestcases.class});
	    testng.addListener(tla);
	    testng.run();
	    
	}
	
	public static void executeTestcases(){
		
		TestListenerAdapter tla = new TestListenerAdapter();
	    TestNG testng = new TestNG();
	    testng.setTestClasses(new Class[] {SN_ExecuteTestcases.class});
	    testng.addListener(tla);
	    testng.run();
	    
	}

	public static void updateTestcases(){
	
	TestListenerAdapter tla = new TestListenerAdapter();
    TestNG testng = new TestNG();
    testng.setTestClasses(new Class[] {SN_UpdateTestcases.class});
    testng.addListener(tla);
    testng.run();
    
}

}