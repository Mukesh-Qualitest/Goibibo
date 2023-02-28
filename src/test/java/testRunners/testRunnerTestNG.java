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
				tags = "@Hotel", 
				plugin = {"pretty", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm" },
				monochrome = true)
public class testRunnerTestNG extends AbstractTestNGCucumberTests{

}
