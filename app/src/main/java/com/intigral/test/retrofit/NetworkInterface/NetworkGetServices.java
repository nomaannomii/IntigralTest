package com.intigral.test.retrofit.NetworkInterface;


import com.intigral.test.modal.MovieModal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkGetServices {

    //https://api.themoviedb.org/3/discover/movie?api_key=dd75a775c11c203b39079e9edd021cbb&sort_by=popularity.desc&page=3
    @GET("movie?api_key=dd75a775c11c203b39079e9edd021cbb&sort_by=popularity.desc&page=")
    Call<MovieModal> getMoviePayload(@Query("page") int page);

}