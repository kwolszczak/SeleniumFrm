package pl.kwolszczak.providers;

import lombok.Getter;

@Getter
public enum Subcategory {

    CLOTHS_MEN("MEN", UrlProvider.CLOTHES_MEN),
    CLOTHS_WOMEN("WOMEN", UrlProvider.CLOTHES_WOMEN),
    ACCESSORIES_HOME("HOME ACCESSORIES", UrlProvider.ACCESSORIES_HOME),
    ACCESSORIES_STATIONERY("STATIONERY", UrlProvider.ACCESSORIES_STATIONERY);

    private final String title;
    private final String url;

    Subcategory(String title, String url) {
        this.title = title;
        this.url = url;
    }

}
