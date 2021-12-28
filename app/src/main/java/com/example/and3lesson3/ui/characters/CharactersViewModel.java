package com.example.and3lesson3.ui.characters;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.example.and3lesson3.common.Resource;
import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.models.RickAndMortyResponse;
import com.example.and3lesson3.data.remote.pagging.CharactersDataSourceFactory;
import com.example.and3lesson3.data.remote.pagging.CharactersStorage;
import com.example.and3lesson3.data.repository.MainRepository;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharactersViewModel extends ViewModel {

    public LiveData<Resource<RickAndMortyResponse<Character>>> charactersLiveData;
    public LiveData<PagedList<Character>> pagedListLiveData;
private CharactersDataSourceFactory factory;
    private MainRepository repository;
    private CharactersStorage storage;

    private PagedList.Config config = (new PagedList.Config.Builder()).setEnablePlaceholders(true)
            .setPageSize(20).setInitialLoadSizeHint(20).build();

    @Inject
    public CharactersViewModel(MainRepository repository, CharactersStorage storage) {
        this.repository = repository;
        this.storage = storage;
        factory = new CharactersDataSourceFactory(storage);
        pagedListLiveData = new LivePagedListBuilder<>(factory,config).build();

    }

    public void getCharacters() {
        charactersLiveData = repository.getCharacters();
    }

}
