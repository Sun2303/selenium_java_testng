package Webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser_P1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		driver.close(); //Nó sẽ đóng tab / window mà nó đang đứng		*
		
		driver.quit(); //Nó sẽ đóng Browser mà ko quan tâm có bao nhiêu tab		**
		
		//=> Cả 2 câu lệnh trên sẽ giống nhau khi chỉ có 1 tab trong 1 browser
		
		
		driver.findElement(By.xpath("//input[@name='txtFirstname']")).click();//Sử dụng một lần		**
		WebElement FNtextbox = driver.findElement(By.xpath("//input[@name='txtFirstname']")); // Có thể lưu để sử dụng cho các step sau -> dùng lại nhiều lần
		FNtextbox.clear();
		FNtextbox.sendKeys("");
		
		List<WebElement> MultiE = driver.findElements(By.xpath("")); //Tìm nhiều Element //Set: không lấy trùng nhau. List: lấy luôn trùng nhau   *
		
		driver.get("wwww.facebook.com"); //Mở 1 url nào đó		**
		
		driver.getCurrentUrl(); //Trả về url hiện tại
		
		// - Có thể lưu nó vào 1 biến để sử dụng cho các step sau
		String vietnamesePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(vietnamesePageUrl, "https://vi-vn.facebook.com/");
				
		// - Có thể sử dụng luôn (ko cần tạo biến)
		Assert.assertEquals(driver.getCurrentUrl(), "https://vi-vn.facebook.com/");
		
		//Trả về source code HTML của 1 page
		driver.getPageSource(); //Verify tương đối
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ"));
		
		//Trả về title của page hiện tại
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//Lấy ID của Window/tab mà driver đang đứng (active)
		String idTab = driver.getWindowHandle();		//*
		
		//Lấy ra tất cả ID của Window/Tab
		Set<String> idMultiTab = driver.getWindowHandles(); //Set: không lấy trùng nhau. List: lấy luôn trùng nhau		*	
		
		//Cookie - Cache
		Options opt = driver.manage();
		
		
		Timeouts time = opt.timeouts();
		
		//Chờ Element xuất hiện trong vòng x giây
		time.implicitlyWait(5, TimeUnit.SECONDS); // 5s = 5000 ms = 5000000 microsecond
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		//time chờ page load xong trong vòng x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		//time chờ script được thực thi trong vòng x giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		Window win = opt.window();
		win.fullscreen();
		win.maximize();			//**
		
		//Test GUI: Font/ Size/ Color/ Postion/ Location... (ko dùng nhiều)
		win.getPosition();
		win.getSize();
		
		Navigation navi = driver.navigate();
		navi.back();
		navi.refresh();
		navi.forward();
		navi.to("https://www.facebook.com/");
		
		TargetLocator tar = driver.switchTo();
		
		// WebDriver API - Alert/ Authencation Alert (Alert library)
		tar.alert();		// *
		
		// WebDriver API - Windowns/ Tabs
		tar.frame("");		// *
		
		tar.window("");		// *
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