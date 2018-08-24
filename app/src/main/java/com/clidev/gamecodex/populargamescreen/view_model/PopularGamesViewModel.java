package com.clidev.gamecodex.populargamescreen.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.clidev.gamecodex.R;
import com.clidev.gamecodex.constants.RetrofitConstantFields;
import com.clidev.gamecodex.constants.SearchTypeConstants;
import com.clidev.gamecodex.populargamescreen.model.repositories.PopularGamesRepository;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;

import java.util.List;

import timber.log.Timber;

public class PopularGamesViewModel extends ViewModel {


    private MutableLiveData<List<Game>> mGameList = new MutableLiveData<>();
    private PopularGamesRepository mPopularGamesRepository;
    private int mScrollCount = 0;
    private int mPreviousTotalItemCount = 0;
    private boolean isLoading = false;
    private int mSearchType;


    public PopularGamesViewModel() {

        mPopularGamesRepository = new PopularGamesRepository();
        //mGameList = mPopularGamesRepository.queryPopularGames();

    }

    public void resetViewModel() {
        mScrollCount = 0;
        mPreviousTotalItemCount = 0;
        isLoading = false;
    }

    public void downloadGames(int searchType) {
        mSearchType = searchType;
        switch(mSearchType) {
            case R.id.ps4_popular:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.PLAYSTATION_4,
                                RetrofitConstantFields.ORDER_POPULARITY,
                                mScrollCount);
                break;

            default:
                Timber.d("Search type not supported");
        }
        mScrollCount++;
    }

    public void scrollDetected(int totalItemCount, int lastVisiblePosition) {

        // initiate previousTotalItemCount
        if (mPreviousTotalItemCount == 0) {
            mPreviousTotalItemCount = totalItemCount;
        }

        // if not loading, and we are near the bottom of the list, initiate loading.
        if (isLoading == false &&
                lastVisiblePosition >= totalItemCount - 25 &&
                totalItemCount > 0) {
            isLoading = true;
            //mLoadingBar.setVisibility(View.VISIBLE);
            downloadGames(mSearchType);

            Timber.d("Scroll loading started");
        }

        // if we are currently loading, check if loading has completed
        if (isLoading == true && totalItemCount > mPreviousTotalItemCount) {
            isLoading = false;
            //mLoadingBar.setVisibility(View.INVISIBLE);
            mPreviousTotalItemCount = totalItemCount;
            Timber.d("Scroll loading ended");
        }

    }

    public MutableLiveData<List<Game>> getGameList() {
        return mGameList;
    }

}
