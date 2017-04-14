package com.example.harsh.harshgopettingtestapp;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {

    @GET("v2/upcomingGuides/")
    Call<ListData> getData();

}
