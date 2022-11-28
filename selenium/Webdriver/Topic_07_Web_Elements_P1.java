package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Elements_P1 {
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
	public void TC_01_() {
		// Dùng biến khi Elements được dùng lại nhiều lần
		/* WebElement emailTextbox = driver.findElement(By.id("Email"));
		emailTextbox.isDisplayed();
		emailTextbox.clear();
		emailTextbox.sendKeys(""); */
		
		//Khi dùng 1 lần thì không cần
		WebElement element = driver.findElement(By.className(""));
		
		//Dùng cho các textbox/ textarea/ dropdown (Editable)
		element.clear(); //Xóa trước khi nhập text	**
		element.sendKeys(); //Nhập liệu		**
		
		//Click vào các button/ link/ radio/ img/...
		element.click();		//**
		
		String searchAttribute = element.getAttribute("placeholder"); //**
		String emailTextboxAttribute = element.getAttribute("value");
		
		//GUI: Font/ Size/ Color/ Location/ Position...
		element.getCssValue("background-color");		//*
		element.getLocation(); //Vị trí của Element so với web
		element.getSize(); //Kích thước của Element (cao + rộng)
		element.getRect(); // Location + Size
		
		// Chụp hình khi TCs failed -> Report
		element.getScreenshotAs(OutputType.FILE);	//*
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		//Khi cần lấy ra tên thẻ HTML của element đó -> Truyền vào cho 1 Locator khác
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.id("Email")).getTagName();
		
		String emailTextboxTagname = driver.findElement(By.cssSelector("#Email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='email']"));
		
		//Lấy text từ error/success message/ Label...
		element.getText();		//**
		
		//Lấy text từ Attribute
		element.getAttribute("placeholder");
		/*=> Khi value cần lấy nằm ở bên ngoài => getText
		  => Khi value nằm ở bên trong => getAttribute */
		
		// Verify xem 1 element hiển thị hoặc không. Phạm vi cho tất cả các Element
		Assert.assertTrue(element.isDisplayed());		//**
		Assert.assertFalse(element.isDisplayed());
		
		// Verify xem 1 element có thao tác được hay không. Phạm vi cho tất cả các Element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// Verify xem 1 element được chọn hay chưa. Phạm vi Checkbox/ Radio
		Assert.assertTrue(element.isSelected());		//*
		Assert.assertFalse(element.isSelected());
		
		element.submit(); //Khi element nằm trong thẻ form. Submit được sử dụng như click vào button (Enter)
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}