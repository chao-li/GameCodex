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

        PopularGamesViewModel popViewModel = ViewModelProviders.of(this).get(PopularGamesViewModel.class);
        popViewModel.getGameList().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> gameList) {
                String firstName = gameList.get(0).getName();
                Timber.d(firstName);
            }
        });



        /*
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
                if (response.body() != null) {
                    Timber.d("Call response body not null");
                    List<Game> gameList = response.body();
                    Timber.d("First game: " + gameList.get(0).getName());

                } else {
                    Timber.d("Call response body is null");
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Timber.d("Call response body is null");
            }
        });
        */
    }
}
