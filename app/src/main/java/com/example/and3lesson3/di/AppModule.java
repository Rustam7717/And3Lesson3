package com.example.and3lesson3.di;

import com.example.and3lesson3.data.remote.RickAndMortyApi;
import com.example.and3lesson3.data.remote.pagging.CharactersStorage;
import com.example.and3lesson3.data.repository.MainRepository;
import java.util.concurrent.TimeUnit;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static MainRepository provideMainRepository(RickAndMortyApi api) {
        return new MainRepository(api);
    }

    @Provides
    public static RickAndMortyApi provideApi(Retrofit retrofit) {
        return retrofit.create(RickAndMortyApi.class) ;
    }

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor
                        .Level.BODY))
                .build();
    }

    @Provides
    public static CharactersStorage provideCharactersStorage(RickAndMortyApi api) {
        return new CharactersStorage(api);

    }
}
