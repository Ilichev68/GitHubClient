package com.example.user.githubclient.model.rest;

import com.example.user.githubclient.model.rest.restObjects.CommitsInformation;
import com.example.user.githubclient.model.rest.restObjects.Repo;
import com.example.user.githubclient.model.rest.restObjects.UsersInformation;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 19.01.2018.
 */

public class GitHubRestClient {

    private static final String BASE_URL = "https://api.github.com/";
    private static volatile GitHubRestClient instance;
    private GitHubAPIService apiService;

    public GitHubRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(GitHubAPIService.class);

    }

    public static GitHubRestClient getInstance() {
        GitHubRestClient localInstance = instance;
        if (localInstance == null) {
            synchronized (GitHubRestClient.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GitHubRestClient();
                }
            }
        }
        return localInstance;
    }

    public UsersInformation suggestSync(String loginAndPass) throws IOException {
        return apiService.getUser(loginAndPass).execute().body();
    }

    public ArrayList<Repo> getReposList(String loginAndPass) throws  IOException{
        return apiService.getReposName(loginAndPass).execute().body();
    }

    public ArrayList<CommitsInformation> getCommits(String loginAndPass, String username, String password) throws IOException{
        return apiService.getCommits(loginAndPass, username, password).execute().body();
    }
}
