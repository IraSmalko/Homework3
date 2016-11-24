package com.exemple.android.myapplication.retrofit;


import com.exemple.android.myapplication.retrofit.data.GithubUser;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitApiInterface {
    public static final String BASE_URL = "https://api.github.com";

    @GET("/users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);

    public class Factory {
        public static GitApiInterface service;
        public static GitApiInterface getService(){
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(GitApiInterface.class);
                return service;
            } else {
                return service;
            }
        }
    }
}