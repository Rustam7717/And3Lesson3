package com.example.and3lesson3.ui.characters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.and3lesson3.R;
import com.example.and3lesson3.common.Resource;
import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.models.RickAndMortyResponse;
import com.example.and3lesson3.databinding.FragmentCharactersBinding;

public class CharactersFragment extends Fragment {

    private FragmentCharactersBinding binding;
    private CharactersAdapter adapter;
    private CharactersViewModel viewModel;

    public CharactersFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharactersBinding.inflate(inflater, container, false);
        adapter = new CharactersAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(CharactersViewModel.class);
        viewModel.getCharacters();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvCharacter.setAdapter(adapter);

        viewModel.charactersLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<RickAndMortyResponse<Character>>>() {
            @Override
            public void onChanged(Resource<RickAndMortyResponse<Character>> resource) {
                switch (resource.status) {
                    case LOADING: {
                        binding.progress.setVisibility(View.VISIBLE);
                        binding.rvCharacter.setVisibility(View.GONE);
                        break;
                    }
                    case SUCCESS: {
                        binding.progress.setVisibility(View.GONE);
                        binding.rvCharacter.setVisibility(View.VISIBLE);
                        adapter.setCharacters(resource.data.getResults());
                        break;
                    }
                    case ERROR: {
                        binding.progress.setVisibility(View.GONE);
                        binding.rvCharacter.setVisibility(View.GONE);
                        break;
                    }
                }
            }
        });
    }
}