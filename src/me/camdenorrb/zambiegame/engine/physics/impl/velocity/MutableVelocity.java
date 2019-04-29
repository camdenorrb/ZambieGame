package me.camdenorrb.zambiegame.engine.physics.impl.velocity;

public class MutableVelocity extends Velocity {

	public MutableVelocity(double x, double y) {
		super(x, y);
	}


	public void setX(double x) {
		this.x = x;
	}

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
