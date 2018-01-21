package com.example.user.githubclient.model.rest.restObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 20.01.2018.
 */

public class Author {

    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
