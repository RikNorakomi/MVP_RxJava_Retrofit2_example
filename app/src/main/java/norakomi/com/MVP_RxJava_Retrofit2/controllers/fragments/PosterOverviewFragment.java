package norakomi.com.MVP_RxJava_Retrofit2.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import norakomi.com.MVP_RxJava_Retrofit2.data.DataManager;
import norakomi.com.MVP_RxJava_Retrofit2.data.IDataManagerCallback;
import norakomi.com.MVP_RxJava_Retrofit2.data.models.Poster;
import norakomi.com.MVP_RxJava_Retrofit2.errorHandling.UIErrorResolution;
import norakomi.com.MVP_RxJava_Retrofit2.utils.App;
import norakomi.com.MVP_RxJava_Retrofit2.views.posterOverview.IPosterOverviewMvc;
import norakomi.com.MVP_RxJava_Retrofit2.views.posterOverview.PosterOverviewMvcImpl;

/**
 * A Fragment containing a list of SovietArtMe Posters
 */
public class PosterOverviewFragment extends AbstractFragment implements
        IPosterOverviewMvc.IPosterOverviewMvcListener,
        IDataManagerCallback {

    private final String TAG = PosterOverviewFragment.class.getSimpleName();
    private IPosterOverviewMvc mPosterOverviewMVC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Instantiate MVC view and set the fragment as listener
        mPosterOverviewMVC = new PosterOverviewMvcImpl(inflater, container, this);
//        mPosterOverviewMVC.setListener(this);

        // Return the root view of the associated MVC view
        return mPosterOverviewMVC.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            new DataManager().loadSovietArtMePoster(this);
        } catch (Exception e) {
            App.logError(TAG, "Unable to load posters", e);
            onFailure(e);
        }
    }

    @Override
    public void onRecyclerItemClicked(Poster poster) {
        App.log(TAG, "in onRecyclerItemClicked. Poster with title: " + poster.title);
        //todo do something on poster click
    }


    //IDataManagerCallback methods
    @Override
    public void onDataLoaded(ArrayList<Poster> posters) {
        mPosterOverviewMVC.setPosters(posters);
    }

    @Override
    public void onFailure(Throwable t) {
        App.logError(TAG, "Failed loading posters: " + t.toString());
        new UIErrorResolution(mPosterOverviewMVC.getRootView(), t);
    }
    //End IDataManagerCallback methods
}
