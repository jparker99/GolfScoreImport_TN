package com.importtn.scoremyputtputt;

public class Player {
    private String name;
    private String icon; // path to where icon is saved

    public Player(String name, String icon) {
        this.name = name;
        this.icon = icon;
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
}
