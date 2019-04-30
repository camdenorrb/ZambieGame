package me.camdenorrb.zambiegame.engine.net.base.db;

import me.camdenorrb.zambiegame.type.Named;


/**
 * The basics for a database
 */
public interface DBBase extends Named {

    /**
     * Connects to a database
     */
    void connect();

    /**
     * Disconnects from a database
     */
    void disconnect();


    /**
     * Gets the host in a (ip:port) format
     *
     * @return the host of the database
     */
    String getHost();

    /**
     * Checks if the client is connected to the database
     *
     * @return If the client is connected
     */
    boolean isConnected();

}
