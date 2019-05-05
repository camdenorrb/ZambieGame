package me.camdenorrb.zambiegame.fort.struct;

import me.camdenorrb.zambiegame.fort.base.FortBase;
import me.camdenorrb.zambiegame.impl.pos.MutablePos;

import java.nio.file.Path;


public abstract class FortStruct implements FortBase {

	private int health;

	private boolean isSpawned;


	protected final MutablePos<Float> pos;


	protected abstract void onKill();

	protected abstract void onSpawn();

	protected abstract void onDamage();


	protected abstract Path getImagePath();


	public FortStruct(MutablePos<Float> pos) {
		this.pos = pos;
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