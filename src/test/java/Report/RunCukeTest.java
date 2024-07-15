package Report;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import util.ExtentManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature/contoh.feature",
        glue = {"StepDef"},
        plugin = {"pretty", "html:target/cucumber-pretty"}
)
public class RunCukeTest {

    @BeforeClass
    public static void setup() {
        ExtentManager.init();
    }

    @AfterClass
    public static void teardown() {
        ExtentManager.flush();
    }
}
