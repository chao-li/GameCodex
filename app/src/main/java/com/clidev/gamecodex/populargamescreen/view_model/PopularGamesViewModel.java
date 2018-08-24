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
    private MutableLiveData<Integer> mSearchTypeLiveData = new MutableLiveData<>();
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
        mSearchTypeLiveData.setValue(searchType);
        selectRequiredQueryMethod();
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

    public MutableLiveData<Integer> getSearchTypeLiveData() {
        return mSearchTypeLiveData;
    }



    // Additional helper method
    private void selectRequiredQueryMethod() {
        switch(mSearchType) {
            case R.id.ps4_popular:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.PLAYSTATION_4,
                                RetrofitConstantFields.ORDER_POPULARITY,
                                mScrollCount,
                                true);
                break;

            case R.id.ps4_rated:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.PLAYSTATION_4,
                                RetrofitConstantFields.ORDER_AGGREGATED_RATING,
                                mScrollCount,
                                true);
                break;

            case R.id.ps4_upcoming:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.PLAYSTATION_4,
                                RetrofitConstantFields.ORDER_RELEASE_DATE,
                                mScrollCount,
                                false);
                break;

            case R.id.xb1_popular:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.XBOX_ONE,
                                RetrofitConstantFields.ORDER_POPULARITY,
                                mScrollCount,
                                true);
                break;

            case R.id.xb1_rated:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.XBOX_ONE,
                                RetrofitConstantFields.ORDER_AGGREGATED_RATING,
                                mScrollCount,
                                true);
                break;

            case R.id.xb1_upcoming:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.XBOX_ONE,
                                RetrofitConstantFields.ORDER_RELEASE_DATE,
                                mScrollCount,
                                false);
                break;


            case R.id.switch_popular:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.SWITCH,
                                RetrofitConstantFields.ORDER_POPULARITY,
                                mScrollCount,
                                true);
                break;

            case R.id.switch_rated:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.SWITCH,
                                RetrofitConstantFields.ORDER_AGGREGATED_RATING,
                                mScrollCount,
                                true);
                break;

            case R.id.switch_upcoming:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.SWITCH,
                                RetrofitConstantFields.ORDER_RELEASE_DATE,
                                mScrollCount,
                                false);
                break;

            case R.id.pc_popular:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.PC,
                                RetrofitConstantFields.ORDER_POPULARITY,
                                mScrollCount,
                                true);
                break;

            case R.id.pc_rated:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.PC,
                                RetrofitConstantFields.ORDER_AGGREGATED_RATING,
                                mScrollCount,
                                true);
                break;

            case R.id.pc_upcoming:
                mGameList = mPopularGamesRepository
                        .queryGames(RetrofitConstantFields.PC,
                                RetrofitConstantFields.ORDER_RELEASE_DATE,
                                mScrollCount,
                                false);
                break;

            default:
                Timber.d("Search type not supported");
        }
    }
}
