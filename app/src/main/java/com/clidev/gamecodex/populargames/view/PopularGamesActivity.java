package com.clidev.gamecodex.populargames.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.gamedetails.view.GameDetails;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.view.adapters.GameListRvAdapter;

public class PopularGamesActivity extends AppCompatActivity implements GameListRvAdapter.ItemClickHandler{

    // Constants
    private static final String POPULAR_GAMES = "POPULAR_GAMES";
    public static final String SELECTED_GAME = "SELECTED_GAME";

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


    @Override
    public void onGameItemClicked(Game game) {
        Intent intent = new Intent(PopularGamesActivity.this, GameDetails.class);
        intent.putExtra(SELECTED_GAME, game);

        startActivity(intent);
    }
}
