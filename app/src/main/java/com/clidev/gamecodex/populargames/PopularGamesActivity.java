package com.clidev.gamecodex.populargames;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.view_model.PopularGamesViewModel;
import com.clidev.gamecodex.populargames.view_model.PopularGamesViewModelFactory;

import java.util.List;

import timber.log.Timber;

public class PopularGamesActivity extends AppCompatActivity {


    private static final String igdbBaseUrl = "https://api-endpoint.igdb.com/";
    private static final String FIELDS = "id,name,genres,cover,popularity";
    private static final String ORDER = "popularity:desc";
    private static final int LIMIT = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_games);

        PopularGamesViewModelFactory factory = new PopularGamesViewModelFactory();

        final PopularGamesViewModel popViewModel = ViewModelProviders.of(this, factory).get(PopularGamesViewModel.class);

        popViewModel.getGameList().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> gameList) {
                String firstName = gameList.get(0).getName();
                Timber.d(firstName);
                Toast.makeText(PopularGamesActivity.this, "First game name: " + firstName,
                        Toast.LENGTH_LONG).show();

                // remove the live model observer
                popViewModel.getGameList().removeObserver(this);


                // TODO: populate the RecyclerView


            }

        });

    }
}
