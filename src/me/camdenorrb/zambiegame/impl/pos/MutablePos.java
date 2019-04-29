package me.camdenorrb.zambiegame.impl.pos;

public class MutablePos<N extends Number> extends Pos<N> {

	public MutablePos(N x, N y) {
		super(x, y);
	}


	public void setX(N x) {
		this.x = x;
	}

	public void setY(N y) {
		this.y = y;
	}


	/**
	 * @deprecated No reason to make a MutablePos mutable... Lol
	 */
	@Override
	@Deprecated
	public MutablePos<N> toMutable() {
		return super.toMutable();
	}

}
