package pl.kwolszczak.providers;

import lombok.Getter;

@Getter
public enum Category {

    CLOTHS("clothes", UrlProvider.CLOTHES),
    ACCESSORIES("accessories", UrlProvider.ACCESSORIES),
    ART("art", UrlProvider.ART);

    private final String title;
    private final String url;

    Category(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
