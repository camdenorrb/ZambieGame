package me.camdenorrb.zambiegame.impl.pos;

import me.camdenorrb.zambiegame.engine.physics.impl.Distance;

/**
 * A position implementation
 */
public class Pos {

	protected double x, y;


	/**
	 * Constructs a Position
	 *
	 * @param x The x position
	 * @param y The y position
	 */
	public Pos(double x, double y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Gets the 'X' position
	 *
	 * @return The 'X' position
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the 'Y' position
	 *
	 * @return The 'Y' position
	 */
	public double getY() {
		return y;
	}


	/**
	 * The distance between this position and another
	 *
	 * @param to The other position
	 *
	 * @return The distance between this and the other position
	 */
	public Distance distTo(Pos to) {

		final Pos fromDouble = new Pos(x, y);
		final Pos toDouble = new Pos(to.x, to.y);

		return new Distance(fromDouble.x - toDouble.x, fromDouble.y - toDouble.y);
		//return Math.sqrt(Math.pow(fromDouble.x - toDouble.x, 2) + Math.pow(fromDouble.y - toDouble.y, 2));
	}

	/**
	 * The absolute distance between this position and another
	 *
	 * @param to The other position
	 *
	 * @return The absolute distance between this and the other position
	 */
	public Distance distAbsTo(Pos to) {

		final Pos fromDouble = new Pos(x, y);
		final Pos toDouble = new Pos(to.x, to.y);

		return new Distance(Math.abs(fromDouble.x - toDouble.x), Math.abs(fromDouble.y - toDouble.y));
		//return Math.sqrt(Math.pow(fromDouble.x - toDouble.x, 2) + Math.pow(fromDouble.y - toDouble.y, 2));
	}

	/**
	 * Converts to a mutable state
	 *
	 * @return The mutable state
	 */
	public MutablePos toMutable() {
		return new MutablePos(x, y);
	}

	/**
	 * A human readable form of this class
	 *
	 * @return The human readable form of this class
	 */
	@Override
	public String toString() {
		return "Pos(" + x + ", " + y + ")";
	}

}
