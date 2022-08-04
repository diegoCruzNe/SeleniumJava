package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageReservation {
	private By passengersDrop;
	private By fromDrop;
	private By toDrop;
	private WebDriver driver;
	private By titleText;
	public PageReservation(WebDriver driver) {
		this.driver = driver;
		titleText = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/h3");
		passengersDrop = By.name("passCount");
		fromDrop = By.name("fromPort");
		toDrop = By.name("toPort");
	}
	public void assertPage() {
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Login Successfully"));
	}
	
	public void selectPassengers(int cant) {
		//En cuanto cargue la linea 31, ejecutar el codigo, tienes 10 seg.
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop));
		Select selectPasajeros = new Select(cantidadPasajeros);
		selectPasajeros.selectByVisibleText(Integer.toString(cant));
	}
	
	public void selectFromPort(int index) {
		Select selectFrom = new Select(driver.findElement(fromDrop));
		selectFrom.selectByIndex(index);
	}
	
	public void selectToPort(String city) {
		Select selectTo = new Select(driver.findElement(toDrop));
		selectTo.selectByValue(city);
	}
	
	

}
