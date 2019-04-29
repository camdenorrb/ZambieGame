package me.camdenorrb.zambiegame.utils;

import java.util.function.Consumer;


public final class JavaUtils {

	private JavaUtils() {}


	public static <T> T apply(T input, Consumer<T> consumer) {
		consumer.accept(input);
		return input;
	}

}
