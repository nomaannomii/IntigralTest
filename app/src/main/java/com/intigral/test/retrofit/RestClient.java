package com.intigral.test.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static final String API_BASE_URL = "https://api.themoviedb.org/3/discover/";
    private Retrofit builder;
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public RestClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        httpClient.addInterceptor(logging);
        OkHttpClient shortHttpClient = httpClient.readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        builder = new Retrofit.Builder().
                baseUrl(API_BASE_URL).
                addConverterFactory(new ToStringConverterFactory()).
                addConverterFactory(GsonConverterFactory.create(gson)).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                client(shortHttpClient).
                build();
    }

    public <S> S createService(Class<S> serviceClass) {
        //builder = builder.client(client).build();
        return builder.create(serviceClass);
    }
}