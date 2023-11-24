package configuration.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import configuration.model.Configuration;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.InputStream;


public class YamlLoader {

    @Getter
    private Configuration config;
    private static final Logger log = LoggerFactory.getLogger(YamlLoader.class);
    private static final String CONFIG_YAML = "config.yaml";

    public void loadYaml() {

        if (config == null) {
            try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(CONFIG_YAML)) {
                ObjectMapper om = new ObjectMapper(new YAMLFactory());
                config = om.readValue(inputStream, Configuration.class);
            } catch (IOException exc) {
                log.error("%%%% Error, couldn't load config_yaml {}", CONFIG_YAML);
                throw new RuntimeException();
            }
        }
    }

}
