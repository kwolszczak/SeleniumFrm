package pl.kwolszczak.pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pl.kwolszczak.pages.support.Component;

public class AddressComponent extends Component {

    @FindBy(css = "a[data-link-action='different-invoice-address']")
    private WebElement billingAddressLink;

    @FindBy(css = "div#invoice-address")
    private WebElement invoiceAddressSection;

    @FindBy(css = "input[name='address1']")
    private WebElement addressInp;

    @FindBy(css = "input[name='city']")
    private WebElement cityInp;

    @FindBy(css = "input[name='postcode']")
    private WebElement zipcodeInp;

    @FindBy(css = "select[name='id_state']")
    private WebElement stateSelect;

    @FindBy(css = "button[type='submit']")
    private WebElement continueBtn;

    protected AddressComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    private void clickBillingAddressDiffers() {
        clickIt(billingAddressLink);
        wait.until(ExpectedConditions.visibilityOfAllElements(invoiceAddressSection));

    }
    protected void changeBillingAddress(String address, String city, String zipcode,String state) {
        clickBillingAddressDiffers();

        fillIn(addressInp,address);
        fillIn(cityInp,city);
        fillIn(zipcodeInp, zipcode);
        Select select = new Select(stateSelect);
        select.selectByVisibleText(state);
        clickIt(continueBtn);
    }
}
