package norakomi.com.MVP_RxJava_Retrofit2.data;

import java.util.ArrayList;

import norakomi.com.MVP_RxJava_Retrofit2.data.models.Poster;
import norakomi.com.MVP_RxJava_Retrofit2.data.models.SovietArtMePosters;
import norakomi.com.MVP_RxJava_Retrofit2.utils.App;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rik van Velzen, on 27-9-2016.
 */
public class DataManager {

    private final String TAG = DataManager.class.getSimpleName();

    public void loadSovietArtMePoster(final IDataManagerCallback dataManagerCallback) throws Exception {
        Retrofit retrofit = getRetrofitInstance(SovietArtMeService.BASE_URL);
        SovietArtMeService sovietArtMeService = retrofit.create(SovietArtMeService.class);
        Observable<SovietArtMePosters> posters = sovietArtMeService.loadPosters();

        posters.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SovietArtMePosters>() {
                    @Override
                    public void onCompleted() {
                        App.log(TAG, "completed!");
                    }

                    @Override
                    public void onError(Throwable t) {
                        App.logError(TAG, "in onError. Failed loading posters", t);
                        dataManagerCallback.onFailure(t);
                    }

                    @Override
                    public void onNext(SovietArtMePosters response) {
                        App.log(TAG, "onNext!");
                        dataManagerCallback.onDataLoaded((ArrayList<Poster>) response.getPosters());
                    }
                });
    }

    /**
     * @param baseUrl belonging to Retrofit service.
     * @return a Retrofit instance with a CallAdapter that will return
     * RxJava Observable<T>
     */
    private Retrofit getRetrofitInstance(String baseUrl) throws Exception {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }


}
