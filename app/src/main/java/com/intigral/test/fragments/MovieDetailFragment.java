package com.intigral.test.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intigral.test.R;
import com.intigral.test.modal.MovieModal;
import com.intigral.test.modal.Result;
import com.intigral.test.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import retrofit2.Call;

public class MovieDetailFragment extends BaseFragment  {
    public static String KEY_MOVIE_ITEM = "movie_item";
    private Activity activity;
    private Result movie;

    @BindView(R.id.iv_movie_poster)
    ImageView ivPoster;

    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;

    @BindView(R.id.tv_movie_rating)
    TextView tvMovieRating;

    @BindView(R.id.tv_movie_desc)
    TextView tvMovieDesc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        Bundle bundle = this.getArguments();
        // Receive clicked movie fromparent
        movie =(Result) bundle.getSerializable(KEY_MOVIE_ITEM);


        tvMovieName.setText(movie.getTitle());
        tvMovieRating.setText(movie.getPopularity().toString());
        tvMovieDesc.setText(movie.getOverview());

        Picasso.with(activity)
            .load(Constants.URL_BASE_IMAGE + movie.getPosterPath())
            .placeholder(R.drawable.ic_local_movies_black_24dp)
            .into(ivPoster);
    }
    public static MovieDetailFragment newInstance(Result movieItem) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_MOVIE_ITEM, movieItem);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_movie_detail;
    }


}
