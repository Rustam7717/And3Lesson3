package com.example.and3lesson3.ui.character_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.and3lesson3.common.Resource;
import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.databinding.FragmentCharacterDetailBinding;
import com.example.and3lesson3.ui.characters.CharactersViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharacterDetailFragment extends Fragment {

    private FragmentCharacterDetailBinding binding;
    private CharacterDetailFragmentArgs args;
    private CharacterDetailViewModel viewModel;
    public CharacterDetailFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterDetailViewModel.class);
        args = CharacterDetailFragmentArgs.fromBundle(getArguments());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        binding.tvName.setText(String.valueOf(args.getCharacterId()));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getCharacterById(args.getCharacterId());
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<Character>>() {
            @Override
            public void onChanged(Resource<Character> resource) {
                switch (resource.status) {
                    case LOADING: {
                        binding.tvName.setVisibility(View.GONE);
                        binding.tvStatus.setVisibility(View.GONE);
                        binding.ivCharacterImage.setVisibility(View.GONE);
                        binding.progress.setVisibility(View.VISIBLE);
                        break;
                    }
                    case SUCCESS: {
                        Character character = resource.data;
                        binding.tvName.setVisibility(View.VISIBLE);
                        binding.tvStatus.setVisibility(View.VISIBLE);
                        binding.ivCharacterImage.setVisibility(View.VISIBLE);
                        binding.progress.setVisibility(View.GONE);
                        binding.tvStatus.setText(character.getStatus());
                        binding.tvName.setText(character.getName());
                        Glide.with(binding.getRoot())
                                .load(character.getImage())
                                .centerCrop().into(binding.ivCharacterImage);
                        break;
                    }
                    case ERROR: {
                        binding.tvName.setVisibility(View.GONE);
                        binding.tvStatus.setVisibility(View.GONE);
                        binding.ivCharacterImage.setVisibility(View.GONE);
                        binding.progress.setVisibility(View.GONE);
                    }
                    break;
                }
            }
        });
    }
}