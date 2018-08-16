package com.clidev.gamecodex.launchscreen.model;

import com.clidev.gamecodex.constants.RetrofitConstantFields;
import com.clidev.gamecodex.populargames.model.PopularGamesRetrofitClient;
import com.clidev.gamecodex.populargames.model.repositories.GenreRepository;
import com.clidev.gamecodex.populargames.model.room.AppExecutors;
import com.clidev.gamecodex.populargames.model.room.genre.Genre;
import com.clidev.gamecodex.populargames.model.room.genre.GenreDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class GenrePreLoadRepository {


    private GenreDatabase mGenreDb;
    private GenrePreLoadHandler mGenrePreLoadHandler;


    public interface GenrePreLoadHandler {
        void onGenreLoadComplete();
        void onGenreLoadFailed();
    }


    public GenrePreLoadRepository(GenreDatabase genreDatabase, GenrePreLoadHandler genrePreLoadHandler) {
        mGenreDb = genreDatabase;
        mGenrePreLoadHandler = genrePreLoadHandler;
    }


    public void checkGenreList() {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // check if item exists first
                List<Genre> genres = mGenreDb.genreDao().loadAllGenre();

                if (genres != null && genres.isEmpty() != true) {
                    // if database exists, set genre data
                    // TODO inform view to move onto next objective

                    Timber.d("Genre database already exists, loading current database");

                    mGenrePreLoadHandler.onGenreLoadComplete();

                } else {
                    // if database don't exist, query genre data, then set data
                    Timber.d("Genre database don't exist, will query from online");

                    queryGameGenres();
                }

            }
        });

    }


    private void queryGameGenres() {

        // Create retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(RetrofitConstantFields.GENRE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        // Build Retrofit
        Retrofit retrofit = builder.build();

        // Create the retrofit client
        PopularGamesRetrofitClient client = retrofit.create(PopularGamesRetrofitClient.class);
        Call<List<Genre>> call = client.getGenres(RetrofitConstantFields.NAME, RetrofitConstantFields.GENRE_LIMIT);

        // perform the call for list of genres
        call.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if (response.isSuccessful()) {
                    Timber.d("Genre api call successful.");
                    List<Genre> genres = response.body();

                    addGenresToDatabase(genres);

                } else {
                    Timber.d("Genre call failed");

                    // TODO: inform view that genre download failed.
                    mGenrePreLoadHandler.onGenreLoadFailed();
                }
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {
                Timber.d("Genre call failed");

                // TODO: inform view that the genre download failed.
                mGenrePreLoadHandler.onGenreLoadFailed();
            }
        });


    }

    private void addGenresToDatabase(List<Genre> genres) {
        for (final Genre genre : genres) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    // check if item exists first
                    Genre previousGenre = mGenreDb.genreDao().loadGenreById(genre.getId());

                    if (previousGenre == null) {
                        mGenreDb.genreDao().insertGenre(genre);
                        Timber.d("Inserting: " + genre.getName());
                    } else {
                        Timber.d(genre.getName() + " already in database");
                    }

                }

            });
        }

        // TODO: inform view that process is complete, move to next task.
        mGenrePreLoadHandler.onGenreLoadComplete();
    }

}
