package Webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_TC_XPath_Exercises {
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
	public void TC_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Input value
		driver.findElement(By.xpath("//input[@name='txtFirstname']")).sendKeys("Sun Lee");
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("sunlee.123@gmail@com");
		driver.findElement(By.xpath("//input[@name='txtCEmail']")).sendKeys("sunlee.123@gmail@com");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("abcd123345");
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("abcd123345");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0909686868");
		
		//Click submit
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify error messages
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtEmail']")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtCEmail']")).getText(), "Vui lòng nhập email hợp lệ");
	}

	@Test
	public void TC_03_Incorrect_ConfirmEmail() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Input value
		driver.findElement(By.xpath("//input[@name='txtFirstname']")).sendKeys("Sun June");
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("sunlee.123@gmail.com");
		driver.findElement(By.xpath("//input[@name='txtCEmail']")).sendKeys("sunlee.123@gmail@com.vn");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("abcd7890");
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("abcd7890");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0909686868");
		
		//Click submit
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify error messages
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtCEmail']")).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Input value
		driver.findElement(By.xpath("//input[@name='txtFirstname']")).sendKeys("Sun June");
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("sunlee.123@gmail.com");
		driver.findElement(By.xpath("//input[@name='txtCEmail']")).sendKeys("sunlee.123@gmail.com");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("a@12");
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("a@12");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0909686868");
		
		//Click submit
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify error messages
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtPassword']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtCPassword']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Incorrect_ConfirmPassword() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Input value
		driver.findElement(By.xpath("//input[@name='txtFirstname']")).sendKeys("Sun June");
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("sunlee.123@gmail.com");
		driver.findElement(By.xpath("//input[@name='txtCEmail']")).sendKeys("sunlee.123@gmail.com");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("abcd7890");
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("abcd321");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0909686868");
		
		//Click submit
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify error messages
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtCPassword']")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Invalid_PhoneNumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Input value
		driver.findElement(By.xpath("//input[@name='txtFirstname']")).sendKeys("Sun June");
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("sunlee.123@gmail.com");
		driver.findElement(By.xpath("//input[@name='txtCEmail']")).sendKeys("sunlee.123@gmail.com");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("abcd7890");
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("abcd7890");
		
		
		//Click submit and verify error message khi sdt > 11 ký tự
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("090968686868");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtPhone']")).getText(), "Số điện thoại phải từ 10-11 số.");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).clear();
		
		//Click submit and verify error message khi sdt < 10 ký tự
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("0909123");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtPhone']")).getText(), "Số điện thoại phải từ 10-11 số.");
		driver.findElement(By.xpath("//input[@name='txtPhone']")).clear();
		
		//Nhập không phải số nhà mạng
		driver.findElement(By.xpath("//input[@name='txtPhone']")).sendKeys("9898123456");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='txtPhone']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}