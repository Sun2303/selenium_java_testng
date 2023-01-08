package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Popup_Part_II_Fixed_Not_In_DOM {
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
		
		//Tắt notification cho Firefox
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(new FirefoxProfile());
//		options.addPreference("dom.webnotifications.enabled", false);
//		driver = new FirefoxDriver(options);		
		
		
		//*Tắt notification cho Chrome
//		Map<String, Integer> prefs = new HashMap<String, Integer>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		driver = new ChromeDriver(options);
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	//@Test
	public void TC_01_Fixed_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		//By là để tìm Element
		By LoginPopup = By.cssSelector("div.ReactModal__Content");
		
		//Verify nó chưa hiển thị
		Assert.assertEquals(driver.findElements(LoginPopup).size(), 0);
		
		//Click bật login popup
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(2);
		
		//Verify nó hiển thị
		Assert.assertEquals(driver.findElements(LoginPopup).size(), 1);
		//or Assert.assertTrue(driver.findElement(LoginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		//Verify Popup đã mất sau khi tắt
		Assert.assertEquals(driver.findElements(LoginPopup).size(), 0);
	}

	@Test
	public void TC_02_Fixed_Not_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		By createAccountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		//Verify create popup isn't displayed
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		//Verify create popup is displayed
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 1);
		
		driver.findElement(By.name("firstname")).sendKeys("Sunbeos");
		driver.findElement(By.name("lastname")).sendKeys("Walker");
		driver.findElement(By.name("reg_email__")).sendKeys("0909696969");
		driver.findElement(By.name("reg_passwd__")).sendKeys("Sunbeos123");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("22");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("June");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("2020");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		//Verify create popup isn't displayed
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
		
		
	}
	
	@Test
	public void TC_03_Not_In_DOM_Tiki() {
		
	}

	@Test
	public void TC_05_() {

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