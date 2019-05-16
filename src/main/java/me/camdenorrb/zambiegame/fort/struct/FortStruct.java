package me.camdenorrb.zambiegame.fort.struct;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Layer;
import me.camdenorrb.zambiegame.fort.base.FortBase;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;


/**
 * A strict structure for every Fort
 */
public abstract class FortStruct implements FortBase {

	protected int health;

	protected boolean isSpawned;

	protected Pos entitySpawnPos;


	protected final ZambieGameStruct game;

	protected MutablePos pos = new MutablePos(0, 0);


	//protected abstract Path getImagePath();

	/**
	 * Constructs a Fort structure
	 * @param game
	 */
	public FortStruct(ZambieGameStruct game) {
		this.game = game;
	}


	/**
	 * Handles damage on the Fort
	 */
	protected void onDamage() {}

	/**
	 * Teleports the fort to a new position
	 *
	 * @param x The x coordinate to teleport to
	 * @param y The y coordinate to teleport to
	 */
	protected void onTeleport(double x, double y) {
		pos.setXY(x, y);
	}


	/**
	 * Handles the killing of Forts
	 */
	protected void onKill() {
		game.getGui().remElements(Layer.FORT, getParts());
	}

	/**
	 * Handles the spawning of Forts
	 *
	 * @param pos The position to spawn at
	 */
	protected void onSpawn(Pos pos) {
		game.getGui().addElements(Layer.FORT, getParts());
	}


	/**
	 * Gets the width of the Fort
	 *
	 * @return The width of the fort
	 */
	public abstract double getWidth();

	/**
	 * Gets the height of the Fort
	 *
	 * @return The height of the fort
	 */
	public abstract double getHeight();


	/**
	 * Gets the center of the Fort
	 *
	 * @return The center of the fort
	 */
	public abstract Pos getCenter();


	/**
	 * Gets the health of the Fort
	 *
	 * @return The health of the Fort
	 */
	@Override
	public final int getHealth() {
		return health;
	}

	/**
	 * Sets the health of the Fort
	 *
	 * @param health The new health of the Fort
	 */
	@Override
	public final void setHealth(int health) {
		this.health = health;
		if (health <= 0 && isSpawned()) kill();
	}


	/**
	 * Teleports the Fort
	 *
	 * @param x The new x position for the Fort
	 * @param y The new y position for the Fort
	 */
	@Override
	public final void teleport(double x, double y) {
		onTeleport(x, y);
		entitySpawnPos = makeEntitySpawnPos();
	}

	/**
	 * Spawns the Fort at a position
	 *
	 * @param pos The position to spawn the fort at
	 */
	@Override
	public final void spawn(Pos pos) {

		if (isSpawned) return;

		this.pos = pos.toMutable();
		onSpawn(pos);

		isSpawned = true;
		entitySpawnPos = makeEntitySpawnPos();
	}

	/**
	 * Kills the Fort
	 */
	@Override
	public final void kill() {

		if (!isSpawned) return;

		pos = null;
		health = 0;

		onKill();
		isSpawned = false;
	}

	/**
	 * Checks if the fort has been spawned
	 *
	 * @return If the fort has been spawned
	 */
	@Override
	public final boolean isSpawned() {
		return isSpawned;
	}

	/**
	 * Gets the Entity spawn position
	 *
	 * @return The Entities spawn position
	 */
	public Pos getEntitySpawnPos() {
		return entitySpawnPos;
	}

	/**
	 * Constructs a spawn position for Entities
	 *
	 * @return The spawn position for Entities
	 */
	protected Pos makeEntitySpawnPos() {
		return new Pos(getCenter().getX(), getHeight() - 20);
	}

}