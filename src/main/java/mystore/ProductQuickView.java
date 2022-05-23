package mystore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProductQuickView extends ProductCommon{

    public ProductQuickView() {
        modalCSS = "div[id^='quickview-modal'] ";
        this.waitForModal();
    }

    private void waitForModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(getCSSForModal(addToCartButtonCss))));
    }
}