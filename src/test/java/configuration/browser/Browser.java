package configuration.browser;

public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("ie"),
    EDGE("edge"),
    SAFARI("safari");

    private final String name;

    Browser(String name) {
        this.name = name;
    }

    public static Browser get(String name) {
        for (var browser : values()) {
            if (browser.name.equalsIgnoreCase(name)) {
                return browser;
            }
        }
        throw new IllegalArgumentException("No matching browser for name: " + name);
    }
}
