package com.example.and3lesson3.ui.characters;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.and3lesson3.App;
import com.example.and3lesson3.common.Resource;
import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.models.RickAndMortyResponse;
import com.example.and3lesson3.data.repository.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharactersViewModel extends ViewModel {

    public LiveData<Resource<RickAndMortyResponse<Character>>> charactersLiveData;

    private MainRepository repository;
@Inject
    public CharactersViewModel(MainRepository repository) {
    this.repository=repository;

    }

    public void getCharacters(){
        charactersLiveData = repository.getCharacters();
    }

}
