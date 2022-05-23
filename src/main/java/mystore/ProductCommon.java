package mystore;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class ProductCommon extends BasePage{
    String modalCSS = "";
    String itemPriceCSS = ".current-price>span[content]";
    String qtyCSS = "input[name='qty']";
    String addToCartButtonCss = "button.add-to-cart[type=\"submit\"]";

    protected String getCSSForModal(String css) {
        return modalCSS + css;
    }
    public Float getItemPrice() {
        return Float.parseFloat(driver.findElement(By.cssSelector(getCSSForModal(itemPriceCSS))).getAttribute("content"));
    }

    public ProductCommon changeQuantity(int i) {
        WebElement qty = driver.findElement(By.cssSelector(getCSSForModal(qtyCSS)));
        qty.clear();
        qty.sendKeys(""+i);
        return this;
    }

    public ProductCommon addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement addButton = driver.findElement(By.cssSelector(getCSSForModal(addToCartButtonCss)));
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
        cartPreview = new CartPreview();
        return this;
    }

    public ProductCommon changeProductVariant(String label, String visibleText) {
        List<WebElement> variants = driver.findElements(By.cssSelector("div.product-variants-item"));
        for (WebElement variant : variants) {
            String title = variant.findElement(By.cssSelector("span.control-label")).getText();
            if (title.equals(label)) {
                String oldPrice = driver.findElement(By.cssSelector(getCSSForModal(itemPriceCSS))).getAttribute("content");
                WebElement element = variant.findElement(By.cssSelector("span.control-label+*"));
                if(element.getTagName().equals("select")) {
                    changeProductVariantSelect(element, visibleText);
                }
                else {
                    changeProductVariantRadios(element, visibleText);
                }
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
                try {
                    wait.until(c -> !driver.findElement(By.cssSelector(getCSSForModal(itemPriceCSS))).getAttribute("content").equals(oldPrice));
                }
                catch (TimeoutException e) {}
                break;
            }
        }
        return this;
    }

    private void changeProductVariantRadios(WebElement element, String visibleText) {
        WebElement radio = element.findElement(By.cssSelector("input[type=\"radio\"][title=\""+visibleText+"\"]"));
        radio.click();
    }

    private void changeProductVariantSelect(WebElement element, String visibleText) {
        WebElement optionSelected = element.findElement(By.cssSelector("option[selected]"));
        if (optionSelected.getText().equals(visibleText))
            return;
        Select dp = new Select(element);
        dp.selectByVisibleText(visibleText);
    }


}