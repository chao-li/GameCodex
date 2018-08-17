package com.clidev.gamecodex.gamedetails.model.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.clidev.gamecodex.constants.RetrofitConstantFields;
import com.clidev.gamecodex.retrofit.GameDetailsRetrofitClient;
import com.clidev.gamecodex.populargames.model.modeldata.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class GameDetailsRepository {

    // Retrofit fields
    private Retrofit.Builder mBuilder;
    private Retrofit mRetrofit;
    private GameDetailsRetrofitClient mClient;

    // Variable
    private MutableLiveData<Game> mGameLiveData = new MutableLiveData<>();

    // Constructor
    public GameDetailsRepository() {
        // Create retrofit builder
        mBuilder = new Retrofit.Builder()
                .baseUrl(RetrofitConstantFields.igdbBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        // Build retrofit
        mRetrofit = mBuilder.build();

        // Create the retrofit client
        mClient = mRetrofit.create(GameDetailsRetrofitClient.class);
    }

    // Retrieve game data


    public MutableLiveData<Game> getGame(Long gameId) {
        // Create the call
        Call<List<Game>> call = mClient.getThisGame(gameId,
                RetrofitConstantFields.FIELDS);

        // Perform the call
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful()) {
                    Timber.d("Api call successful");

                    mGameLiveData.setValue(response.body().get(0));
                } else {
                    Timber.d("response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Timber.d("Api call failed");
            }
        });

        return mGameLiveData;
    }
}
