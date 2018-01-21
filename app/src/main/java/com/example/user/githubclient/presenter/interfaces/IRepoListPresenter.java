package com.example.user.githubclient.presenter.interfaces;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by User on 20.01.2018.
 */

public interface IRepoListPresenter {

    void readUsernameAndPassFromFile(Context context);
    void getReposList();
    void getReposListFromServer(ArrayList list);
    void dismissLoadingProgressBar();
    void showErrorSnackbar();
    void switchUser(Context context);
    void startActivityForEnterUsernameAndPassword();
}
