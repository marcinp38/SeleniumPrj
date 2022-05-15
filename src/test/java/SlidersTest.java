import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SlidersTest  extends TestBase{

    private String url = "https://seleniumui.moderntester.pl/slider.php";

    @Test
    public void SliderKeys() {
        driver.get(url);
        WebElement handle = driver.findElement(By.id("custom-handle"));

        while (Integer.parseInt(handle.getText()) <50) {
            handle.sendKeys(Keys.ARROW_RIGHT);
        }
        logger.info(handle.getText());
        while (Integer.parseInt(handle.getText()) >30) {
            handle.sendKeys(Keys.ARROW_LEFT);
        }
        logger.info(handle.getText());

            }
}
