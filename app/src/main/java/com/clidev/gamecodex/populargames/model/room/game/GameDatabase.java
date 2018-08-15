package com.clidev.gamecodex.populargames.model.room.game;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.clidev.gamecodex.populargames.model.modeldata.Game;

import timber.log.Timber;

@Database(entities = {Game.class}, version = 1, exportSchema = false)
public abstract class GameDatabase extends RoomDatabase {

    private static GameDatabase sInstance;
    private static final Object LOCK = new Object();
    public static final String DATABASE_NAME = "game database";

    public static GameDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Timber.d("Creating Game database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        GameDatabase.class, GameDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Timber.d("getting game database instance");
        return sInstance;
    }

    public abstract GameDao gameDao();

}
