package me.camdenorrb.zambiegame.engine.physics.impl.velocity;

/**
 * A Velocity implementation for moving things
 */
public class Velocity {

	protected double x, y;


	public Velocity(double x, double y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Gets the 'X' velocity
	 *
	 * @return The 'X' velocity
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the 'Y' velocity
	 *
	 * @return The 'Y' velocity
	 */
	public double getY() {
		return y;
	}


	/**
	 * Converts to a mutable state
	 *
	 * @return The mutable state
	 */
	public MutableVelocity toMutable() {
		return new MutableVelocity(x, y);
	}

}
