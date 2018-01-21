package com.example.user.githubclient.model.rest.restObjects;

import com.example.user.githubclient.model.rest.restObjects.Author;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 20.01.2018.
 */

public class Commit {

    @SerializedName("message")
    private String message;
    @SerializedName("author")
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
