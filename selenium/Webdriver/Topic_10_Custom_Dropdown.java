package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		SelectItemsInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Medium");
		
		SelectItemsInDropdown("span#files-button", "ul#files-menu div[role='option']", "Some unknown file");
		
		SelectItemsInDropdown("span#number-button", "ul#number-menu div[role='option']", "5");
		
		SelectItemsInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Prof.");
		
//		//1- Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
//		driver.findElement(By.cssSelector("span#speed-button")).click();
//		
//		//2- Chờ cho tất cả các item được load ra thành công
//		// Lấy locator đại diện cho tất cả các item
//		// Lấy đến thẻ chứa text
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
//		
//		//Đưa hết tất cả items trong dropdown vào 1 List
//		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
//		
//		//3- Tìm item cần (dùng vòng lặp duyệt qua)
//		for (WebElement tempItem : speedDropdownItems) {
//			String itemText = tempItem.getText();
//			System.out.println(itemText);
//			
//			//4- Kiểm tra đã tìm thấy đúng cái mình mong muốn hay chưa
//			if(itemText.equals("Slower")) {
//				tempItem.click();
//				break;
//			}
//		}
	}
	

	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		SelectItemsInDropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		
		SelectItemsInDropdown("i.dropdown.icon", "span.text", "Matt");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
		
		SelectItemsInDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		SelectItemsInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		SelectItemsInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		SelectItemsInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
	}
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		EnterAndSelectItemsInDropdown("input.search", "span.text", "Angola");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");
		
		EnterAndSelectItemsInDropdown("input.search", "span.text", "Belgium");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
		
		EnterAndSelectItemsInDropdown("input.search", "span.text", "Australia");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
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
	
	public void SelectItemsInDropdown(String parentCss, String allItemsCss, String expectedItem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemsCss)));
		List<WebElement> DropdownItems = driver.findElements(By.cssSelector(allItemsCss));
		
		for (WebElement tempItem : DropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			//4- Kiểm tra đã tìm thấy đúng cái mình mong muốn hay chưa
			if(itemText.trim().equals(expectedItem)) {
				tempItem.click();
				break;
			}
		}
	}
	
	public void EnterAndSelectItemsInDropdown(String textboxCss, String allItemsCss, String expectedItem) {
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedItem);
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemsCss)));
		List<WebElement> DropdownItems = driver.findElements(By.cssSelector(allItemsCss));
		
		for (WebElement tempItem : DropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			//4- Kiểm tra đã tìm thấy đúng cái mình mong muốn hay chưa
			if(itemText.trim().equals(expectedItem)) {
				tempItem.click();
				break;
			}
		}
	}
}