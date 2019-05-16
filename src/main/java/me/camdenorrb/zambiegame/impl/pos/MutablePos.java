package me.camdenorrb.zambiegame.impl.pos;

/**
 * A mutable version of a position
 */
public class MutablePos extends Pos {

	/**
	 * Constructs a Mutable Position
	 *
	 * @param x The x coordinate for the position
	 * @param y The y coordinate for the position
	 */
	public MutablePos(double x, double y) {
		super(x, y);
	}


	/**
	 * Adds (x, y) to the position
	 *
	 * @param x The x position to add
	 * @param y The y position to add
	 */
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * Subtracts (x, y) to the position
	 *
	 * @param x The x position to subtract
	 * @param y The y position to subtract
	 */
	public void sub(double x, double y) {
		this.x -= x;
		this.y -= y;
	}

	/**
	 * Divides (x, y) to the position
	 *
	 * @param x The x position to divides
	 * @param y The y position to divides
	 */
	public void divide(double x, double y) {
		this.x /= x;
		this.y /= y;
	}

	/**
	 * Multiplies (x, y) to the position
	 *
	 * @param x The x position to multiply
	 * @param y The y position to multiply
	 */
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


	/**
	 * Setting the (x, y) position
	 *
	 * @param x The new x position
	 * @param y The new y position
	 */
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