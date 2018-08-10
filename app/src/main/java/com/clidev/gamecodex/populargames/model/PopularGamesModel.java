package com.clidev.gamecodex.populargames.model;

import android.arch.lifecycle.LiveData;

import com.clidev.gamecodex.populargames.model.modeldata.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class PopularGamesModel {




    private LiveData<List<Game>> mGameList;


    public PopularGamesModel() {
        mGameList = null;
    }

    // to get the list of popular games that are available for all platforms
    public void queryListOfPopularGames() {


    }

    public LiveData<List<Game>> getListOfPopularGames() {
        if (mGameList != null) {
            return mGameList;
        }
        return null;
    }

}
