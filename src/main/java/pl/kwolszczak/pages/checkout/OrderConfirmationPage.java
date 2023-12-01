package pl.kwolszczak.pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.common.CommonPage;

public class OrderConfirmationPage extends CommonPage {

    @FindBy(xpath = "//section[@id='content']//li[contains(text(),'reference')]")
    private WebElement orderNumber;

    @FindBy(xpath = "//tr[contains(@class,'total-value')]/td[2]")
    private WebElement totalPrice;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getOrderNumber() {
        System.out.println(orderNumber.getText());
        return orderNumber.getText().split(":")[1].trim();
    }

    public String getOrderPrice() {

        return totalPrice.getText().replaceAll("[$]","").trim();
    }
}
