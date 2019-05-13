package me.camdenorrb.zambiegame.entity.impl;

import me.camdenorrb.zambiegame.engine.gif.Gif;
import me.camdenorrb.zambiegame.engine.gui.impl.element.impl.Element;
import me.camdenorrb.zambiegame.entity.struct.EntityStruct;
import me.camdenorrb.zambiegame.impl.pos.Pos;
import me.camdenorrb.zambiegame.struct.game.ZambieGameStruct;
import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;
import me.camdenorrb.zambiegame.utils.ResourceUtils;

import java.util.Collections;
import java.util.List;

import static me.camdenorrb.zambiegame.utils.GifUtils.lazyLoad;


public class Cloud extends EntityStruct {

	protected static final String CLOUD_PATH = "huemin/cloud.gif";

	protected final LazyStruct<Gif> cloudGif = lazyLoad(ResourceUtils.get(CLOUD_PATH));

	protected Element.GifElem body = new Element.GifElem(pos, cloudGif.get());


	public Cloud(ZambieGameStruct game) {
		super(Integer.MAX_VALUE, game);
	}

	@Override
	public String getName() {
		return "Cloud";
	}

	@Override
	public List<Element> getParts() {
		return Collections.singletonList(body);
	}

	@Override
	public Pos getCenter() {
		return null;
	}

	@Override
	protected void onTick() {

	}

	@Override
	public double getWidth() {
		return body.getSize().getWidth();
	}

	@Override
	public double getHeight() {
		return body.getSize().getHeight();
	}

	@Override
	public boolean isInRange(Pos pos) {
		// TODO
		return false;
	}
}
