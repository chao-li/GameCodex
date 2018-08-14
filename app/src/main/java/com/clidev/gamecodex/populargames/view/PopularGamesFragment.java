package com.clidev.gamecodex.populargames.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
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

public class PopularGamesFragment extends Fragment {

    private static final String TOTAL_ITEM_COUNT = "TOTAL_ITEM_COUNT";
    private static final String ISLOADING = "ISLOADING";
    private GameListRvAdapter mGameListRvAdapter;
    private GridLayoutManager mGridLayoutManager;
    private List<Genre> mGenres = new ArrayList<>();
    private PopularGamesViewModel mPopViewModel;

    // fields for scroll listener
    private int mPreviousTotalItemCount = 0;
    private boolean isLoading = false;

    @BindView(R.id.popular_games_rv) RecyclerView mGameListRv;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TOTAL_ITEM_COUNT, mPreviousTotalItemCount);
        outState.putBoolean(ISLOADING, isLoading);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_popular_games, container, false);

        ButterKnife.bind(this, rootView);

        if (savedInstanceState != null) {
            mPreviousTotalItemCount = savedInstanceState.getInt(TOTAL_ITEM_COUNT);
            isLoading = savedInstanceState.getBoolean(ISLOADING);

            Timber.d("PreviousItemCount restored instance state: " + mPreviousTotalItemCount);
            Timber.d("isLoading restored instance state: " + isLoading);
        }

        prepareRecyclerView();

        loadGameGenres();

        loadPopularGames();

        return rootView;
    }

    private void loadGameGenres() {
        // Destroys database.
        //getActivity().getApplicationContext().deleteDatabase(GenreDatabase.DATABASE_NAME);

        GenreViewModelFactory factory = new GenreViewModelFactory(getActivity().getApplicationContext());

        final GenreViewModel genreViewModel = ViewModelProviders.of(this, factory).get(GenreViewModel.class);

        genreViewModel.updateGenreList();

        genreViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(@Nullable List<Genre> genres) {
                Timber.d("Genre list successfully loaded");
                mGenres = genres;

                genreViewModel.getGenres().removeObserver(this);
            }
        });

    }

    private void loadPopularGames() {
        PopularGamesViewModelFactory factory = new PopularGamesViewModelFactory();

        mPopViewModel = ViewModelProviders.of(this, factory).get(PopularGamesViewModel.class);

        mPopViewModel.getGameList().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> gameList) {
                Timber.d("Updated game list data observed, updating recycler view...");

                // populate the RecyclerView
                populateRecyclerView(gameList);

            }

        });
    }


    private void prepareRecyclerView() {
        mGameListRvAdapter = new GameListRvAdapter(getContext());

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mGridLayoutManager = new GridLayoutManager(getContext(), 2,
                    GridLayoutManager.VERTICAL, false);
        } else {
            mGridLayoutManager = new GridLayoutManager(getContext(), 4,
                    GridLayoutManager.VERTICAL, false);
        }

        mGameListRv.setAdapter(mGameListRvAdapter);
        mGameListRv.setLayoutManager(mGridLayoutManager);

        mGameListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Total items in the list
                int totalItemCount = mGridLayoutManager.getItemCount();
                //Timber.d("Total item count: " + totalItemCount);

                // the position of the last visible item on the list;
                int lastVisiblePosition = mGridLayoutManager.findLastVisibleItemPosition();
                //Timber.d("Current scroll position's last item: " + lastVisiblePosition);

                // initiate previousTotalItemCount
                if (mPreviousTotalItemCount == 0) {
                    mPreviousTotalItemCount = totalItemCount;
                }

                // if not loading, and we are near the bottom of the list, initiate loading.
                if (isLoading == false &&
                        lastVisiblePosition >= totalItemCount - 25 &&
                        totalItemCount > 0) {
                    isLoading = true;
                    queryForMoreGames();

                    Timber.d("Scroll loading started");
                }

                // if we are currently loading, check if loading has completed
                if (isLoading == true && totalItemCount > mPreviousTotalItemCount) {
                    isLoading = false;
                    mPreviousTotalItemCount = totalItemCount;
                    Timber.d("Scroll loading ended");
                }


            }
        });
    }


    private void populateRecyclerView(List<Game> gameList) {
        // Determining screen width
        //DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        //float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        mGameListRvAdapter.setGameList(gameList);
        mGameListRvAdapter.setGenreList(mGenres);

    }


    private void queryForMoreGames() {
        mPopViewModel.downloadNextSetOfGames();
    }
}
