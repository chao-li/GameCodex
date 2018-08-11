package com.clidev.gamecodex.populargames.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.clidev.gamecodex.populargames.model.RetrofitClient;
import com.clidev.gamecodex.populargames.model.modeldata.Game;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class PopularGamesViewModel extends ViewModel {

    private static final String igdbBaseUrl = "https://api-endpoint.igdb.com/";
    private static final String FIELDS = "id,name,genres,cover,popularity";
    private static final String ORDER = "popularity:desc";
    private static final int LIMIT = 30;

    public MutableLiveData<List<Game>> mGameList = new MutableLiveData<>();


    public PopularGamesViewModel() {

        // Create the retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(igdbBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        // Build retrofit
        Retrofit retrofit = builder.build();

        // Create the retrofit client
        RetrofitClient client = retrofit.create(RetrofitClient.class);
        Call<List<Game>> call = client.getGame(FIELDS,
                ORDER,
                LIMIT);



        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                Timber.d("api call sucesss");

                List<Game> gameList = response.body();
                mGameList.setValue(gameList);

                Timber.d("First game: " + response.body().get(0).getName());

            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Timber.d("api call failed");
            }
        });

    }

    public MutableLiveData<List<Game>> getGameList() {
        return mGameList;
    }

}
