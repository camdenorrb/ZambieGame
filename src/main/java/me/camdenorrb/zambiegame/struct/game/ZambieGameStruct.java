package me.camdenorrb.zambiegame.struct.game;

import me.camdenorrb.zambiegame.engine.game.struct.GameStruct;
import me.camdenorrb.zambiegame.engine.gui.impl.ProcGui;
import me.camdenorrb.zambiegame.entity.base.EntityBase;

import java.util.Deque;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;


public abstract class ZambieGameStruct extends GameStruct {

	protected final Deque<EntityBase> entities = new ConcurrentLinkedDeque<>();

	protected final static ThreadLocalRandom random = ThreadLocalRandom.current();


	public ZambieGameStruct(long tps) {
		super(tps);
	}


	public abstract ProcGui getGui();


	public Deque<EntityBase> getEntities() {
		return entities;
	}

	public static Random getRandom() {
		return random;
	}
}
