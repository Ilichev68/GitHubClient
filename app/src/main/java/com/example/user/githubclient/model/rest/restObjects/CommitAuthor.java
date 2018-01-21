package com.example.user.githubclient.model.rest.restObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 21.01.2018.
 */

public class CommitAuthor {

    @SerializedName("avatar_url")
    private String avatarImage;

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}
