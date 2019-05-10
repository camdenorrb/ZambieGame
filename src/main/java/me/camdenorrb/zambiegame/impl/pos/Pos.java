package me.camdenorrb.zambiegame.impl.pos;

import me.camdenorrb.zambiegame.impl.Pair;

/**
 * A position implementation
 */
public class Pos {

	protected double x, y;


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


	public Pair<Double, Double> distTo(Pos to) {

		final Pos fromDouble = new Pos(x, y);
		final Pos toDouble = new Pos(to.x, to.y);

		return new Pair<>(Math.abs(fromDouble.x - toDouble.x), Math.abs(fromDouble.y - toDouble.y));
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

	@Override
	public String toString() {
		return "Pos(" + x + ", " + y + ")";
	}

}
