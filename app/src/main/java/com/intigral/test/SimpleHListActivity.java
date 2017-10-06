package com.intigral.test;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.intigral.test.interfaces.OnMovieItemClickListener;
import com.intigral.test.modal.Result;


public class SimpleHListActivity extends BaseActivity implements OnMovieItemClickListener {

    private FragmentActivity activity;
    private static final String LOG_TAG = "SimpleHListActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

//        FragmentManager fm = getFragmentManager();
//        android.app.FragmentTransaction ft = fm.beginTransaction();
//        Bundle bundle;
//
//        MovieListFragment popularMovies = new MovieListFragment();
//        bundle = new Bundle();
//        bundle.putString("title", "Popular");
//        popularMovies.setArguments(bundle);
//        ft.add(R.id.popular_movie_list, popularMovies);

//        MovieListFragment topratedMovies = new MovieListFragment();
//        bundle = new Bundle();
//        bundle.putString("title", "Top Rated");
//        topratedMovies.setArguments(bundle);
//        ft.add(R.id.toprated_movie_list,  topratedMovies);
//
//        MovieListFragment revenueMovies = new MovieListFragment();
//        bundle = new Bundle();
//        bundle.putString("title", "Revenue");
//        revenueMovies.setArguments(bundle);
//        ft.add(R.id.revenue_movie_list,  revenueMovies);
        //ft.commit();
        setHealthGraphLayout();

    }

    private void setHealthGraphLayout() {
        Bundle bundle;
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        MovieListFragment popularMovies = new MovieListFragment();
        int id = R.id.popular_movie_list;
        bundle = new Bundle();
        bundle.putString("title", "Popular");
        bundle.putInt("urlType", 1);
        popularMovies.setArguments(bundle);
        ApplicationData.getInstance().setFragment(popularMovies, fragmentManager, id);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onMovieItemClick(Result item) {
        Toast.makeText(activity, "Activity: " + item.getOriginalTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
