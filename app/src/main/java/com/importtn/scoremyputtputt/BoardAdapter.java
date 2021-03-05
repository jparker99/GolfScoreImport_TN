package com.importtn.scoremyputtputt;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private final List<Player> players;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerNameThing;
        private final TextView playerScoreThing;

        public ViewHolder(View view) {
            super(view);
            playerNameThing = (TextView) view.findViewById(R.id.playerNameThing);
            playerScoreThing = (TextView) view.findViewById(R.id.playerScoreThing);
        }


        public TextView getPlayerNameThing() {
            return playerNameThing;
        }

        public TextView getPlayerScoreThing() {
            return playerScoreThing;
        }
    }

    public BoardAdapter(Game gameObject) {
        this.players = gameObject.getPlayers();
        Collections.sort(players);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.board_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getPlayerNameThing().setText(players.get(position).getName());
        viewHolder.getPlayerScoreThing().setText(Integer.toString(players.get(position).getTotalScore()));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
