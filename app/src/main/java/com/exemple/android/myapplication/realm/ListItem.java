package com.exemple.android.myapplication.realm;

import io.realm.RealmObject;

public class ListItem extends RealmObject {

    private int id;
    private String name;
    private String googlePlusUrl;
    private String git;
    private String gitUrl;
    private String searchName;

    public ListItem(String name, String googlePlusUrl, String git, String gitUrl) {
        this.name = name;
        this.googlePlusUrl = googlePlusUrl;
        this.gitUrl = gitUrl;
        this.git = git;
    }

    public ListItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getGooglePlusUrl() {
        return googlePlusUrl;
    }

    public void setGooglePlusUrl(String googlePlusUrl) {
        this.googlePlusUrl = googlePlusUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName.toLowerCase();
    }
}
