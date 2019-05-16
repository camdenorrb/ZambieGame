package me.camdenorrb.zambiegame.struct.game;

import me.camdenorrb.zambiegame.engine.game.struct.GameStruct;
import me.camdenorrb.zambiegame.engine.gui.impl.ProcGui;
import me.camdenorrb.zambiegame.entity.base.EntityBase;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * A strict structure for Zambie Games
 */
public abstract class ZambieGameStruct extends GameStruct {

	protected final Deque<EntityBase> entities = new ConcurrentLinkedDeque<>();


	/**
	 * Constructs a Zambie Game Structure
	 *
	 * @param tps The ticks per second
	 */
	public ZambieGameStruct(long tps) {
		super(tps);
	}

	/**
	 * Gets the GUI
	 *
	 * @return The GUI
	 */
	public abstract ProcGui getGui();

	/**
	 * Gets the Entities on the game
	 *
	 * @return The entities on the game
	 */
	public Deque<EntityBase> getEntities() {
		return entities;
	}

}
