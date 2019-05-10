package me.camdenorrb.zambiegame.type;

import me.camdenorrb.zambiegame.impl.pos.Pos;

/**
 * A type that is spawnable
 */
public interface Spawnable {

	/**
	 * Whether or not it's spawned
	 *
	 * @return if it's spawned
	 */
	boolean isSpawned();

	/**
	 * Handle spawning
	 */
	void spawn(Pos pos);
}


/*List<Element> getParts();*/
