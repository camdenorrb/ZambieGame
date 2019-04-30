package me.camdenorrb.zambiegame.impl;

import me.camdenorrb.zambiegame.type.Named;


/**
 * A player implementation
 */
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

    /**
     * Gets the Jahcoins for the player
     *
     * @return The player's Jahcoins
     */
    public long getJahcoins() {
        return jahcoins;
    }

    /**
     * Sets the name of the player
     *
     * @param name The new name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the Jahcoins of the player
     *
     * @param jahcoins The new Jahcoins for the player
     */
    public void setJahcoins(long jahcoins) {
        this.jahcoins = jahcoins;
    }

}
