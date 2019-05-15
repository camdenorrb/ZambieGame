package me.camdenorrb.zambiegame.listener.base;

import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;

/**
 * The basics of a key listener
 */
public interface KeyListenerBase {

    default void onKeyPress(GuiStruct gui, char key) {}

    default void onKeyRelease(GuiStruct gui, char key) {}

}
