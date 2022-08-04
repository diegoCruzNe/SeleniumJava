package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import helpers.Screenshooter;
import helpers.WebDriverManager;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;

public class Tests {
	private WebDriver driver;
	ArrayList<String> tabs;
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().window().fullscreen();
		//driver.manage().window().setSize(new Dimension(200,200));
		driver.navigate().to("https://demo.guru99.com/test/newtours/");
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
		String googleWindow = "window.open('https://www.google.com')";
		javaScriptExecutor.executeScript(googleWindow);
		tabs = new ArrayList<String> (driver.getWindowHandles());
		//Helpers helper = new Helpers();
		//helper.sleepSeconds(5);
	}
	
	@Test
	public void loginIncorrecto() {
		WebDriverManager.setWindowSize(driver, "maximized");
		driver.switchTo().window(tabs.get(1)).navigate().to("https://www.youtube.com");		
		driver.switchTo().window(tabs.get(0));
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("dasdas","dasdasd");
		pageLogon.assertLogonPage();
	}
	
	@Test
	public void pruebaUno() {
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);		
		pageLogin.login("fdsfsdf", "fsdfsdfdsfsd");		
		pageLogon.assertLogonPage();		
	}
	
	@Test
	public void pruebaDos() {
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);		
		pageLogin.login("mercury", "mercury");		
		pageReservation.assertPage();
	}
	
	@Test
	public void pruebaTres() {
		WebDriverManager.setWindowSize(driver,400,400);
		driver.findElement(By.xpath("//a[@href='reservation.php']")).click();
		Helpers helper = new Helpers();
		helper.sleepSeconds(3);
		//PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);		
		//pageLogin.login("mercury", "mercury");
		pageReservation.selectPassengers(2);
		pageReservation.selectFromPort(3);
		pageReservation.selectToPort("London");		
		
	}
	
	
	@Test
	public void login() {
		WebDriverManager.setWindowSize(driver, "fullscreen");
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.login("dasdsad", "dasdsa");
		pageReservation.assertPage();
	}
	
	@Test
	public void pruebaCantidadCampos() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.verifyFields();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(!result.isSuccess()) {
			Screenshooter.takeScreenshot("Error", driver);
		}
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0)).close();
		//driver.close();
	}
	
	
	/*@AfterMethod
	public void tearDown() {
		driver.close();
	}*/
	
	
}