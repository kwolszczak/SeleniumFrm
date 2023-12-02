package configuration;

import configuration.loader.YamlLoader;
import configuration.model.Configuration;
import configuration.model.Environment;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class TestDataConf {

    private static final String CONFIG_YAML = "testData.yaml";
    private static final TestDataConf INSTANCE = new TestDataConf();
    private static Configuration config;


    private TestDataConf() {
        loadYamlConfig();
        loadConfigurationToSystemProperties();
    }

    public static TestDataConf getInstance() {
        return TestDataConf.INSTANCE;
    }

    private void loadYamlConfig() {
        if (config == null) {
            YamlLoader loader = new YamlLoader();
            loader.loadYaml(CONFIG_YAML);
            config = loader.getConfig();
        } else {
            log.debug("Config already loaded. No need to load configuration");
        }
    }

    private void loadConfigurationToSystemProperties() {

        var otherSections = config.getProperties();
        log.info("#### Loading configuration to SystemProperty: ####");
        setSystemProperties(otherSections);
    }

    private void setSystemProperties(Map<String, Object> map) {

        var entrySet = map.entrySet();
        for (var entry : entrySet) {
            var key = entry.getKey();
            var value = entry.getValue();

            if (value == null) {
                log.error("{} = null. Value can't be null", key);
                throw new RuntimeException();
            } else if (value instanceof String valueStr) {
                setSystemPropertyForString(key, valueStr);
            } else if (value instanceof Boolean val) {
                setSystemPropertyForString(key, Boolean.toString(val));
            } else if (value instanceof Number val) {
                setSystemPropertyForString(key, val.toString());
            } else {
                Map<String, Object> innerMap = (Map<String, Object>) entry.getValue();
                setSystemPropertyForMap(innerMap, key);
            }
        }
    }

    private void setSystemPropertyForString(String key, String value) {
        System.setProperty(key, value);
        log.debug("{} = {}", key, value);
    }

    private void setSystemPropertyForMap(Map<String, Object> map, String parentKey) {
        for (var entry : map.entrySet()) {

            var keyProperty = parentKey + "-" + entry.getKey();
            var valueProperty = entry.getValue();
            if (valueProperty == null) {
                log.error("{} = null. Value can't be null", keyProperty);
                throw new RuntimeException();
            } else {
                System.setProperty(keyProperty, valueProperty.toString());
                log.debug("{} = {}", keyProperty, valueProperty);
            }
        }
    }



}
