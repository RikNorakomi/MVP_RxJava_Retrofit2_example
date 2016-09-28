package norakomi.com.MVP_RxJava_Retrofit2.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Rik van Velzen, on 28-9-2016.
 */
public class AppContext extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        AppContext.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AppContext.context;
    }

}
