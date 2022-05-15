import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    WebDriver driver;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public void setupDriverManager(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }
    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep (2000);
        driver.quit();
    }

}
