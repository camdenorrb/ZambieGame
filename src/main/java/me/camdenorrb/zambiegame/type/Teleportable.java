package me.camdenorrb.zambiegame.type;

/**
 * A type that is teleportable
 */
public interface Teleportable {

	/**
	 * Handle teleporting
	 *
	 * @param x The new x position
	 * @param y The new y position
	 */
	void teleport(double x, double y);

}
