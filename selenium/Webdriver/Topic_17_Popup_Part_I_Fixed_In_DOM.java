package Webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Popup_Part_I_Fixed_In_DOM {
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
		
		//Tắt notification cho Firefox
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(new FirefoxProfile());
//		options.addPreference("dom.webnotifications.enabled", false);
//		driver.new FirefoxDriver(options);		
		
		
		//*Tắt notification cho Chrome
		Map<String, Integer> prefs = new HashMap<String, Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	//@Test
	public void TC_01_Fixed_In_DOM_NgoaiNgu24h() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	}

	//@Test
	public void TC_02_Fixed_In_DOM_KynaEnghlish() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(2);
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("sunchodien123");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close span[aria-hidden='true']")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
	}
	
	//@Test
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