package com.clidev.gamecodex.populargames.model.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import timber.log.Timber;

@Database(entities = {Genre.class}, version = 1, exportSchema = false)
public abstract class GenreDatabase extends RoomDatabase{

    private static GenreDatabase sInstance;
    private static final Object LOCK = new Object();
    public static final String DATABASE_NAME = "genre database";

    public static GenreDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Timber.d("Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        GenreDatabase.class, GenreDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Timber.d("getting the database instance");
        return sInstance;
    }

    public abstract GenreDao genreDao();



}
