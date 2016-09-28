package norakomi.com.MVP_RxJava_Retrofit2.data.common;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Rik van Velzen, on 28-9-2016.
 */


interface IRxHttpResolution {
    void onHttpException(HttpException httpException);

    void onGenericRxException(Throwable t);
}


