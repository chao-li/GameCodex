package com.clidev.gamecodex.gamedetailscreen.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;

public class PlatformViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Game mGame;

    public PlatformViewModelFactory(Game game) {
        this.mGame = game;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlatformViewModel(mGame);
    }
}
