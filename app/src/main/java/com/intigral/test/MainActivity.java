package com.intigral.test;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.intigral.test.modal.MovieModal;
import com.intigral.test.retrofit.NetworkInterface.NetworkGetServices;
import com.intigral.test.utils.Utils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    int page = 1;
    Call<MovieModal> movieModalCall;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isOnline()) {
            getMoviesData(page);
        } else {
            Utils.getAppUtils(this).showToast(this, getResources().getString(R.string.check_internet_connection));
        }
        initToolBar();
    }


    public void initToolBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public void getMoviesData(int value) {
        movieModalCall = ApplicationData.getRestClient().createService(NetworkGetServices.class)
                .getMoviePayload(value);
        movieModalCall.enqueue(new Callback<MovieModal>() {
            @Override
            public void onResponse(Call<MovieModal> call, Response<MovieModal> response) {
                try {
                    if (response.body() != null && response.isSuccessful()) {
                        Log.d("Movie", "Response = " + response);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MovieModal> call, Throwable t) {
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }
}
