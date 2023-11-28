package pl.kwolszczak.providers;

import lombok.Getter;

@Getter
public enum Category {

    CLOTHS("clothes", UrlProvider.CLOTHES),
    ACCESSORIES("accessories", UrlProvider.ACCESSORIES),
    ART("art", UrlProvider.ART);

    private final String name;
    private final String url;

    Category(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
