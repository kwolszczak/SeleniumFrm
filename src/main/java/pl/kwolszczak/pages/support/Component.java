package pl.kwolszczak.pages.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Component extends SupportPage{

    protected Component(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }


}
