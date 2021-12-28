package com.example.and3lesson3.data.remote;

import com.example.and3lesson3.data.models.Character;
import com.example.and3lesson3.data.models.RickAndMortyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RickAndMortyApi {

    @GET("character")
    Call<RickAndMortyResponse<Character>> getCharacters();

    @GET("character/{id}")
    Call<Character> getCharacterById(
            @Path("id") int id
    );
    @GET ("character")
    Call<RickAndMortyResponse<Character>> getCharactersByPage(
            @Query("page") int page
    );
}
