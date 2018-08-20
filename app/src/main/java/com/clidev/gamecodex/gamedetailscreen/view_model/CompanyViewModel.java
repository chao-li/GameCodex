package com.clidev.gamecodex.gamedetailscreen.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.clidev.gamecodex.gamedetailscreen.model.modeldata.Company;
import com.clidev.gamecodex.gamedetailscreen.model.repositories.CompanyRepository;

import java.util.List;

public class CompanyViewModel extends ViewModel {

    private MutableLiveData<List<Company>> mCompanyLiveData = new MutableLiveData<>();
    private CompanyRepository mCompanyRepository;

    public CompanyViewModel(List<Long> developers) {
        mCompanyRepository = new CompanyRepository();
        mCompanyLiveData = mCompanyRepository.getCompany(developers);
    }

    public MutableLiveData<List<Company>> getCompanyLiveData() {
        return mCompanyLiveData;
    }
}
