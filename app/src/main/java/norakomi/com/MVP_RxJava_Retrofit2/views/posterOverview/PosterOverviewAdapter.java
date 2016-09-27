package norakomi.com.MVP_RxJava_Retrofit2.views.posterOverview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import norakomi.com.MVP_RxJava_Retrofit2.R;
import norakomi.com.MVP_RxJava_Retrofit2.data.models.Poster;
import norakomi.com.MVP_RxJava_Retrofit2.utils.App;

/**
 * Created by Rik van Velzen, on 25-9-2016.
 */
public class PosterOverviewAdapter extends RecyclerView.Adapter<PosterOverviewAdapter.ViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private final IPosterOverviewMvc.IPosterOverviewMvcListener clickListener;

    private ArrayList<Poster> posters = new ArrayList<>();

    public PosterOverviewAdapter(ArrayList<Poster> posters,
                                 IPosterOverviewMvc.IPosterOverviewMvcListener clickListener) {
        this.clickListener = clickListener;
        if (posters != null) { // todo check null check placement
            this.posters = posters;
        }
    }

    public void refreshPosters(@NonNull ArrayList<Poster> posters) {
        this.posters = posters;// todo check null check placement
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_over_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // move loading logic etc. to viewHolder to keep adapter care of SRP
        App.log(TAG, "in onBindViewHolder: pos=" + position);
        Poster poster = posters.get(position);
        holder.loadPoster(poster);
        holder.setClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = ViewHolder.class.getSimpleName();

        public ImageView imageView;
        private Poster poster;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.recycler_poster_item);
        }

        public void loadPoster(Poster poster) {
            this.poster = poster;
            final String url = poster.getThumbnailUrl();

            Glide.with(imageView.getContext())
                    .load(url)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            Log.e(TAG, "Glide load exception for url: " + url);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            // do nothing
                            return false;
                        }
                    })
                    .centerCrop()
                    .into(imageView);
        }

        /**
         * Connects the Presenter's MVC listener with ViewHolder's onClickListener and
         * let's presenter handle what should be done
         * */
        public void setClickListener(final IPosterOverviewMvc.IPosterOverviewMvcListener clickListener){
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onRecyclerItemClicked(poster);
                }
            });
        }
    }
}
