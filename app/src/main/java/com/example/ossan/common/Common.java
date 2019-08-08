package com.example.ossan.common;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Common {
    public final static String URL = "http://10.0.2.2:8080/ossanRental";
    public final static String PREF_FILE = "preference";
    public static final String TAG = "common";
    public static void showToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }


    public static boolean networkConnected(Activity activity) {
        ConnectivityManager conManager =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager != null ? conManager.getActiveNetworkInfo() : null;
        return networkInfo != null && networkInfo.isConnected();
    }
}
