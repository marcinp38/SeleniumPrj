import org.testng.Assert;
import org.testng.annotations.*;

public class Tester {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Do this before Class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Do this before Method");
    }

    @Test
    public void test1() {
        Assert.assertEquals(OperacjeMatematyczne.dodaj(2, 3), 5);
    }
    @Test
    public void test2() {
        Assert.assertEquals(OperacjeMatematyczne.pomnoz(2, 3), 10);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Do this after Method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Do this after Class");
    }

}
