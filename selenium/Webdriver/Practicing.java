package Webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Practicing {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String city, state, email;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS"))
		{
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");	
		}
		
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		city = "Washington";
		state = "Floria";
		email = "suncd" + rand.nextInt(99) + "@info.net";
		
	}
	
	//id: mngr456121
	//pass: gUpaheg
	@Test
	public void TC_01_Xuly_TextBox_TextArea() {
		driver.get("https://demo.guru99.com/v4/");
		driver.findElement(By.name("uid")).sendKeys("mngr456121");
		driver.findElement(By.name("password")).sendKeys("gUpaheg");
		driver.findElement(By.name("btnLogin")).click();
		String title = driver.findElement(By.xpath("//a[text()='Demo Site']")).getText();
		System.out.println(title);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Demo Site']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys("SunCD");
		driver.findElement(By.xpath("//input[@name='rad1' and @value='m']")).isSelected();
		driver.findElement(By.name("dob")).sendKeys("11/11/1992");
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("This is an automation testing\nRun by Sun CD");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0909123456");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("missu2303");
		driver.findElement(By.name("sub")).click();
		String IDnum = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		System.out.println(IDnum);
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

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
		//driver.quit();
	}
}