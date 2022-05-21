import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class StoreCart extends TestBase{

    String url = "http://146.59.32.4/index.php?id_product=6&rewrite=mug-the-best-is-yet-to-come&controller=product&id_lang=2";

    @BeforeMethod
    public void testSetup() {
        driver.get(url);
    }

    @Test
    public void shouldRemoveFromCart(){
        driver.findElement(By.className("add-to-cart")).click();
        String btnCSS = "#blockcart-modal .cart-content-btn a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnCSS)));
        btn.click();
        String koszCss = ".remove-from-cart";
        driver.findElement(By.cssSelector(koszCss)).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(koszCss)));
        Assert.assertEquals(driver.findElement(By.cssSelector(".cart-overview")).getText(),
                "There are no more items in your cart");
    }

}
