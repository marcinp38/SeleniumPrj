package mystore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultPage extends BasePage{
    public int getResultsNumber() {
        List<WebElement> productList = driver.findElements(By.className("product"));
        return productList.size();
    }

    public void clickQuickViewOnProduct(String productName) {
        List<WebElement> productList = driver.findElements(By.className("product"));
        for(int i = 0; i < productList.size(); i++) {
            WebElement item = productList.get(i);
            WebElement titleEl =item.findElement(By.cssSelector(".h3.product-title"));
            String title = titleEl.getText();
            if (title.equals(productName)) {
                //wersja js jesli juz wszystko zawiedzie
//                String css = ".product:nth-of-type("+(i+1)+") .quick-view";
//                String jsScript = "$('"+css+"').click();";
//                js.executeScript(jsScript);
                //wersja z explicit wait
                WebElement quickView = item.findElement(By.cssSelector(".quick-view"));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                actions.moveToElement(titleEl).perform();
                wait.until(ExpectedConditions.visibilityOf(quickView));
                quickView.click();
                break;
            }
        }

        productQuickView = new ProductQuickView();
    }


}