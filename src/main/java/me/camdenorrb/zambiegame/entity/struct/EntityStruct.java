package me.camdenorrb.zambiegame.entity.struct;

import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Layer;
import me.camdenorrb.zambiegame.engine.physics.impl.velocity.MutableVelocity;
import me.camdenorrb.zambiegame.engine.physics.impl.velocity.Velocity;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.type.Ranged;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


/**
 * The strict structure of an Entity
 */
public abstract class EntityStruct implements EntityBase {

	private static final int DEFAULT_HEALTH = 20;


	private volatile int health;

	private volatile boolean isSpawned;


	protected final ZambieGameStruct game;


	protected final MutablePos pos = new MutablePos(0.0, 0.0);

	protected final MutableVelocity velocity = new MutableVelocity(0, 0);

	// Entity class --> Consumer
	protected final Map<Class, Set<Consumer<?>>> collisionHandlers = new HashMap<>();


	/**
	 * Constructs an EntityStruct
	 *
	 * @param game The game to spawn the Entity on
	 */
	public EntityStruct(ZambieGameStruct game) {
		this(DEFAULT_HEALTH, game);
	}

	/**
	 * Constructs an EntityStruct
	 *
	 * @param initHealth The initial health for the Entity
	 * @param game The game to spawn the Entity on
	 */
	public EntityStruct(int initHealth, ZambieGameStruct game) {
		this.game = game;
		this.health = initHealth;
	}


	/**
	 * Gets the center position of the Entity
	 *
	 * @return The center position of the Entity
	 */
	public abstract Pos getCenter();

	/**
	 * Handles tick of the entity
	 */
	protected abstract void onTick();


	/**
	 * Moves the Entity by a certain amount
	 *
	 * @param x The x distance to move by
	 * @param y The y distance to move by
	 */
	public void moveBy(double x, double y) {
		pos.add(x, y);
	}



	/**
	 * Handles death of the entity
	 */
	protected void onKill() {
		onRemove();
	}

	/**
	 * Handles spawn of the entity
	 *
	 * @param pos The position to spawn the Entity at
	 */
	protected void onSpawn(Pos pos) {
		game.getEntities().add(this);
		game.getGui().addElements(Layer.ENTITY, getParts());
	}


	/**
	 * Handles removal of the entity parts and self
	 */
	protected void onRemove() {
		game.getEntities().remove(this);
		game.getGui().remElements(Layer.ENTITY, getParts());
	}


	/**
	 * Handles teleporting of the entity
	 *
	 * @param x The x coordinate to teleport to
	 * @param y The y coordinate to teleport to
	 */
	protected void onTeleport(double x, double y) {
		pos.setXY(x, y);
	}


	/**
	 * Removes the Entity from existence
	 */
	public final void remove() {
		onRemove();
	}

	/**
	 * Adds a collision handler
	 *
	 * @param clazz The entity class to look for
	 * @param handler The handler for collisions
	 * @param <S> The type of ranged element to look for
	 */
	public final <S extends Ranged> void addCollideHandler(Class<S> clazz, Consumer<S> handler) {
		collisionHandlers.computeIfAbsent(clazz, ignored -> new HashSet<>()).add(handler);
	}

	/**
	 * Calls the collision handlers
	 *
	 * @param input The ranged element to input
	 * @param <S> The ranged element type
	 */
	@SuppressWarnings("unchecked")
	public final <S extends Ranged> void callCollideHandlers(S input) {

		final Set<Consumer<?>> consumers = collisionHandlers.get(input.getClass());
		if (consumers == null) return;

		consumers.stream().map(it -> (Consumer<S>) it).forEach(it -> it.accept(input));
	}

	/**
	 * Gets the health of the Entity
	 *
	 * @return The health of the Entity
	 */
	@Override
	public final int getHealth() {
		return health;
	}

	/**
	 * Sets the health of the Entity
	 *
	 * @param health The health to set the Entity to
	 */
	@Override
	public void setHealth(int health) {
		this.health = health;
		if (health <= 0 && isSpawned) kill();
	}

	/**
	 * Ticks the Entity
	 */
	public final void tick() {

		onTick();

		for (EntityBase entity : game.getEntities()) {
			if (entity == this || !entity.isInRange(getCenter())) continue;
			callCollideHandlers(entity);
		}
	}

	/**
	 * Spawns the Entity
	 *
	 * @param pos The position to spawn at
	 */
	@Override
	public final void spawn(Pos pos) {

		if (isSpawned) return;

		this.pos.setX(pos.getX());
		this.pos.setY(pos.getY());

		onSpawn(pos);
		isSpawned = true;
	}

	/**
	 * Kills the Entity
	 */
	@Override
	public final void kill() {

		if (!isSpawned) return;

		health = 0;

		onKill();
		isSpawned = false;
	}


	/**
	 * Teleport the Entity
	 *
	 * @param x The x coordinate to teleport to
	 * @param y The y coordinate to teleport to
	 */
	@Override
	public final void teleport(double x, double y) {
		onTeleport(x, y);
	}

	/**
	 * Gets the position of the Entity
	 *
	 * @return The position of the Entity
	 */
	@Override
	public final Pos getPosition() {
		return pos;
	}

	/**
	 * Checks if the Entity is spawned
	 *
	 * @return If the Entity is spawned
	 */
	@Override
	public final boolean isSpawned() {
		return isSpawned;
	}

	/**
	 * Gets the velocity of the Entity
	 *
	 * @return The velocity of the Entity
	 */
	@Override
	public final Velocity getVelocity() {
		return velocity;
	}

	/**
	 * Sets the velocity of the Entity
	 *
	 * @param velocity The new velocity
	 */
	@Override
	public void setVelocity(Velocity velocity) {
		this.velocity.setX(velocity.getX());
		this.velocity.setY(velocity.getY());
	}

}