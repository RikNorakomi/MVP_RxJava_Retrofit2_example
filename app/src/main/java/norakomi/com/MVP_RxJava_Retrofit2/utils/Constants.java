package norakomi.com.MVP_RxJava_Retrofit2.utils;

/**
 * Created by Rik van Velzen, on 27-9-2016.
 */
public class Constants {

    public static final String URL_BASE_PATH = "http://sovietart.me";
    public static final String POSTER_THUMB_PATH = "/img/posters/190px/";

    public static final String THUMBNAIL_BASE_PATH = URL_BASE_PATH + POSTER_THUMB_PATH;

    /**
     * Private constructor in order to prevent accidental instantiation
     */
    private Constants() {}

    public static String getThumbnailBasePath(){
        return THUMBNAIL_BASE_PATH;
    }
}
