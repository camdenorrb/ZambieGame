package me.camdenorrb.zambiegame.type;

import me.camdenorrb.zambiegame.impl.pos.Pos;


/**
 * A type that holds a position
 */
public interface Positioned {

	/**
	 * The position of the implementation
	 *
	 * @return The position
	 */
	Pos getPosition();

}
