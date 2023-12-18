package pl.kwolszczak.pages.support.componentFactory;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class ComponentFactory2 {
    @SneakyThrows
    public static void initComponents(WebDriver driver, Object pageObject) {
        for (var field : pageObject.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(SetupComponent.class)) {
                continue;
            }

            field.setAccessible(true);
            var annotation = field.getAnnotation(SetupComponent.class);
            var locator = getLocator(annotation);

            if (locator == null) {
                continue;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                processListField(driver, field, pageObject, locator);
            } else {
                processSingleField(driver, field, pageObject, locator);
            }

        }
    }

    private static By getLocator(SetupComponent annotation) {
        if (!annotation.css().isEmpty()) {
            return By.cssSelector(annotation.css());
        } else if (!annotation.xpath().isEmpty()) {
            return By.xpath(annotation.xpath());
        }
        return null;
    }

    @SneakyThrows
    private static void processListField(WebDriver driver, Field field, Object pageObject, By locator) {
        var parameterizedType = (ParameterizedType) field.getGenericType();
        var listItemClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

        var webElements = driver.findElements(locator);
        var pageObjects = new ArrayList<>();
        for (var webElement : webElements) {
            var pageObj = listItemClass.getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, webElement);
            pageObjects.add(pageObj);
        }
        field.set(pageObject, pageObjects);
    }

    @SneakyThrows
    private static void processSingleField(WebDriver driver, Field field, Object pageObject, By locator) {
        var parentElement = driver.findElement(locator);
        var nestedPageObject = field.getType().getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, parentElement);
        field.set(pageObject, nestedPageObject);
    }
}
