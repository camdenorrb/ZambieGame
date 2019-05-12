package me.camdenorrb.zambiegame.engine.physics.impl;

/**
 * Helps me from creating 2 Double instances, which creates memory issues
 */
public class Distance {

	private final double x;

	private final double y;


	public Distance(double x, double y) {
		this.x = x;
		this.y = y;
	}


	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
