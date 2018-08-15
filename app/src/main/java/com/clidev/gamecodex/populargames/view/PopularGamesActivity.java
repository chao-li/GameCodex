package com.clidev.gamecodex.populargames.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clidev.gamecodex.R;

public class PopularGamesActivity extends AppCompatActivity {

    // Constants
    private static final String POPULAR_GAMES = "POPULAR_GAMES";

    // member variables
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_games);


        // Only initiate this fragment if this is the first time initiating this screen.
        if (savedInstanceState == null) {

            mFragment = new PopularGamesFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.popular_games_fragment_container, mFragment, POPULAR_GAMES)
                    .commit();
        } else {
            // catch the already created fragment
            mFragment = getSupportFragmentManager().findFragmentByTag(POPULAR_GAMES);
        }

    }


}
