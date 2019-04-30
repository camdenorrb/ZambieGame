package me.camdenorrb.zambiegame.impl.pos;

/**
 * A mutable version of the position
 *
 * @param <N> The number type
 */
public class MutablePos<N extends Number> extends Pos<N> {

	public MutablePos(N x, N y) {
		super(x, y);
	}


	/**
	 * Setting the 'X' position
	 *
	 * @param x The new 'X' position
	 */
	public void setX(N x) {
		this.x = x;
	}

	/**
	 * Setting the 'Y' position
	 *
	 * @param y The new 'Y' position
	 */
	public void setY(N y) {
		this.y = y;
	}


	/**
	 * Converts to a mutable state
	 *
	 * @deprecated No reason to make a MutablePos mutable... Lol
	 */
	@Override
	@Deprecated
	public MutablePos<N> toMutable() {
		return super.toMutable();
	}

}
