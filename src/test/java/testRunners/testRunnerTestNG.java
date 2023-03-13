/**
 * 
 */
package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * @author mukesh.mandal
 *
 */

@CucumberOptions(features = {"src\\test\\features\\feature\\backgroundDemo.feature"}, 
				glue = {"stepDefinitons"},
				tags = "@Flight_oneWay", 
				plugin = {"pretty", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm","json:target/cucumber.json " },
				monochrome = true)
public class testRunnerTestNG extends AbstractTestNGCucumberTests{

}
