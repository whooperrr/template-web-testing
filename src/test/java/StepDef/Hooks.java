package StepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import util.ExtentManager;
import com.aventstack.extentreports.ExtentTest;

import java.util.Base64;

public class Hooks {
    public static WebDriver webDriver;

    @Before
    public void openBrowser(Scenario scenario) {
        ExtentTest featureTest = ExtentManager.getExtentReports().createTest(scenario.getName());
        ExtentManager.setFeatureTest(featureTest);

        System.out.println("Initializing WebDriver...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--force-device-scale-factor=0.75");

        WebDriverManager.chromedriver().driverVersion("126.0.6478.57").setup();
        webDriver = new ChromeDriver(options);

        String appUrl = "https://practicetestautomation.com/practice-test-login/";
        webDriver.get(appUrl);
        webDriver.manage().window().maximize();
        System.out.println("WebDriver initialized successfully.");
    }

    @After
    public void closeBrowser(Scenario scenario) {
        // Clear cookies and localStorage
        webDriver.manage().deleteAllCookies();
        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver).executeScript("window.localStorage.clear();");
            ((JavascriptExecutor) webDriver).executeScript("window.sessionStorage.clear();");
        }

        if (webDriver != null) {
            webDriver.quit();
        }

        // Clear WebDriver reference to avoid memory leaks
        webDriver = null;

        System.out.println("WebDriver closed and resources cleaned up.");
    }
}
