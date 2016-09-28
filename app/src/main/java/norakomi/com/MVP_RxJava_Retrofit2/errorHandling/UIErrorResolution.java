package norakomi.com.MVP_RxJava_Retrofit2.errorHandling;

import android.view.View;

import java.net.UnknownHostException;

import norakomi.com.MVP_RxJava_Retrofit2.R;
import norakomi.com.MVP_RxJava_Retrofit2.utils.App;

/**
 * Created by Rik van Velzen, on 28-9-2016.
 */
public class UIErrorResolution {

    private final String TAG = UIErrorResolution.class.getSimpleName();

    public UIResolver mUIResolver;

    public UIErrorResolution (View rootView, Throwable t) {
        mUIResolver = new UIResolver(rootView);
        determineErrorResolution(t);
    }

    private void determineErrorResolution(Throwable t) {
        // todo handle different exception scenario's

        if (t instanceof UnknownHostException){

        }

    }

    private void onInternalServerError() {
        mUIResolver.showSnackBar(R.string.error_http_exception);
    }

    private void onConnectivityAvailable() {
        mUIResolver.hidePersistentSnackBar();

    }

    private void onConnectivityUnavailable() {
        mUIResolver.showSnackBar(R.string.error_no_network_available);

    }

    private void onGenericRxException(Throwable t) {
        // todo do something on RxExceptions
        App.logError(TAG, "Generic Rx Exception", t);
    }

    private void onNotFound() {
        // todo do something on notFound
    }
}
