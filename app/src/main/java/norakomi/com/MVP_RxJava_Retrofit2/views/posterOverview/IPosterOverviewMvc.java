package norakomi.com.MVP_RxJava_Retrofit2.views.posterOverview;

import java.util.ArrayList;

import norakomi.com.MVP_RxJava_Retrofit2.data.models.Poster;
import norakomi.com.MVP_RxJava_Retrofit2.views.IViewMvc;

/**
 * This interface corresponds to "poster overview screen" of the app where a list containing many
 * poster items should be displayed.
 */
public interface IPosterOverviewMvc extends IViewMvc {

    interface IPosterOverviewMvcListener {
        /**
         * This callback method will be invoked when one of the recycler items is being clicked
         *
         * @param poster item clicked
         */
        void onRecyclerItemClicked(Poster poster);
    }

    /**
     * Set a listener that will be notified by this MVC view
     *
     * @param listener listener that should be notified; null to clear
     */
    void setListener(IPosterOverviewMvcListener listener);

    void setPosters(ArrayList<Poster> posters);

}
