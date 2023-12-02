package other;

import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.Callable;

 class PopUpTest {
    private static WebDriver driver;

    @RepeatedTest(10)
    void popupTest() throws InterruptedException {
        var mealName = "My first dinner";
        var mealDate = "12/5/2024";
        var chef = "Tom Smykowski";
        var meal = "Wheat";
        var bonusMeal = "Barley";
        //driver = new FirefoxDriver();
      driver = new ChromeDriver();
        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));


        driver.get("https://demo.aspnetawesome.com/PopupFormDemo");

        //Main Window
        driver.findElement(By.xpath("//button[@class='awe-btn'][contains(text(),'Create')]")).click();
        Awaitility.waitAtMost(Duration.ofSeconds(5)).until(popupWait);

        //NAME + DATE
        var name = driver.findElement(By.cssSelector("input#createDinnerName-awed"));
        var date = driver.findElement(By.cssSelector("input#createDinnerDate"));
        var chefBtn = driver.findElement(By.xpath("(//button[@class='awe-btn awe-openbtn awe-lkpbtn'])[1]"));

        name.sendKeys(mealName);
        date.sendKeys(mealDate);

        //CHEF
        chefBtn.click();
        Awaitility.waitAtMost(Duration.ofSeconds(5)).until(searchChef);

        var okChefBtn = driver.findElement(By.xpath("//div[@id='createDinnerChefId-awepw']/following-sibling::div//button[contains(text(),'Ok')]"));
        var moreChefBtn = driver.findElement(By.cssSelector("div.awe-morebtn"));
        try {
            while (moreChefBtn.isDisplayed()) {
                moreChefBtn.click();
                try {
                    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("li div.awe-loading"))));
                } catch (Exception e) {

                }

                moreChefBtn = driver.findElement(By.cssSelector("div.awe-morebtn"));
            }
        } catch (Exception e) {
            System.out.println("no 'more' ever");
        }
        var searchChefList = driver.findElements(By.cssSelector("div.awe-list.awe-srlcont li"));
        searchChefList.stream().filter(we -> we.getText().equals(chef)).findFirst().get().click();
        okChefBtn.click();

        //MEAL
        var mealsBtn = driver.findElement(By.xpath("(//button[@class='awe-btn awe-openbtn awe-lkpbtn'])[2]"));
        mealsBtn.click();

        Awaitility.waitAtMost(Duration.ofSeconds(5)).until(searchMeal);
        var okMealBtn = driver.findElement(By.xpath("//div[@id='createDinnerMealsIds-awepw']/following-sibling::div//button[contains(text(),'Ok')]"));
        var searchMeal = driver.findElement(By.xpath("//div[@id='createDinnerMealsIds-awepw']//input[@name='search']"));
        searchMeal.sendKeys(meal);
        searchMeal.sendKeys(Keys.ENTER);
        try {
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("li div.awe-loading"))));
        } catch (Exception e) {

        }


        var foundedMeal = driver.findElement(By.cssSelector("div.awe-list.awe-srlcont li button"));
        foundedMeal.click();
        okMealBtn.click();

        //BONUS MEAL
        var bonusMealBtn = driver.findElement(By.cssSelector("button#createDinnerBonusMealId-awed"));
        bonusMealBtn.click();
        var bonusMeals = driver.findElements(By.cssSelector("div#createDinnerBonusMealId-dropmenu li"));
        bonusMeals.stream().filter(we -> we.getText().equals(bonusMeal)).findFirst().get().click();

        //ACCEPT MEAL
        var okFinal = driver.findElement(By.xpath("//div[@id='createDinner']/following-sibling::div//button[contains(text(),'Ok')]"));
        okFinal.click();

        //POPUP
        Awaitility.waitAtMost(Duration.ofSeconds(5)).until(alertWait);
        Alert alert = driver.switchTo().alert();
        var alertInfo = alert.getText();
        alert.accept();


        Assertions.assertThat(alertInfo).isEqualTo("dinner created");
        driver.quit();
    }


    Callable<Boolean> searchMeal = () -> {
        try {
            return driver.findElements(By.cssSelector("div.awe-list.awe-srlcont li button")).size() != 0;
        } catch (Exception e) {
            return false;
        }
    };

    Callable<Boolean> searchChef = () -> {
        try {
            return driver.findElements(By.cssSelector("div.awe-list.awe-srlcont li")).size() != 1;
        } catch (Exception e) {
            return false;
        }
    };

    Callable<Boolean> alertWait = () -> {
        try {
            return driver.switchTo().alert() != null;
        } catch (Exception e) {
            return false;
        }
    };


    Callable<Boolean> popupWait = () -> {
        try {
            return driver.findElement(By.cssSelector("input#createDinnerName-awed")) != null;
        } catch (Exception e) {
            return false;
        }
    };


}
