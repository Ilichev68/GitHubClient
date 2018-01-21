package com.example.user.githubclient.model.rest.restObjects;

import com.example.user.githubclient.model.rest.restObjects.Commit;
import com.example.user.githubclient.model.rest.restObjects.CommitAuthor;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 20.01.2018.
 */

public class CommitsInformation {

    @SerializedName("sha")
    private String sha;
    @SerializedName("commit")
    private Commit commit;
    @SerializedName("author")
    private CommitAuthor commitAuthor;

    public CommitAuthor getCommitAuthor() {
        return commitAuthor;
    }

    public void setCommitAuthor(CommitAuthor commitAuthor) {
        this.commitAuthor = commitAuthor;
    }

    public String getSha() {

        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}
