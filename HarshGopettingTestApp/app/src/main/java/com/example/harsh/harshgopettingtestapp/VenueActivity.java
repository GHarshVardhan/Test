package com.example.harsh.harshgopettingtestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    List<Datum> table;
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        recyclerView = (RecyclerView) findViewById(R.id.list_item);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        inflateRecycler();
    }
    private void inflateRecycler() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ListData> call = apiService.getData();
        call.enqueue(new Callback<ListData>() {
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {
                table = response.body().getData();
                mRecyclerAdapter = new RecyclerAdapter(table,VenueActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mRecyclerAdapter);
                Log.d("Data", "Number of movies received: " + table.size());
            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {

            }


        });
    }
}
