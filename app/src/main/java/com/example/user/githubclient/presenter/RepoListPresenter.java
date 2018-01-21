package com.example.user.githubclient.presenter;

import android.content.Context;

import com.example.user.githubclient.model.MainActivityModel;
import com.example.user.githubclient.model.interfaces.IMainActivityModel;
import com.example.user.githubclient.presenter.interfaces.IRepoListPresenter;
import com.example.user.githubclient.view.MainActivity;
import com.example.user.githubclient.view.interfaces.IRepoListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by User on 20.01.2018.
 */

public class RepoListPresenter implements IRepoListPresenter {

    private WeakReference<IRepoListView> iRepoListView;
    private IMainActivityModel iMainModel;
    private Context context;

    public RepoListPresenter(IRepoListView iRepoListView){
        this.iRepoListView = new WeakReference<>(iRepoListView);
        this.iMainModel = new MainActivityModel(this);
    }

    @Override
    public void readUsernameAndPassFromFile(Context context) {
        iMainModel.readUsernameAndPassFromFile(context);
    }

    @Override
    public void getReposList() {
        iMainModel.getReposListFromServer();
    }

    @Override
    public void getReposListFromServer(ArrayList list) {
        iRepoListView.get().showRV(list);
    }

    @Override
    public void dismissLoadingProgressBar() {
        iRepoListView.get().dismissProgressBar();
    }

    @Override
    public void showErrorSnackbar() {
        iRepoListView.get().showConnectProblemSnackbar();
        iRepoListView.get().dismissProgressBar();
    }

    @Override
    public void switchUser(Context context) {
        this.context = context;
        iMainModel.deleteUsernameAndPassword(context);
    }

    @Override
    public void startActivityForEnterUsernameAndPassword() {
        MainActivity.start(context);
    }
}
