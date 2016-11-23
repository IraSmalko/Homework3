package com.exemple.android.myapplication.retrofit;


import com.exemple.android.myapplication.retrofit.data.GithubUser;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitApiInterface {
    @GET("/users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);
}
