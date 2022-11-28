package Webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_TextArea {
	WebDriver driver;
	Random rand = new Random();
	Actions action; //giả lập chuột + bàn phím
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportNumber = "12345-712-88-2303";
	String comment = "This is generated data\nof real people";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS"))
		{
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");	
		}
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("FC");
		
		WebElement employeeIDTextpox = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		employeeIDTextpox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		sleepInSecond(1);
		employeeIDTextpox.sendKeys(Keys.DELETE);
		sleepInSecond(2);
		employeeIDTextpox.sendKeys(employeeID);
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(6);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("username")).sendKeys("afc" + employeeID);
		driver.findElement(By.name("password")).sendKeys("Password123!!!");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewMyDetails']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
	}

	@Test
	public void TC_02_Verify_Employee() {

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