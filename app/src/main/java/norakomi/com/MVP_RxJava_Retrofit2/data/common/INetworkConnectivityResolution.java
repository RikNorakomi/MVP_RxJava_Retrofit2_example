package norakomi.com.MVP_RxJava_Retrofit2.data.common;

/**
 * Created by Rik van Velzen, on 28-9-2016.
 */
interface INetworkConnectivityResolution {
        void onConnectivityAvailable();
        void onConnectivityUnavailable();

}
