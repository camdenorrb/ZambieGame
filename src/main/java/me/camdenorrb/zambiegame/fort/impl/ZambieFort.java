package me.camdenorrb.zambiegame.fort.impl;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.fort.struct.FortStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.util.Collections;
import java.util.List;


/**
 * Represents a Zambie Fort
 */
public class ZambieFort extends FortStruct {

    private Element.Image body = new Element.Image(pos, ResourceUtils.get("fort/destroyed-castle.png"));


    /**
     * Constructs a Zambie Fort instance
     *
     * @param game The game to spawn the Fort in
     */
    public ZambieFort(ZambieGameStruct game) {
        super(game);
    }


    /**
     * Gets the name of the Fort
     *
     * @return The name of the fort
     */
    @Override
    public String getName() {
        return "ZambieFort";
    }


    /**
     * Gets the center of the Fort
     *
     * @return The center of the fort
     */
    @Override
    public Pos getCenter() {
        return body.getCenter();
    }

    /**
     * Handles the spawning of the Entity
     *
     * @param pos Where to spawn the Entity
     */
    @Override
    protected void onSpawn(Pos pos) {
        body.getPosition().setXY(pos.getX(), pos.getY());
        super.onSpawn(pos);
    }

    /**
     * Teleports the fort to a new position
     *
     * @param x The x coordinate to teleport to
     * @param y The y coordinate to teleport to
     */
    @Override
    protected void onTeleport(double x, double y) {
        body.getPosition().setXY(x, y);
        super.onTeleport(x, y);
    }

    /**
     * Gets the height of the Fort
     *
     * @return The height of the fort
     */
    @Override
    public double getHeight() {
        return body.getSize().getHeight();
    }

    /**
     * Gets the width of the Fort
     *
     * @return The width of the fort
     */
    @Override
    public double getWidth() {
        return body.getSize().getWidth();
    }

    /**
     * Gets the parts of the Fort
     *
     * @return The parts of the fort
     */
    @Override
    public List<Element> getParts() {
        return Collections.singletonList(body);
    }

}
