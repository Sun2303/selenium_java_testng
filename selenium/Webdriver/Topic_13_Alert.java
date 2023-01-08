package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS"))
		{
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");	
		}
		
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
		//Wait và switch qua luôn
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		//Accept cái alert
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		String textAlert = "Sun Automation";
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(textAlert);
		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + textAlert);
	}
	
	//@Test
	public void TC_04_Authentication_Alert_I() {
		driver.get("http://the-internet.herokuapp.com/");
		
		String authenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(passUserAndPassToUrl(authenUrl, "admin", "admin"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public void TC_04_Authentication_Alert_II() {
	
		
	}
	
	public String passUserAndPassToUrl(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return url = arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}