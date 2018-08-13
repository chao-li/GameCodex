package com.clidev.gamecodex.populargames.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.clidev.gamecodex.populargames.model.GenreRepository;
import com.clidev.gamecodex.populargames.model.room.Genre;

import java.util.List;

public class GenreViewModel extends ViewModel{

    private MutableLiveData<List<Genre>> mGenres = new MutableLiveData<>();
    private Context mContext;

    // Constructor
    public GenreViewModel(Context context) {
        mContext = context;
    }

    public void updateGenreList() {
        GenreRepository genreRepository = new GenreRepository(mContext.getApplicationContext());
        genreRepository.checkGenreList();
        mGenres = genreRepository.getGenreList();

    }

    public LiveData<List<Genre>> getGenres() {
        return mGenres;
    }


}
