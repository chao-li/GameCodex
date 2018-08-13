package com.clidev.gamecodex.populargames.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.clidev.gamecodex.populargames.model.room.AppExecutors;
import com.clidev.gamecodex.populargames.model.room.Genre;
import com.clidev.gamecodex.populargames.model.room.GenreDatabase;

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
    private static final int LIMIT = 50;

    private GenreDatabase mGenreDb;
    private MutableLiveData<List<Genre>> mGenres = new MutableLiveData<>();

    public GenreRepository(Context context) {
        mGenreDb = GenreDatabase.getInstance(context);
    }

    public MutableLiveData<List<Genre>> getGenreList() {
        return mGenres;
    }


    public void checkGenreList() {
        //  Check if database exists,
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // check if item exists first
                List<Genre> genres = mGenreDb.genreDao().loadAllGenre();

                if (genres != null && genres.isEmpty() != true) {
                    // if database exists, set genre data
                    mGenres.postValue(genres);

                    Timber.d("Genre database already exists, loading current database");

                } else {
                    // if database don't exist, query genre data, then set data
                    Timber.d("Genre database don't exist, will query from online");

                    queryGameGenres();
                }

            }
        });

    }

    // Retrieve list of genres
    private void queryGameGenres() {

        // Create retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(GENRE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        // Build Retrofit
        Retrofit retrofit = builder.build();

        // Create the retrofit client
        RetrofitClient client = retrofit.create(RetrofitClient.class);
        Call<List<Genre>> call = client.getGenres(NAME, LIMIT);

        // perform the call for list of genres
        call.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if (response.isSuccessful()) {
                    Timber.d("Genre api call successful.");
                    List<Genre> genres = response.body();

                    // set Genres to member field
                    mGenres.setValue(genres);


                    addGenresToDatabase(genres);

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


    }



}
