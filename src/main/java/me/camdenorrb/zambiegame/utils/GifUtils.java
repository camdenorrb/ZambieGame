package me.camdenorrb.zambiegame.utils;

import me.camdenorrb.zambiegame.engine.gif.Gif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;

import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.InputStream;

import static me.camdenorrb.zambiegame.utils.JavaUtils.*;
import static me.camdenorrb.zambiegame.utils.LazyUtils.*;


/**
 * Utilities to help with Gif related things
 */
public final class GifUtils {

	/**
	 * Constructs GifUtils
	 */
	private GifUtils() {}


	/**
	 * Loads a Gif from a file
	 *
	 * @param file The file to load from
	 *
	 * @return The loaded gif
	 */
	public static Gif load(File file) {
		return apply(new Gif(), it -> it.read(file));
	}

	/**
	 * Loads a Gif from a input stream
	 *
	 * @param inputStream The input stream to load from
	 *
	 * @return The loaded gif
	 */
	public static Gif load(InputStream inputStream) {
		return apply(new Gif(), it -> it.read(inputStream));
	}

	/**
	 * Loads a Gif from a input stream
	 *
	 * @param inputStream The input stream to load from
	 *
	 * @return The loaded gif
	 */
	public static Gif load(ImageInputStream inputStream) {
		return apply(new Gif(), it -> it.read(inputStream));
	}


	/**
	 * Lazy loads a Gif from a file
	 *
	 * @param file The file to load from
	 *
	 * @return The lazy loaded gif
	 */
	public static LazyStruct<Gif> lazyLoad(File file) {
		return lazy(() -> load(file));
	}

	/**
	 * Lazy loads a Gif from an input stream
	 *
	 * @param inputStream The input stream to load from
	 *
	 * @return The lazy loaded gif
	 */
	public static LazyStruct<Gif> lazyLoad(InputStream inputStream) {
		return lazy(() -> load(inputStream));
	}

	/**
	 * Lazy loads a Gif from an input stream
	 *
	 * @param inputStream The input stream to load from
	 *
	 * @return The lazy loaded gif
	 */
	public static LazyStruct<Gif> lazyLoad(ImageInputStream inputStream) {
		return lazy(() -> load(inputStream));
	}


	/**
	 * Lazy loads a Gif Element from a file
	 *
	 * @param file The file to load from
	 *
	 * @return The lazy loaded gif element
	 */
	public static LazyStruct<Element.GifElem> lazyLoadElem(File file) {
		return lazy(() -> new Element.GifElem(new Pos(0, 0), load(file)));
	}

	/**
	 * Lazy loads a Gif Element from an input stream
	 *
	 * @param inputStream The input stream to load from
	 *
	 * @return The lazy loaded gif element
	 */
	public static LazyStruct<Element.GifElem> lazyLoadElem(InputStream inputStream) {
		return lazy(() -> new Element.GifElem(new Pos(0, 0), load(inputStream)));
	}

	/**
	 * Lazy loads a Gif Element from an input stream
	 *
	 * @param inputStream The input stream to load from
	 *
	 * @return The lazy loaded gif element
	 */
	public static LazyStruct<Element.GifElem> lazyLoadElem(ImageInputStream inputStream) {
		return lazy(() -> new Element.GifElem(new Pos(0, 0), load(inputStream)));
	}

}
