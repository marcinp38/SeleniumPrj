import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DragAndDropTest extends TestBase {

    String url = "https://seleniumui.moderntester.pl/droppable.php";
    private Actions action;
    private WebElement dropZone;
    private WebElement draggable;

    @BeforeMethod
    public void setupDragAndDrop() {
        driver.get(url);
        draggable = driver.findElement(By.id("draggable"));
        dropZone = driver.findElement(By.id("droppable"));
        action = new Actions(driver);
    }

    @Test
    public void DragAndDrop() {
        action.dragAndDrop(draggable, dropZone).perform();
        Assert.assertEquals(dropZone.getText(), "Dropped!");
        logger.info(dropZone.getText());
    }

    @Test
    public void holdAndMoveOver() {
        action.clickAndHold(draggable).moveToElement(dropZone).release().perform();
        Assert.assertEquals(dropZone.getText(), "Dropped!");
        logger.info(dropZone.getText());
    }

    @Test
    public void moveByOffset() {
        action.clickAndHold(draggable)
                .moveByOffset(dropZone.getLocation().x - draggable.getLocation().x +5,10)
                .release().perform();

        Assert.assertEquals(dropZone.getText(), "Dropped!");
        logger.info(dropZone.getText());
    }

}
