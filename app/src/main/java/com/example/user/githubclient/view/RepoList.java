package com.example.user.githubclient.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.user.githubclient.R;
import com.example.user.githubclient.model.rest.restObjects.Repo;
import com.example.user.githubclient.presenter.RepoListPresenter;
import com.example.user.githubclient.presenter.interfaces.IRepoListPresenter;
import com.example.user.githubclient.view.adapters.RecyclerViewAdapter;
import com.example.user.githubclient.view.interfaces.IRepoListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoList extends AppCompatActivity implements IRepoListView, RecyclerViewAdapter.Listner {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgressBar;
    private RecyclerViewAdapter adapter;
    private IRepoListPresenter repoListPresenter;
    private ArrayList<Repo> list = new ArrayList<>();
    private View view;

    public static void start(Context context) {
        Intent starter = new Intent(context, RepoList.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);

        ButterKnife.bind(this);

        initialize(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(list);
        adapter.setListner(this);
        recyclerView.setAdapter(adapter);

        repoListPresenter.readUsernameAndPassFromFile(this);
    }

    private void initialize(IRepoListView iRepoListView) {
        repoListPresenter = new RepoListPresenter(iRepoListView);
    }

    @Override
    public void showRV(ArrayList<Repo> list) {
        this.list.addAll(list);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                repoListPresenter.dismissLoadingProgressBar();
            }
        });


    }

    @Override
    public void dismissProgressBar() {
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showConnectProblemSnackbar() {
        Snackbar.make(recyclerView, getResources().getString(R.string.internet_connection_problem), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(Repo repo) {
        RepoDetailsView.start(this, repo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_item_exit):
                repoListPresenter.switchUser(this);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
