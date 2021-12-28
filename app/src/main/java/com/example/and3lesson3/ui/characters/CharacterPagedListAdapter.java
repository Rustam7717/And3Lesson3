package com.example.and3lesson3.ui.characters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.databinding.ItemCharacterBinding;

public class CharacterPagedListAdapter extends PagedListAdapter<Character, CharacterPagedListAdapter.CharacterPaggingViewHolder> {

    protected CharacterPagedListAdapter(@NonNull DiffUtil.ItemCallback<Character> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CharacterPaggingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new CharacterPaggingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterPaggingViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    protected class CharacterPaggingViewHolder extends RecyclerView.ViewHolder {
        private ItemCharacterBinding binding;

        public CharacterPaggingViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Character item) {
            binding.tvName.setText(item.getName());
            binding.tvStatus.setText(item.getStatus());
            Glide.with(binding.getRoot()).load(item.getImage())
                    .centerCrop().into(binding.ivCharacter);
        }
    }
}
