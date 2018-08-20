package com.clidev.gamecodex.gamedetailscreen.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class GameDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Long mId;

    public GameDetailsViewModelFactory(Long id) {
        mId = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GameDetailsViewModel(mId);
    }
}
