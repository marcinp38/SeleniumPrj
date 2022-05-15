import org.testng.annotations.Test;

public class BasicSeleniumTest extends TestBase{

    @Test
    public void openAutomationPractice() throws InterruptedException{
        driver.get("https://seleniumui.moderntester.pl/");

                Thread.sleep(5000);
    }
}
