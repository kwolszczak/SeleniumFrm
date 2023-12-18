package pl.kwolszczak.pages.support.componentFactory;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class ComponentFactory {

    private ComponentFactory() {
    }


    public static void init(WebDriver driver, Object page) {
        initElements(page, driver);
    }


    public static void initElements(Object page, WebDriver driver) {
        Class<?> proxyIn = page.getClass();
        while (proxyIn != Object.class) {
            proxyFields(page, proxyIn, driver);
            proxyIn = proxyIn.getSuperclass();
        }
    }

    @SneakyThrows
    private static void proxyFields(Object page, Class<?> proxyIn, WebDriver driver) {
        Field[] fields = proxyIn.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(SetupComponent.class) == null) {
                continue;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                setupListFields(page, driver, field);
            } else {
                setupSingleField(page, driver, field);
            }
        }
    }


    @SneakyThrows
    private static void setupListFields(Object pageObj, WebDriver driver, Field field) {
        By by = By.cssSelector(field.getAnnotation(SetupComponent.class).css());
        List<WebElement> webElements = driver.findElements(by);

        var parameterizedType = (ParameterizedType) field.getGenericType();
        var listItemClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        var componentObjs = new ArrayList<>();

        for (var webElement : webElements) {
            var component = listItemClass.getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, webElement);
            componentObjs.add(component);
        }

        field.setAccessible(true);
        field.set(pageObj, componentObjs);
    }

    @SneakyThrows
    private static void setupSingleField(Object pageObj, WebDriver driver, Field field) {

        By by = By.cssSelector(field.getAnnotation(SetupComponent.class).css());
        WebElement parentWElement = driver.findElement(by);
        var componentObj = field.getType().getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, parentWElement);

        field.setAccessible(true);
        field.set(pageObj, componentObj);
    }
}

