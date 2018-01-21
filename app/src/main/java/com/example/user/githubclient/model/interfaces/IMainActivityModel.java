package com.example.user.githubclient.model.interfaces;

import android.content.Context;

import com.example.user.githubclient.model.rest.restObjects.Repo;

/**
 * Created by User on 19.01.2018.
 */

public interface IMainActivityModel {

    void sendLogAndPass(String log, String pass, Context context);
    void saveUsernameAndPasswordInFile();
    void readUsernameAndPassFromFile(Context context);
    void getReposListFromServer();
    void getCommitInformation(Repo repo);
    boolean checkFileInSystem(Context context);
    void deleteUsernameAndPassword(Context context);
}
