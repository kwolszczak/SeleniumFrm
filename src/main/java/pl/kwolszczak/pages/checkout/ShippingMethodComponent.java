package pl.kwolszczak.pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pl.kwolszczak.pages.support.Component;

public class ShippingMethodComponent extends Component {


    @FindBy(css = "button[name='confirmDeliveryOption']")
    private WebElement continueBtn;

    protected ShippingMethodComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }
    
    protected void clickContinue() {
      clickIt(continueBtn);
    }
}
