package pl.kwolszczak.pages.support.componentFactory;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.kwolszczak.pages.categories.FilterComponent;

import java.lang.reflect.Field;

public class CustomPageFactory {



/*    public static Object init(WebDriver driver, Object page) throws NoSuchMethodException {

        initElements(new CustomFieldDecorator(new DefaultElementLocatorFactory(driver), driver, page), page);
        return page;
    }*/

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

            if (field.getAnnotation(FindByCustom.class) != null) {
                By by;
                WebElement we = null;
                by = By.cssSelector(field.getAnnotation(FindByCustom.class).css());
                we = driver.findElement(by);

               /* if (List.class.isAssignableFrom(field.getType())) {
                    var parameterizedType = (ParameterizedType) field.getGenericType();
                    var listItemClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    List<WebElement> webElements = driver.findElements(by);
                    var pageObjects = new ArrayList<>();
                    for (var webElement : webElements) {
                        var pageObj = listItemClass.getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, webElement);
                        pageObjects.add(pageObj);
                    }
                    field.setAccessible(true);
                    field.set(page, pageObjects);
                }*/
                try {
                    field.setAccessible(true);
                   // var nestedPageObject = field.getType().getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, we);
                    field.set(page, new FilterComponent(driver, we));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}
