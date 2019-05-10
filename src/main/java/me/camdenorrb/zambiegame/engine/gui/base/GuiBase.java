package me.camdenorrb.zambiegame.engine.gui.base;

import me.camdenorrb.zambiegame.type.Named;


/**
 * The basics for every GUI
 */
public interface GuiBase extends Named {

    /**
     * Showing the GUI
     */
    void show();

    /**
     * Hiding the GUI
     */
    void hide();


    /**
     * Telling if it's visible or not
     *
     * @return If it's visible
     */
    boolean isVisible();

}