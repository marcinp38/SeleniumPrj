import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DelayedAlert extends TestBase{
    String url = "https://seleniumui.moderntester.pl/alerts.php";

    @BeforeMethod
    public void testSetup() {
        driver.get(url);
    }
    @Test
    public void shouldAcceptDelayedAlert() {
        //klikamy button
        driver.findElement(By.id("delayed-alert")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        //a potem asercje
        String text = driver.findElement(By.id("delayed-alert-label")).getText();
        Assert.assertEquals(text, ("OK button pressed"));
    }




}
