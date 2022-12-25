package Webdriver;

import java.util.List;
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

public class Topic_14_Action_Part_II {
	WebDriver driver;
	Actions action;
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
		
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li")); //Đang chứa 12 items trong này
		
		//1 - Click vào số 1 (source) -> Giữ chuột - chưa nhả ra
		action.clickAndHold(listNumber.get(0)) //Get số index
		//2 - Di chuột tới số mong muốn (target)
		.moveToElement(listNumber.get(7))
		//3 - Nhả chuột trái ra
		.release()
		//Execute:
		.perform();
		sleepInSecond(2);
		
		List<WebElement> selectedListNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(selectedListNumber.size(), 8);
	}

	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		Keys key = null;
		if(osName.contains("Windows")) {
			key = Keys.CONTROL;
		}else {
			key = Keys.COMMAND;
		}
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li")); //Đang chứa 12 items trong này
		
		//Nhấn Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		//Click chọn các số random
		action.click(listNumber.get(0))
		.click(listNumber.get(3))
		.click(listNumber.get(10)).perform();
		//Nhả phím Ctrl ra
		action.keyUp(key).perform();
		sleepInSecond(2);
		List<WebElement> selectedListNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(selectedListNumber.size(), 3);
		
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