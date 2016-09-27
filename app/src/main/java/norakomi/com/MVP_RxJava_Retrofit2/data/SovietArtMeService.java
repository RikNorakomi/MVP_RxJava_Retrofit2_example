package norakomi.com.MVP_RxJava_Retrofit2.data;

import norakomi.com.MVP_RxJava_Retrofit2.data.models.SovietArtMePosters;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Rik van Velzen, on 26-9-2016.
 */
public interface SovietArtMeService {

    String BASE_URL = "http://www.norakomi.com/assets/json/";

    /**
     * Loads posters data from json
     */
    @GET("soviet_art.json")
    Observable<SovietArtMePosters> loadPosters();
}
