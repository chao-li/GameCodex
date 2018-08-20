package com.clidev.gamecodex.gamedetailscreen.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.clidev.gamecodex.gamedetailscreen.model.modeldata.Platform;
import com.clidev.gamecodex.gamedetailscreen.model.repositories.PlatformRepository;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;

import java.util.List;

public class PlatformViewModel extends ViewModel {

    private MutableLiveData<List<Platform>> mPlatformLiveData = new MutableLiveData<>();
    private PlatformRepository mPlatformRepository;

    public PlatformViewModel(Game game) {
        mPlatformRepository = new PlatformRepository();
        mPlatformLiveData = mPlatformRepository.getPlatformLiveData(game);
    }

    public MutableLiveData<List<Platform>> getPlatformLiveData() {
        return mPlatformLiveData;
    }
}
