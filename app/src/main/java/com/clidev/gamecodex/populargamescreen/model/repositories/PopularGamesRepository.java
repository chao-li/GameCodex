package com.clidev.gamecodex.populargamescreen.model.repositories;

import android.arch.lifecycle.MutableLiveData;


import com.clidev.gamecodex.constants.RetrofitConstantFields;
import com.clidev.gamecodex.retrofit.PopularGamesRetrofitClient;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;

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


    // Retrofit fields
    private Retrofit.Builder mBuilder;
    private Retrofit mRetrofit;
    private PopularGamesRetrofitClient mClient;

    // Gamelist fields
    private MutableLiveData<List<Game>> mLiveDataGameList = new MutableLiveData<>();
    private List<Game> mGameList = new ArrayList<>();

    // Constructor
    public PopularGamesRepository() {
        // Create retrofit builder
        mBuilder = new Retrofit.Builder()
                .baseUrl(RetrofitConstantFields.igdbBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        // Build retrofit
        mRetrofit = mBuilder.build();

        // Create the retrofit client
        mClient = mRetrofit.create(PopularGamesRetrofitClient.class);
    }



    public MutableLiveData<List<Game>> queryGames(int platformId, String sortBy, int scrollCount,
                                                  boolean isReleased) {
        // Get current time and date
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(currentTime);

        Call<List<Game>> call;

        if (isReleased) {
            // Create call
            call = mClient.getReleasedGames(RetrofitConstantFields.FIELDS,
                    currentDate,
                    platformId,
                    sortBy,
                    RetrofitConstantFields.LIMIT,
                    RetrofitConstantFields.LIMIT * scrollCount);
        } else {
            call = mClient.getUpComingGames(RetrofitConstantFields.FIELDS,
                    currentDate,
                    platformId,
                    sortBy,
                    RetrofitConstantFields.LIMIT,
                    RetrofitConstantFields.LIMIT * scrollCount);
        }

        Timber.d("Call offset by: " + RetrofitConstantFields.LIMIT*scrollCount);

        // Perform the call for popular movie list
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful()) {
                    //mGameList = response.body();
                    mGameList.addAll(response.body());
                    mLiveDataGameList.setValue(mGameList);
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


}
