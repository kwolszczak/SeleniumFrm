package configuration.driver.setup;

import org.openqa.selenium.edge.EdgeDriver;


public class SetupEdge extends SetupDriver {

    @Override
    void initDriverWithOptions() {
        driver = new EdgeDriver();

        if (maximize) {
            this.driver.manage().window().maximize();
        }
    }
}
