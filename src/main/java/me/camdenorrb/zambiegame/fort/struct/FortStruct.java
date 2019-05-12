package me.camdenorrb.zambiegame.fort.struct;

import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.fort.base.FortBase;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;


public abstract class FortStruct implements FortBase {

	private int health;

	private boolean isSpawned;

	private Pos entitySpawnPos;


	private final ZambieGame game;

	protected MutablePos pos = new MutablePos(0, 0);


	//protected abstract Path getImagePath();

	public FortStruct(ZambieGame game) {
		this.game = game;
	}


	protected void onDamage() {}

	protected void onTeleport(Pos toPos) {}


	protected void onKill() {
		game.getGui().remElements(getParts());
	}

	protected void onSpawn(Pos pos) {
		game.getGui().addElements(getParts());
	}


	public abstract double getWidth();

	public abstract double getHeight();


	public abstract Pos getCenter();


	@Override
	public final int getHealth() {
		return health;
	}

	@Override
	public final void setHealth(int health) {
		this.health = health;
		if (health <= 0 && isSpawned()) kill();
	}


	@Override
	public final void teleport(Pos pos) {

		this.pos = pos.toMutable();

		onTeleport(pos);

		entitySpawnPos = makeEntitySpawnPos();
	}

	@Override
	public final void spawn(Pos pos) {

		if (isSpawned) return;

		this.pos = pos.toMutable();
		onSpawn(pos);

		isSpawned = true;
		entitySpawnPos = makeEntitySpawnPos();
	}

	@Override
	public final void kill() {

		if (!isSpawned) return;

		pos = null;
		health = 0;

		onKill();
		isSpawned = false;
	}

	@Override
	public final boolean isSpawned() {
		return isSpawned;
	}

	public Pos getEntitySpawnPos() {
		return entitySpawnPos;
	}

	protected Pos makeEntitySpawnPos() {
		return new Pos(getCenter().getX(), getHeight() - 20);
	}

}