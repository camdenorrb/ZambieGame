package me.camdenorrb.zambiegame.entity.struct;

import me.camdenorrb.zambiegame.engine.physics.impl.velocity.MutableVelocity;
import me.camdenorrb.zambiegame.engine.physics.impl.velocity.Velocity;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;


/**
 * The strict structure of the entity
 */
public abstract class EntityStruct implements EntityBase {

	private static final int DEFAULT_HEALTH = 20;


	private int health;

	private boolean isSpawned;


	protected final MutablePos<Double> pos;

	protected final MutableVelocity velocity = new MutableVelocity(0, 0);


	/**
	 * Handles death of the entity
	 */
	protected abstract void onKill();

	/**
	 * Handles spawn of the entity
	 */
	protected abstract void onSpawn();


	public EntityStruct(int initHealth, Pos<Double> pos) {
		this.health = initHealth;
		this.pos = pos.toMutable();
	}

	public EntityStruct(Pos<Double> pos) {
		this(DEFAULT_HEALTH, pos);
	}


	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
		if (health <= 0 && !isSpawned) kill();
	}

	@Override
	public final void spawn() {

		if (isSpawned) return;

		onSpawn();
		isSpawned = true;
	}

	@Override
	public final void kill() {

		if (!isSpawned) return;

		health = 0;

		onKill();
		isSpawned = false;
	}

	@Override
	public final Pos<Double> getPosition() {
		return pos;
	}

	@Override
	public final boolean isSpawned() {
		return isSpawned;
	}

	@Override
	public final Velocity getVelocity() {
		return velocity;
	}

	@Override
	public void setVelocity(Velocity velocity) {
		this.velocity.setX(velocity.getX());
		this.velocity.setY(velocity.getY());
	}

}