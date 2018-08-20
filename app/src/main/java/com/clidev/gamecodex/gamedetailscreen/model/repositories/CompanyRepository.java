package com.clidev.gamecodex.gamedetailscreen.model.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.clidev.gamecodex.constants.RetrofitConstantFields;
import com.clidev.gamecodex.gamedetailscreen.model.modeldata.Company;
import com.clidev.gamecodex.retrofit.GameDetailsRetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class CompanyRepository {

    // Retrofit fields
    private Retrofit.Builder mBuilder;
    private Retrofit mRetrofit;
    private GameDetailsRetrofitClient mClient;

    // Variable
    private MutableLiveData<List<Company>> mCompanyLiveData = new MutableLiveData<>();

    // Constructor
    public CompanyRepository() {
        // Create retrofit builder
        mBuilder = new Retrofit.Builder()
                .baseUrl(RetrofitConstantFields.igdbBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        // Build retrofit
        mRetrofit = mBuilder.build();

        // Create the retrofit client
        mClient = mRetrofit.create(GameDetailsRetrofitClient.class);
    }


    // Get company data
    public MutableLiveData<List<Company>> getCompany(List<Long> developers) {
        String developerIds = "";

        for (Long developer : developers) {
            if (developerIds.matches("")) {
                developerIds = developer + "";
            } else {
                developerIds = developerIds + "," + developer;
            }
        }

        Call<List<Company>> call =
                mClient.getTheseCompanies(developerIds, RetrofitConstantFields.COMPANY_FIELDS);


        call.enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                if (response.isSuccessful()) {
                    Timber.d("Company Api call successful");

                    mCompanyLiveData.setValue(response.body());
                } else {
                    Timber.d("Company call response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Timber.d("Company Api call failed");
            }
        });


        return mCompanyLiveData;
    }



}
