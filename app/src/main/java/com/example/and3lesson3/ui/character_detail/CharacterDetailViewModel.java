package com.example.and3lesson3.ui.character_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.and3lesson3.common.Resource;
import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.repository.MainRepository;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharacterDetailViewModel extends ViewModel {

    public LiveData<Resource<Character>> liveData;
    private MainRepository repository;

    @Inject
    public CharacterDetailViewModel(MainRepository repository){
        this.repository = repository;

    }



        public void getCharacterById(int id){
        liveData = repository.getCharacterById(id);

    }

    public void getCharacterById(Character characterId) {
    }
}
