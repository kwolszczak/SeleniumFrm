package pl.kwolszczak.pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.common.CommonPage;

public class SigningPage extends CommonPage {

    @FindBy(css = "form#login-form input[name='email']")
    private WebElement emailInp;

    @FindBy(css = "form#login-form input[name='password']")
    private WebElement passwordInp;

    @FindBy(css = "form#login-form button#submit-login")
    private WebElement signinBtn;

    public SigningPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage login(String email, String password) {
        fill(emailInp,email);
        fill(passwordInp,password);
        click(signinBtn);
        return new AccountPage(driver);
    }
}
