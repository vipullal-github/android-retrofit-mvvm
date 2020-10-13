package com.fareastsoftware.utilities.android.usingretrofit;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkAdapter.getInstance().openForBusiness();
    }
}
