package norakomi.com.MVP_RxJava_Retrofit2.data;

import java.util.ArrayList;

import norakomi.com.MVP_RxJava_Retrofit2.data.models.Poster;

/**
 * Created by Rik van Velzen, on 27-9-2016.
 */
public interface IDataManagerCallback {

    void onDataLoaded(ArrayList<Poster> posters);

    void onFailure(Throwable t);
}
