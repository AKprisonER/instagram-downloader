package com.dl.lvak.insta;

import android.app.Application;
import android.content.Context;

import android.util.DisplayMetrics;

import io.realm.*;


/**
 * Created by Windows on 30-01-2015.
 */
public class MyApplication extends Application {


    private static MyApplication sInstance;

    public DisplayMetrics metrics;


    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }



    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        Realm.init(this);
    }

    public DisplayMetrics getMetrics() {
        metrics = new DisplayMetrics();

        metrics = getResources().getDisplayMetrics();
        return metrics;
    }



    @Override
    public void onTerminate() {
        super.onTerminate();

    }

}
