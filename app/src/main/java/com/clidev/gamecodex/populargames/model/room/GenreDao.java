package com.clidev.gamecodex.populargames.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GenreDao {

    @Query("SELECT * FROM genre ORDER BY id")
    List<Genre> loadAllTask();

    @Insert
    void insertGenre(Genre genre);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGenre(Genre genre);

    @Delete
    void deleteGenre(Genre genre);

    @Query("SELECT * FROM genre WHERE id = :id")
    Genre loadGenreById(int id);
}
