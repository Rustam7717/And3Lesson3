package com.example.and3lesson3;

import android.app.Application;

import com.example.and3lesson3.data.remote.RetrofitClient;
import com.example.and3lesson3.data.remote.RickAndMortyApi;
import com.example.and3lesson3.data.repository.MainRepository;

public class App extends Application {

    public static RickAndMortyApi api;
    private RetrofitClient client;
    public static MainRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new RetrofitClient();
        api = client.provideApi();
        repository=new MainRepository();
    }
}
