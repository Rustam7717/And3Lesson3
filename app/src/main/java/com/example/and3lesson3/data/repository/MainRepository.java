package com.example.and3lesson3.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.and3lesson3.App;
import com.example.and3lesson3.common.Resource;
import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.models.RickAndMortyResponse;
import com.example.and3lesson3.data.remote.RickAndMortyApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private RickAndMortyApi api;

    @Inject
    public MainRepository(RickAndMortyApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<RickAndMortyResponse<Character>>> getCharacters() {
        MutableLiveData<Resource<RickAndMortyResponse<Character>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getCharacters().enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                }

            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }

    public MutableLiveData<Resource<Character>> getCharacterById(int id) {
        MutableLiveData<Resource<Character>> result = new MutableLiveData<>();
        result.setValue(Resource.loading());
        api.getCharacterById(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(Resource.success(response.body()));
                } else {
                    result.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                result.setValue(Resource.error(t.getLocalizedMessage(), null));

            }
        });
        return result;
    }


}
