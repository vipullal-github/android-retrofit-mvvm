package com.fareastsoftware.utilities.android.usingretrofit;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    static final String TAG = "MainActivityViewModel";


    private MutableLiveData<List<Person>> mPersonsList;


    // ----------------------------------------
    public MainActivityViewModel(){
        super();
    }


    // ----------------------------------------
    public MutableLiveData<List<Person>> getPersonsList(){
        if( this.mPersonsList == null ){
            this.mPersonsList = new MediatorLiveData<>();
        }
        if( this.mPersonsList.getValue() == null ){
            this.mPersonsList.setValue( new ArrayList<Person>() );
        }
        return this.mPersonsList;
    }


    // ----------------------------------------
    void loadPersonsList(){
        Call<List<Person>> persons = NetworkAdapter.getInstance().getService().getPersons();
        persons.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                Log.d(TAG, "onResponse called with Response " + response.body());
                List<Person> persons = response.body();
                for (Person p : persons) {
                    Log.d(TAG, String.format("Person: givenName [%s] familyName [%s], id [%d]", p.getGivenName(), p.getFamilyName(), p.getId()));
                }
                mPersonsList.setValue( persons );
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.d(TAG, "onFailure called with reason " + t.getCause());
            }
        });
    }

}
