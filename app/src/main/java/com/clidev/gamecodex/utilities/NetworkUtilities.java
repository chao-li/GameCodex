package com.clidev.gamecodex.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

public final class NetworkUtilities {

    public static boolean isNetworkAvailable(Context context) {
        // create a connectivity manager
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // create a current networkinfo variable
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        // check if network is available, if it is available, change bool to true.
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    public static void alertNetworkNotAvailable(Context context) {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("No network connection!")
                .setMessage("Your network is not turned on. App may not be able to display data properly!")
                .setPositiveButton("Ok", null)
                .show();
    }



}
