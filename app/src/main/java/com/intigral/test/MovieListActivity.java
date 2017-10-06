package com.intigral.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ScrollView;

import com.intigral.test.fragments.MovieDetailFragment;
import com.intigral.test.fragments.MovieListFragment;
import com.intigral.test.interfaces.OnMovieItemClickListener;
import com.intigral.test.modal.MovieItem;
import com.intigral.test.utils.Constants;

import butterknife.BindView;


public class MovieListActivity extends BaseActivity implements OnMovieItemClickListener {

    private FragmentActivity activity;
    private static final String LOG_TAG = "MovieListActivity";
    private android.support.v4.app.FragmentManager fragmentManager;
    @BindView(R.id.sv_movie_list)
    ScrollView svMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        fragmentManager = getSupportFragmentManager();
        setMovieListFragments();
    }
    private void setMovieListFragments() {
        // Set Popular movie list fragment
        MovieListFragment popularMovies = MovieListFragment.newInstance(Constants.URL_SORT_BY_PUPOLAR,getResources().getString(R.string.popular));
        int id = R.id.popular_movie_list;
        ApplicationData.getInstance().setFragment(popularMovies, fragmentManager, id);

        // Set Top Rated movie list fragment
        MovieListFragment topRatedMovies = MovieListFragment.newInstance(Constants.URL_SORT_BY_RATED,getResources().getString(R.string.top_rated));
        id = R.id.toprated_movie_list;
        ApplicationData.getInstance().setFragment(topRatedMovies, fragmentManager, id);

        // Set Revenue movie list fragment
        MovieListFragment revenueMovies = MovieListFragment.newInstance(Constants.URL_SORT_BY_REVENUE,getResources().getString(R.string.revenue));
        id = R.id.revenue_movie_list;
        ApplicationData.getInstance().setFragment(revenueMovies, fragmentManager, id);
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_movie_list;
    }

    @Override
    public void onMovieItemClick(MovieItem item) {
       // Check if detail view exist in main activity then load detail fragment
        // in the same activity else start new activity
        if (findViewById(R.id.fl_movie_detail) != null) {
            MovieDetailFragment detailFragment = MovieDetailFragment.newInstance(item);
            ApplicationData.getInstance().setFragment(detailFragment, fragmentManager, R.id.fl_movie_detail);
        }
        else {
            Intent intent = new Intent(activity, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.KEY_MOVIE_ITEM, item);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
