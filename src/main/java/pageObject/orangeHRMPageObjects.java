/**
 * 
 */
package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

/**
 * @author mukesh.mandal
 *
 */
public class orangeHRMPageObjects {

	private WebDriver driver;

	public orangeHRMPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	By NameText = By.xpath("//p[contains(.,'Username')]");
	By passText = By.xpath("//p[contains(.,'Password')]");
	By userInput = By.xpath("//input[@name='username']");
	By passwordInput = By.xpath("//input[@name='password']");
	By loginButton = By.xpath("//button[text()=' Login ']");
	By dropClick = By.xpath("//li[contains(@class,'userdropdown')]");
	By aboutClick = By.xpath("//a[text()='Über']");
	By companyText = By.xpath("//p[text()='OrangeHRM']");
	By closeButton = By.xpath("//button[contains(@class,'oxd-dialog-close-button')]");
	By logoutButton = By.xpath("//a[text()='Abmelden']");

	public void enterUserName() {
		WebElement wait = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(NameText));
		String user = driver.findElement(NameText).getText();
		String userName = user.split(":")[1].trim();
		driver.findElement(userInput).sendKeys(userName);
	}

	public void enterPassword() {
		String pass = driver.findElement(passText).getText();
		String password = pass.split(":")[1].trim();
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(loginButton).click();
	}

	public void homePageVerifcation() {
		WebElement wait = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(dropClick));
		driver.findElement(dropClick).click();
		driver.findElement(aboutClick).click();
		WebElement wait1 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(companyText));
		String companyName = driver.findElement(companyText).getText();
		Assert.assertEquals(companyName, "OrangeHRM");
		driver.findElement(closeButton).click();
	}

	public void logout() {
		WebElement wait1 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(dropClick));
		driver.findElement(dropClick).click();
		driver.findElement(logoutButton).click();
	}
}
