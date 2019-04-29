package me.camdenorrb.zambiegame.engine.net.base.db;

import me.camdenorrb.zambiegame.type.Named;

public interface DBBase extends Named {

    void connect();

    void disconnect();


    /**
     * Gets the host in a (ip:port) format
     *
     * @return the host of the database
     */
    String getHost();

    boolean isConnected();

}
