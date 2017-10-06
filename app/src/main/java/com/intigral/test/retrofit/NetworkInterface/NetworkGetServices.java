package com.intigral.test.retrofit.NetworkInterface;


import com.intigral.test.modal.MovieModal;
import com.intigral.test.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkGetServices {
    @GET("movie?"+Constants.URL_API_KEY+Constants.URL_API_KEY_VAL)
    Call<MovieModal> getMoviePayload(@Query(Constants.URL_PAGE) int page, @Query(Constants.URL_SORT_BY) String sortBy);
}