package com.intigral.test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.intigral.test.fragments.MovieDetailFragment;
import com.intigral.test.fragments.MovieListFragment;
import com.intigral.test.interfaces.OnMovieItemClickListener;
import com.intigral.test.modal.Result;
import com.intigral.test.utils.Constants;

import butterknife.BindView;


public class MovieDetailActivity extends BaseActivity  {

    private FragmentActivity activity;
    private static final String LOG_TAG = "MovieDetailActivity";
    private android.support.v4.app.FragmentManager fragmentManager;
    private Result movie;

    @BindView(R.id.fl_movie_detail)
    FrameLayout flMovieDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        movie = (Result)getIntent().getExtras().getSerializable(MovieDetailFragment.KEY_MOVIE_ITEM);
        fragmentManager = getSupportFragmentManager();
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setMovieDetailFragment();

    }

    private void setMovieDetailFragment() {
        MovieDetailFragment detailFragment = MovieDetailFragment.newInstance(movie);
        ApplicationData.getInstance().setFragment(detailFragment, fragmentManager, R.id.fl_movie_detail);
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
