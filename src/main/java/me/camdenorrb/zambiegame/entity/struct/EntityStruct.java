package me.camdenorrb.zambiegame.entity.struct;

import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.physics.impl.velocity.MutableVelocity;
import me.camdenorrb.zambiegame.engine.physics.impl.velocity.Velocity;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.type.Ranged;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


/**
 * The strict structure of the entity
 */
public abstract class EntityStruct implements EntityBase {

	private static final int DEFAULT_HEALTH = 20;


	private int health;

	private boolean isSpawned;


	protected final ZambieGame game;

	protected final MutablePos pos = new MutablePos(0.0, 0.0);

	protected final MutableVelocity velocity = new MutableVelocity(0, 0);

	protected final Map<Class, Set<Consumer<?>>> collisionHandlers = new HashMap<>();


	public abstract Pos getCenter();

	/**
	 * Handles tick of the entity
	 */
	protected abstract void onTick();


	/**
	 * Handles death of the entity
	 */
	protected void onKill() {
		game.getEntities().remove(this);
		game.getGui().remElements(getParts());
	}

	/**
	 * Handles spawn of the entity
	 */
	protected void onSpawn(Pos pos) {
		game.getEntities().add(this);
		game.getGui().addElements(getParts());
	}


	/**
	 * Handles spawn of the entity
	 */
	protected void onRemove() {
		game.getGui().remElements(getParts());
	}


	/**
	 * Handles teleporting of the entity
	 */
	protected void onTeleport(Pos newPos) {

		remove();

		pos.setX(newPos.getX());
		pos.setY(newPos.getY());

		spawn(newPos);
	}


	public EntityStruct(ZambieGame game) {
		this(DEFAULT_HEALTH, game);
	}

	public EntityStruct(int initHealth, ZambieGame game) {
		this.game = game;
		this.health = initHealth;
	}


	public final void remove() {
		onRemove();
	}

	public final <S extends Ranged> void addCollideHandler(Class<S> clazz, Consumer<S> handler) {
		collisionHandlers.computeIfAbsent(clazz, (ignored) -> new HashSet<>()).add(handler);
	}

	@SuppressWarnings("unchecked")
	public final <S extends Ranged> void callCollideHandlers(S input) {

		final Set<Consumer<?>> consumers = collisionHandlers.get(input.getClass());
		if (consumers == null) return;

		consumers.stream().map(it -> (Consumer<S>) it).forEach(it -> it.accept(input));
	}

	@Override
	public final int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
		if (health <= 0 && !isSpawned) kill();
	}

	public final void tick() {

		onTick();

		for (EntityBase entity : game.getEntities()) {
			if (entity == this || !entity.isInRange(getCenter())) continue;
			callCollideHandlers(entity);
		}
	}

	@Override
	public final void spawn(Pos pos) {

		if (isSpawned) return;

		this.pos.setX(pos.getX());
		this.pos.setY(pos.getY());

		onSpawn(pos);
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
	public final void teleport(Pos pos) {

		this.pos.setX(pos.getX());
		this.pos.setY(pos.getY());

		onTeleport(pos);
	}

	@Override
	public final Pos getPosition() {
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