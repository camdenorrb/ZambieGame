package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.struct.LazyStruct;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Objects;


public final class ResourceUtils {

	private static LazyStruct<ClassLoader> loader = LazyUtils.lazy(ResourceUtils.class::getClassLoader);


	private ResourceUtils() {}


	public static BufferedInputStream get(String path) {
		return new BufferedInputStream(Objects.requireNonNull(loader.get().getResourceAsStream(path)));
	}

}
