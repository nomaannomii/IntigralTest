package com.intigral.test.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intigral.test.ApplicationData;
import com.intigral.test.R;
import com.intigral.test.adapters.HorizontalAdapter;
import com.intigral.test.modal.MovieModal;
import com.intigral.test.modal.MovieItem;
import com.intigral.test.retrofit.NetworkInterface.NetworkGetServices;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private static String KEY_MOVIE_LIST_TYPE = "movie_list_type", KEY_TITLE ="title";



    private Activity activity;
    private String strCategoryTitle;
    private List<MovieItem> horizontalList;
    private HorizontalAdapter horizontalAdapter;




    @BindView(R.id.ho)
    RecyclerView hRecyclerView;

    @BindView(R.id.rl_progress)
    RelativeLayout progressBar;

    @BindView(R.id.category_title)
    TextView categoryTitle;
    private String movieListType;

    int page = 1;
    Call<MovieModal> movieModalCall;

    MovieModal movieModal;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        Bundle bundle = this.getArguments();
        strCategoryTitle = bundle.getString("title");
        movieListType = bundle.getString(KEY_MOVIE_LIST_TYPE);
        categoryTitle.setText(strCategoryTitle);
        horizontalList = new ArrayList<>();
        /*send call to server*/
        getMoviesData(page, movieListType);
    }

    public void getMoviesData(int page, String movieListType) {
        movieModalCall = ApplicationData.getRestClient().createService(NetworkGetServices.class).getMoviePayload(page, movieListType);
        movieModalCall.enqueue(new Callback<MovieModal>() {
            @Override
            public void onResponse(Call<MovieModal> call, Response<MovieModal> response) {
                try {
                    setVisibility();
                    if (response.body() != null && response.isSuccessful()) {
                        movieModal = response.body();
                        horizontalList.addAll(movieModal.getResults());
                        horizontalAdapter = new HorizontalAdapter(horizontalList, getActivity());
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
                        hRecyclerView.setLayoutManager(horizontalLayoutManager);
                        hRecyclerView.setAdapter(horizontalAdapter);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    setVisibility();
                }
            }

            @Override
            public void onFailure(Call<MovieModal> call, Throwable t) {
                setVisibility();
            }
        });
    }
    public static MovieListFragment newInstance(String movieListType, String title) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MOVIE_LIST_TYPE, movieListType);
        bundle.putString(KEY_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }
    private void setVisibility() {
        progressBar.setVisibility(View.GONE);
        hRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_movielist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(activity, "onItemClick: " + position, Toast.LENGTH_LONG).show();
    }

}
