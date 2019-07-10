package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {


    @GET("posts")
    Call<List<Posts>> getPosts();

    @POST("posts")
    Call<Posts>createPost(@Body Posts posts);

    @FormUrlEncoded
    @POST("posts")
    Call<Posts> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @PUT("posts/{id}")
    Call<Posts> putPost(@Path("id") int id, @Body Posts posts);

    @PATCH("posts/{id}")
    Call<Posts> patchPost(@Path("id")int id, @Body Posts posts);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
