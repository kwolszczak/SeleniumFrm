package pl.kwolszczak.pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.support.Component;

public class PaymentComponent extends Component {


    @FindBy(css = "div#payment-confirmation button")
    private WebElement placeOrderBtn;

    @FindBy(css = "input#payment-option-1")
    private WebElement payByCheckBtn;

    @FindBy(css = "input[id='conditions_to_approve[terms-and-conditions]']")
    private WebElement approveConditionsBtn;

    protected PaymentComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }


    protected void placeOrder() {
        selectIt(payByCheckBtn);
        selectIt(approveConditionsBtn);
        clickIt(placeOrderBtn);

    }
}
