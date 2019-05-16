package me.camdenorrb.zambiegame.listener.base;

import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;

/**
 * The basics of a key listener
 */
public interface KeyListenerBase {

    /**
     * Handles key pressing
     *
     * @param gui The gui pressed on
     * @param keyCode The keycode pressed
     */
    default void onKeyPress(GuiStruct gui, int keyCode) {}

    /**
     * Handles key release
     *
     * @param gui The gui released on
     * @param keyCode The keycode released
     */
    default void onKeyRelease(GuiStruct gui, int keyCode) {}

}
