package Webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_05_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Test
	public void TC_01_Run_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.quit();
	}

	@Test
	public void TC_02_Run_Firefox() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");	
		driver = new FirefoxDriver();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.quit();
	}

	@Test
	public void TC_03_Run_Edge() {
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");	
		driver = new EdgeDriver();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.quit();
	}
}