package me.camdenorrb.zambiegame.impl.pos;

public class Pos<N extends Number> {

	protected N x, y;


	public Pos(N x, N y) {
		this.x = x;
		this.y = y;
	}


	public N getX() {
		return x;
	}

	public N getY() {
		return y;
	}


	public MutablePos<N> toMutable() {
		return new MutablePos<>(x, y);
	}

}
