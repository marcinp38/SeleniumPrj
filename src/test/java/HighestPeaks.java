import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HighestPeaks extends TestBase {
    private String url = "https://seleniumui.moderntester.pl/table.php";

    @Test
    public void shouldBe9RowsAbove4000() {
        driver.get(url);

        List<WebElement> peaks = driver.findElements(By.cssSelector("tbody tr"));


        int count = 0;
        for (WebElement row:peaks) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String name = cells.get(0).getText();
            String range = cells.get(1).getText();
            String country = cells.get(2).getText();
            Integer height = Integer.valueOf(cells.get(3).getText());


            if (height > 4000) {
                System.out.println(name + " " + range + " " + country +" " + height);
                count++;
            }

        }
        Assert.assertEquals(count, 9);




    }



}
