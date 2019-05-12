package me.camdenorrb.zambiegame.engine.manager;

import me.camdenorrb.zambiegame.base.ModuleBase;
import me.camdenorrb.zambiegame.engine.gif.processing.PGif;
import me.camdenorrb.zambiegame.utils.TimerUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class PGifManager implements ModuleBase {

	private boolean isEnabled;

	private Set<PGif> gifs = new HashSet<>();


	private final Runnable loopTask = () -> {
		new HashSet<>(gifs).forEach((it) -> {
			it.updatePixels();
		});
		//gifs
	};


	@Override
	public void enable() {

		if (isEnabled) return;

		isEnabled = true;
	}

	@Override
	public void disable() {

		if (!isEnabled) return;

		isEnabled = false;
	}


	@Override
	public boolean isEnabled() {
		return isEnabled;
	}


	public boolean addGif(PGif gif) {
		return gifs.add(gif);
	}

	public boolean remGif(PGif gif) {
		return gifs.remove(gif);
	}


	public boolean addGif(PGif... gifs) {
		return this.gifs.addAll(Arrays.asList(gifs));
	}

	public boolean addGif(Collection<? extends PGif> gifs) {
		return this.gifs.addAll(gifs);
	}


	public boolean remGif(PGif... gifs) {
		return this.gifs.removeAll(Arrays.asList(gifs));
	}

	public boolean remGif(Collection<PGif> gifs) {
		return this.gifs.removeAll(gifs);
	}


	public Set<PGif> getGifs() {
		return Collections.unmodifiableSet(gifs);
	}

}
