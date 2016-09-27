package norakomi.com.MVP_RxJava_Retrofit2.data.models;

import norakomi.com.MVP_RxJava_Retrofit2.utils.Constants;

/**
 * Created by Rik van Velzen, on 25-9-2016.
 */
public class Poster {

    public String title;
    public String filename;

    public String getThumbnailUrl() {
        return Constants.getThumbnailBasePath() + filename;
    }

    @Override
    public String toString() {
        return title != null ? title : "";
    }
}
