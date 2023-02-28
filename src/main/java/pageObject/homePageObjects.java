/**
 * 
 */
package pageObject;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author mukesh.mandal
 *
 */
public class homePageObjects {

	private WebDriver driver;

	public homePageObjects(WebDriver driver) {
		this.driver = driver;
	}

	By qr_Code = By.xpath("//p[contains(@class,'dRQhOp')]");
	By navigation_Buttons = By.xpath("//ul[@class='happy-nav']/li");
	By home_Button = By.xpath("//a[contains(@class,'logo__home')]");

	public void clickOnHomeButton() {
		if (popUp()) {
			driver.findElement(By.xpath("//span[contains(@class,'icClose')]")).click();
		}
		driver.findElement(home_Button).click();
	}

	public void closeOfferButton() {
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(qr_Code));
		driver.findElement(qr_Code).click();
	}

	public void allNavButtons(String cat) {
		if (popUp()) {
			driver.findElement(By.xpath("//span[contains(@class,'icClose')]")).click();
		}
		List<WebElement> navButtons = driver.findElements(navigation_Buttons);
		for (WebElement nvb : navButtons) {
			String match = nvb.getText();
			if (match.equals(cat)) {
				driver.findElement(By.xpath("//ul[@class='happy-nav']/li//a[text()='" + nvb.getText() + "']")).click();
				break;
			}
		}
	}

	public boolean popUp() {
		boolean ret = false;
		try {
			if (driver.findElement(By.xpath("//h3[contains(text(),'Signup')]")).isDisplayed()) {
				ret = true;
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);

		} finally {
			return ret;
		}
	}
}
