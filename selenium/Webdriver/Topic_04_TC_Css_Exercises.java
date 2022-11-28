package Webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_TC_Css_Exercises {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS"))
		{
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");	
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Register() {
		driver.get("http://live.techpanda.org/index.php/");
		
		driver.findElement(By.cssSelector("div>a[data-target-element='#header-account']")).click();
		driver.findElement(By.cssSelector("li>a[title='Register']")).click();
		driver.findElement(By.cssSelector("#firstname")).sendKeys("Sun");
		driver.findElement(By.cssSelector("#lastname")).sendKeys("June");
		driver.findElement(By.cssSelector("li input[name='email'][id='email_address']")).sendKeys("sun.june2303@gmail.com");
		driver.findElement(By.cssSelector("div[class='input-box'] #password")).sendKeys("abcd56789");
		driver.findElement(By.cssSelector("div[class='input-box'] #confirmation")).sendKeys("abcd56789");
		driver.findElement(By.cssSelector(".checkbox")).click();
		driver.findElement(By.cssSelector("button[type='submit'][title='Register']")).click();
	}
	
	@Test
	public void TC_02_AddToCart_Mobile() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//a[@class='level0 ' and text()='Mobile']")).click();
		driver.findElement(By.xpath("//li/a[@title='IPhone']/following-sibling::div//button")).click();
		driver.findElement(By.xpath("//a[@class='level0 ' and text()='Mobile']")).click();
		driver.findElement(By.xpath("//li/a[@title='Xperia']/following-sibling::div//button")).click();
		driver.findElement(By.xpath("//a[@class='level0 ' and text()='Mobile']")).click();
		driver.findElement(By.xpath("//li/a[@title='Samsung Galaxy']/following-sibling::div//button")).click();
	}

	@Test
	public void TC_03_AddToCart_TV() {
		driver.findElement(By.xpath("//a[@class='level0 ' and text()='TV']")).click();
		driver.findElement(By.xpath("//h2/a[@title='LG LCD']/parent::h2/following-sibling::div//button")).click();
		driver.findElement(By.xpath("//a[@class='level0 ' and text()='TV']")).click();
		driver.findElement(By.xpath("//h2/a[@title='Samsung LCD']/parent::h2/following-sibling::div//button")).click();
		driver.get("http://live.techpanda.org/index.php/");
	}
	
	@Test
	public void TC_04_Verify_Result_By_Search() {
		driver.findElement(By.cssSelector("#search")).sendKeys("Automation Testing");
		driver.findElement(By.cssSelector(".search-button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='note-msg']")).getText(), "Your search returns no results.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}