package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// ExtentManager.java

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> featureTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> stepTest = new ThreadLocal<>();

    public static synchronized void init() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    public static ExtentReports getExtentReports() {
        return extent;
    }

    public static ExtentTest getFeatureTest() {
        return featureTest.get();
    }

    public static void setFeatureTest(ExtentTest test) {
        featureTest.set(test);
    }

    public static ExtentTest getScenarioTest() {
        return scenarioTest.get();
    }

    public static void setScenarioTest(ExtentTest test) {
        scenarioTest.set(test);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}