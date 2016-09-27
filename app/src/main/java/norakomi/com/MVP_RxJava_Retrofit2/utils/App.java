package norakomi.com.MVP_RxJava_Retrofit2.utils;

import android.util.Log;

/**
 * Created by Rik van Velzen, on 25-9-2016.
 * This class contains static utility methods for logging
 */
public class App {

    private static final String TAG_PREFIX = "MVC: ";

    /**
     * Private constructor in order to prevent accidental instantiation
     */
    private App() {}

    public static void logError(String tag, String msg) {
        Log.e(TAG_PREFIX + tag, msg);
    }

    public static void logError(String tag, String msg, Throwable t) {
        Log.e(TAG_PREFIX + tag, msg, t);
    }

    public static void log(String tag, String msg) {
        Log.v(TAG_PREFIX + tag, msg);
    }
}
