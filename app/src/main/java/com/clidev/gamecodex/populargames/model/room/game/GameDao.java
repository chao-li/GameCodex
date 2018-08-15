package com.clidev.gamecodex.populargames.model.room.game;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.model.room.genre.Genre;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM game ORDER BY id")
    List<Game> loadAllGames();

    @Insert
    void insertGames(Game game);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGame(Game game);

    @Delete
    void deleteGame(Game game);

    @Query("SELECT * FROM game WHERE id = :id")
    Game loadGameById(int id);
}
