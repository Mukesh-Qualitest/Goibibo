package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class baseClass {

	public static WebDriver driver;

	public static WebDriver setDriver() {
		driver = new EdgeDriver();
		return driver;
	}

	public static void launchWebsite() {
		driver.get("https://www.goibibo.com/");
		driver.manage().window().maximize();
	}
	
	public static void launchOrangeHrm() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
	}

	public static void closeBrowser() {
		driver.quit();
	}
	
	public static void delayScript() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
