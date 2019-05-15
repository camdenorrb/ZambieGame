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


public final class GifUtils {

	private GifUtils() {}


	public static Gif load(File file) {
		return apply(new Gif(), it -> it.read(file));
	}

	public static Gif load(InputStream inputStream) {
		return apply(new Gif(), it -> it.read(inputStream));
	}

	public static Gif load(ImageInputStream inputStream) {
		return apply(new Gif(), it -> it.read(inputStream));
	}


	public static LazyStruct<Gif> lazyLoad(File file) {
		return lazy(() -> load(file));
	}

	public static LazyStruct<Gif> lazyLoad(InputStream inputStream) {
		return lazy(() -> load(inputStream));
	}

	public static LazyStruct<Gif> lazyLoad(ImageInputStream inputStream) {
		return lazy(() -> load(inputStream));
	}

	public static LazyStruct<Element.GifElem> lazyLoadElem(File file) {
		return lazy(() -> new Element.GifElem(new Pos(0, 0), load(file)));
	}

	public static LazyStruct<Element.GifElem> lazyLoadElem(InputStream inputStream) {
		return lazy(() -> new Element.GifElem(new Pos(0, 0), load(inputStream)));
	}

	public static LazyStruct<Element.GifElem> lazyLoadElem(ImageInputStream inputStream) {
		return lazy(() -> new Element.GifElem(new Pos(0, 0), load(inputStream)));
	}

}
