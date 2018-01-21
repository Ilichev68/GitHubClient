package com.example.user.githubclient.presenter.interfaces;

import android.content.Context;

/**
 * Created by User on 19.01.2018.
 */

public interface IMainActivityPresenter {

    void auth(String login, String pass, Context context);
    void showToast();
    void correctUsernameAndPassword(boolean correct);
    void startReposListAcrivity();
    void chekUserInSystem(Context context);
    void showInternetConnectionError();
}
