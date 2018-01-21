package com.example.user.githubclient.model.rest;


import com.example.user.githubclient.model.rest.restObjects.CommitsInformation;
import com.example.user.githubclient.model.rest.restObjects.Repo;
import com.example.user.githubclient.model.rest.restObjects.UsersInformation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by User on 19.01.2018.
 */

public interface GitHubAPIService {

    @GET("user")
    Call<UsersInformation> getUser(@Header("Authorization") String loginAndPass);

    @GET("user/repos")
    Call<ArrayList<Repo>> getReposName(@Header("Authorization") String loginAndPass);

    @GET("repos/{username}/{repoName}/commits")
    Call<ArrayList<CommitsInformation>> getCommits(@Header("Authorization") String loginAndPass, @Path("username") String username, @Path("repoName") String repoName);

}
