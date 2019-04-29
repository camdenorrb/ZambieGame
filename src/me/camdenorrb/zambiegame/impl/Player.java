package me.camdenorrb.zambiegame.impl;

import me.camdenorrb.zambiegame.type.Named;

public class Player implements Named {

    private String name;

    private final int id;

    private long jahcoins;


    public Player(int id, String name, long jahcoins) {
        this.id = id;
        this.name = name;
        this.jahcoins = jahcoins;
    }


    @Override
    public String getName() {
        return name;
    }

    public long getJahcoins() {
        return jahcoins;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setJahcoins(long jahcoins) {
        this.jahcoins = jahcoins;
    }

}
