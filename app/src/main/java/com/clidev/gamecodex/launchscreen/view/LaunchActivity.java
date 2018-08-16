package com.clidev.gamecodex.launchscreen.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.launchscreen.model.GenrePreLoadRepository;
import com.clidev.gamecodex.populargames.model.room.genre.Genre;
import com.clidev.gamecodex.populargames.model.room.genre.GenreDatabase;
import com.clidev.gamecodex.populargames.view.PopularGamesActivity;
import com.clidev.gamecodex.populargames.view_model.GenreViewModel;
import com.clidev.gamecodex.populargames.view_model.GenreViewModelFactory;

import java.util.List;

import timber.log.Timber;

public class LaunchActivity extends AppCompatActivity implements GenrePreLoadRepository.GenrePreLoadHandler {

    private Boolean isGenreLoadComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // TODO: load genre data to database
        // Destroys database.
        getApplicationContext().deleteDatabase(GenreDatabase.DATABASE_NAME);

        loadGenreData();



        // TODO: load developer data

        // TODO: load publisher data

        // TODO: load player perspectives data

        // TODO: load game modes

        // TODO: load platform data




    }

    // LOADING GENRE DATA
    private void loadGenreData() {
        GenreDatabase genreDatabase = GenreDatabase.getInstance(getApplicationContext());

        GenrePreLoadRepository genrePreLoadRepository =
                new GenrePreLoadRepository(genreDatabase, this);

        genrePreLoadRepository.checkGenreList();
    }

    @Override
    public void onGenreLoadComplete() {
        Timber.d("Genre preload complete");
        isGenreLoadComplete = true;

        proceedNextActivity();
    }

    @Override
    public void onGenreLoadFailed() {
        Timber.d("Genre preload failed");
        isGenreLoadComplete = false;
    }
    //.........................................................................



    // PROCEED NEXT ACTIVITY ///////////////////////////////////
    private void proceedNextActivity() {
        Intent intent = new Intent(LaunchActivity.this, PopularGamesActivity.class);
        startActivity(intent);
    }
    //............................................
}
