package com.clidev.gamecodex.gamedetails.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.clidev.gamecodex.gamedetails.model.repositories.GameDetailsRepository;
import com.clidev.gamecodex.populargames.model.modeldata.Game;

public class GameDetailsViewModel extends ViewModel {

    private MutableLiveData<Game> mGame = new MutableLiveData<>();
    private GameDetailsRepository mGameDetailsRepository;

    public GameDetailsViewModel(Long id) {

        mGameDetailsRepository = new GameDetailsRepository();
        mGame = mGameDetailsRepository.getGame(id);
    }

    public MutableLiveData<Game> getGame() {
        return mGame;
    }
}
