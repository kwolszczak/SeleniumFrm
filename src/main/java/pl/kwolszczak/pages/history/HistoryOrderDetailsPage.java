package pl.kwolszczak.pages.history;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Order;
import pl.kwolszczak.models.OrderAddress;
import pl.kwolszczak.pages.common.CommonPage;

public class HistoryOrderDetailsPage extends CommonPage {

    @FindBy(xpath = "//section[@id='order-history']//tr//td[1]")
    private WebElement date;

    @FindBy(xpath = "//div[@id='order-infos']//*[contains(text(),'Order Reference')]")
    private WebElement orderNumber;

    @FindBy(xpath = "//table[@id='order-products']//tr[3]//td[2]")
    private WebElement price;

    @FindBy(xpath = "//section[@id='order-history']//tr//td[2]")
    private WebElement paymentStatus;

    @FindBy(css = "article#delivery-address address")
    private WebElement deliveryAddress;

    @FindBy(css = "article#invoice-address address")
    private WebElement billingAddress;


    public HistoryOrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public OrderAddress getDeliveryAddress() {
        return parseAddress(deliveryAddress);
    }

    public OrderAddress getInvoiceAddress() {
        return parseAddress(billingAddress);
    }

    public Double getPrice() {
        return parsePrice(price);
    }

    public String getPaymentStatus() {
        return paymentStatus.getText().trim();
    }

    public String getOrderNumber() {
        String regexOrderReference = "Order Reference (\\w+).*";
        String orderNumber = "$1";
        return this.orderNumber.getText().replaceAll(regexOrderReference, orderNumber);
    }


    public String getDate() {
        return date.getText();
    }

    public Order toOrderModel() {

        OrderAddress delivery = getDeliveryAddress();
        OrderAddress billing = getInvoiceAddress();
        Order order = new Order(getOrderNumber(), getDate(), getPrice());
        order.setDeliveryAddress(delivery);
        order.setBillingAddress(billing);

        return order;
    }

    private OrderAddress parseAddress(WebElement element) {
        var addres = element.getText().split("\n");
        var orderAddress = new OrderAddress();

        orderAddress.setAddress(addres[1]);
        orderAddress.setCity(addres[2].split(",")[0].trim());
        orderAddress.setZipcode(addres[2].split(",")[1].replaceAll("\\D", "").trim());
        orderAddress.setState(addres[2].split(",")[1].replaceAll("\\d", "").trim());

        return orderAddress;
    }

}
