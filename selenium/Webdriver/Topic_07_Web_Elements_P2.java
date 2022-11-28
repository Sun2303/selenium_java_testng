package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Elements_P2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#development");
	
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

	//@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Textbox
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Sun Chan Be Du");
			System.out.println("Email textbox is displayed");
		}else {
			System.out.println("The Email textbox is not displayed");
		}
		
		// Textarea
		if(driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Never give up!");
			System.out.println("Education textarea is displayed");
		}else {
			System.out.println("Education textarea is not displayed");
		}
		
		// Radio
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age under 18 is displayed");
		}else {
			System.out.println("Age under 18 is not displayed");
		}
		
		if(driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Name User 5 is displayed");
		}else {
			System.out.println("Name User 5 is not displayed");
		}
	}

	//@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(passwordTextbox).isEnabled())
		{
			System.out.println("Password Textbox is enabled");
		} else {
			System.out.println("Password Textbox is disabled");
		}
		
		if(driver.findElement(biographyTextArea).isEnabled())
		{
			System.out.println("Biography TextArea is enabled");
		} else {
			System.out.println("Biography TextArea is disabled");
		}
		
		if(driver.findElement(emailTextbox).isEnabled())
		{
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is disabled");
		}
		
	}

	//@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Verify checkbox/ Radio button are deselected
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		
		//Click on checkbox/ Radio
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		sleepInSecond(3);
		
		//Verify checkbox/ Radio are selected
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
	}
	
	@Test
	public void TC_04_Combine(){
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.cssSelector("#email")).sendKeys("suntest2303@gmail.com");
		
		By chimpNewPassword = By.id("new_password");
		By signupButton = By.id("create-account-enable");
		
		
		//Verify input Password = number
		driver.findElement(chimpNewPassword).sendKeys("123");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(chimpNewPassword).clear();

		//Verify input Password = lowercase
		driver.findElement(chimpNewPassword).sendKeys("test");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(chimpNewPassword).clear();

		//Verify input Password = UPPERCASE
		driver.findElement(chimpNewPassword).sendKeys("ALOAL");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(chimpNewPassword).clear();
		
		//Verify input Password = Special Char
		driver.findElement(chimpNewPassword).sendKeys("@.*_");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		driver.findElement(chimpNewPassword).clear();
		
		//Verify input Password > = 8 characters
		driver.findElement(chimpNewPassword).sendKeys("abcd1234");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(chimpNewPassword).clear();
		
		//Verify full data
		driver.findElement(chimpNewPassword).sendKeys("Password.123");
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
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
}