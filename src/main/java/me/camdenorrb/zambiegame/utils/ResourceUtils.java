package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;

import java.io.BufferedInputStream;

import static java.util.Objects.requireNonNull;
import static me.camdenorrb.zambiegame.utils.LazyUtils.lazy;


/**
 * Utilities to help obtain resources
 */
public final class ResourceUtils {

	private static LazyStruct<ClassLoader> loader = lazy(ResourceUtils.class::getClassLoader);


	/**
	 * Constructs ResourceUtils
	 */
	private ResourceUtils() {}


	/**
	 * Gets a resource input stream at a specific path
	 *
	 * @param path The path to look at
	 *
	 * @return An input stream to the resource
	 */
	public static BufferedInputStream get(String path) {
		return new BufferedInputStream(requireNonNull(loader.get().getResourceAsStream(path)));
	}

}
