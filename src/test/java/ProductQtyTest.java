import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductQtyTest extends TestBase{

    private int price;
    private WebElement inputQty;

    @BeforeMethod
    public void openPage() {
        driver.get("http://146.59.32.4/index.php?id_product=5&id_product_attribute=19&rewrite=today-is-a-good-day-framed-poster&controller=product&id_lang=2#/19-dimension-40x60cm");
        price = Integer.parseInt(driver.findElement(By.cssSelector(".current-price>span")).getAttribute("content"));
        inputQty = driver.findElement(By.id("quantity_wanted"));
    }

    @Test
    public void userCanUseKeyUpAndDownOnQuantity(){
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_DOWN);
        inputQty.sendKeys(Keys.ENTER);
        String btnCss = "#blockcart-modal .cart-content-btn a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnCss)));

        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal .product-quantity>strong")).getText()
                , "5");
        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal span.subtotal")).getText()
                , "$"+(5*price)+".00");
    }

    @Test
    public void verifyUserCanChangeQtyWithButtons() {
        WebElement upArrowBtn = driver.findElement(By.cssSelector(".input-group-btn-vertical>.bootstrap-touchspin-up"));
        WebElement downArrowBtn = driver.findElement(By.cssSelector(".input-group-btn-vertical>.bootstrap-touchspin-down"));
        WebElement addBtn = driver.findElement(By.cssSelector("button.add-to-cart"));
        upArrowBtn.click();
        upArrowBtn.click();
        upArrowBtn.click();
        downArrowBtn.click();
        downArrowBtn.click();
        addBtn.click();

        String btnCss = "#blockcart-modal .cart-content-btn a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnCss)));

        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal .product-quantity>strong")).getText()
                , "2");
        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal span.subtotal")).getText()
                , "$"+(2*price)+".00");

    }

    @Test
    public void verifyUserCanChangeQtyProvidingNumber() {
        int number = 11;
        inputQty.clear();
        inputQty.sendKeys(""+number);
        inputQty.sendKeys(Keys.ENTER);
//        Actions action = new Actions(driver);
//        action.click(inputQty).doubleClick(inputQty).sendKeys(""+number).sendKeys(Keys.ENTER).perform();


        String btnCss = "#blockcart-modal .cart-content-btn a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnCss)));

        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal .product-quantity>strong")).getText()
                , ""+number);
        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal span.subtotal")).getText()
                , "$"+(number*price)+".00");

    }
}