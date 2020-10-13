package com.fareastsoftware.utilities.android.usingretrofit;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkAdapter {

    static final String TAG = "NetworkAdapter";
    static final NetworkAdapter sInstance = new NetworkAdapter();
    private Retrofit mRetrofit;
    private PersonAPI mApiEP;

    public static NetworkAdapter getInstance(){
        return sInstance;
    }


    // -----------------------------------------------------
    public PersonAPI getService(){
        return mApiEP;
    }


    // -----------------------------------------------------
    public void openForBusiness(){
        this.mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.120:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "Created instance of Retrofit");

        this.mApiEP =  mRetrofit.create( PersonAPI.class );
        Log.d(TAG, "Api end point created...");
    }




}
