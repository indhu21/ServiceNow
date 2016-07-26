package com.punchit.scripts.sn;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.QueryDB;
import utils.Reporter;
import wrapper.ServiceNowWrappers;


public class SN_ExecuteTestcases extends ServiceNowWrappers {

	@Test
	public void executeTests() throws ClassNotFoundException{
		
		Set<Map<String,String>> testcases = QueryDB.fetchTestcasesToBeExecuted();
		
		for (Map<String, String> map : testcases) {
			    
			    Class testClass = Class.forName(map.get("testcaseid"));
			    Class[] testClasses = {testClass};
			    TestListenerAdapter tla = new TestListenerAdapter();
			    TestNG testng = new TestNG();
			    testng.setTestClasses(testClasses);
			    testng.addListener(tla);
			    testng.setDefaultSuiteName(map.get("runid"));
			    testng.run();			
			
		}

		
	}

}