package com.example.user.githubclient.model.rest.restObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 19.01.2018.
 */

public class UsersInformation {

    @SerializedName("login")
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
