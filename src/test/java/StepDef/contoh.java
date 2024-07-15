package StepDef;

import Base.BaseTest;
import Interfaces.TestActions;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ExtentManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static StepDef.Hooks.webDriver;
import static org.junit.Assert.assertTrue;

// Kelas Contoh yang mengimplementasikan interface TestActions dan mewarisi BaseTest
public class contoh extends BaseTest implements TestActions {

    // Konstruktor yang memanggil konstruktor superclass
    public contoh() {
        super(webDriver);
    }

    @Override
    @Given("user navigates to the login page")
    public void userNavigatesToTheLoginPage() {
        // Membuat node laporan Extent untuk langkah Given
        ExtentTest scenarioTest = ExtentManager.getFeatureTest().createNode("Given - user navigates to the login page");
        ExtentManager.setScenarioTest(scenarioTest);

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            var cekLoginPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='max-width']")));
            var actions = new Actions(webDriver);
            actions.moveToElement(cekLoginPage).click().perform();
            assertTrue(cekLoginPage.isDisplayed());
            scenarioTest.pass("Navigated to the login page");
            takeScreenshot(scenarioTest, "testgiven.png");
        } catch (Exception e) {
            // Menangani pengecualian dan mencatat kesalahan
            handleException(e, scenarioTest, "Fail to navigate login page");
            takeScreenshot(scenarioTest, "testgivenfail.png");
        }
    }

    @Override
    @When("user enters username and password")
    public void userEntersUsernameAndPassword() {
        // Membuat node laporan Extent untuk langkah When
        ExtentTest scenarioTest = ExtentManager.getScenarioTest().createNode("When - user enters username and password");
        ExtentManager.setScenarioTest(scenarioTest);

        try {
            // Membaca file properties untuk mendapatkan username dan password
            Properties properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                scenarioTest.fail("Failed to load properties file");
            }

            String email = properties.getProperty("emailApproval");
            String password = properties.getProperty("passwordApproval");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

            var emailoc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#username")));
            var actions = new Actions(webDriver);
            emailoc.click();
            emailoc.clear();
            emailoc.sendKeys(email);

            var passloc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#password")));
            passloc.click();
            passloc.clear();
            passloc.sendKeys(password);
            takeScreenshot(scenarioTest, "testwhen.png");

            var loginLoc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submit")));
            loginLoc.click();

            scenarioTest.pass("Entered username and password");

        } catch (Exception e) {
            // Menangani pengecualian dan mencatat kesalahan
            handleException(e, scenarioTest, "Fail to enter username and password");

        }
    }

    @Override
    @Then("user is navigated to the home page")
    public void userIsNavigatedToTheHomePage() {
        // Membuat node laporan Extent untuk langkah Then
        ExtentTest scenarioTest = ExtentManager.getScenarioTest().createNode("Then - user is navigated to the home page");
        ExtentManager.setScenarioTest(scenarioTest);

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            var homePage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class='post-title']")));
            var actions = new Actions(webDriver);
            actions.moveToElement(homePage).click().perform();
            assertTrue(homePage.isDisplayed());
            scenarioTest.pass("Navigated to the home page");
            takeScreenshot(scenarioTest, "testthen.png");
        } catch (Exception e) {
            // Menangani pengecualian dan mencatat kesalahan
            handleException(e, scenarioTest, "Failed to navigate to home page");

        }
    }

    @Override
    @When("user enter invalid username")
    public void userEnterInvalidUsername() {
        // Membuat node laporan Extent untuk langkah When
        ExtentTest scenarioTest = ExtentManager.getScenarioTest().createNode("When user enter invalid username and password");
        ExtentManager.setScenarioTest(scenarioTest);

        try {
            // Membaca file properties untuk mendapatkan username dan password yang salah
            Properties properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
                properties.load((fileInputStream));
            } catch (IOException e) {
                scenarioTest.fail("Failed to load properties file");
            }
            String email = properties.getProperty("userFalse");
            String password = properties.getProperty("passwordApproval");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

            var emailoc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#username")));
            var actions = new Actions(webDriver);
            emailoc.click();
            emailoc.clear();
            emailoc.sendKeys(email);

            var passloc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#password")));
            passloc.click();
            passloc.clear();
            passloc.sendKeys(password);
            takeScreenshot(scenarioTest, "testwhen2");

            var loginLoc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submit")));
            loginLoc.click();

            scenarioTest.pass("Invalid username & password entered");

        } catch (Exception e) {
            // Menangani pengecualian dan mencatat kesalahan
            handleException(e, scenarioTest, "Invalid username fail to entered");
        }
    }

    @Override
    @Then("user see error message invalid username")
    public void userSeeErrorMessageInvalidUsername() {
        // Membuat node laporan Extent untuk langkah Then
        ExtentTest scenarioTest = ExtentManager.getScenarioTest().createNode("Then user see error message invalid username");
        ExtentManager.setScenarioTest(scenarioTest);

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            var errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='error']")));
            String expectederrorMsg = "Your username is invalid!";
            String actualerrorMsg = errorMsg.getText();
            Assert.assertEquals(expectederrorMsg, actualerrorMsg);

            scenarioTest.pass("Error message is displayed with right text");
            takeScreenshot(scenarioTest, "testthenmsg");
        } catch (Exception e) {
            // Menangani pengecualian dan mencatat kesalahan
            handleException(e, scenarioTest, "Error message is not displayed with right text");
        }
    }

    @Override
    @When("user enter invalid password")
    public void userEnterInvalidPassword() {
        // Membuat node laporan Extent untuk langkah When
        ExtentTest scenarioTest = ExtentManager.getScenarioTest().createNode("When user enter invalid password");
        ExtentManager.setScenarioTest(scenarioTest);

        try {
            // Membaca file properties untuk mendapatkan password yang salah
            Properties properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
                properties.load((fileInputStream));
            } catch (IOException e) {
                scenarioTest.fail("Failed to load properties file");
            }
            String email = properties.getProperty("emailApproval");
            String password = properties.getProperty("passFalse");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

            var emailoc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#username")));
            var actions = new Actions(webDriver);
            emailoc.click();
            emailoc.clear();
            emailoc.sendKeys(email);

            var passloc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#password")));
            passloc.click();
            passloc.clear();
            passloc.sendKeys(password);
            takeScreenshot(scenarioTest, "testwheninvpass");

            var loginLoc = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submit")));
            loginLoc.click();

            scenarioTest.pass("Invalid password entered");

        } catch (Exception e) {
            // Menangani pengecualian dan mencatat kesalahan
            handleException(e, scenarioTest, "Invalid password fail to entered");
        }
    }

    @Override
    @Then("user see error message invalid password")
    public void userSeeErrorMessageInvalidPassword() {
        // Membuat node laporan Extent untuk langkah Then
        ExtentTest scenarioTest = ExtentManager.getScenarioTest().createNode("Then user see error message invalid password");
        ExtentManager.setScenarioTest(scenarioTest);

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            var errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='error']")));
            String expectederrorMsg = "Your password is invalid!";
            String actualerrorMsg = errorMsg.getText();
            Assert.assertEquals(expectederrorMsg, actualerrorMsg);

            scenarioTest.pass("Error message is displayed with right text");
            takeScreenshot(scenarioTest, "testthenmsginvalidpass");
        } catch (Exception e) {
            // Menangani pengecualian dan mencatat kesalahan
            handleException(e, scenarioTest, "Error message is not displayed with right text");
        }
    }

    @Override
    @Then("error message is displayed")
    public void errorMessageIsDisplayed() {
        // Membuat node laporan Extent untuk langkah Then
        ExtentTest scenarioTest = ExtentManager.getScenarioTest().createNode("Then user see error message");
        ExtentManager.setScenarioTest(scenarioTest);

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            var errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='error']")));
            var action = new Actions(webDriver);
            assertTrue(errorMsg.isDisplayed());

            scenarioTest.pass("Error message is displayed");
            takeScreenshot(scenarioTest, "testthen2");
        } catch (Exception e) {
            // Menangani pengecualian dan mencatat kesalahan
            handleException(e, scenarioTest, "Error message is not displayed");
        }
    }
}