package pl.kwolszczak.pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.OrderAddress;
import pl.kwolszczak.pages.common.CommonPage;

public class CheckoutPage extends CommonPage {

    private AddressComponent addressComponent;
    private ShippingMethodComponent shippingComponent;
    private PaymentComponent paymentComponent;

    @FindBy(css = "section#checkout-addresses-step")
    private WebElement addressSection;

    @FindBy(css = "section#checkout-delivery-step")
    private WebElement shippingSection;
    @FindBy(css = "section#checkout-payment-step")
    private WebElement paymentSection;


    public CheckoutPage(WebDriver driver) {
        super(driver);
        addressComponent = new AddressComponent(driver,addressSection);
        shippingComponent = new ShippingMethodComponent(driver,shippingSection);
        paymentComponent = new PaymentComponent(driver, paymentSection);
    }

    public CheckoutPage changeBillingAddress(String address, String city, String zipcode,String state) {
        addressComponent.changeBillingAddress(address,city,zipcode,state);
        return this;
    }
    public CheckoutPage changeBillingAddress(OrderAddress orderAddress) {
        addressComponent.changeBillingAddress(orderAddress.getAddress(),orderAddress.getCity(),orderAddress.getZipcode(),orderAddress.getState());
        return this;
    }

    public CheckoutPage acceptShippingMethod() {
        shippingComponent.clickContinue();
        return this;
    }

    public OrderConfirmationPage placeOrder() {
        paymentComponent.placeOrder();
        return new OrderConfirmationPage(driver);
    }
}
