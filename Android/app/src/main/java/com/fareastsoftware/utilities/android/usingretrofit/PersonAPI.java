package com.fareastsoftware.utilities.android.usingretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonAPI {
    @GET("/persons/")
    Call<List<Person>>  getPersons();

    @POST("add/")
    Call<Person> addUser(@Body Person user);

    @PUT("update/{id}")
    Call<Person> updateUser(@Path("id") int id, @Body Person user);

    @DELETE("delete/{id}")
    Call<Person> deleteUser(@Path("id") int id);
}
