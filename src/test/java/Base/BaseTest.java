package Base;

// Mengimpor kelas-kelas yang diperlukan untuk fungsi dasar pengujian
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import util.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

// Kelas dasar untuk pengujian, menyediakan metode umum untuk menangani pengecualian dan mengambil screenshot
public class BaseTest {
    protected WebDriver driver;

    // Konstruktor untuk inisialisasi WebDriver
    public BaseTest(WebDriver driver) {
        this.driver = driver;
    }

    // Metode untuk menangani pengecualian dan mencatat kesalahan ke laporan Extent
    protected void handleException(Exception e, ExtentTest scenarioTest, String message) {
        e.printStackTrace();
        scenarioTest.fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
        throw new RuntimeException(e);  // Add this line to stop the scenario on failure
    }

    // Metode untuk mengambil screenshot dalam format Base64
    protected String takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent;
        try {
            fileContent = java.nio.file.Files.readAllBytes(screenshot.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }

    // Metode untuk mengambil screenshot dan menambahkannya ke laporan Extent
    protected void takeScreenshot(ExtentTest scenarioTest, String imagePath) {
        String screenshot = takeScreenshot();
        scenarioTest.addScreenCaptureFromBase64String(screenshot, imagePath);
    }
}