package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.impl.pos.Pos;


public final class PosUtils {

	private PosUtils() {}


	public static Pos<Float> toFloat(Pos<Double> pos) {
		return new Pos<>(pos.getX().floatValue(), pos.getY().floatValue());
	}

}
