package com.clidev.gamecodex.populargames;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargames.model.PopularGamesModel;
import com.clidev.gamecodex.populargames.model.RetrofitClient;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.view_model.PopularGamesViewModel;
import com.clidev.gamecodex.populargames.view_model.PopularGamesViewModelFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class PopularGamesActivity extends AppCompatActivity {


    private static final String igdbBaseUrl = "https://api-endpoint.igdb.com/";
    private static final String FIELDS = "id,name,genres,cover,popularity";
    private static final String ORDER = "popularity:desc";
    private static final int LIMIT = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_games);

        PopularGamesViewModelFactory factory = new PopularGamesViewModelFactory();

        PopularGamesViewModel popViewModel = ViewModelProviders.of(this, factory).get(PopularGamesViewModel.class);

        popViewModel.mGameList.observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> gameList) {
                String firstName = gameList.get(0).getName();
                Timber.d(firstName);
            }

        });

    }
}
