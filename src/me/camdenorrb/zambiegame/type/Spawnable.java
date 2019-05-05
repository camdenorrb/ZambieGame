package me.camdenorrb.zambiegame.type;

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
	void spawn();
}


/*List<Element> getParts();*/
