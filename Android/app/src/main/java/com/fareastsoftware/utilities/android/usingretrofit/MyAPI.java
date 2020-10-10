package com.fareastsoftware.utilities.android.usingretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPI {
    @GET("/persons")
    Call<String>  getPersons();
}
