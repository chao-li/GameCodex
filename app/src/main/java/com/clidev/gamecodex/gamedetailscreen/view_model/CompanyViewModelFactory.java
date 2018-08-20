package com.clidev.gamecodex.gamedetailscreen.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.List;

public class CompanyViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final List<Long> developers;

    public CompanyViewModelFactory(List<Long> developers) {
        this.developers = developers;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CompanyViewModel(developers);
    }
}
