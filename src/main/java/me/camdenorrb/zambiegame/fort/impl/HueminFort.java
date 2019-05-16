package me.camdenorrb.zambiegame.fort.impl;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.fort.struct.FortStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.util.Collections;
import java.util.List;


/**
 * Represents a Huemin Fort
 */
public class HueminFort extends FortStruct {

	private Element.Image body = new Element.Image(pos, ResourceUtils.get("fort/huemin-castle.png"));


	/**
	 * Constructs a Huemin Fort
	 *
	 * @param game The game to spawn the Huemin fort in
	 */
	public HueminFort(ZambieGameStruct game) {
		super(game);
	}


	/**
	 * Gets the name of the Fort
	 *
	 * @return The name of the Fort
	 */
	@Override
	public String getName() {
		return "HueminFort";
	}


	/**
	 * Gets the center of the Fort
	 *
	 * @return The center of the Fort
	 */
	@Override
	public Pos getCenter() {
		return isSpawned() ? body.getCenter() : null;
	}


	/**
	 * Handles the spawning of the Fort
	 *
	 * @param pos The position to spawn the fort at
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
	 * Gets the width of the Fort
	 *
	 * @return The width of the fort
	 */
	@Override
	public double getWidth() {
		return body.getSize().getWidth();
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
	 * Gets the parts of the Fort
	 *
	 * @return The parts of the fort
	 */
	@Override
	public List<Element> getParts() {
		return Collections.singletonList(body);
	}

}