import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProgressBar extends TestBase{
    String url = "https://seleniumui.moderntester.pl/progressbar.php";

    @BeforeMethod
    public void testSetup() {
        driver.get(url);
    }
    @Test
    public void shouldProgressBarComplete() {
        //klikamy button
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.cssSelector("#progressbar>div.ui-progressbar-complete"));
        String text = driver.findElement(By.className("progress-label")).getText();
        Assert.assertEquals(text, ("Complete!"));
    }
    @Test
    public void progressBarClassShouldContainCompleted() {
        //klikamy button
        String className = "ui-progressbar-complete";
        WebElement bar = driver.findElement(By.cssSelector("#progressbar>div.ui-progressbar-value"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.attributeContains(bar, "class", className));

    }


}
