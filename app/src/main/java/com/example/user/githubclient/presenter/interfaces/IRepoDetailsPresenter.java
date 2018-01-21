package com.example.user.githubclient.presenter.interfaces;

import com.example.user.githubclient.model.rest.restObjects.CommitsInformation;
import com.example.user.githubclient.model.rest.restObjects.Repo;

import java.util.List;

/**
 * Created by User on 21.01.2018.
 */

public interface IRepoDetailsPresenter {

    void getRepoDetails(Repo repo);
    void showRepoDetails(List<CommitsInformation> commitsInformations);
    void showErrorSnackbar();
    void dismissProgressbar();
}
