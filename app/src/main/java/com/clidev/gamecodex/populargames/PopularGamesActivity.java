package com.clidev.gamecodex.populargames;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargames.model.PopularGamesModel;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.view_model.PopularGamesViewModel;

import java.util.List;

import timber.log.Timber;

public class PopularGamesActivity extends AppCompatActivity {

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
    }
}
