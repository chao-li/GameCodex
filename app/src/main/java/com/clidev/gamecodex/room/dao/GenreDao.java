package com.clidev.gamecodex.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.clidev.gamecodex.room.entities.Genre;

import java.util.List;

@Dao
public interface GenreDao {

    @Query("SELECT * FROM genre ORDER BY id")
    List<Genre> loadAllGenre();

    @Insert
    void insertGenre(Genre genre);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGenre(Genre genre);

    @Delete
    void deleteGenre(Genre genre);

    @Query("SELECT * FROM genre WHERE id = :id")
    Genre loadGenreById(Long id);
}
