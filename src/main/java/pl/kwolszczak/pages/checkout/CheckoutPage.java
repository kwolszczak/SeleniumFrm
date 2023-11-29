package pl.kwolszczak.pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.common.CommonPage;

public class CheckoutPage extends CommonPage {

    private AddressComponent addressComponent;

    @FindBy(css = "section#checkout-addresses-step")
    private WebElement addressSection;


    public CheckoutPage(WebDriver driver) {
        super(driver);
        addressComponent = new AddressComponent(driver,addressSection);
    }

    public void changeBillingAddress(String address, String city, String zipcode,String state) {
        addressComponent.changeBillingAddress(address,city,zipcode,state);
    }
}
