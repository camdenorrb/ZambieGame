package me.camdenorrb.zambiegame.fort.struct;

import me.camdenorrb.zambiegame.fort.base.FortBase;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;
import me.camdenorrb.zambiegame.impl.pos.Pos;


public abstract class FortStruct implements FortBase {

	private int health;

	private boolean isSpawned;


	protected final MutablePos<Double> pos;


	protected void onKill() {}

	protected void onSpawn() {}

	protected void onDamage() {}


	//protected abstract Path getImagePath();


	public FortStruct(Pos<Double> pos) {
		this.pos = pos.toMutable();
	}


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
	public void teleport(Pos<Double> pos) {
		this.pos.setX(pos.getX());
		this.pos.setY(pos.getY());
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
	public final boolean isSpawned() {
		return isSpawned;
	}

}