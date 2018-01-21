package com.example.user.githubclient.view.interfaces;

import com.example.user.githubclient.model.rest.restObjects.Repo;

import java.util.ArrayList;

/**
 * Created by User on 20.01.2018.
 */

public interface IRepoListView {

    void showRV(ArrayList<Repo> list);
    void dismissProgressBar();
    void showConnectProblemSnackbar();
}
