package com.clidev.gamecodex.populargamescreen.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.clidev.gamecodex.constants.RetrofitConstantFields;
import com.clidev.gamecodex.constants.SearchTypeConstants;
import com.clidev.gamecodex.populargamescreen.model.repositories.PopularGamesRepository;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;

import java.util.List;

public class PopularGamesViewModel extends ViewModel {


    private MutableLiveData<List<Game>> mGameList = new MutableLiveData<>();
    private PopularGamesRepository mPopularGamesRepository;
    private int mScrollCount = 0;


    public PopularGamesViewModel() {

        mPopularGamesRepository = new PopularGamesRepository();
        //mGameList = mPopularGamesRepository.queryPopularGames();

    }

    public void downloadGames(String searchCase) {
        mScrollCount = 0;
        switch(searchCase) {
            case SearchTypeConstants.PS4_POPULAR:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.PLAYSTATION_4,
                                RetrofitConstantFields.ORDER_POPULARITY);
                break;
        }
    }

    public void downloadNextSetOfGames(String searchCase) {
        switch(searchCase) {
            case SearchTypeConstants.PS4_POPULAR:
                mScrollCount++;
                mGameList = mPopularGamesRepository
                        .queryNextSetGames(mScrollCount,RetrofitConstantFields.PLAYSTATION_4,
                                RetrofitConstantFields.ORDER_POPULARITY);
                break;
        }

    }

    public MutableLiveData<List<Game>> getGameList() {
        return mGameList;
    }

}
