package com.clidev.gamecodex.populargames.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.clidev.gamecodex.populargames.model.PopularGamesRepository;
import com.clidev.gamecodex.populargames.model.modeldata.Game;

import java.util.List;

public class PopularGamesViewModel extends ViewModel {


    private MutableLiveData<List<Game>> mGameList = new MutableLiveData<>();
    private PopularGamesRepository mPopularGamesRepository;


    public PopularGamesViewModel() {
        mPopularGamesRepository = new PopularGamesRepository();
        mGameList = mPopularGamesRepository.queryPopularGames();
    }

    public void downloadNextSetOfGames(int scrollCount) {
        mGameList = mPopularGamesRepository.queryNextSetPopularGames(scrollCount);
    }

    public MutableLiveData<List<Game>> getGameList() {
        return mGameList;
    }

}
