import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FormTest extends TestBase{
    String url = "https://seleniumui.moderntester.pl/form.php";

    @Test
    public void shouldFillFormWithSuccess(){
        driver.get(url);
        WebElement fname = driver.findElement(By.id("inputFirstName3"));
        fname.sendKeys("Marcin");

        WebElement lname = driver.findElement(By.id("inputLastName3"));
        lname.sendKeys("Petynia");

        WebElement email = driver.findElement(By.id("inputEmail3"));
        email.sendKeys("tester@test.com");

        List<WebElement> radioSex = driver.findElements(By.name("gridRadiosSex"));

        for (WebElement element:radioSex) {
            if (element.getAttribute("value").equals("other")) {
                element.click();
                break;
            }

        }

        WebElement age = driver.findElement(By.id("inputAge3"));
        age.sendKeys("33");


        List<WebElement> radiosExperience = driver.findElements(By.name("gridRadiosExperience"));

        for (WebElement element:radiosExperience) {
            if (element.getAttribute("value").equals("1")) {
                element.click();
                break;
            }

        }

        List<WebElement> professions = driver.findElements(By.name("gridCheckboxProfession"));
        driver.findElement(By.id("gridCheckManulTester")).click();
        driver.findElement(By.id("gridCheckAutomationTester")).click();





        Select continent = new Select(driver.findElement(By.id("selectContinents")));
        continent.selectByValue("europe");

        Select SeleniumCommands = new Select(driver.findElement(By.id("selectSeleniumCommands")));
        SeleniumCommands.selectByValue("navigation-commands");
        SeleniumCommands.selectByValue("switch-commands");
        SeleniumCommands.selectByValue("wait-commands");

        WebElement file = driver.findElement(By.id("chooseFile"));
        file.sendKeys("C:\\text.txt");

        //submit
        driver.findElement(By.tagName("form")).submit();

        //assercje
        WebElement message = driver.findElement(By.id("validator-message"));
        Assert.assertEquals(message.getText(),"Form send with success");

    }


}


