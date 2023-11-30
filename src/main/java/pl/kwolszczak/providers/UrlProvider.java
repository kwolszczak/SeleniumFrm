package pl.kwolszczak.providers;

import java.util.List;

public class UrlProvider {
    private static final String APP_URL = System.getProperty("environment.url");
    private static final String CONTROLLER_CATEGORY = "&controller=category&id_lang=2";

    public static final String ORDER_HISTORY = APP_URL + "?controller=history";
    public static final String SIGN_IN = APP_URL + "?controller=authentication&back=my-account";
    public static final String CREATE_ACCOUNT = APP_URL + "?controller=authentication&create_account=1";
    public static final String CART = APP_URL + "?controller=cart&action=show";
    public static final String ART = APP_URL + "?id_category=9" + CONTROLLER_CATEGORY;
    public static final String CLOTHES = APP_URL + "?id_category=3" + CONTROLLER_CATEGORY;
    public static final String CLOTHES_MEN = APP_URL + "?id_category=4" + CONTROLLER_CATEGORY;
    public static final String CLOTHES_WOMEN = APP_URL + "?id_category=5" + CONTROLLER_CATEGORY;
    public static final String ACCESSORIES = APP_URL + "?id_category=6" + CONTROLLER_CATEGORY;
    public static final String ACCESSORIES_HOME = APP_URL + "?id_category=8" + CONTROLLER_CATEGORY;
    public static final String ACCESSORIES_STATIONERY = APP_URL + "?id_category=7" + CONTROLLER_CATEGORY;

    private UrlProvider() {}
}
