package org.example;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import java.util.List;

public interface JSONPlaceholderAPI {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("photos")
    Call<List<Photo>> getPhotos();

    static JSONPlaceholderAPI getInstance() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
                .create(JSONPlaceholderAPI.class);
    }
}
