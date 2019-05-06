package me.camdenorrb.zambiegame.entity.impl;

import me.camdenorrb.zambiegame.ZambieGame;
import me.camdenorrb.zambiegame.engine.gui.impl.element.Element;
import me.camdenorrb.zambiegame.engine.physics.impl.velocity.Velocity;
import me.camdenorrb.zambiegame.entity.base.EntityBase;
import me.camdenorrb.zambiegame.impl.pos.Pos;

import java.util.List;


/**
 * A Zambie entity that acts as the enemy in the game.
 */
public class Zambie implements EntityBase {

	private int health = 20;

	private final ZambieGame game;


	public Zambie(ZambieGame game) {
		this.game = game;
	}


	@Override
	public String getName() {
		return "Zambie";
	}

	@Override
	public boolean isSpawned() {
		return false;
	}

	@Override
	public Pos getPosition() {
		return null;
	}

	@Override
	public Velocity getVelocity() {
		return null;
	}

	@Override
	public void setVelocity(Velocity velocity) {

	}

	@Override
	public void tick() {

	}


	@Override
	public void spawn() {

	}

	@Override
	public void kill() {

	}

	@Override
	public void teleport(Pos<Double> pos) {

	}

	@Override
	public List<Element> getParts() {
		return null;
	}


	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

}
