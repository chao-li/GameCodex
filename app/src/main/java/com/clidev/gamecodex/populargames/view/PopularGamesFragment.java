package com.clidev.gamecodex.populargames.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargames.model.GenreRepository;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.model.room.Genre;
import com.clidev.gamecodex.populargames.model.room.GenreDatabase;
import com.clidev.gamecodex.populargames.view.adapters.GameListRvAdapter;
import com.clidev.gamecodex.populargames.view_model.GenreViewModel;
import com.clidev.gamecodex.populargames.view_model.GenreViewModelFactory;
import com.clidev.gamecodex.populargames.view_model.PopularGamesViewModel;
import com.clidev.gamecodex.populargames.view_model.PopularGamesViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PopularGamesFragment extends Fragment{

    private GridLayoutManager mGridLayoutManager;
    private List<Genre> mGenres = new ArrayList<>();

    @BindView(R.id.popular_games_rv) RecyclerView mGameListRv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_popular_games, container, false);

        ButterKnife.bind(this, rootView);

        loadGameGenres();

        loadPopularGames();

        return rootView;
    }

    private void loadGameGenres() {
        // Destroys database.
        getActivity().getApplicationContext().deleteDatabase(GenreDatabase.DATABASE_NAME);

        GenreViewModelFactory factory = new GenreViewModelFactory(getActivity().getApplicationContext());

        final GenreViewModel genreViewModel = ViewModelProviders.of(this, factory).get(GenreViewModel.class);

        genreViewModel.updateGenreList();

        genreViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(@Nullable List<Genre> genres) {
                Timber.d("Genre list successfully loaded");
                mGenres = genres;
            }
        });

    }

    private void loadPopularGames() {
        PopularGamesViewModelFactory factory = new PopularGamesViewModelFactory();

        final PopularGamesViewModel popViewModel = ViewModelProviders.of(this, factory).get(PopularGamesViewModel.class);

        popViewModel.getGameList().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> gameList) {
                String firstName = gameList.get(0).getName();
                Timber.d(firstName);
                Toast.makeText(getContext(), "First game name: " + firstName,
                        Toast.LENGTH_LONG).show();

                // remove the live model observer
                popViewModel.getGameList().removeObserver(this);


                // populate the RecyclerView
                populateRecyclerView(gameList);

            }

        });
    }


    private void populateRecyclerView(List<Game> gameList) {
        // Determining screen width
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;


        GameListRvAdapter gameListRvAdapter = new GameListRvAdapter(getContext(), dpWidth);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false);

        mGameListRv.setAdapter(gameListRvAdapter);
        mGameListRv.setLayoutManager(mGridLayoutManager);

        gameListRvAdapter.setGameList(gameList);

    }


}
