package pl.kwolszczak.providers;

import java.util.List;

public class UrlProvider {
    private static String appUrl = System.getProperty("environment.url");
    public static final String CLOTHES = appUrl + "?id_category=3&controller=category&id_lang=2";
    public static final String CLOTHS_MEN = appUrl + "?id_category=4&controller=category&id_lang=2";
    public static final String CLOTHS_WOMEN = appUrl + "?id_category=5&controller=category&id_lang=2";
    public static final String ACCESSORIES = appUrl + "?id_category=6&controller=category&id_lang=2";
    public static final String ACCESSORIES_STATIONERY = appUrl + "?id_category=7&controller=category&id_lang=2";
    public static final String ACCESSORIES_HOME = appUrl + "?id_category=8&controller=category&id_lang=2";
    public static final String ART = appUrl + "?id_category=9&controller=category&id_lang=2";

    public static List<String> CATEGORIES = List.of(CLOTHES, ACCESSORIES, ART);
}
