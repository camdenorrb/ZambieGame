package me.camdenorrb.zambiegame.utils;

import java.util.function.Consumer;


/**
 * Utilities to make my life easier
 */
public final class JavaUtils {

	/**
	 * Constructs JavaUtils
	 */
	private JavaUtils() {}


	/**
	 * Applies an action on an object and returns it back
	 *
	 * @param input The object to use
	 * @param consumer The action to do
	 * @param <T> The input type
	 *
	 * @return The changed input
	 */
	public static <T> T apply(T input, Consumer<T> consumer) {
		consumer.accept(input);
		return input;
	}

}
