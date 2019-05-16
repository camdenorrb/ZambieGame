package me.camdenorrb.zambiegame.entity.impl;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.Size;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.util.Collections;
import java.util.List;


/**
 * Represents a Cloud entity
 */
public class Cloud extends EntityStruct {

	public static final double WIDTH = 128;
	public static final double HEIGHT = 128;

	protected static final String CLOUD1_PATH = "cloud/cloud-1.png";

	protected Element.Image body = new Element.Image(pos, ResourceUtils.get(CLOUD1_PATH));


	/**
	 * Constructs a Cloud entity instance
	 *
	 * @param game The game to spawn in
	 * @param xVel The velocity of the cloud
	 */
	public Cloud(ZambieGameStruct game, double xVel) {
		super(Integer.MAX_VALUE, game);
		velocity.setX(xVel);
	}

	/**
	 * Gets the name of the Entity
	 *
	 * @return The name of the Entity
	 */
	@Override
	public String getName() {
		return "Cloud";
	}

	/**
	 * Gets the parts of the Entity
	 *
	 * @return The parts of the Entity
	 */
	@Override
	public List<Element> getParts() {
		return Collections.singletonList(body);
	}

	/**
	 * Gets the center of the Entity
	 *
	 * @return The center of the Entity
	 */
	@Override
	public Pos getCenter() {
		return body.getCenter();
	}

	/**
	 * Handles the ticking of the Entity
	 */
	@Override
	protected void onTick() {

		moveBy(velocity.getX(), 0);

		final Size guiSize = game.getGui().getSize();

		if (pos.getX() < 0) {
			teleport(guiSize.getWidth(), pos.getY());
		}
		else if (pos.getX() >= guiSize.getWidth()) {
			teleport(0, pos.getY());
		}

		if (pos.getY() < 0) {
			teleport(pos.getX(), guiSize.getHeight());
		}
		else if (pos.getY() >= guiSize.getHeight()) {
			teleport(pos.getX(), 0);
		}

	}

	/**
	 * Gets the width of the Entity
	 *
	 * @return The width of the Entity
	 */
	@Override
	public double getWidth() {
		return body.getSize().getWidth();
	}

	/**
	 * Gets the height of the Entity
	 *
	 * @return The height of the Entity
	 */
	@Override
	public double getHeight() {
		return body.getSize().getHeight();
	}

	/**
	 * Checks if the Entity is in range
	 *
	 * @param pos The position of the Entity
	 *
	 * @return If the position is in range
	 */
	@Override
	public boolean isInRange(Pos pos) {
		return false;
	}

	/**
	 * Moves the Entity by a certain amount
	 *
	 * @param x The x distance to move by
	 * @param y The y distance to move by
	 */
	@Override
	public void moveBy(double x, double y) {
		super.moveBy(x, y);
		body.getPosition().add(x, y);
	}

	/**
	 * Handles the teleporting of an Entity
	 *
	 * @param x The x coordinate to teleport to
	 * @param y The y coordinate to teleport to
	 */
	@Override
	protected void onTeleport(double x, double y) {
		super.onTeleport(x, y);
		body.getPosition().setXY(x, y);
	}
}
