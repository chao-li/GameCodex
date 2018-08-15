package com.clidev.gamecodex.gamedetails.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.view.PopularGamesActivity;

import timber.log.Timber;

public class GameDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        Intent intent = getIntent();
        Game selectedGame = intent.getParcelableExtra(PopularGamesActivity.SELECTED_GAME);

        String name = selectedGame.getName();
        String url = selectedGame.getUrl();
        String summary = selectedGame.getSummary();
        Double popularity = selectedGame.getPopularity();
        Double aggregatedRating = selectedGame.getAggregatedRating();
        Long firstReleaseDate = selectedGame.getFirstReleaseDate();
        Long releaseDate = selectedGame.getReleaseDates().get(0).getDate();
        String artworkUrl = selectedGame.getArtworks().get(0).getUrl();

        Timber.d(name);
        Timber.d(url);
        Timber.d(summary);
        Timber.d(popularity + "");
        Timber.d(aggregatedRating + "");
        Timber.d(firstReleaseDate + "");
        Timber.d(releaseDate + "");
        Timber.d(artworkUrl + "");


    }
}
