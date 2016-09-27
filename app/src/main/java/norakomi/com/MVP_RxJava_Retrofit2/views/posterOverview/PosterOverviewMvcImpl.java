package norakomi.com.MVP_RxJava_Retrofit2.views.posterOverview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import norakomi.com.MVP_RxJava_Retrofit2.R;
import norakomi.com.MVP_RxJava_Retrofit2.data.models.Poster;
import norakomi.com.MVP_RxJava_Retrofit2.utils.App;

/**
 * This MVC view contains a recyclerView and intercepts click events
 * which are passed on to the controller (PosterOverviewFragment)
 */
public class PosterOverviewMvcImpl implements
        IPosterOverviewMvc{

    private final String TAG = PosterOverviewMvcImpl.class.getSimpleName();

    private View mRootView;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PosterOverviewAdapter mPosterOverviewAdapter;

    private IPosterOverviewMvcListener mListener;

    public PosterOverviewMvcImpl(LayoutInflater inflater,
                                 ViewGroup container,
                                 IPosterOverviewMvc.IPosterOverviewMvcListener listener) {
        // todo listener issue oplossen: doorpassen naar adapter opschonen
        try {
            mRootView = inflater.inflate(R.layout.mvc_view_poster_overview, container, false);
            mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.poster_overview_fragment_swipe_container);

            mPosterOverviewAdapter = new PosterOverviewAdapter(null, listener);
            mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.poster_overview_recycler);

            int numberOfColumns = 2;
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(numberOfColumns, LinearLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(mPosterOverviewAdapter);


            // setup SwipeToRefreshView
            // todo implement STR correctly
            mSwipeRefreshLayout.setEnabled(false);
            mSwipeRefreshLayout.setRefreshing(false);

        } catch (Exception e) {
            App.logError(TAG, "Caught exception in constructor", e);
        }
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void setListener(IPosterOverviewMvcListener listener) {
        mListener = listener;
    }

    @Override
    public void setPosters(ArrayList<Poster> posters) {
        App.log(TAG, "in setPosters()");
        mPosterOverviewAdapter.refreshPosters(posters);
    }
}
