package pl.kwolszczak.providers;

import lombok.Getter;

@Getter
public enum Subcategory {
    CLOTHS_MEN("MEN", UrlProvider.CLOTHS_MEN),
    CLOTHS_WOMEN("WOMEN", UrlProvider.CLOTHS_WOMEN),
    ACCESSORIES_HOME("HOME ACCESSORIES", UrlProvider.ACCESSORIES_HOME),
    ACCESSORIES_STATIONERY("STATIONERY", UrlProvider.ACCESSORIES_STATIONERY);

    private String name;
    private String url;

    Subcategory(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
