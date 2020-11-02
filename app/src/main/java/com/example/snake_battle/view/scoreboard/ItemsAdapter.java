package com.example.snake_battle.view.scoreboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snake_battle.R;
import com.example.snake_battle.model.domainModel.Score;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    List<Score> listOfScores;

    public ItemsAdapter(List<Score> list){
        this.listOfScores=list;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        holder.name.setText(listOfScores.get(position).getNickname());
        holder.score.setText(listOfScores.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return listOfScores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nickname);
            score = itemView.findViewById(R.id.score);
        }
    }
}
