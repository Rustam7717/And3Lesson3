package com.example.and3lesson3.ui.characters;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.and3lesson3.App;
import com.example.and3lesson3.common.Resource;
import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.models.RickAndMortyResponse;

public class CharactersViewModel extends ViewModel {

    public LiveData<Resource<RickAndMortyResponse<Character>>> charactersLiveData;

    public CharactersViewModel() {

    }

    public void getCharacters(){
        charactersLiveData = App.repository.getCharacters();
    }

}
