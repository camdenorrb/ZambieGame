package me.camdenorrb.zambiegame.engine.gui.base;

import me.camdenorrb.zambiegame.type.Named;


public interface GuiBase extends Named {

    void show();

    void hide();


    boolean isVisible();

}
