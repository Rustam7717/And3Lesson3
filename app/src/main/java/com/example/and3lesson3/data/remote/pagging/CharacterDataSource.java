package com.example.and3lesson3.data.remote.pagging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.models.RickAndMortyResponse;

import java.util.List;

import javax.inject.Inject;

public class CharacterDataSource extends PageKeyedDataSource<Integer, Character> {

    private CharactersStorage storage;

    @Inject
    public CharacterDataSource(CharactersStorage storage) {
        this.storage = storage;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Character> callback) {
        storage.getCharactersByPage(0, new CharactersStorage.OnCharactersReadyCallBack() {
            @Override
            public void onReady(RickAndMortyResponse<Character> result) {
                List<Character> response = result.getResults();
                String[] splitedKey = result.getInfo().getNext().split("=");
                Integer nextPage = Integer.parseInt(splitedKey[1]);
                callback.onResult(response, null, nextPage);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Character> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Character> callback) {
        storage.getCharactersByPage(params.key, new CharactersStorage.OnCharactersReadyCallBack() {
            @Override
            public void onReady(RickAndMortyResponse<Character> result) {
                List<Character> response = result.getResults();
                String[] splitedKey = result.getInfo().getNext().split("=");
                Integer nextPage = Integer.parseInt(splitedKey[1]);
                callback.onResult(response, nextPage);
            }
        });
    }
}
