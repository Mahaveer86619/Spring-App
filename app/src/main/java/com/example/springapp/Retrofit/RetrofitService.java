package com.example.springapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService(){
        initilizeRetrofit();
    }

    private void initilizeRetrofit() {
        retrofit = new Retrofit.Builder()
        .baseUrl("http://192.168.188.32:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
