package me.camdenorrb.zambiegame.impl.pos;

/**
 * A position implementation
 *
 * @param <N> The number type
 */
public class Pos<N extends Number> {

	protected volatile N x, y;


	public Pos(N x, N y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Gets the 'X' position
	 *
	 * @return The 'X' position
	 */
	public N getX() {
		return x;
	}

	/**
	 * Gets the 'Y' position
	 *
	 * @return The 'Y' position
	 */
	public N getY() {
		return y;
	}


	/**
	 * Converts to a mutable state
	 *
	 * @return The mutable state
	 */
	public MutablePos<N> toMutable() {
		return new MutablePos<>(x, y);
	}

	@Override
	public String toString() {
		return "Pos(" + x + ", " + y + ")";
	}
}
