package com.fareastsoftware.utilities.android.usingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.fareastsoftware.utilities.android.usingretrofit.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //ViewModelProvider p = new ViewModelProvider(this,  ViewModelProvider.Factory());
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        Call<List<Person>> persons = NetworkAdapter.getInstance().getService().getPersons();
        persons.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                Log.d(TAG, "onResponse called with Response " + response.body());
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.d(TAG, "onFailure called with reason " + t.getCause());

            }
        });
    }
}