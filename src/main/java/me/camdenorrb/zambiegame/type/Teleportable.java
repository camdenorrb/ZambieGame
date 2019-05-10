package me.camdenorrb.zambiegame.type;

import me.camdenorrb.zambiegame.impl.pos.Pos;


/**
 * A type that is teleportable
 */
public interface Teleportable {

	/**
	 * Handle teleporting
	 *
	 * @param pos The new position
	 */
	void teleport(Pos pos);

}
