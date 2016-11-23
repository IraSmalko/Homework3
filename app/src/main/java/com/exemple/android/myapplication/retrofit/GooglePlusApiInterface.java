package com.exemple.android.myapplication.retrofit;


import com.exemple.android.myapplication.retrofit.data.GooglePlusUser;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GooglePlusApiInterface {
    public static final String BASE_URL = "https://www.googleapis.com";

    @GET("/plus/v1/people/{username}?key=AIzaSyC7xbtBoEVQUwWWcDjENu0Z11mSyTeaijE")
    Call<GooglePlusUser> getUser(@Path("username") String username);

    public class Factory {
        public static GooglePlusApiInterface service;
        public static GooglePlusApiInterface getService(){
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(GooglePlusApiInterface.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
