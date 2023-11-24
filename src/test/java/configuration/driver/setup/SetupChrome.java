package configuration.driver.setup;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SetupChrome extends SetupDriver {

    @Override
    void initDriverWithOptions() {

        String path = downloadDir;
        File file = new File(path);

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", file.getAbsolutePath());
        chromeOptions.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(chromeOptions);

        if (maximize) {
            this.driver.manage().window().maximize();
        }
    }
}
