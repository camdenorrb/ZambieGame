package me.camdenorrb.zambiegame.engine.net.struct.db;

import me.camdenorrb.zambiegame.engine.net.base.db.DBBase;


/**
 * A strict structure for databases
 */
public abstract class DBStruct implements DBBase {

    private boolean isConnected = false;


    /**
     * Handles connections
     */
    protected abstract void onConnect();

    /**
     * Handles disconnecting
     */
    protected abstract void onDisconnect();


    /**
     * Connects to the database
     */
    @Override
    public void connect() {

        if (isConnected) return;

        onConnect();
        isConnected = true;
    }

    /**
     * Disconnects from the database
     */
    @Override
    public void disconnect() {

        if (!isConnected) return;

        onDisconnect();
        isConnected = false;
    }


    /**
     * Checks if it is connected to the database
     *
     * @return If it is connected to the database
     */
    @Override
    public boolean isConnected() {
        return isConnected;
    }

}
