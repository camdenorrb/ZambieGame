package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.struct.LazyStruct;

import java.util.function.Supplier;


/**
 * Utilities for cleaner syntax with Lazy variables
 */
public final class LazyUtils {

	private LazyUtils() {}


	/**
	 * Makes a lazy instance of a supplier
	 *
	 * @param supplier The supplier that supplies the value
	 * @param <T> The type of value
	 *
	 * @return A lazy reference for the value
	 */
	public static <T> LazyStruct<T> lazy(Supplier<T> supplier) {
		return new LazyStruct<>() {
			@Override
			protected T onGet() {
				return supplier.get();
			}
		};
	}

}
