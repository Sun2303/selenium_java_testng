package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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
		
		//Mở trang Register
		driver.get("http://demo.nopcommerce.com/register");
	}

	@Test
	public void TC_01_ID() {
		// Thao tác lên Element thì đầu tiên phải tìm được element đó: findElement
		// Find theo cái gì: id/class/name/css/xpath/...
		// Find thấy element rồi thì action lên nó: click/sendKey/...
		driver.findElement(By.id("FirstName")).sendKeys("Automation Testing");
	}

	@Test
	public void TC_02_Class() {
		// Mở màn hình search
		driver.get("http://demo.nopcommerce.com/search");
		driver.findElement(By.className("search-text")).sendKeys("Macbook Pro M1");
	}

	@Test
	public void TC_03_Name() {
		// Click vào Advance Search checkbox
		driver.findElement(By.name("advs")).click();
	}
	
	@Test
	public void TC_04_TagName() {
		// Tìm ra bao nhiêu thẻ input trên màn hình hiện tại
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	
	@Test
	public void TC_05_LinkText() {
		// Click vào đường link "Addresses" (truyền tuyệt đối)
		driver.findElement(By.linkText("Addresses")).click();
	}
	
	@Test
	public void TC_06_parialLinkText() {
		// Click vào đường link "Apply for vendor account" (truyền tương đối)
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_Css() {
		// Mở lại trang Register
		driver.get("http://demo.nopcommerce.com/register");
		// 1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		
		//2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		
		//3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("Automation testingg");
	}
	
	@Test
	public void TC_08_Xpath() {
		// Mở lại trang Register
		driver.get("http://demo.nopcommerce.com/register");
		// 1
		driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("Selenium");
		
		//2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
		
		//3
		driver.findElement(By.xpath("//label[text()='Last name:']/following-sibling::input")).sendKeys("Automation testingg");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}