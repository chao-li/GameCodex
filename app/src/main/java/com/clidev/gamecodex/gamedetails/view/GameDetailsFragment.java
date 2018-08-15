package com.clidev.gamecodex.gamedetails.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.gamedetails.view_model.GameDetailsViewModel;
import com.clidev.gamecodex.gamedetails.view_model.GameDetailsViewModelFactory;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.view.PopularGamesActivity;

import timber.log.Timber;

public class GameDetailsFragment extends Fragment {

    private Long mId;
    private GameDetailsViewModel mGameDetailsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_details, container, false);

        Intent intent = getActivity().getIntent();
        mId = intent.getLongExtra(PopularGamesActivity.SELECTED_GAME_ID, 0);

        Timber.d("Selected game id is: " + mId);

        if (mId != 0) {
            loadGameData();
        }

        return rootView;
    }

    private void loadGameData() {
        GameDetailsViewModelFactory factory = new GameDetailsViewModelFactory(mId);

        mGameDetailsViewModel = ViewModelProviders.of(this, factory).get(GameDetailsViewModel.class);

        mGameDetailsViewModel.getGame().observe(this, new Observer<Game>() {
            @Override
            public void onChanged(@Nullable Game game) {
                Timber.d("LiveData change observed");

                Timber.d("Game name: " + game.getName());
            }
        });



    }
}
