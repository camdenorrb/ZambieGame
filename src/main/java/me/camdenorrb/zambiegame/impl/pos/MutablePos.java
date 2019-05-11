package me.camdenorrb.zambiegame.impl.pos;

/**
 * A mutable version of the position
 *
 */

public class MutablePos extends Pos {

	public MutablePos(double x, double y) {
		super(x, y);
	}


	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}

	public void sub(double x, double y) {
		this.x -= x;
		this.y -= y;
	}


	public void divide(double x, double y) {
		this.x /= x;
		this.y /= y;
	}

	public void multiply(double x, double y) {
		this.x *= x;
		this.y *= y;
	}


	/**
	 * Setting the 'X' position
	 *
	 * @param x The new 'X' position
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Setting the 'Y' position
	 *
	 * @param y The new 'Y' position
	 */
	public void setY(double y) {
		this.y = y;
	}


	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Converts to a mutable state
	 *
	 * @deprecated No reason to make a MutablePos mutable... Lol
	 */
	@Override
	@Deprecated
	public MutablePos toMutable() {
		return super.toMutable();
	}

}