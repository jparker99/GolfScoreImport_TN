package com.importtn.scoremyputtputt;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String icon; // path to where icon is saved
    private int[] scores;

    public Player(String name, String icon) {
        this.name = name;
        this.icon = icon;
        this.scores = new int[18];
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }

    private void setIcon(String icon) {
        this.icon = icon;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }
}
