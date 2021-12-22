package com.example.and3lesson3;

import android.app.Application;
import com.example.and3lesson3.data.remote.RetrofitClient;
import com.example.and3lesson3.data.remote.RickAndMortyApi;
import com.example.and3lesson3.data.repository.MainRepository;

import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltAndroidApp
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

}
