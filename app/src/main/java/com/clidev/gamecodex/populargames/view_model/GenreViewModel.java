package com.clidev.gamecodex.populargames.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.clidev.gamecodex.populargames.model.repositories.GenreRepository;
import com.clidev.gamecodex.room.entities.Genre;
import com.clidev.gamecodex.room.database.GenreDatabase;

import java.util.List;

public class GenreViewModel extends ViewModel{

    private MutableLiveData<List<Genre>> mGenres = new MutableLiveData<>();

    // Constructor
    public GenreViewModel() {
    }

    public void updateGenreList(GenreDatabase genreDatabase) {

        GenreRepository genreRepository = new GenreRepository(genreDatabase);
        genreRepository.checkGenreList();
        mGenres = genreRepository.getGenreList();

    }

    public LiveData<List<Genre>> getGenres() {
        return mGenres;
    }


}
