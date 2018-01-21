package com.example.user.githubclient.presenter;


import android.content.Context;

import com.example.user.githubclient.model.interfaces.IMainActivityModel;
import com.example.user.githubclient.model.MainActivityModel;
import com.example.user.githubclient.presenter.interfaces.IMainActivityPresenter;
import com.example.user.githubclient.view.RepoList;
import com.example.user.githubclient.view.interfaces.IMainActivityView;

import java.lang.ref.WeakReference;

/**
 * Created by User on 19.01.2018.
 */

    public class MainActivityPresenter implements IMainActivityPresenter {

    private WeakReference<IMainActivityView> iMainActivity;
    private Context context;
    private IMainActivityModel iMainModel;

    public MainActivityPresenter(IMainActivityView iMainView){
        this.iMainActivity = new WeakReference<>(iMainView);
        this.iMainModel = new MainActivityModel(this);
    }

    @Override
    public void auth(String login, String pass, Context context) {
        this.context = context;
        iMainModel.sendLogAndPass(login, pass, context);
    }

    @Override
    public void showToast() {
        iMainActivity.get().showErrorSnackbar();
    }

    @Override
    public void correctUsernameAndPassword(boolean correct) {
        if (correct) iMainModel.saveUsernameAndPasswordInFile();
    }

    @Override
    public void startReposListAcrivity() {
        RepoList.start(context);
    }

    @Override
    public void chekUserInSystem(Context context) {
        if (iMainModel.checkFileInSystem(context))
            RepoList.start(context);
    }

    @Override
    public void showInternetConnectionError() {
        iMainActivity.get().showInternetConnectionError();
    }

}
