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

import com.clidev.gamecodex.populargames.model.PopularGamesRepository;
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


    private MutableLiveData<List<Game>> mGameList = new MutableLiveData<>();


    public PopularGamesViewModel() {
        PopularGamesRepository popularGamesRepository = new PopularGamesRepository();
        mGameList = popularGamesRepository.queryPopularMovies();
    }

    public MutableLiveData<List<Game>> getGameList() {
        return mGameList;
    }

}
