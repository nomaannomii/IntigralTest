package com.intigral.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intigral.test.adapters.HorizontalAdapter;
import com.intigral.test.fragments.BaseFragment;
import com.intigral.test.modal.MovieModal;
import com.intigral.test.modal.Result;
import com.intigral.test.retrofit.NetworkInterface.NetworkGetServices;
import com.intigral.test.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private Activity activity;
    private String strCategoryTitle;
    private List<Result> horizontalList;
    private HorizontalAdapter horizontalAdapter;

    @BindView(R.id.ho)
    RecyclerView hRecyclerView;

    @BindView(R.id.rl_progress)
    RelativeLayout progressBar;

    @BindView(R.id.category_title)
    TextView categoryTitle;
    private int urlType;

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
        urlType = bundle.getInt("urlType");
        categoryTitle.setText(strCategoryTitle);
        horizontalList = new ArrayList<>();
        /*send call to server*/
        if (urlType == 1) {
            getMoviesData(page);
        }
    }

    public void getMoviesData(int value) {
        movieModalCall = ApplicationData.getRestClient().createService(NetworkGetServices.class).getMoviePayload(value);
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
        return R.layout.fragment_category_movielist;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(activity, "onItemClick: " + position, Toast.LENGTH_LONG).show();
    }

}
