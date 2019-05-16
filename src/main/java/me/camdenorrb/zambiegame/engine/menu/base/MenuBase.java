package me.camdenorrb.zambiegame.engine.menu.base;

import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;
import me.camdenorrb.zambiegame.type.Pieced;


/**
 * The basics for a GUI menu
 */
public interface MenuBase extends Pieced {

    /**
     * Builds the Menu
     */
    void build();

    /**
     * Clears the Menu
     */
    void clear();

    /**
     * Shows the Menu
     */
    void show();

    /**
     * Hides the Menu
     */
    void hide();


    /**
     * Checks if the Menu is shown
     *
     * @return If the Menu is shown
     */
    boolean isShown();

    /**
     * Checks if the Menu is built
     *
     * @return If the Menu is built
     */
    boolean isBuilt();


    /**
     * Gets the GUI the Menu is on
     *
     * @return The GUI the Menu is on
     */
    GuiStruct getGui();

}
