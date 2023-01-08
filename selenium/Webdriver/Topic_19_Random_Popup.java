package Webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Random_Popup {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	@Test
	public void TC_01_Random_In_DOM_Java_Code_Geek() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(15);
		
		WebElement Popup = driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style*='display:none'])"));
		
		if(Popup.isDisplayed()) {
			System.out.print("Go to Popup");
			driver.findElement(By.cssSelector("input[aria-label='E-mail Field']")).sendKeys(getRandomEmail());
			sleepInSecond(2);
			driver.findElement(By.cssSelector("a.lepopup-button")).click();
			sleepInSecond(2);
		} else {
			System.out.print("Move to look for a book");
		}
		
		driver.findElement(By.cssSelector("input#s")).sendKeys("Automation Testing");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='post-title']/a[text()='Complete Automation Testing – Is It Feasible?']")).isDisplayed());
		
	}

	@Test
	public void TC_02_Fixed_Not_In_DOM_Facebook() {

		
		
	}
	
	@Test
	public void TC_03_Not_In_DOM_Tiki() {
		
	}

	@Test
	public void TC_04_() {

	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getRandomEmail() {
		Random rand = new Random();
		return "sun.auto" + rand.nextInt(99999) + "@mailinator.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}