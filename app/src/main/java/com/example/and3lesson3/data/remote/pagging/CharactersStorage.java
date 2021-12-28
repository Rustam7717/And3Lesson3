package com.example.and3lesson3.data.remote.pagging;

import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.models.RickAndMortyResponse;
import com.example.and3lesson3.data.remote.RickAndMortyApi;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersStorage {
    private RickAndMortyApi api;

    @Inject
    public CharactersStorage(RickAndMortyApi RickAndMortyApi api) {
        this.api = this.api;
    }
    public void getCharactersByPage(int page, OnCharactersReadyCallBack callBack){
        api.getCharactersByPage(page).enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                callBack.onReady(response.body());

                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {

            }
        });
    }
    interface OnCharactersReadyCallBack {
        void onReady(RickAndMortyResponse<Character> result);
    }
}
