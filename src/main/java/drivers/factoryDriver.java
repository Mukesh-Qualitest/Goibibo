/**
 * 
 */
package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * @author mukesh.mandal
 *
 */
public class factoryDriver {
	public static WebDriver driver;

	public static WebDriver init_driver() {
		System.setProperty("webdriver.edge.driver","C:\\Hertz\\Goibibo\\src\\test\\resources\\drivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.goibibo.com/");
		return driver;
	}
}
