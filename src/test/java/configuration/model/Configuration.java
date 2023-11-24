package configuration.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
    private List<Environment> environment;
    private Map<String, Object> properties = new LinkedHashMap<>();

    public List<Environment> getEnvironment() {
        return environment;
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    @JsonAnySetter
    private void setProperties(String key, Object value) {
        this.properties.put(key,value);
    }
}
