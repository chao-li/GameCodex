package com.clidev.gamecodex.populargamescreen.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.clidev.gamecodex.populargamescreen.model.repositories.PopularGamesRepository;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;

import java.util.List;

public class PopularGamesViewModel extends ViewModel {


    private MutableLiveData<List<Game>> mGameList = new MutableLiveData<>();
    private PopularGamesRepository mPopularGamesRepository;
    private int mScrollCount = 0;


    public PopularGamesViewModel() {

        mPopularGamesRepository = new PopularGamesRepository();
        mGameList = mPopularGamesRepository.queryPopularGames();

    }

    public void downloadNextSetOfGames() {
        mScrollCount++;
        mGameList = mPopularGamesRepository.queryNextSetPopularGames(mScrollCount);
    }

    public MutableLiveData<List<Game>> getGameList() {
        return mGameList;
    }

}
