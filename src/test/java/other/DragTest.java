package other;

import storeTests.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;


public class DragTest extends BaseTest {

    @RepeatedTest(1)
    @DisplayName("Drag test")
    void dragAndDropTest() throws InterruptedException, IOException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demo.aspnetawesome.com/DragAndDropDemo");

        WebElement dragZone = driver.findElement(By.xpath("(//div[@class='dropZone'])[1]"));
        WebElement elementB = dragZone.findElement(By.xpath("(./div)[2]"));
        WebElement elementC = dragZone.findElement(By.xpath("(./div)[3]"));
        WebElement dropeZone = driver.findElement(By.cssSelector("div.example div.dropZone p:nth-last-child(1)"));
        WebElement elementA = driver.findElement(By.cssSelector("div.dropZone div:nth-child(2)"));

        Actions actions = new Actions(driver);


        actions.clickAndHold(elementA)
                .moveToElement(elementC)
                .moveToElement(dropeZone)
                .release()
                .build().perform();

        actions.clickAndHold(elementB)
                .moveToElement(elementC)
                .moveToElement(dropeZone)
                .release()
                .build().perform();

        actions.clickAndHold(elementC)
                .moveToElement(elementA)
                .moveToElement(dropeZone)
                .release()
                .build().perform();

        takeScreenShoot(driver);

   /*     actions.clickAndHold(elementA)
                .moveByOffset(10,0)
                .moveByOffset(40,0)
                .release()
                .build().perform();*/
        //  actions.clickAndHold(elementA).perform();
        //  WebElement a = driver.findElement(By.xpath("//div[@class='ditem o-plh']"));

/*
        String source = "div.dropZone div:nth-child(2)";
        String target = "div.example div.dropZone p:nth-last-child(1)";
        performDragAndDrop(driver, source, target);*/


        Thread.sleep(4000);
        driver.quit();
    }

    public static void performDragAndDrop(WebDriver driver, String cssSource, String cssTarget) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String java_script = """
                (function( $ ) {
                        $.fn.simulateDragDrop = function(options) {
                                return this.each(function() {
                                        new $.simulateDragDrop(this, options);
                                });
                        };
                        $.simulateDragDrop = function(elem, options) {
                                this.options = options;
                                this.simulateEvent(elem, options);
                        };
                        $.extend($.simulateDragDrop.prototype, {
                                simulateEvent: function(elem, options) {
                                        /*Simulating drag start*/
                                        var type = 'dragstart';
                                        var event = this.createEvent(type);
                                        this.dispatchEvent(elem, type, event);
                           
                                        /*Simulating drop*/
                                        type = 'drop';
                                        var dropEvent = this.createEvent(type, {});
                                        dropEvent.dataTransfer = event.dataTransfer;
                                        this.dispatchEvent($(options.dropTarget)[0], type, dropEvent);
                           
                                        /*Simulating drag end*/
                                        type = 'dragend';
                                        var dragEndEvent = this.createEvent(type, {});
                                        dragEndEvent.dataTransfer = event.dataTransfer;
                                        this.dispatchEvent(elem, type, dragEndEvent);
                                },
                                createEvent: function(type) {
                                        var event = document.createEvent("CustomEvent");
                                        event.initCustomEvent(type, true, true, null);
                                        event.dataTransfer = {
                                                data: {
                                                },
                                                setData: function(type, val){
                                                        this.data[type] = val;
                                                },
                                                getData: function(type){
                                                        return this.data[type];
                                                }
                                        };
                                        return event;
                                },
                                dispatchEvent: function(elem, type, event) {
                                        if(elem.dispatchEvent) {
                                                elem.dispatchEvent(event);
                                        }else if( elem.fireEvent ) {
                                                elem.fireEvent("on"+type, event);
                                        }
                                }
                        });
                })(jQuery);""";

        java_script = java_script + "$('" + cssSource + "').simulateDragDrop({ dropTarget: '" + cssTarget + "'});";

        js.executeScript(java_script);
    }

    public static void takeScreenShoot(WebDriver driver) throws IOException {

        String fileName = "screenshot.png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "target/" + fileName;
        FileUtils.copyFile(screenshot, new File(path));
    }
}
