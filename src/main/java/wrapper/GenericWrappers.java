package wrapper;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testng.SuiteMethods;
import utils.QueryDB;
import utils.QueryDB_ServiceNowFrontEnd;
import utils.Reporter;
import utils.Reporter_ServiceNow;


public abstract class GenericWrappers {

	protected static RemoteWebDriver driver;
	protected static int sStepNumber;
	private static String primaryWindowHandle;
	public static String sUrl, OD_Url, sHubUrl,sHubPort;
	public static String sPlatform;
	protected static Properties prop, objRep;

	public GenericWrappers() {

		// Load the property file
		prop = loadProperties("config.properties");
		objRep = loadProperties("object.properties");

		sHubUrl = prop.getProperty("HUB");
		sHubPort = prop.getProperty("PORT");
		sPlatform =prop.getProperty("PLATFORM");

	}
	/*
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Rajkumar
	 * @param url - The url with http or https
	 * 
	 */
	public void launchApp() {
		launchApp("firefox",false);
	}

	/**
	 * This method will launch any browser and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * 
	 * @param browser - Browser of type firefox or chrome or ie
	 * @param url - The url with http or https
	 * @author Rajkumar
	 *  
	 */
	public boolean launchApp(String browser)  {
		return launchApp(browser, false);

	}

