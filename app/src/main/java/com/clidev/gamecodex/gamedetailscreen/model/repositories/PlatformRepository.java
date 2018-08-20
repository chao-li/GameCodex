package com.clidev.gamecodex.gamedetailscreen.model.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.clidev.gamecodex.constants.RetrofitConstantFields;
import com.clidev.gamecodex.gamedetailscreen.model.modeldata.Platform;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;
import com.clidev.gamecodex.retrofit.GameDetailsRetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class PlatformRepository {

    // Retrofit field
    private Retrofit.Builder mBuilder;
    private Retrofit mRetrofit;
    private GameDetailsRetrofitClient mClient;

    // variable
    private MutableLiveData<List<Platform>> mPlatformLiveData = new MutableLiveData<>();

    // Constructor
    public PlatformRepository() {
        // Create retrofit builder
        mBuilder = new Retrofit.Builder()
                .baseUrl(RetrofitConstantFields.igdbBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        // Build retrofit
        mRetrofit = mBuilder.build();

        // Create the retrofit client
        mClient = mRetrofit.create(GameDetailsRetrofitClient.class);
    }

    // Retrieve Platform data
    public MutableLiveData<List<Platform>> getPlatformLiveData(Game game) {
        if (game.getPlatforms() != null) {
            if (game.getPlatforms().isEmpty() != true) {
                List<Integer> platformIds = game.getPlatforms();

                // making the id inputs
                String ids = "";

                for (Integer id : platformIds) {
                    if (ids.matches("")) {
                        ids = id + "";
                    } else {
                        ids = ids + "," + id;
                    }
                }

                // Creating the call
                Call<List<Platform>> call =
                        mClient.getThesePlatforms(ids,RetrofitConstantFields.PLATFORM_FIELDS);

                //PERFORM THE CALL
                call.enqueue(new Callback<List<Platform>>() {
                    @Override
                    public void onResponse(Call<List<Platform>> call, Response<List<Platform>> response) {
                        if (response.isSuccessful()) {
                            Timber.d("platform api call is successful");

                            mPlatformLiveData.setValue(response.body());
                        } else {
                            Timber.d("platform api response not successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Platform>> call, Throwable t) {
                        Timber.d("platform api response not successful");
                    }
                });

            }
        }

        return mPlatformLiveData;
    }
}
