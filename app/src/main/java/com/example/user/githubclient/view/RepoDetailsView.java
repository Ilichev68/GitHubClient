package com.example.user.githubclient.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.githubclient.R;
import com.example.user.githubclient.model.rest.restObjects.CommitsInformation;
import com.example.user.githubclient.model.rest.restObjects.Repo;
import com.example.user.githubclient.presenter.RepoDetailsPresenter;
import com.example.user.githubclient.presenter.interfaces.IRepoDetailsPresenter;
import com.example.user.githubclient.view.adapters.RecyclerViewAdapterForCommits;
import com.example.user.githubclient.view.interfaces.IRepoDetailsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoDetailsView extends AppCompatActivity implements IRepoDetailsView {

    @BindView(R.id.repo_name_details)
    TextView repoNameDetails;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.author_image)
    ImageView authorImage;
    @BindView(R.id.forks_image)
    ImageView forksImage;
    @BindView(R.id.watches_image)
    ImageView watchersImage;
    @BindView(R.id.forks_count)
    TextView forksCount;
    @BindView(R.id.watches_count)
    TextView watchersCount;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.rv_for_commits)
    RecyclerView recyclerView;
    @BindView(R.id.details_progressbar)
    ProgressBar detailsProgressbar;
    @BindView(R.id.commit_text)
    TextView commitTextView;
    private RecyclerViewAdapterForCommits adapter;
    private ArrayList<CommitsInformation> commitList = new ArrayList<>();

    private IRepoDetailsPresenter repoDetailsPresenter;

    public static void start(Context context, Repo repo) {
        Intent starter = new Intent(context, RepoDetailsView.class);
        starter.putExtra(Repo.class.getCanonicalName(), repo);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details_view);

        ButterKnife.bind(this);

        initialize(this);

        Repo repo = getIntent().getParcelableExtra(
                Repo.class.getCanonicalName());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapterForCommits(commitList, this);
        recyclerView.setAdapter(adapter);

        repoDetailsPresenter.getRepoDetails(repo);

    }

    @Override
    public void setRepoDetails(final Repo repo, final List<CommitsInformation> commitsInformations) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                commitTextView.setText(R.string.commits);
                repoNameDetails.setText(repo.getName());
                if (repo.getDescription() != null)
                    description.setText(repo.getDescription());
                else description.setText(R.string.no_description);
                forksCount.setText(String.valueOf(repo.getForks_count()));
                watchersCount.setText(String.valueOf(repo.getWatchers()));
                username.setText(repo.getOwner().getLogin());
                Glide.with(RepoDetailsView.this).load(R.drawable.logo).into(forksImage);
                Glide.with(RepoDetailsView.this).load(R.drawable.ic_watcher).into(watchersImage);
                Glide.with(RepoDetailsView.this).load(repo.getOwner().getImage()).into(authorImage);
                commitList.addAll(commitsInformations);
                adapter.notifyDataSetChanged();
                repoDetailsPresenter.dismissProgressbar();
            }
        });
    }

    @Override
    public void showInternetConnectionErrorSnackbar() {
        Snackbar.make(repoNameDetails, getResources().getString(R.string.internet_connection_problem), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void dismissProgressbar() {
        detailsProgressbar.setVisibility(View.INVISIBLE);
    }

    private void initialize(IRepoDetailsView iRepoDetailsView) {
        repoDetailsPresenter = new RepoDetailsPresenter(iRepoDetailsView);
    }
}
