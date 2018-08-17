package com.clidev.gamecodex.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.clidev.gamecodex.room.dao.CompanyDao;
import com.clidev.gamecodex.room.entities.Company;

import timber.log.Timber;

@Database(entities = {Company.class}, version = 1, exportSchema = false)
public abstract class CompanyDatabase extends RoomDatabase{

    private static CompanyDatabase sInstance;
    private static final Object LOCK = new Object();
    public static final String DATABASE_NAME = "company database";

    public static CompanyDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Timber.d("Creating new company database");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        CompanyDatabase.class, CompanyDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Timber.d("getting the company database instance");
        return sInstance;
    }

    public abstract CompanyDao companyDao();
}
