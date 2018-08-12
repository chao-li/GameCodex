package com.clidev.gamecodex.populargames.model;

import android.support.v7.widget.RecyclerView;

import com.clidev.gamecodex.populargames.model.modeldata.Genre;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class GenreRepository {

    private static final String GENRE_BASE_URL = "https://api-endpoint.igdb.com/";
    public static final String NAME = "name";

    public GenreRepository() {
    }

    // Retrieve list of genres
    public void queryGameGenres() {

        // Create retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(GENRE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        // Build Retrofit
        Retrofit retrofit = builder.build();

        // Create the retrofit client
        RetrofitClient client = retrofit.create(RetrofitClient.class);
        Call<List<Genre>> call = client.getGenres(NAME);

        // perform the call for list of genres
        call.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if (response.isSuccessful()) {
                    Timber.d("Genre api call successful.");
                    List<Genre> genres = response.body();

                    for (Genre genre : genres) {
                        Timber.d("Genre: " + genre.getName() + "\n");
                    }

                    queryGenreTranslation(genres);

                } else {
                    Timber.d("Genre call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {
                Timber.d("Genre call failed");
            }
        });


    }

    private void queryGenreTranslation(List<Genre> genres) {

    }



}
