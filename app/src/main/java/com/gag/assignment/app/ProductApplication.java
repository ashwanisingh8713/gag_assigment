package com.gag.assignment.app;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.gag.assignment.db.ProductDB;

public class ProductApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.fullyInitialize();
        context = this;
        ProductDB.getInstance();
    }

    public static Context getContext() {
        return context;
    }
}