	/**
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Rajkumar
	 * @param url - The url with http or https
	 * 
	 */
	public boolean launchApp(String browser, boolean remote) {

		if(browser.equals("ie"))
			browser = "internet explorer";

	/*	StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		for (int i = 0; i < stElements.length; i++) {
			if(stElements[i].getClassName().startsWith("com.punchit.scripts")){
				String url = stElements[i].getClassName().substring(stElements[i].getClassName().lastIndexOf(".")+1).split("_")[0].toUpperCase();
				System.out.println(url);
				sUrl = prop.getProperty(url+"_URL");
				System.out.println(sUrl);
				break;
			}


		}*/


		//		  sUrl=QueryDB.getUrl(SuiteMethods.entityId);
		boolean bReturn = false;
		try {

			DesiredCapabilities dc = new DesiredCapabilities();

			if(browser.equals("internet explorer"))
				dc.setVersion("11");

			dc.setBrowserName(browser);

			if(sPlatform.equalsIgnoreCase("windows"))
				dc.setPlatform(Platform.WINDOWS);
			else if(sPlatform.equalsIgnoreCase("win8"))
				dc.setPlatform(Platform.WIN8);
			else if(sPlatform.equalsIgnoreCase("WIN8_1"))
				dc.setPlatform(Platform.WIN8);
			else if(sPlatform.equalsIgnoreCase("linux"))
				dc.setPlatform(Platform.LINUX);
			else if(sPlatform.equalsIgnoreCase("mac"))
				dc.setPlatform(Platform.MAC);
			else
				dc.setPlatform(Platform.WINDOWS);


			if(remote)
				driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
			else{

				// if the browser is firefox
				if (browser.equals("firefox")) {
					driver = new FirefoxDriver();

				} else if (browser.equals("chrome")) {

					if(sPlatform.startsWith("win")){
						System.setProperty("webdriver.chrome.driver", getAbsolutePath()+"drivers/chromedriver.exe");}
					else if(sPlatform.startsWith("linux")){
						System.setProperty("webdriver.chrome.driver", getAbsolutePath()+"drivers/chromedriver_linux/chromedriver");}
					else if(sPlatform.startsWith("mac")){
						System.setProperty("webdriver.chrome.driver", getAbsolutePath()+"drivers/chromedriver_mac/chromedriver");}


					driver = new ChromeDriver();

				} else if (browser.equals("internet explorer")) {
					System.setProperty("webdriver.ie.driver", getAbsolutePath()+"drivers/IEDriverServer.exe");

					driver = new InternetExplorerDriver();

				} else if (browser.equals("headless")){

					/*	     DesiredCapabilities caps = new DesiredCapabilities();
		     if(sPlatform.startsWith("linux")){
		      caps.setJavascriptEnabled(true);                
		      caps.setCapability("takesScreenshot", true);  
		      caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		                     "./drivers/phantomjs-linux/bin/phantomjs"); }             

		     if(sPlatform.startsWith("win")){
		      caps.setJavascriptEnabled(true);                
		      caps.setCapability("takesScreenshot", true);  
		      caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		       "./drivers/phantomjs-windows/bin/phantomjs.exe"
		       );}
		     if(sPlatform.startsWith("mac")){
		      caps.setJavascriptEnabled(true);                
		      caps.setCapability("takesScreenshot", true);  
		      caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		       "./drivers/phantomjs-macosx/bin/phantomjs"
		       );}


		     driver = new  PhantomJSDriver(caps);*/
				}
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println(sUrl);
			driver.get(sUrl);

			primaryWindowHandle = driver.getWindowHandle();  
			bReturn = true;
			//   Reporter.reportStep("The browser:" + browser + " launched successfully", "SUCCESS");


		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAILURE");
		}
		return bReturn;
	}
	/**
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Rajkumar
	 * @param url - The url with http or https
	 * 
	 */
	public boolean launchApp_ServiceNow(String browser, boolean remote,String entityid) {

		if(browser.equals("ie"))
			browser = "internet explorer";

		String url=QueryDB_ServiceNowFrontEnd.readInstance(entityid);
		sUrl = url;
		System.out.println("URL is "+sUrl);


		boolean bReturn = false;
		try {

			DesiredCapabilities dc = new DesiredCapabilities();

			if(browser.equals("internet explorer"))
				dc.setVersion("11");

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);

			if(remote)
				driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
			else{

				// if the browser is firefox
				if (browser.equals("firefox")) {
					driver = new FirefoxDriver();

				} else if (browser.equals("chrome")) {
					System.setProperty("webdriver.chrome.driver", getAbsolutePath()+"drivers\\chromedriver.exe");

					driver = new ChromeDriver();

				} else if (browser.equals("internet explorer")) {
					System.setProperty("webdriver.ie.driver", getAbsolutePath()+"drivers\\IEDriverServer.exe");

					driver = new InternetExplorerDriver();

				} else if (browser.equals("headless")){
					/*					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setJavascriptEnabled(true);                
					caps.setCapability("takesScreenshot", true);  
					caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
							"drivers\\phantomjs.exe"
							);
					driver = new  PhantomJSDriver(caps);*/
				}
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println(sUrl);
			driver.get(sUrl);

			primaryWindowHandle = driver.getWindowHandle();		
			bReturn = true;
			//Reporter.reportStep("The browser:" + browser + " launched successfully", "SUCCESS");


		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAILURE");
		}
		return bReturn;
	}


	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Rajkumar
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean enterById(String idValue, String data) {
		boolean bReturn = false;
		try {
			driver.findElement(By.id(objRep.getProperty(idValue))).clear();
			driver.findElement(By.id(objRep.getProperty(idValue))).sendKeys(data);	
			bReturn = true;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (WebDriverException e1) {
			e1.printStackTrace();
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using xpath attribute to locate
	 * 
	 * @param xpathVal - xpath of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Rajkumar
	 */
	public boolean enterByXpath(final String xpathVal, String data){
		boolean bReturn = false;
		try{

			driver.findElement(By.xpath(objRep.getProperty(xpathVal))).clear();
			driver.findElement(By.xpath(objRep.getProperty(xpathVal))).sendKeys(data);
			bReturn = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}


	/**
	 * This method will enter the value to the text field using xpath attribute to locate
	 * 
	 * @param xpathVal - xpath of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Rajkumar
	 */
	public boolean enterByXpathAndClick(String xpathVal, String data){
		boolean bReturn = false;
		try{
			driver.findElement(By.xpath(objRep.getProperty(xpathVal))).clear();
			driver.findElement(By.xpath(objRep.getProperty(xpathVal))).sendKeys(data, Keys.ENTER);
			bReturn = true;

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}
	public boolean changeUrlForIM(){
		boolean bReturn = false;
		try{

			/*if(sUrl.contains("/login.do")){
				sUrl=sUrl.replaceAll("/login.do", "/navpage.do");
				driver.get(sUrl);}
			*/	
				driver.get(sUrl);
			bReturn = true;

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using xpath attribute to locate
	 * 
	 * @param xpathVal - xpath of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Rajkumar
	 */

	public boolean enterAndChoose1(String xpathVal, String data){
		boolean bReturn = false;
		try{
			WebElement ele = driver.findElement(By.xpath(objRep.getProperty(xpathVal)));

			ele.clear();

			Actions builder = new Actions(driver);   
			builder.sendKeys(ele, data)
			.pause(15000)
			.sendKeys(Keys.DOWN)
			.sendKeys(Keys.ENTER)
			.build().perform();

			if(!getAttributeByXpath(xpathVal, "title").equals("Invalid reference"))
				bReturn = true;

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {

		}
		return bReturn;
	}

	public boolean enterAndChoose(final String xpathVal, String data){
		boolean bReturn = false;
		try{
			WebElement ele = driver.findElement(By.xpath(objRep.getProperty(xpathVal)));

			ele.clear();	

			Actions builder = new Actions(driver);			
			builder.sendKeys(ele, data)
			.pause(5000)
			.build().perform();

			builder.sendKeys(Keys.DOWN)
			.sendKeys(Keys.ENTER)
			.build().perform();

			(new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.findElement(By.xpath(objRep.getProperty(xpathVal))).getAttribute("title").length() == 0;
				}
			});

			if(!getAttributeByXpath(xpathVal, "title").equals("Invalid reference"))
				bReturn = true;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (WebDriverException e1) {
			e1.printStackTrace();

		}
		return bReturn;
	}
	/**
	 * This method will click by class name 
	 * @param classValue - class locator 
	 * @author Rajkumar
	 */
	public boolean clickByClassName(String classValue){
		boolean bReturn = false;
		try {
			driver.findElement(By.className(objRep.getProperty(classValue))).click();
			bReturn = true;

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {

		}
		return bReturn;
	}

	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Rajkumar
	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title))
				bReturn = true;
		}catch(Exception e){

		}

		return bReturn;
	}

	// Close browser
	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//Reporter.reportStep("The browser closed successfully", "SUCCESS");

	}

	public boolean clickLink(String linkName) {
		return clickLink(linkName,true);

	}

	public boolean clickLink(String linkName, boolean bObjRep) {
		boolean bReturn = false;
		String loc = linkName;
		try{
			if(bObjRep)
				loc = objRep.getProperty(linkName);				

			driver.findElement(By.linkText(loc)).click();
			bReturn = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (WebDriverException e1) {
			e1.printStackTrace();

		}
		return bReturn;

	}

	public boolean clickPartialLink(String linkName) {
		boolean bReturn = false;
		try{
			driver.findElement(By.partialLinkText(objRep.getProperty(linkName))).click();
			bReturn = true;

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;

	}

	public boolean clickById(String id) {
		boolean bReturn = false;
		try{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(objRep.getProperty(id)))));

			driver.findElement(By.id(objRep.getProperty(id))).click();
			bReturn = true;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (WebDriverException e1) {
			e1.printStackTrace();
		}
		return bReturn;
	}

	public boolean clickByName(String name) {
		boolean bReturn = false;
		try{
			driver.findElement(By.name(objRep.getProperty(name))).click();
			bReturn = true;

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	// Click by xpath
	public boolean clickByXpath(String xpathVal) {
		boolean bReturn = false;
		try{

			//			WebDriverWait wait = new WebDriverWait(driver, 30);
			//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathVal)));

			driver.findElement(By.xpath(objRep.getProperty(xpathVal))).click();
			bReturn = true;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (WebDriverException e1) {
			e1.printStackTrace();
		}
		return bReturn;
	}

	// Click by css
	public boolean clickByCss(String css) {
		boolean bReturn = false;
		try{
			driver.findElement(By.cssSelector(objRep.getProperty(css))).click();
			bReturn = true;

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	// Select visible text by xpath
	public boolean selectByVisibleTextByXpath(String xpathVal, String val) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.xpath(objRep.getProperty(xpathVal)))).selectByVisibleText(val);
			bReturn = true;
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	public boolean selectByVisibleTextById(String id, String val) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.id(objRep.getProperty(id)))).selectByVisibleText(val);
			bReturn = true;
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	public String getDefaultValueById(String id) {
		String retValue = "";
		try{
			retValue = getDefaultValue(driver.findElement(By.id(objRep.getProperty(id))));
			System.out.println(retValue);
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return retValue;
	}

	public String getDefaultValueByXpath(String xpath) {
		String retValue = "";
		try{
			retValue = getDefaultValue(driver.findElement(By.xpath(objRep.getProperty(xpath))));
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return retValue;
	}

	public String getDefaultValue(WebElement ele) { 
		String retValue = "";
		try{
			retValue = new Select(ele).getFirstSelectedOption().getText();
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return retValue;
	}

	public List<String> getOptionsById(String id) {
		return getOptions(driver.findElement(By.id(objRep.getProperty(id))));
	}

	public List<String> getOptionsByXpath(String xpath) {

		return getOptions(driver.findElement(By.xpath(objRep.getProperty(xpath))));
	}

	public List<String> getOptions(WebElement ele) {
		List<String> retValues = new ArrayList<>();
		try{
			Wait(10000);
			List<WebElement> options = new Select(ele).getOptions();	

			for (WebElement optionEle : options) {
				retValues.add(optionEle.getText());
			}
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return retValues;
	}

	// switch to the frame until it is available within timeout
	public boolean switchToFrame(String nameOrId) {
		boolean bReturn = false;
		try {
			driver.switchTo().defaultContent();

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(objRep.getProperty(nameOrId)));
			bReturn = true;
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	public WebDriver goOutOfFrame() {
		return driver.switchTo().defaultContent();
	}

	// instead pass an argument for number of window to be switched...
	// have a counter to check the nth window switched 
	public boolean switchToSecondWindow() {


		boolean bReturn = false;
		try {
			Set<String> winHandles = driver.getWindowHandles();

			for (String winHandle : winHandles) {
				//				resetImplicitWait(7);
				driver.switchTo().window(winHandle).manage().window().maximize();

				//System.out.println(winHandle);
				bReturn = true;
				//				resetImplicitWait(5);
			}
		} catch (Exception e) {

		}

		return bReturn;

	}



	public boolean switchToPrimary() {
		boolean bReturn = false;
		try {
			resetImplicitWait(5);
			driver.switchTo().window(primaryWindowHandle);
			resetImplicitWait(30);
		} catch (Exception e) {

		}
		return bReturn;
	}

	public String getAttributeById(String id, String attribute) {
		return getAttributeById(id, attribute, true);
	}

	public String getAttributeByXpath(String xpathVal, String attribute) {
		String attrVal = "";
		try{
			attrVal = driver.findElement(By.xpath(objRep.getProperty(xpathVal))).getAttribute(attribute);
		}catch(Exception e){

		}
		return attrVal;
	}

	public String getAttributeById(String id, String attribute, boolean bObjRep) {
		String attrVal = "";
		try {
			String loc = id;
			if(bObjRep)
				loc = objRep.getProperty(id);
			attrVal = driver.findElement(By.id(loc)).getAttribute(attribute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attrVal;
	}

	public RemoteWebDriver getDriver(){
		return driver;
	}

	public void Wait(long waitTime){
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean alertAccept(){
		boolean bReturn = false;

		try {
			driver.switchTo().alert().accept();
			bReturn = true;
		} catch (Exception e) {

		}
		return bReturn;

	}

	public boolean isAlertPresent() 
	{ 
		try 
		{ 
			driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	}   
	public String getTextAndAcceptAlert(){
		String sText = "";
		try{
			sText = driver.switchTo().alert().getText();
			alertAccept();
		} catch(Exception e){

		}
		return sText;
	}

	public boolean switchToDefault() {
		boolean bReturn = false;
		try {
			driver.switchTo().defaultContent();	
			bReturn = true;
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	public boolean sendKeys(String xpathVal, Keys keyName) {
		boolean bReturn = false;
		try {
			Actions builder = new Actions(driver);
			builder.contextClick(driver.findElement(By.xpath(objRep.getProperty(xpathVal)))).pause(10000)
			.sendKeys(keyName)
			.build().perform();	
			bReturn = true;
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	public int getCountOfElementsByXpath(String xpathVal){
		return getCountOfElementsByXpath(xpathVal, true);
	}

	public int getCountOfElementsByXpath(String xpathVal, boolean bObjRep){
		int count = 0;
		try {
			if(bObjRep)
				count = getCountOfElements(driver.findElementsByXPath(objRep.getProperty(xpathVal)));
			else
				count = getCountOfElements(driver.findElementsByXPath(xpathVal));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	public int getCountOfElementsById(String id){
		return getCountOfElements(driver.findElementsById(objRep.getProperty(id)));
	}

	public int getCountOfElements(List<WebElement> eles){
		return eles.size();
	}

	public List<WebElement> getAllElementsByXpath(String xpathVal){
		List<WebElement> eles = null;
		try {
			System.out.println();
			eles = driver.findElementsByXPath(objRep.getProperty(xpathVal));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eles;
	}

	// Select visible text by xpath
	public String getTextByXpath(String xpathVal) {
		String sText = "";
		try{
			sText = getText(driver.findElement(By.xpath(objRep.getProperty(xpathVal))));

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}	
		return sText;

	}

	// Select visible text by xpath
	public String getTextById(String id) {
		String sText = "";
		try{
			sText = getText(driver.findElement(By.id(objRep.getProperty(id))));

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}	
		return sText;

	}

	public String getText(WebElement ele) {
		String sText = "";
		try{
			sText = ele.getText();

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}	
		return sText;

	}

	// Select visible text by xpath
	public boolean verifyTextByXpath(String xpathVal, String text) {
		boolean bReturn = false;
		try {
			System.out.println(getTextByXpath(xpathVal));
			if(text.trim().equals(getTextByXpath(xpathVal))){
				bReturn = true;
			}
		} catch (Exception e) {
		}
		return bReturn;

	}

	public boolean rightClickById(String id){	
		boolean bClick = false;
		try {
			bClick = rightClick(driver.findElement(By.id(objRep.getProperty(id))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bClick;

	}

	public boolean rightClickByLinkText(String linkName){	
		boolean bClick = false;
		try {
			bClick = rightClickByLinkText(linkName, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bClick;

	}

	public boolean rightClickByLinkText(String linkName, Boolean bObjRep){	
		boolean bClick = false;
		try {	

			if(bObjRep)
				bClick = rightClick(driver.findElement(By.linkText(objRep.getProperty(linkName))));
			else
				bClick = rightClick(driver.findElement(By.linkText(linkName)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bClick;
	}

	public boolean rightClickByXpath(String xpathVal){	
		boolean bClick = false;
		try {
			bClick = rightClick(driver.findElement(By.xpath(objRep.getProperty(xpathVal))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bClick;
	}

	public boolean rightClick(WebElement ele){	
		boolean bReturn = false;
		try{
			// Right click and save	
			Actions builder = new Actions(driver);
			builder.contextClick(ele).pause(4000)
			.build().perform();	
			bReturn = true;
		}catch(Exception e){

		}

		return bReturn;
	}

	public boolean IsElementNotPresentByXpath(String xpathVal){	
		boolean bReturn = false;

		resetImplicitWait(30);

		if(getCountOfElementsByXpath(xpathVal) == 0)
			bReturn = true;

		resetImplicitWait(30);
		return bReturn;
	}

	public boolean IsElementNotPresentById(String id){	
		boolean bReturn = false;

		resetImplicitWait(5);

		if(getCountOfElementsById(id) == 0)
			bReturn = true;

		resetImplicitWait(30);

		return bReturn;
	}

	public boolean IsElementPresentByXpath(String xpathVal){	
		return !IsElementNotPresentByXpath(xpathVal);
	}

	public boolean IsElementPresentById(String id){	
		return !IsElementNotPresentById(id);
	}

	public Properties loadProperties(String fileName){
		// Load the property file
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(getAbsolutePath()+fileName));
		} catch (FileNotFoundException e) {
			Reporter.reportStep("The property file:"+fileName+" is not found", "FAILURE");
		} catch (IOException e) {
			Reporter.reportStep("The property file:"+fileName+" could not loaded", "FAILURE");
		}	
		return prop;
	}

	public void resetImplicitWait(long wait){
		driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
	}

	public boolean isExistById(String id) {
		return isExistById(id, true);
	}

	public boolean isExistByXpath(String xpathVal){
		return isExistByXpath(xpathVal, true);

	}

	public boolean isExistById(String id, boolean bObjRep) {
		boolean bReturn = false;
		try{

			if(bObjRep)
				bReturn = isExist(driver.findElementById(objRep.getProperty(id)));
			else
				bReturn = isExist(driver.findElementById(id));

		} catch (NoSuchElementException e) {
			//			e.printStackTrace();
		} catch (WebDriverException e) {
			//			e.printStackTrace();
		}
		return bReturn; 
	}

	public boolean isExistByXpath(String xpathVal, boolean bObjRep){
		boolean bReturn = false;
		try{
			if(bObjRep)
				bReturn = isExist(driver.findElementByXPath(objRep.getProperty(xpathVal)));
			else
				bReturn = isExist(driver.findElementByXPath(xpathVal));
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
		} catch (WebDriverException e) {
			//e.printStackTrace();
		}
		return bReturn; 

	}

	public boolean isExist(WebElement ele) {

		boolean bFound = false;
		try {


			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));
			bFound = ele.isDisplayed();
			bFound = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return bFound;
	}

	public boolean isEnabledById(String id) {
		return isEnabled(driver.findElementById(objRep.getProperty(id)));
	}

	public boolean isEnabledByXpath(String xpathVal){
		return isEnabled(driver.findElementByXPath(objRep.getProperty(xpathVal)));

	}

	public boolean isEnabled(WebElement ele) {
		boolean bEnabled = false;
		try {
			bEnabled = ele.isEnabled();
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bEnabled;
	}

	public boolean scrollToElementById(String id){
		boolean bFound = false;
		try {
			bFound = scrollToElement(driver.findElement(By.id(objRep.getProperty(id))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFound;
	}

	public boolean scrollToElementByXpath(String xpathVal){
		boolean bFound = false;
		try {
			bFound = scrollToElement(driver.findElement(By.xpath(objRep.getProperty(xpathVal))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFound;
	}

	public boolean scrollToElement(WebElement ele){
		boolean bFound = false;
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			bFound = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFound;
	}

	public boolean scrollToElementById(String id, int x, int y){
		boolean bFound = false;
		try {
			bFound = scrollToElement(driver.findElement(By.id(objRep.getProperty(id))),x,y);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFound;
	}

	public boolean scrollToElementByXpath(String xpathVal, int x, int y){
		boolean bFound = false;
		try {
			bFound = scrollToElement(driver.findElement(By.xpath(objRep.getProperty(xpathVal))),x, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFound;
	}

	public boolean scrollToElement(WebElement ele, int x, int y){
		boolean bFound = false;
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("window.scrollBy("+x+","+y+")", "");
			bFound = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFound;
	}

	// Verify Attribute text by xpath
	public boolean verifyAttributeTextByXpath(String xpathVal,String attribute, String attrValue) {
		boolean bReturn = false;
		try {
			if(attrValue.trim().equals(getAttributeByXpath(xpathVal, attribute))){
				bReturn = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bReturn;

	}

	// Verify  Attribute text by Id
	public boolean verifyAttributeTextById(String xpathVal,String attribute, String attrValue) {
		boolean bReturn = false;
		try {
			if(attrValue.trim().equals(getAttributeById(xpathVal, attribute))){
				bReturn = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bReturn;

	}

	public boolean refresh() {
		boolean bReturn = false;
		try {
			driver.navigate().refresh();
			bReturn = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bReturn;
	}

	public boolean mouseOverById(String id) {
		boolean bMouseOver = false;
		try {
			bMouseOver = mouseOver(driver.findElementById(objRep.getProperty(id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bMouseOver;
	}

	public boolean mouseOverByXpath(String xpathVal){
		boolean bMouseOver = false;
		try {
			bMouseOver = mouseOver(driver.findElementByXPath(objRep.getProperty(xpathVal)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bMouseOver;

	}

	public boolean mouseOver(WebElement ele) {
		boolean bMouseOver = false;
		try {
			new Actions(driver).moveToElement(ele).build().perform();
			bMouseOver = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bMouseOver;
	}

	public boolean pressKey(Keys key){
		boolean bPressKey = false;
		try {
			new Actions(driver).sendKeys(key).build().perform();
			bPressKey = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bPressKey;
	}

	public boolean doubleCick(WebElement ele){
		boolean bPressKey = false;
		try {
			new Actions(driver).doubleClick(ele).build().perform();
			bPressKey = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bPressKey;
	}

	public boolean doubleCickById(String id){
		boolean bPressKey = false;
		try {
			bPressKey = doubleCick(driver.findElement(By.id(objRep.getProperty(id))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bPressKey;
	}


	public boolean doubleCickByXpath(String xpathval){
		boolean bPressKey = false;
		try {
			bPressKey = doubleCick(driver.findElement(By.xpath(objRep.getProperty(xpathval))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bPressKey;
	}

	public String getFirstSelectedValue(WebElement ele){
		String firstSelected = "";
		try {
			firstSelected = new Select(ele).getFirstSelectedOption().getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return firstSelected;
	}

	public String getFirstSelectedValueById(String id){
		String firstSelected = "";
		try {
			firstSelected = getFirstSelectedValue(driver.findElement(By.id(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return firstSelected;
	}

	public String getFirstSelectedValueByXpath(String xpathval){
		String firstSelected = "";
		try {
			firstSelected = getFirstSelectedValue(driver.findElement(By.xpath(xpathval)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return firstSelected;
	}

	public String getBackgroundColor(WebElement ele){
		return ele.getCssValue("background-color");
	}

	public String getBackgroundColorById(String id){
		return getBackgroundColor(driver.findElementById(objRep.getProperty(id)));
	}

	public String getBackgroundColorByXpath(String xpath){
		return getBackgroundColor(driver.findElementByXPath(objRep.getProperty(xpath)));
	}

	public String getCurrentTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy h:mm:ss");
		return sdf.format(date);
	}

	public String convertStringArrayToString(String[] strg){
		String str = "";
		try {
			StringBuilder builder = new StringBuilder();
			for(String s : strg) {
				builder.append(s).append(",");
			}
			str = builder.substring(0,builder.length()-1).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public static String getAbsolutePath(){


		return System.getProperty("user.dir")+"/";
		//		System.out.println(GenericWrappers.class.getProtectionDomain().getCodeSource().getLocation().getFile().replace("%20"," ")+"../");
		//		return GenericWrappers.class.getProtectionDomain().getCodeSource().getLocation().getFile().replace("%20"," ")+"../";
	}

	/*
	public void scrollPageDown(){
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.CONTROL).sendKeys(Keys.END).build().perform();
	}

	 */

	public void PresEnter()
	{
		Actions action = new Actions(driver);        
		action.sendKeys(Keys.ENTER).build().perform();
	}
	/**Select the first value from DropDown
	 * 
	 * @author Aman
	 * @throws InterruptedException 
	 *             
	 */
	public boolean SelectDropown_byXpath(String xpath) throws InterruptedException
	{
		try {
			WebElement select=driver.findElementByXPath(objRep.getProperty(xpath));        
			Thread.sleep(1000);
			select.click();
			Thread.sleep(2000);
			Actions action=new Actions(driver); 
			action.sendKeys(Keys.DOWN).build().perform(); 
			action.sendKeys(Keys.ENTER).build().perform(); 
			Thread.sleep(2000);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public void ActionsDriver(String keys){

		Actions action = new Actions(driver);
		action.sendKeys(Keys.valueOf(keys))
		.build().perform();

	}
	public boolean sendKey(String key){
		boolean bPressKey = false;
		try {
			new Actions(driver).sendKeys(key).build().perform();
			bPressKey = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bPressKey;
	}
	public boolean VerifyByLink(String linkName, boolean bObjRep) {
		boolean bReturn = false;
		String loc = linkName;
		try{
			if(bObjRep)
				//loc = objRep.getProperty(linkName);    

				driver.findElement(By.linkText(loc));
			bReturn = true;
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;

	}

	public String getTextAlert(){
		String sText = "";
		try{
			sText = driver.switchTo().alert().getText();
		} catch(Exception e){

		}
		return sText;
	}


	//	public String getTextAlert(){
	//		String sText = "";
	//		try{
	//			sText = driver.switchTo().alert().getText();
	//		} catch(Exception e){
	//
	//		}
	//		return sText;
	//	}

	public boolean switchToFrame(String nameOrId, long timeOut) {
		boolean bReturn = false;
		try {
			driver.switchTo().defaultContent();

			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(objRep.getProperty(nameOrId)));
			bReturn = true;
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	public String getTextByXpath(String xpathVal ,boolean bObjRep) {

		String sText = "";
		try{
			if(!bObjRep){
				WebDriverWait wait=new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(xpathVal), 0));
				sText = getText(driver.findElement(By.xpath(xpathVal)));
				System.out.println(sText);
			}
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}	
		return sText;

	}

	public List<WebElement> getAllElementByXpath(String xpathVal){
		List<WebElement> eles = null;
		try {
			eles = driver.findElementsByXPath(xpathVal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eles;
	} 


	public boolean scrollToElementByXpath(String xpathVal, boolean bObjRep){
		boolean bFound = false;
		try {
			bFound = scrollToElement(driver.findElement(By.xpath(xpathVal)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFound;
	}

	public boolean clickAndHold(String xpathVal) {
		boolean bReturn = false;
		try {
			Actions builder = new Actions(driver);
			builder.clickAndHold(driver.findElement(By.xpath(objRep.getProperty(xpathVal))))
			.build().perform();	
			bReturn = true;
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}
	public boolean isExistByLinkText(String linkText, boolean bObjRep){
		boolean bReturn = false;
		try{
			if(bObjRep)
				bReturn = isExist(driver.findElementByLinkText((objRep.getProperty(linkText))));
			else
				bReturn = isExist(driver.findElementByLinkText(linkText));
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
		} catch (WebDriverException e) {
			//e.printStackTrace();
		}
		return bReturn; 

	}

	public void web(String xpathVal, String data) {
		Actions builder = new Actions(driver);   
		WebElement ele = driver.findElement(By.xpath(objRep.getProperty(xpathVal)));

		builder.sendKeys(ele, data)
		.pause(15000)
		.sendKeys(Keys.DOWN)
		.sendKeys(Keys.ENTER)
		.build().perform();

	}

	public boolean doubleCickByXpath(String xpathval, boolean bObjRep){

		boolean bPressKey = false;
		try {
			if(!bObjRep)
				bPressKey = doubleCick(driver.findElement(By.xpath(xpathval)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bPressKey;
	}
	public boolean clickByXpath(String xpathVal, boolean bObjRep) {
		boolean bReturn = false;
		try{
			if(!bObjRep){

				driver.findElement(By.xpath(xpathVal)).click();
				bReturn = true;}

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}

	public void waitUntillValueBecomeNotNull(final String xpathVal) {

		(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath(objRep.getProperty(xpathVal))).getAttribute("value").length() != 0;
			}

		});

	}
	public void waitUntillElementTobeVisible(final String xpathVal) {

		try{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(objRep.getProperty(xpathVal)))));
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}

	}

	// Select visible text by xpath
	public boolean selectByValueTextByXpath(String xpathVal, String val) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.xpath(objRep.getProperty(xpathVal)))).selectByValue(val);
			bReturn = true;
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		return bReturn;
	}
	public List<WebElement> getAllElementsByXpath(String xpathVal, boolean bObject){
		List<WebElement> eles = null;
		try {
			eles = driver.findElementsByXPath(xpathVal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eles;
	}
	public void waitUntillValueBecomeNotNull(final String xpathVal, boolean bObject) {

		if(!bObject)
			(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.findElement(By.xpath(xpathVal)).getText() .length()!= 0;
				}

			});
	}
	public boolean clickUsingActions(String xPath){	
		boolean bReturn = false;
		try{
			// Right click and save	
			Actions builder = new Actions(driver);
			builder.click(driver.findElement(By.xpath((objRep.getProperty(xPath)))))
			.build().perform();	
			bReturn = true;
		}catch(Exception e){

		}

		return bReturn;
	}
	public static Date addDays(Date date, int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DATE, days);//minus number would decrement the days
		return cal.getTime();
	}
	public String increamentOneDay() throws ParseException {

		String sourceDate = getCurrentTime();
		//		System.out.println(sourceDate);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		Date myDate = format.parse(sourceDate);
		//		System.out.println(myDate);
		myDate = GenericWrappers.addDays(myDate, 1);
		//		System.out.println(myDate);
		//		System.out.println(format.format(myDate).toString());
		return format.format(myDate).toString();
	}

	public String increamentnNumberofDay(int days) throws ParseException {

		String sourceDate = getCurrentTime();
		System.out.println(sourceDate);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		Date myDate = format.parse(sourceDate);
		System.out.println(myDate);
		myDate = GenericWrappers.addDays(myDate, days);
		System.out.println(myDate);
		System.out.println(format.format(myDate).toString());
		return format.format(myDate).toString();
	}
	public String increamentnNumberofDay(String sourceDate, int days) throws ParseException {

		//		String sourceDate = getCurrentTime();
		System.out.println(sourceDate);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		Date myDate = format.parse(sourceDate);
		System.out.println(myDate);
		myDate = GenericWrappers.addDays(myDate, days);
		System.out.println(myDate);
		System.out.println(format.format(myDate).toString());
		return format.format(myDate).toString();
	}

	public void enterByIdAndTab(String id, String data) {
		try{
			Actions builder = new Actions(driver);   
			WebElement ele = driver.findElement(By.id(objRep.getProperty(id)));  
			ele.clear();
			builder.sendKeys(ele, data)
			.pause(2000)
			.sendKeys(Keys.TAB)
			.build().perform();
		}
		catch(Exception e){

		}
	}

	public void enterByIdAndEnter(String id, String data) {
		try{
			Actions builder = new Actions(driver);   
			WebElement ele = driver.findElement(By.id(objRep.getProperty(id)));
			ele.clear();
			builder.sendKeys(ele, data)
			.pause(2000)
			.sendKeys(Keys.ENTER)
			.build().perform();
		}			
		catch(Exception e){

		}
	}

	public boolean rightClickByXpath(String xpathVal, boolean bObject){	
		boolean bClick = false;
		try {
			if(bObject)
				bClick = rightClick(driver.findElement(By.xpath(objRep.getProperty(xpathVal))));
			else
				bClick = rightClick(driver.findElement(By.xpath(xpathVal)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bClick;
	}
	public boolean enterByXpathAndTab(String xpath, String data) {
		boolean bClick = false;
		try{
			Actions builder = new Actions(driver);   
			WebElement ele = driver.findElement(By.xpath(objRep.getProperty(xpath)));
			ele.clear();
			builder.sendKeys(ele, data)
			.pause(2000)
			.sendKeys(Keys.TAB)
			.build().perform();
			bClick = true;
		}
		catch(Exception e){

		}
		return bClick; 
	}

	/*public boolean isExistElementAtAnyFrame(String xpathVal) {
	boolean bReturn = false;
	try {

		switchToDefault();
		switchToFrame("Frame_Main");
		List<WebElement> frames = driver.findElementsByTagName("iframe");
		System.out.println(frames.size());
		System.out.println(frames.get(0).getAttribute("src"));
		System.out.println(frames.get(1).getAttribute("src"));
		for (WebElement frame : frames) {
			switchToDefault();
			switchToFrame("Frame_Main");
//			driver.switchTo().frame("gsft_main");
			WebDriverWait wait = new WebDriverWait(driver, 180);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));	
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if(driver.findElementsByXPath(objRep.getProperty(xpathVal)).size() > 0){
				bReturn = true;
				break;
			}

		}
	} catch (WebDriverException e1) {
		e1.printStackTrace();
	}
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	return bReturn;
}*/

	public boolean isExistElementAtAnyFrame(String xpathVal) {
		boolean bReturn = false;
		try {

			switchToDefault();
			switchToFrame("Frame_Main");
			List<WebElement> frames = driver.findElementsByTagName("iframe");
			/*  System.out.println(frames.size());
	  System.out.println(frames.get(0).getAttribute("src"));
	  System.out.println(frames.get(1).getAttribute("src"));
			 */  for (WebElement frame : frames) {
				 switchToDefault();
				 switchToFrame("Frame_Main");
				 //   driver.switchTo().frame("gsft_main");
				 WebDriverWait wait = new WebDriverWait(driver, 30);
				 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame)); 
				 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				 if(driver.findElementsByXPath(objRep.getProperty(xpathVal)).size() > 0){
					 scrollToElementByXpath(xpathVal);
					 bReturn = true;
					 break;
				 }

			 }
		} catch (WebDriverException e1) {
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return bReturn;
	}
	public boolean compareMorethanTimes(String actualTime, String frominstance) throws ParseException {

		boolean bReturn = false;
		actualTime="00 00 0000 "+actualTime;
		frominstance="00 00 0000 "+frominstance;

		System.out.println(frominstance);
		System.out.println(actualTime);
		 SimpleDateFormat parser = new SimpleDateFormat("dd mm yyy HH:mm:ss");
			Date date1 = parser.parse(frominstance);
			Date date2 = parser.parse(actualTime);
		try {

		    if(date1.after(date2)||date2.equals(date1))
		    	bReturn=true;
		    
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		return bReturn;

	}
	public boolean compareLessthanTimes(String actualTime, String frominstance) throws ParseException {

		boolean bReturn = false;
		actualTime="00 00 0000 "+actualTime;
		frominstance="00 00 0000 "+frominstance;

		System.out.println(frominstance);
		System.out.println(actualTime);
		 SimpleDateFormat parser = new SimpleDateFormat("dd mm yyy HH:mm:ss");
			Date date1 = parser.parse(frominstance);
			Date date2 = parser.parse(actualTime);
		try {

		    if(date1.before(date2)||date2.equals(date1))
		    	bReturn=true;
		    
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		return bReturn;

	}
	
	public boolean changeUrlForIMFornaNavigation(){
		  boolean bReturn = false;
		   try{
		   
			   System.out.println(sUrl);
		    if(sUrl.contains("/login.do")){
		    	String cUrl=sUrl.replaceAll("/login.do", "/navpage.do");
		    	driver.get(cUrl);
		    	}
		    else{
		    	driver.get(sUrl);}
		    
		    bReturn = true;

		   } catch (NoSuchElementException e) {
		   } catch (WebDriverException e1) {
		   }
		   return bReturn;
		 }

	public boolean switchToperticularWindow(int indexofWindow) {

		int i=0;
		boolean bReturn = false;
		try {
			Set<String> winHandles = driver.getWindowHandles();

			for (String winHandle : winHandles) {
				driver.switchTo().window(winHandle).manage().window().maximize();
				i=i+1;	
				if(i==indexofWindow){	
					bReturn = true;
					break;
				}
			}
		} catch (Exception e) {

		}

		return bReturn;

	}

}





