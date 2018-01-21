package com.example.user.githubclient.view.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.githubclient.R;
import com.example.user.githubclient.model.rest.restObjects.Repo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 20.01.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Repo> repoNames;
    private Listner listner;

    public RecyclerViewAdapter(ArrayList<Repo> repoNames) {
        this.repoNames = repoNames;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);
        return new RecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.bindTextView(repoNames.get(position));
    }

    @Override
    public int getItemCount() {
        return repoNames.size();
    }

    public void setListner(Listner listner) {
        this.listner = listner;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv)
        CardView cardView;
        @BindView(R.id.repo_name)
        TextView repoName;
        private Repo repo;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onClick(repo);
                }
            });
        }

        void bindTextView(Repo repo){
            this.repo = repo;
            repoName.setText(repo.getName());
        }
    }

    public interface Listner{
        void onClick(Repo repo);
    }
}
