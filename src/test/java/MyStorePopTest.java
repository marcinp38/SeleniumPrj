import mystore.BasePage;

import mystore.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyStorePopTest extends TestBase{
    @Test
    public void verifySearchAndAddToCart() {
        List<Float> itemPrice = new ArrayList<>();
        List<Integer> itemQty = new ArrayList<>();

        BasePage myStore = new BasePage();

        BasePage.homePage.navigateTo();
        BasePage.homePage.searchFor("poster");

        Assert.assertEquals(BasePage.searchResultPage.getResultsNumber(), 3);

        BasePage.searchResultPage.clickQuickViewOnProduct("TODAY POSTER");

        BasePage.productQuickView.changeProductVariant("Dimension", "80x120cm");
        itemPrice.add(BasePage.productQuickView.getItemPrice());
        BasePage.productQuickView.changeQuantity(2);
        itemQty.add(2);
        BasePage.productQuickView.addToCart();
        BasePage.cartPreview.close();

        BasePage.navigate.openClothes();
        BasePage.categoryPage.clickProductTile(1);
        itemPrice.add(BasePage.productPage.getItemPrice());
        itemQty.add(17);
        BasePage.productPage.changeProductVariant("Size", "M").changeProductVariant("Color", "Black").changeQuantity(17).addToCart();
        BasePage.cartPreview.clickProceedToCheckout();

        //asercje na cartPage
        for (int i=0; i < itemPrice.size(); i++) {
            String expectedPrice = formatPrice(itemPrice.get(i)*itemQty.get(i));
            Assert.assertEquals(BasePage.cartPage.getCartItem(i).getTotalPrice(), expectedPrice);
            Assert.assertEquals(BasePage.cartPage.getCartItem(i).getQty(), itemQty.get(i));
        }

        Assert.assertEquals(BasePage.cartPage.getCartSubtotal(), formatPrice(getTestSubtotal(itemPrice, itemQty)));
        Assert.assertEquals(BasePage.cartPage.getCartTotal(), formatPrice(getTestSubtotal(itemPrice, itemQty)+7));

    }

    private Float getTestSubtotal(List<Float> itemPrice, List<Integer> itemQty) {
        float itemsSubtotal = 0;
        for (int i=0; i< itemPrice.size(); i++)
            itemsSubtotal+=itemPrice.get(i)*itemQty.get(i);
        return itemsSubtotal;
    }

    private String formatPrice(Float price) {
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat formatter = new DecimalFormat("0.00", otherSymbols);
        return "$"+formatter.format(price);
    }
}