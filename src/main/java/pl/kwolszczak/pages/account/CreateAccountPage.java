package pl.kwolszczak.pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.common.CommonPage;

public class CreateAccountPage extends CommonPage {

    @FindBy(css = "")
    private WebElement emailInp;
    @FindBy(css = "")
    private WebElement passwordInp;
    @FindBy(css = "")
    private WebElement signinBtn;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }
}
