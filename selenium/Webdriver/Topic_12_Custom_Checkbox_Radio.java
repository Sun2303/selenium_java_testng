package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		
		/* CASE 1 -> FAILED */
		//Thẻ input bị che, nên không thao tác được. Nhưng nó dùng để verify được vì hàm isSelected chỉ work với thẻ input
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::div/input")).isSelected());
	}

	//@Test
	public void TC_02_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		
		/* CASE 2 -> FAILED */
		//Dùng thẻ khác input để click (span/div/label/...). Nhưng không dùng để verify được vì hàm isSelected chỉ work với thẻ input
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).isSelected());
	}
	
	//@Test
	public void TC_03_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		
		/* CASE 3 -> PASSED nhưng rườm rà */
		//Dùng thẻ khác input để click (span/div/label/...). Nhưng dùng input để verify vì hàm isSelected chỉ work với thẻ input
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}

	@Test
	public void TC_04_() {
		/* CASE 4 -> PASSED (kết hợp với thư viện của Javascript - JavascriptExecutor) */
		//Thẻ input bị ẩn nhưng vẫn muốn dùng để click (Hàm click của WebElement sẽ ko thao tác vào element bị ẩn được)
		//=> Dùng hàm click của Javascript để click vào element bị ẩn
		//Selenium cung cấp 1 thư viện để nhúng các đoạn code JS vào kịch bản test => JavascriptExecutor
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		
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