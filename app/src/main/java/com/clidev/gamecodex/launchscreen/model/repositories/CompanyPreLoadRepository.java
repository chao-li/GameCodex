package com.clidev.gamecodex.launchscreen.model.repositories;

import com.clidev.gamecodex.room.database.CompanyDatabase;
import com.clidev.gamecodex.room.entities.Company;
import com.clidev.gamecodex.utilities.AppExecutors;

import java.util.List;

import timber.log.Timber;

public class CompanyPreLoadRepository {

    private CompanyDatabase mCompanyDb;
    private CompanyPreLoadHandler mCompanyPreLoadHandler;


    public interface CompanyPreLoadHandler {
        void onCompanyLoadComplete();
        void onCompanyLoadFailed();
    }


    public CompanyPreLoadRepository(CompanyDatabase companyDatabase, CompanyPreLoadHandler companyPreLoadHandler) {
        mCompanyDb = companyDatabase;
        mCompanyPreLoadHandler = companyPreLoadHandler;
    }

    public void checkIfCompanyLoaded() {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Company> companies = mCompanyDb.companyDao().loadAllCompany();

                if (companies != null && companies.isEmpty() != true) {
                    Timber.d("Company database already exists, loading current database");

                    mCompanyPreLoadHandler.onCompanyLoadComplete();

                } else {
                    Timber.d("Company database don't exist, will query from online");

                    queryCompanyData();
                }
            }
        });
    }

    private void queryCompanyData() {



    }

}
