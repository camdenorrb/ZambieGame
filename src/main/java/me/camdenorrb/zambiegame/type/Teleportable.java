package me.camdenorrb.zambiegame.type;

/**
 * A type that is teleportable
 */
public interface Teleportable {

	/**
	 * Handle teleporting
	 *
	 * @param pos The new position
	 */
	void teleport(double x, double y);

}
