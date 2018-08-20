package com.clidev.gamecodex.populargamescreen.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class PopularGamesViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    public PopularGamesViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PopularGamesViewModel();
    }
}
