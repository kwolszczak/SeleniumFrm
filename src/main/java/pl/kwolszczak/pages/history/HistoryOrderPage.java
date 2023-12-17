package pl.kwolszczak.pages.history;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.common.CommonPage;

import java.util.List;

public class HistoryOrderPage extends CommonPage {

    @FindBy(css = "tbody tr")
    private List<WebElement> orders;

    public HistoryOrderPage(WebDriver driver) {
        super(driver);
    }

    public void openOrderDetails(String orderName) {
        var order =orders.stream()
                .filter(we-> we.findElement(By.xpath("./th")).getText().equals(orderName))
                .map(we-> we.findElement(By.xpath("./td[6]/a[1]")))
                .findFirst()
                .orElse(null);

        click(order);
    }
}
