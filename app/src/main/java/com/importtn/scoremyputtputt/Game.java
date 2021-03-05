package com.importtn.scoremyputtputt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    private List<Player> players;
    private int currentHole;


    public Game() {
        this.players = new ArrayList<>();
        this.currentHole = 1;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentHole() {
        return currentHole;
    }

    public void setCurrentHole(int currentHole) {
        this.currentHole = currentHole;
    }

    public void nextHole() {
        this.currentHole++;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}
