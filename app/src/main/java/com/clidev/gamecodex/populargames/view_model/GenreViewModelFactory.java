package com.clidev.gamecodex.populargames.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class GenreViewModelFactory extends ViewModelProvider.NewInstanceFactory{


    public GenreViewModelFactory () {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GenreViewModel();
    }

}
