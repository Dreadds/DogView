package com.dreads.dogapp;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.orm.SugarApp;

public class DogApp extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
