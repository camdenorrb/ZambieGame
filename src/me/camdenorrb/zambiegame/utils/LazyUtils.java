package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.struct.LazyStruct;

import java.util.function.Supplier;


public final class LazyUtils {

	private LazyUtils() {}


	public static <T> LazyStruct<T> lazy(Supplier<T> supplier) {
		return new LazyStruct<T>() {
			@Override
			protected T onGet() {
				return supplier.get();
			}
		};
	}

}
