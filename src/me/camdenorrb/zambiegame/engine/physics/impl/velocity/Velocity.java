package me.camdenorrb.zambiegame.engine.physics.impl.velocity;

public class Velocity {

	protected double x, y;


	public Velocity(double x, double y) {
		this.x = x;
		this.y = y;
	}


	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}


	public MutableVelocity toMutable() {
		return new MutableVelocity(x, y);
	}

}
