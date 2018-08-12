package com.clidev.gamecodex.populargames.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.clidev.gamecodex.populargames.model.modeldata.Game;

import java.text.SimpleDateFormat;
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
    private static final String igdbBaseUrl = "https://api-endpoint.igdb.com/";
    private static final String FIELDS = "id,name,genres,cover,popularity";
    private static final String ORDER = "popularity:desc";
    private static final int LIMIT = 30;

    // Constructor
    public PopularGamesRepository() {
    }

    // Retrieve popular movie data Retrofit
    public MutableLiveData<List<Game>> queryPopularMovies() {
        final MutableLiveData<List<Game>> gameList = new MutableLiveData<>();

        // Get current time and date
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String releaseAfterDate = dateFormat.format(currentTime);


        // Create the retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(igdbBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        // Build retrofit
        Retrofit retrofit = builder.build();

        // Create the retrofit client
        RetrofitClient client = retrofit.create(RetrofitClient.class);
        Call<List<Game>> call = client.getGame(FIELDS,
                releaseAfterDate,
                ORDER,
                LIMIT);

        // Perform the call for popular movie list
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful()) {

                    Timber.d("api call sucesss");
                    Timber.d("First game: " + response.body().get(0).getName());

                    gameList.setValue(response.body());
                } else {
                    Timber.d("api call not successful");
                }

            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Timber.d("api call failed");
            }
        });

        return gameList;
    }


}
