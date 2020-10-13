package com.fareastsoftware.utilities.android.usingretrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("givenName")
    @Expose
    String givenName;

    @SerializedName("familyName")
    @Expose
    String familyName;

    @SerializedName("id")
    @Expose
    int id;

    public Person(String givenName, String familyName, int id) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.id = id;
    }

    

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
