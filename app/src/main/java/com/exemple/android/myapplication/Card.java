package com.exemple.android.myapplication;


import android.graphics.Bitmap;

public class Card {
    private String login;
    private Bitmap avatar;

    public String getLogin() {
        return login;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public Card(String login, Bitmap bitmap) {
        this.login = login;
        this.avatar = bitmap;
    }


}
