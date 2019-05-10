package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.struct.LazyStruct;

import java.io.InputStream;


public final class ResourceUtils {

	private static LazyStruct<ClassLoader> loader = LazyUtils.lazy(ResourceUtils.class::getClassLoader);


	private ResourceUtils() {}


	public static InputStream get(String path) {
		return loader.get().getResourceAsStream(path);
	}

}
