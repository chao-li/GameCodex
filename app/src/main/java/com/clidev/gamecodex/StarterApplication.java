package com.clidev.gamecodex;

import android.app.Application;

import timber.log.Timber;

public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
