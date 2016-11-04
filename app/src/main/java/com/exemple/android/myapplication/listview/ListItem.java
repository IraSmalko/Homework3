package com.exemple.android.myapplication.listview;

public class ListItem {

    private String name;
    private String googlePlusUrl;
    private String git;
    private String gitUrl;


    public ListItem(String name, String googlePlusUrl, String git, String gitUrl) {
        this.name = name;
        this.googlePlusUrl = googlePlusUrl;
        this.gitUrl = gitUrl;
        this.git = git;
    }
    public String getGit() { return git;  }

    public void setGit(String git) {
        this.git = git;
    }

    public String getGitUrl() { return gitUrl;  }

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
}
