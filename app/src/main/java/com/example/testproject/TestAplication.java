package com.example.testproject;

import android.app.Application;

import com.example.testproject.http.AppService;

public class TestAplication extends Application {
    public void onCreate() {
        super.onCreate();
        AppService.getInstance().setContext(getApplicationContext());
    }
}
