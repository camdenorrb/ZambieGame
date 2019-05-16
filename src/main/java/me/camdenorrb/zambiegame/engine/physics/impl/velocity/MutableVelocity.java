package me.camdenorrb.zambiegame.engine.physics.impl.velocity;

/**
 * Velocity in a mutable state
 */
public class MutableVelocity extends Velocity {

	/**
	 * Constructs a MutableVelocity instance
	 *
	 * @param x The x magnitude
	 * @param y The y magnitude
	 */
	public MutableVelocity(double x, double y) {
		super(x, y);
	}


	/**
	 * Sets the 'X' velocity
	 *
	 * @param x The new 'X' velocity
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the 'Y' velocity
	 *
	 * @param y The new 'Y' velocity
	 */
	public void setY(double y) {
		this.y = y;
	}


	/**
	 * @deprecated No reason to make a MutableVelocity mutable... Lol
	 */
	@Override
	@Deprecated
	public MutableVelocity toMutable() {
		return super.toMutable();
	}

}
