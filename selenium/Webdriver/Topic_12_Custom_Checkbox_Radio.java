package Webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Custom_Checkbox_Radio {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/registre-person");
		sleepInSecond(5);
		
		/* CASE 1 -> FAILED */
		//Thẻ input bị che, nên không thao tác được. Nhưng nó dùng để verify được vì hàm isSelected chỉ work với thẻ input
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::div/input")).isSelected());
	}

	@Test
	public void TC_02_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/registre-person");
		sleepInSecond(5);
		
		/* CASE 2 -> FAILED */
		//Dùng thẻ khác input để click (span/div/label/...). Nhưng không dùng để verify được vì hàm isSelected chỉ work với thẻ input
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).isSelected());
	}
	
	@Test
	public void TC_03_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/registre-person");
		sleepInSecond(5);
		
		/* CASE 3 -> PASSED nhưng rườm rà */
		//Dùng thẻ khác input để click (span/div/label/...). Nhưng dùng input để verify vì hàm isSelected chỉ work với thẻ input
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label//div[text()='Đăng ký cho người thân']/parent::div/input")).isSelected());
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}