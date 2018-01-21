package com.example.user.githubclient.presenter;

import com.example.user.githubclient.model.MainActivityModel;
import com.example.user.githubclient.model.interfaces.IMainActivityModel;
import com.example.user.githubclient.model.rest.restObjects.CommitsInformation;
import com.example.user.githubclient.model.rest.restObjects.Repo;
import com.example.user.githubclient.presenter.interfaces.IRepoDetailsPresenter;
import com.example.user.githubclient.view.interfaces.IRepoDetailsView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by User on 21.01.2018.
 */

public class RepoDetailsPresenter implements IRepoDetailsPresenter {

    private WeakReference<IRepoDetailsView> iRepoDetailsView;
    private IMainActivityModel iMainModel;
    private Repo repo;

    public RepoDetailsPresenter(IRepoDetailsView iRepoDetailsView){
        this.iRepoDetailsView = new WeakReference<>(iRepoDetailsView);
        this.iMainModel = new MainActivityModel(this);
    }


    @Override
    public void getRepoDetails(Repo repo) {
        this.repo = repo;
        iMainModel.getCommitInformation(repo);
    }

    @Override
    public void showRepoDetails(List<CommitsInformation> commitsInformations) {
        iRepoDetailsView.get().setRepoDetails(repo, commitsInformations);
    }

    @Override
    public void showErrorSnackbar() {
        iRepoDetailsView.get().showInternetConnectionErrorSnackbar();
    }

    @Override
    public void dismissProgressbar() {
        iRepoDetailsView.get().dismissProgressbar();
    }
}
