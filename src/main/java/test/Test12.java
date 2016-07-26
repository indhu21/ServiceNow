package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test12 {

public static void main(String[] args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	ChromeDriver driver = new ChromeDriver();
	
driver.get("https://www.irctc.co.in/eticketing/userSignUp.jsf");
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.findElementById("userRegistrationForm:userName").sendKeys("santhosh");
driver.findElementById("userRegistrationForm:password").sendKeys("santhosh");
driver.findElementById("userRegistrationForm:confpasword").sendKeys("santhosh");
Select question = new Select(driver.findElementById("userRegistrationForm:securityQ"));
question.selectByVisibleText("What is your pet name?");
driver.findElementById("userRegistrationForm:securityAnswer").sendKeys("answer");
Select lang = new Select(driver.findElementById("userRegistrationForm:prelan"));
lang.selectByVisibleText("English");
driver.findElementById("userRegistrationForm:firstName").sendKeys("santhosh");
driver.findElementById("userRegistrationForm:middleName").sendKeys("test");
driver.findElementById("userRegistrationForm:lastName").sendKeys("manickam");
driver.findElementById("userRegistrationForm:gender:0").click();
driver.findElementById("userRegistrationForm:maritalStatus:0").click();
Select day = new Select(driver.findElementById("userRegistrationForm:dobDay"));
day.selectByVisibleText("19");
Select month = new Select(driver.findElementById("userRegistrationForm:dobMonth"));
month.selectByVisibleText("JUL");
Select year = new Select(driver.findElementById("userRegistrationForm:dateOfBirth"));
year.selectByVisibleText("1984");
Select occupation = new Select(driver.findElementById("userRegistrationForm:occupation"));
occupation.selectByVisibleText("Professional");
driver.findElementById("userRegistrationForm:uidno").sendKeys("test");
driver.findElementById("userRegistrationForm:idno").sendKeys("test");
driver.findElementById("userRegistrationForm:email").sendKeys("test@gmail.com");
driver.findElementById("userRegistrationForm:mobile").sendKeys("1234567890");
Select nationality = new Select(driver.findElementById("userRegistrationForm:nationalityId"));
nationality.selectByVisibleText("India");
driver.findElementById("userRegistrationForm:address").sendKeys("test");
driver.findElementById("userRegistrationForm:street").sendKeys("test");
driver.findElementById("userRegistrationForm:area").sendKeys("test");
Select country = new Select(driver.findElementById("userRegistrationForm:countries"));
country.selectByVisibleText("India");
driver.findElementById("userRegistrationForm:pincode").sendKeys("600054",Keys.TAB);
Thread.sleep(7000);
Select city = new Select(driver.findElementById("userRegistrationForm:cityName"));
city.selectByVisibleText("Tiruvallur");
Select po = new Select(driver.findElementById("userRegistrationForm:postofficeName"));
po.selectByVisibleText("Avadi S.O");
driver.findElementById("userRegistrationForm:landline").sendKeys("1234567890");
//driver.close();

}

}