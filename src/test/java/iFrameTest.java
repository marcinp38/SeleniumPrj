import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class iFrameTest extends TestBase{
    String url = "https://seleniumui.moderntester.pl/iframes.php";

    @BeforeMethod
    public void testSetup() {
        driver.get(url);
    }

    @Test
    public void iFrameSwitch() {

        driver.switchTo().frame("iframe1");
        driver.findElement(By.id("inputFirstName3")).sendKeys("Marcin");
        driver.switchTo().defaultContent();


        driver.switchTo().frame("iframe2");
        driver.findElement(By.id("inputLogin")).sendKeys("blabla@bla.com");
        driver.switchTo().defaultContent();


    }


}
