package me.camdenorrb.zambiegame.type;

import me.camdenorrb.zambiegame.engine.physics.impl.velocity.Velocity;


/**
 * A mobile type
 */
public interface Mobile {

	/**
	 * Gets the velocity
	 *
	 * @return The velocity
	 */
	Velocity getVelocity();

	/**
	 * Sets the velocity
	 *
	 * @param velocity The new velocity
	 */
	void setVelocity(Velocity velocity);

}
