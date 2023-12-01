package pl.kwolszczak.pages.history;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.common.CommonPage;

public class HistoryOrderDetailsPage extends CommonPage {

    @FindBy(xpath = "//section[@id='order-history']//tr//td[1]")
    private WebElement date;

    @FindBy(xpath = "//table[@id='order-products']//tr[3]//td[2]")
    private WebElement price;

    @FindBy(xpath = "//section[@id='order-history']//tr//td[2]")
    private WebElement payment;

    @FindBy(css = "article#delivery-address")
    private WebElement deliveryAddress;

    @FindBy(css = "article#invoice-address")
    private WebElement billingAddress;


    public HistoryOrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getDeliveryAddress() {
        return deliveryAddress.getText();
    }

    public String getInvoiceAddress() {
        return billingAddress.getText();
    }

    public String getPrice() {
        return price.getText().replaceAll("[$]","").trim();
    }
    public String getPayment() {
        return payment.getText().trim();
    }

    public String getDate() {
        return date.getText();
    }


}
