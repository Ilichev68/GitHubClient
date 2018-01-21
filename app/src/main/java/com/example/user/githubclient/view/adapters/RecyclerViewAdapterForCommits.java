package com.example.user.githubclient.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.githubclient.R;
import com.example.user.githubclient.model.rest.restObjects.CommitsInformation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 21.01.2018.
 */

public class RecyclerViewAdapterForCommits extends RecyclerView.Adapter<RecyclerViewAdapterForCommits.ViewHolder> {

    private ArrayList<CommitsInformation> commits;
    private Context context;

    public RecyclerViewAdapterForCommits(ArrayList<CommitsInformation> repoNames, Context context) {
        this.commits = repoNames;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapterForCommits.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.commit_item, parent, false);
        return new RecyclerViewAdapterForCommits.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterForCommits.ViewHolder holder, int position) {
            holder.bindCommitView(commits.get(position));
    }

    @Override
    public int getItemCount() {
        return commits.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.commit_author_image)
        ImageView commit_author_image;
        @BindView(R.id.commit_author)
        TextView commitAuthor;
        @BindView(R.id.commit_date)
        TextView commitDate;
        @BindView(R.id.commit_message)
        TextView commitMessage;
        @BindView(R.id.hash)
        TextView hash;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void bindCommitView(CommitsInformation commitsInformation){
            Glide.with(context).load(commitsInformation.getCommitAuthor().getAvatarImage()).into(commit_author_image);
            commitAuthor.setText(commitsInformation.getCommit().getAuthor().getName());
            commitDate.setText(commitsInformation.getCommit().getAuthor().getDate());
            commitMessage.setText(commitsInformation.getCommit().getMessage());
            hash.setText(commitsInformation.getSha());
        }
    }
}
