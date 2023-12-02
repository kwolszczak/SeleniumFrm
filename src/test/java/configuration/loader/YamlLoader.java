package configuration.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import configuration.model.Configuration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class YamlLoader {

    @Getter
    private Configuration config;

    public void loadYaml(String config_yaml) {

        if (config == null) {
            try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(config_yaml)) {
                ObjectMapper om = new ObjectMapper(new YAMLFactory());
                config = om.readValue(inputStream, Configuration.class);
            } catch (IOException exc) {
                log.error("%%%% Error, couldn't load config_yaml {}", config_yaml);
                throw new RuntimeException();
            }
        }
    }

}
