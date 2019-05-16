package me.camdenorrb.zambiegame.engine.physics.impl;

/**
 * Represents the Distance between points
 *
 * Helps me from creating 2 Double instances, which creates memory issues
 */
public class Distance {

	private final double x;

	private final double y;


	/**
	 * Construct a Distance instance
	 *
	 * @param x The x distance
	 * @param y The y distance
	 */
	public Distance(double x, double y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Gets the x distance
	 *
	 * @return The x distance
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the y distance
	 *
	 * @return The y distance
	 */
	public double getY() {
		return y;
	}

	/**
	 * Converts this into a human readable string
	 *
	 * @return The human readable string
	 */
	@Override
	public String toString() {
		return "Distance (" + x + ", " + y + ")";
	}
}
