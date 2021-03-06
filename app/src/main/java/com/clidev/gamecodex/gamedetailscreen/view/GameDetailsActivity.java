package com.clidev.gamecodex.gamedetailscreen.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;
import com.clidev.gamecodex.populargamescreen.view.PopularGamesActivity;

import timber.log.Timber;

public class GameDetailsActivity extends AppCompatActivity {

    private static final String GAME_DETAILS = "GAME_DETAILS";
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        //getSupportActionBar().hide();

        if (savedInstanceState == null) {

            mFragment = new GameDetailsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.game_details_fragment_container, mFragment, GAME_DETAILS)
                    .commit();
        } else {
            mFragment = getSupportFragmentManager().findFragmentByTag(GAME_DETAILS);
        }

    }
}
