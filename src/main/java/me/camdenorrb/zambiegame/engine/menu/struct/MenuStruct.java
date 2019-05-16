package me.camdenorrb.zambiegame.engine.menu.struct;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Layer;
import me.camdenorrb.zambiegame.engine.gui.struct.GuiStruct;
import me.camdenorrb.zambiegame.engine.menu.base.MenuBase;

import java.util.ArrayList;
import java.util.List;


/**
 * A strict structure for GUI menus
 */
public abstract class MenuStruct implements MenuBase {

    protected boolean isBuilt, isShown;


    protected final GuiStruct gui;

    protected final List<Element> parts = new ArrayList<>();


    /**
     * Handles the building of the Menu
     */
    protected void onBuild() {}

    /**
     * Handles the clearing of the Menu
     */
    protected void onClear() {}


    /**
     * Handles the Showing of the Menu
     */
    protected void onShow() {
        gui.addElements(Layer.MENU, parts);
    }

    /**
     * Handles the hiding of the Menu
     */
    protected void onHide() {
        gui.remElements(Layer.MENU, parts);
    }


    /**
     * Creates an instance of the Menu
     *
     * @param gui The gui to place the Menu on
     */
    public MenuStruct(GuiStruct gui) {
        this.gui = gui;
    }


    /**
     * Builds the Menu
     */
    @Override
    public final void build() {

        if (isBuilt) return;

        onBuild();
        isBuilt = true;
    }

    /**
     * Clears the Menu
     */
    @Override
    public final void clear() {

        if (!isBuilt) return;

        onClear();
        parts.clear();

        isBuilt = false;
    }

    /**
     * Shows the Menu
     */
    @Override
    public final void show() {

        if (isShown) return;

        onShow();
        isShown = true;
    }

    /**
     * Hides the Menu
     */
    @Override
    public final void hide() {

        if (!isShown) return;

        onHide();
        isShown = false;
    }


    /**
     * Checks if the Menu is shown
     *
     * @return If the Menu is shown
     */
    @Override
    public boolean isShown() {
        return isShown;
    }

    /**
     * Checks if the Menu is built
     *
     * @return If the Menu is built
     */
    @Override
    public boolean isBuilt() {
        return isBuilt;
    }


    /**
     * Gets the GUI for the Menu
     *
     * @return The GUI for the Menu
     */
    @Override
    public GuiStruct getGui() {
        return gui;
    }

    /**
     * Gets the parts of the Menu
     *
     * @return The parts of the Menu
     */
    @Override
    public final List<Element> getParts() {
        return parts;
    }


    /**
     * Adds parts to the Menu
     *
     * @param element The element to add
     */
    protected final void addPart(Element element) {
        parts.add(element);
    }

    /**
     * Removes parts to the Menu
     *
     * @param element The element to remove
     */
    protected final void remPart(Element element) {
        parts.remove(element);
    }

}
