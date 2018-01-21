package com.example.user.githubclient.view.interfaces;

import com.example.user.githubclient.model.rest.restObjects.CommitsInformation;
import com.example.user.githubclient.model.rest.restObjects.Repo;

import java.util.List;

/**
 * Created by User on 20.01.2018.
 */

public interface IRepoDetailsView {

    void setRepoDetails(Repo repo, List<CommitsInformation> commitsInformations);
    void showInternetConnectionErrorSnackbar();
    void dismissProgressbar();
}
