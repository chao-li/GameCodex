package com.clidev.gamecodex.populargames.model.repositories;

import android.arch.lifecycle.MutableLiveData;


import com.clidev.gamecodex.populargames.model.RetrofitClient;
import com.clidev.gamecodex.populargames.model.modeldata.Game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class PopularGamesRepository {

    // Constants
    private static final String igdbBaseUrl = "https://api-endpoint.igdb.com/";
    private static final String FIELDS = "id,name,genres,url,summary,aggregated_rating,popularity,cover,developers,publishers,player_perspectives,game_modes,first_release_date,release_dates,artworks,videos,multiplayer_modes";
    private static final String ORDER = "popularity:desc";
    private static final int LIMIT = 30;


    // Retrofit fields
    Retrofit.Builder mBuilder;
    Retrofit mRetrofit;
    RetrofitClient mClient;

    // Gamelist fields
    private MutableLiveData<List<Game>> mLiveDataGameList = new MutableLiveData<>();
    private List<Game> mGameList = new ArrayList<>();

    // Constructor
    public PopularGamesRepository() {
        // Create retrofit builder
        mBuilder = new Retrofit.Builder()
                .baseUrl(igdbBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        // Build retrofit
        mRetrofit = mBuilder.build();

        // Create the retrofit client
        mClient = mRetrofit.create(RetrofitClient.class);
    }

    // Retrieve popular movie data Retrofit
    public MutableLiveData<List<Game>> queryPopularGames() {
        //final MutableLiveData<List<Game>> gameList = new MutableLiveData<>();

        // Get current time and date
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String releaseAfterDate = dateFormat.format(currentTime);

        // Create call
        Call<List<Game>> call = mClient.getGame(FIELDS,
                releaseAfterDate,
                ORDER,
                LIMIT);


        // Perform the call for popular movie list
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful()) {

                    Timber.d("api call success");
                    Timber.d("First game: " + response.body().get(0).getName());

                    mGameList = response.body();
                    mLiveDataGameList.setValue(response.body());
                } else {
                    Timber.d("api call not successful");
                }

            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Timber.d("api call failed");
            }
        });

        return mLiveDataGameList;
    }

    // create method to query the next set of games data
    public MutableLiveData<List<Game>> queryNextSetPopularGames(int scrollCount) {
        // Get current time and date
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String releaseAfterDate = dateFormat.format(currentTime);

        // Create call
        Call<List<Game>> call = mClient.getNextSetGame(FIELDS,
                releaseAfterDate,
                ORDER,
                LIMIT,
                LIMIT*scrollCount);

        Timber.d("Call offset by: " + LIMIT*scrollCount);

        // begin the callback;
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful()) {
                    mGameList.addAll(response.body());
                    mLiveDataGameList.setValue(mGameList);
                } else {
                    Timber.d("Scroll call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Timber.d("Scroll call failed");
            }
        });

        return mLiveDataGameList;
    }



}
