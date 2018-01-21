package com.example.user.githubclient.model;

import android.content.Context;
import android.util.Base64;

import com.example.user.githubclient.model.interfaces.IMainActivityModel;
import com.example.user.githubclient.model.rest.restObjects.CommitsInformation;
import com.example.user.githubclient.model.rest.GitHubRestClient;
import com.example.user.githubclient.model.rest.restObjects.Repo;
import com.example.user.githubclient.model.rest.restObjects.UsersInformation;
import com.example.user.githubclient.presenter.interfaces.IMainActivityPresenter;
import com.example.user.githubclient.presenter.interfaces.IRepoDetailsPresenter;
import com.example.user.githubclient.presenter.interfaces.IRepoListPresenter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by User on 19.01.2018.
 */

public class MainActivityModel implements IMainActivityModel {

    private IMainActivityPresenter iMainActivityPresenter;
    private IRepoListPresenter iRepoListPresenter;
    private IRepoDetailsPresenter iRepoDetailsPresenter;
    private final static ExecutorService executor = Executors.newSingleThreadExecutor();
    private String encodingUsernameAndPassword;
    private final String FILENAME = "secretFile";
    private FileOutputStream outputStream;
    private Context context;
    private String stringFromFile;
    private String stringFromFileForClient;
    private ArrayList<Repo> repoNames = new ArrayList<>();

    public MainActivityModel(IMainActivityPresenter iMainActivityPresenter) {
        this.iMainActivityPresenter = iMainActivityPresenter;
    }

    public MainActivityModel(IRepoListPresenter iRepoListPresenter) {
        this.iRepoListPresenter = iRepoListPresenter;
    }

    public MainActivityModel(IRepoDetailsPresenter iRepoDetailsPresenter) {
        this.iRepoDetailsPresenter = iRepoDetailsPresenter;
    }


    @Override
    public void sendLogAndPass(final String log, final String pass, Context context) {
        this.context = context;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    UsersInformation usersInformation = GitHubRestClient.getInstance().suggestSync(getLoginAndPass(log, pass));
                    if (usersInformation == null)
                        iMainActivityPresenter.showToast();
                    else {
                        iMainActivityPresenter.correctUsernameAndPassword(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    iMainActivityPresenter.showInternetConnectionError();
                }
            }
        };
        executor.submit(runnable);

    }

    @Override
    public void saveUsernameAndPasswordInFile() {
        try{
            outputStream = context.openFileOutput(FILENAME, MODE_PRIVATE);
            outputStream.write(encodingUsernameAndPassword.getBytes());
            outputStream.close();
            iMainActivityPresenter.startReposListAcrivity();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void readUsernameAndPassFromFile(Context context) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(FILENAME)));
            while ((stringFromFile = br.readLine()) != null) {
                stringFromFileForClient = stringFromFile;
            }
            iRepoListPresenter.getReposList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getReposListFromServer() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    repoNames = GitHubRestClient.getInstance().getReposList("Basic " + stringFromFileForClient);
                    iRepoListPresenter.getReposListFromServer(repoNames);
                } catch (IOException e) {
                    e.printStackTrace();
                    iRepoListPresenter.showErrorSnackbar();
                }
            }
        }; executor.submit(runnable);
    }

    @Override
    public void getCommitInformation(final Repo repo) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<CommitsInformation> commitsInformations;
                    commitsInformations = GitHubRestClient.getInstance().getCommits("Basic " + stringFromFileForClient, repo.getOwner().getLogin(), repo.getName());
                    iRepoDetailsPresenter.showRepoDetails(commitsInformations);
                } catch (IOException e) {
                    e.printStackTrace();
                    iRepoDetailsPresenter.dismissProgressbar();
                    iRepoDetailsPresenter.showErrorSnackbar();
                }
            }
        };
        executor.submit(runnable);
    }

    @Override
    public boolean checkFileInSystem(Context context) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(FILENAME)));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteUsernameAndPassword(Context context) {
        context.deleteFile(FILENAME);
        iRepoListPresenter.startActivityForEnterUsernameAndPassword();
    }

    private String getLoginAndPass(String login, String pass) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builderForEncoding = new StringBuilder();
        builderForEncoding.append(login);
        builderForEncoding.append(":");
        builderForEncoding.append(pass);
        String logAndPassBase = Base64.encodeToString(builderForEncoding.toString().getBytes(), Base64.DEFAULT);
        encodingUsernameAndPassword = logAndPassBase.trim();
        builder.append("Basic ");
        builder.append(logAndPassBase.trim());
        return builder.toString();
    }
}
