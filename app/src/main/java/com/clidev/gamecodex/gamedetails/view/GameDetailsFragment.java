package com.clidev.gamecodex.gamedetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.view.PopularGamesActivity;

import timber.log.Timber;

public class GameDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_details, container, false);

        Intent intent = getActivity().getIntent();
        Long selectedGameId = intent.getLongExtra(PopularGamesActivity.SELECTED_GAME_ID, 0);

        Timber.d("Selected game id is: " + selectedGameId);

        return rootView;
    }
}
