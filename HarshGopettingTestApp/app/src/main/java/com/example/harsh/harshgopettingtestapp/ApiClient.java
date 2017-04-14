package com.example.harsh.harshgopettingtestapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Harsh on 5/4/17.
 */


public class ApiClient {

    public static final String BASE_URL = "http://guidebook.com/service/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}